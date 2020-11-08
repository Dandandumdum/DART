package com.company;

import java.util.Iterator;

public class Customer extends Person{ //Change how objects are stored and manipulated
    private String customerMembership;
    private boolean customerUpgrade;
    private int customerCredits = 0;
    private boolean hasMail = false;
    private int numRented =0;
    private double profitPerCustomer;

    public Customer(int Id, String name, String membership, boolean upgrade, int credits, int rentals, boolean hasMail, double profitPerCustomer) throws Exception{
        super(Id, name);
        if(name.isEmpty()){
            throw new NameCannotBeEmptyException("Name field cannot be empty.");
        }else {this.setName(name);}
        this.setCustomerMembership(membership);
        this.setCustomerUpgrade(upgrade);
        this.setCustomerCredits(credits);
        this.setNumRented(rentals);
        this.setHasMail(hasMail);
        this.setProfitPerCustomer(profitPerCustomer);

    }
    public void viewAllCustomers() {
        for (Person cust : Main.aLp) {
            System.out.println(cust.getId() + " : " + cust.getName() + ". Membership level: " + cust.getCustomerMembership());
        }
    }

     boolean unreadMail(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                if (cust.getHasMail()) {
                    System.out.println("You have new Mail!");
                    return true;
                }
            }
        }
        return false;
    }

    void hasMailTrue(int Id) {
        for (Person cust : Main.aLp) {
            cust.setHasMail(true);
        }
    }

     void hasMailFalse(int Id) {
        for (Person cust : Main.aLp) {
            cust.setHasMail(false);
        }
    }

     void getUpgradeList(boolean customerUpgrade) {
        for (Person cust : Main.aLp) {
            if (customerUpgrade == cust.getCustomerUpgrade()) {
                System.out.println(cust.toString());
                System.out.println();
                return;
            } else {
                System.out.println("No customers currently wish to upgrade.");
                System.out.println();
            }
        }
    }

   void viewCredits(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                System.out.println("Your current Credit total is: " + cust.getCustomerCredits() + ".");
                System.out.println();
                return;
            } else {
                System.out.println("No customer with matching Id found.");
            }
        }
    }

    boolean customerCredits(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                if ((cust.getCustomerCredits()) > 5) {
                    return true;
                }
            }
        }
        return false;
    }

    public Customer paymentConditionsCredits(int Id) {//Delete later, integrated into payment conditions member
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                if (cust.getCustomerMembership().equalsIgnoreCase("silver")) {
                    //g.aLA. = (daysRented*g.aLA.getDailyFee)*0.9;
                    // System.out.println("Total price to pay after discount: " + Media.profit);
                    cust.setCustomerCredits(cust.getCustomerCredits() + 1);
                    System.out.println("Your current Credit total is: " + cust.getCustomerCredits() + ".");
                    System.out.println();

                } else if (cust.getCustomerMembership().equalsIgnoreCase("gold")) {
                    cust.setCustomerCredits(cust.getCustomerCredits() + 2);
                    System.out.println("Your current Credit total is: " + cust.getCustomerCredits() + ".");
                    System.out.println();
                } else if (cust.getCustomerMembership().equalsIgnoreCase("premium")) {
                    cust.setCustomerCredits(cust.getCustomerCredits() + 3);
                    System.out.println("Your current Credit total is: " + cust.getCustomerCredits() + ".");
                    System.out.println();
                } else {
                    //g.profit = (daysRented*getDailyFee);
                    // System.out.println("Total price to pay: " + Media.profit);
                }
                return null;
            } else {
                System.out.println("No customer with matching Id found.");
            }
        }
        return null;
    }

    void viewMembership(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                System.out.println("Your Membership status is: " + cust.getCustomerMembership() + ".");
                System.out.println();
                return;
            } else {
                System.out.println("No customer with matching Id found.");
            }
        }
    }

     void requestUpgrade(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                cust.setCustomerUpgrade(true);
                return;
            } else {
                System.out.println("No customer with matching Id found.");
            }
        }
    }

    void employeeUpgrade(int Id) {
        for (Person iD : Main.aLp) {
            if (Id == iD.getId()) {
                if (iD.getCustomerMembership().equalsIgnoreCase("regular")) {
                    iD.setCustomerMembership("silver");
                    System.out.println("Customer upgraded to Silver membership status");
                    System.out.println();
                    iD.setCustomerUpgrade(false);

                } else if (iD.getCustomerMembership().equalsIgnoreCase("silver")) {
                    iD.setCustomerMembership("gold");
                    System.out.println("Customer upgraded to Gold membership status");
                    System.out.println();
                    iD.setCustomerUpgrade(false);

                } else if (iD.getCustomerMembership().equalsIgnoreCase("gold")) {
                    iD.setCustomerMembership("premium");
                    System.out.println("Customer upgraded to Premium membership status");
                    System.out.println();
                    iD.setCustomerUpgrade(false);

                } else if (iD.getCustomerMembership().equalsIgnoreCase("premium")) {
                    System.out.println("Customer membership already at maximum level.");
                    System.out.println();
                    iD.setCustomerUpgrade(false);
                }
                return;
            } else {
                System.out.println("No customer with that Id found.");
                System.out.println();
            }
        }
    }

    void increaseNumRented(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                cust.setNumRented(cust.getNumRented() + 1);
            }
        }
    }

    void decreaseNumRented(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                cust.setNumRented(cust.getNumRented() - 1);
            }
        }
    }

     boolean checkNumRented(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                if (cust.getCustomerMembership().equalsIgnoreCase("silver")) {
                    if (cust.getNumRented() <= 3) {
                        return true;
                    } else {
                        System.out.println("You have rented too many items. The maximum number a Silver member can rent is 3.\nYou currently have rented  " + cust.getNumRented() + " items.");
                        return false;
                    }
                } else if (cust.getCustomerMembership().equalsIgnoreCase("gold")) {
                    if (cust.getNumRented() <= 5) {
                        return true;
                    } else {
                        System.out.println("You have rented too many items. The maximum number a Gold member can rent is 5.\n You currently have rented  " + cust.getNumRented() + " items.");
                        return false;
                    }
                } else if (cust.getCustomerMembership().equalsIgnoreCase("premium")) {
                    if (cust.getNumRented() <= 7) {
                        return true;
                    } else {
                        System.out.println("You have rented too many items. The maximum number a Premium member can rent is 7.\n You currently have rented  " + cust.getNumRented() + " items.");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void payWithCredits(int Id) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {
                cust.setCustomerCredits(0);
            }
        }
    }

    void paymentConditionsMember(int Id, int daysRented, double dailyRent) {
        for (Person cust : Main.aLp) {
            if (Id == cust.getId()) {

                if (cust.getCustomerMembership().equalsIgnoreCase("silver")) {
                    cust.setCustomerCredits(cust.getCustomerCredits() + 1);
                    double toPay = (daysRented * dailyRent) * 0.9;
                    cust.setProfitPerCustomer(cust.getProfitPerCustomer() + toPay);
                    Main.totalProfitAlbum = Main.totalProfitAlbum + toPay;
                    System.out.println("Total price to pay after discount: " + toPay + " SEK.");
                    System.out.println("Your current Credit total is: " + cust.getCustomerCredits() + ".");
                    System.out.println();

                } else if (cust.getCustomerMembership().equalsIgnoreCase("gold")) {
                    cust.setCustomerCredits(cust.getCustomerCredits() + 2);
                    double toPay = (daysRented * dailyRent) * 0.85;
                    cust.setProfitPerCustomer(cust.getProfitPerCustomer() + toPay);
                    Main.totalProfitAlbum = Main.totalProfitAlbum + toPay;
                    System.out.println("Total price to pay after discount: " + toPay + " SEK.");
                    System.out.println("Your current Credit total is: " + cust.getCustomerCredits() + ".");
                    System.out.println();

                } else if (cust.getCustomerMembership().equalsIgnoreCase("premium")) {
                    cust.setCustomerCredits(cust.getCustomerCredits() + 3);
                    double toPay = (daysRented * dailyRent) * 0.75;
                    cust.setProfitPerCustomer(cust.getProfitPerCustomer() + toPay);
                    Main.totalProfitAlbum = Main.totalProfitAlbum + toPay;
                    System.out.println("Total price to pay after discount: " + toPay + " SEK.");
                    System.out.println("Your current Credit total is: " + cust.getCustomerCredits() + ".");
                    System.out.println();
                } else {
                    double toPay = (daysRented * dailyRent);
                    cust.setProfitPerCustomer(cust.getProfitPerCustomer() + toPay);
                    Main.totalProfitAlbum = Main.totalProfitAlbum + toPay;
                    System.out.println("Total price to pay: " + toPay + " SEK.");
                    System.out.println();
                }
                return;
            }
        }
    }

     void paymentConditionsNonMember(int daysRented, double dailyRent) {
        double toPay = (daysRented * dailyRent);
        Main.totalProfitAlbum = Main.totalProfitAlbum + toPay;
        System.out.println("Total price to pay: " + toPay + " SEK.");
        System.out.println();
    }

    public String toString(){
        return getId() + " : " + getName() + ". Membership level: " + getCustomerMembership();
    }

    public void setCustomerMembership(String customerMembership) { this.customerMembership = customerMembership; }
    public String getCustomerMembership() { return customerMembership; }

    public void setCustomerUpgrade(boolean customerUpgrade) { this.customerUpgrade = customerUpgrade; }

    @Override
    public void importData(String filename) {

    }

    public boolean getCustomerUpgrade() { return customerUpgrade; }

    public void setCustomerCredits(int customerCredits) { this.customerCredits = customerCredits; }

    public int getCustomerCredits() { return customerCredits; }

    public void setHasMail(boolean hasMail) {
        this.hasMail = hasMail;
    }
    public boolean getHasMail() {
        return hasMail;
    }

    public void setNumRented(int numRented) {
        this.numRented = numRented;
    }
    public int getNumRented() {
        return numRented;
    }

    public double getProfitPerCustomer() {
        return profitPerCustomer;
    }
    public void setProfitPerCustomer(double profitPerCustomer) {
        this.profitPerCustomer = profitPerCustomer;
    }

    @Override
    public void storePerson(Person p) {
        Main.aLp.add(p);
        Main.totalCustomers++;
    }

    @Override
    public void removePerson(Person p, int id) {
        for(Iterator<Person> itr = Main.aLp.iterator(); itr.hasNext(); ) {//Used iterator here to fix Concurrent modification error
            Person item = itr.next();
            if(id == item.getId()) {
                itr.remove();
            }
        }
    }


    public boolean idComparison(int id) {
            for (Person cust : Main.aLp) {
                if (id == cust.getId()) {
                    return true;
                }
            }
            return false;
    }

    @Override
    public void viewAll() {
        for (Person p : Main.aLp){
            System.out.print(p.toString());
        }

    }
}
