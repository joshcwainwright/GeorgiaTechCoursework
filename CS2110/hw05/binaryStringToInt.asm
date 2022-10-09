;;=============================================================
;; CS 2110 - Fall 2022
;; Homework 5 - binaryStringToInt
;;=============================================================
;; Name: Joshua Wainwright
;;=============================================================


;;    int result = x4000; (given memory address to save the converted value)
;;    String binaryString= "01000000"; (given binary string)
;;    int length = 8; (given length of the above binary string)
;;    int base = 1;
;;    int value = 0;
;;    while (length > 0) {
;;        int y = binaryString.charAt(length - 1) - 48;
;;        if (y == 1) {
;;            value += base;
;;        }
;;            base += base;
;;            length--;
;;    }
;;    mem[result] = value;

;; R0 = binary address, R1 = length value, R2 = value, R3 = 0, R4 = base, R5 = y; R6, R7, R8 = scratch
.orig x3000
;; clear all registers
        AND 	R0, R0, #0              ;; R0 <- 0
        AND 	R1, R1, #0		        ;; R1 <- 0
        AND 	R2, R2, #0		        ;; R2 <- 0
        AND 	R3, R3, #0		        ;; R3 <- 0
        AND 	R4, R4, #0		        ;; R4 <- 0
        AND 	R5, R5, #0		        ;; R5 <- 0
        AND 	R6, R6, #0		        ;; R6 <- 0
        AND 	R7, R7, #0	            ;; R7 <- 0

;; set R0
        LD      R0, binaryString        ;; R0 <- mem[binaryString]

;; set R1
        LD      R1, length              ;; R1 <- mem[length]

;; set R4
        ADD     R4, R4, #1              ;; R4 <- 1

;; begin while
    W1  ADD     R1, R1, #0              ;; nzp <- length value

;; branch fail
        BRnz E

;; while body
        AND     R6, R6, #0              ;; R6 <- 0
        ADD     R6, R1, #-1             ;; R6 <- length - 1
        AND     R7, R7, #0              ;; R7 <- 0
        ADD     R7, R6, R0              ;; R7 <- binary address + length - 1
        AND     R3, R3, #0              ;; R3 <- 0
        LDR     R3, R7, #0              ;; R3, nzp <- mem[binary address + length - 1]
        ADD     R3, R3, #-12            ;; R3 <- R3 - 12
        ADD     R3, R3, #-12            ;; R3 <- R3 - 12
        ADD     R3, R3, #-12            ;; R3 <- R3 - 12
        ADD     R3, R3, #-12            ;; R3 <- R3 - 12
        BRz     Z                       ;; branch zero if zero
        ADD     R2, R2, R4              ;; value <- value + base
    Z   ADD     R4, R4, R4              ;; base <- base + base
        ADD     R1, R1, #-1             ;; length <- length - 1
        BR      W1
;; end while
    E   LD      R7, result              ;; R8 <- result address
        STR     R2, R7, #0              ;; mem[result address] <- R2

    HALT

    binaryString .fill x5000
    length .fill 8
    result .fill x4000
.end

.orig x5000
    .stringz "010010100"
.end
