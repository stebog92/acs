	.file	"cast2.lcpl.ir"
	.text
	.globl	intModel_init
	.align	16, 0x90
	.type	intModel_init,@function
intModel_init:                          # @intModel_init
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
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp2:
	.size	intModel_init, .Ltmp2-intModel_init
.Leh_func_end0:

	.globl	int_init
	.align	16, 0x90
	.type	int_init,@function
int_init:                               # @int_init
.Leh_func_begin1:
# BB#0:
	subl	$12, %esp
.Ltmp3:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	intModel_init
	addl	$12, %esp
	ret
.Ltmp4:
	.size	int_init, .Ltmp4-int_init
.Leh_func_end1:

	.globl	Main_init
	.align	16, 0x90
	.type	Main_init,@function
Main_init:                              # @Main_init
.Leh_func_begin2:
# BB#0:
	subl	$12, %esp
.Ltmp5:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	IO_init
	addl	$12, %esp
	ret
.Ltmp6:
	.size	Main_init, .Ltmp6-Main_init
.Leh_func_end2:

	.globl	M8_intModel_setValue
	.align	16, 0x90
	.type	M8_intModel_setValue,@function
M8_intModel_setValue:                   # @M8_intModel_setValue
.Leh_func_begin3:
# BB#0:
	subl	$8, %esp
.Ltmp7:
	movl	12(%esp), %eax
	movl	%eax, 4(%esp)
	movl	16(%esp), %eax
	movl	%eax, (%esp)
	movl	4(%esp), %ecx
	movl	%eax, 4(%ecx)
	addl	$8, %esp
	ret
.Ltmp8:
	.size	M8_intModel_setValue, .Ltmp8-M8_intModel_setValue
.Leh_func_end3:

	.globl	M3_int_getValue
	.align	16, 0x90
	.type	M3_int_getValue,@function
M3_int_getValue:                        # @M3_int_getValue
.Leh_func_begin4:
# BB#0:
	pushl	%eax
.Ltmp9:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	4(%eax), %eax
	popl	%edx
	ret
.Ltmp10:
	.size	M3_int_getValue, .Ltmp10-M3_int_getValue
.Leh_func_end4:

	.globl	M4_Main_mac
	.align	16, 0x90
	.type	M4_Main_mac,@function
M4_Main_mac:                            # @M4_Main_mac
.Leh_func_begin5:
# BB#0:
	pushl	%edi
.Ltmp11:
	pushl	%esi
.Ltmp12:
	subl	$20, %esp
.Ltmp13:
	movl	32(%esp), %eax
	movl	%eax, 16(%esp)
	movl	36(%esp), %eax
	movl	%eax, 12(%esp)
	movl	40(%esp), %eax
	movl	%eax, 8(%esp)
	movl	44(%esp), %esi
	movl	%esi, 4(%esp)
	movl	12(%esp), %edi
	imull	8(%esp), %edi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*32(%eax)
	addl	%edi, %eax
	addl	$20, %esp
	popl	%esi
	popl	%edi
	ret
.Ltmp14:
	.size	M4_Main_mac, .Ltmp14-M4_Main_mac
.Leh_func_end5:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin6:
# BB#0:
	pushl	%ebp
.Ltmp15:
	pushl	%ebx
.Ltmp16:
	pushl	%edi
.Ltmp17:
	pushl	%esi
.Ltmp18:
	subl	$28, %esp
.Ltmp19:
	movl	48(%esp), %eax
	movl	%eax, 24(%esp)
	movl	$Rint, (%esp)
	calll	__lcpl_new
	movl	%eax, (%esp)
	movl	$RintModel, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, %esi
	movl	%esi, 20(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$3, 4(%esp)
	calll	*28(%eax)
	movl	24(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	24(%esp), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	36(%eax), %ebp
	movl	20(%esp), %eax
	movl	%eax, (%esp)
	movl	$Rint, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 12(%esp)
	movl	%ebx, (%esp)
	movl	$2, 8(%esp)
	movl	$1, 4(%esp)
	calll	*%ebp
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$28, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	ret
.Ltmp20:
	.size	M4_Main_main, .Ltmp20-M4_Main_main
.Leh_func_end6:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin7:
# BB#0:
	pushl	%esi
.Ltmp21:
	subl	$8, %esp
.Ltmp22:
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
.Ltmp23:
	.size	startup, .Ltmp23-startup
.Leh_func_end7:

	.type	class_name.intModel,@object # @class_name.intModel
	.section	.rodata,"a",@progbits
class_name.intModel:
	.asciz	 "intModel"
	.size	class_name.intModel, 9

	.type	NintModel,@object       # @NintModel
	.data
	.globl	NintModel
	.align	8
NintModel:
	.long	RString
	.long	8                       # 0x8
	.long	class_name.intModel
	.size	NintModel, 12

	.type	class_name.int,@object  # @class_name.int
	.section	.rodata,"a",@progbits
class_name.int:
	.asciz	 "int"
	.size	class_name.int, 4

	.type	Nint,@object            # @Nint
	.data
	.globl	Nint
	.align	8
Nint:
	.long	RString
	.long	3                       # 0x3
	.long	class_name.int
	.size	Nint, 12

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

	.type	RintModel,@object       # @RintModel
	.globl	RintModel
	.align	16
RintModel:
	.long	NintModel
	.long	7                       # 0x7
	.long	RObject
	.long	intModel_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M8_intModel_setValue
	.size	RintModel, 32

	.type	Rint,@object            # @Rint
	.globl	Rint
	.align	16
Rint:
	.long	Nint
	.long	7                       # 0x7
	.long	RintModel
	.long	int_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M8_intModel_setValue
	.long	M3_int_getValue
	.size	Rint, 36

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
	.long	M4_Main_mac
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
.LintModel_init.eh:
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

.Lint_init.eh:
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
	.byte	16                      # Offset
	.align	4
.Leh_frame_end1:

.LMain_init.eh:
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

.LM8_intModel_setValue.eh = 0

.LM3_int_getValue.eh = 0

.LM4_Main_mac.eh:
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
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	3                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end5:

.LM4_Main_main.eh:
.Lset20 = .Leh_frame_end6-.Leh_frame_begin6 # Length of Frame Information Entry
	.long	.Lset20
.Leh_frame_begin6:
.Lset21 = .Leh_frame_begin6-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset21
	.long	.Leh_func_begin6        # FDE initial location
.Lset22 = .Leh_func_end6-.Leh_func_begin6 # FDE address range
	.long	.Lset22
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset23 = .Ltmp15-.Leh_func_begin6
	.long	.Lset23
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset24 = .Ltmp16-.Ltmp15
	.long	.Lset24
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset25 = .Ltmp17-.Ltmp16
	.long	.Lset25
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset26 = .Ltmp18-.Ltmp17
	.long	.Lset26
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	20                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset27 = .Ltmp19-.Ltmp18
	.long	.Lset27
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
.Leh_frame_end6:

.Lstartup.eh:
.Lset28 = .Leh_frame_end7-.Leh_frame_begin7 # Length of Frame Information Entry
	.long	.Lset28
.Leh_frame_begin7:
.Lset29 = .Leh_frame_begin7-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset29
	.long	.Leh_func_begin7        # FDE initial location
.Lset30 = .Leh_func_end7-.Leh_func_begin7 # FDE address range
	.long	.Lset30
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset31 = .Ltmp21-.Leh_func_begin7
	.long	.Lset31
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset32 = .Ltmp22-.Ltmp21
	.long	.Lset32
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end7:


	.section	".note.GNU-stack","",@progbits
