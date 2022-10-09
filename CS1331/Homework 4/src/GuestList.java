import java.util.ArrayList;

/**
 * GuestList Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class GuestList {
    private ArrayList<Monster> guests;
    private DancingMonster bestDancer;

    /**
     * 1 arg constructor.
     * @param guests ArrayList of guests
     */
    public GuestList(ArrayList<Monster> guests) {
        this.guests = guests;
        this.bestDancer = findBestDancer(guests);
    }

    /**
     * 0 arg constructor.
     */
    public GuestList() {
        this(new ArrayList<Monster>());
    }

    /**
     * findBestDancer Method.
     * @param guest ArrayList of guests
     * @return best dancer in guest
     */
    private DancingMonster findBestDancer(ArrayList<Monster> guest) {
        ArrayList<Monster> guestClone = new ArrayList<Monster>();
        for (int i = 0; i < guest.size(); i++) {
            guestClone.add(guest.get(i));
        }
        if (hasDancingMonster(guestClone)) {
            for (int i = 0; i < (guestClone.size() - 1); i++) {
                for (int j = 0; j < (guestClone.size() - 1); j++) {
                    if (((Monster) (guestClone.get(j))).compareTo((Monster) (guestClone.get(j + 1))) == -1) {
                        Monster temp1 = guestClone.get(j);
                        Monster temp2 = guestClone.get(j + 1);
                        guestClone.set(j, temp2);
                        guestClone.set(j + 1, temp1);
                    }
                }
            }
            for (int i = 0; i < guestClone.size(); i++) {
                if (guestClone.get(i) instanceof DancingMonster) {
                    return (DancingMonster) guestClone.get(i);
                }
            }
        }
        return null;
    }

    /**
     * hasDancingMonster method.
     * @param guest ArrayList of guests.
     * @return boolean representing if guest contains DancingMonster object.
     */
    private boolean hasDancingMonster(ArrayList<Monster> guest) {
        for (int i = 0; i < guest.size(); i++) {
            if (guest.get(i) instanceof DancingMonster) {
                return true;
            }
        }
        return false;
    }

    /**
     * addGuest method.
     * @param m Monster object to be added to the end of guests ArrayList
     */
    public void addGuest(Monster m) {
        guests.add(m);
        bestDancer = findBestDancer(guests);
    }

    /**
     * toString method.
     * @return String representation of object
     */
    public String toString() {
        String tempString = "";
        for (int i = 0; i < guests.size(); i++) {
            if (i == (guests.size() - 1)) {
                tempString += guests.get(i).getName();
            } else {
                tempString += guests.get(i).getName() + ", ";
            }
        }
        return tempString;
    }

    /**
     * danceBattle method that simulates dance battle between two danceable guests.
     * @param m1 monster object
     * @param m2 monster object
     */
    public void danceBattle(Monster m1, Monster m2) {
        System.out.println(m1);
        System.out.println(m2);
        String wonString = "%s won the dance battle!";
        String tieString = "And this dance battle is a tie!";
        if (m1 instanceof DancingMonster && !(m2 instanceof DancingMonster)) {
            System.out.println(String.format(wonString, m1.getName()));
            ((DancingMonster) m1).wonDance();
        } else if (m2 instanceof DancingMonster && !(m1 instanceof DancingMonster)) {
            System.out.println(String.format(wonString, m2.getName()));
            ((DancingMonster) m2).wonDance();
        } else if (!(m1 instanceof DancingMonster) && !(m2 instanceof DancingMonster)) {
            System.out.println(tieString);
        } else {
            int m1Dance = ((DancingMonster) m1).dance();
            int m2Dance = ((DancingMonster) m2).dance();
            if (m1Dance > m2Dance) {
                System.out.println(String.format(wonString, m1.getName()));
                ((DancingMonster) m1).wonDance();
            } else if (m1Dance < m2Dance) {
                System.out.println(String.format(wonString, m2.getName()));
                ((DancingMonster) m2).wonDance();
            } else {
                System.out.println(tieString);
            }
        }
    }
}
