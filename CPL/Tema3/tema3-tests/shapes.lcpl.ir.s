	.file	"shapes.lcpl.ir"
	.text
	.globl	Shape_init
	.align	16, 0x90
	.type	Shape_init,@function
Shape_init:                             # @Shape_init
.Leh_func_begin0:
# BB#0:
	pushl	%esi
.Ltmp0:
	subl	$8, %esp
.Ltmp1:
	movl	16(%esp), %esi
	movl	%esi, 4(%esp)
	movl	%esi, (%esp)
	calll	Object_init
	movl	$0, 4(%esi)
	movl	$0, 8(%esi)
	movl	$0, 12(%esi)
	movl	$0, 16(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp2:
	.size	Shape_init, .Ltmp2-Shape_init
.Leh_func_end0:

	.globl	Ellipse_init
	.align	16, 0x90
	.type	Ellipse_init,@function
Ellipse_init:                           # @Ellipse_init
.Leh_func_begin1:
# BB#0:
	pushl	%esi
.Ltmp3:
	subl	$8, %esp
.Ltmp4:
	movl	16(%esp), %esi
	movl	%esi, 4(%esp)
	movl	%esi, (%esp)
	calll	Shape_init
	movl	$0, 4(%esi)
	movl	$0, 8(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp5:
	.size	Ellipse_init, .Ltmp5-Ellipse_init
.Leh_func_end1:

	.globl	Main_init
	.align	16, 0x90
	.type	Main_init,@function
Main_init:                              # @Main_init
.Leh_func_begin2:
# BB#0:
	subl	$12, %esp
.Ltmp6:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	IO_init
	addl	$12, %esp
	ret
.Ltmp7:
	.size	Main_init, .Ltmp7-Main_init
.Leh_func_end2:

	.globl	M5_Shape_init
	.align	16, 0x90
	.type	M5_Shape_init,@function
M5_Shape_init:                          # @M5_Shape_init
.Leh_func_begin3:
# BB#0:
	subl	$20, %esp
.Ltmp8:
	movl	24(%esp), %eax
	movl	%eax, 16(%esp)
	movl	28(%esp), %eax
	movl	%eax, 12(%esp)
	movl	32(%esp), %eax
	movl	%eax, 8(%esp)
	movl	36(%esp), %eax
	movl	%eax, 4(%esp)
	movl	40(%esp), %eax
	movl	%eax, (%esp)
	movl	16(%esp), %eax
	movl	12(%esp), %ecx
	movl	%ecx, 4(%eax)
	movl	16(%esp), %eax
	movl	8(%esp), %ecx
	movl	%ecx, 8(%eax)
	movl	12(%esp), %eax
	addl	4(%esp), %eax
	movl	16(%esp), %ecx
	movl	%eax, 12(%ecx)
	movl	8(%esp), %eax
	addl	(%esp), %eax
	movl	16(%esp), %ecx
	movl	%eax, 16(%ecx)
	addl	$20, %esp
	ret
.Ltmp9:
	.size	M5_Shape_init, .Ltmp9-M5_Shape_init
.Leh_func_end3:

	.globl	M5_Shape_print
	.align	16, 0x90
	.type	M5_Shape_print,@function
M5_Shape_print:                         # @M5_Shape_print
.Leh_func_begin4:
# BB#0:
	pushl	%ebx
.Ltmp10:
	pushl	%edi
.Ltmp11:
	pushl	%esi
.Ltmp12:
	subl	$16, %esp
.Ltmp13:
	movl	32(%esp), %eax
	movl	%eax, 12(%esp)
	movl	36(%esp), %esi
	movl	%esi, 8(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	12(%esp), %eax
	movl	4(%eax), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	$_string_constant.12, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.15, 4(%esp)
	calll	M6_String_concat
	movl	%eax, %ebx
	movl	12(%esp), %eax
	movl	8(%eax), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%ebx, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.14, 4(%esp)
	calll	M6_String_concat
	movl	%eax, %ebx
	movl	12(%esp), %eax
	movl	12(%eax), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%ebx, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.15, 4(%esp)
	calll	M6_String_concat
	movl	%eax, %ebx
	movl	12(%esp), %eax
	movl	16(%eax), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%ebx, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.16, 4(%esp)
	calll	M6_String_concat
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$16, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	ret
.Ltmp14:
	.size	M5_Shape_print, .Ltmp14-M5_Shape_print
.Leh_func_end4:

	.globl	M5_Shape_area
	.align	16, 0x90
	.type	M5_Shape_area,@function
M5_Shape_area:                          # @M5_Shape_area
.Leh_func_begin5:
# BB#0:
	pushl	%eax
.Ltmp15:
	movl	8(%esp), %ecx
	movl	%ecx, (%esp)
	movl	12(%ecx), %eax
	movl	16(%ecx), %edx
	subl	8(%ecx), %edx
	subl	4(%ecx), %eax
	imull	%edx, %eax
	popl	%edx
	ret
.Ltmp16:
	.size	M5_Shape_area, .Ltmp16-M5_Shape_area
.Leh_func_end5:

	.globl	M7_Ellipse_init
	.align	16, 0x90
	.type	M7_Ellipse_init,@function
M7_Ellipse_init:                        # @M7_Ellipse_init
.Leh_func_begin6:
# BB#0:
	pushl	%ebx
.Ltmp17:
	pushl	%edi
.Ltmp18:
	pushl	%esi
.Ltmp19:
	subl	$48, %esp
.Ltmp20:
	movl	64(%esp), %eax
	movl	%eax, 44(%esp)
	movl	68(%esp), %eax
	movl	%eax, 40(%esp)
	movl	72(%esp), %eax
	movl	%eax, 36(%esp)
	movl	76(%esp), %eax
	movl	%eax, 32(%esp)
	movl	80(%esp), %eax
	movl	%eax, 28(%esp)
	movl	44(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	40(%esp), %ecx
	movl	36(%esp), %edx
	movl	32(%esp), %edi
	movl	28(%esp), %ebx
	movl	%ebx, 16(%esp)
	movl	%edi, 12(%esp)
	movl	%edx, 8(%esp)
	movl	%ecx, 4(%esp)
	movl	%esi, (%esp)
	calll	*28(%eax)
	movl	32(%esp), %eax
	shrl	%eax
	movl	44(%esp), %ecx
	movl	%eax, 20(%ecx)
	movl	28(%esp), %eax
	shrl	%eax
	movl	44(%esp), %ecx
	movl	%eax, 24(%ecx)
	addl	$48, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	ret
.Ltmp21:
	.size	M7_Ellipse_init, .Ltmp21-M7_Ellipse_init
.Leh_func_end6:

	.globl	M7_Ellipse_setR
	.align	16, 0x90
	.type	M7_Ellipse_setR,@function
M7_Ellipse_setR:                        # @M7_Ellipse_setR
.Leh_func_begin7:
# BB#0:
	subl	$12, %esp
.Ltmp22:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	20(%esp), %eax
	movl	%eax, 4(%esp)
	movl	24(%esp), %eax
	movl	%eax, (%esp)
	movl	8(%esp), %eax
	movl	4(%esp), %ecx
	movl	%ecx, 20(%eax)
	movl	8(%esp), %eax
	movl	(%esp), %ecx
	movl	%ecx, 24(%eax)
	addl	$12, %esp
	ret
.Ltmp23:
	.size	M7_Ellipse_setR, .Ltmp23-M7_Ellipse_setR
.Leh_func_end7:

	.globl	M7_Ellipse_print
	.align	16, 0x90
	.type	M7_Ellipse_print,@function
M7_Ellipse_print:                       # @M7_Ellipse_print
.Leh_func_begin8:
# BB#0:
	pushl	%edi
.Ltmp24:
	pushl	%esi
.Ltmp25:
	subl	$20, %esp
.Ltmp26:
	movl	32(%esp), %eax
	movl	%eax, 16(%esp)
	movl	36(%esp), %eax
	movl	%eax, 12(%esp)
	movl	16(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	12(%esp), %ecx
	movl	%ecx, 4(%esp)
	movl	%esi, (%esp)
	calll	*32(%eax)
	movl	12(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	16(%esp), %eax
	movl	20(%eax), %ecx
	addl	24(%eax), %ecx
	shrl	%ecx
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	%ecx, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	$_string_constant.17, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.18, 4(%esp)
	calll	M6_String_concat
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$20, %esp
	popl	%esi
	popl	%edi
	ret
.Ltmp27:
	.size	M7_Ellipse_print, .Ltmp27-M7_Ellipse_print
.Leh_func_end8:

	.globl	M7_Ellipse_area
	.align	16, 0x90
	.type	M7_Ellipse_area,@function
M7_Ellipse_area:                        # @M7_Ellipse_area
.Leh_func_begin9:
# BB#0:
	pushl	%eax
.Ltmp28:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	20(%eax), %ecx
	imull	24(%eax), %ecx
	imull	$314, %ecx, %eax        # imm = 0x13A
	movl	$1374389535, %ecx       # imm = 0x51EB851F
	mull	%ecx
	movl	%edx, %eax
	shrl	$5, %eax
	popl	%edx
	ret
.Ltmp29:
	.size	M7_Ellipse_area, .Ltmp29-M7_Ellipse_area
.Leh_func_end9:

	.globl	M4_Main_print
	.align	16, 0x90
	.type	M4_Main_print,@function
M4_Main_print:                          # @M4_Main_print
.Leh_func_begin10:
# BB#0:
	pushl	%ebp
.Ltmp30:
	pushl	%ebx
.Ltmp31:
	pushl	%edi
.Ltmp32:
	pushl	%esi
.Ltmp33:
	subl	$28, %esp
.Ltmp34:
	movl	48(%esp), %eax
	movl	%eax, 24(%esp)
	movl	52(%esp), %esi
	movl	%esi, 20(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	24(%esp), %eax
	movl	%eax, (%esp)
	movl	$RIO, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	movl	24(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	20(%esp), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	calll	*20(%eax)
	movl	%eax, (%esp)
	movl	$_string_constant.22, 4(%esp)
	calll	M6_String_concat
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	movl	24(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	20(%esp), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	calll	*36(%eax)
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	$_string_constant.20, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.21, 4(%esp)
	calll	M6_String_concat
	movl	%eax, %ebx
	movl	20(%esp), %ebp
	movl	%ebp, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebp), %eax
	movl	%ebp, (%esp)
	calll	*36(%eax)
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%ebx, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.22, 4(%esp)
	calll	M6_String_concat
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$28, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	ret
.Ltmp35:
	.size	M4_Main_print, .Ltmp35-M4_Main_print
.Leh_func_end10:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin11:
# BB#0:
	pushl	%esi
.Ltmp36:
	subl	$40, %esp
.Ltmp37:
	movl	48(%esp), %eax
	movl	%eax, 36(%esp)
	movl	$REllipse, (%esp)
	calll	__lcpl_new
	movl	%eax, (%esp)
	movl	$RShape, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 32(%esp)
	movl	$RShape, (%esp)
	calll	__lcpl_new
	movl	%eax, 28(%esp)
	movl	$0, 24(%esp)
	movl	32(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$20, 16(%esp)
	movl	$10, 12(%esp)
	movl	$2, 8(%esp)
	movl	$1, 4(%esp)
	calll	*40(%eax)
	movl	28(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$100, 16(%esp)
	movl	$100, 12(%esp)
	movl	$0, 8(%esp)
	movl	$0, 4(%esp)
	calll	*28(%eax)
	movl	32(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*24(%eax)
	movl	%eax, (%esp)
	movl	$RShape, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 24(%esp)
	movl	%eax, (%esp)
	movl	$REllipse, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$10, 8(%esp)
	movl	$8, 4(%esp)
	calll	*44(%eax)
	movl	36(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%esp), %ecx
	movl	%ecx, 4(%esp)
	movl	%esi, (%esp)
	calll	*36(%eax)
	movl	36(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	28(%esp), %ecx
	movl	%ecx, 4(%esp)
	movl	%esi, (%esp)
	calll	*36(%eax)
	movl	36(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	24(%esp), %ecx
	movl	%ecx, 4(%esp)
	movl	%esi, (%esp)
	calll	*36(%eax)
	addl	$40, %esp
	popl	%esi
	ret
.Ltmp38:
	.size	M4_Main_main, .Ltmp38-M4_Main_main
.Leh_func_end11:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin12:
# BB#0:
	pushl	%esi
.Ltmp39:
	subl	$8, %esp
.Ltmp40:
	movl	$RMain, (%esp)
	calll	__lcpl_new
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*40(%eax)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp41:
	.size	startup, .Ltmp41-startup
.Leh_func_end12:

	.type	class_name.Shape,@object # @class_name.Shape
	.section	.rodata,"a",@progbits
class_name.Shape:
	.asciz	 "Shape"
	.size	class_name.Shape, 6

	.type	NShape,@object          # @NShape
	.data
	.globl	NShape
	.align	8
NShape:
	.long	RString
	.long	5                       # 0x5
	.long	class_name.Shape
	.size	NShape, 12

	.type	class_name.Ellipse,@object # @class_name.Ellipse
	.section	.rodata,"a",@progbits
class_name.Ellipse:
	.asciz	 "Ellipse"
	.size	class_name.Ellipse, 8

	.type	NEllipse,@object        # @NEllipse
	.data
	.globl	NEllipse
	.align	8
NEllipse:
	.long	RString
	.long	7                       # 0x7
	.long	class_name.Ellipse
	.size	NEllipse, 12

	.type	class_name.Main,@object # @class_name.Main
	.section	.rodata,"a",@progbits
class_name.Main:
	.asciz	 "Main"
	.size	class_name.Main, 5

	.type	NMain,@object           # @NMain
	.data
	.globl	NMain
	.align	8
NMain:
	.long	RString
	.long	4                       # 0x4
	.long	class_name.Main
	.size	NMain, 12

	.type	string_constant.strEmpty,@object # @string_constant.strEmpty
	.section	.rodata,"a",@progbits
string_constant.strEmpty:
	.zero	1
	.size	string_constant.strEmpty, 1

	.type	_string_constant.strEmpty,@object # @_string_constant.strEmpty
	.data
	.globl	_string_constant.strEmpty
	.align	8
_string_constant.strEmpty:
	.long	RString
	.long	0                       # 0x0
	.long	string_constant.strEmpty
	.size	_string_constant.strEmpty, 12

	.type	string_constant.0,@object # @string_constant.0
	.section	.rodata,"a",@progbits
	.globl	string_constant.0
string_constant.0:
	.asciz	 "("
	.size	string_constant.0, 2

	.type	_string_constant.0,@object # @_string_constant.0
	.data
	.globl	_string_constant.0
	.align	8
_string_constant.0:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.0
	.size	_string_constant.0, 12

	.type	string_constant.1,@object # @string_constant.1
	.section	.rodata,"a",@progbits
	.globl	string_constant.1
string_constant.1:
	.asciz	 ","
	.size	string_constant.1, 2

	.type	_string_constant.1,@object # @_string_constant.1
	.data
	.globl	_string_constant.1
	.align	8
_string_constant.1:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.1
	.size	_string_constant.1, 12

	.type	string_constant.2,@object # @string_constant.2
	.section	.rodata,"a",@progbits
	.globl	string_constant.2
string_constant.2:
	.asciz	 ")("
	.size	string_constant.2, 3

	.type	_string_constant.2,@object # @_string_constant.2
	.data
	.globl	_string_constant.2
	.align	8
_string_constant.2:
	.long	RString
	.long	2                       # 0x2
	.long	string_constant.2
	.size	_string_constant.2, 12

	.type	string_constant.3,@object # @string_constant.3
	.section	.rodata,"a",@progbits
	.globl	string_constant.3
string_constant.3:
	.asciz	 ","
	.size	string_constant.3, 2

	.type	_string_constant.3,@object # @_string_constant.3
	.data
	.globl	_string_constant.3
	.align	8
_string_constant.3:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.3
	.size	_string_constant.3, 12

	.type	string_constant.4,@object # @string_constant.4
	.section	.rodata,"a",@progbits
	.globl	string_constant.4
string_constant.4:
	.asciz	 ")"
	.size	string_constant.4, 2

	.type	_string_constant.4,@object # @_string_constant.4
	.data
	.globl	_string_constant.4
	.align	8
_string_constant.4:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.4
	.size	_string_constant.4, 12

	.type	string_constant.5,@object # @string_constant.5
	.section	.rodata,"a",@progbits
	.globl	string_constant.5
string_constant.5:
	.asciz	 "("
	.size	string_constant.5, 2

	.type	_string_constant.5,@object # @_string_constant.5
	.data
	.globl	_string_constant.5
	.align	8
_string_constant.5:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.5
	.size	_string_constant.5, 12

	.type	string_constant.6,@object # @string_constant.6
	.section	.rodata,"a",@progbits
	.globl	string_constant.6
string_constant.6:
	.asciz	 ","
	.size	string_constant.6, 2

	.type	_string_constant.6,@object # @_string_constant.6
	.data
	.globl	_string_constant.6
	.align	8
_string_constant.6:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.6
	.size	_string_constant.6, 12

	.type	string_constant.7,@object # @string_constant.7
	.section	.rodata,"a",@progbits
	.globl	string_constant.7
string_constant.7:
	.asciz	 ")("
	.size	string_constant.7, 3

	.type	_string_constant.7,@object # @_string_constant.7
	.data
	.globl	_string_constant.7
	.align	8
_string_constant.7:
	.long	RString
	.long	2                       # 0x2
	.long	string_constant.7
	.size	_string_constant.7, 12

	.type	string_constant.8,@object # @string_constant.8
	.section	.rodata,"a",@progbits
	.globl	string_constant.8
string_constant.8:
	.asciz	 ","
	.size	string_constant.8, 2

	.type	_string_constant.8,@object # @_string_constant.8
	.data
	.globl	_string_constant.8
	.align	8
_string_constant.8:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.8
	.size	_string_constant.8, 12

	.type	string_constant.9,@object # @string_constant.9
	.section	.rodata,"a",@progbits
	.globl	string_constant.9
string_constant.9:
	.asciz	 ")"
	.size	string_constant.9, 2

	.type	_string_constant.9,@object # @_string_constant.9
	.data
	.globl	_string_constant.9
	.align	8
_string_constant.9:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.9
	.size	_string_constant.9, 12

	.type	string_constant.10,@object # @string_constant.10
	.section	.rodata,"a",@progbits
	.globl	string_constant.10
string_constant.10:
	.asciz	 "["
	.size	string_constant.10, 2

	.type	_string_constant.10,@object # @_string_constant.10
	.data
	.globl	_string_constant.10
	.align	8
_string_constant.10:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.10
	.size	_string_constant.10, 12

	.type	string_constant.11,@object # @string_constant.11
	.section	.rodata,"a",@progbits
	.globl	string_constant.11
string_constant.11:
	.asciz	 "]"
	.size	string_constant.11, 2

	.type	_string_constant.11,@object # @_string_constant.11
	.data
	.globl	_string_constant.11
	.align	8
_string_constant.11:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.11
	.size	_string_constant.11, 12

	.type	string_constant.12,@object # @string_constant.12
	.section	.rodata,"a",@progbits
	.globl	string_constant.12
string_constant.12:
	.asciz	 "("
	.size	string_constant.12, 2

	.type	_string_constant.12,@object # @_string_constant.12
	.data
	.globl	_string_constant.12
	.align	8
_string_constant.12:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.12
	.size	_string_constant.12, 12

	.type	string_constant.13,@object # @string_constant.13
	.section	.rodata,"a",@progbits
	.globl	string_constant.13
string_constant.13:
	.asciz	 ","
	.size	string_constant.13, 2

	.type	_string_constant.13,@object # @_string_constant.13
	.data
	.globl	_string_constant.13
	.align	8
_string_constant.13:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.13
	.size	_string_constant.13, 12

	.type	string_constant.14,@object # @string_constant.14
	.section	.rodata,"a",@progbits
	.globl	string_constant.14
string_constant.14:
	.asciz	 ")("
	.size	string_constant.14, 3

	.type	_string_constant.14,@object # @_string_constant.14
	.data
	.globl	_string_constant.14
	.align	8
_string_constant.14:
	.long	RString
	.long	2                       # 0x2
	.long	string_constant.14
	.size	_string_constant.14, 12

	.type	string_constant.15,@object # @string_constant.15
	.section	.rodata,"a",@progbits
	.globl	string_constant.15
string_constant.15:
	.asciz	 ","
	.size	string_constant.15, 2

	.type	_string_constant.15,@object # @_string_constant.15
	.data
	.globl	_string_constant.15
	.align	8
_string_constant.15:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.15
	.size	_string_constant.15, 12

	.type	string_constant.16,@object # @string_constant.16
	.section	.rodata,"a",@progbits
	.globl	string_constant.16
string_constant.16:
	.asciz	 ")"
	.size	string_constant.16, 2

	.type	_string_constant.16,@object # @_string_constant.16
	.data
	.globl	_string_constant.16
	.align	8
_string_constant.16:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.16
	.size	_string_constant.16, 12

	.type	string_constant.17,@object # @string_constant.17
	.section	.rodata,"a",@progbits
	.globl	string_constant.17
string_constant.17:
	.asciz	 "["
	.size	string_constant.17, 2

	.type	_string_constant.17,@object # @_string_constant.17
	.data
	.globl	_string_constant.17
	.align	8
_string_constant.17:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.17
	.size	_string_constant.17, 12

	.type	string_constant.18,@object # @string_constant.18
	.section	.rodata,"a",@progbits
	.globl	string_constant.18
string_constant.18:
	.asciz	 "]"
	.size	string_constant.18, 2

	.type	_string_constant.18,@object # @_string_constant.18
	.data
	.globl	_string_constant.18
	.align	8
_string_constant.18:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.18
	.size	_string_constant.18, 12

	.type	string_constant.19,@object # @string_constant.19
	.section	.rodata,"a",@progbits
	.globl	string_constant.19
string_constant.19:
	.asciz	 "\n"
	.size	string_constant.19, 2

	.type	_string_constant.19,@object # @_string_constant.19
	.data
	.globl	_string_constant.19
	.align	8
_string_constant.19:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.19
	.size	_string_constant.19, 12

	.type	string_constant.20,@object # @string_constant.20
	.section	.rodata,"a",@progbits
	.globl	string_constant.20
string_constant.20:
	.asciz	 " > "
	.size	string_constant.20, 4

	.type	_string_constant.20,@object # @_string_constant.20
	.data
	.globl	_string_constant.20
	.align	8
_string_constant.20:
	.long	RString
	.long	3                       # 0x3
	.long	string_constant.20
	.size	_string_constant.20, 12

	.type	string_constant.21,@object # @string_constant.21
	.section	.rodata,"a",@progbits
	.globl	string_constant.21
string_constant.21:
	.asciz	 " vs "
	.size	string_constant.21, 5

	.type	_string_constant.21,@object # @_string_constant.21
	.data
	.globl	_string_constant.21
	.align	8
_string_constant.21:
	.long	RString
	.long	4                       # 0x4
	.long	string_constant.21
	.size	_string_constant.21, 12

	.type	string_constant.22,@object # @string_constant.22
	.section	.rodata,"a",@progbits
	.globl	string_constant.22
string_constant.22:
	.asciz	 "\n"
	.size	string_constant.22, 2

	.type	_string_constant.22,@object # @_string_constant.22
	.data
	.globl	_string_constant.22
	.align	8
_string_constant.22:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.22
	.size	_string_constant.22, 12

	.type	RShape,@object          # @RShape
	.globl	RShape
	.align	16
RShape:
	.long	NShape
	.long	16                      # 0x10
	.long	RObject
	.long	Shape_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M5_Shape_init
	.long	M5_Shape_print
	.long	M5_Shape_area
	.size	RShape, 40

	.type	REllipse,@object        # @REllipse
	.globl	REllipse
	.align	16
REllipse:
	.long	NEllipse
	.long	22                      # 0x16
	.long	RShape
	.long	Ellipse_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M5_Shape_init
	.long	M5_Shape_print
	.long	M5_Shape_area
	.long	M7_Ellipse_init
	.long	M7_Ellipse_setR
	.long	M7_Ellipse_print
	.long	M7_Ellipse_area
	.size	REllipse, 56

	.type	RMain,@object           # @RMain
	.globl	RMain
	.align	16
RMain:
	.long	NMain
	.long	4                       # 0x4
	.long	RIO
	.long	Main_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M2_IO_in
	.long	M2_IO_out
	.long	M4_Main_print
	.long	M4_Main_main
	.size	RMain, 44

	.section	.eh_frame,"a",@progbits
.LEH_frame0:
.Lsection_eh_frame0:
.Leh_frame_common0:
.Lset0 = .Leh_frame_common_end0-.Leh_frame_common_begin0 # Length of Common Information Entry
	.long	.Lset0
.Leh_frame_common_begin0:
	.long	0                       # CIE Identifier Tag
	.byte	1                       # DW_CIE_VERSION
	.byte	0                       # CIE Augmentation
	.byte	1                       # CIE Code Alignment Factor
	.byte	124                     # CIE Data Alignment Factor
	.byte	8                       # CIE Return Address Column
	.byte	12                      # DW_CFA_def_cfa
	.byte	4                       # Register
	.byte	4                       # Offset
	.byte	136                     # DW_CFA_offset + Reg (8)
	.byte	1                       # Offset
	.align	4
.Leh_frame_common_end0:
.LShape_init.eh:
.Lset1 = .Leh_frame_end0-.Leh_frame_begin0 # Length of Frame Information Entry
	.long	.Lset1
.Leh_frame_begin0:
.Lset2 = .Leh_frame_begin0-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset2
	.long	.Leh_func_begin0        # FDE initial location
.Lset3 = .Leh_func_end0-.Leh_func_begin0 # FDE address range
	.long	.Lset3
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset4 = .Ltmp0-.Leh_func_begin0
	.long	.Lset4
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset5 = .Ltmp1-.Ltmp0
	.long	.Lset5
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end0:

.LEllipse_init.eh:
.Lset6 = .Leh_frame_end1-.Leh_frame_begin1 # Length of Frame Information Entry
	.long	.Lset6
.Leh_frame_begin1:
.Lset7 = .Leh_frame_begin1-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset7
	.long	.Leh_func_begin1        # FDE initial location
.Lset8 = .Leh_func_end1-.Leh_func_begin1 # FDE address range
	.long	.Lset8
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset9 = .Ltmp3-.Leh_func_begin1
	.long	.Lset9
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset10 = .Ltmp4-.Ltmp3
	.long	.Lset10
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end1:

.LMain_init.eh:
.Lset11 = .Leh_frame_end2-.Leh_frame_begin2 # Length of Frame Information Entry
	.long	.Lset11
.Leh_frame_begin2:
.Lset12 = .Leh_frame_begin2-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset12
	.long	.Leh_func_begin2        # FDE initial location
.Lset13 = .Leh_func_end2-.Leh_func_begin2 # FDE address range
	.long	.Lset13
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset14 = .Ltmp6-.Leh_func_begin2
	.long	.Lset14
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.align	4
.Leh_frame_end2:

.LM5_Shape_init.eh = 0

.LM5_Shape_print.eh:
.Lset15 = .Leh_frame_end4-.Leh_frame_begin4 # Length of Frame Information Entry
	.long	.Lset15
.Leh_frame_begin4:
.Lset16 = .Leh_frame_begin4-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset16
	.long	.Leh_func_begin4        # FDE initial location
.Lset17 = .Leh_func_end4-.Leh_func_begin4 # FDE address range
	.long	.Lset17
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset18 = .Ltmp10-.Leh_func_begin4
	.long	.Lset18
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset19 = .Ltmp11-.Ltmp10
	.long	.Lset19
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset20 = .Ltmp12-.Ltmp11
	.long	.Lset20
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset21 = .Ltmp13-.Ltmp12
	.long	.Lset21
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	4                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	3                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end4:

.LM5_Shape_area.eh = 0

.LM7_Ellipse_init.eh:
.Lset22 = .Leh_frame_end6-.Leh_frame_begin6 # Length of Frame Information Entry
	.long	.Lset22
.Leh_frame_begin6:
.Lset23 = .Leh_frame_begin6-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset23
	.long	.Leh_func_begin6        # FDE initial location
.Lset24 = .Leh_func_end6-.Leh_func_begin6 # FDE address range
	.long	.Lset24
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset25 = .Ltmp17-.Leh_func_begin6
	.long	.Lset25
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset26 = .Ltmp18-.Ltmp17
	.long	.Lset26
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset27 = .Ltmp19-.Ltmp18
	.long	.Lset27
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset28 = .Ltmp20-.Ltmp19
	.long	.Lset28
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	64                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	4                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	3                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end6:

.LM7_Ellipse_setR.eh = 0

.LM7_Ellipse_print.eh:
.Lset29 = .Leh_frame_end8-.Leh_frame_begin8 # Length of Frame Information Entry
	.long	.Lset29
.Leh_frame_begin8:
.Lset30 = .Leh_frame_begin8-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset30
	.long	.Leh_func_begin8        # FDE initial location
.Lset31 = .Leh_func_end8-.Leh_func_begin8 # FDE address range
	.long	.Lset31
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset32 = .Ltmp24-.Leh_func_begin8
	.long	.Lset32
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset33 = .Ltmp25-.Ltmp24
	.long	.Lset33
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset34 = .Ltmp26-.Ltmp25
	.long	.Lset34
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	3                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end8:

.LM7_Ellipse_area.eh = 0

.LM4_Main_print.eh:
.Lset35 = .Leh_frame_end10-.Leh_frame_begin10 # Length of Frame Information Entry
	.long	.Lset35
.Leh_frame_begin10:
.Lset36 = .Leh_frame_begin10-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset36
	.long	.Leh_func_begin10       # FDE initial location
.Lset37 = .Leh_func_end10-.Leh_func_begin10 # FDE address range
	.long	.Lset37
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset38 = .Ltmp30-.Leh_func_begin10
	.long	.Lset38
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset39 = .Ltmp31-.Ltmp30
	.long	.Lset39
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset40 = .Ltmp32-.Ltmp31
	.long	.Lset40
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset41 = .Ltmp33-.Ltmp32
	.long	.Lset41
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	20                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset42 = .Ltmp34-.Ltmp33
	.long	.Lset42
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	48                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	5                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	4                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	3                       # Offset
	.byte	133                     # DW_CFA_offset + Reg (5)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end10:

.LM4_Main_main.eh:
.Lset43 = .Leh_frame_end11-.Leh_frame_begin11 # Length of Frame Information Entry
	.long	.Lset43
.Leh_frame_begin11:
.Lset44 = .Leh_frame_begin11-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset44
	.long	.Leh_func_begin11       # FDE initial location
.Lset45 = .Leh_func_end11-.Leh_func_begin11 # FDE address range
	.long	.Lset45
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset46 = .Ltmp36-.Leh_func_begin11
	.long	.Lset46
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset47 = .Ltmp37-.Ltmp36
	.long	.Lset47
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	48                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end11:

.Lstartup.eh:
.Lset48 = .Leh_frame_end12-.Leh_frame_begin12 # Length of Frame Information Entry
	.long	.Lset48
.Leh_frame_begin12:
.Lset49 = .Leh_frame_begin12-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset49
	.long	.Leh_func_begin12       # FDE initial location
.Lset50 = .Leh_func_end12-.Leh_func_begin12 # FDE address range
	.long	.Lset50
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset51 = .Ltmp39-.Leh_func_begin12
	.long	.Lset51
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset52 = .Ltmp40-.Ltmp39
	.long	.Lset52
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end12:


	.section	".note.GNU-stack","",@progbits
