#ifndef CRC
#define CRC

#include "lib.h"
#define CRCCCITT 0x1021

typedef unsigned short int word;
typedef unsigned char byte;

typedef struct {
        int package;
        char *char_pos;
        int length;
        msg* pack;
} elem;

typedef struct {
        elem** elements;
        int max;
        int size;
} _buffer;

_buffer make_buffer (int length)
{
        _buffer new;
        new.size = 0;
        new.max = length;
        new.elements = calloc (length, sizeof (elem*));
        return new;
}


elem* element_at (_buffer buffer, int i)
{
        return buffer.elements[i % buffer.max];
}
void remove_at (_buffer* buffer, int i)
{
        elem* to_remove = buffer->elements[i % buffer->max];
        free(to_remove);
        buffer->elements[i % buffer->max] = NULL;
        buffer->size --;
}
void put_at (_buffer* b, int p, msg x)
{
        elem* new = malloc (sizeof (elem));
        b->elements[p % b->max] = new;
        new->pack = malloc (sizeof (msg));
        *new->pack = x;
        if (x.type != 0)
        {
                new->char_pos = new->pack->payload + new->pack->type;
                new->length = strlen (new->char_pos);
        }
        b->size ++;
}

int size (_buffer* b)
{
        return b->size;
}

word calculcrc (word data, word genpoli, word acum)
{
  int i;
  data <<= 8;
  for (i=8; i>0; i--)
     {
     if ((data^acum) & 0x8000)
       acum = (acum << 1) ^ genpoli;
     else
       acum <<= 1;
     data <<= 1;
     }
  return acum;
}


word* tabelcrc (word poli )
{
  word* ptabelcrc;
  int i;
  if ((ptabelcrc = (word*) malloc (256 * sizeof (word))) == NULL)
     return NULL;
  for (i=0; i<256; i++)
    ptabelcrc [i] = calculcrc (i, poli, 0);
  return ptabelcrc;
}

void crctabel (word data, word* acum, word* tabelcrc)
  {
  word valcomb;
  valcomb = ((*acum >> 8) ^ data) & 0x00FF;
  *acum = ((*acum & 0x00FF) << 8) ^ tabelcrc [valcomb];
}
void compute_crc (msg *r, word* tabel)
{
	byte *x = (byte*)r;
	word acum = 0;
	int i;
	for (i = 0; i < sizeof (*r); i++)
	{
		crctabel (x[i], &acum, tabel);
	}
	r->payload[1398] = acum & 0xFF;
	r->payload[1399] = (acum >> 8) & 0xFF;
	
}

int check_crc (msg *r, word* tabel)
{
	byte b1, b2;
	msg *r1 = malloc(sizeof(msg));
	*r1 = *r;
	byte *x = (byte*) r1;
	word acum = 0;
	b1 = r1->payload[1399];
	b2 = r1->payload[1398];
	r1->payload[1399] = 0;
	r1->payload[1398] = 0;
	int i;
	for (i = 0; i < sizeof(*r); i++)
	{
		crctabel( x[i], &acum, tabel);
	}
	crctabel(b1, &acum, tabel);
        crctabel(b2, &acum, tabel);

	if (acum == 0)
		return 1;
	return 0;
}
#endif
