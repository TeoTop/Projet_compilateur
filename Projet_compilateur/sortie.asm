;entete
extrn ecrch:proc, lirent:proc, ligsuiv:proc, ecrent:proc
.MODEL SMALL
.586
.CODE
debut:
	STARTUPCODE

	;ouvrePrinc 6
	mov bp,sp
	sub sp,6

	;ecrireChaine "x = "
.DATA
	mess0 DB "x = $"
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

	;ecrireChaine "y = "
.DATA
	mess1 DB "y = $"
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

	;ecrireChaine "x = "
.DATA
	mess2 DB "x = $"
.CODE
	lea dx,mess2
	push dx
	call ecrch

	;iload -2
	push word ptr [bp-2]

	;ecrireEnt
	call ecrent

	;aLaLigne
	call ligsuiv

	;ecrireChaine "z = "
.DATA
	mess3 DB "z = $"
.CODE
	lea dx,mess3
	push dx
	call ecrch

	;iload -6
	push word ptr [bp-6]

	;ecrireEnt
	call ecrent

	;aLaLigne
	call ligsuiv

	;ecrireChaine "a = "
.DATA
	mess4 DB "a = $"
.CODE
	lea dx,mess4
	push dx
	call ecrch

	;iconst -6
	push -6

	;ecrireEnt
	call ecrent

	;queue
	nop
	EXITCODE
End debut
