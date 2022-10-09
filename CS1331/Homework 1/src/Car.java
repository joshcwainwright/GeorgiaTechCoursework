/**
 * Car class.
 * @author Joshua Wainwright
 * @version 1
 */
public class Car {
    private String type;
    private int mileage;
    private int nextOilChange;
    private double[] tireLife;
    private static int numCars = 0;
    private int constrNum = 0;

    /**
     * Copy Constructor.
     * @param anotherCar car object that should be coppied
     */
    public Car(Car anotherCar) {
        this.type = new String(anotherCar.type);
        if (anotherCar.mileage >= 0) {
            this.mileage = anotherCar.mileage;
        } else {
            this.mileage = 0;
        }
        if (anotherCar.nextOilChange >= 0) {
            this.nextOilChange = anotherCar.nextOilChange;
        } else {
            this.nextOilChange = mileage + 3000;
        }
        this.tireLife = new double[4];
        for (int i = 0; i < anotherCar.tireLife.length; i++) {
            if (anotherCar.tireLife[i] >= 0.00 && anotherCar.tireLife[i] <= 1.00) {
                this.tireLife[i] = anotherCar.tireLife[i];
            } else {
                this.tireLife[i] = 1.00;
            }
        }
        this.numCars += 1;
    }

    /**
     * two arg constructor.
     * @param type String of name of car type
     * @param tireLife double array of tire life
     */
    public Car(String type, double[] tireLife) {
        this(type, 0, tireLife);
        this.constrNum += 1;
    }

    /**
     * 3 arg constructor.
     * @param type String of name of car type
     * @param mileage int of mileage on car
     * @param tireLife double array of tire life
     */
    public Car(String type, int mileage, double[] tireLife) {
        this(type, mileage, 3000 + mileage, tireLife);
        this.constrNum += 1;
    }

    /**
     * 4 arg constructor.
     * @param type String of name of car type
     * @param mileage int of mileage on car
     * @param nextOilChange int of mileage of next oil change
     * @param tireLife double array of tire life
     */
    public Car(String type, int mileage, int nextOilChange, double[] tireLife) {
        this.type = type;
        if (mileage >= 0) {
            this.mileage = mileage;
        } else {
            if (this.constrNum == 0) {
                this.mileage = 0;
            } else {
                nextOilChange -= mileage;
                this.mileage = 0;
            }
        }
        if (nextOilChange >= 0) {
            this.nextOilChange = nextOilChange;
        } else {
            this.nextOilChange = this.mileage + 3000;
        }
        for (int i = 0; i < tireLife.length; i++) {
            if (tireLife[i] >= 0.00 && tireLife[i] <= 1.00) {
                continue;
            } else {
                tireLife[i] = 1.00;
            }
        }
        this.tireLife = tireLife;
        this.numCars += 1;
    }

    /**
     * helper method that calculates average tire life.
     * @return int representing the average tire life in percentage form
     */
    private int averageTireLife() {
        double sum = 0;
        for (int i = 0; i < this.tireLife.length; i++) {
            sum += this.tireLife[i];
        }
        int tempVar = (int) ((sum / 4) * 100);
        return tempVar;
    }

    /**
     * print method.
     * @return String representation of car object
     */
    public String toString() {
        String tempString = "This is a car of type %s with a mileage of %d miles. I'm due for a checkup in %d miles, "
                + "and my average tire life is %d%%.";
        return String.format(tempString, this.type, this.mileage, (this.nextOilChange - this.mileage),
                this.averageTireLife());
    }

    /**
     * type getter.
     * @return String representing car type
     */
    public String getType() {
        return this.type;
    }

    /**
     * type Setter.
     * @param type String of car type name
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * mileage getter.
     * @return int representing car mileage
     */
    public int getMileage() {
        return this.mileage;
    }

    /**
     * mileage setter.
     * @param mileage int of car mileage
     */
    public void setMileage(int mileage) {
        if (mileage >= 0) {
            this.mileage = mileage;
        } else {
            this.mileage = 0;
        }
    }

    /**
     * nextOilChange getter.
     * @return in representing car nextOilChange miles
     */
    public int getNextOilChange() {
        return this.nextOilChange;
    }

    /**
     * nextOilChnage setter.
     * @param nextOilChange int of car mileage at next oil change
     */
    public void setNextOilChange(int nextOilChange) {
        if (nextOilChange >= 0) {
            this.nextOilChange = nextOilChange;
        } else {
            this.nextOilChange = mileage + 3000;
        }
    }

    /**
     * tireLife getter.
     * @return double array representing the life of each tire on the car
     */
    public double[] getTireLife() {
        return this.tireLife;
    }

    /**
     * tireLife setter.
     * @param tireLife double array of tire life
     */
    public void setTireLife(double[] tireLife) {
        for (int i = 0; i < tireLife.length; i++) {
            if (tireLife[i] >= 0.00 && tireLife[i] <= 1.00) {
                this.tireLife[i] = tireLife[i];
            } else {
                this.tireLife[i] = 1.00;
            }
        }
    }

    /**
     * numCars getter.
     * @return int representing the number of cars that have been initialized
     */
    public int getNumCars() {
        return this.numCars;
    }

    /**
     * numCars setter.
     * @param numCars int representing number of cars initialized
     */
    public void setNumCars(int numCars) {
        if (numCars >= 0) {
            this.numCars = numCars;
        } else {
            this.numCars = 0;
        }
    }
}
