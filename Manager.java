package com.company;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Manager extends Person {
    private String filename;

    public Manager(int id, String name) {
        super(id, name);
    }

    @Override
    public void storePerson(Person p) {
        Main.aLp.add(p);
        Main.totalEmployees++;
    }

    @Override
    public void removePerson(Person p, int id) {
        for(Iterator<Person> itr = Main.aLp.iterator(); itr.hasNext(); ) {//Used iterator here to fix Concurrent modification error
            Person item = itr.next();
            if(id == item.getId()) {
                itr.remove();
                Main.totalEmployees--;
            }

        }
    }

    @Override
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
        for (Person q : Main.aLp){
            System.out.println(q.toString());
        }

    }
    @Override
    public void importData(String filename) {
        try {
            System.out.println();
            File in = new File(filename);
            Scanner input = new Scanner(in);
            input.useDelimiter("[;\n]");
            while (input.hasNext()) {
                if (input.hasNext("Employee")) {
                    input.next();
                    String id = input.next();
                    String id2 = id.replace("I", "").replace("D", "");
                    String name = input.next();
                    String birthYear = input.next();
                    String salary = input.next();
                    Person importedEmployee = new Employee(Integer.parseInt(id2), name, Integer.parseInt(birthYear), (2020 - Integer.parseInt(birthYear)), Integer.parseInt(salary),Integer.parseInt(salary));
                    storePerson(importedEmployee);
                    input.nextLine();
                }
                else if (input.hasNext("Customer")) {
                    input.next();
                    String id = input.next();
                    String id2 = id.replace("I", "").replace("D", "");
                    String name = input.next();
                    Person importedCustomer = new Customer(Integer.parseInt(id2), name, "regular" , false, 0,0, false,0);
                    storePerson(importedCustomer);
                    input.nextLine();
                }
                else if (input.hasNext("Game")) {
                    input.next();
                    String id = input.next();
                    String id2 = id.replace("I", "").replace("D", "");
                    String name = input.next();
                    String genre = input.next();
                    String dailyRent = input.next();
                    Media importedGame = new Game(Integer.parseInt(id2), name, genre, Integer.parseInt(dailyRent),true,0,0,"",0);
                    Main.aLm.add(importedGame);
                    input.nextLine();
                }
                else if (input.hasNext("Album")) {
                    input.next();
                    String id = input.next();
                    String id2 = id.replace("I", "").replace("D", "");
                    String name = input.next();
                    String artist = input.next();
                    String release = input.next();
                    String dailyRent = input.next();
                    Media importedAlbum = new Album(Integer.parseInt(id2), name, artist, Integer.parseInt(release), Integer.parseInt(dailyRent),0,true,0,"",0);
                    Main.aLm.add(importedAlbum);
                    input.nextLine();
                }else{
                    System.out.println("Importing Data.");
                    break;
                }
            }
            System.out.println("Data successfully imported.");
            System.out.println();
        }catch(FileNotFoundException exception ){
            System.out.println(exception.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
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
    protected void setCustomerUpgrade(boolean b) {

    }
}
