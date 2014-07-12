/* Ciocan Mihai
 * 334CA
 */
#include "vmsim.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define NUM_ENTRIES 20

w_exception_handler_t initial_handler;
w_mem_table_entry_t vm_table[NUM_ENTRIES];
int size;
int page_size;

/* Return in mem the vm_table_entry which starts at location 'start'*/
static w_boolean_t get_vm_entry(w_ptr_t start, w_mem_table_entry_t **mem)
{
    int i, num_pages;
    w_ptr_t start_i;
    for (i = 0; i < size; i++) {
        start_i = vm_table[i].map->start;
        num_pages = vm_table[i].num_pages;
        if (start_i <= start && start <= start_i + (num_pages * page_size)) {
            *mem = &vm_table[i];
            return TRUE;
        }
    }
    return FALSE;
}

/* Demand ram page*/
static w_boolean_t demand_page(siginfo_t *info,
                        w_mem_table_entry_t *mem,
                        w_page_table_entry_t *page_entry)
{
    int i;
    for (i = 0; i < mem->num_frames; i++) {
        if (mem->frames[i].pte == NULL) {
            munmap (page_entry->start, page_size);
            mmap (page_entry->start, page_size, PROT_READ, 
                MAP_SHARED, mem->map->ram_handle, i * page_size);
            page_entry->prev_state = page_entry->state;
            page_entry->state = STATE_IN_RAM;
            page_entry->dirty = FALSE;
            mem->frames[i].pte = page_entry;
            mem->ram_occupied++;
            break;
        }
    }
    return TRUE;
}

static w_boolean_t swap_out(siginfo_t *info,
                    w_mem_table_entry_t *mem,
                    w_page_table_entry_t *page_entry)
{
    int i;
    for (i = 0; i < mem->num_pages; i++) {
        if (mem->swap_page_state[i]) {
            continue;
        }
        break;
    }
    /* Copy data in swap page */
    w_set_file_pointer(mem->map->swap_handle, i * page_size);
    w_write_file(mem->map->swap_handle, mem->frames[0].pte->start, page_size);

    /* Set memory protection to PROT_NONE */
    munmap(mem->frames[0].pte->start, page_size);
    mmap (mem->frames[0].pte->start, page_size, 
        PROT_NONE, MAP_ANONYMOUS | MAP_SHARED, -1, 0);

    /* Set fields */
    mem->frames[0].pte->state = STATE_IN_SWAP;
    mem->frames[0].pte->prev_state = STATE_IN_RAM;
    mem->frames[0].pte->swap_page = i;
    mem->swap_page_state[i] = 1;
    mem->frames[0].pte->protection = PROTECTION_NONE;
    mem->ram_occupied --;
    mem->frames[0].pte = NULL;
    return TRUE;
}

/* Copy data in swap page in ram page*/
static w_boolean_t swap_in (siginfo_t *info, 
                    w_mem_table_entry_t *mem,
                    w_page_table_entry_t *page_entry)
{
    w_set_file_pointer(mem->map->swap_handle,page_entry->swap_page * page_size);
    mprotect(page_entry->start, page_size, PROT_READ | PROT_WRITE);
    w_read_file(mem->map->swap_handle, page_entry->start, page_size);
    mprotect(page_entry->start, page_size, PROT_READ);
    mem->swap_page_state[page_entry->swap_page] = 0;
    return TRUE;
}

void vmsim_exception_handler(int signum, siginfo_t *info, void *context)
{
    w_mem_table_entry_t *mem;
    w_page_table_entry_t *page_entry;
    get_vm_entry (info->si_addr, &mem);
    page_entry = &mem->page_entries[(info->si_addr -mem->map->start)/page_size];

    if (page_entry->protection == PROTECTION_NONE) {
        page_entry->protection = PROTECTION_READ;
        if (page_entry->state != STATE_IN_RAM) {
            /* Check ram space */
            if (mem->ram_occupied != mem->num_frames) {
                /* Enough room - demand page */
                demand_page(info, mem, page_entry);
            } else {
                /* Swap out if dirty and demand page */
                if (mem->frames[0].pte->dirty == TRUE ||
                    mem->frames[0].pte->prev_state == STATE_NOT_ALLOC) {
                    swap_out(info, mem, page_entry);
                } else {
                    mem->frames[0].pte = NULL;
                    mem->ram_occupied --;
                }
                demand_page(info, mem, page_entry);
            }
            /* If prev. location was in swap - swap in */
            if (page_entry->prev_state == STATE_IN_SWAP) {
                swap_in(info, mem, page_entry);
            }
        }
    } else {
        /* Set write protection */
        mprotect(page_entry->start, page_size, PROT_READ | PROT_WRITE);
        page_entry->protection = PROTECTION_WRITE; 
        page_entry->dirty = TRUE;
    }
}

/* Initialize vmsim with vmsim_exception_handler */
FUNC_DECL_PREFIX w_boolean_t vmsim_init()
{
    w_get_current_exception_handler(&initial_handler);
    w_set_exception_handler(vmsim_exception_handler);
    page_size = w_get_page_size();
    return TRUE;
}

/* Unmap memory and free local structs */
FUNC_DECL_PREFIX w_boolean_t vm_free(w_ptr_t start)
{
    w_mem_table_entry_t *mem;
    if (start == NULL) {
        return FALSE;
    }
    if (get_vm_entry(start, &mem) == FALSE) {
        return FALSE;
    }

    if (munmap(start, mem->num_pages * page_size) < 0) {
        return FALSE;
    }
    close(mem->map->swap_handle);
    close(mem->map->ram_handle);
    w_delete_file(mem->RAM_NAME);
    w_delete_file(mem->SWAP_NAME);

    free(mem->frames);
    free(mem->page_entries);

    return TRUE;
}

/* Allocate memory */
FUNC_DECL_PREFIX w_boolean_t vm_alloc(w_size_t num_pages, w_size_t num_frames, vm_map_t *map)
{
    int i;
    if (num_frames > num_pages) {
        return FALSE;
    }
    map->start = mmap(NULL, num_pages * page_size, PROT_NONE,
                        MAP_ANONYMOUS | MAP_SHARED, -1, 0);
    if (map->start == MAP_FAILED) {
        printf ("map error");
        return FALSE;
    }
    strcpy(vm_table[size].RAM_NAME, "RAM_FILE_XXXXXX");
    strcpy(vm_table[size].SWAP_NAME, "SWAP_FILE_XXXXXX");

    map->ram_handle = mkstemp(vm_table[size].RAM_NAME);
    map->swap_handle = mkstemp(vm_table[size].SWAP_NAME);

    if (map->ram_handle == -1 || map->swap_handle == -1) {
        return FALSE;
    }
    /* Resize files */
    ftruncate(map->ram_handle, num_frames * page_size);
    ftruncate(map->swap_handle, num_pages * page_size);

    /* Initialize structs */
    vm_table[size].frames = calloc(num_frames, sizeof (struct frame));
    vm_table[size].swap_page_state = calloc (num_pages, sizeof (int));
    vm_table[size].num_frames = num_frames;
    vm_table[size].num_pages = num_pages;
    vm_table[size].page_entries = calloc(num_pages, sizeof (w_page_table_entry_t));
    vm_table[size].map = map;

    for (i = 0; i < num_pages; i++) {
        vm_table[size].page_entries[i].start = map->start + (i * page_size);
        vm_table[size].page_entries[i].protection = PROTECTION_NONE;
        vm_table[size].page_entries[i].state = STATE_NOT_ALLOC;
        vm_table[size].page_entries[i].dirty = FALSE;
    }
    size++;
    return TRUE;
}

/* Remove exception handler */
FUNC_DECL_PREFIX w_boolean_t vmsim_cleanup()
{
	w_set_exception_handler(initial_handler);
    return TRUE;
}

