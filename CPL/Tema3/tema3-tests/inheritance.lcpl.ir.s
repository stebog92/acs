	.file	"inheritance.lcpl.ir"
	.text
	.globl	Main_init
	.align	16, 0x90
	.type	Main_init,@function
Main_init:                              # @Main_init
.Leh_func_begin0:
# BB#0:
	subl	$12, %esp
.Ltmp0:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	B_init
	addl	$12, %esp
	ret
.Ltmp1:
	.size	Main_init, .Ltmp1-Main_init
.Leh_func_end0:

	.globl	A_init
	.align	16, 0x90
	.type	A_init,@function
A_init:                                 # @A_init
.Leh_func_begin1:
# BB#0:
	pushl	%esi
.Ltmp2:
	subl	$8, %esp
.Ltmp3:
	movl	16(%esp), %esi
	movl	%esi, 4(%esp)
	movl	%esi, (%esp)
	calll	Object_init
	movl	$0, 4(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp4:
	.size	A_init, .Ltmp4-A_init
.Leh_func_end1:

	.globl	B_init
	.align	16, 0x90
	.type	B_init,@function
B_init:                                 # @B_init
.Leh_func_begin2:
# BB#0:
	subl	$12, %esp
.Ltmp5:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	A_init
	addl	$12, %esp
	ret
.Ltmp6:
	.size	B_init, .Ltmp6-B_init
.Leh_func_end2:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin3:
# BB#0:
	pushl	%eax
.Ltmp7:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	popl	%eax
	ret
.Ltmp8:
	.size	M4_Main_main, .Ltmp8-M4_Main_main
.Leh_func_end3:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin4:
# BB#0:
	pushl	%esi
.Ltmp9:
	subl	$8, %esp
.Ltmp10:
	movl	$RMain, (%esp)
	calll	__lcpl_new
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*28(%eax)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp11:
	.size	startup, .Ltmp11-startup
.Leh_func_end4:

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

	.type	class_name.A,@object    # @class_name.A
	.section	.rodata,"a",@progbits
class_name.A:
	.asciz	 "A"
	.size	class_name.A, 2

	.type	NA,@object              # @NA
	.data
	.globl	NA
	.align	8
NA:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.A
	.size	NA, 12

	.type	class_name.B,@object    # @class_name.B
	.section	.rodata,"a",@progbits
class_name.B:
	.asciz	 "B"
	.size	class_name.B, 2

	.type	NB,@object              # @NB
	.data
	.globl	NB
	.align	8
NB:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.B
	.size	NB, 12

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

	.type	RMain,@object           # @RMain
	.globl	RMain
	.align	16
RMain:
	.long	NMain
	.long	7                       # 0x7
	.long	RB
	.long	Main_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M4_Main_main
	.size	RMain, 32

	.type	RA,@object              # @RA
	.globl	RA
	.align	16
RA:
	.long	NA
	.long	7                       # 0x7
	.long	RObject
	.long	A_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.size	RA, 28

	.type	RB,@object              # @RB
	.globl	RB
	.align	16
RB:
	.long	NB
	.long	7                       # 0x7
	.long	RA
	.long	B_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.size	RB, 28

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
.LMain_init.eh:
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

.LA_init.eh:
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
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset9 = .Ltmp3-.Ltmp2
	.long	.Lset9
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end1:

.LB_init.eh:
.Lset10 = .Leh_frame_end2-.Leh_frame_begin2 # Length of Frame Information Entry
	.long	.Lset10
.Leh_frame_begin2:
.Lset11 = .Leh_frame_begin2-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset11
	.long	.Leh_func_begin2        # FDE initial location
.Lset12 = .Leh_func_end2-.Leh_func_begin2 # FDE address range
	.long	.Lset12
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset13 = .Ltmp5-.Leh_func_begin2
	.long	.Lset13
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.align	4
.Leh_frame_end2:

.LM4_Main_main.eh = 0

.Lstartup.eh:
.Lset14 = .Leh_frame_end4-.Leh_frame_begin4 # Length of Frame Information Entry
	.long	.Lset14
.Leh_frame_begin4:
.Lset15 = .Leh_frame_begin4-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset15
	.long	.Leh_func_begin4        # FDE initial location
.Lset16 = .Leh_func_end4-.Leh_func_begin4 # FDE address range
	.long	.Lset16
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset17 = .Ltmp9-.Leh_func_begin4
	.long	.Lset17
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset18 = .Ltmp10-.Ltmp9
	.long	.Lset18
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end4:


	.section	".note.GNU-stack","",@progbits
