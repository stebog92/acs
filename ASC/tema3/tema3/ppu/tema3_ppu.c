#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/time.h>
#include <libspe2.h>
#include <errno.h>
#include <pthread.h>
#include <libmisc.h>

extern spe_program_handle_t tema3_spu;
#define PRINT_ERR_MSG_AND_EXIT(format, ...) \
	{ \
	fprintf(stderr, "%s:%d: " format, __func__, __LINE__, ##__VA_ARGS__); \
	fflush(stderr); \
	exit(1); \
	}

#define MAX_SPU_THREADS     16
#define NUM_STREAMS 		16	
#define MAX_FRAMES			100	//there are at most 100 frames available
#define MAX_PATH_LEN		256
#define IMAGE_TYPE_LEN 		2
#define SMALL_BUF_SIZE 		16
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
				
//structure that is used to store an image into memory
struct image{
	unsigned int width, height;
	unsigned char *data __attribute__ ((aligned(128)));
};

typedef struct {
    int max_frames;
    int height;
    int width;
    struct image *frames __attribute__ ((aligned(128)));
    spe_context_ptr_t ctx;
    unsigned char *big_image_location;
} pointers_t;



//read a character from a file specified by a descriptor
char read_char(int fd, char* path){
	char c;
	int bytes_read;
	
	bytes_read = read(fd, &c, 1);
	if (bytes_read != 1){
		PRINT_ERR_MSG_AND_EXIT("Error reading from %s\n", path);
	}
	
	return c;
}

//allocate image data
void alloc_image(struct image* img){		
	img->data = malloc_align(NUM_CHANNELS * img->width * img->height * sizeof(char), 7);
	
	if (!img->data){
		PRINT_ERR_MSG_AND_EXIT("Calloc failed\n");
	}
}

//free image data
void free_image(struct image* img){
	free(img->data);
}

/* read from fd until character c is found
 * result will be atoi(str) where str is what was read before c was
 * found 
 */
unsigned int read_until(int fd, char c, char* path){

	char buf[SMALL_BUF_SIZE];
	int i;
	unsigned int res;
	
	i = 0;
	memset(buf, 0, SMALL_BUF_SIZE);
	buf[i] = read_char(fd, path);
	while (buf[i] != c){
		i++;
		if (i >= SMALL_BUF_SIZE){
			PRINT_ERR_MSG_AND_EXIT("Unexpected file format for %s\n", path);
		}
		buf[i] = read_char(fd, path);
	}
	res = atoi(buf);
	if (res <= 0) {
		PRINT_ERR_MSG_AND_EXIT("Result is %d when reading from %s\n", 
			res, path);
	}
	
	return res;
}


//read a pnm image
void read_pnm(char* path, struct image* img){
	int fd, bytes_read, bytes_left;
	char image_type[IMAGE_TYPE_LEN];
	unsigned char *ptr __attribute__ ((aligned(128)));
	unsigned int max_color;
	
	fd = open(path, O_RDONLY);
	
	if (fd < 0){
		PRINT_ERR_MSG_AND_EXIT("Error opening %s\n", path);
		exit(1);
	}
	
	//read image type; should be P6
	bytes_read = read(fd, image_type, IMAGE_TYPE_LEN);
	if (bytes_read != IMAGE_TYPE_LEN){
		PRINT_ERR_MSG_AND_EXIT("Couldn't read image type for %s\n", path);
	}
	if (strncmp(image_type, "P6", IMAGE_TYPE_LEN)){
		PRINT_ERR_MSG_AND_EXIT("Expecting P6 image type for %s. Got %s\n", 
			path, image_type);
	}
	
	//read \n
	read_char(fd, path);
		
	//read width, height and max color value
	img->width = read_until(fd, ' ', path);
	img->height = read_until(fd, '\n', path);
	max_color = read_until(fd, '\n', path);
	if (max_color != MAX_COLOR){
		PRINT_ERR_MSG_AND_EXIT("Unsupported max color value %d for %s\n", 
			max_color, path);
	}
		
	//allocate image data
	alloc_image(img);
	
	//read the actual data 
	bytes_left = img->width * img->height * NUM_CHANNELS;
	ptr = img->data;
	while (bytes_left > 0){
		bytes_read = read(fd, ptr, bytes_left);
		if (bytes_read <= 0){
			PRINT_ERR_MSG_AND_EXIT("Error reading from %s\n", path);
		}
		ptr += bytes_read;
		bytes_left -= bytes_read;
	}
			
	close(fd);
}

void write_pnm(char* path, struct image* img){
	int fd, bytes_written, bytes_left;
	char buf[32];
	unsigned char* ptr;
		
	fd = open(path, O_WRONLY | O_CREAT | O_TRUNC, 0644);
	if (fd < 0){
		PRINT_ERR_MSG_AND_EXIT("Error opening %s\n", path);
	}
		
	//write image type, image width, height and max color
	sprintf(buf, "P6\n%d %d\n%d\n", img->width, img->height, MAX_COLOR);
	ptr = (unsigned char*)buf;
	bytes_left = strlen(buf);
	while (bytes_left > 0){
		bytes_written = write(fd, ptr, bytes_left);
		if (bytes_written <= 0){
			PRINT_ERR_MSG_AND_EXIT("Error writing to %s\n", path);
		}
		bytes_left -= bytes_written;
		ptr += bytes_written;
	}
	
	//write the actual data
	ptr = img->data;
	bytes_left = img->width * img->height * NUM_CHANNELS;
	while (bytes_left > 0){
		bytes_written = write(fd, ptr, bytes_left);
		if (bytes_written <= 0){
			PRINT_ERR_MSG_AND_EXIT("Error writing to %s\n", path);
		}
		bytes_left -= bytes_written;
		ptr += bytes_written;
	}
	
	close(fd);
}


void *ppu_pthread_function(void *thread_arg) {

	spe_context_ptr_t ctx;
	pointers_t *arg = (pointers_t *) thread_arg;

    ctx = arg->ctx;
	/* Load SPE program into context */
	if (spe_program_load (ctx, &tema3_spu)) {
        perror ("Failed loading program");
        exit (1);
    }

	/* Run SPE context */
	unsigned int entry = SPE_DEFAULT_ENTRY;

	if (spe_context_run(ctx, &entry, 0, arg, sizeof (pointers_t), NULL) < 0) {  
		perror ("Failed running context");
		exit (1);
	} 
   	
    /* Destroy context */
	if (spe_context_destroy(ctx) != 0) {
        perror("Failed destroying context");
        exit (1);
    }
	pthread_exit(NULL);
}


int main(int argc, char** argv){
    int i, j, num_frames, spu_threads, offset, nevents;
    char input_path[MAX_PATH_LEN];
	char output_path[MAX_PATH_LEN];
	struct image input[NUM_STREAMS][MAX_FRAMES] __attribute__ ((aligned(128)));
    struct image big_image __attribute__ ((aligned(128)));
    unsigned int data;

    char buf[MAX_PATH_LEN];

    struct timeval t1, t2, t3, t4;
	double scale_time = 0, total_time = 0;



    pthread_t threads[MAX_SPU_THREADS];
    pointers_t thread_arg[MAX_SPU_THREADS] __attribute__ ((aligned(128)));

    spe_event_unit_t pevents[MAX_SPU_THREADS], event_received;
    spe_event_handler_ptr_t event_handler;
    event_handler = spe_event_handler_create();

    spu_threads = spe_cpu_info_get(SPE_COUNT_USABLE_SPES, -1);
    if (spu_threads > MAX_SPU_THREADS) spu_threads = MAX_SPU_THREADS;


    if (argc != 4){
		printf("Usage: ./serial input_path output_path num_frames\n");
		exit(1);
	}

	strncpy(input_path, argv[1], MAX_PATH_LEN - 1);
	strncpy(output_path, argv[2], MAX_PATH_LEN - 1);
	num_frames = atoi(argv[3]);
    
    if (num_frames > MAX_FRAMES)
		num_frames = MAX_FRAMES;


    for (j = 0; j < NUM_STREAMS; j++){
		//read the input images
        for (i = 0; i < num_frames; i++) {
			sprintf(buf, "%s/stream%02d/image%d.pnm", input_path, j + 1, i + 1);
			read_pnm(buf, &input[j][i]);
		}
    }
    big_image.height = (input[0][0].height / SCALE_FACTOR) * NUM_IMAGES_HEIGHT;
	big_image.width = (input[0][0].width / SCALE_FACTOR) * NUM_IMAGES_WIDTH;

    int scaled_height = input[0][0].height / SCALE_FACTOR;
    int scaled_width = input[0][0].width / SCALE_FACTOR;

    alloc_image(&big_image);
    gettimeofday(&t3, NULL);


    /* Create thread for each SPE context */
    for (i = 0; i < spu_threads; i++) {
        if ((thread_arg[i].ctx = spe_context_create (SPE_EVENTS_ENABLE, NULL)) == NULL) {
            perror ("Failed creating context");
            exit (1);
        }

        /* Compute the where the spu should write the scaled image */
        offset = (i / NUM_IMAGES_HEIGHT) * (scaled_height * scaled_width * NUM_CHANNELS * NUM_IMAGES_WIDTH)
                + (i % NUM_IMAGES_WIDTH) * (scaled_width * NUM_CHANNELS);

        thread_arg[i].frames = &input[i][0];
        thread_arg[i].max_frames = num_frames;
        thread_arg[i].height = input[i][0].height;
        thread_arg[i].width = input[i][0].width;
        thread_arg[i].big_image_location = big_image.data + offset;

        pevents[i].events = SPE_EVENT_OUT_INTR_MBOX;
        pevents[i].spe = thread_arg[i].ctx;
        spe_event_handler_register(event_handler, &pevents[i]);

        if (pthread_create (&threads[i], NULL, &ppu_pthread_function, &thread_arg[i])) {
            perror ("Failed creating thread");
            exit (1);
        }
    }

    for (i = 0; i < num_frames; i++) {
        printf("Processing Frame %d\n", i + 1);
        for(j = 0; j < MAX_SPU_THREADS; j++) {
            gettimeofday(&t1, NULL);
            //wait for spu notification that he finished scaling frame i
            nevents = spe_event_wait(event_handler,&event_received,1,-1);
            if(nevents<0) {
                printf("SPU:Error la spe_event_wait(res=%d)\n",nevents);
            } else {
                if (event_received.events & SPE_EVENT_OUT_INTR_MBOX) {
                    while (spe_out_intr_mbox_status(event_received.spe) < 1);
                    spe_out_intr_mbox_read(event_received.spe, &data, 1, SPE_MBOX_ANY_NONBLOCKING);
                }
            }
            gettimeofday(&t2, NULL);
            scale_time += GET_TIME_DELTA(t1, t2);
        }
        // WRITE IMAGE
        sprintf(buf, "%s/result%d.pnm", output_path, i + 1);
        write_pnm(buf, &big_image);

        //Send notification to spu to begin scaling next image
        for(j = 0; j < MAX_SPU_THREADS; j++) {
            if(spe_in_mbox_write(pevents[j].spe, &data, 1, SPE_MBOX_ANY_NONBLOCKING) <0) {
                perror("ERROR, writing messages to spe failed\n");
            }
        }
    }

    /* Wait for SPU-thread to complete execution.  */
    for (i = 0; i < spu_threads; i++) {
        if (pthread_join (threads[i], NULL)) {
            perror("Failed pthread_join");
            exit (1);
        }
    }
    gettimeofday(&t4, NULL);
	total_time += GET_TIME_DELTA(t3, t4);
				
	printf("Scale time: %lf\n", scale_time);
	printf("Total time: %lf\n", total_time);

	return 0;
}
