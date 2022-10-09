public class Driver {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList();
        list.addToFront(5);
        list.removeAtIndex(0);
        list.addToBack(6);

        list.addAtIndex(0,4);
        list.addAtIndex(1,5);
        list.addAtIndex(3,0);
        list.addToBack(7);
        list.addToBack(9);
        list.addAtIndex(5, 0);
        DoublyLinkedListNode<Integer> c1 = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(c1.getData() + "  ");
            c1 = c1.getNext();
        }
        System.out.print("\n");
        System.out.println(list.removeFromBack());
        System.out.println(list.removeFromFront());
        System.out.println(list.removeAtIndex(5));
        list.clear();
        System.out.println(list.isEmpty());
        DoublyLinkedListNode<Integer> c2 = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(c2.getData() + "  ");
            c2 = c2.getNext();
        }
        System.out.print("\n");
        for (int i = 0; i < list.toArray().length; i++) {
            System.out.print(list.toArray()[i] + "  ");
        }
        System.out.print("\n");
    }
}
