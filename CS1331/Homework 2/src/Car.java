/**
 * Car class.
 * @author Joshua Wainwright
 * @version 1
 */
public abstract class Car {
    private String name;
    private String make;
    private double fuelLevel;
    private int horsepower;
    private boolean previousUpgrade;
    protected  static final double MAX_FUEL_LEVEL = 100;

    /**
     * 5 arg constructor.
     * @param name string name of car
     * @param make string make of car
     * @param fuelLevel double fuel level of car
     * @param horsepower int horsepower of car
     * @param previousUpgrade boolean representing if the car has been upgraded
     */
    public Car(String name, String make, double fuelLevel, int horsepower, boolean previousUpgrade) {
        this.name = name;
        this.make = make;
        if (fuelLevel <= MAX_FUEL_LEVEL) {
            this.fuelLevel = fuelLevel;
        } else {
            this.fuelLevel = MAX_FUEL_LEVEL;
        }
        this.horsepower = horsepower;
        this.previousUpgrade = previousUpgrade;
    }

    /**
     * Print method.
     * @return String representing car object.
     */
    public String toString() {
        String toString = "Car named: %s, Make: %s, Fuel Amount: %.1f, HP: %d";
        return String.format(toString, this.name, this.make, this.fuelLevel, this.horsepower);
    }

    /**
     * Equals method that test if two car objects are equal.
     * @param otherCar Car object being passed in to test equality
     * @return Boolean representing if the two cars are equal or not equal
     */
    @Override
    public boolean equals(Object otherCar) {
        if (otherCar instanceof Car) {
            if (((Car) otherCar) == null || this == null) {
                return false;
            } else if (((Car) otherCar).name == null || name == null) {
                return false;
            } else if (((Car) otherCar).make == null || make == null) {
                return false;
            } else {
                if (((Car) otherCar).name.equals(name)) {
                    if (((Car) otherCar).make.equals(make)) {
                        return ((Car) otherCar).horsepower == this.horsepower;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Method that upgrades car.
     */
    public void upgrade() {
        if (!(this.previousUpgrade)) {
            if (this.horsepower < 200) {
                this.horsepower += 100;
            } else if (this.horsepower >= 200) {
                this.horsepower += 50;
            }
            this.previousUpgrade = true;
        }
    }

    /**
     * abtract method that will be defined in ShelbyMustang and FordGT classes.
     * @param otherCar Car object being passed in to race
     */
    public abstract void race(Car otherCar);

    /**
     * fuel level getter.
     * @return double fuel level
     */
    public double getFuelLevel() {
        return fuelLevel;
    }

    /**
     * name getter.
     * @return string car name
     */
    public String getName() {
        return name;
    }

    /**
     * fuel level setter.
     * @param fuelLevel boolean of fuel level
     */
    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    /**
     * horsepower getter.
     * @return int of horsepower
     */
    public int getHorsepower() {
        return horsepower;
    }

    /**
     * previous upgrade getter.
     * @return boolean of previous upgrade
     */
    public boolean getPreviousUpgrade() {
        return previousUpgrade;
    }

    /**
     * horsepower setter.
     * @param horsepower int of horsepower
     */
    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    /**
     * previous upgrade setter.
     * @param previousUpgrade boolean of previous upgrade
     */
    public void setPreviousUpgrade(boolean previousUpgrade) {
        this.previousUpgrade = previousUpgrade;
    }
}