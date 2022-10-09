! This program executes pow as a test program using the LC 2200 calling convention
! Check your registers ($v0) and memory to see if it is consistent with this program

        ! vector table
vector0:
        .fill 0x00000000                        ! device ID 0
        .fill 0x00000000                        ! device ID 1
        .fill 0x00000000                        ! ...
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000                        ! device ID 7
        ! end vector table

main:	lea $sp, initsp                         ! initialize the stack pointer
        lw $sp, 0($sp)                          ! finish initialization

        lea $t0, timer_handler                  ! Done: Install timer interrupt handler into vector table
        lea $t1, vector0
        sw  $t0, 0($t1)

        lea $t0, distance_tracker_handler       ! Done: Install distance tracker interrupt handler into vector table
        sw  $t0, 1($t1)

        lea $t0, minval
        lw  $t0, 0($t0)
        addi $t1, $zero, 65535                  ! store 0000ffff into minval (to make comparisons easier)
        sw  $t1, 0($t0)

        ei                                      ! Enable interrupts

        lea $a0, BASE                           ! load base for pow
        lw $a0, 0($a0)
        lea $a1, EXP                            ! load power for pow
        lw $a1, 0($a1)
        lea $at, POW                            ! load address of pow
        jalr $ra, $at                           ! run pow
        lea $a0, ANS                            ! load base for pow
        sw $v0, 0($a0)

        addi $v0, $zero, 1
        halt                                    ! stop the program here
        addi $v0, $zero, -1                     ! load a bad value on failure to halt

BASE:   .fill 17
EXP:    .fill 8
ANS:	.fill 0                                 ! should come out to 256 (BASE^EXP)

POW:    addi $sp, $sp, -1                       ! allocate space for old frame pointer
        sw $fp, 0($sp)

        addi $fp, $sp, 0                        ! set new frame pointer

        skpgt $a1, $zero                        ! check if $a1 is zero
        br RET1                                 ! if the exponent is 0, return 1
        skpgt $a0, $zero                        ! if the base is 0, return 0
        br RET0                                 

        addi $a1, $a1, -1                       ! decrement the power

        lea $at, POW                            ! load the address of POW
        addi $sp, $sp, -2                       ! push 2 slots onto the stack
        sw $ra, -1($fp)                         ! save RA to stack
        sw $a0, -2($fp)                         ! save arg 0 to stack
        jalr $ra, $at                           ! recursively call POW
        add $a1, $v0, $zero                     ! store return value in arg 1
        lw $a0, -2($fp)                         ! load the base into arg 0
        lea $at, MULT                           ! load the address of MULT
        jalr $ra, $at                           ! multiply arg 0 (base) and arg 1 (running product)
        lw $ra, -1($fp)                         ! load RA from the stack
        addi $sp, $sp, 2

        br FIN                                  ! unconditional branch to FIN

RET1:   add $v0, $zero, $zero                   ! return a value of 0
	addi $v0, $v0, 1                        ! increment and return 1
        skpgt $v0, $zero                        ! unconditional branch to FIN

RET0:   add $v0, $zero, $zero                   ! return a value of 0

FIN:	lw $fp, 0($fp)                          ! restore old frame pointer
        addi $sp, $sp, 1                        ! pop off the stack
        jalr $zero, $ra

MULT:   add $v0, $zero, $zero                   ! allocate space for old frame pointer
        addi $t0, $zero, 0                      ! sentinel = 0
        addi $s0, $a0, 0
        addi $s1, $a1, 0
        
MULT_WHILE:  
        skpgt $s1, $zero                        ! check if a0 is zero and return
        jalr $zero, $ra

        addi $t0, $zero, 1                        
        nand $t0, $t0, $s1
        nand $t0, $t0, $t0                      ! calculate (a1 & 0x01)

MULT_IF: 
        skpeq $t0, $zero                        ! skip if (a1 % 2 != 1)
        add $v0, $v0, $s0                       ! ans += n   
        
        addi $t0, $zero, 1    
        sll $s0, $s0, $t0                       ! n = n << 1                    
        srl $s1, $s1, $t0                       ! m /= 2
        br MULT_WHILE

timer_handler:
        
!store $k0                                      ! Done
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $k0, 0($sp)                                ! mem[$sp] <- k0
ei                                              ! enable interrupts

!store $at
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $at, 0($sp)                                ! mem[$sp] <- $at

!store $v0
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $v0, 0($sp)                                ! mem[$sp] <- $v0

!store $a0 - $a2
addi $sp, $sp, -3                               ! $sp <- $sp - 3 (decrement stack)
sw   $a0, 2($sp)                                ! mem[$sp + 2] <- $a0
sw   $a1, 1($sp)                                ! mem[$sp + 1] <- $a1
sw   $a2, 0($sp)                                ! mem[$sp + 0] <- $a2

!store $t0 - $t2
addi $sp, $sp, -3                               ! $sp <- $sp - 3 (decrement stack)
sw   $t0, 2($sp)                                ! mem[$sp + 2] <- $t0
sw   $t1, 1($sp)                                ! mem[$sp + 1] <- $t1
sw   $t2, 0($sp)                                ! mem[$sp + 0] <- $t2

!store $s0 - $s2
addi $sp, $sp, -3                               ! $sp <- $sp - 3 (decrement stack)
sw   $s0, 2($sp)                                ! mem[$sp + 2] <- $s0
sw   $s1, 1($sp)                                ! mem[$sp + 1] <- $s1
sw   $s2, 0($sp)                                ! mem[$sp + 0] <- $s2

!store $fp
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $fp, 0($sp)                                ! mem[$sp] <- $fp

!store $ra
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $ra, 0($sp)                                ! mem[$sp] <- $ra

!incrament ticks                                    
lea  $t0, ticks                                 ! $t0 <- ticks
lw   $t0, 0($t0)                                ! $t0 <- mem[ticks]
lw   $t1, 0($t0)                                ! $t1 <- mem[mem[ticks]]
addi $t1, $t1, 1                                ! $t1 <- $t1 + 1
sw   $t1, 0($t0)                                ! mem[mem[ticks]] <- $t1

!restore $ra
lw   $ra, 0($sp)                                ! $ra <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)

!restore $fp
lw   $fp, 0($sp)                                ! $fp <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)

!restore $s0 - $s2
lw   $s2, 0($sp)                                ! $s2 <- mem[$sp]
lw   $s1, 1($sp)                                ! $s1 <- mem[$sp + 1]
lw   $s0, 2($sp)                                ! $s0 <- mem[$sp + 2]
addi $sp, $sp, 3                                ! $sp <- $sp + 3 (incrament stack)

!restore $t0 - $t2
lw   $t2, 0($sp)                                ! $t2 <- mem[$sp]
lw   $t1, 1($sp)                                ! $t1 <- mem[$sp + 1]
lw   $t0, 2($sp)                                ! $t0 <- mem[$sp + 2]
addi $sp, $sp, 3                                ! $sp <- $sp + 3 (incrament stack)

!restore $a0 - $a2
lw   $a2, 0($sp)                                ! $a2 <- mem[$sp]
lw   $a1, 1($sp)                                ! $a1 <- mem[$sp + 1]
lw   $a0, 2($sp)                                ! $a0 <- mem[$sp + 2]
addi $sp, $sp, 3                                ! $sp <- $sp + 3 (incrament stack)

!restore $v0
lw   $v0, 0($sp)                                ! $v0 <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)

!restore $at
lw   $at, 0($sp)                                ! $at <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)

!restore $k0                                    
di
lw   $k0, 0($sp)                                ! $k0 <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)
reti                                            ! return from interrupt

distance_tracker_handler:

                                                ! Done
!store $k0                                      
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $k0, 0($sp)                                ! mem[$sp] <- k0
ei                                              ! enable interrupts

!store $at
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $at, 0($sp)                                ! mem[$sp] <- $at

!store $v0
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $v0, 0($sp)                                ! mem[$sp] <- $v0

!store $a0 - $a2
addi $sp, $sp, -3                               ! $sp <- $sp - 3 (decrement stack)
sw   $a0, 2($sp)                                ! mem[$sp + 2] <- $a0
sw   $a1, 1($sp)                                ! mem[$sp + 1] <- $a1
sw   $a2, 0($sp)                                ! mem[$sp + 0] <- $a2

!store $t0 - $t2
addi $sp, $sp, -3                               ! $sp <- $sp - 3 (decrement stack)
sw   $t0, 2($sp)                                ! mem[$sp + 2] <- $t0
sw   $t1, 1($sp)                                ! mem[$sp + 1] <- $t1
sw   $t2, 0($sp)                                ! mem[$sp + 0] <- $t2

!store $s0 - $s2
addi $sp, $sp, -3                               ! $sp <- $sp - 3 (decrement stack)
sw   $s0, 2($sp)                                ! mem[$sp + 2] <- $s0
sw   $s1, 1($sp)                                ! mem[$sp + 1] <- $s1
sw   $s2, 0($sp)                                ! mem[$sp + 0] <- $s2

!store $fp
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $fp, 0($sp)                                ! mem[$sp] <- $fp

!store $ra
addi $sp, $sp, -1                               ! $sp <- $sp - 1 (decrement stack)
sw   $ra, 0($sp)                                ! mem[$sp] <- $ra

!handle distance tracker
lea $t0, maxval                                 ! $t0 <- maxval
lw  $t0, 0($t0)                                 ! $t0 <- mem[maxval]
lw  $t0, 0($t0)                                 ! $t0 <- mem[mem[maxval]]

lea $t1, minval                                 ! $t1 <- minval
lw  $t1, 0($t1)                                 ! $t1 <- mem[minval]
lw  $t1, 0($t1)                                 ! $t1 <- mem[mem[minval]]

in  $t2, 1                                      ! $t2 <- distance
skpgt $t2, $t0                                  ! distance > maxval = pc + 2
br  skip                                        ! branch skip
br  distance_maxval                             ! branch on distance < maxval

skip:
skpgt $t1, $t2                                  ! minval > distance = pc +2
br  end_distance_tracker                        ! branch end
br  distance_minval                             ! branch on distance < minval

distance_maxval:
lea  $t0, maxval                                ! $t0 <- maxval
lw   $t0, 0($t0)                                ! $t0 <- mem[maxval]
sw   $t2, 0($t0)                                ! mem[maxval] <- $t2
br   end_distance_tracker                       ! branch end

distance_minval:
lea  $t1, minval                                ! $t1 <- minval
lw   $t1, 0($t1)                                ! $t1 <- mem[minval]
sw   $t2, 0($t1)                                ! mem[minval] <- $t2
br   end_distance_tracker                       ! branch end

end_distance_tracker:

!lshift
lea  $t0, maxval                                ! $t0 <- maxval
lw   $t0, 0($t0)                                ! $t0 <- mem[maxval]
lw   $t0, 0($t0)                                ! $t0 <- mem[mem[maxval]]
addi $t1, $zero, 1                              ! $t1 <- 1
sll  $t0, $t0, $t1                              ! $t0 <- mem[mem[maxval]] << 1
lea  $t1, lshift                                ! $t1 <- lshift
lw   $t1, 0($t1)                                ! $t1 <- mem[lshift]
sw   $t0, 0($t1)                                ! mem[mem[lshift]] <- $t0

!rshift
lea  $t0, minval                                ! $t0 <- minval
lw   $t0, 0($t0)                                ! $t0 <- mem[minval]
lw   $t0, 0($t0)                                ! $t0 <- mem[mem[minval]]
addi $t1, $zero, 1                              ! $t1 <- 1
srl  $t0, $t0, $t1                              ! $t0 <- mem[mem[minval]] >> 1
lea  $t1, rshift                                ! $t1 <- rshift
lw   $t1, 0($t1)                                ! $t1 <- mem[rshift]
sw   $t0, 0($t1)                                ! mem[mem[rshift]] <- $t0

!restore $ra
lw   $ra, 0($sp)                                ! $ra <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)

!restore $fp
lw   $fp, 0($sp)                                ! $fp <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)

!restore $s0 - $s2
lw   $s2, 0($sp)                                ! $s2 <- mem[$sp]
lw   $s1, 1($sp)                                ! $s1 <- mem[$sp + 1]
lw   $s0, 2($sp)                                ! $s0 <- mem[$sp + 2]
addi $sp, $sp, 3                                ! $sp <- $sp + 3 (incrament stack)

!restore $t0 - $t2
lw   $t2, 0($sp)                                ! $t2 <- mem[$sp]
lw   $t1, 1($sp)                                ! $t1 <- mem[$sp + 1]
lw   $t0, 2($sp)                                ! $t0 <- mem[$sp + 2]
addi $sp, $sp, 3                                ! $sp <- $sp + 3 (incrament stack)

!restore $a0 - $a2
lw   $a2, 0($sp)                                ! $a2 <- mem[$sp]
lw   $a1, 1($sp)                                ! $a1 <- mem[$sp + 1]
lw   $a0, 2($sp)                                ! $a0 <- mem[$sp + 2]
addi $sp, $sp, 3                                ! $sp <- $sp + 3 (incrament stack)

!restore $v0
lw   $v0, 0($sp)                                ! $v0 <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)

!restore $at
lw   $at, 0($sp)                                ! $at <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)

!restore $k0                                    
di
lw   $k0, 0($sp)                                ! $k0 <- mem[$sp]
addi $sp, $sp, 1                                ! $sp <- sp + 1 (incrament stack)
reti                                            ! return from interrupt

initsp: .fill 0xA000
ticks:  .fill 0xFFFF
lshift: .fill 0xFFFE
rshift: .fill 0xFFFD
maxval: .fill 0xFFFC
minval: .fill 0xFFFB
