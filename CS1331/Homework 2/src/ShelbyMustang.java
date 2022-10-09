/**
 * ShelbyMustang class.
 * @author Joshua Wainwright
 * @version 1
 */
public class ShelbyMustang extends Car {
    private int racesWon;

    /**
     * 6 arg constructor.
     * @param name string name of car
     * @param make string make of car
     * @param fuelLevel double of fuel level
     * @param horsepower into of horsepower
     * @param previousUpgrade boolean of previous upgrade
     * @param racesWon int of races won
     */
    public ShelbyMustang(String name, String make, double fuelLevel, int horsepower, boolean previousUpgrade,
                         int racesWon) {
        super(name, make, fuelLevel, horsepower, previousUpgrade);
        this.racesWon = racesWon;
    }

    /**
     * no arg constructor.
     */
    public ShelbyMustang() {
        this("Carroll", "Shelby Automotives", 100, 350, false, 0);
    }

    /**
     * Print method.
     * @return String representation of Shelby Mustang object
     */
    public String toString() {
        String parent = super.toString();
        String shelby = ", Races Won: %d";
        return parent + String.format(shelby, this.racesWon);
    }

    /**
     * equals method.
     * @param otherCar Car object being passed in to test equality
     * @return boolean representing if both objects are equal in value
     */
    @Override
    public boolean equals(Object otherCar) {
        if (otherCar instanceof ShelbyMustang) {
            if (((ShelbyMustang) otherCar).racesWon == racesWon) {
                return super.equals(otherCar);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * method that races two cars.
     * @param otherCar Car object being passed in to race
     */
    public void race(Car otherCar) {
        if (otherCar instanceof ShelbyMustang && (this.getFuelLevel() > 50 && otherCar.getFuelLevel() > 50)) {
            this.setFuelLevel(this.getFuelLevel() - 25);
            otherCar.setFuelLevel(otherCar.getFuelLevel() - 25);
            if (this.getHorsepower() > otherCar.getHorsepower()) {
                System.out.printf("%s won against %s", this.getName(), otherCar.getName());
                this.setRacesWon(this.getRacesWon() + 1);
            } else if (otherCar.getHorsepower() > this.getHorsepower()) {
                System.out.printf("%s won against %s", otherCar.getName(), this.getName());
                ((ShelbyMustang) otherCar).setRacesWon(((ShelbyMustang) otherCar).getRacesWon() + 1);
            } else if (otherCar.getHorsepower() == this.getHorsepower()) {
                if (this.racesWon > ((ShelbyMustang) otherCar).getRacesWon()) {
                    System.out.printf("%s won against %s", this.getName(), otherCar.getName());
                    this.setRacesWon(this.getRacesWon() + 1);
                } else if (this.racesWon < ((ShelbyMustang) otherCar).getRacesWon()) {
                    System.out.printf("%s won against %s", otherCar.getName(), this.getName());
                    ((ShelbyMustang) otherCar).setRacesWon(((ShelbyMustang) otherCar).getRacesWon() + 1);
                } else if (this.racesWon == ((ShelbyMustang) otherCar).getRacesWon()) {
                    System.out.printf("%s tied with %s", otherCar.getName(), this.getName());
                }
            }
        } else {
            System.out.printf("%s could not race %s", otherCar.getName(), this.getName());
        }
    }

    /**
     * races won getter.
     * @return int of races won
     */
    public int getRacesWon() {
        return racesWon;
    }

    /**
     * races won setter.
     * @param racesWon int of races won
     */
    public void setRacesWon(int racesWon) {
        this.racesWon = racesWon;
    }
}
