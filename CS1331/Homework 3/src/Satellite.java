/**
 * Satellite Parent Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class Satellite {
    private String name;
    private int catalogNumber;
    private int launchYear;
    private int launchDay;
    private double inclination;
    private double meanMotion;

    /**
     *Six argument Constructor.
     * @param name String of name
     * @param catalogNumber int of catalog number
     * @param launchYear int of launch year
     * @param launchDay int of lauch day
     * @param inclination double of inclination
     * @param meanMotion double of meanMotion
     */
    public Satellite(String name, int catalogNumber, int launchYear, int launchDay, double inclination,
                     double meanMotion) {
        this.name = name;
        this.catalogNumber = catalogNumber;
        this.launchYear = launchYear;
        this.launchDay = launchDay;
        this.inclination = inclination;
        this.meanMotion = meanMotion;
    }

    /**
     * orbitTime helper method.
     * @return double of how long it takes for satellite to complete orbit
     */
    public double orbitTime() {
        double time = (24 * 60) / meanMotion;
        return time;
    }

    /**
     * toString method.
     * @return String representation of satellite object
     */
    public String toString() {
        String toString = "%s: #%d. Launched on day %d of %d. Inclination : %f degrees. %f orbits per day "
                + "- %f minutes per orbit.\n";
        return String.format(toString, name, catalogNumber, launchDay, launchYear, inclination, meanMotion,
                this.orbitTime());
    }

    /**
     * csv method.
     * @return csv formatted String representation of satellite object
     */
    public String encodeCSV() {
        return String.format("%s, %d, %d, %d, %f, %f, %f\n", name, catalogNumber, launchYear, launchDay,
                inclination, meanMotion, this.orbitTime());
    }

}
