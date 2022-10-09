/**
 * Mechanic class.
 * @author Joshua Wainwright
 * @version 1
 */
public class Mechanic {
    private final String name;
    private double revenue;
    private double oilChangePrice;
    private double newTirePrice;

    /**
     * 1 arg constructor.
     * @param name name of mechanic
     */
    public Mechanic(String name) {
        this(name, 44.99, 59.99);
    }

    /**
     * 3 arg constructor.
     * @param name name of mechanic
     * @param oilChangePrice price of an oil change
     * @param newTirePrice price of a new tire
     */
    public Mechanic(String name, double oilChangePrice, double newTirePrice) {
        this(name, 0.00,  oilChangePrice, newTirePrice);
    }

    /**
     * 4 arg constructor.
     * @param name name of mechanic
     * @param revenue profit of mechanic
     * @param oilChangePrice price of an oil change
     * @param newTirePrice price of a new tire
     */
    public Mechanic(String name, double revenue, double oilChangePrice, double newTirePrice) {
        this.name = name;
        if (revenue >= 0) {
            this.revenue = revenue;
        } else {
            this.revenue = 0.00;
        }
        if (oilChangePrice >= 0.99) {
            this.oilChangePrice = oilChangePrice;
        } else {
            this.oilChangePrice = 44.99;
        }
        if (newTirePrice >= 0.99) {
            this.newTirePrice = newTirePrice;
        } else {
            this.newTirePrice = 59.99;
        }
    }

    /**
     * Print method.
     * @return String representation of mechanic
     */
    public String toString() {
        String tempString = "This is a mechanic named %s. I charge $%.2f for an oil change, and "
                + "I charge $%.2f for a new tire. I've made $%.2f in revenue.";
        return String.format(tempString, this.name, this.oilChangePrice, this.newTirePrice, this.revenue);
    }

    /**
     * service method that applies maintenance to car object.
     * @param c passed in car
     */
    public void service(Car c) {
        if (c.getMileage() >= c.getNextOilChange()) {
            c.setNextOilChange(3000 + c.getMileage());
            this.revenue += this.oilChangePrice;
        }
        for (int i = 0; i < c.getTireLife().length; i++) {
            if (c.getTireLife()[i] <= 0.5) {
                c.getTireLife()[i] = 1.00;
                this.revenue += this.newTirePrice;
            }
        }
    }
}
