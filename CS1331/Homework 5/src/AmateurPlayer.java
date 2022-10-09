/**
 * AmateurPlayer class.
 * @author Joshua Wainwright
 * @version 1
 */
public class AmateurPlayer extends Player {
    /**
     * 3 arg constructor.
     * @param name String name
     * @param points double points
     * @param trophies int trophies
     */
    public AmateurPlayer(String name, double points, int trophies) {
        super(name, points, trophies);
    }

    /**
     * Implementation of calculate skill level abstract method.
     * @return double of skill level
     */
    @Override
    public double calculateSkillLevel() {
        double skillPoints = getPoints() + 0.5 * (double) getTrophies();
        return skillPoints;
    }
}
