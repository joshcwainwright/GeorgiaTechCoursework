/**
 * ProfessionalPlayer class.
 * @author Joshua Wainwright
 * @version 1
 */
public class ProfessionalPlayer extends Player {
    private boolean isSeeded;

    /**
     * 4 arg constructor.
     * @param name String name
     * @param points double points
     * @param trophies int trophies
     * @param isSeeded boolean isSeeded
     */
    public ProfessionalPlayer(String name, double points, int trophies, boolean isSeeded) {
        super(name, points, trophies);
        this.isSeeded = isSeeded;
    }

    /**
     * implementation of calculate skill level abstract method.
     * @return double of skill level
     */
    @Override
    public double calculateSkillLevel() {
        double skillPoints = getPoints() + 1.5 * (double) getTrophies();
        if (isSeeded) {
            skillPoints *= 2;
        }
        return skillPoints;
    }

    /**
     * Ovveride of object equals method.
     * @param p object of p
     * @return boolean representing equal or not equal
     */
    @Override
    public boolean equals(Object p) {
        if (!(p instanceof ProfessionalPlayer) || p == null) {
            return false;
        }
        return (isSeeded == ((ProfessionalPlayer) p).isSeeded && super.equals(p));
    }
}
