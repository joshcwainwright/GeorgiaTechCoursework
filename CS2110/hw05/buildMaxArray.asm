;;=============================================================
;; CS 2110 - Fall 2022
;; Homework 5 - buildMaxArray
;;=============================================================
;; Name: Joshua Wainwright
;;=============================================================


;; R0 = A, R1 = B, R2 = C, R3 = i, R4 = -i, R5 = scratch, R6 = scratch R7 = scratch
.orig x3000
		AND 	R0, R0, #0		;; R0 <- 0
		AND 	R1, R1, #0		;; R1 <- 0
		AND 	R2, R2, #0		;; R2 <- 0
		AND 	R3, R3, #0		;; R3 <- 0
		AND 	R4, R4, #0		;; R4 <- 0
		AND 	R5, R5, #0		;; R5 <- 0
		AND 	R6, R6, #0		;; R6 <- 0
		AND 	R7, R7, #0		;; R7 <- 0

;;	int A[] = {1,2,3};
		LD	 	R0, A			;; R0 <- A (address)

;;	int B[] = {-1, 7, 8};
		LD	 	R1, B			;; R1 <- B (address)

;;	int C[3];
		LD		R2, C			;; R2 <- C (address)

;;	int i = 0;
		AND 	R3, R3, #0		;; R3 <- i (i = 0)

;;	while (i < A.length) {
	W1	NOT 	R4, R3			;; R4 <- ~i
		ADD 	R4, R4, #1		;; R4 <- ~i + 1 (R4 <- -i)
		AND 	R5, R5, #0		;; R5 <- 0
		LD		R5, LEN 		;; R5 <- LEN
		ADD 	R5, R5, R4		;; R5 <- LEN - i (nzp set)
		BRnz 	ENDW1			;; while(i < A.length)

;;		if (A[i] < B[i])
		AND 	R5, R5, #0		;; R5 <- 0
		ADD		R5, R0, R3		;; R5 <- A + i
		LDR  	R5, R5, #0		;; R5 <- mem[A + i]
		AND		R6, R6, #0		;; R6 <- 0
		ADD		R6, R1, R3		;; R6 <- B + i
		LDR		R6, R6, #0		;; R6 <- mem[B + i]
		BRn		C1				;; Branch neg if mem[B + i] < 0
		BRzp	C2				;; Branch pos if mem[B + i] >= 0

	C1	ADD 	R5, R5, #0		;; nzp <- mem[A + i] (Both Neg)
		BRzp	C3				;; Branch opp if mem [A + i] >= 0
		NOT		R5, R5			;; R5 <- ~mem[A + i]
		ADD		R5, R5, #1		;; R5 <- ~mem[A + i] + 1 (R5 <- -mem[A + i])
		ADD     R5, R5, R6		;; R5 <- -mem[A + i] + mem[B + i]
		BRzp	I1				;; Branch load B if B > A
		BRn		E1				;; Branch load A if A > B

	C2	ADD 	R5, R5, #0		;; nzp <- mem[A + i] (Both Pos)
		BRn 	C3				;; Branch opp if mem [A + i] < 0
		NOT		R5, R5			;; R5 <- ~mem[A + i]
		ADD		R5, R5, #1		;; R5 <- ~mem[A + i] + 1 (R5 <- -mem[A + i])
		ADD     R5, R5, R6		;; R5 <- -mem[A + i] + mem[B + i]
		BRn		E1				;; Branch load B if B > A
		BRzp	I1				;; Branch load A if A > B

	C3	BRn     I1				;; Branch load B if A < 0
		BRzp	E1				;; Branch load A if A >= 0

;;			C[i] = B[i];
	I1	AND 	R6, R6, #0		;; R6 <- 0
		ADD		R6, R1, R3		;; R6 <- B + i
		LDR     R6, R6, #0		;; R6 <- mem[B + i]
		AND		R7, R7, #0		;; R7 <- 0
		ADD		R7, R2, R3		;; R7 <- C + i
		STR     R6, R7, #0		;; mem[C + i] <- mem[B + i]
		BR		S1				;; Branch i++

;;		else
;;			C[i] = A[i];
	E1  AND 	R5, R5, #0		;; R5 <- 0
		ADD		R5, R0, R3		;; R5 <- A + i
		LDR     R5, R5, #0		;; R5 <- mem[A + i]
		AND		R7, R7, #0		;; R7 <- 0
		ADD		R7, R2, R3		;; R7 <- C + i
		STR     R5, R7, #0		;; mem[C + i] <- mem[A + i]
		BR		S1				;; Branch i++

;;		i += 1;
	S1	ADD R3, R3, #1			;; i <- i + 1

	BR	W1						;; restart while
;;	}
		ENDW1
	HALT


A 	.fill x3200
B 	.fill x3300
C 	.fill x3400
LEN .fill 4

.end

.orig x3200
	.fill -1
	.fill 2
	.fill 7
	.fill -3
.end

.orig x3300
	.fill 3
	.fill 6
	.fill 0
	.fill 5
.end

.orig x3400
	.blkw 4
.end
