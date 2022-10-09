import java.util.ArrayList;

/**
 * Masters class.
 * @author Joshua Wainwright
 * @version 1
 */
public class Masters {
    private String name;
    private ArrayList<Player> leaderboard;

    /**
     * 2 arg constructor.
     * @param name String of name
     * @param leaderboard arraylist of leaderboard
     */
    public Masters(String name, ArrayList<Player> leaderboard) {
        this.name = name;
        this.leaderboard = leaderboard;
    }

    /**
     * createLeaderBoard method that sorts inputted leaderboard.
     */
    public void createLeaderboard() {
        for (int i = 0; i < leaderboard.size(); i++) {
            int j = i;
            while (j > 0 && leaderboard.get(j - 1).compareTo(leaderboard.get(j)) == -1) {
                Player temp = leaderboard.get(j);
                leaderboard.set(j, leaderboard.get(j - 1));
                leaderboard.set(j - 1, temp);
                j -= 1;
            }
        }
    }

    /**
     * findPlayer method that finds passed in player.
     * @param p player object
     * @return index of searched player
     */
    public int findPlayer(Player p) {
        for (int i = 0; i < leaderboard.size(); i++) {
            if (p.equals(leaderboard.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * toString method.
     * @return string representation of object
     */
    public String toString() {
        String tempString = "Welcome to the %s tournament! The current leaderboard is %s";
        return String.format(tempString, name, leaderboard.toString());
    }
}
