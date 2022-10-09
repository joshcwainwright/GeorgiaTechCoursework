;;=============================================================
;; CS 2110 - Fall 2022
;; Homework 5 - fourCharacterStrings
;;=============================================================
;; Name: Joshua Wainwright
;;=============================================================


;; Pseudocode (see PDF for explanation)
;;
;; int count = 0;
;; int chars = 0;
;; int i = 0;
;;
;;  while(str[i] != '\0') {
;;      if (str[i] != ' ')
;;          chars++;
;;
;;      else {
;;          if (chars == 4)
;;              count++;
;;          chars = 0;
;;      }
;;      i++;
;;  }
;; ***IMPORTANT***
;; - Assume that all strings provided will end with a space (' ').
;; - Special characters do not have to be treated differently. For instance, strings like "it's" and "But," are considered 4 character strings.
;;

;; R0 = SPACE; R1 = STRING; R2 = count; R3 = chars; R4 = i; R5, R6, R7 = scratch
.orig x3000
;; clear all registers
        AND 	R0, R0, #0              ;; R0 <- 0
        AND 	R1, R1, #0		        ;; R1 <- 0

;; init R2
        AND 	R2, R2, #0		        ;; R2 <- 0

;; init R3
        AND 	R3, R3, #0		        ;; R3 <- 0

;; init R4
        AND 	R4, R4, #0		        ;; R4 <- 0

        AND 	R5, R5, #0		        ;; R5 <- 0
        AND 	R6, R6, #0		        ;; R6 <- 0
        AND 	R7, R7, #0	            ;; R7 <- 0
;; init R0
		LD 		R0, SPACE				;; R0 <- mem[SPACE]

;; init R1
		LD		R1, STRING				;; R1 <- mem[STRING]

;; while (mem[i + STRING] != 0)
	W1	AND 	R5, R5, 0				;; R5 <- 0
		ADD 	R5, R4, R1				;; R5 <- i + STRING
		LDR 	R5, R5, #0				;; R5, nzp <- mem[i + STRING]
		BRz		EW1						;; Branch EW1 if mem[i + STRING] = 0

;; if (str[i] != ' ')
		ADD 	R5, R5, R0				;; R5 <- mem[i + STRING] - 32
		BRz		EL1						;; branch else

;; chars++
		ADD		R3, R3, #1				;; R3 <- chars++
		BR		END

;; else
	EL1	NOP

;; if (chars == 4)
		AND 	R6, R6, #0				;; R6 <- 0
		ADD		R6, R3, #-4				;; R6 <- chars - 4
		BRnp	S1						;; Branch S1 if chars != 4

;; count++
		ADD 	R2, R2, #1				;; R2 <- count++

;; chars = 0
	S1	AND 	R3, R3, #0				;; R3 <- 0

;; i++
	END ADD 	R4, R4, #1				;; R4 <- i++
		BR 		W1						;; restart while

;; end while loop
	EW1	LEA		R1, STRING				;; R1 <- STRING
		STR		R2, R1, #1				;; mem[STRING + 1] <- count
		HALT


SPACE 	.fill #-32
STRING	.fill x4000
ANSWER .blkw 1

.end


.orig x4000

.stringz "I love CS 2110 and assembly is very fun! "

.end
