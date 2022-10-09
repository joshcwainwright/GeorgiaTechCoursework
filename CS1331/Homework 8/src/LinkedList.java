import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList class.
 * @author Josh W
 * @version 1
 * @param <T> dataType of data variable in node
 */
public class LinkedList<T> implements List<T> {
    private Node head;
    private int size;

    /**
     * 0 arg constructor.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * add method that adds data at a specific index.
     * @param index - index at which the data is to be inserted
     * @param data - data to be inserted
     * @throws IndexOutOfBoundsException if index is out of range
     * @throws IllegalArgumentException if data arg is null
     */
    public void add(int index, T data) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (index > size - 1 || size == 0) {
            throw new IndexOutOfBoundsException();
        }
        if (data == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        Node temp = head;
        while (count < index - 1) {
            temp = temp.getNext();
            count++;
        }
        Node left = temp;
        Node right = temp.getNext();
        left.setNext(new Node(data, right));
        size++;
    }

    /**
     * add method that adds data at end of LinkedList.
     * @param data - data to be appended to this list
     * @throws IllegalArgumentException if data arg is null
     */
    public void add(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (head == null) {
            head = new Node(data);
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node(data));
        }
        size++;
    }

    /**
     * get method that retreives data at a specific index.
     * @param index - the index of the data to be retreived
     * @return data at index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index > size - 1 || size == 0) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        Node temp = head;
        while (count < index) {
            temp = temp.getNext();
            count++;
        }
        return (T) temp.getData();
    }

    /**
     * isEmpty method that returns if LinkedList is empty.
     * @return boolean of True or False
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * clear method that removes all data from LinkedList.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * test method (not for grading).
     */
    public void complete() {
        Iterator<T> iterate = iterator();
        while (iterate.hasNext()) {
            System.out.println(iterate.next());
        }
    }

    /**
     * iterator method.
     * @return new instance of a LinkedListIterator
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * LinkedListIterator class.
     * @author Josh W
     * @version 1
     */
    private class LinkedListIterator implements Iterator<T> {
        private Node nextNode;

        /**
         * 0 arg constructor.
         */
        LinkedListIterator() {
            this.nextNode = head;
        }

        /**
         * hasNext method that determines if there is a following node in LinkedList.
         * @return boolean of true or false
         */
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * next method.
         * @return data of sequential node in LinkedList
         * @throws NoSuchElementException if there is no sequential node
         */
        public T next() throws NoSuchElementException {
            if (nextNode == null) {
                throw new NoSuchElementException();
            } else {
                T temp = (T) nextNode.getData();
                nextNode = nextNode.getNext();
                return temp;
            }
        }
    }

    /**
     * head getter.
     * @return Node head
     */
    public Node getHead() {
        return head;
    }

    /**
     * size getter.
     * @return int size
     */
    public int getSize() {
        return size;
    }
}
