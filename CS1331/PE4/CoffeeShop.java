import java.util.Scanner;
public class CoffeeShop {
    public static void main(String[] args) {
        System.out.println("Welcome to your local coffee shop! What does the rush look like today?");
        Scanner myscan = new Scanner(System.in);
        String odr = myscan.nextLine();
        Order[] version = createOrderArray(odr);
        computeOrderMakeTime(version, 1);
        computeOrderMakeTime(version, 2);
        computeOrderMakeTime(version, 3);
    }
    public static Order[] createOrderArray(String str) {
        Order[] arry = new Order[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'L') {
                arry[i] = Order.LATTE;
            } else if (str.charAt(i) == 'C') {
                arry[i] = Order.COFFEE;
            } else if (str.charAt(i) == 'I') {
                arry[i] = Order.ICED_COFFEE;
            } else if (str.charAt(i) == 'F') {
                arry[i] = Order.FRAPPE;
            } else if (str.charAt(i) == 'P') {
                arry[i] = Order.PASTRY;
            }
        }
        return arry;
    }
    public static double lookupMakeTime(Order var) {
        switch (var) {
        case LATTE:
            return 3.0;
        case COFFEE:
            return 0.5;
        case ICED_COFFEE:
            return 2.0;
        case FRAPPE:
            return 6.0;
        case PASTRY:
            return 3.0;
        default:
            return 0.0;
        }
    }
    public static void computeOrderMakeTime(Order[] var1, int numBaristas) {
        double totalMakeTime = 0;
        int numOrders = var1.length;
        for (int i = 0; i < numOrders; i++) {
            totalMakeTime += lookupMakeTime(var1[i]);
        }
        double time = (totalMakeTime / numBaristas) + (numOrders % numBaristas);
        System.out.printf("It will take %.1f minutes for %d baristas to make these orders.\n", time, numBaristas);
    }
}
