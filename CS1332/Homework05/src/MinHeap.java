import java.util.ArrayList;

/**
 * Your implementation of a MinHeap.
 *
 * @author Joshua Wainwright
 * @version 1.0
 * @userid YOUR USER ID HERE (jwainwright3)
 * @GTID YOUR GT ID HERE (903584588)
 *
 * Collaborators:
 *
 * Resources: Modules and vistool
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MinHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MinHeap(ArrayList<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Inputted ArrayList is null");
        }
        backingArray = (T[]) new Comparable[(2 * data.size()) + 1];
        int i = 1;
        for (T dataItem : data) {
            if (dataItem == null) {
                throw new java.lang.IllegalArgumentException("Data within the ArrayList is null");
            }
            backingArray[i] = dataItem;
            size++;
            i++;
        }
        for (int j = size / 2; j >= 1; j--) {
            heapify(j);
        }

    }

    /**
     * recursive helper method for MinHeap that correctly orders data
     * @param pIndex parent data to be heapified
     */
    private void heapify(int pIndex) {
        int c1Index = 2 * pIndex;
        int c2Index = (2 * pIndex) + 1;
        if (backingArray[c1Index] == null) {
            return;
        } else if (backingArray[c2Index] == null) {
            if (backingArray[c1Index].compareTo(backingArray[pIndex]) == -1) {
                T pData = backingArray[pIndex];
                T cData = backingArray[c1Index];
                backingArray[c1Index] = pData;
                backingArray[pIndex] = cData;
                heapify(c1Index);
            } else {
                return;
            }
        } else if (backingArray[c1Index].compareTo(backingArray[pIndex]) == -1
                || backingArray[c2Index].compareTo(backingArray[pIndex]) == -1) {
            int sIndex;
            if (backingArray[c1Index].compareTo(backingArray[c2Index]) == -1) {
                sIndex = c1Index;
            } else {
                sIndex = c2Index;
            }
            T pData = backingArray[pIndex];
            T cData = backingArray[sIndex];
            backingArray[sIndex] = pData;
            backingArray[pIndex] = cData;
            heapify(sIndex);
        }
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * The order property of the heap must be maintained after adding.
     * 
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Inputted data is null");
        }
        if (size == backingArray.length - 1) {
            resize();
        }
        backingArray[size + 1] = data;
        int cIndex = size + 1;
        size += 1;
        int pIndex = cIndex / 2;
        while (pIndex >= 1 && backingArray[cIndex].compareTo(backingArray[pIndex]) == -1) {
            T pData = backingArray[pIndex];
            T cData = backingArray[cIndex];
            backingArray[cIndex] = pData;
            backingArray[pIndex] = cData;
            cIndex = pIndex;
            pIndex = pIndex / 2;
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     * The order property of the heap must be maintained after adding.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Heap is empty, so nothing can be removed");
        }
        T data = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size -= 1;
        int pIndex = 1;
        int c1Index = 2 * pIndex;
        int c2Index = (2 * pIndex) + 1;
        while (c1Index < backingArray.length && c2Index < backingArray.length) {
            if (backingArray[c1Index] == null) {
                break;
            } else if (backingArray[c2Index] == null) {
                if (backingArray[c1Index].compareTo(backingArray[pIndex]) == -1) {
                    T pData = backingArray[pIndex];
                    T cData = backingArray[c1Index];
                    backingArray[c1Index] = pData;
                    backingArray[pIndex] = cData;
                    pIndex = c1Index;
                } else {
                    break;
                }
            } else if (backingArray[c1Index].compareTo(backingArray[pIndex]) == -1
                    || backingArray[c2Index].compareTo(backingArray[pIndex]) == -1) {
                int sIndex;
                if (backingArray[c1Index].compareTo(backingArray[c2Index]) == -1) {
                    sIndex = c1Index;
                } else {
                    sIndex = c2Index;
                }
                T pData = backingArray[pIndex];
                T cData = backingArray[sIndex];
                backingArray[sIndex] = pData;
                backingArray[pIndex] = cData;
                pIndex = sIndex;
            } else {
                break;
            }
            c1Index = 2 * pIndex;
            c2Index = (2 * pIndex) + 1;
        }
        return data;
    }

    /**
     *
     * Returns the minimum element in the heap.
     *
     * @return the minimum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMin() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Heap is empty, so nothing can be removed");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Method to resize backing array to double its orginial size
     * NOTE: I assumed that I was doubling the capacity of the backing array, not the heap itself
     */
    private void resize() {
        T[] newBackingArray = (T[]) new Comparable[(backingArray.length) * 2];
        for (int i = 1; i < backingArray.length; i++) {
            newBackingArray[i] = backingArray[i];
        }
        backingArray = newBackingArray;
    }


    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
