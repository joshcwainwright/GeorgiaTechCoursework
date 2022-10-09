;; TLDR Linked List Structure:
;;
;; For this Timed Lab, you are given a doubly-linked list and you need to check
;; if its data is a palindrome ignoring case.
;; Each node in the doubly-linked list is represented by three consecutive words in memory.
;; Each node has data, the address of the previous node, and the address of the next node.
;;
;; Given the address `node`, you can do
;; * `mem[node]` to get the data
;; * `mem[node + 1]` to get the address of the previous node
;; * `mem[node + 2]` to get the address of the next node

; Data for the one test
.orig x6000
        .fill 'a'
        .fill x0000
        .fill x4000
.end

.orig x4000
        .fill 'b'
        .fill x6000
        .fill x5000
.end

.orig x5000
        .fill 'A'
        .fill x4000
        .fill x0000
.end

.orig x3000

    ; One test is at the bottom of this file
    BR test_start


;; to_lowercase function
;; Parameter c: Character encoded in ASCII
;; Returns: Lowercase version of c if it's in the range 'A' to 'Z'; otherwise
;;          just c unchanged
;;
;; Examples:
;; * to_lowercase('a') == 'a'
;; * to_lowercase('A') == 'a'
;; * to_lowercase('5') == '5'

to_lowercase

.fill x1dba
.fill x7f84
.fill x7b83
.fill x1ba2
.fill x7180
.fill x7381
.fill x6344
.fill x2015
.fill x1040
.fill x080c
.fill x2013
.fill x1040
.fill x0209
.fill x2011
.fill x1040
.fill x7143
.fill x6180
.fill x6381
.fill x1d63
.fill x6f42
.fill x6b41
.fill xc1c0
.fill x7343
.fill x6180
.fill x6381
.fill x1d63
.fill x6f42
.fill x6b41
.fill xc1c0
.fill xffbf
.fill xffa6
.fill x0020


;; This function takes in two nodes from the doubly-linked list as input. It
;; outputs whether they contain equal data ignoring case. Note that case is
;; ignored. All the characters should be taken to lowercase before comparing
;; them.
;;
;; Parameter node1: The address of a node in the doubly-linked list
;; Parameter node2: The address of a node in the doubly-linked list
;; Returns: 1 if both nodes have the same data ignoring case, 0 otherwise
;;
;; Examples:
;; * are_letters_equal on node1 containing 'A' and node2 containing 'A' returns 1
;; * are_letters_equal on node1 containing 'A' and node2 containing 'a' returns 1
;; * are_letters_equal on node1 containing '5' and node2 containing '6' returns 0
;;
;; Pseudocode:
;; // (Checkpoint 1)


are_letters_equal

;; are_letters_equal(Node node1 (address), Node node2 (address)) {
        ADD     R6, R6, #-4             ;; R6 <- Stack - 4, allocate frame space
        STR     R7, R6, #2              ;; mem[stack + 2] <- ret addr, Save return address
        STR     R5, R6, #1              ;; mem[stack + 1] <- old fp, Save old frame pointer
        ADD     R5, R6, #0              ;; R5 <- stack, set new frame
        ADD     R6, R6, #-5             ;; R6 < Sack - 5, allocate reg space
        STR     R0, R5, #-1             ;; mem[fp - 1] <- R0
        STR     R1, R5, #-2             ;; mem[fp - 2] <- R1
        STR     R2, R5, #-3             ;; mem[fp - 3] <- R2
        STR     R3, R5, #-4             ;; mem[fp - 4] <- R3
        STR     R4, R5, #-5             ;; mem[fp - 5] <- R4
;;      char letter1 = mem[node1];
        LDR     R0, R5, #4              ;; R0 <- node1 (address)
        LDR     R0, R0, #0              ;; R0 <- mem[node1]
;;      char letter2 = mem[node2];
        LDR     R1, R5, #5              ;; R1 <- node2 (address)
        LDR     R1, R1, #0              ;; R1 <- mem[node2]
;;
;;      letter1 = to_lowercase(letter1);
        ADD     R6, R6, #-1             ;; STACK <- STACK - 1
        STR     R0, R6, #0              ;; mem[STACK] <- mem[node1]
        JSR     to_lowercase            ;;
        LDR     R0, R6, #0              ;; R0 <- to_lowercase(mem[node1])

;;      letter2 = to_lowercase(letter2);
        ADD     R6, R6, #-1             ;; STACK <- STACK - 1
        STR     R1, R6, #0              ;; mem[STACK] <- mem[node1]
        JSR     to_lowercase            ;;
        LDR     R1, R6, #0              ;; R1 <- to_lowercase(mem[node2])
;;
;;      if (letter1 == letter2) {
        NOT     R0, R0
        ADD     R0, R0, #1              ;; R0 <- -to_lowercase(mem[node1])
        ADD     R0, R0, R1              ;; R0 <- to_lowercase(mem[node2]) - to_lowercase(mem[node1])
        BRnp    E1
        AND     R0, R0, #0              ;; R0 <- 0
        ADD     R0, R0, #1              ;; R0 <- 1
        BR      RE1
;;          return 1;
;;      } else {
    E1  AND     R0, R0, #0              ;; R0 <- 0
        BR      RE1
;;          return 0;
;;      }
;; }
    RE1 STR     R0, R5, #3              ;; mem[fp + 3] (return) <- R0
        LDR     R0, R5, #-1             ;; R0 <- mem [fp - 1]
        LDR     R1, R5, #-2             ;; R1 <- mem [fp - 2]
        LDR     R2, R5, #-3             ;; R2 <- mem [fp - 3]
        LDR     R3, R5, #-4             ;; R3 <- mem [fp - 4]
        LDR     R4, R5, #-5             ;; R4 <- mem [fp - 5]
        ADD     R6, R5, #0              ;; R6 <- fp
        LDR     R5, R6, #1              ;; R5 <- mem[stack + 1] (old fp)
        LDR     R7, R6, #2              ;; R7 <- mem[stack + 2] (ret addr)
        ADD     R6, R6, #3              ;; R6 <- STACK + 3

    RET

;; Takes in both the head and tail addresses for a doubly-linked list, and
;; returns whether the data contained inside is a palindrome ignoring case. Note
;; that case is ignored. All the characters should be taken to lowercase before
;; comparing them.
;;
;;
;; Parameter head: The address of the first node in the doubly-linked list
;; Parameter tail: The address of the last node in the doubly-linked list
;; Returns: 1 if the list's data represents a palindrome ignoring case; 0
;;          otherwise
;;
;; Examples:
;; * is_palindrome on "aba" returns 1
;; * is_palindrome on "abA" returns 1
;; * is_palindrome on "abba" returns 1
;; * is_palindrome on "ab!" returns 0
;; * is_palindrome on "Happy Birthday!" returns 0
;;
;; Pseudocode:


is_palindrome

;; is_palindrome(Node head (address), Node tail (address)) {
;;      // (Checkpoint 2 - Base Case)
        ADD     R6, R6, #-4             ;; R6 <- Stack - 4, allocate frame space
        STR     R7, R6, #2              ;; mem[stack + 2] <- ret addr, Save return address
        STR     R5, R6, #1              ;; mem[stack + 1] <- old fp, Save old frame pointer
        ADD     R5, R6, #0              ;; R5 <- stack, set new frame
        ADD     R6, R6, #-5             ;; R6 < Sack - 5, allocate reg space
        STR     R0, R5, #-1             ;; mem[fp - 1] <- R0
        STR     R1, R5, #-2             ;; mem[fp - 2] <- R1
        STR     R2, R5, #-3             ;; mem[fp - 3] <- R2
        STR     R3, R5, #-4             ;; mem[fp - 4] <- R3
        STR     R4, R5, #-5             ;; mem[fp - 5] <- R4
;;      if (head == 0 || tail == 0) {
;;      [if head == 0]
        LDR     R0, R5, #4              ;; R0 <- head (address)
        BRnp    S2
        BRz     O1

;;      [if tail == 0]
    S2  LDR     R1, R5, #5              ;; R1 <- tail (address)
        BRnp    I1
        BRz     O1
;;          return 1;
    O1  AND     R0, R0, #0              ;; R0 <- 0
        ADD     R0, R0, #1              ;; R0 <- 1
        BR      RE2

;;      }
;;
;;      if (are_letters_equal(head, tail) == 0) {
    I1  LDR     R0, R5, #4              ;; R0 <- head (address)
        LDR     R1, R5, #5              ;; R1 <- tail (address)
        ADD     R6, R6, #-1             ;; STACK <- STACK -1
        STR     R1, R6, #0              ;; mem[STACK] = tail (address)
        ADD     R6, R6, #-1             ;; STACK <- STACK - 1
        STR     R0, R6, #0              ;; mem[STACK] = head (address)
        JSR     are_letters_equal
        LDR     R0, R6, #0              ;; R0 <- are_letters_equal(head, tail)
        ADD     R6, R6, #1              ;; STACK <- STACK + 1
        ADD     R0, R0, #0              ;; nzp <- are_letters_equal(head,tail)
        BRp     F1
        AND     R0, R0, #0              ;; R0 <- 0
        BR      RE2
;;          return 0;
;;      }
;;
;;      // (Checkpoint 3 - Recursion)
;; F1  Node new_head = mem[head + 2];
    F1  LDR     R0, R5, #4              ;; R0 <- head (address)
        ADD     R0, R0, #2              ;; R0 <- head + 2
        LDR     R0, R0, #0

;;      Node new_tail = mem[tail + 1];
        LDR     R1, R5, #5              ;; R1 <- tail (address)
        ADD     R1, R1, #1              ;; R1 <- tail + 1
        LDR     R1, R1, #0
        ADD     R6, R6, #-1             ;; STACK <- STACK - 1
        STR     R1, R6, #0              ;; mem[STACK] <- tail + 1
        ADD     R6, R6, #-1             ;; STACK <- STACK - 1
        STR     R0, R6, #0              ;; mem[STACK] <- head + 2
        JSR     is_palindrome
        LDR     R0, R6, #0              ;; R0 <- is_palindrome(new_head, new_tail)
        ADD     R6, R6, #1              ;; STACK <- STACK + 1
        BR      RE2
;;
;;      return is_palindrome(new_head, new_tail);
;; }
    RE2 STR     R0, R5, #3              ;; mem[fp + 3] (return) <- R0
        LDR     R0, R5, #-1             ;; R0 <- mem [fp - 1]
        LDR     R1, R5, #-2             ;; R1 <- mem [fp - 2]
        LDR     R2, R5, #-3             ;; R2 <- mem [fp - 3]
        LDR     R3, R5, #-4             ;; R3 <- mem [fp - 4]
        LDR     R4, R5, #-5             ;; R4 <- mem [fp - 5]
        ADD     R6, R5, #0              ;; R6 <- fp
        LDR     R5, R6, #1              ;; R5 <- mem[stack + 1] (old fp)
        LDR     R7, R6, #2              ;; R7 <- mem[stack + 2] (ret addr)
        ADD     R6, R6, #3              ;; R6 <- STACK + 3
        RET


; Helper code that runs one test
test_start

    ; Set up the stack
    LD R6, initial_r6

    ; Place arguments on the stack
    LD R0, head_addr
    LD R1, tail_addr
    ADD R6, R6, -2
    STR R0, R6, 0
    STR R1, R6, 1

    ; Set all the other registers to notable values
    ; Done to ease debugging
    LD R0, initial_r0
    LD R1, initial_r1
    LD R2, initial_r2
    LD R3, initial_r3
    LD R4, initial_r4
    LD R5, initial_r5
    LD R7, initial_r7

    ; Call the function
    ; Pop off return value and arguments
    JSR is_palindrome
    ADD R6, R6, 3

    HALT

; Easy to recognize initial values for registers
; Chosen to ease debugging
initial_r0 .fill xDEAD
initial_r1 .fill xBEEF
initial_r2 .fill x2110
initial_r3 .fill x2200
initial_r4 .fill x4290
initial_r5 .fill xCAFE
initial_r6 .fill xF000
initial_r7 .fill x8000

; Arguments to pass to the subroutine
head_addr .fill x6000
tail_addr .fill x5000

.end
.orig x7000
.fill 17152
.fill 0
.end
