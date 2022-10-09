import java.util.ArrayList;

/**
 * GrandSlam class.
 * @author Joshua Wainwright
 * @version 1
 */
public class GrandSlam {
    private String name;
    private ArrayList<ProfessionalPlayer> leaderboard;

    /**
     * 2 arg constructor.
     * @param name String of name
     * @param leaderboard arraylist of leaderboard
     */
    public GrandSlam(String name, ArrayList<ProfessionalPlayer> leaderboard) {
        this.name = name;
        this.leaderboard = leaderboard;
        createLeaderboard();
    }

    /**
     * createLeaderBoard method that sorts passed in leaderboard.
     */
    public void createLeaderboard() {
        int j = 0;
        for (int i = 0; i < leaderboard.size(); i++) {
            ProfessionalPlayer maxVal = leaderboard.get(j);
            int maxInd = j;
            for (int e = j; e < leaderboard.size(); e++) {
                if (leaderboard.get(e).compareTo(maxVal) == 1) {
                    maxVal = leaderboard.get(e);
                    maxInd = e;
                }
            }
            leaderboard.set(maxInd, leaderboard.get(j));
            leaderboard.set(j, maxVal);
            j += 1;
        }
    }

    /**
     * findPlayer method that searches for passed in player.
     * @param p ProfessionalPlayer object
     * @return index of searched ProfessionalPlayer
     */
    public int findPlayer(ProfessionalPlayer p) {
        int bot = 0;
        int top = leaderboard.size() - 1;
        while (bot <= top) {
            int mid = (top + bot) / 2;
            if (leaderboard.get(mid).compareTo(p) == 0) {
                if (leaderboard.get(mid).equals(p)) {
                    return mid;
                } else {
                    return -1;
                }
            } else if (leaderboard.get(mid).compareTo(p) == -1) {
                top = mid - 1;
            } else if (leaderboard.get(mid).compareTo(p) == 1) {
                bot = mid + 1;
            }
        }
        return -1;
    }

    /**
     * toString method.
     * @return String representation of object
     */
    public String toString() {
        String temp = "Welcome to the %s tornament! the current leaderboard is: %s";
        return String.format(temp, name, leaderboard.toString());
    }
}
