;;=============================================================
;; CS 2110 - Fall 2021
;; Homework 6 - Binary Search
;;=============================================================
;; Name:
;;============================================================

;; In this file, you must implement the 'binarySearch' subroutine.

;; Little reminder from your friendly neighborhood 2110 TA staff: don't run
;; this directly by pressing 'RUN' in complx, since there is nothing put at
;; address x3000. Instead, load it and use 'Debug' -> 'Simulate
;; Subroutine Call' and choose the 'binarySearch' label.


;; Pseudocode:

;; Nodes are blocks of size 3 in memory:

;; The data is located in the 1st memory location
;; The node's left child address is located in the 2nd memory location
;; The node's right child address is located in the 3rd memory location

;; Binary Search


.orig x3000
    ;; you do not need to write anything here
HALT

binary_search   ;; please do not change the name of your subroutine
;;    binarySearch(Node root (addr), int data) {
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

;;    if (root == 0) {
        LDR     R0, R5, #4
        BRnp    S1
;;        return 0;
        BR      RE
;;    }

;;        if (data == root.data) {
    S1  LDR     R0, R5, #4              ;; R0 <- root Node (x4004)
        LDR     R0, R0, #0              ;; R0 <- mem[root Node] (2)
        NOT     R0, R0
        ADD     R0, R0, #1              ;; R0 <- -mem[root Node] (-2)
        LDR     R1, R5, #5              ;; R1 <- data (2)
        ADD     R0, R0, R1              ;; R0 <- data - mem[root Node] (0)
        BRnp    S2                      ;; branch skip
;;            return root;
        LDR     R0, R5, #4              ;; R0 <- root Node (x4004)
        BR      RE                      ;; branch return
;;        }

;;        if (data < root.data) {
    S2  LDR     R0, R5, #4              ;; R0 <- root Node (x4000)
        LDR     R0, R0, #0              ;; R0 <- mem[root Node] (4)
        NOT     R0, R0
        ADD     R0, R0, #1              ;; R0 <- -mem[root Node] (-4)
        LDR     R1, R5, #5              ;; R1 <- data (3)
        ADD     R0, R0, R1              ;; R0 <- data - mem[root Node] (-1)
        BRzp    S3                      ;; branch skip
;;            return binarySearch(root.left, data);
        LDR     R0, R5, #5              ;; R0 <- data (4)
        LDR     R1, R5, #4              ;; R1 <- root Node (x4000)
        ADD     R1, R1, #1              ;; R1 <- root.left (addr) (x4001)
        LDR     R1, R1, #0              ;; R1 <- root.left (x4004)
        ADD     R6, R6, #-2             ;; STACK <- STACK - 2
        STR     R0, R6, #1              ;; mem[STACK + 1] <- data   (2)
        STR     R1, R6, #0              ;; mem[STACK] <- root.left (x4004)
        JSR     binary_search           ;; binarySearch(root.left, data)
        LDR     R0, R6, #0              ;; R0 <- return
        ADD     R6, R6, #3              ;; STACK <- STACK + 3
        BR      RE
;;        }
;;        return binarySearch(root.right, data);
    S3  LDR     R0, R5, #5              ;; R0 <- data
        LDR     R1, R5, #4              ;; R1 <- root Node
        ADD     R1, R1, #2              ;; R1 <- root.right (addr)
        LDR     R1, R1, #0              ;; R1 <- root.right
        ADD     R6, R6, #-2             ;; STACK <- STACK - 2
        STR     R0, R6, #1              ;; mem[STACK + 1] <- data
        STR     R1, R6, #0              ;; mem[STACK] <- root.right
        JSR     binary_search           ;; binarySearch(root.right, data)
        LDR     R0, R6, #0              ;; R0 <- return
        ADD     R6, R6, #3              ;; STACK <- STACK + 3
        BR      RE

    RE  STR     R0, R5, #3              ;; mem[fp + 3] (return) <- R0
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
;;    }
STACK .fill xF000
.end

;; Assuming the tree starts at address x4000, here's how the tree (see below and in the pdf) represents in memory
;;
;;              4
;;            /   \
;;           2     8
;;         /   \
;;        1     3
;;
;; Memory address           Data
;; x4000                    4
;; x4001                    x4004
;; x4002                    x4008
;; x4003                    Don't Know
;; x4004                    2
;; x4005                    x400C
;; x4006                    x4010
;; x4007                    Don't Know
;; x4008                    8
;; x4009                    0(NULL)
;; x400A                    0(NULL)
;; x400B                    Don't Know
;; x400C                    1
;; x400D                    0(NULL)
;; x400E                    0(NULL)
;; x400F                    Dont't Know
;; x4010                    3
;; x4011                    0(NULL)
;; x4012                    0(NULL)
;; x4013                    Dont't Know
;;
;; *note: 0 is equivalent to NULL in assembly
