	.file	"attributes3.lcpl.ir"
	.text
	.globl	Main_init
	.align	16, 0x90
	.type	Main_init,@function
Main_init:                              # @Main_init
.Leh_func_begin0:
# BB#0:
	pushl	%esi
.Ltmp0:
	subl	$8, %esp
.Ltmp1:
	movl	16(%esp), %esi
	movl	%esi, 4(%esp)
	movl	%esi, (%esp)
	calll	IO_init
	movl	$0, 4(%esi)
	movl	$1234, 8(%esi)          # imm = 0x4D2
	movl	$_string_constant.0, 12(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp2:
	.size	Main_init, .Ltmp2-Main_init
.Leh_func_end0:

	.globl	M4_Main_print
	.align	16, 0x90
	.type	M4_Main_print,@function
M4_Main_print:                          # @M4_Main_print
.Leh_func_begin1:
# BB#0:
	pushl	%edi
.Ltmp3:
	pushl	%esi
.Ltmp4:
	subl	$20, %esp
.Ltmp5:
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
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	16(%esp), %ecx
	movl	12(%ecx), %ecx
	movl	%ecx, 4(%esp)
	movl	%esi, (%esp)
	calll	*32(%eax)
	movl	12(%esp), %eax
	movl	%eax, 4(%esp)
	movl	%eax, (%esp)
	calll	M6_String_concat
	movl	%eax, 12(%esp)
	movl	16(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	12(%esp), %ecx
	movl	%ecx, 4(%esp)
	movl	%esi, (%esp)
	calll	*32(%eax)
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	16(%esp), %eax
	movl	4(%eax), %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	16(%esp), %eax
	movl	4(%eax), %ecx
	addl	8(%eax), %ecx
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	%ecx, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$20, %esp
	popl	%esi
	popl	%edi
	ret
.Ltmp6:
	.size	M4_Main_print, .Ltmp6-M4_Main_print
.Leh_func_end1:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin2:
# BB#0:
	pushl	%esi
.Ltmp7:
	subl	$24, %esp
.Ltmp8:
	movl	32(%esp), %esi
	movl	%esi, 20(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$_string_constant.1, 4(%esp)
	calll	*36(%eax)
	addl	$24, %esp
	popl	%esi
	ret
.Ltmp9:
	.size	M4_Main_main, .Ltmp9-M4_Main_main
.Leh_func_end2:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin3:
# BB#0:
	pushl	%esi
.Ltmp10:
	subl	$8, %esp
.Ltmp11:
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
.Ltmp12:
	.size	startup, .Ltmp12-startup
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
	.align	16
string_constant.0:
	.asciz	 "This is a string"
	.size	string_constant.0, 17

	.type	_string_constant.0,@object # @_string_constant.0
	.data
	.globl	_string_constant.0
	.align	8
_string_constant.0:
	.long	RString
	.long	16                      # 0x10
	.long	string_constant.0
	.size	_string_constant.0, 12

	.type	string_constant.1,@object # @string_constant.1
	.section	.rodata,"a",@progbits
	.globl	string_constant.1
string_constant.1:
	.asciz	 ">"
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

	.type	RMain,@object           # @RMain
	.globl	RMain
	.align	16
RMain:
	.long	NMain
	.long	13                      # 0xd
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

.LM4_Main_print.eh:
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
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset11 = .Ltmp5-.Ltmp4
	.long	.Lset11
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	3                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end1:

.LM4_Main_main.eh:
.Lset12 = .Leh_frame_end2-.Leh_frame_begin2 # Length of Frame Information Entry
	.long	.Lset12
.Leh_frame_begin2:
.Lset13 = .Leh_frame_begin2-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset13
	.long	.Leh_func_begin2        # FDE initial location
.Lset14 = .Leh_func_end2-.Leh_func_begin2 # FDE address range
	.long	.Lset14
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset15 = .Ltmp7-.Leh_func_begin2
	.long	.Lset15
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset16 = .Ltmp8-.Ltmp7
	.long	.Lset16
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end2:

.Lstartup.eh:
.Lset17 = .Leh_frame_end3-.Leh_frame_begin3 # Length of Frame Information Entry
	.long	.Lset17
.Leh_frame_begin3:
.Lset18 = .Leh_frame_begin3-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset18
	.long	.Leh_func_begin3        # FDE initial location
.Lset19 = .Leh_func_end3-.Leh_func_begin3 # FDE address range
	.long	.Lset19
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset20 = .Ltmp10-.Leh_func_begin3
	.long	.Lset20
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset21 = .Ltmp11-.Ltmp10
	.long	.Lset21
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end3:


	.section	".note.GNU-stack","",@progbits
