;;=============================================================
;; CS 2110 - Fall 2021
;; Homework 6 - Factorial
;;=============================================================
;; Name:
;;============================================================

;; In this file, you must implement the 'factorial' and "mult" subroutines.

;; Little reminder from your friendly neighborhood 2110 TA staff: don't run
;; this directly by pressing 'RUN' in complx, since there is nothing put at
;; address x3000. Instead, load it and use 'Debug' -> 'Simulate
;; Subroutine Call' and choose the 'factorial' or 'mult' label.

;; Pseudocode

;; Factorial



;; Multiply

;;    mult(int a, int b) {
;;        int ret = 0;
;;        int copyB = b;
;;        while (copyB > 0):
;;            ret += a;
;;            copyB--;
;;        return ret;
;;    }


.orig x3000
    ;; you do not need to write anything here
HALT

factorial   ;; please do not change the name of your subroutine
;;    factorial(int n) {
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
;;        int ret = 1;
        AND     R0, R0, #0              ;; R0 <- 0
        ADD     R0, R0, #1              ;; R0 <- 1
        STR     R0, R5, #0              ;; mem[fp] (answer) = 1

        AND     R1, R1, #0              ;; R1 <- 0
        ADD     R1, R1, #2              ;; R1 <- 2


;;        for (int x = 2; x < n+1; x++) {
    F1  ADD     R2, R2, #0              ;; R2 <- 0
        LDR     R2, R5, #4              ;; R2 <- n
        ADD     R2, R2, #1              ;; R2 <- n + 1
        NOT     R2, R2                  ;; R2 <- ~(n + 1)
        ADD     R2, R2, #1              ;; R2 <- -(n + 1)
        ADD     R2, R2, R1              ;; R2 <- -(n + 1) + x
        BRzp    ENDF1

;;            ret = mult(ret, x);
        LDR     R0, R5, #0              ;; R0 <- answer
        ADD     R6, R6, #-1             ;; STACK <- STACK - 1
        STR     R1, R6, #0              ;; mem[STACK] <- x
        ADD     R6, R6, #-1             ;; STACK <- STACK - 1
        STR     R0, R6, #0              ;; mem[STACK] <- ret
        JSR     mult                    ;; mult
        LDR     R0, R6, 0               ;; R0 <- return
        STR     R0, R5, #0              ;; mem[answer] = ret * x
        ADD     R6, R6, #1              ;; STACK <- STACK + 3

        ADD     R1, R1, #1              ;; R1 <- x++
        BR      F1
;;        }
ENDF1   LDR     R0, R5, #0
;;        return ret;
;;    }
        STR     R0, R5, #3              ;; mem[fp + 3] (return) <- R0
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

mult        ;; please do not change the name of your subroutine
;;    mult(int a, int b) {
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
;;        int ret = 0;
        AND     R0, R0, #0              ;; R0 <- 0
        STR     R0, R5, #0              ;; mem[answer] <- 0

;;        int copyB = b;
        LDR     R1, R5, #5              ;; R1 <- b

;;        while (copyB > 0):
    W1  ADD     R1, R1, #0              ;; nzp <- R1
        BRnz    ENDW1

;;            ret += a;
        LDR     R0, R5, #0              ;; R0 <- answer
        LDR     R2, R5, #4              ;; R2 <- a
        ADD     R0, R0, R2              ;; R0 <- answer + a
        STR     R0, R5, #0              ;; answer <- ret + a

;;            copyB--;
        ADD     R1, R1, #-1             ;; R1 <- b--
        BR      W1
ENDW1   LDR     R0, R5, #0              ;; R0 <- mem[fp]

;;        return ret;
        STR     R0, R5, #3              ;; mem[fp + 3] (return) <- R0
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

STACK .fill xF000
.end
