!============================================================
! CS 2200 Homework 2 Part 2: Tower of Hanoi
!
! Apart from initializing the stack,
! please do not edit mains functionality.
!============================================================

! int minimumHanoi(int n) { 
!     if (n == 1)
!         return 1; 
!     else
!         return (2 * minimumHanoi(n - 1)) + 1; 
! }

main:
    add     $zero, $zero, $zero     ! TODO: Here, you need to get the address of the stack
    lea     $sp, stack              ! using the provided label to initialize the stack pointer.
                                    ! load the label address into $sp and in the next instruction,
    lw      $sp, 0($sp)             ! use $sp as base register to load the value (0xFFFF) into $sp.


    lea     $at, hanoi              ! loads address of hanoi label into $at

    lea     $a0, testNumDisks1      ! loads address of number into $a0
    lw      $a0, 0($a0)             ! loads value of number into $a0

    sw      $ra, 0($sp)             ! ra -> mem[sp]

    jalr    $ra, $at                ! jump to hanoi, set $ra to return addr
    halt                            ! when we return, just halt

hanoi:
    add     $zero, $zero, $zero     ! TODO: perform post-call portion of
                                    ! the calling convention. Make sure to
                                    ! save any registers you will be using!

    addi    $sp, $sp, -1            ! decrements stack
    sw      $fp, 0($sp)             ! fp -> mem[sp]
    add     $fp, $zero, $sp         ! sp -> fp




    add     $zero, $zero, $zero     ! TODO: Implement the following pseudocode in assembly:
                                    ! IF ($a0 == 1)
                                    !    GOTO base
                                    ! ELSE
                                    !    GOTO else
    
    addi    $t0, $zero, 1           ! 1 -> t0
    skpeq   $a0, $t0                ! if($a0 == 1) {pc + 1}
    br      else                    ! br else if ($a0 != 1)
    br      base                    ! br base if ($a0 == 1)



else:
    add     $zero, $zero, $zero     ! TODO: perform recursion after decrementing
                                    ! the parameter by 1. Remember, $a0 holds the
                                    ! parameter value.
    
    addi    $a0, $a0, -1            ! a0 - 1 -> a0
    addi    $sp, $sp, -1            ! decrements stack
    sw      $ra, 0($sp)             ! prev ra -> mem[sp]

    lea     $at, hanoi              ! hanoi -> at
    jalr    $ra, $at                ! jump to hanoi

    lw      $ra, 0($sp)             ! mem[sp] -> ra
    addi    $sp, $sp, 1             ! increments stack

    add     $zero, $zero, $zero     ! TODO: Implement the following pseudocode in assembly:
                                    ! $v0 = 2 * $v0 + 1
                                    ! RETURN $v0

    add     $v0, $v0, $v0           ! v0 + v0 -> v0
    addi    $v0, $v0, 1             ! v0 + 1 -> v0

    br      teardown

base:
    add     $zero, $zero, $zero     ! TODO: Return 1

    addi    $t0, $zero, 1           ! 1 -> t0
    add     $v0, $t0, $zero         ! t0 -> v0

    br      teardown

teardown:
    add     $zero, $zero, $zero     ! TODO: perform pre-return portion
                                    ! of the calling convention

    lw      $fp, 0($sp)             ! mem[sp] = fp
    addi    $sp, $sp, 1             ! increments stack

    jalr    $zero, $ra              ! return to caller



stack: .word 0xFFFF                 ! the stack begins here


! Words for testing \/

! 1
testNumDisks1:
    .word 0x0001

! 10
testNumDisks2:
    .word 0x000a

! 20
testNumDisks3:
    .word 0x0014
