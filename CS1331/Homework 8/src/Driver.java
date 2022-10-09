import java.util.Iterator;
import java.util.NoSuchElementException;

public class Driver {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(1, 3);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        Iterator i = list.iterator();
        System.out.println(i.next());
        System.out.println(i.hasNext());
        while (i.hasNext()) {
            System.out.println(i.next());
        }
        System.out.println(list.isEmpty());
        list.add(5,5);


    }
}
