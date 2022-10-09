import java.util.ArrayList;
public class Driver {
    public static void main(String[] args){
        DancingMonster m1 = new DancingMonster("m1", 5, "scoot");
        DancingMonster m2 = new DancingMonster("m2", 8, "shoot");
        ArrayList<Monster> guestList = new ArrayList<Monster>();
        guestList.add(m1);
        guestList.add(m2);
        GuestList g1 = new GuestList(guestList);
        DancingMonster m3 = new DancingMonster("m2", 8, "shoot");
    }
}
