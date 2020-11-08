package com.company;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Game extends Media {
    private String gameGenre;
    private int profit;

    public Game(int id, String name, String genre, int dailyRent, boolean rentStatus, double userRating, int timesRented, String review, double profitPerItem) {
        super(id, name, dailyRent, userRating, rentStatus, timesRented, review, profitPerItem);
        if(name.isEmpty()){
            throw new NameCannotBeEmptyException("Game name field cannot be empty.");
        }else {this.setName(name);}
        if(dailyRent < 0){
            throw  new RentCannotBeNegativeException("Game Daily rent cannot be negative value.");
        }else{this.setDailyRent(dailyRent);}
        this.setGameGenre(genre);

    }

    public boolean genreComparisonGame(String gameGenre) {
        for (Media gm : Main.aLm) {
            if (gameGenre.equalsIgnoreCase(gm.getGameGenre())){
                System.out.println(gm.toString());
                return true;
            }
        }
        return false;
    }
    public String toString() {
        return getId() + " : " + getName() + " (" + getGameGenre() + "). " + getDailyRent() + " : " + getRentStatus();
    }
    private static void showTotalProfit() {
        System.out.println("Total profit " + Main.totalProfit + " SEK.");
    }
//Can maybe delete all overrides here

    @Override
    public void storeHistory(int id, int daysRented, int cId) throws IOException {
        for (Media gm : Main.aLm){
            if (id == gm.getId()){
                for (Person cust : Main.aLp){
                    if(cId == cust.getId()){
                        if (cust.getCustomerMembership().equalsIgnoreCase("silver")){
                            File letter = new File("TransactionHistory.txt");
                            FileWriter fw = new FileWriter(letter,true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(cId +";"+id+";"+ gm.getName() +";" +(returnDailyFee(id) * daysRented*0.9));
                            pw.close();
                        }if (cust.getCustomerMembership().equalsIgnoreCase("gold")){
                            File letter = new File("TransactionHistory.txt");
                            FileWriter fw = new FileWriter(letter,true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(cId +";"+id+";"+ gm.getName() +";" +(returnDailyFee(id) * daysRented*0.85));
                            pw.close();
                        }if(cust.getCustomerMembership().equalsIgnoreCase("premium")){
                            File letter = new File("TransactionHistory.txt");
                            FileWriter fw = new FileWriter(letter,true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(cId +";"+id+";"+ gm.getName() +";" +(returnDailyFee(id) * daysRented*0.75));
                            pw.close();
                        }else{
                            File letter = new File("TransactionHistory.txt");
                            FileWriter fw = new FileWriter(letter,true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(cId +";"+id+";"+ gm.getName() +";" +(returnDailyFee(id) * daysRented));
                            pw.close();

                        }
                    }
                }
            }
        }
    }

    @Override
    public void storeMedia(Media g) {
        Main.aLm.add(g);
        Main.totalMedia++;
    }

    @Override
    public  void removeMedia( Media g , int id) {
        for(Iterator<Media> itr = Main.aLm.iterator(); itr.hasNext(); ) {//Used iterator here to fix Concurrent modification error
            Media item = itr.next();
            if(id == item.getId()) {
                itr.remove();
            }
        }
    }

    @Override
    public double returnDailyFee(int id) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                return gm.getDailyRent();
            }
        }
        return 0;
    }

    @Override
    public boolean idComparison(int id) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public  boolean compareRentStatus(int id, boolean rentStatus) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                if (rentStatus == gm.getRentStatus()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void rentMedia(int id) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                gm.setRentStatus(false);
                System.out.println();
            }
        }

    }

    @Override
    public  void returnMedia(int id) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                gm.setRentStatus(true);
                System.out.println();
            }
        }
    }

    @Override
    public  void userRatingMedia(int id, double userRating) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                gm.setUserRating(userRating);
                gm.setTotalUserRating(gm.getTotalUserRating() + gm.getUserRating());
            }
        }
    }

    @Override
    public void increaseTimesRented(int id) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                gm.setTimesRented(gm.getTimesRented() + 1);
            }
        }
    }

    @Override
    public void averageUserRating(int id) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                gm.setUserRating(gm.getTotalUserRating() / gm.getTimesRented());
            }
        }
    }

    @Override
    public void viewAverageUserRatings(int id) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {
                System.out.println("Average user rating for " + id + " is " + gm.getUserRating() + " out of 5.");
                System.out.println();
            }
        }
    }

    @Override
    public void ratingsComparison() {
        for (Media gm : Main.aLm) {
            System.out.toString();
       }
    }

    @Override
    public void profitsComparison() {
        for (Media gm : Main.aLm) {
            System.out.toString();
        }
    }

    @Override
    public void rentalsComparison() {
        for (Media gm : Main.aLm) {
            System.out.toString();
        }
    }

    @Override
    public  void userReview(int id, String review) {
        for (Media gm : Main.aLm) {
            if (id == gm.getId()) {//Maybe delete all these extra checks
                gm.setReview(review);
            }
        }
    }

    @Override
    public void storeReceipt(int id, int daysRented, int customerId) throws IOException {
        for (Media gm : Main.aLm){
            if(id == gm.getId()){
                File letter = new File("TransactionOfId" + id + ".txt");
                FileWriter fw = new FileWriter(letter);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("Game  " + id + ".");
                pw.println("Rented and returned by Customer: " + customerId + ".");
                pw.println("Rented for " + daysRented + " ");
                pw.println("Rent expense " + returnDailyFee(id) * daysRented + " SEK.");//Better ??
                pw.println("Rented a total of " + gm.getTimesRented() + " times.");
                pw.println("User rating: " + gm.getUserRating() + ".");
                pw.println("User review: " + gm.getReview() + ".");
                pw.close();
            }

        }
    }

    @Override
    public void openReceipt(int id) {
        try {
            System.out.println();
            String filename = "TransactionOfId" + id + ".txt";
            File in = new File(filename);
            Scanner input = new Scanner(in);
            while (input.hasNext())//Displayed only first letter(character)
            {
                System.out.println(input.nextLine());
            }
            System.out.println();
        }catch(FileNotFoundException exception ){
        System.out.println(exception.getMessage());
        }
    }

    @Override
    public void profitPerMedia(int id, double daysRented) {
        for(Media gm : Main.aLm){
            if(id == gm.getId()){
                double toPay = gm.getDailyRent()*daysRented;
                gm.setProfitPerItem(gm.getProfitPerItem()+toPay);
            }
        }
    }
    @Override
    public void viewAll() {
        for(Media gm : Main.aLm){
            System.out.println(gm.toString());
        }
    }
        public void setProfit(int profit) {
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    @Override
    protected int getAlbumRelease() {
        return getAlbumRelease();
    }

    @Override
    public int compareTo(Media o) {
        return 0;
    }
}
