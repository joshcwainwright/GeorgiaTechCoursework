/**
 * @author Josh W
 * @version 1
 * @param <T> dataType of data variable in node
 */
public class Node<T> {
    private T data;
    private Node next;

    /**
     * 2 arg constructor.
     * @param data type T that node holds
     * @param next following node in linked list
     */
    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }

    /**
     * 1 arg constructor.
     * @param data type T that node holds
     */
    public Node(T data) {
        this.next = null;
        this.data = data;
    }

    /**
     * following node getter.
     * @return next node
     */
    public Node getNext() {
        return next;
    }

    /**
     * data getter.
     * @return type T data held in node
     */
    public T getData() {
        return data;
    }

    /**
     * following node setter.
     * @param next node to be set as next in Linked List
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * data setter.
     * @param data type T data to be set as data in node
     */
    public void setData(T data) {
        this.data = data;
    }
}
