	.file	"dispatch.lcpl.ir"
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
	calll	A_init
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
	pushl	%esi
.Ltmp4:
	subl	$8, %esp
.Ltmp5:
	movl	16(%esp), %esi
	movl	%esi, 4(%esp)
	movl	%esi, (%esp)
	calll	IO_init
	movl	$RB, (%esp)
	calll	__lcpl_new
	movl	%eax, 4(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp6:
	.size	Main_init, .Ltmp6-Main_init
.Leh_func_end2:

	.globl	M1_A_method
	.align	16, 0x90
	.type	M1_A_method,@function
M1_A_method:                            # @M1_A_method
.Leh_func_begin3:
# BB#0:
	subl	$12, %esp
.Ltmp7:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	20(%esp), %eax
	movl	%eax, 4(%esp)
	movl	24(%esp), %eax
	movl	%eax, (%esp)
	addl	4(%esp), %eax
	addl	$12, %esp
	ret
.Ltmp8:
	.size	M1_A_method, .Ltmp8-M1_A_method
.Leh_func_end3:

	.globl	M1_B_method
	.align	16, 0x90
	.type	M1_B_method,@function
M1_B_method:                            # @M1_B_method
.Leh_func_begin4:
# BB#0:
	subl	$12, %esp
.Ltmp9:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	20(%esp), %eax
	movl	%eax, 4(%esp)
	movl	24(%esp), %eax
	movl	%eax, (%esp)
	imull	4(%esp), %eax
	addl	$12, %esp
	ret
.Ltmp10:
	.size	M1_B_method, .Ltmp10-M1_B_method
.Leh_func_end4:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin5:
# BB#0:
	pushl	%ebx
.Ltmp11:
	pushl	%edi
.Ltmp12:
	pushl	%esi
.Ltmp13:
	subl	$16, %esp
.Ltmp14:
	movl	32(%esp), %esi
	movl	%esi, 12(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	12(%esp), %eax
	movl	4(%eax), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	movl	$2, 8(%esp)
	movl	$1, 4(%esp)
	calll	*32(%eax)
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	movl	12(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	12(%esp), %eax
	movl	4(%eax), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	movl	$4, 8(%esp)
	movl	$3, 4(%esp)
	calll	*28(%eax)
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	movl	12(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	12(%esp), %eax
	movl	4(%eax), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	movl	$6, 8(%esp)
	movl	$5, 4(%esp)
	calll	*32(%eax)
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$16, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
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
	.long	M1_A_method
	.size	RA, 32

	.type	RB,@object              # @RB
	.globl	RB
	.align	16
RB:
	.long	NB
	.long	4                       # 0x4
	.long	RA
	.long	B_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M1_A_method
	.long	M1_B_method
	.size	RB, 36

	.type	RMain,@object           # @RMain
	.globl	RMain
	.align	16
RMain:
	.long	NMain
	.long	7                       # 0x7
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
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset13 = .Ltmp5-.Ltmp4
	.long	.Lset13
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end2:

.LM1_A_method.eh = 0

.LM1_B_method.eh = 0

.LM4_Main_main.eh:
.Lset14 = .Leh_frame_end5-.Leh_frame_begin5 # Length of Frame Information Entry
	.long	.Lset14
.Leh_frame_begin5:
.Lset15 = .Leh_frame_begin5-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset15
	.long	.Leh_func_begin5        # FDE initial location
.Lset16 = .Leh_func_end5-.Leh_func_begin5 # FDE address range
	.long	.Lset16
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset17 = .Ltmp11-.Leh_func_begin5
	.long	.Lset17
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset18 = .Ltmp12-.Ltmp11
	.long	.Lset18
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset19 = .Ltmp13-.Ltmp12
	.long	.Lset19
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset20 = .Ltmp14-.Ltmp13
	.long	.Lset20
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	4                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	3                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end5:

.Lstartup.eh:
.Lset21 = .Leh_frame_end6-.Leh_frame_begin6 # Length of Frame Information Entry
	.long	.Lset21
.Leh_frame_begin6:
.Lset22 = .Leh_frame_begin6-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset22
	.long	.Leh_func_begin6        # FDE initial location
.Lset23 = .Leh_func_end6-.Leh_func_begin6 # FDE address range
	.long	.Lset23
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset24 = .Ltmp16-.Leh_func_begin6
	.long	.Lset24
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset25 = .Ltmp17-.Ltmp16
	.long	.Lset25
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end6:


	.section	".note.GNU-stack","",@progbits
