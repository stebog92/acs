
#ifndef __LCPL_RUNTIME__
#define __LCPL_RUNTIME__

#include <stdlib.h>

struct __lcpl_rtti
{
	struct TString* name;			
	size_t size;					
	struct __lcpl_rtti *parent;		
	void* vtable[];					
};

struct TObject { struct __lcpl_rtti *rtti; };
struct TString { struct __lcpl_rtti *rtti; int length; char *string; };
struct TIO { struct __lcpl_rtti *rtti; };

struct __lcpl_rtti RObject, RString, RIO;

void Object_init(struct TObject *);
void M6_Object_abort(struct TObject *);
struct TString* M6_Object_typeName(struct TObject *);
struct TObject * M6_Object_copy(struct TObject *);

void IO_init(struct TIO *);
struct TString *M2_IO_in(struct TIO *);
struct TIO* M2_IO_out(struct TIO *self, struct TString *string);

void String_init(struct TString * self);
int M6_String_length(struct TString *);
int M6_String_toInt(struct TString *);
struct TString* M6_String_substring(struct TString* self, int start, int final);
struct TString* M6_String_concat(struct TString* self, struct TString* other);
int M6_String_equal(struct TString* self, struct TString* other);

void* __lcpl_new(struct __lcpl_rtti*);
void __lcpl_checkNull(void *);
void* __lcpl_cast(void*, struct __lcpl_rtti*);
struct TString* __lcpl_intToString(int value);

void startup(void);

#endif 
