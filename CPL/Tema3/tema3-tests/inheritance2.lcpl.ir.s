	.file	"inheritance2.lcpl.ir"
	.text
	.globl	t_init
	.align	16, 0x90
	.type	t_init,@function
t_init:                                 # @t_init
.Leh_func_begin0:
# BB#0:
	subl	$12, %esp
.Ltmp0:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	p_init
	addl	$12, %esp
	ret
.Ltmp1:
	.size	t_init, .Ltmp1-t_init
.Leh_func_end0:

	.globl	Main_init
	.align	16, 0x90
	.type	Main_init,@function
Main_init:                              # @Main_init
.Leh_func_begin1:
# BB#0:
	subl	$12, %esp
.Ltmp2:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	ph_init
	addl	$12, %esp
	ret
.Ltmp3:
	.size	Main_init, .Ltmp3-Main_init
.Leh_func_end1:

	.globl	p_init
	.align	16, 0x90
	.type	p_init,@function
p_init:                                 # @p_init
.Leh_func_begin2:
# BB#0:
	subl	$12, %esp
.Ltmp4:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	q_init
	addl	$12, %esp
	ret
.Ltmp5:
	.size	p_init, .Ltmp5-p_init
.Leh_func_end2:

	.globl	ph_init
	.align	16, 0x90
	.type	ph_init,@function
ph_init:                                # @ph_init
.Leh_func_begin3:
# BB#0:
	pushl	%esi
.Ltmp6:
	subl	$8, %esp
.Ltmp7:
	movl	16(%esp), %esi
	movl	%esi, 4(%esp)
	movl	%esi, (%esp)
	calll	IO_init
	movl	$Rx, (%esp)
	calll	__lcpl_new
	movl	%eax, 4(%esi)
	addl	$8, %esp
	popl	%esi
	ret
.Ltmp8:
	.size	ph_init, .Ltmp8-ph_init
.Leh_func_end3:

	.globl	q_init
	.align	16, 0x90
	.type	q_init,@function
q_init:                                 # @q_init
.Leh_func_begin4:
# BB#0:
	subl	$12, %esp
.Ltmp9:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	r_init
	addl	$12, %esp
	ret
.Ltmp10:
	.size	q_init, .Ltmp10-q_init
.Leh_func_end4:

	.globl	r_init
	.align	16, 0x90
	.type	r_init,@function
r_init:                                 # @r_init
.Leh_func_begin5:
# BB#0:
	subl	$12, %esp
.Ltmp11:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	Object_init
	addl	$12, %esp
	ret
.Ltmp12:
	.size	r_init, .Ltmp12-r_init
.Leh_func_end5:

	.globl	x_init
	.align	16, 0x90
	.type	x_init,@function
x_init:                                 # @x_init
.Leh_func_begin6:
# BB#0:
	subl	$12, %esp
.Ltmp13:
	movl	16(%esp), %eax
	movl	%eax, 8(%esp)
	movl	%eax, (%esp)
	calll	t_init
	addl	$12, %esp
	ret
.Ltmp14:
	.size	x_init, .Ltmp14-x_init
.Leh_func_end6:

	.globl	M1_t_value
	.align	16, 0x90
	.type	M1_t_value,@function
M1_t_value:                             # @M1_t_value
.Leh_func_begin7:
# BB#0:
	pushl	%eax
.Ltmp15:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	$7, %eax
	popl	%edx
	ret
.Ltmp16:
	.size	M1_t_value, .Ltmp16-M1_t_value
.Leh_func_end7:

	.globl	M4_Main_main
	.align	16, 0x90
	.type	M4_Main_main,@function
M4_Main_main:                           # @M4_Main_main
.Leh_func_begin8:
# BB#0:
	pushl	%ebp
.Ltmp17:
	pushl	%ebx
.Ltmp18:
	pushl	%edi
.Ltmp19:
	pushl	%esi
.Ltmp20:
	subl	$12, %esp
.Ltmp21:
	movl	32(%esp), %esi
	movl	%esi, 8(%esp)
	movl	%esi, (%esp)
	calll	__lcpl_checkNull
	movl	(%esi), %eax
	movl	32(%eax), %edi
	movl	8(%esp), %eax
	movl	4(%eax), %ebx
	movl	%ebx, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebx), %eax
	movl	%ebx, (%esp)
	calll	*36(%eax)
	movl	%eax, %ebx
	movl	8(%esp), %eax
	movl	4(%eax), %ebp
	movl	%ebp, (%esp)
	calll	__lcpl_checkNull
	movl	(%ebp), %eax
	movl	%ebp, (%esp)
	calll	*44(%eax)
	imull	%ebx, %eax
	movl	%eax, (%esp)
	calll	__lcpl_intToString
	movl	%eax, 4(%esp)
	movl	%esi, (%esp)
	calll	*%edi
	addl	$12, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	popl	%ebp
	ret
.Ltmp22:
	.size	M4_Main_main, .Ltmp22-M4_Main_main
.Leh_func_end8:

	.globl	M1_p_val
	.align	16, 0x90
	.type	M1_p_val,@function
M1_p_val:                               # @M1_p_val
.Leh_func_begin9:
# BB#0:
	pushl	%eax
.Ltmp23:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	$6, %eax
	popl	%edx
	ret
.Ltmp24:
	.size	M1_p_val, .Ltmp24-M1_p_val
.Leh_func_end9:

	.globl	M1_q_val
	.align	16, 0x90
	.type	M1_q_val,@function
M1_q_val:                               # @M1_q_val
.Leh_func_begin10:
# BB#0:
	pushl	%eax
.Ltmp25:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	$5, %eax
	popl	%edx
	ret
.Ltmp26:
	.size	M1_q_val, .Ltmp26-M1_q_val
.Leh_func_end10:

	.globl	M1_r_val
	.align	16, 0x90
	.type	M1_r_val,@function
M1_r_val:                               # @M1_r_val
.Leh_func_begin11:
# BB#0:
	pushl	%eax
.Ltmp27:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	$4, %eax
	popl	%edx
	ret
.Ltmp28:
	.size	M1_r_val, .Ltmp28-M1_r_val
.Leh_func_end11:

	.globl	M1_x_value
	.align	16, 0x90
	.type	M1_x_value,@function
M1_x_value:                             # @M1_x_value
.Leh_func_begin12:
# BB#0:
	pushl	%eax
.Ltmp29:
	movl	8(%esp), %eax
	movl	%eax, (%esp)
	movl	$8, %eax
	popl	%edx
	ret
.Ltmp30:
	.size	M1_x_value, .Ltmp30-M1_x_value
.Leh_func_end12:

	.globl	startup
	.align	16, 0x90
	.type	startup,@function
startup:                                # @startup
.Leh_func_begin13:
# BB#0:
	pushl	%esi
.Ltmp31:
	subl	$8, %esp
.Ltmp32:
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
.Ltmp33:
	.size	startup, .Ltmp33-startup
.Leh_func_end13:

	.type	class_name.t,@object    # @class_name.t
	.section	.rodata,"a",@progbits
class_name.t:
	.asciz	 "t"
	.size	class_name.t, 2

	.type	Nt,@object              # @Nt
	.data
	.globl	Nt
	.align	8
Nt:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.t
	.size	Nt, 12

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

	.type	class_name.p,@object    # @class_name.p
	.section	.rodata,"a",@progbits
class_name.p:
	.asciz	 "p"
	.size	class_name.p, 2

	.type	Np,@object              # @Np
	.data
	.globl	Np
	.align	8
Np:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.p
	.size	Np, 12

	.type	class_name.ph,@object   # @class_name.ph
	.section	.rodata,"a",@progbits
class_name.ph:
	.asciz	 "ph"
	.size	class_name.ph, 3

	.type	Nph,@object             # @Nph
	.data
	.globl	Nph
	.align	8
Nph:
	.long	RString
	.long	2                       # 0x2
	.long	class_name.ph
	.size	Nph, 12

	.type	class_name.q,@object    # @class_name.q
	.section	.rodata,"a",@progbits
class_name.q:
	.asciz	 "q"
	.size	class_name.q, 2

	.type	Nq,@object              # @Nq
	.data
	.globl	Nq
	.align	8
Nq:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.q
	.size	Nq, 12

	.type	class_name.r,@object    # @class_name.r
	.section	.rodata,"a",@progbits
class_name.r:
	.asciz	 "r"
	.size	class_name.r, 2

	.type	Nr,@object              # @Nr
	.data
	.globl	Nr
	.align	8
Nr:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.r
	.size	Nr, 12

	.type	class_name.x,@object    # @class_name.x
	.section	.rodata,"a",@progbits
class_name.x:
	.asciz	 "x"
	.size	class_name.x, 2

	.type	Nx,@object              # @Nx
	.data
	.globl	Nx
	.align	8
Nx:
	.long	RString
	.long	1                       # 0x1
	.long	class_name.x
	.size	Nx, 12

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

	.type	Rt,@object              # @Rt
	.globl	Rt
	.align	16
Rt:
	.long	Nt
	.long	4                       # 0x4
	.long	Rp
	.long	t_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M1_r_val
	.long	M1_q_val
	.long	M1_p_val
	.long	M1_t_value
	.size	Rt, 44

	.type	RMain,@object           # @RMain
	.globl	RMain
	.align	16
RMain:
	.long	NMain
	.long	7                       # 0x7
	.long	Rph
	.long	Main_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M2_IO_in
	.long	M2_IO_out
	.long	M4_Main_main
	.size	RMain, 40

	.type	Rp,@object              # @Rp
	.globl	Rp
	.align	16
Rp:
	.long	Np
	.long	4                       # 0x4
	.long	Rq
	.long	p_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M1_r_val
	.long	M1_q_val
	.long	M1_p_val
	.size	Rp, 40

	.type	Rph,@object             # @Rph
	.globl	Rph
	.align	16
Rph:
	.long	Nph
	.long	7                       # 0x7
	.long	RIO
	.long	ph_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M2_IO_in
	.long	M2_IO_out
	.size	Rph, 36

	.type	Rq,@object              # @Rq
	.globl	Rq
	.align	16
Rq:
	.long	Nq
	.long	4                       # 0x4
	.long	Rr
	.long	q_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M1_r_val
	.long	M1_q_val
	.size	Rq, 36

	.type	Rr,@object              # @Rr
	.globl	Rr
	.align	16
Rr:
	.long	Nr
	.long	4                       # 0x4
	.long	RObject
	.long	r_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M1_r_val
	.size	Rr, 32

	.type	Rx,@object              # @Rx
	.globl	Rx
	.align	16
Rx:
	.long	Nx
	.long	4                       # 0x4
	.long	Rt
	.long	x_init
	.long	M6_Object_abort
	.long	M6_Object_typeName
	.long	M6_Object_copy
	.long	M1_r_val
	.long	M1_q_val
	.long	M1_p_val
	.long	M1_t_value
	.long	M1_x_value
	.size	Rx, 48

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
.Lt_init.eh:
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

.LMain_init.eh:
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

.Lp_init.eh:
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

.Lph_init.eh:
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
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end3:

.Lq_init.eh:
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
	.byte	16                      # Offset
	.align	4
.Leh_frame_end4:

.Lr_init.eh:
.Lset22 = .Leh_frame_end5-.Leh_frame_begin5 # Length of Frame Information Entry
	.long	.Lset22
.Leh_frame_begin5:
.Lset23 = .Leh_frame_begin5-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset23
	.long	.Leh_func_begin5        # FDE initial location
.Lset24 = .Leh_func_end5-.Leh_func_begin5 # FDE address range
	.long	.Lset24
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset25 = .Ltmp11-.Leh_func_begin5
	.long	.Lset25
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.align	4
.Leh_frame_end5:

.Lx_init.eh:
.Lset26 = .Leh_frame_end6-.Leh_frame_begin6 # Length of Frame Information Entry
	.long	.Lset26
.Leh_frame_begin6:
.Lset27 = .Leh_frame_begin6-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset27
	.long	.Leh_func_begin6        # FDE initial location
.Lset28 = .Leh_func_end6-.Leh_func_begin6 # FDE address range
	.long	.Lset28
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset29 = .Ltmp13-.Leh_func_begin6
	.long	.Lset29
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.align	4
.Leh_frame_end6:

.LM1_t_value.eh = 0

.LM4_Main_main.eh:
.Lset30 = .Leh_frame_end8-.Leh_frame_begin8 # Length of Frame Information Entry
	.long	.Lset30
.Leh_frame_begin8:
.Lset31 = .Leh_frame_begin8-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset31
	.long	.Leh_func_begin8        # FDE initial location
.Lset32 = .Leh_func_end8-.Leh_func_begin8 # FDE address range
	.long	.Lset32
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset33 = .Ltmp17-.Leh_func_begin8
	.long	.Lset33
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset34 = .Ltmp18-.Ltmp17
	.long	.Lset34
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	12                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset35 = .Ltmp19-.Ltmp18
	.long	.Lset35
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset36 = .Ltmp20-.Ltmp19
	.long	.Lset36
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	20                      # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset37 = .Ltmp21-.Ltmp20
	.long	.Lset37
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	32                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	5                       # Offset
	.byte	135                     # DW_CFA_offset + Reg (7)
	.byte	4                       # Offset
	.byte	131                     # DW_CFA_offset + Reg (3)
	.byte	3                       # Offset
	.byte	133                     # DW_CFA_offset + Reg (5)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end8:

.LM1_p_val.eh = 0

.LM1_q_val.eh = 0

.LM1_r_val.eh = 0

.LM1_x_value.eh = 0

.Lstartup.eh:
.Lset38 = .Leh_frame_end13-.Leh_frame_begin13 # Length of Frame Information Entry
	.long	.Lset38
.Leh_frame_begin13:
.Lset39 = .Leh_frame_begin13-.Leh_frame_common0 # FDE CIE offset
	.long	.Lset39
	.long	.Leh_func_begin13       # FDE initial location
.Lset40 = .Leh_func_end13-.Leh_func_begin13 # FDE address range
	.long	.Lset40
	.byte	0                       # Augmentation size
	.byte	4                       # DW_CFA_advance_loc4
.Lset41 = .Ltmp31-.Leh_func_begin13
	.long	.Lset41
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	8                       # Offset
	.byte	4                       # DW_CFA_advance_loc4
.Lset42 = .Ltmp32-.Ltmp31
	.long	.Lset42
	.byte	14                      # DW_CFA_def_cfa_offset
	.byte	16                      # Offset
	.byte	134                     # DW_CFA_offset + Reg (6)
	.byte	2                       # Offset
	.align	4
.Leh_frame_end13:


	.section	".note.GNU-stack","",@progbits
