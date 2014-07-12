	.file	"dispatch-shape.lcpl.ir"
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
	pushl	%esi
.Ltmp6:
	subl	$24, %esp
.Ltmp7:
	movl	32(%esp), %eax
	movl	%eax, 20(%esp)
	movl	36(%esp), %esi
	movl	%esi, 16(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$_string_constant.3, 4(%esp)
	calll	*32(%eax)
	addl	$24, %esp
	popl	%esi
	ret
.Ltmp8:
	.size	M5_Shape_print, .Ltmp8-M5_Shape_print
.Leh_func_end3:

	.globl	M6_Circle_print
	.align	16, 0x90
	.type	M6_Circle_print,@function
M6_Circle_print:                        # @M6_Circle_print
.Leh_func_begin4:
# BB#0:
	pushl	%esi
.Ltmp9:
	subl	$24, %esp
.Ltmp10:
	movl	32(%esp), %eax
	movl	%eax, 20(%esp)
	movl	36(%esp), %esi
	movl	%esi, 16(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$_string_constant.4, 4(%esp)
	calll	*32(%eax)
	addl	$24, %esp
	popl	%esi
	ret
.Ltmp11:
	.size	M6_Circle_print, .Ltmp11-M6_Circle_print
.Leh_func_end4:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin5:
# BB#0:
	pushl	%edi
.Ltmp12:
	pushl	%esi
.Ltmp13:
	subl	$20, %esp
.Ltmp14:
	movl	32(%esp), %eax
	movl	%eax, 16(%esp)
	movl	$RCircle, (%esp)
	calll	__lcpl_new
	movl	%eax, (%esp)
	movl	$RShape, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, %esi
	movl	%esi, 12(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	16(%esp), %eax
	movl	%eax, (%esp)
	movl	$RIO, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	movl	12(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	28(%eax), %edi
	movl	16(%esp), %eax
	movl	%eax, (%esp)
	movl	$RIO, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$20, %esp
	popl	%esi
	popl	%edi
	ret
.Ltmp15:
	.size	M4_Main_main, .Ltmp15-M4_Main_main
.Leh_func_end5:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin6:
# BB#0:
	pushl	%esi
.Ltmp16:
	subl	$8, %esp
.Ltmp17:
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
.Ltmp18:
	.size	startup, .Ltmp18-startup
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
	.asciz	 "This is a generic shape\n"
	.size	string_constant.0, 25

	.type	_string_constant.0,@object # @_string_constant.0
	.data
	.globl	_string_constant.0
	.align	8
_string_constant.0:
	.long	RString
	.long	24                      # 0x18
	.long	string_constant.0
	.size	_string_constant.0, 12

	.type	string_constant.1,@object # @string_constant.1
	.section	.rodata,"a",@progbits
	.globl	string_constant.1
	.align	16
string_constant.1:
	.asciz	 "This is a generic shape\n"
	.size	string_constant.1, 25

	.type	_string_constant.1,@object # @_string_constant.1
	.data
	.globl	_string_constant.1
	.align	8
_string_constant.1:
	.long	RString
	.long	24                      # 0x18
	.long	string_constant.1
	.size	_string_constant.1, 12

	.type	string_constant.2,@object # @string_constant.2
	.section	.rodata,"a",@progbits
	.globl	string_constant.2
	.align	16
string_constant.2:
	.asciz	 "This is a circle\n"
	.size	string_constant.2, 18

	.type	_string_constant.2,@object # @_string_constant.2
	.data
	.globl	_string_constant.2
	.align	8
_string_constant.2:
	.long	RString
	.long	17                      # 0x11
	.long	string_constant.2
	.size	_string_constant.2, 12

	.type	string_constant.3,@object # @string_constant.3
	.section	.rodata,"a",@progbits
	.globl	string_constant.3
	.align	16
string_constant.3:
	.asciz	 "This is a generic shape\n"
	.size	string_constant.3, 25

	.type	_string_constant.3,@object # @_string_constant.3
	.data
	.globl	_string_constant.3
	.align	8
_string_constant.3:
	.long	RString
	.long	24                      # 0x18
	.long	string_constant.3
	.size	_string_constant.3, 12

	.type	string_constant.4,@object # @string_constant.4
	.section	.rodata,"a",@progbits
	.globl	string_constant.4
	.align	16
string_constant.4:
	.asciz	 "This is a circle\n"
	.size	string_constant.4, 18

	.type	_string_constant.4,@object # @_string_constant.4
	.data
	.globl	_string_constant.4
	.align	8
_string_constant.4:
	.long	RString
	.long	17                      # 0x11
	.long	string_constant.4
	.size	_string_constant.4, 12

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
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end3:

.LM6_Circle_print.eh:
.Lset18 = .Leh_frame_end4-.Leh_frame_begin4 # Length of Frame Information Entry
	.long	.Lset18
.Leh_frame_begin4:
.Lset19 = .Leh_frame_begin4-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset19
	.long	.Leh_func_begin4        # FDE initial location
.Lset20 = .Leh_func_end4-.Leh_func_begin4 # FDE address range
	.long	.Lset20
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset21 = .Ltmp9-.Leh_func_begin4
	.long	.Lset21
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset22 = .Ltmp10-.Ltmp9
	.long	.Lset22
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end4:

.LM4_Main_main.eh:
.Lset23 = .Leh_frame_end5-.Leh_frame_begin5 # Length of Frame Information Entry
	.long	.Lset23
.Leh_frame_begin5:
.Lset24 = .Leh_frame_begin5-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset24
	.long	.Leh_func_begin5        # FDE initial location
.Lset25 = .Leh_func_end5-.Leh_func_begin5 # FDE address range
	.long	.Lset25
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset26 = .Ltmp12-.Leh_func_begin5
	.long	.Lset26
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset27 = .Ltmp13-.Ltmp12
	.long	.Lset27
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset28 = .Ltmp14-.Ltmp13
	.long	.Lset28
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	3                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end5:

.Lstartup.eh:
.Lset29 = .Leh_frame_end6-.Leh_frame_begin6 # Length of Frame Information Entry
	.long	.Lset29
.Leh_frame_begin6:
.Lset30 = .Leh_frame_begin6-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset30
	.long	.Leh_func_begin6        # FDE initial location
.Lset31 = .Leh_func_end6-.Leh_func_begin6 # FDE address range
	.long	.Lset31
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset32 = .Ltmp16-.Leh_func_begin6
	.long	.Lset32
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset33 = .Ltmp17-.Ltmp16
	.long	.Lset33
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end6:


	.section	".note.GNU-stack","",@progbits
