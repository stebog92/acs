CC = cl
EXE = tema0.exe
CFLAGS = /W3 -D_CRT_NONSTDC_NO_DEPRECATE -D_CRT_SECURE_NO_DEPRECATE /nologo

build: main.obj hashtable.obj hash.obj
	$(CC) $(CFLAGS) /Fe$(EXE) main.obj hashtable.obj hash.obj

hash.obj: hash.c

hashtable.obj: hashtable.c
	$(CC) $(CFLAGS) /Fo$@ /c $**

main.obj: main.c

clean:
	del $(EXE)
