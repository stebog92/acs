	.file	"runtime-error2.lcpl.ir"
	.text
	.globl	A_init
	.align	16, 0x90
	.type	A_init,@function
A_init:                                 # @A_init
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
	.size	A_init, .Ltmp1-A_init
.Leh_func_end0:

	.globl	B_init
	.align	16, 0x90
	.type	B_init,@function
B_init:                                 # @B_init
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
	.size	B_init, .Ltmp3-B_init
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
	calll	Object_init
	addl	$12, %esp
	ret
.Ltmp5:
	.size	Main_init, .Ltmp5-Main_init
.Leh_func_end2:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin3:
# BB#0:
	subl	$12, %esp
.Ltmp6:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	$RA, (%esp)
	calll	__lcpl_new
	movl	%eax, (%esp)
	movl	$RObject, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, (%esp)
	movl	$RB, 4(%esp)
	calll	__lcpl_cast
	addl	$12, %esp
	ret
.Ltmp7:
	.size	M4_Main_main, .Ltmp7-M4_Main_main
.Leh_func_end3:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin4:
# BB#0:
	pushl	%esi
.Ltmp8:
	subl	$8, %esp
.Ltmp9:
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
.Ltmp10:
	.size	startup, .Ltmp10-startup
.Leh_func_end4:

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

	.type	RA,@object              # @RA
	.globl	RA
	.align	16
RA:
	.long	NA
	.long	4                       # 0x4
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
	.long	4                       # 0x4
	.long	RObject
	.long	B_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.size	RB, 28

	.type	RMain,@object           # @RMain
	.globl	RMain
	.align	16
RMain:
	.long	NMain
	.long	4                       # 0x4
	.long	RObject
	.long	Main_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M4_Main_main
	.size	RMain, 32

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
.LA_init.eh:
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

.LB_init.eh:
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

.LM4_Main_main.eh:
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
	.byte	16                      # Offset
	.align	4
.Leh_frame_end3:

.Lstartup.eh:
.Lset17 = .Leh_frame_end4-.Leh_frame_begin4 # Length of Frame Information Entry
	.long	.Lset17
.Leh_frame_begin4:
.Lset18 = .Leh_frame_begin4-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset18
	.long	.Leh_func_begin4        # FDE initial location
.Lset19 = .Leh_func_end4-.Leh_func_begin4 # FDE address range
	.long	.Lset19
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset20 = .Ltmp8-.Leh_func_begin4
	.long	.Lset20
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset21 = .Ltmp9-.Ltmp8
	.long	.Lset21
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end4:


	.section	".note.GNU-stack","",@progbits
