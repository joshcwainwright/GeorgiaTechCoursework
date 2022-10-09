/**
 * FordGT class.
 * @author Joshua Wainwright
 * @version 1
 */
public class FordGT extends Car {
    private double weight;
    private String driverName;

    /**
     * 7 arg constructor.
     * @param name string name
     * @param make string make
     * @param fuelLevel double fuel level
     * @param horsepower int horsepower
     * @param previousUpgrade boolean of previous upgrade
     * @param weight double weight
     * @param driverName string driver name
     */
    public FordGT(String name, String make, double fuelLevel, int horsepower, boolean previousUpgrade, double weight,
                  String driverName) {
        super(name, make, fuelLevel, horsepower, previousUpgrade);
        this.weight = weight;
        this.driverName = driverName;
    }

    /**
     * no arg constructor.
     */
    public FordGT() {
        this("Pony", "Ford", 100, 450, false, 3300, "Ken Miles");
    }

    /**
     * Print method.
     * @return string representation of FordGT
     */
    public String toString() {
        String parent = super.toString();
        String fordGt = ", Weight: %.1f, Driver: %s";
        return parent + String.format(fordGt, weight, driverName);
    }

    /**
     * equals method.
     * @param otherCar Car object being passed in to test equality
     * @return boolean representing if the two objects are equal in value
     */
    @Override
    public boolean equals(Object otherCar) {
        if (otherCar instanceof FordGT) {
            if (((FordGT) otherCar).weight == weight) {
                return super.equals(otherCar);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * upgrade method.
     */
    public void upgrade() {
        if (!this.getPreviousUpgrade()) {
            this.setFuelLevel(this.getFuelLevel() + 50);
            if (this.getFuelLevel() > this.MAX_FUEL_LEVEL) {
                this.setFuelLevel(MAX_FUEL_LEVEL);
            }
            super.upgrade();
        }
    }

    /**
     * weight getter.
     * @return double weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * method that races two Ford GT objects.
     * @param otherCar Car object being passed in to race
     */
    public void race(Car otherCar) {
        if (otherCar instanceof FordGT && (this.getFuelLevel() > 50 && otherCar.getFuelLevel() > 50)) {
            this.setFuelLevel(this.getFuelLevel() - 25);
            otherCar.setFuelLevel(otherCar.getFuelLevel() - 25);
            if (this.getHorsepower() > otherCar.getHorsepower()) {
                System.out.printf("%s with %s as the driver won against %s with %s as the driver",
                        this.getName(), this.driverName, otherCar.getName(), ((FordGT) otherCar).driverName);
            } else if (otherCar.getHorsepower() > this.getHorsepower()) {
                System.out.printf("%s with %s as the driver won against %s with %s as the driver",
                        otherCar.getName(), ((FordGT) otherCar).driverName, this.getName(), this.driverName);
            } else if (otherCar.getHorsepower() == this.getHorsepower()) {
                if (this.weight > ((FordGT) otherCar).getWeight()) {
                    System.out.printf("%s with %s as the driver won against %s with %s as the driver",
                            this.getName(), this.driverName, otherCar.getName(), ((FordGT) otherCar).driverName);
                } else if (this.weight < ((FordGT) otherCar).getWeight()) {
                    System.out.printf("%s with %s as the driver won against %s with %s as the driver",
                            otherCar.getName(), ((FordGT) otherCar).driverName, this.getName(), this.driverName);
                } else if (this.weight == ((FordGT) otherCar).getWeight()) {
                    System.out.printf("%s tied with %s", otherCar.getName(), this.getName());
                }
            }
        } else {
            System.out.printf("%s could not race %s", otherCar.getName(), this.getName());
        }
    }
}
