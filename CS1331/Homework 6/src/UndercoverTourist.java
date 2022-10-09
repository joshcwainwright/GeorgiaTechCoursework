import java.util.ArrayList;

/**
 * UndercoverTourist Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class UndercoverTourist {
    /**
     * calculateNumActivities method.
     * @param tList arraylist of activities
     * @param budget double of budget
     * @param purchased int of purchased activities
     * @return number of activities completed
     */

    public static int calculateNumActivities(ArrayList<TravelActivity> tList, double budget, int purchased) {
        if (tList.size() == 0) {
            return purchased;
        }
        int index = search(tList);
        if (tList.get(index).getPrice() <= budget) {
            budget -= tList.get(index).getPrice();
            tList.remove(index);
            return 1 + calculateNumActivities(tList, budget, purchased);
        }
        return purchased;
    }

    /**
     * search helper method.
     * @param tList arraylist of activities
     * @return lowsest price index
     */
    public static int search(ArrayList<TravelActivity> tList) {
        int minIndex = 0;
        for (int i = 0; i < tList.size(); i++) {
            if (tList.get(minIndex) == null) {
                continue;
            } else if (tList.get(i).getPrice() < tList.get(minIndex).getPrice()) {
                minIndex = i;
            }
        }
        return minIndex;
    }

}
