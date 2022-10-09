import java.util.Scanner;

/**
 * Classified Satellite Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class ClassifiedSatellite extends Satellite {
    /**
     * Six argument constructor.
     * @param name String of name
     * @param catalogNumber int of catalog number
     * @param launchYear int of launch year
     * @param launchDay int of lauch day
     * @param inclination double of inclination
     * @param meanMotion double of meanMotion
     */
    public ClassifiedSatellite(String name, int catalogNumber, int launchYear, int launchDay, double inclination,
                               double meanMotion) {
        super(name, catalogNumber, launchYear, launchDay, inclination, meanMotion);
    }

    /**
     * Password method that checks if inputted password is correct.
     * @param input String of inputted password.
     * @return boolean true if password is correct, false if password is incorrect
     */
    public boolean password(String input) {
        String check = "f8ee89496da476b3849f4de45a4528b4";
        return check.equals(input);
    }

    /**
     * toString method.
     * @return string representation of objects (returns "INCORRECT PASSWORD" if password() fails)
     */
    @Override
    public String toString() {
        Scanner myScan = new Scanner(System.in);
        System.out.println("Password:");
        String input = myScan.nextLine();
        if (this.password(input)) {
            return super.toString();
        } else {
            String returnString = "INCORRECT PASSWORD";
            return returnString;
        }
    }

    /**
     * csv method.
     * @return csv formatted String representation of object (returns "INCORRECT PASSWORD" if password() fails)
     */
    @Override
    public String encodeCSV() {
        Scanner myScan = new Scanner(System.in);
        System.out.println("Password:");
        String input = myScan.nextLine();
        if (this.password(input)) {
            return super.encodeCSV();
        } else {
            String returnString = "INCORRECT PASSWORD";
            return returnString;
        }
    }
}
