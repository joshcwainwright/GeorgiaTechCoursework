;;=============================================================
;; CS 2110 - Fall 2021
;; Homework 6 - Quick Sort
;;=============================================================
;; Name:
;;============================================================

;; In this file, you must implement the 'quicksort' and 'partition' subroutines.

;; Little reminder from your friendly neighborhood 2110 TA staff: don't run
;; this directly by pressing 'RUN' in complx, since there is nothing put at
;; address x3000. Instead, load it and use 'Debug' -> 'Simulate
;; Subroutine Call' and choose the 'quicksort' or 'partition' label.


;; Pseudocode:

;; Partition

;;    partition(int[] arr, int low, int high) {
;;        int pivot = arr[high];
;;        int i = low - 1;
;;        for (j = low; j < high; j++) {
;;            if (arr[j] < pivot) {
;;                i++;
;;                int temp = arr[j];
;;                arr[j] = arr[i];
;;                arr[i] = temp;
;;            }
;;        }
;;        int temp = arr[high];
;;        arr[high] = arr[i + 1];
;;        arr[i + 1] = temp;
;;        return i + 1;
;;    }

;; Quicksort

;;    quicksort(int[] arr, int left, int right) {
;;        if (left < right) {
;;            int pi = partition(arr, left, right);
;;            quicksort(arr, left, pi - 1);
;;            quicksort(arr, pi + 1, right);
;;        }
;;    }


.orig x3000
    ;; you do not need to write anything here
HALT

partition   ;; please do not change the name of your subroutine
;;    #0 temp, #1 j, #2 i, #3 pivot, #4 old fp, #5 ret addr, #6 ret, #7 arr, #8 low, #9 high
;;    partition(int[] arr, int low, int high) {
        ADD     R6, R6, #-7             ;; R6 <- Stack - 7, allocate frame space
        STR     R7, R6, #5              ;; mem[stack + 5] <- ret addr, Save return address
        STR     R5, R6, #4              ;; mem[stack + 4] <- old fp, Save old frame pointer
        ADD     R5, R6, #0              ;; R5 <- stack, set new frame
        ADD     R6, R6, #-5             ;; R6 < Sack - 5, allocate reg space
        STR     R0, R5, #-1             ;; mem[fp - 1] <- R0
        STR     R1, R5, #-2             ;; mem[fp - 2] <- R1
        STR     R2, R5, #-3             ;; mem[fp - 3] <- R2
        STR     R3, R5, #-4             ;; mem[fp - 4] <- R3
        STR     R4, R5, #-5             ;; mem[fp - 5] <- R4

;;        int pivot = arr[high];
        LDR     R0, R5, #7              ;; R0 <- arr (arr)
        LDR     R1, R5, #9              ;; R1 <- high (2)
        ADD     R0, R0, R1              ;; R0 <- arr + high (2)
        LDR     R0, R0, #0              ;; R0 <- mem[arr + high] (1)
        STR     R0, R5, #3              ;; pivot <- mem[arr + high] (1)

;;        int i = low - 1;
        LDR     R0, R5, #8              ;; R0 <- low (0)
        ADD     R0, R0, #-1             ;; R0 <- low - 1 (-1)
        STR     R0, R5, #2              ;; i <- low - 1 (-1)

;;        for (j = low; j < high; j++) {
        LDR     R0, R5, #8              ;; R0 <- low (0)
        STR     R0, R5, #1              ;; j <- low (0)

    F1  LDR     R0, R5, #1              ;; R0 <- j (0)
        NOT     R0, R0
        ADD     R0, R0, #1              ;; R0 <- -j (0)
        LDR     R1, R5, #9              ;; R1 <- high (2)
        ADD     R0, R0, R1              ;; R0 <- high - j (2)
        BRnz    EF1                     ;; branch end for

;;            if (arr[j] < pivot) {
        LDR     R0, R5, #7              ;; R0 <- arr (arr)
        LDR     R1, R5, #1              ;; R1 <- j (0)
        ADD     R0, R0, R1              ;; arr + j (0)
        LDR     R0, R0, #0              ;; R0 <- mem[arr +j] (23)
        NOT     R0, R0
        ADD     R0, R0, #1              ;; R0 <- -mem[arr + j] (-23)
        LDR     R1, R5, #3              ;; R1 <- pivot (1)
        ADD     R0, R0, R1              ;; R0 <- pivot - mem[arr + j] 1-23
        BRnz    S1
;;                i++;
        LDR     R0, R5, #2              ;; R0 <- i
        ADD     R0, R0, #1              ;; R0 <- i++
        STR     R0, R5, #2              ;; i <- i++

;;                int temp = arr[j];
        LDR     R0, R5, #7              ;; R0 <- arr
        LDR     R1, R5, #1              ;; R1 <- j
        ADD     R0, R0, R1              ;; R0 <- arr + j
        LDR     R0, R0, #0              ;; R0 <- mem[arr +j]
        STR     R0, R5, #0              ;; temp <- mem[arr + j]

;;                arr[j] = arr[i];
        LDR     R0, R5, #7              ;; R0 <- arr
        LDR     R1, R5, #1              ;; R1 <- j
        ADD     R0, R0, R1              ;; R0 <- arr + j
        LDR     R1, R5, #7              ;; R1 <- arr
        LDR     R2, R5, #2              ;; R2 <- i
        ADD     R1, R1, R2              ;; R1 <- arr + i
        LDR     R1, R1, #0              ;; R1 <- mem[arr + i]
        STR     R1, R0, #0              ;; mem[arr + j] <- mem[arr + i]

;;                arr[i] = temp;
        LDR     R0, R5, #7              ;; R0 <- arr
        LDR     R1, R5, #2              ;; R1 <- i
        ADD     R0, R0, R1              ;; R0 <- i + arr
        LDR     R1, R5, #0              ;; R1 <- temp
        STR     R1, R0, #0              ;; mem[i + arr] <- temp
;;            }

    S1  LDR     R0, R5, #1              ;; R0 <- j
        ADD     R0, R0, #1              ;; R0 <- j++
        STR     R0, R5, #1              ;; j <- j++
        BR      F1
;;        }
    EF1
;;        int temp = arr[high];
        LDR     R0, R5, #7              ;; R0 <- arr
        LDR     R1, R5, #9              ;; R1 <- high
        ADD     R0, R0, R1              ;; R0 <- arr + high
        LDR     R0, R0, #0              ;; R0 <- mem[arr + high]
        STR     R0, R5, #0              ;; temp <- mem[arr + high]

;;        arr[high] = arr[i + 1];
        LDR     R0, R5, #7              ;; R0 <- arr
        LDR     R1, R5, #9              ;; R1 <- high
        ADD     R0, R0, R1              ;; R0 <- arr + high
        LDR     R1, R5, #7              ;; R1 <- arr
        LDR     R2, R5, #2              ;; R2 <- i
        ADD     R1, R1, R2              ;; R1 <- arr + i
        ADD     R1, R1, #1              ;; R1 <- arr + i + 1
        LDR     R1, R1, #0              ;; R1 <- mem[arr + i + 1]
        STR     R1, R0, #0              ;; mem[arr + high] <- mem[arr + i + 1]
;;        arr[i + 1] = temp;
        LDR     R0, R5, #7              ;; R0 <- arr
        LDR     R1, R5, #2              ;; R1 <- i
        ADD     R0, R0, R1              ;; R0 <- arr + i
        ADD     R0, R0, #1              ;; R0 <- arr + i + 1
        LDR     R1, R5, #0              ;; R1 <- temp
        STR     R1, R0, #0              ;; mem[arr + i + 1] <- temp
;;        return i + 1;
        LDR     R0, R5, #2              ;; R0 <- i
        ADD     R0, R0, #1              ;; R0 <- i + 1
        STR     R0, R5, #6              ;; mem[fp + 6] (return) <- R0
        LDR     R0, R5, #-1             ;; R0 <- mem [fp - 1]
        LDR     R1, R5, #-2             ;; R1 <- mem [fp - 2]
        LDR     R2, R5, #-3             ;; R2 <- mem [fp - 3]
        LDR     R3, R5, #-4             ;; R3 <- mem [fp - 4]
        LDR     R4, R5, #-5             ;; R4 <- mem [fp - 5]
        ADD     R6, R5, #0              ;; R6 <- fp
        LDR     R5, R6, #4              ;; R5 <- mem[stack + 4] (old fp)
        LDR     R7, R6, #5              ;; R7 <- mem[stack + 5] (ret addr)
        ADD     R6, R6, #6              ;; R6 <- STACK + 6
;;    }
    RET

quicksort   ;; please do not change the name of your subroutine
;;    quicksort(int[] arr, int left, int right) {
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
;;        if (left < right) {
        LDR     R0, R5, #5              ;; R0 <- left
        NOT     R0, R0
        ADD     R0, R0, #1              ;; R0 <- -left
        LDR     R1, R5, #6              ;; R1 <- right
        ADD     R0, R0, R1              ;; R0 <- right - left
        BRnz    S4

;;            int pi = partition(arr, left, right);
        LDR     R0, R5, #6              ;; R0 <- right
        LDR     R1, R5, #5              ;; R1 <- left
        LDR     R2, R5, #4              ;; R2 <- arr
        ADD     R6, R6, #-3             ;; STACK <- STACK - 3
        STR     R0, R6, #2              ;; mem[STACK + 2] <- right
        STR     R1, R6, #1              ;; mem[STACK + 1] <- left
        STR     R2, R6, #0              ;; mem[STACK] <- arr
        JSR     partition
        LDR     R0, R6, #0              ;; R0 <- pi
        ADD     R6, R6, #1              ;; STACK <- STACK + 1

;;            quicksort(arr, left, pi - 1);
        ADD     R1, R0, #-1             ;; R1 <- pi - 1
        LDR     R2, R5, #5              ;; R2 <- left
        LDR     R3, R5, #4              ;; R3 <- arr
        ADD     R6, R6, #-3             ;; STACK <- STACK -3
        STR     R1, R6, #2              ;; mem[STACK + 2] <- pi - 1
        STR     R2, R6, #1              ;; mem[STACK + 1] <- left
        STR     R3, R6, #0              ;; mem[STACK] <- arr
        JSR     quicksort
        ADD     R6, R6, #1              ;; STACK <- STACK + 1
;;            quicksort(arr, pi + 1, right);
        LDR     R1, R5, #6              ;; R1 <- right
        ADD     R2, R0, #1              ;; R2 <- pi + 1
        LDR     R3, R5, #4              ;; R3 <- arr
        ADD     R6, R6, #-3             ;; STACK <- STACK - 3
        STR     R1, R6, #2              ;; mem[STACK + 2] <- right
        STR     R2, R6, #1              ;; mem[STACK + 1] <- pi + 1
        STR     R3, R6, #0              ;; mem[STACK] <- arr
        JSR     quicksort
        ADD     R6, R6, #1              ;; STACK <- STACK + 1

;;        }
    S4

        STR     R0, R5, #3              ;; mem[fp + 3] (return) <- R0
        LDR     R0, R5, #-1             ;; R0 <- mem [fp - 1]
        LDR     R1, R5, #-2             ;; R1 <- mem [fp - 2]
        LDR     R2, R5, #-3             ;; R2 <- mem [fp - 3]
        LDR     R3, R5, #-4             ;; R3 <- mem [fp - 4]
        LDR     R4, R5, #-5             ;; R4 <- mem [fp - 5]
        ADD     R6, R5, #0              ;; R6 <- fp
        LDR     R5, R6, #1              ;; R5 <- mem[stack + 1] (old fp)
        LDR     R7, R6, #2              ;; R7 <- mem[stack + 2] (ret addr)
        ADD     R6, R6, #3              ;; R6 <- STACK + 3 (reset stack + 1 void return)
;;    }
    RET

STACK .fill xF000
.end
.orig x5000
    .fill 23
    .fill 10
    .fill 1
.end


;; Assuming the array starts at address x4000, here's how the array [1,3,2,5] represents in memory
;; Memory address           Data
;; x4000                    1
;; x4001                    3
;; x4002                    2
;; x4003                    5
