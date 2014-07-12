	.file	"overload.lcpl.ir"
	.text
	.globl	i_init
	.align	16, 0x90
	.type	i_init,@function
i_init:                                 # @i_init
.Leh_func_begin0:
# BB#0:
	pushl	%esi
.Ltmp0:
	subl	$8, %esp
.Ltmp1:
	movl	16(%esp), %esi
	movl	%esi, 4(%esp)
	movl	%esi, (%esp)
	calll	j_init
	movl	$0, 4(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp2:
	.size	i_init, .Ltmp2-i_init
.Leh_func_end0:

	.globl	j_init
	.align	16, 0x90
	.type	j_init,@function
j_init:                                 # @j_init
.Leh_func_begin1:
# BB#0:
	pushl	%esi
.Ltmp3:
	subl	$8, %esp
.Ltmp4:
	movl	16(%esp), %esi
	movl	%esi, 4(%esp)
	movl	%esi, (%esp)
	calll	Object_init
	movl	$0, 4(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp5:
	.size	j_init, .Ltmp5-j_init
.Leh_func_end1:

	.globl	Main_init
	.align	16, 0x90
	.type	Main_init,@function
Main_init:                              # @Main_init
.Leh_func_begin2:
# BB#0:
	pushl	%esi
.Ltmp6:
	subl	$24, %esp
.Ltmp7:
	movl	32(%esp), %esi
	movl	%esi, 20(%esp)
	movl	%esi, (%esp)
	calll	Object_init
	movl	$Ri, (%esp)
	calll	__lcpl_new
	movl	%eax, (%esp)
	movl	$Rj, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 4(%esi)
	addl	$24, %esp
	popl	%esi
	ret
.Ltmp8:
	.size	Main_init, .Ltmp8-Main_init
.Leh_func_end2:

	.globl	M1_i_I
	.align	16, 0x90
	.type	M1_i_I,@function
M1_i_I:                                 # @M1_i_I
.Leh_func_begin3:
# BB#0:
	pushl	%eax
.Ltmp9:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	4(%eax), %ecx
	movl	%ecx, 8(%eax)
	popl	%eax
	ret
.Ltmp10:
	.size	M1_i_I, .Ltmp10-M1_i_I
.Leh_func_end3:

	.globl	M1_i_J
	.align	16, 0x90
	.type	M1_i_J,@function
M1_i_J:                                 # @M1_i_J
.Leh_func_begin4:
# BB#0:
	pushl	%eax
.Ltmp11:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	8(%eax), %ecx
	movl	%ecx, 4(%eax)
	popl	%eax
	ret
.Ltmp12:
	.size	M1_i_J, .Ltmp12-M1_i_J
.Leh_func_end4:

	.globl	M1_i_i
	.align	16, 0x90
	.type	M1_i_i,@function
M1_i_i:                                 # @M1_i_i
.Leh_func_begin5:
# BB#0:
	pushl	%esi
.Ltmp13:
	subl	$24, %esp
.Ltmp14:
	movl	32(%esp), %eax
	movl	%eax, 20(%esp)
	movl	36(%esp), %eax
	movl	%eax, 16(%esp)
	incl	%eax
	movl	20(%esp), %ecx
	movl	%eax, 4(%ecx)
	movl	20(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*28(%eax)
	movl	16(%esp), %eax
	addl	%eax, %eax
	movl	20(%esp), %ecx
	movl	%eax, 4(%ecx)
	movl	16(%esp), %eax
	addl	$2, %eax
	movl	%eax, 12(%esp)
	movl	20(%esp), %ecx
	subl	4(%ecx), %eax
	movl	%eax, 12(%esp)
	movl	20(%esp), %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	calll	*32(%eax)
	movl	20(%esp), %eax
	movl	4(%eax), %eax
	imull	12(%esp), %eax
	addl	$24, %esp
	popl	%esi
	ret
.Ltmp15:
	.size	M1_i_i, .Ltmp15-M1_i_i
.Leh_func_end5:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin6:
# BB#0:
	pushl	%ebx
.Ltmp16:
	pushl	%edi
.Ltmp17:
	pushl	%esi
.Ltmp18:
	subl	$16, %esp
.Ltmp19:
	movl	32(%esp), %eax
	movl	%eax, 12(%esp)
	movl	4(%eax), %eax
	movl	%eax, (%esp)
	movl	$Ri, 4(%esp)
	calll	__lcpl_cast
	movl	%eax, 8(%esp)
	movl	$RIO, (%esp)
	calll	__lcpl_new
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	8(%esp), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	movl	$4, 4(%esp)
	calll	*36(%eax)
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	movl	%eax, %esi
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	%esi, (%esp)
	movl	$_string_constant.0, 4(%esp)
	calll	*32(%eax)
	addl	$16, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
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
	calll	*28(%eax)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp23:
	.size	startup, .Ltmp23-startup
.Leh_func_end7:

	.type	class_name.i,@object    # @class_name.i
	.section	.rodata,"a",@progbits
class_name.i:
	.asciz	 "i"
	.size	class_name.i, 2

	.type	Ni,@object              # @Ni
	.data
	.globl	Ni
	.align	8
Ni:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.i
	.size	Ni, 12

	.type	class_name.j,@object    # @class_name.j
	.section	.rodata,"a",@progbits
class_name.j:
	.asciz	 "j"
	.size	class_name.j, 2

	.type	Nj,@object              # @Nj
	.data
	.globl	Nj
	.align	8
Nj:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.j
	.size	Nj, 12

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
	.asciz	 "\n"
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

	.type	Ri,@object              # @Ri
	.globl	Ri
	.align	16
Ri:
	.long	Ni
	.long	10                      # 0xa
	.long	Rj
	.long	i_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M1_i_I
	.long	M1_i_J
	.long	M1_i_i
	.size	Ri, 40

	.type	Rj,@object              # @Rj
	.globl	Rj
	.align	16
Rj:
	.long	Nj
	.long	7                       # 0x7
	.long	RObject
	.long	j_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.size	Rj, 28

	.type	RMain,@object           # @RMain
	.globl	RMain
	.align	16
RMain:
	.long	NMain
	.long	7                       # 0x7
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
.Li_init.eh:
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

.Lj_init.eh:
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
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end1:

.LMain_init.eh:
.Lset11 = .Leh_frame_end2-.Leh_frame_begin2 # Length of Frame Information Entry
	.long	.Lset11
.Leh_frame_begin2:
.Lset12 = .Leh_frame_begin2-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset12
	.long	.Leh_func_begin2        # FDE initial location
.Lset13 = .Leh_func_end2-.Leh_func_begin2 # FDE address range
	.long	.Lset13
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset14 = .Ltmp6-.Leh_func_begin2
	.long	.Lset14
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset15 = .Ltmp7-.Ltmp6
	.long	.Lset15
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end2:

.LM1_i_I.eh = 0

.LM1_i_J.eh = 0

.LM1_i_i.eh:
.Lset16 = .Leh_frame_end5-.Leh_frame_begin5 # Length of Frame Information Entry
	.long	.Lset16
.Leh_frame_begin5:
.Lset17 = .Leh_frame_begin5-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset17
	.long	.Leh_func_begin5        # FDE initial location
.Lset18 = .Leh_func_end5-.Leh_func_begin5 # FDE address range
	.long	.Lset18
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset19 = .Ltmp13-.Leh_func_begin5
	.long	.Lset19
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset20 = .Ltmp14-.Ltmp13
	.long	.Lset20
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end5:

.LM4_Main_main.eh:
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
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset26 = .Ltmp18-.Ltmp17
	.long	.Lset26
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset27 = .Ltmp19-.Ltmp18
	.long	.Lset27
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
