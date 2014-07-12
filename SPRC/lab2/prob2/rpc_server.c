#include <stdio.h>
#include <rpc/rpc.h>
#include "arb.h"
struct Arbr {
    int val;
    struct Arbr *left;
    struct Arbr *right;
};

void d(struct Arbr*);

static struct Arbr *arb = NULL;
int *insert_1_svc(int *p, struct svc_req* dummy) {

    static int x = 3;
    struct Arbr *cpy = arb;

    printf("here");
    if (cpy == NULL) {

        arb = malloc (sizeof(struct Arbr));
        arb->left = NULL;
        arb->right = NULL;
        arb->val = *p;
    } else {
        while (1) {
            if (*p > cpy->val) {
                if (cpy->right != NULL) {
                    cpy = cpy->right;
                } else {
                    cpy->right = malloc(sizeof(struct Arbr));
                    cpy->right->val = *p;
                    cpy->right->left = NULL;
                    cpy->right->right = NULL;
                    break;
                }
            } else {
                if (cpy->left != NULL) {
                    cpy = cpy->left;
                } else {
                    cpy->left = malloc(sizeof(struct Arbr));
                    cpy->left->val = *p;
                    cpy->left->left = NULL;
                    cpy->left->right = NULL;
                    break;
                }
            }
        }
    }
    printf("here");
    return &x;
}


int * display_1_svc(void *p, struct svc_req* dummy) {
    static int s;
    s = 3;
    d(arb);
    return &s;
}

void d(struct Arbr *arb) {
    if (arb == NULL) {
        return;
    } else {
        d(arb->left);
        printf("%d ", arb->val);
        d(arb->right);
    }
}
