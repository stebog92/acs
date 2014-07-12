	.file	"dispatch2.lcpl.ir"
	.text
	.globl	Circle_init
	.align	16, 0x90
	.type	Circle_init,@function
Circle_init:                            # @Circle_init
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
	.size	Circle_init, .Ltmp1-Circle_init
.Leh_func_end0:

	.globl	Line_init
	.align	16, 0x90
	.type	Line_init,@function
Line_init:                              # @Line_init
.Leh_func_begin1:
# BB#0:
	subl	$12, %esp
.Ltmp2:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	Object_init
	addl	$12, %esp
	ret
.Ltmp3:
	.size	Line_init, .Ltmp3-Line_init
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

	.globl	M6_Circle_name
	.align	16, 0x90
	.type	M6_Circle_name,@function
M6_Circle_name:                         # @M6_Circle_name
.Leh_func_begin3:
# BB#0:
	pushl	%eax
.Ltmp6:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	$_string_constant.3, %eax
	popl	%edx
	ret
.Ltmp7:
	.size	M6_Circle_name, .Ltmp7-M6_Circle_name
.Leh_func_end3:

	.globl	M4_Line_name
	.align	16, 0x90
	.type	M4_Line_name,@function
M4_Line_name:                           # @M4_Line_name
.Leh_func_begin4:
# BB#0:
	pushl	%eax
.Ltmp8:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	$_string_constant.4, %eax
	popl	%edx
	ret
.Ltmp9:
	.size	M4_Line_name, .Ltmp9-M4_Line_name
.Leh_func_end4:

	.globl	M4_Main_shape
	.align	16, 0x90
	.type	M4_Main_shape,@function
M4_Main_shape:                          # @M4_Main_shape
.Leh_func_begin5:
# BB#0:
	subl	$12, %esp
.Ltmp10:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	$RLine, (%esp)
	calll	__lcpl_new
	addl	$12, %esp
	ret
.Ltmp11:
	.size	M4_Main_shape, .Ltmp11-M4_Main_shape
.Leh_func_end5:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin6:
# BB#0:
	pushl	%ebx
.Ltmp12:
	pushl	%edi
.Ltmp13:
	pushl	%esi
.Ltmp14:
	subl	$16, %esp
.Ltmp15:
	movl	32(%esp), %esi
	movl	%esi, 12(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	12(%esp), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	calll	*36(%eax)
	movl	%eax, %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	calll	*28(%eax)
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$16, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	ret
.Ltmp16:
	.size	M4_Main_main, .Ltmp16-M4_Main_main
.Leh_func_end6:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin7:
# BB#0:
	pushl	%esi
.Ltmp17:
	subl	$8, %esp
.Ltmp18:
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
.Ltmp19:
	.size	startup, .Ltmp19-startup
.Leh_func_end7:

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

	.type	class_name.Line,@object # @class_name.Line
	.section	.rodata,"a",@progbits
class_name.Line:
	.asciz	 "Line"
	.size	class_name.Line, 5

	.type	NLine,@object           # @NLine
	.data
	.globl	NLine
	.align	8
NLine:
	.long	RString
	.long	4                       # 0x4
	.long	class_name.Line
	.size	NLine, 12

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
	.asciz	 "round"
	.size	string_constant.0, 6

	.type	_string_constant.0,@object # @_string_constant.0
	.data
	.globl	_string_constant.0
	.align	8
_string_constant.0:
	.long	RString
	.long	5                       # 0x5
	.long	string_constant.0
	.size	_string_constant.0, 12

	.type	string_constant.1,@object # @string_constant.1
	.section	.rodata,"a",@progbits
	.globl	string_constant.1
string_constant.1:
	.asciz	 "round"
	.size	string_constant.1, 6

	.type	_string_constant.1,@object # @_string_constant.1
	.data
	.globl	_string_constant.1
	.align	8
_string_constant.1:
	.long	RString
	.long	5                       # 0x5
	.long	string_constant.1
	.size	_string_constant.1, 12

	.type	string_constant.2,@object # @string_constant.2
	.section	.rodata,"a",@progbits
	.globl	string_constant.2
string_constant.2:
	.asciz	 "straight"
	.size	string_constant.2, 9

	.type	_string_constant.2,@object # @_string_constant.2
	.data
	.globl	_string_constant.2
	.align	8
_string_constant.2:
	.long	RString
	.long	8                       # 0x8
	.long	string_constant.2
	.size	_string_constant.2, 12

	.type	string_constant.3,@object # @string_constant.3
	.section	.rodata,"a",@progbits
	.globl	string_constant.3
string_constant.3:
	.asciz	 "round"
	.size	string_constant.3, 6

	.type	_string_constant.3,@object # @_string_constant.3
	.data
	.globl	_string_constant.3
	.align	8
_string_constant.3:
	.long	RString
	.long	5                       # 0x5
	.long	string_constant.3
	.size	_string_constant.3, 12

	.type	string_constant.4,@object # @string_constant.4
	.section	.rodata,"a",@progbits
	.globl	string_constant.4
string_constant.4:
	.asciz	 "straight"
	.size	string_constant.4, 9

	.type	_string_constant.4,@object # @_string_constant.4
	.data
	.globl	_string_constant.4
	.align	8
_string_constant.4:
	.long	RString
	.long	8                       # 0x8
	.long	string_constant.4
	.size	_string_constant.4, 12

	.type	RCircle,@object         # @RCircle
	.globl	RCircle
	.align	16
RCircle:
	.long	NCircle
	.long	4                       # 0x4
	.long	RObject
	.long	Circle_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M6_Circle_name
	.size	RCircle, 32

	.type	RLine,@object           # @RLine
	.globl	RLine
	.align	16
RLine:
	.long	NLine
	.long	4                       # 0x4
	.long	RObject
	.long	Line_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M4_Line_name
	.size	RLine, 32

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
	.long	M4_Main_shape
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
.LCircle_init.eh:
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

.LLine_init.eh:
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

.LM6_Circle_name.eh = 0

.LM4_Line_name.eh = 0

.LM4_Main_shape.eh:
.Lset13 = .Leh_frame_end5-.Leh_frame_begin5 # Length of Frame Information Entry
	.long	.Lset13
.Leh_frame_begin5:
.Lset14 = .Leh_frame_begin5-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset14
	.long	.Leh_func_begin5        # FDE initial location
.Lset15 = .Leh_func_end5-.Leh_func_begin5 # FDE address range
	.long	.Lset15
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset16 = .Ltmp10-.Leh_func_begin5
	.long	.Lset16
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.align	4
.Leh_frame_end5:

.LM4_Main_main.eh:
.Lset17 = .Leh_frame_end6-.Leh_frame_begin6 # Length of Frame Information Entry
	.long	.Lset17
.Leh_frame_begin6:
.Lset18 = .Leh_frame_begin6-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset18
	.long	.Leh_func_begin6        # FDE initial location
.Lset19 = .Leh_func_end6-.Leh_func_begin6 # FDE address range
	.long	.Lset19
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset20 = .Ltmp12-.Leh_func_begin6
	.long	.Lset20
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset21 = .Ltmp13-.Ltmp12
	.long	.Lset21
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset22 = .Ltmp14-.Ltmp13
	.long	.Lset22
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset23 = .Ltmp15-.Ltmp14
	.long	.Lset23
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	4                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	3                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end6:

.Lstartup.eh:
.Lset24 = .Leh_frame_end7-.Leh_frame_begin7 # Length of Frame Information Entry
	.long	.Lset24
.Leh_frame_begin7:
.Lset25 = .Leh_frame_begin7-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset25
	.long	.Leh_func_begin7        # FDE initial location
.Lset26 = .Leh_func_end7-.Leh_func_begin7 # FDE address range
	.long	.Lset26
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset27 = .Ltmp17-.Leh_func_begin7
	.long	.Lset27
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset28 = .Ltmp18-.Ltmp17
	.long	.Lset28
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end7:


	.section	".note.GNU-stack","",@progbits
