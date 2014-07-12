	.file	"string-conv.lcpl.ir"
	.text
	.globl	IntStr_init
	.align	16, 0x90
	.type	IntStr_init,@function
IntStr_init:                            # @IntStr_init
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
	movl	$_string_constant.strEmpty, 4(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp2:
	.size	IntStr_init, .Ltmp2-IntStr_init
.Leh_func_end0:

	.globl	Main_init
	.align	16, 0x90
	.type	Main_init,@function
Main_init:                              # @Main_init
.Leh_func_begin1:
# BB#0:
	subl	$12, %esp
.Ltmp3:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	IO_init
	addl	$12, %esp
	ret
.Ltmp4:
	.size	Main_init, .Ltmp4-Main_init
.Leh_func_end1:

	.globl	M6_IntStr_set
	.align	16, 0x90
	.type	M6_IntStr_set,@function
M6_IntStr_set:                          # @M6_IntStr_set
.Leh_func_begin2:
# BB#0:
	subl	$8, %esp
.Ltmp5:
	movl	12(%esp), %eax
	movl	%eax, 4(%esp)
	movl	16(%esp), %eax
	movl	%eax, (%esp)
	movl	4(%esp), %ecx
	movl	%eax, 4(%ecx)
	addl	$8, %esp
	ret
.Ltmp6:
	.size	M6_IntStr_set, .Ltmp6-M6_IntStr_set
.Leh_func_end2:

	.globl	M6_IntStr_get
	.align	16, 0x90
	.type	M6_IntStr_get,@function
M6_IntStr_get:                          # @M6_IntStr_get
.Leh_func_begin3:
# BB#0:
	pushl	%esi
.Ltmp7:
	subl	$8, %esp
.Ltmp8:
	movl	16(%esp), %eax
	movl	%eax, 4(%esp)
	movl	4(%eax), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*32(%eax)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp9:
	.size	M6_IntStr_get, .Ltmp9-M6_IntStr_get
.Leh_func_end3:

	.globl	M4_Main_mac
	.align	16, 0x90
	.type	M4_Main_mac,@function
M4_Main_mac:                            # @M4_Main_mac
.Leh_func_begin4:
# BB#0:
	pushl	%edi
.Ltmp10:
	pushl	%esi
.Ltmp11:
	subl	$20, %esp
.Ltmp12:
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
.Ltmp13:
	.size	M4_Main_mac, .Ltmp13-M4_Main_mac
.Leh_func_end4:

	.globl	M4_Main_mac2
	.align	16, 0x90
	.type	M4_Main_mac2,@function
M4_Main_mac2:                           # @M4_Main_mac2
.Leh_func_begin5:
# BB#0:
	pushl	%edi
.Ltmp14:
	pushl	%esi
.Ltmp15:
	subl	$20, %esp
.Ltmp16:
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
.Ltmp17:
	.size	M4_Main_mac2, .Ltmp17-M4_Main_mac2
.Leh_func_end5:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin6:
# BB#0:
	pushl	%ebp
.Ltmp18:
	pushl	%ebx
.Ltmp19:
	pushl	%edi
.Ltmp20:
	pushl	%esi
.Ltmp21:
	subl	$28, %esp
.Ltmp22:
	movl	48(%esp), %eax
	movl	%eax, 24(%esp)
	movl	$RIntStr, (%esp)
	calll	__lcpl_new
	movl	%eax, 20(%esp)
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
	movl	$3, (%esp)
	calll	__lcpl_intToString
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
	movl	20(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$_string_constant.0, 4(%esp)
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
	movl	20(%esp), %ecx
	movl	%ecx, 12(%esp)
	movl	%ebx, (%esp)
	movl	$2, 8(%esp)
	movl	$1, 4(%esp)
	calll	*40(%eax)
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
.Ltmp23:
	.size	M4_Main_main, .Ltmp23-M4_Main_main
.Leh_func_end6:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin7:
# BB#0:
	pushl	%esi
.Ltmp24:
	subl	$8, %esp
.Ltmp25:
	movl	$RMain, (%esp)
	calll	__lcpl_new
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*44(%eax)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp26:
	.size	startup, .Ltmp26-startup
.Leh_func_end7:

	.type	class_name.IntStr,@object # @class_name.IntStr
	.section	.rodata,"a",@progbits
class_name.IntStr:
	.asciz	 "IntStr"
	.size	class_name.IntStr, 7

	.type	NIntStr,@object         # @NIntStr
	.data
	.globl	NIntStr
	.align	8
NIntStr:
	.long	RString
	.long	6                       # 0x6
	.long	class_name.IntStr
	.size	NIntStr, 12

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

	.type	RIntStr,@object         # @RIntStr
	.globl	RIntStr
	.align	16
RIntStr:
	.long	NIntStr
	.long	7                       # 0x7
	.long	RObject
	.long	IntStr_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M6_IntStr_set
	.long	M6_IntStr_get
	.size	RIntStr, 36

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
	.long	M4_Main_mac2
	.long	M4_Main_main
	.size	RMain, 48

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
.LIntStr_init.eh:
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

.LMain_init.eh:
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

.LM6_IntStr_set.eh = 0

.LM6_IntStr_get.eh:
.Lset10 = .Leh_frame_end3-.Leh_frame_begin3 # Length of Frame Information Entry
	.long	.Lset10
.Leh_frame_begin3:
.Lset11 = .Leh_frame_begin3-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset11
	.long	.Leh_func_begin3        # FDE initial location
.Lset12 = .Leh_func_end3-.Leh_func_begin3 # FDE address range
	.long	.Lset12
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset13 = .Ltmp7-.Leh_func_begin3
	.long	.Lset13
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset14 = .Ltmp8-.Ltmp7
	.long	.Lset14
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end3:

.LM4_Main_mac.eh:
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
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	3                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end4:

.LM4_Main_mac2.eh:
.Lset21 = .Leh_frame_end5-.Leh_frame_begin5 # Length of Frame Information Entry
	.long	.Lset21
.Leh_frame_begin5:
.Lset22 = .Leh_frame_begin5-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset22
	.long	.Leh_func_begin5        # FDE initial location
.Lset23 = .Leh_func_end5-.Leh_func_begin5 # FDE address range
	.long	.Lset23
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset24 = .Ltmp14-.Leh_func_begin5
	.long	.Lset24
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset25 = .Ltmp15-.Ltmp14
	.long	.Lset25
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset26 = .Ltmp16-.Ltmp15
	.long	.Lset26
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	3                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end5:

.LM4_Main_main.eh:
.Lset27 = .Leh_frame_end6-.Leh_frame_begin6 # Length of Frame Information Entry
	.long	.Lset27
.Leh_frame_begin6:
.Lset28 = .Leh_frame_begin6-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset28
	.long	.Leh_func_begin6        # FDE initial location
.Lset29 = .Leh_func_end6-.Leh_func_begin6 # FDE address range
	.long	.Lset29
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset30 = .Ltmp18-.Leh_func_begin6
	.long	.Lset30
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset31 = .Ltmp19-.Ltmp18
	.long	.Lset31
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset32 = .Ltmp20-.Ltmp19
	.long	.Lset32
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset33 = .Ltmp21-.Ltmp20
	.long	.Lset33
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	20                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset34 = .Ltmp22-.Ltmp21
	.long	.Lset34
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
.Lset35 = .Leh_frame_end7-.Leh_frame_begin7 # Length of Frame Information Entry
	.long	.Lset35
.Leh_frame_begin7:
.Lset36 = .Leh_frame_begin7-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset36
	.long	.Leh_func_begin7        # FDE initial location
.Lset37 = .Leh_func_end7-.Leh_func_begin7 # FDE address range
	.long	.Lset37
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset38 = .Ltmp24-.Leh_func_begin7
	.long	.Lset38
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset39 = .Ltmp25-.Ltmp24
	.long	.Lset39
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end7:


	.section	".note.GNU-stack","",@progbits
