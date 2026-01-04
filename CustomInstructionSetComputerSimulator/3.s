.global main
	.extern btoi
	.extern itob
.data
ten: .long 10
counter: .long 0
itoa_string: .ascii "          \n"
sjsuprompt: .ascii "(sjsu) "
instruction: .space 64
ilen: .long 0
alu: .long 0

itob_string: .ascii "                                  \n"
.text
main:
	mov $4, %eax
	mov $1, %ebx
	mov $sjsuprompt, %ecx
	mov $7, %edx
	int $0x80

	mov $3,%eax
	mov $0, %ebx
	mov $instruction, %ecx
	mov $64, %edx
	int $0x80

	dec %eax
	mov %eax, ilen

	cmpl $0x20766f6d, instruction
	je pre_mov
	cmpl $0x20726f72, instruction
	je do_ror
	cmpl $0x206c6f72, instruction
	je do_rol
	cmpl $0x206c6173, instruction
	je do_sal
	cmpl $0x20726173, instruction
	je do_sar
	cmpl $0x20726f78, instruction
	je pre_xor
	cmpw $0x726f, instruction
	jne not
	cmpb $' ', instruction + 2
	jne not
	jmp do_or

not:
	cmpl $0x20646e61, instruction
	je do_and
	cmpl $0x0a746f6e, instruction
	je do_not
	jmp main

pre_mov:
	call check
	jmp do_mov

pre_xor:
	call check
	jmp do_xor
do_mov:
	mov counter, %eax
	mov %eax, alu
	mov %eax, counter
	call itoa

	mov $4, %eax
	mov $1, %ebx
	mov $itoa_string, %ecx
	mov $11, %edx
	int $0x80

	call print_b
	jmp main

do_rol:
	call check
    mov alu, %eax
	mov counter, %ecx
    andl $31, %ecx
	roll %cl, %eax
	mov %eax, alu
	mov %eax, counter
    call itoa

    mov $4, %eax
    mov $1, %ebx
    mov $itoa_string, %ecx
    mov $11, %edx
    int $0x80

    call print_b
    jmp main

do_ror:
	call check
	mov alu, %eax
	mov counter, %ecx
	andl $31, %ecx
	rorl %cl, %eax
	mov %eax, alu
	mov %eax, counter
	call itoa

	mov $4, %eax
	mov $1, %ebx
	mov $itoa_string, %ecx
	mov $11, %edx
	int $0x80

	call print_b
	jmp main

do_sal:
	call check
	mov alu, %eax
    mov counter, %ecx
    andl $31, %ecx
    sall %cl, %eax
    mov %eax, alu
    mov %eax, counter
    call itoa

    mov $4, %eax
    mov $1, %ebx
    mov $itoa_string, %ecx
    mov $11, %edx
	int $0x80

    call print_b
    jmp main

do_sar:
	call check
	mov alu, %eax
    mov counter, %ecx
    andl $31, %ecx
    sarl %cl, %eax
    mov %eax, alu
    mov %eax, counter
    call itoa

    mov $4, %eax
    mov $1, %ebx
    mov $itoa_string, %ecx
    mov $11, %edx
	int $0x80

    call print_b
    jmp main

do_and:
	call check
	mov alu, %eax
    andl counter, %eax
    mov %eax, alu
    mov %eax, counter
    call itoa

    mov $4, %eax
    mov $1, %ebx
    mov $itoa_string, %ecx
    mov $11, %edx
	int $0x80

    call print_b
    jmp main

do_or:
	call check
	mov alu, %eax
    orl counter, %eax
    mov %eax, alu
    mov %eax, counter
    call itoa

    mov $4, %eax
    mov $1, %ebx
    mov $itoa_string, %ecx
    mov $11, %edx
	int $0x80

    call print_b
    jmp main

do_xor:
	call check
    mov alu, %eax
    xorl counter, %eax
    mov %eax, alu
    mov %eax, counter
    call itoa

    mov $4, %eax
    mov $1, %ebx
    mov $itoa_string, %ecx
    mov $11, %edx
	int $0x80

    call print_b
    jmp main

do_not:
    mov alu, %eax
    notl %eax
    mov %eax, alu
    mov %eax, counter
    call itoa

    mov $4, %eax
    mov $1, %ebx
    mov $itoa_string, %ecx
    mov $11, %edx
	int $0x80

    call print_b
    jmp main

	mov $1, %eax
	mov $0, %ebx
	int $0x80
atoi:
	mov ilen, %esi
	dec %esi
	mov $1, %ebx
	movl $0, counter

atoi_loop:
	mov $0, %eax
	movb instruction(%esi), %al
	subb $'0', %al
	imull %ebx
	add %eax, counter
	imull $10, %ebx, %ebx
	dec %esi
	cmpl $4, %esi
	jge atoi_loop
	ret

itoa:
	mov counter, %eax
	movl $0x20202020, itoa_string
	movl $0x20202020, itoa_string + 4
	movw $0x2020, itoa_string + 8
	lea itoa_string + 9, %edi

	mov $0, %ecx
	cmp $0, %eax
	jge itoa_loop
	neg %eax
	mov $1, %ecx

itoa_loop:
	mov $0, %edx
	idivl ten
	addl $'0', %edx
	movb %dl, (%edi)
	dec %edi
	cmpl $0, %eax
	jg itoa_loop
	cmp $0, %ecx
	je done
	movb $'-', (%edi)

done:
	ret

print_b:
	push $itob_string
	push alu
	call itob
	addl $8, %esp

	mov $4, %eax
	mov $1, %ebx
	mov $itob_string, %ecx
	mov $35, %edx
	int $0x80
	ret

check:
	movb instruction + 4, %al
	cmpb $'0', %al
	jne decimal
	movb instruction + 5, %al
	cmpb $'b', %al
	jne decimal

	lea instruction + 4, %eax
	push %eax
	call btoi
	addl $4, %esp

	mov %eax, counter
	mov %eax, alu
	jmp done

decimal:
	call atoi
	jmp done

