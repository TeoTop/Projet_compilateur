;entete
.MODEL SMALL
.586
.CODE

affect:

	;ouvbloc 0
	enter 0,0

	;iload 4
	push word ptr [bp+4]

	;ireturn 6
	pop ax
	mov [bp+6]

	;fermeBloc 2
	leave
	ret 2

debut:
	STARTUPCODE

main:

	;ouvbloc 2
	enter 2,0

	;reserveRetour
	sub sp,2

	;iconst 1
	push 1

	;call affect
	call affect

	;istore -2
	pop ax
	mov word ptr [bp-2],ax

	;queue
	nop
	EXITCODE
End debut
