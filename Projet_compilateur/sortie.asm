;entete
.MODEL SMALL
.586
.CODE
debut:
	STARTUPCODE

	;ouvrePrinc 14
	mov bp,sp
	sub sp,14

	;ecrireChaine " x = "
.DATA
	mess0 DB " x = $"
.CODE
	lea dx,mess0
	push dx
	call ecrch

	;lireEnt -2
	lea dx,[bp-2]
	push dx
	call lirent

	;aLaLigne
	call ligsuiv

	;ecrireChaine " y = "
.DATA
	mess1 DB " y = $"
.CODE
	lea dx,mess1
	push dx
	call ecrch

	;lireEnt -4
	lea dx,[bp-4]
	push dx
	call lirent

	;aLaLigne
	call ligsuiv

	;ecrireChaine " x + y = "
.DATA
	mess2 DB " x + y = $"
.CODE
	lea dx,mess2
	push dx
	call ecrch

	;iload -2
	push word ptr [bp-2]

	;iload -4
	push word ptr [bp-4]

	;iadd
	pop bx
	pop ax
	add ax,bx
	push ax

	;ecrireEnt
	call ecrent

	;aLaLigne
	call ligsuiv

	;iload -2
	push word ptr [bp-2]

	;iload -4
	push word ptr [bp-4]

	;iconst 2
	push 2

	;idiv
	pop bx
	pop ax
	cwd
	div bx
	push ax

	;iadd
	pop bx
	pop ax
	add ax,bx
	push ax

	;ineg
	pop ax
	mul -1
	push ax

	;iconst 5
	push 5

	;idiv
	pop bx
	pop ax
	cwd
	div bx
	push ax

	;istore -6
	pop ax
	mov word ptr [bp-6],ax

	;iload -4
	push word ptr [bp-4]

	;iconst 3
	push 3

	;iload -4
	push word ptr [bp-4]

	;imul
	pop bx
	pop ax
	mul bx
	push ax

	;iadd
	pop bx
	pop ax
	add ax,bx
	push ax

	;iconst 4
	push 4

	;isub
	pop bx
	pop ax
	sub ax,bx
	push ax

	;istore -2
	pop ax
	mov word ptr [bp-2],ax

	;iload -2
	push word ptr [bp-2]

	;iload -6
	push word ptr [bp-6]

	;iinf
	pop bx
	pop ax
	cmp ax,bx
	jge $+6
	push -1
	jmp $+4
	push 0

	;istore -8
	pop ax
	mov word ptr [bp-8],ax

	;iload -8
	push word ptr [bp-8]

	;iconst -1
	push -1

	;ior
	pop bx
	pop ax
	or ax,bx
	push ax

	;inot
	pop ax
	not ax
	push ax

	;istore -8
	pop ax
	mov word ptr [bp-8],ax

	;queue
	nop
	EXITCODE
End debut
