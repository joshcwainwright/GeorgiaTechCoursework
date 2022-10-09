/**
 * Geostationary Satellite Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class GeostationarySatellite extends Satellite {
    /**
     * Five argument contructor.
     * @param name String of name
     * @param catalogNumber int of catalog number
     * @param launchYear int of launch year
     * @param launchDay int of lauch day
     * @param inclination double of inclination
     */
    public GeostationarySatellite(String name, int catalogNumber, int launchYear, int launchDay, double inclination) {
        super(name, catalogNumber, launchYear, launchDay, inclination, 1.0);
    }
}
