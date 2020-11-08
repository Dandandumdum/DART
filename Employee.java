package com.company;

import java.util.Iterator;

class Employee extends Person{

    private int grossSalary;
    private int birthYear;
    private int age;
    private double netSalary;

    public Employee(int id, String name, int year, int age, int salary, double netSal)throws Exception{
        super(id, name);
        if(name.isEmpty()){
            throw new NameCannotBeEmptyException("Name field cannot be empty.");
        }else {this.setName(name);}
        this.setBirthYear(year);
        this.setGrossSalary(salary);
        this.setNetSalary(netSal);
        this.setAge(age);
    }


    public void setBirthYear(int birthYear){this.birthYear = birthYear; }
    public int getBirthYear(){return birthYear; }

    public void setGrossSalary(int grossSalary) { this.grossSalary = grossSalary; }
    public int getGrossSalary() { return grossSalary; }

    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
    public double getNetSalary() { return netSalary; }

    public void setAge(int age) { this.age = age; }
    public int getAge() { return age;}

    public String toString(){
        return getId() + " : " + getName() + " - "+ getBirthYear() + " (" + (getAge())+ "):  " + getGrossSalary() + " SEK.";
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


    public boolean idComparison(int id)
    { for (Person gId : Main.aLp) {
        if (id == gId.getId()) {
            return true;
        }
    }
        return false;

    }

    @Override
    public void viewAll() {
        for (Person p : Main.aLp){
            System.out.println(p.toString());
        }

    }

    @Override
    protected boolean getHasMail() {
        return false;
    }

    @Override
    protected void setHasMail(boolean b) {

    }

    @Override
    protected boolean getCustomerUpgrade() {
        return false;
    }

    @Override
    protected int getCustomerCredits() {
        return 0;
    }

    @Override
    protected String getCustomerMembership() {
        return null;
    }

    @Override
    protected void setCustomerCredits(int s) {

    }

    @Override
    protected void setCustomerUpgrade(boolean b) {

    }

    @Override
    public void importData(String filename) {

    }

    @Override
    protected void setCustomerMembership(String silver) {

    }

    @Override
    protected int getNumRented() {
        return 0;
    }

    @Override
    protected double getProfitPerCustomer() {
        return 0;
    }

    @Override
    protected void setProfitPerCustomer(double v) {

    }

    @Override
    protected void setNumRented(int i) {

    }
}
