/*
 * virtual machine simulator header
 *
 * 2011, Operating Systems
 */

#ifndef VMSIM_H_
#define VMSIM_H_	1

#ifdef __cplusplus
extern "C" {
#endif

#include "common.h"

/* virtual memory mapping encapsulation; initialized by vm_alloc */
typedef struct vm_map {
	w_ptr_t start;
	w_handle_t ram_handle;
	w_handle_t swap_handle;
} vm_map_t;

#if defined _WIN32
#if defined DLL_EXPORTS
#define FUNC_DECL_PREFIX __declspec(dllexport)
#else
#define FUNC_DECL_PREFIX __declspec(dllimport)
#endif
#else
#define FUNC_DECL_PREFIX
#endif

/* initialize and cleanup library -- consider exception handler */
FUNC_DECL_PREFIX w_boolean_t vmsim_init(void);
FUNC_DECL_PREFIX w_boolean_t vmsim_cleanup(void);

/*
 * allocate physical pages in RAM mapped by virt_pages in swap
 * map is to be filled with start address and handle to RAM and swap
 * files
 */

FUNC_DECL_PREFIX w_boolean_t vm_alloc(w_size_t num_pages, w_size_t num_frames, vm_map_t *map);

/*
 * free space previously allocated with vm_alloc
 * start is the start address of the previously allocated area
 *
 * implementation has to also close handles corresponding to RAM and
 * swap files
 */

FUNC_DECL_PREFIX w_boolean_t vm_free(w_ptr_t start);

#if defined _WIN32
  LONG vmsim_exception_handler(PEXCEPTION_POINTERS eptr);
#elif defined __linux__
  void vmsim_exception_handler(int sig, siginfo_t *siginfo, void *aux);
#else
  #error "Unknown platform"
#endif

#ifdef __cplusplus
}
#endif

#endif /* VMSIM_H_ */
