/**
 * Monster Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class Monster implements Comparable<Monster> {
    private String name;
    private int spookiness;

    /**
     * 2 arg constructor.
     * @param name String of name
     * @param spookiness int of spookiness level
     */
    public Monster(String name, int spookiness) {
        this.name = name;
        this.spookiness = spookiness;
    }

    /**
     * compareTo method that overrides comparable.
     * @param m Monster object
     * @return number representing boolean relationship
     */
    @Override
    public int compareTo(Monster m) {
        if (spookiness > m.spookiness) {
            return 1;
        } else if (spookiness < m.spookiness) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * toString method.
     * @return String representation of object
     */
    public String toString() {
        String tempString = "%s has a spook rating of %d";
        return String.format(tempString, name, spookiness);
    }

    /**
     * Spookiness getter.
     * @return int of spookiness
     */
    public int getSpookiness() {
        return spookiness;
    }

    /**
     * Name getter.
     * @return String of name.
     */
    public String getName() {
        return name;
    }
}
