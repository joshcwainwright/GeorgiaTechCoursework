/**
 * Shop Driver Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class ShopDriver {
    /**
     * Main Method.
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        double[] dArr = {0.7, 0.32, 0.45, 0.5};
        Car c1 = new Car("SUV", 15000, 14600, dArr);
        Car c2 = new Car("SUV", 50, new double[]{0.4, 0.5, 0.6, 0.7});
        Mechanic m1 = new Mechanic("Raul");
        m1.service(c1);
        System.out.println(m1);
        System.out.println(c1);
        //the next section of code is meant to test if a negative mileage is passed into the setter method for Car
        c1.setMileage(-100);
        System.out.println(c1.getMileage());
        System.out.println(c1.getNumCars());
        Car c3 = new Car(c1);
    }
}
