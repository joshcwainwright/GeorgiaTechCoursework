;;=============================================================
;; CS 2110 - Fall 2022
;; Homework 5 - summation
;;=============================================================
;; Name: Joshua Wainwright
;;=============================================================

;; Pseudocode (see PDF for explanation)
;;
;;    int result; (to save the summation of x)
;;    int x= -9; (given integer)
;;    int answer = 0;
;;    while (x > 0) {
;;        answer += x;
;;        x--;
;;    }
;;    result = answer;
;;
;; R0 = mem[x]
;; R1 = answer
;; R2 = x + 1

.orig x3000

    AND     R0, R0, #0          ;; R0 <- 0
    LD      R0, x               ;; R0 <- mem[x]

    AND     R1, R1, #0          ;; R1 <- 0

    AND     R2, R2, #0          ;; R2 <- 0
    LEA     R2, x               ;; R2 <- x

    W1      ADD     R0, R0, #0  ;; nzp <- x
            BRnz    ENDW1
            ADD     R1, R1, R0  ;; R2 <- R2 + x
            ADD     R0, R0, #-1 ;; R0 <- x + (-1)
            BR      W1
    ENDW1   STR     R1, R2, #1  ;; mem[x + 1] <- R1

    HALT

    x .fill 4
    result .blkw 1
.end
