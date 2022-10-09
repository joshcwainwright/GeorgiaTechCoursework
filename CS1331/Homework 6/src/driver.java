import java.util.ArrayList;

public class driver {
    public static void main(String[] args) {
        TravelActivity n1 = new TravelActivity("jump",10);
        TravelActivity n2 = new TravelActivity("hop", 20);
        TravelActivity n4 = new TravelActivity("hop", 10);
        TravelActivity n3 = new TravelActivity("Skip", 30.50);
        ArrayList<TravelActivity> activities = new ArrayList<TravelActivity>();
        activities.add(n2);
        activities.add(n1);
        activities.add(n3);
        activities.add(n4);
        int i = UndercoverTourist.calculateNumActivities(activities, 0, 4);
        System.out.println(i);
    }
}
