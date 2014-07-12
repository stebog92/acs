#include <linux/module.h>
#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/cdev.h>
#include <asm/uaccess.h>
#include <linux/sched.h>
#include <linux/wait.h>
#include <linux/pci.h>
#include <linux/netdevice.h>
#include <linux/etherdevice.h>
#include <linux/delay.h>
#include <linux/interrupt.h>
#include <linux/mii.h>

MODULE_DESCRIPTION("Ixia challenge");
MODULE_AUTHOR("Mihai Ciocan");
MODULE_LICENSE("GPL");

#define LOG_LEVEL	KERN_ALERT

#define MODULE_NAME	"e100-ix.ko"
#define DRV_NAME        "e100"
#define DEVICE_NUM      2
#define E100_NAPI_WEIGHT 16

static DEFINE_PCI_DEVICE_TABLE(e100_ix_id_table) = {
        {
                PCI_DEVICE(0x8086, 0x1209)
        },
        {0}
};

#define UCODE_SIZE			134

static struct net_device *devices[2];
struct cb {
	__le16 status;
	__le16 command;
	__le32 link;
	union {
		u8 iaaddr[ETH_ALEN];
		__le32 ucode[UCODE_SIZE];
		//struct config config;
		//struct multi multi;
		struct {
			u32 tbd_array;
			u16 tcb_byte_count;
			u8 threshold;
			u8 tbd_count;
			struct {
				__le32 buf_addr;
				__le16 size;
				u16 eol;
			} tbd;
		} tcb;
		__le32 dump_buffer_addr;
	} u;
	struct cb *next, *prev;
	dma_addr_t dma_addr;
	struct sk_buff *skb;
};


enum scb_stat_ack {
	stat_ack_not_ours    = 0x00,
	stat_ack_sw_gen      = 0x04,
	stat_ack_rnr         = 0x10,
	stat_ack_cu_idle     = 0x20,
	stat_ack_frame_rx    = 0x40,
	stat_ack_cu_cmd_done = 0x80,
	stat_ack_not_present = 0xFF,
	stat_ack_rx = (stat_ack_sw_gen | stat_ack_rnr | stat_ack_frame_rx),
	stat_ack_tx = (stat_ack_cu_idle | stat_ack_cu_cmd_done),
};

enum ru_state  {
	RU_SUSPENDED = 0,
	RU_RUNNING	 = 1,
	RU_UNINITIALIZED = -1,
};

enum scb_cmd_lo {
    cuc_nop        = 0x00,
    ruc_start      = 0x01,
    ruc_load_base  = 0x06,
    cuc_start      = 0x10,
    cuc_resume     = 0x20,
    cuc_dump_addr  = 0x40,
    cuc_dump_stats = 0x50,
    cuc_load_base  = 0x60,
    cuc_dump_reset = 0x70,
};

struct rx {
	struct rx *next, *prev;
	struct sk_buff *skb;
	dma_addr_t dma_addr;
};

enum cb_command {
	cb_nop    = 0x0000,
	cb_iaaddr = 0x0001,
	cb_config = 0x0002,
	cb_multi  = 0x0003,
	cb_tx     = 0x0004,
	cb_ucode  = 0x0005,
	cb_dump   = 0x0006,
	cb_tx_sf  = 0x0008,
	cb_tx_nc  = 0x0010,
	cb_cid    = 0x1f00,
	cb_i      = 0x2000,
	cb_s      = 0x4000,
	cb_el     = 0x8000,
};

struct rfd {
	__le16 status;
	__le16 command;
	__le32 link;
	__le32 rbd;
	__le16 actual_size;
	__le16 size;
};

struct csr {
        struct {
                u8 status;
                u8 stat_ack;
                u8 cmd_lo;
                u8 cmd_hi;
                u32 gen_ptr;
        } scb;
        u32 port;
        u16 flash_ctrl;
        u8 eeprom_ctrl_lo;
        u8 eeprom_ctrl_hi;
        u32 mdi_ctrl;
        u32 rx_dma_count;
};


enum port {
    software_reset  = 0x0000,
    selftest        = 0x0001,
    selective_reset = 0x0002,
};

enum eeprom_op {
        op_write = 0x05,
        op_read  = 0x06,
        op_ewds  = 0x10,
        op_ewen  = 0x13,
};

enum eeprom_ctrl_lo {
        eesk = 0x01,
        eecs = 0x02,
        eedi = 0x04,
        eedo = 0x08,
};

enum scb_cmd_hi {
        irq_mask_none = 0x00,
        irq_mask_all  = 0x01,
        irq_sw_gen    = 0x02,
};

MODULE_DEVICE_TABLE(pci, e100_ix_id_table);

struct ix_priv {
        struct net_device *netdev;
        struct csr __iomem *csr;
        u16 eeprom_wc;
        __le16 eeprom[256];
        u32 msg_enable                          ____cacheline_aligned;
	    struct rx *rxs				            ____cacheline_aligned;

        struct pci_dev *pdev;
	    struct rfd blank_rfd;

	spinlock_t cmd_lock;
        spinlock_t lock;

	spinlock_t cb_lock			____cacheline_aligned;
        int rx_int_enabled;
        struct napi_struct napi;
        struct rx *rx_to_use;
        struct rx *rx_to_clean;
        enum ru_state ru_running;
        
	enum scb_cmd_lo cuc_cmd;
	struct cb *cb_to_use;
	struct cb *cb_to_send;
	struct cb *cb_to_clean;
        struct mii_if_info mii;


};
static inline void e100_write_flush(struct ix_priv *ix_priv)
{
        /* Flush previous PCI writes through intermediate bridges
         * by doing a benign read */
        (void)ioread8(&ix_priv->csr->scb.status);
}

static int e100_exec_cmd(struct ix_priv *ix_priv, u8 cmd, dma_addr_t dma_addr)
{
	unsigned long flags;
	unsigned int i;
	int err = 0;

	spin_lock_irqsave(&ix_priv->lock, flags);

	if (unlikely(cmd != cuc_resume))
		iowrite32(dma_addr, &ix_priv->csr->scb.gen_ptr);

    printk(KERN_ALERT "enabled irq\n");
	iowrite8(cmd, &ix_priv->csr->scb.cmd_lo);

err_unlock:
	spin_unlock_irqrestore(&ix_priv->lock, flags);
	return err;
}

static inline void e100_start_receiver(struct ix_priv *ix_priv, struct rx *rx)
{

    //printk(LOG_LEVEL "here\n");
	if (!ix_priv->rxs) return;
	if (RU_SUSPENDED != ix_priv->ru_running) return;

	/* handle init time starts */
	if (!rx) rx = ix_priv->rxs;

	/* (Re)start RU if suspended or idle and RFA is non-NULL */
	if (rx->skb) {
        printk(LOG_LEVEL "here\n");
		e100_exec_cmd(ix_priv, ruc_start, rx->dma_addr);
		ix_priv->ru_running = RU_RUNNING;
	}
}

static __le16 e100_eeprom_read(struct ix_priv *ix_priv, u16 *addr_len, u16 addr)
{
        u32 cmd_addr_data;
        u16 data = 0;
        u8 ctrl;
        int i;

        cmd_addr_data = ((op_read << *addr_len) | addr) << 16;

        /* Chip select */
        iowrite8(eecs | eesk, &ix_priv->csr->eeprom_ctrl_lo);
        e100_write_flush(ix_priv); udelay(4);

        /* Bit-bang to read word from eeprom */
        for (i = 31; i >= 0; i--) {
                ctrl = (cmd_addr_data & (1 << i)) ? eecs | eedi : eecs;
                iowrite8(ctrl, &ix_priv->csr->eeprom_ctrl_lo);
                e100_write_flush(ix_priv); udelay(4);

                iowrite8(ctrl | eesk, &ix_priv->csr->eeprom_ctrl_lo);
                e100_write_flush(ix_priv); udelay(4);

                /* Eeprom drives a dummy zero to EEDO after receiving
                 * complete address.  Use this to adjust addr_len. */
                ctrl = ioread8(&ix_priv->csr->eeprom_ctrl_lo);
                if (!(ctrl & eedo) && i > 16) {
                        *addr_len -= (i - 16);
                        i = 17;
                }

                data = (data << 1) | (ctrl & eedo ? 1 : 0);
        }

        /* Chip deselect */
        iowrite8(0, &ix_priv->csr->eeprom_ctrl_lo);
        e100_write_flush(ix_priv); udelay(4);
        return cpu_to_le16(data);
};


static int e100_eeprom_load(struct ix_priv *ix_priv)
{
        u16 addr, addr_len = 8, checksum = 0;

        /* Try reading with an 8-bit addr len to discover actual addr len */
        e100_eeprom_read(ix_priv, &addr_len, 0);
        ix_priv->eeprom_wc = 1 << addr_len;

        for (addr = 0; addr < ix_priv->eeprom_wc; addr++) {
                ix_priv->eeprom[addr] = e100_eeprom_read(ix_priv, &addr_len, addr);
                if (addr < ix_priv->eeprom_wc - 1)
                        checksum += le16_to_cpu(ix_priv->eeprom[addr]);
        }

        /* The checksum, stored in the last word, is calculated such that
         * the sum of words should be 0xBABA */
        /*if (cpu_to_le16(0xBABA - checksum) != ix_priv->eeprom[ix_priv->eeprom_wc - 1]) {
                netif_err(ix_priv, probe, ix_priv->netdev, "EEPROM corrupted\n");
                //if (!eeprom_bad_csum_allow)
                  //      return -EAGAIN;
        }*/

        return 0;
}

static void e100_enable_irq(struct ix_priv *ix_priv)
{
    unsigned long flags;

    spin_lock_irqsave(&ix_priv->lock, flags);
    iowrite8(irq_mask_none, &ix_priv->csr->scb.cmd_hi);
    e100_write_flush(ix_priv);
    printk(LOG_LEVEL "int enabled");
    spin_unlock_irqrestore(&ix_priv->lock, flags);

}


static void e100_disable_irq(struct ix_priv *ix_priv)
{
    unsigned long flags;

    spin_lock_irqsave(&ix_priv->lock, flags);
    iowrite8(irq_mask_all, &ix_priv->csr->scb.cmd_hi);
    e100_write_flush(ix_priv);
    printk(LOG_LEVEL "int disabled");
    spin_unlock_irqrestore(&ix_priv->lock, flags);
}

static void e100_hw_reset(struct ix_priv *ix_priv)
{
    /* Put CU and RU into idle with a selective reset to get
     * device off of PCI bus */
    iowrite32(selective_reset, &ix_priv->csr->port);
    e100_write_flush(ix_priv); udelay(20);

    /* Now fully reset device */
    iowrite32(software_reset, &ix_priv->csr->port);
    e100_write_flush(ix_priv); udelay(20);

    /* Mask off our interrupt line - it's unmasked after reset */
    e100_disable_irq(ix_priv);
}

static irqreturn_t e100_intr(int irq, void *dev_id)
{
    struct net_device *netdev = dev_id;
    struct ix_priv *ix_priv = netdev_priv(netdev);
    u8 stat_ack = ioread8(&ix_priv->csr->scb.stat_ack);

    netif_printk(ix_priv, intr, KERN_DEBUG, ix_priv->netdev,
            "stat_ack = 0x%02X\n", stat_ack);

    if (stat_ack == stat_ack_not_ours ||
            stat_ack == stat_ack_not_present)	
        return IRQ_NONE;


    iowrite8(stat_ack, &ix_priv->csr->scb.stat_ack);
    if (stat_ack & stat_ack_rnr)
		ix_priv->ru_running = RU_SUSPENDED;

    if (likely(napi_schedule_prep(&ix_priv->napi))) {
        e100_disable_irq(ix_priv);
        __napi_schedule(&ix_priv->napi);
    }
    printk(LOG_LEVEL "interrupt\n");
    return IRQ_HANDLED;
}

static int e100_rx_alloc_skb(struct ix_priv *ix_priv, struct rx *rx)
{
    printk(LOG_LEVEL "allok skb\n");

	if (!(rx->skb = netdev_alloc_skb_ip_align(ix_priv->netdev, 1538)))
		return -ENOMEM;

    ix_priv->blank_rfd.command = 0;
	ix_priv->blank_rfd.rbd = cpu_to_le32(0xFFFFFFFF);
    ix_priv->blank_rfd.size = cpu_to_le16(ETH_FCS_LEN);


	/* Init, and map the RFD. */
	skb_copy_to_linear_data(rx->skb, &ix_priv->blank_rfd, sizeof(struct rfd));
	rx->dma_addr = pci_map_single(ix_priv->pdev, rx->skb->data,
		1538, PCI_DMA_BIDIRECTIONAL);

	if (pci_dma_mapping_error(ix_priv->pdev, rx->dma_addr)) {
		dev_kfree_skb_any(rx->skb);
		rx->skb = NULL;
		rx->dma_addr = 0;
		return -ENOMEM;
	}

	if (rx->prev->skb) {
		struct rfd *prev_rfd = (struct rfd *)rx->prev->skb->data;
		put_unaligned_le32(rx->dma_addr, &prev_rfd->link);
		pci_dma_sync_single_for_device(ix_priv->pdev, rx->prev->dma_addr,
			sizeof(struct rfd), PCI_DMA_BIDIRECTIONAL);
	}

	return 0;
}

static int e100_rx_alloc_list(struct ix_priv *ix_priv)
{
	struct rx *rx;
	unsigned int i, count = 256;
	struct rfd *before_last;


	ix_priv->rx_to_use = ix_priv->rx_to_clean = NULL;
	ix_priv->ru_running = RU_UNINITIALIZED;

	if (!(ix_priv->rxs = kcalloc(count, sizeof(struct rx), GFP_ATOMIC))) {
        printk(LOG_LEVEL "cannot allocate\n");
		return -ENOMEM;
    }

	for (rx = ix_priv->rxs, i = 0; i < count; rx++, i++) {
		rx->next = (i + 1 < count) ? rx + 1 : ix_priv->rxs;
		rx->prev = (i == 0) ? ix_priv->rxs + count - 1 : rx - 1;
		if (e100_rx_alloc_skb(ix_priv, rx)) {
			//e100_rx_clean_list(ix_priv);
			return -ENOMEM;
		}
	}
	rx = ix_priv->rxs->prev->prev;
	before_last = (struct rfd *)rx->skb->data;
	before_last->command |= cpu_to_le16(cb_el);
	before_last->size = 0;
	pci_dma_sync_single_for_device(ix_priv->pdev, rx->dma_addr,
		sizeof(struct rfd), PCI_DMA_BIDIRECTIONAL);

	ix_priv->rx_to_use = ix_priv->rx_to_clean = ix_priv->rxs;
	ix_priv->ru_running = RU_SUSPENDED;

	return 0;
}

static int e100_ix_open(struct net_device *dev)
{

        int err = 0;
        struct ix_priv *ix_priv = netdev_priv(dev);
	    netif_carrier_off(dev);
        printk(LOG_LEVEL "Entered : e100_ix_open\n");
        if ((err = e100_rx_alloc_list(ix_priv)))
		    return err;

	    e100_start_receiver(ix_priv, NULL);
        if ((err = request_irq(ix_priv->pdev->irq, e100_intr, IRQF_SHARED,
                                        ix_priv->netdev->name, ix_priv->netdev))) {
                printk(LOG_LEVEL "Request irq failed\n");
                goto err_no_irq;
        }
       
        netif_wake_queue(ix_priv->netdev);
        napi_enable(&ix_priv->napi);
        e100_enable_irq(ix_priv);

err_no_irq:
        return err;
}

int e100_ix_stop (struct net_device *dev)
{

        return 0;
}
static netdev_tx_t e100_ix_start_xmit(struct sk_buff *skb,
                struct net_device *netdev)
{

        dma_addr_t dma_addr;

        struct cb *stop_cb;
        unsigned long flags;
        struct ix_priv *ix_priv = netdev_priv(netdev);
        int err = 0;

        spin_lock_irqsave(&ix_priv->cb_lock, flags);

        stop_cb = ix_priv->cb_to_use;
        ix_priv->cb_to_use = stop_cb->next;
        stop_cb->skb = skb;

        dma_addr = pci_map_single(ix_priv->pdev,
                        skb->data, skb->len, PCI_DMA_TODEVICE);

        while (ix_priv->cb_to_send != ix_priv->cb_to_use) {
                e100_exec_cmd(ix_priv, ix_priv->cuc_cmd,
                                ix_priv->cb_to_send->dma_addr);
                ix_priv->cuc_cmd = cuc_resume;
                ix_priv->cb_to_send = ix_priv->cb_to_send->next;
        }

        spin_unlock_irqrestore(&ix_priv->cb_lock, flags);

        return NETDEV_TX_OK;
}



static int ix_poll(struct napi_struct *napi, int budget)
{
        struct ix_priv *ix_priv = container_of(napi, struct ix_priv, napi);
	    unsigned int work_done = 0;

            mii_check_link(&ix_priv->mii);
    
	    //e100_rx_clean(ix_priv, &work_done, budget);
	    //e100_tx_clean(ix_priv);

	    if (work_done < budget) {
		    napi_complete(napi);
		    e100_enable_irq(ix_priv);
            printk(LOG_LEVEL "napi complete%d\n", budget);
	    }

        printk(LOG_LEVEL "here\n");
        return work_done;
}

static void ix_rx_ints(struct net_device *dev, int enable)
{
        struct ix_priv *priv = netdev_priv(dev);
        priv->rx_int_enabled = enable;
}


static const struct net_device_ops e100_ix_netdev_ops = {
        .ndo_open       = e100_ix_open,
        .ndo_stop       = e100_ix_stop,
        .ndo_start_xmit = e100_ix_start_xmit,
};


static int e100_probe(struct pci_dev *pdev, const struct pci_device_id *ent)
{
        int err, i;
        struct ix_priv *ix_priv[2];
        int use_io = 1;

        for (i = 0; i < DEVICE_NUM; i++) {
                if (!(devices[i] = alloc_etherdev(sizeof(struct ix_priv)))) {
                        printk(LOG_LEVEL "Cannot alloc memory, aborting\n");
                        return -ENOMEM;
                }
        }

        if ((err = pci_enable_device(pdev))) {
                printk(LOG_LEVEL "Cannot enable PCI device, aborting\n");
                goto err_out_free_dev;
        }

        if ((err = pci_request_regions(pdev, DRV_NAME))) {
                printk(LOG_LEVEL "Cannot obtain PCI resources, aborting\n");
                goto err_out_free_dev;
        }


        printk(LOG_LEVEL "Net device enabled\n");


        for (i = 0; i < DEVICE_NUM; i++) {

                /*if (!(devices[i] = alloc_etherdev(sizeof(struct ix_priv)))) {
                        printk(LOG_LEVEL "Cannot alloc memory, aborting\n");
                        return -ENOMEM;
                }*/
        
                devices[i]->netdev_ops = &e100_ix_netdev_ops;
                ix_priv[i] = netdev_priv(devices[i]);
                ix_priv[i]->pdev = pdev;
                ix_priv[i]->netdev = devices[i];

                pci_set_drvdata(pdev, devices[0]);
                
                netif_napi_add(devices[i], &ix_priv[i]->napi, ix_poll, E100_NAPI_WEIGHT);
                
                ix_priv[i]->csr = pci_iomap(pdev, 0, sizeof(struct csr));

                if (!ix_priv[i]->csr) {
                    printk(LOG_LEVEL "Cannot map device registers\n"); 
                    err = -ENOMEM;
                    goto err_out_free;
                }


                if ((err = e100_eeprom_load(ix_priv[i]))) {
                        printk(LOG_LEVEL "Cannot load, aborting\n");
                        goto err_out_free;
                }
                memcpy(devices[i]->dev_addr, ix_priv[i]->eeprom, ETH_ALEN);

                printk(LOG_LEVEL "address %s\n", devices[i]->dev_addr);
                spin_lock_init(&ix_priv[i]->lock);
                spin_lock_init(&ix_priv[i]->cmd_lock);


                pci_set_master(pdev);

                ix_rx_ints(devices[i], 1);
                if (i == 1) {
                        devices[i]->dev_addr[ETH_ALEN - 1] =+ 1;
                }

                strcpy(devices[i]->name, "ixeth");
                devices[i]->name[5] = '0' + i;

                ix_priv[i].mii

                if ((err = register_netdev(devices[i]))) {
                        printk(LOG_LEVEL "Cannot register net device, aborting\n");
                        goto err_out_free;
                }
                printk(LOG_LEVEL "ixeth%d registered\n", i);
        }

        return 0;
err_out_free:
        // e100_free(ix_priv)
err_out_free_dev:
        //free_netdev(netdev)
        return err;
}

static void e100_remove(struct pci_dev *pdev) 
{
        struct net_device *netdev = pci_get_drvdata(pdev);
        if (netdev) {
                unregister_netdev(netdev);
                free_netdev(netdev);
        }
}

static struct pci_driver e100_ix_driver = {
        .name = DRV_NAME,
        .id_table = e100_ix_id_table,
        .probe = e100_probe,
        .remove = e100_remove,
};

static int e100_ix_init(void)
{
        printk(LOG_LEVEL "In init\n");
        return pci_register_driver(&e100_ix_driver);
}

static void e100_ix_exit(void)
{
        pci_unregister_driver(&e100_ix_driver);
}

module_init(e100_ix_init);
module_exit(e100_ix_exit);
