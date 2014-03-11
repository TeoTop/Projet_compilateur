;entete
extrn lirent:proc
.MODEL SMALL
.586
.CODE
debut:
	STARTUPCODE

	;ouvrePrinc 8
	mov bp,sp
	sub sp,8

	;lireEnt -2
	lea dx,[bp-2]
	push dx
	call lirent

	;istore -2
	pop ax
	mov word ptr [bp-2],ax

	;iload -2
	push word ptr [bp-2]

	;istore -4
	pop ax
	mov word ptr [bp-4],ax

	;queue
	nop
	EXITCODE
End debut
