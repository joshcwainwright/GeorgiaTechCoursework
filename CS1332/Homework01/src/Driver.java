public class Driver {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.addToFront(1);
        arr.addToFront(2);
        arr.addToFront(3);
        arr.addToFront(4);
        arr.addToFront(5);
        arr.addToFront(6);
        arr.addToFront(7);
        arr.addToFront(8);
        arr.addToFront(9);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i)+ " ");
        }
        System.out.print("\n");
        System.out.println(arr.removeAtIndex(3));
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i)+ " ");
        }
        System.out.print("\n");
        System.out.println(arr.removeFromFront());
        System.out.println(arr.removeFromBack());
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        arr.addToFront(9);
        System.out.println(arr.get(15));
        System.out.println(arr.isEmpty());
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i)+ " ");
        }
        System.out.print("\n");
        arr.clear();
        System.out.println(arr.isEmpty());


    }
}
