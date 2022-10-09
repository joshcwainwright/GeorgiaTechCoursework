/**
 * Player class.
 * @author Joshua Wainwright
 * @version 1
 */
public abstract class Player implements Comparable<Player> {
    private String name;
    private double points;
    private int trophies;

    /**
     * 3 arg constructor.
     * @param name String of name
     * @param points double of points
     * @param trophies int of trophies
     */
    public Player(String name, double points, int trophies) {
        this.name = name;
        this.points = points;
        this.trophies = trophies;
    }

    /**
     * method to calculate skill level abstract placeholder.
     * @return double of skill level
     */
    public abstract double calculateSkillLevel();

    /**
     * Override of compareTo from comparable.
     * @param p player object
     * @return int value of comparable
     */
    @Override
    public int compareTo(Player p) {
        if (calculateSkillLevel() > p.calculateSkillLevel()) {
            return 1;
        } else if (calculateSkillLevel() < p.calculateSkillLevel()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Override of object equals method.
     * @param p object of p
     * @return boolean representing if they are equal
     */
    @Override
    public boolean equals(Object p) {
        if (!(p instanceof Player) || p == null) {
            return false;
        }
        return (trophies == ((Player) p).trophies && points == ((Player) p).points && name.equals(((Player) p).name));
    }

    /**
     * toString method.
     * @return string representation of object
     */
    public String toString() {
        String tempString = "%s: %.1f points - %d trophies";
        return String.format(tempString, name, points, trophies);
    }

    /**
     * points getter.
     * @return double of points
     */
    public double getPoints() {
        return points;
    }

    /**
     * trophies getter.
     * @return int of trophies
     */
    public int getTrophies() {
        return trophies;
    }
}
