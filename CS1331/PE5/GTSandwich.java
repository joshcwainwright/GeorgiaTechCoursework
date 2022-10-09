public class GTSandwich {
    private String bread;
    private String meat;
    private String[] extras;
    private int numMeat;
    private double price;
    private boolean hasSauce;
    private boolean makeCombo;
    public GTSandwich() {
        this("Rye", "Turkey", true);
    }
    public GTSandwich(String bread, String meat, boolean makeCombo) {
        this(bread, meat, new String[]{}, 4, 8.75, true, makeCombo);
    }
    public GTSandwich(String bread, String meat, String[] extras, int numMeat, double price, boolean hasSauce, boolean
        makeCombo) {
        this.bread = bread;
        this.meat = meat;
        this.extras = extras;
        this.numMeat = numMeat;
        this.price = price;
        this.hasSauce = hasSauce;
        this.makeCombo = makeCombo;
    }
    public String getBread() {
        return this.bread;
    }
    public String getMeat() {
        return this.meat;
    }
    public String[] getExtras() {
        return this.extras;
    }
    public int getNumMeat() {
        return this.numMeat;
    }
    public double getPrice() {
        return this.price;
    }
    public boolean getHasSauce() {
        return this.hasSauce;
    }
    public boolean getMakeCombo() {
        return this.makeCombo;
    }
    public void setBread(String bread) {
        this.bread = bread;
    }
    public void setMeat(String meat) {
        this.meat = meat;
    }
    public void setExtras(String[] extras) {
        this.extras = extras;
    }
    public void setNumMeat(int numMeat) {
        this.numMeat = numMeat;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setHasSauce(boolean hasSauce) {
        this.hasSauce = hasSauce;
    }
    public void setMakeCombo(boolean makeCombo) {
        if (!makeCombo && this.makeCombo) {
            this.price -= 2.0;
        } else if (makeCombo && !this.makeCombo) {
            this.price += 2.0;
        }
        this.makeCombo = makeCombo;
    }
    public void applyCoupon(String code) {
        if (code.equals("CS1331") && this.makeCombo) {
            this.price = this.price * 0.5;
            this.numMeat = this.numMeat * 2;
            System.out.println("Coupon Applied");
        } else {
            System.out.println("Coupon Not Applied");
        }
    }
    public void applyCoupon() {
        if (this.price >= 20.0) {
            this.price -= 5.0;
            if (!this.makeCombo) {
                this.makeCombo = true;
            }
            System.out.println("Discount Applied");
        } else {
            System.out.println("Discount Not Applied");
        }
    }
    public void printExtras() {
        if (this.extras.length == 0) {
            System.out.println("The customer wants no extras.");
        } else {
            String extraString = "The customer wants these extras:";
            for (int i = 0; i < this.extras.length; i++) {
                if (i == 0) {
                    extraString += " " + this.extras[i];
                } else {
                    extraString += ", " + this.extras[i];
                }
            }
            System.out.println(extraString);
        }
    }
    public String toString() {
        String returnString = "GT Sandwich that costs %.2f with %s, %s";
        if (this.extras.length != 0) {
            for (int i = 0; i < this.extras.length; i++) {
                returnString += ", " + this.extras[i];
            }
        }
        returnString += ", %d slices of meat, Suace: %b, Combo: %b.";
        return String.format(returnString, this.price, this.bread, this.meat, this.numMeat, this.hasSauce,
         this.makeCombo);
    }
    public static void main(String[] args) {
        GTSandwich houseSpecial = new GTSandwich("French", "Turkey", new String[]{"Banana Peppers", "Spinach",
            "Chipotle Mayo"}, 4, 10.75, true, true);
        GTSandwich javaSandwich = new GTSandwich();
        houseSpecial.applyCoupon("CS1331");
        javaSandwich.applyCoupon();
        houseSpecial.printExtras();
        System.out.println(houseSpecial);
        System.out.println(javaSandwich);
    }
}
