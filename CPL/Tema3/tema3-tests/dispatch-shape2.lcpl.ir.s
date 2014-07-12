	.file	"dispatch-shape2.lcpl.ir"
	.text
	.globl	Shape_init
	.align	16, 0x90
	.type	Shape_init,@function
Shape_init:                             # @Shape_init
.Leh_func_begin0:
# BB#0:
	subl	$12, %esp
.Ltmp0:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	Object_init
	addl	$12, %esp
	ret
.Ltmp1:
	.size	Shape_init, .Ltmp1-Shape_init
.Leh_func_end0:

	.globl	Circle_init
	.align	16, 0x90
	.type	Circle_init,@function
Circle_init:                            # @Circle_init
.Leh_func_begin1:
# BB#0:
	subl	$12, %esp
.Ltmp2:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	Shape_init
	addl	$12, %esp
	ret
.Ltmp3:
	.size	Circle_init, .Ltmp3-Circle_init
.Leh_func_end1:

	.globl	Main_init
	.align	16, 0x90
	.type	Main_init,@function
Main_init:                              # @Main_init
.Leh_func_begin2:
# BB#0:
	subl	$12, %esp
.Ltmp4:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	IO_init
	addl	$12, %esp
	ret
.Ltmp5:
	.size	Main_init, .Ltmp5-Main_init
.Leh_func_end2:

	.globl	M5_Shape_print
	.align	16, 0x90
	.type	M5_Shape_print,@function
M5_Shape_print:                         # @M5_Shape_print
.Leh_func_begin3:
# BB#0:
	pushl	%ebx
.Ltmp6:
	pushl	%edi
.Ltmp7:
	pushl	%esi
.Ltmp8:
	subl	$32, %esp
.Ltmp9:
	movl	48(%esp), %eax
	movl	%eax, 28(%esp)
	movl	52(%esp), %eax
	movl	%eax, 24(%esp)
	movl	56(%esp), %eax
	movl	%eax, 20(%esp)
	movl	60(%esp), %eax
	movl	%eax, 16(%esp)
	movl	24(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	20(%esp), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	$_string_constant.9, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.13, 4(%esp)
	calll	M6_String_concat
	movl	%eax, %ebx
	movl	16(%esp), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%ebx, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.14, 4(%esp)
	calll	M6_String_concat
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$32, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	ret
.Ltmp10:
	.size	M5_Shape_print, .Ltmp10-M5_Shape_print
.Leh_func_end3:

	.globl	M6_Circle_print
	.align	16, 0x90
	.type	M6_Circle_print,@function
M6_Circle_print:                        # @M6_Circle_print
.Leh_func_begin4:
# BB#0:
	pushl	%ebx
.Ltmp11:
	pushl	%edi
.Ltmp12:
	pushl	%esi
.Ltmp13:
	subl	$32, %esp
.Ltmp14:
	movl	48(%esp), %eax
	movl	%eax, 28(%esp)
	movl	52(%esp), %eax
	movl	%eax, 24(%esp)
	movl	56(%esp), %eax
	movl	%eax, 20(%esp)
	movl	60(%esp), %eax
	movl	%eax, 16(%esp)
	movl	24(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	20(%esp), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	$_string_constant.12, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.13, 4(%esp)
	calll	M6_String_concat
	movl	%eax, %ebx
	movl	16(%esp), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%ebx, (%esp)
	calll	M6_String_concat
	movl	%eax, (%esp)
	movl	$_string_constant.14, 4(%esp)
	calll	M6_String_concat
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$32, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	ret
.Ltmp15:
	.size	M6_Circle_print, .Ltmp15-M6_Circle_print
.Leh_func_end4:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin5:
# BB#0:
	pushl	%edi
.Ltmp16:
	pushl	%esi
.Ltmp17:
	subl	$36, %esp
.Ltmp18:
	movl	48(%esp), %eax
	movl	%eax, 32(%esp)
	movl	$RCircle, (%esp)
	calll	__lcpl_new
	movl	%eax, (%esp)
	movl	$RShape, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, %esi
	movl	%esi, 28(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	32(%esp), %eax
	movl	%eax, (%esp)
	movl	$RIO, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	movl	$2, 12(%esp)
	movl	$1, 8(%esp)
	calll	*%edi
	movl	28(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	28(%eax), %edi
	movl	32(%esp), %eax
	movl	%eax, (%esp)
	movl	$RIO, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	movl	$4, 12(%esp)
	movl	$3, 8(%esp)
	calll	*%edi
	addl	$36, %esp
	popl	%esi
	popl	%edi
	ret
.Ltmp19:
	.size	M4_Main_main, .Ltmp19-M4_Main_main
.Leh_func_end5:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin6:
# BB#0:
	pushl	%esi
.Ltmp20:
	subl	$8, %esp
.Ltmp21:
	movl	$RMain, (%esp)
	calll	__lcpl_new
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*36(%eax)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp22:
	.size	startup, .Ltmp22-startup
.Leh_func_end6:

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

	.type	class_name.Circle,@object # @class_name.Circle
	.section	.rodata,"a",@progbits
class_name.Circle:
	.asciz	 "Circle"
	.size	class_name.Circle, 7

	.type	NCircle,@object         # @NCircle
	.data
	.globl	NCircle
	.align	8
NCircle:
	.long	RString
	.long	6                       # 0x6
	.long	class_name.Circle
	.size	NCircle, 12

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
	.align	16
string_constant.0:
	.asciz	 "This is a generic shape @("
	.size	string_constant.0, 27

	.type	_string_constant.0,@object # @_string_constant.0
	.data
	.globl	_string_constant.0
	.align	8
_string_constant.0:
	.long	RString
	.long	26                      # 0x1a
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
	.asciz	 ")\n"
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
	.align	16
string_constant.3:
	.asciz	 "This is a generic shape @("
	.size	string_constant.3, 27

	.type	_string_constant.3,@object # @_string_constant.3
	.data
	.globl	_string_constant.3
	.align	8
_string_constant.3:
	.long	RString
	.long	26                      # 0x1a
	.long	string_constant.3
	.size	_string_constant.3, 12

	.type	string_constant.4,@object # @string_constant.4
	.section	.rodata,"a",@progbits
	.globl	string_constant.4
string_constant.4:
	.asciz	 ","
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
	.asciz	 ")\n"
	.size	string_constant.5, 3

	.type	_string_constant.5,@object # @_string_constant.5
	.data
	.globl	_string_constant.5
	.align	8
_string_constant.5:
	.long	RString
	.long	2                       # 0x2
	.long	string_constant.5
	.size	_string_constant.5, 12

	.type	string_constant.6,@object # @string_constant.6
	.section	.rodata,"a",@progbits
	.globl	string_constant.6
	.align	16
string_constant.6:
	.asciz	 "This is a circle @("
	.size	string_constant.6, 20

	.type	_string_constant.6,@object # @_string_constant.6
	.data
	.globl	_string_constant.6
	.align	8
_string_constant.6:
	.long	RString
	.long	19                      # 0x13
	.long	string_constant.6
	.size	_string_constant.6, 12

	.type	string_constant.7,@object # @string_constant.7
	.section	.rodata,"a",@progbits
	.globl	string_constant.7
string_constant.7:
	.asciz	 ","
	.size	string_constant.7, 2

	.type	_string_constant.7,@object # @_string_constant.7
	.data
	.globl	_string_constant.7
	.align	8
_string_constant.7:
	.long	RString
	.long	1                       # 0x1
	.long	string_constant.7
	.size	_string_constant.7, 12

	.type	string_constant.8,@object # @string_constant.8
	.section	.rodata,"a",@progbits
	.globl	string_constant.8
string_constant.8:
	.asciz	 ")\n"
	.size	string_constant.8, 3

	.type	_string_constant.8,@object # @_string_constant.8
	.data
	.globl	_string_constant.8
	.align	8
_string_constant.8:
	.long	RString
	.long	2                       # 0x2
	.long	string_constant.8
	.size	_string_constant.8, 12

	.type	string_constant.9,@object # @string_constant.9
	.section	.rodata,"a",@progbits
	.globl	string_constant.9
	.align	16
string_constant.9:
	.asciz	 "This is a generic shape @("
	.size	string_constant.9, 27

	.type	_string_constant.9,@object # @_string_constant.9
	.data
	.globl	_string_constant.9
	.align	8
_string_constant.9:
	.long	RString
	.long	26                      # 0x1a
	.long	string_constant.9
	.size	_string_constant.9, 12

	.type	string_constant.10,@object # @string_constant.10
	.section	.rodata,"a",@progbits
	.globl	string_constant.10
string_constant.10:
	.asciz	 ","
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
	.asciz	 ")\n"
	.size	string_constant.11, 3

	.type	_string_constant.11,@object # @_string_constant.11
	.data
	.globl	_string_constant.11
	.align	8
_string_constant.11:
	.long	RString
	.long	2                       # 0x2
	.long	string_constant.11
	.size	_string_constant.11, 12

	.type	string_constant.12,@object # @string_constant.12
	.section	.rodata,"a",@progbits
	.globl	string_constant.12
	.align	16
string_constant.12:
	.asciz	 "This is a circle @("
	.size	string_constant.12, 20

	.type	_string_constant.12,@object # @_string_constant.12
	.data
	.globl	_string_constant.12
	.align	8
_string_constant.12:
	.long	RString
	.long	19                      # 0x13
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
	.asciz	 ")\n"
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

	.type	RShape,@object          # @RShape
	.globl	RShape
	.align	16
RShape:
	.long	NShape
	.long	4                       # 0x4
	.long	RObject
	.long	Shape_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M5_Shape_print
	.size	RShape, 32

	.type	RCircle,@object         # @RCircle
	.globl	RCircle
	.align	16
RCircle:
	.long	NCircle
	.long	4                       # 0x4
	.long	RShape
	.long	Circle_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M5_Shape_print
	.long	M6_Circle_print
	.size	RCircle, 36

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
	.long	M4_Main_main
	.size	RMain, 40

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
	.byte	16                      # Offset
	.align	4
.Leh_frame_end0:

.LCircle_init.eh:
.Lset5 = .Leh_frame_end1-.Leh_frame_begin1 # Length of Frame Information Entry
	.long	.Lset5
.Leh_frame_begin1:
.Lset6 = .Leh_frame_begin1-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset6
	.long	.Leh_func_begin1        # FDE initial location
.Lset7 = .Leh_func_end1-.Leh_func_begin1 # FDE address range
	.long	.Lset7
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset8 = .Ltmp2-.Leh_func_begin1
	.long	.Lset8
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.align	4
.Leh_frame_end1:

.LMain_init.eh:
.Lset9 = .Leh_frame_end2-.Leh_frame_begin2 # Length of Frame Information Entry
	.long	.Lset9
.Leh_frame_begin2:
.Lset10 = .Leh_frame_begin2-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset10
	.long	.Leh_func_begin2        # FDE initial location
.Lset11 = .Leh_func_end2-.Leh_func_begin2 # FDE address range
	.long	.Lset11
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset12 = .Ltmp4-.Leh_func_begin2
	.long	.Lset12
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.align	4
.Leh_frame_end2:

.LM5_Shape_print.eh:
.Lset13 = .Leh_frame_end3-.Leh_frame_begin3 # Length of Frame Information Entry
	.long	.Lset13
.Leh_frame_begin3:
.Lset14 = .Leh_frame_begin3-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset14
	.long	.Leh_func_begin3        # FDE initial location
.Lset15 = .Leh_func_end3-.Leh_func_begin3 # FDE address range
	.long	.Lset15
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset16 = .Ltmp6-.Leh_func_begin3
	.long	.Lset16
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset17 = .Ltmp7-.Ltmp6
	.long	.Lset17
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset18 = .Ltmp8-.Ltmp7
	.long	.Lset18
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset19 = .Ltmp9-.Ltmp8
	.long	.Lset19
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	48                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	4                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	3                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end3:

.LM6_Circle_print.eh:
.Lset20 = .Leh_frame_end4-.Leh_frame_begin4 # Length of Frame Information Entry
	.long	.Lset20
.Leh_frame_begin4:
.Lset21 = .Leh_frame_begin4-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset21
	.long	.Leh_func_begin4        # FDE initial location
.Lset22 = .Leh_func_end4-.Leh_func_begin4 # FDE address range
	.long	.Lset22
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset23 = .Ltmp11-.Leh_func_begin4
	.long	.Lset23
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset24 = .Ltmp12-.Ltmp11
	.long	.Lset24
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset25 = .Ltmp13-.Ltmp12
	.long	.Lset25
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset26 = .Ltmp14-.Ltmp13
	.long	.Lset26
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	48                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	4                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	3                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end4:

.LM4_Main_main.eh:
.Lset27 = .Leh_frame_end5-.Leh_frame_begin5 # Length of Frame Information Entry
	.long	.Lset27
.Leh_frame_begin5:
.Lset28 = .Leh_frame_begin5-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset28
	.long	.Leh_func_begin5        # FDE initial location
.Lset29 = .Leh_func_end5-.Leh_func_begin5 # FDE address range
	.long	.Lset29
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset30 = .Ltmp16-.Leh_func_begin5
	.long	.Lset30
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset31 = .Ltmp17-.Ltmp16
	.long	.Lset31
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset32 = .Ltmp18-.Ltmp17
	.long	.Lset32
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	48                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	3                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end5:

.Lstartup.eh:
.Lset33 = .Leh_frame_end6-.Leh_frame_begin6 # Length of Frame Information Entry
	.long	.Lset33
.Leh_frame_begin6:
.Lset34 = .Leh_frame_begin6-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset34
	.long	.Leh_func_begin6        # FDE initial location
.Lset35 = .Leh_func_end6-.Leh_func_begin6 # FDE address range
	.long	.Lset35
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset36 = .Ltmp20-.Leh_func_begin6
	.long	.Lset36
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset37 = .Ltmp21-.Ltmp20
	.long	.Lset37
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end6:


	.section	".note.GNU-stack","",@progbits
