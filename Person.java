package com.company;

import java.util.Comparator;

public abstract class Person {
    private int id;
    private String name;

    public Person(int id, String name){
        this.setId(id);
        this.setName(name);
    }
    public static Comparator<Person> ProfitsComparator = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            double Profits1 = o1.getProfitPerCustomer();
            double Profits2 = o2.getProfitPerCustomer();
            return (int) ((int) Profits2 - Profits1);
        }
    };
    public String CustomerProfitsComparison(){
        return Main.aLp.stream().max(Comparator.comparing(Person :: getProfitPerCustomer)).get().toString();
    }
    public abstract void storePerson(Person p);
    public abstract void removePerson(Person p, int id);
    public abstract boolean idComparison(int id);
    public abstract void viewAll();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //protected abstract boolean getHasMail();

    //protected abstract void setHasMail(boolean b);

    //protected abstract boolean getCustomerUpgrade();

    protected abstract int getCustomerCredits();

    protected abstract String getCustomerMembership();

    protected abstract void setCustomerCredits(int s);

   // protected abstract void setCustomerUpgrade(boolean b);

    protected abstract void setCustomerMembership(String silver);

    protected abstract int getNumRented();

    protected abstract double getProfitPerCustomer();

    protected abstract void setProfitPerCustomer(double v);

    protected abstract void setNumRented(int i);

    protected abstract boolean getHasMail();

    protected abstract void setHasMail(boolean b);

    protected abstract boolean getCustomerUpgrade();

    protected abstract void setCustomerUpgrade(boolean b);

    public abstract void importData(String filename);
}
