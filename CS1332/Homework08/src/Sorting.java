import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Random;
import java.util.List;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Joshua Wainwright
 * @version 1.0
 * @userid jwainwright3
 * @GTID 903584588
 *
 * Collaborators: no collaborators
 *
 * Resources  csvistool
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("Inputted array is null");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Inputted comparator is null");
        }
        for (int a = 1; a < arr.length; a++) {
            int i = a - 1;
            int j = a;
            while (i >= 0 && comparator.compare(arr[i], arr[j]) > 0) {
                swap(arr, i, j);
                i--;
                j--;
            }

        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("Inputted array is null");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Inputted comparator is null");
        }
        boolean swap = true;
        int st = 0;
        int end = arr.length - 1;
        int swapI = 0;
        while (swap) {
            swap = false;
            for (int i = st; i < end; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swap = true;
                    swapI = i;
                }
            }
            end = swapI;
            if (swap) {
                swap = false;
                for (int i = end; i > st; i--) {
                    if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                        swap(arr, i - 1, i);
                        swap = true;
                        swapI = i;
                    }
                }
                st = swapI;
            }
        }
    }

    /**
     * Swap helper method that swaps the position of two data values in an array
     * @param arr arr that holds both data values
     * @param i int index of value one
     * @param j int index of value two
     * @param <T> data type to sort
     */
    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("Inputted array is null");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Inputted comparator is null");
        }
        if (arr.length < 2) {
            return;
        }
        int m = arr.length / 2;
        T[] left = (T[]) new Object[m];
        T[] right = (T[]) new Object[arr.length - m];
        for (int i = 0; i < m; i++) {
            left[i] = arr[i];
        }
        for (int i = m; i < arr.length; i++) {
            right[i - m] = arr[i];
        }
        mergeSort(left, comparator);
        mergeSort(right, comparator);

        int l = 0;
        int r = 0;
        while (l < left.length && r < right.length) {
            if (comparator.compare(left[l], right[r]) <= 0) {
                arr[l + r] = left[l];
                l++;
            } else {
                arr[l + r] = right[r];
                r++;
            }
        }
        while (l < left.length) {
            arr[l + r] = left[l];
            l++;
        }
        while (r < right.length) {
            arr[l + r] = right[r];
            r++;
        }
    }


    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("Inputted array is null");
        }
        if (comparator == null) {
            throw new java.lang.IllegalArgumentException("Inputted comparator is null");
        }
        if (rand == null) {
            throw new java.lang.IllegalArgumentException("Inputted rand is null");
        }
        quickSort(0, arr.length - 1, arr, comparator, rand);
    }

    /**
     * Recursive helper method for quickSort algorithm
     * @param start int of start index
     * @param end int of end index
     * @param arr arr where data is stored
     * @param comparator comparator to compare data of type T
     * @param rand rand tool variable
     * @param <T> Type of data to be sorted
     */
    private static <T> void quickSort(int start, int end, T[] arr, Comparator<T> comparator, Random rand) {
        if (end - start < 1) {
            return;
        }
        int pivot = rand.nextInt(end - start + 1) + start;
        T pivotVal = arr[pivot];
        swap(arr, start, pivot);
        int i = start + 1;
        int j = end;
        while (j >= i) {
            while (j >= i && comparator.compare(arr[i], pivotVal) <= 0) {
                i++;
            }
            while (j >= i && comparator.compare(arr[j], pivotVal) >= 0) {
                j--;
            }
            if (j >= i) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, j, start);
        quickSort(start, j - 1, arr, comparator, rand);
        quickSort(j + 1, end, arr, comparator, rand);
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new java.lang.IllegalArgumentException("Inputted array is null");
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        int maxVal = 0;
        for (int i = 0; i < arr.length; i++) {
            if (absolute(arr[i]) > maxVal) {
                maxVal = absolute(arr[i]);
            }
        }
        int place = 1;
        while (maxVal != 0) {
            maxVal /= 10;
            for (int num : arr) {
                int index = num / place;
                if (buckets[index % 10 + 9] == null) {
                    buckets[index % 10 + 9] = new LinkedList<>();
                }
                buckets[index % 10 + 9].add(num);
            }
            int j = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (bucket != null && !bucket.isEmpty()) {
                    arr[j++] = bucket.remove();
                }
            }
            place *= 10;
        }
    }

    /**
     * Absolute value helper method for radix sort
     * @param a integer to be evaluated
     * @return absoluate value of integer a
     */
    private static int absolute(int a) {
        if (a < 0) {
            a *= -1;
        }
        return a;
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Inputted data is null");
        }
        PriorityQueue<Integer> que = new PriorityQueue<>(data);
        int[] sorted = new int[que.size()];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = que.remove();
        }
        return sorted;
    }
}
