/**
 * DancingMonster Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class DancingMonster extends Monster implements Danceable {
    private String danceMove;
    private int dancesWon;

    /**
     * 3 arg constructor.
     * @param name String of name
     * @param spookiness int of spookiness
     * @param danceMove String of danceMove
     */
    public DancingMonster(String name, int spookiness, String danceMove) {
        super(name, spookiness);
        this.danceMove = danceMove;
    }

    /**
     * dance method.
     * @return random int that represents value of dance
     */
    @Override
    public int dance() {
        String tempString = "%s does the %s";
        System.out.println(String.format(tempString, getName(), danceMove));
        int randomInt = (int) (Math.random() * (getSpookiness() + 1));
        return randomInt;
    }

    /**
     * wonDance method (Incraments dancesWon by 1).
     */
    @Override
    public void wonDance() {
        dancesWon += 1;
    }

    /**
     * toString method.
     * @return String representation of object
     */
    public String toString() {
        String tempString = "%s has a spook rating of %d and has won %d dances thus far";
        return String.format(tempString, getName(), getSpookiness(), dancesWon);
    }
}
