	.file	"string-conv1.lcpl.ir"
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
	calll	IO_init
	addl	$12, %esp
	ret
.Ltmp1:
	.size	Main_init, .Ltmp1-Main_init
.Leh_func_end0:

	.globl	M4_Main_mac
	.align	16, 0x90
	.type	M4_Main_mac,@function
M4_Main_mac:                            # @M4_Main_mac
.Leh_func_begin1:
# BB#0:
	subl	$28, %esp
.Ltmp2:
	movl	32(%esp), %eax
	movl	%eax, 24(%esp)
	movl	36(%esp), %eax
	movl	%eax, 20(%esp)
	movl	40(%esp), %eax
	movl	%eax, 16(%esp)
	movl	44(%esp), %eax
	movl	%eax, 12(%esp)
	movl	20(%esp), %eax
	imull	16(%esp), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	12(%esp), %ecx
	movl	%ecx, 4(%esp)
	movl	%eax, (%esp)
	calll	M6_String_concat
	addl	$28, %esp
	ret
.Ltmp3:
	.size	M4_Main_mac, .Ltmp3-M4_Main_mac
.Leh_func_end1:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin2:
# BB#0:
	pushl	%ebx
.Ltmp4:
	pushl	%edi
.Ltmp5:
	pushl	%esi
.Ltmp6:
	subl	$32, %esp
.Ltmp7:
	movl	48(%esp), %esi
	movl	%esi, 28(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	28(%esp), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	movl	$_string_constant.0, 12(%esp)
	movl	$2, 8(%esp)
	movl	$1, 4(%esp)
	calll	*36(%eax)
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$32, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	ret
.Ltmp8:
	.size	M4_Main_main, .Ltmp8-M4_Main_main
.Leh_func_end2:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin3:
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
	calll	*40(%eax)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp11:
	.size	startup, .Ltmp11-startup
.Leh_func_end3:

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
	.asciz	 "3"
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

.LM4_Main_mac.eh:
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
	.byte	32                      # Offset
	.align	4
.Leh_frame_end1:

.LM4_Main_main.eh:
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
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset14 = .Ltmp6-.Ltmp5
	.long	.Lset14
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset15 = .Ltmp7-.Ltmp6
	.long	.Lset15
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	48                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	4                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	3                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end2:

.Lstartup.eh:
.Lset16 = .Leh_frame_end3-.Leh_frame_begin3 # Length of Frame Information Entry
	.long	.Lset16
.Leh_frame_begin3:
.Lset17 = .Leh_frame_begin3-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset17
	.long	.Leh_func_begin3        # FDE initial location
.Lset18 = .Leh_func_end3-.Leh_func_begin3 # FDE address range
	.long	.Lset18
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset19 = .Ltmp9-.Leh_func_begin3
	.long	.Lset19
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset20 = .Ltmp10-.Ltmp9
	.long	.Lset20
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end3:


	.section	".note.GNU-stack","",@progbits
