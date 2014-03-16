;entete
extrn lirent:proc, ligsuiv:proc, ecrent:proc
.MODEL SMALL
.586
.CODE
debut:
	STARTUPCODE

	;ouvrePrinc 6
	mov bp,sp
	sub sp,6

	;lireEnt -2
	lea dx,[bp-2]
	push dx
	call lirent

	;aLaLigne
	call ligsuiv

	;lireEnt -4
	lea dx,[bp-4]
	push dx
	call lirent

	;aLaLigne
	call ligsuiv

	;iload -4
	push word ptr [bp-4]

	;istore -6
	pop ax
	mov word ptr [bp-6],ax

	Faire1 :

	;iload -2
	push word ptr [bp-2]

	;iload -6
	push word ptr [bp-6]

	;iinfegal
	pop bx
	pop ax
	cmp ax,bx
	jg $+6
	push -1
	jmp $+4
	push 0

	;iffaux FAIT1
	pop ax
	cmp ax,0
	je FAIT1

	Faire2 :

	;iload -2
	push word ptr [bp-2]

	;iload -6
	push word ptr [bp-6]

	;iinfegal
	pop bx
	pop ax
	cmp ax,bx
	jg $+6
	push -1
	jmp $+4
	push 0

	;iffaux FAIT2
	pop ax
	cmp ax,0
	je FAIT2

	;iload -2
	push word ptr [bp-2]

	;iconst 1
	push 1

	;iadd
	pop bx
	pop ax
	add ax,bx
	push ax

	;istore -2
	pop ax
	mov word ptr [bp-2],ax

	;goto FAIRE2
	jmp FAIRE2

	FAIT2 :

	Faire3 :

	;iload -2
	push word ptr [bp-2]

	;iload -6
	push word ptr [bp-6]

	;iinfegal
	pop bx
	pop ax
	cmp ax,bx
	jg $+6
	push -1
	jmp $+4
	push 0

	;iffaux FAIT3
	pop ax
	cmp ax,0
	je FAIT3

	;iload -2
	push word ptr [bp-2]

	;iconst 1
	push 1

	;iadd
	pop bx
	pop ax
	add ax,bx
	push ax

	;istore -2
	pop ax
	mov word ptr [bp-2],ax

	;goto FAIRE3
	jmp FAIRE3

	FAIT3 :

	;goto FAIRE1
	jmp FAIRE1

	FAIT1 :

	;iload -6
	push word ptr [bp-6]

	;ecrireEnt
	call ecrent

	;queue
	nop
	EXITCODE
End debut
