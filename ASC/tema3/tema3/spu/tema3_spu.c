#include <stdio.h>
#include <string.h>
#include <spu_intrinsics.h>
#include <spu_mfcio.h>
#include <libmisc.h>

#define waitag(t) mfc_write_tag_mask(1<<t); mfc_read_tag_status_all();

#define SCALE_FACTOR		4
#define NUM_CHANNELS		3 //red, green and blue
#define MAX_COLOR			255
#define NUM_IMAGES_WIDTH	4 // the final big image has 4 small images
#define NUM_IMAGES_HEIGHT	4 // on the width and 4 on the height

//macros for easily accessing data
#define GET_COLOR_VALUE(img, i, j, k) \
	((img)->data[((i) * (img->width) + (j)) * NUM_CHANNELS + (k)])
#define RED(img, i, j)		GET_COLOR_VALUE(img, i, j, 0)
#define GREEN(img, i, j)	GET_COLOR_VALUE(img, i, j, 1)
#define BLUE(img, i, j)		GET_COLOR_VALUE(img, i, j, 2)

//macro for easily getting how much time has passed between two events
#define GET_TIME_DELTA(t1, t2) ((t2).tv_sec - (t1).tv_sec + \
				((t2).tv_usec - (t1).tv_usec) / 1000000.0)
	
struct image{
	unsigned int width, height;
	unsigned char *data __attribute__ ((aligned(128)));
};

typedef struct {
    int max_frames;
    int height;
    int width;
    struct image *frames __attribute__ ((aligned(128)));
    int ctx;
    unsigned char *big_image_location;
} pointers_t;

unsigned int notify  __attribute__((aligned(128)));

void alloc_image(struct image* img){		
	img->data = malloc_align(NUM_CHANNELS * img->width * img->height * sizeof(char), 7);
    memset(img->data, 0, NUM_CHANNELS * img->width * img->height * sizeof(char));
	if (img->data == NULL){
        printf("Calloc failed\n");
	}
}


int main(unsigned long long speid, unsigned long long argp, unsigned long long envp)
{

	int i=0,j=0, write_offset, local_offset, image_offset, k,l,m;
	pointers_t p __attribute__ ((aligned(128)));
    struct image local_image __attribute__ ((aligned(128)));

    struct image new_image __attribute__ ((aligned(128)));
    unsigned char *init_data;
    unsigned char *temp_data;

    int offset;
	uint32_t tag_id = mfc_tag_reserve();
	if (tag_id==MFC_TAG_INVALID){
		printf("SPU: ERROR can't allocate tag ID\n"); return -1;
	}
    

    mfc_get((void*)&p, (uint32_t)argp, (int)envp, tag_id, 0, 0);
    waitag(tag_id);

    new_image.height = p.height / SCALE_FACTOR;
    new_image.width = p.width / SCALE_FACTOR;

  
    alloc_image(&new_image);

    temp_data = malloc_align(3 * p.width * sizeof(char), 7);
    init_data = malloc_align(3 * p.width * sizeof(char), 7);


    unsigned char *buf = malloc_align(16, 7);
    int buf_offset;
    int nv = (p.width*3)/(16/sizeof(char));
    
   
    offset = 0;
    for (i = 0; i < p.max_frames; i++) {
        /* get the ith image structure*/
        mfc_get(&local_image, (uint32_t)(p.frames + offset), sizeof(struct image), tag_id, 0, 0);
        waitag(tag_id);
        
        image_offset = 0;

        /* Compute average for each scaled pixel from initial image using vectors*/
        for (k = 0; k < (int)new_image.height; k++) {
            memset(init_data, 0, NUM_CHANNELS * p.width * sizeof(char));
            vector unsigned char *init;

            for (j = 0; j < SCALE_FACTOR; j++) {
                mfc_get(temp_data,  (uint32_t)(local_image.data + image_offset), p.width * 3, tag_id, 0, 0);
                waitag(tag_id);
                init = (vector unsigned char*)init_data;
                vector unsigned char *temp = (vector unsigned char*)temp_data;
                for (l = 0; l < nv; l++) {
                    init[l] = spu_avg(init[l],temp[l]);
                }
                image_offset += p.width * 3;
            }

            for (l = 0; l < (int)new_image.width; l++) {
                int rval = 0, gval = 0, bval = 0;
                for (m = 0; m < SCALE_FACTOR * 3; m += 3) {
                   rval += init[0][l * 12 + m];
                   gval += init[0][l * 12 + m + 1];
                   bval += init[0][l * 12 + m + 2];
                }
                new_image.data[(k * new_image.width + l) * 3] = rval/4;
                new_image.data[(k * new_image.width + l) * 3 + 1] = gval/4;
                new_image.data[(k * new_image.width + l) * 3 + 2] = bval/4;
            }
        }

            
        /*for (k = 0; k < new_image.height; k++) {
            for (j = 0; j < new_image.width; j++) {
			    int rval = 0, gval = 0, bval = 0;
                local_offset = 0;
                image_offset = 0;
                for (m = k * SCALE_FACTOR; m < (k + 1) * SCALE_FACTOR; m++) {
				    for (n = j * SCALE_FACTOR; n < (j + 1) * SCALE_FACTOR; n++) {
                        buf_offset = 0;
                        
                        while (((uint32_t)(buf + buf_offset) & 0x0000000f) != 
                            ((uint32_t)(local_image.data + (m * p.width + n) * NUM_CHANNELS) & 0x0000000f)) {
                            buf_offset += 1;
                        }

                        mfc_get(buf + buf_offset, (uint32_t)(local_image.data + (m * p.width + n) * NUM_CHANNELS), 1, tag_id, 0, 0);
                        waitag(tag_id);
                        mfc_get(buf + buf_offset + 1, (uint32_t)(local_image.data + (m * p.width + n) * NUM_CHANNELS) + 1, 1, tag_id, 0, 0);
                        waitag(tag_id);
                        mfc_get(buf + buf_offset + 2, (uint32_t)(local_image.data + (m * p.width + n) * NUM_CHANNELS) + 2, 1, tag_id, 0, 0);
                        waitag(tag_id);

                        rval += *(buf + buf_offset);
                        gval += *(buf + buf_offset + 1);
                        bval += *(buf + buf_offset + 2);
                    }
                }
                new_image.data[(k * new_image.width + j) * NUM_CHANNELS] = rval / SCALE_FACTOR / SCALE_FACTOR;
                new_image.data[(k * new_image.width + j) * NUM_CHANNELS + 1] = gval / SCALE_FACTOR / SCALE_FACTOR;
                new_image.data[(k * new_image.width + j) * NUM_CHANNELS + 2] = bval / SCALE_FACTOR / SCALE_FACTOR;

            }
        }*/


        /* write scaled image in the big image position */

        write_offset = 0;
        local_offset = 0;
        for (j = 0; j < (int)new_image.height; j++) {
            for (l = 0; l < (int)(new_image.width * 3)/4; l++) {
                buf_offset = 0;
                while (((uint32_t)(buf + buf_offset) & 0x0000000f) != ((uint32_t)(p.big_image_location + write_offset) & 0x0000000f)) {
                    buf_offset += 4;
                }
                memcpy(buf + buf_offset, new_image.data + local_offset, 1);
                mfc_put(buf + buf_offset, (uint32_t)(p.big_image_location + write_offset), 4, tag_id, 0, 0);
                waitag(tag_id);
                local_offset += 4;
                write_offset += 4;
            }
            write_offset += (p.width - new_image.width) * 3;
        }

        while(spu_stat_out_intr_mbox()==0);
        spu_write_out_intr_mbox(notify);

        while (spu_stat_in_mbox()==0);
        spu_read_in_mbox();

        offset += 1;
    }
    mfc_tag_release(tag_id);
	return 0;
}
