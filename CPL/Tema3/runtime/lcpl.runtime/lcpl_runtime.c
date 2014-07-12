#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "lcpl_runtime.h"	

// String objects representing predefined class names
struct TString NObject = { &RString, 6, "Object" };
struct TString NString = { &RString, 6, "String" };
struct TString NIO = { &RString, 2, "IO" };

// runtime type information of predefined classes 
struct __lcpl_rtti RObject = {
	&NObject, 
	sizeof(struct TObject), 
	0,
	{ Object_init, M6_Object_abort, M6_Object_typeName, M6_Object_copy }
};

struct __lcpl_rtti RString = {
	&NString, 
	sizeof(struct TString), 
	&RObject,
	{ String_init, M6_Object_abort, M6_Object_typeName, M6_Object_copy, M6_String_length, M6_String_toInt, M6_String_substring, M6_String_concat, M6_String_equal }
};

struct __lcpl_rtti RIO = {
	&NIO, 
	sizeof(struct TIO), 
	&RObject,
	{ IO_init, M6_Object_abort, M6_Object_typeName, M6_Object_copy, M2_IO_in, M2_IO_out }
};

// Object
void Object_init(struct TObject *self) {}
void M6_Object_abort(struct TObject *self) 
{
	exit(1);
}
struct TString* M6_Object_typeName(struct TObject *self) 
{
	return self->rtti->name;
}
struct TObject * M6_Object_copy(struct TObject *self) 
{
	struct TObject* newObject = (struct TObject*)malloc(self->rtti->size);
	memcpy(newObject, self, self->rtti->size);
	return newObject; 
}

// IO 
void IO_init(struct TIO *self) {}
struct TString *M2_IO_in(struct TIO *self) 
{ 
	char str[256];

	scanf("%s", str);
	
	struct TString* string = __lcpl_new(&RString);
	
	string->length = strlen(str);
	string->string = (char*)calloc(string->length + 1, 1);
	
	strcpy(string->string, str);
	
	return string;
}
struct TIO* M2_IO_out(struct TIO *self, struct TString *string) 
{ 
	printf("%s", string->string);
	return self;
}

// String
void String_init(struct TString * self) 
{ 
	self->string = ""; 
}
int M6_String_length(struct TString *self) 
{ 
	return self->length; 
}
int M6_String_toInt(struct TString *self) 
{ 
	return atoi(self->string); 
}

struct TString* M6_String_substring(struct TString* self, int start, int final)
{
	if((start < 0) || (start > final) || (final > self->length))
	{
		printf("Runtime error : Substring indices out of bounds.\n");
		exit(1);
	}
	struct TString* substring = __lcpl_new(&RString);
	substring->length = final - start;
	substring->string = (char*)calloc(final - start + 1, 1);
	strncpy(substring->string, self->string + start, final - start);
	
	return substring;
}

struct TString* M6_String_concat(struct TString* self, struct TString* other)
{
	int length = self->length + other->length;
	struct TString* concat = __lcpl_new(&RString);
	
	concat->length = length;
	concat->string = (char*) calloc(length + 1, 1);
	
	strcpy(concat->string, self->string);
	strcat(concat->string, other->string);
	
	return concat;
}

int M6_String_equal(struct TString* self, struct TString* other)
{
	return (strcmp(self->string, other->string) == 0);
}

// additional runtime support
void* __lcpl_new(struct __lcpl_rtti* rtti)
{
	typedef void (*constructor)(void*);

	void* object = malloc(rtti->size);
	memset(object, 0, rtti->size);
	((struct TObject*)object)->rtti = rtti;

	constructor c = (constructor)(rtti->vtable[0]);
	c(object);
	
	return object;	
}

void __lcpl_checkNull(void *ptr)
{
	if (!ptr)
	{
		printf("Runtime error : Calling a method of a void object.\n");
		exit(1);
	}
}

void* __lcpl_cast(void* sourceObject, struct __lcpl_rtti* destRtti)
{
  if (sourceObject == 0)
    return 0;
    
	struct __lcpl_rtti* sourceRtti = ((struct TObject*)sourceObject)->rtti;
	struct __lcpl_rtti* rtti = sourceRtti;
	
	while(rtti != NULL) {
			if(rtti == destRtti) {
				return sourceObject;
			}
			rtti = rtti->parent;
	}
	printf("Runtime error : Unable to convert %s into %s.\n", sourceRtti->name->string, destRtti->name->string);
	exit(1);	
}

struct TString* __lcpl_intToString(int value)
{
	char str[32];
	
	sprintf(str, "%d", value);
	
	struct TString* string = __lcpl_new(&RString);
	string->length = strlen(str);
	string->string = (char*) calloc(string->length + 1, 1);
	strcpy(string->string, str);
	
	return string;
}

extern void startup(void);
int main()
{
	startup();
	return 0;
}
