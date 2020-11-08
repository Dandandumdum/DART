package com.company;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Album extends Media{//Have comparator stuff match games and be more dependent on Media.
    private String albumArtist;
    private int albumRelease;
    private double profitAlbum;
    private double totalUserRating;
    private double profitPerItem;


    public Album(int id, String name, String artist, int release, double dailyRent, double userRating, Boolean rentStatus, int timesRented, String review, double profitPerItem) {
        super(id, name, dailyRent, userRating, rentStatus, timesRented, review, profitPerItem);
        if(name.isEmpty()){
            throw new NameCannotBeEmptyException("Album name field cannot be empty.");
        }else {this.setName(name);}
        if(dailyRent < 0){
            throw  new RentCannotBeNegativeException("Album Daily rent cannot be negative value.");
        }else{this.setDailyRent(dailyRent);}
        this.setAlbumArtist(artist);
        this.setAlbumRelease(release);
    }

    public  boolean releaseComparisonAlbum(int albumRelease) {
        for (Media gId : Main.aLm) {
            if (albumRelease == gId.getAlbumRelease()) {
                System.out.println(gId.getId() + " : " + gId.getName() + " - by " + this.getAlbumArtist() + ". Released in  " + this.getAlbumRelease() + ". Price: " + gId.getDailyRent() + " SEK.");
                return true;
            }
        }
        return false;
    }
    public double returnReleaseDateAlbum() {
        for (Media alb: Main.aLm) {
            return this.getAlbumRelease();
        }
        return 0;
    }
    public String toString() { //Have return value displayed instead but then it is in brackets? BUUUU
        return getId() + " : " + getName() + " - by " + getAlbumArtist() + ". Released in  " + getAlbumRelease() + ". Price: " + getDailyRent() + " SEK. Status: " + getRentStatus();
    }
    private static void showTotalProfitAlbum() {
        System.out.println("Total profit " + Main.totalProfitAlbum + " SEK.");
    }
    private static double combinedProfits() {
        double Q = Main.totalProfitAlbum + Main.totalProfit;
        return Q;
    }
    static String showCombinedProfits() {
        System.out.print("Total combined profits: " + combinedProfits() + " SEK.");
        return null;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumRelease(int albumRelease) {
        this.albumRelease = albumRelease;
    }

    public int getAlbumRelease() {
        return albumRelease;
    }

    @Override
    public void storeMedia(Media g) {
        Main.aLm.add(g);
        Main.totalMedia++;
    }

    @Override
    public  void removeMedia(Media g, int id) {
        for(Iterator<Media> itr = Main.aLm.iterator(); itr.hasNext(); ) {//Used iterator here to fix Concurrent modification error
            Media item = itr.next();
            if(id == item.getId()) {
                itr.remove();
            }
        }
    }

    @Override
    public double returnDailyFee(int id) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {
                return alb.getDailyRent();
            }
        }
        return 0;
    }

    @Override
    public boolean idComparison(int id) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public  boolean compareRentStatus(int id, boolean rentStatus) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {
                if (rentStatus == alb.getRentStatus()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void rentMedia(int id) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {
                alb.setRentStatus(false);
                System.out.println();
            }
        }

    }

    @Override
    public  void returnMedia(int id) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {
                alb.setRentStatus(true);
                System.out.println();
            }
        }
    }

    @Override
    public  void userRatingMedia(int id, double userRating) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {
                alb.setUserRating(userRating);
                alb.setTotalUserRating(alb.getTotalUserRating() + alb.getUserRating());
            }
        }
    }

    @Override
    public void increaseTimesRented(int id) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {
                alb.setTimesRented(alb.getTimesRented() + 1);
            }
        }
    }

    @Override
    public void averageUserRating(int id) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {//Maybe delete all these extra checks
                alb.setUserRating(alb.getTotalUserRating() / alb.getTimesRented());
            }
        }
    }

    @Override
    public void viewAverageUserRatings(int id) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {
                System.out.println("Average user rating for " + id + " is " + alb.getUserRating() + " out of 5.");
                System.out.println();
            }
        }
    }

    @Override
    public void ratingsComparison() {
        //Collections.sort(Main.aLm, Media.RatingsComparator);
        for (Media alb : Main.aLm) {
            System.out.toString();
            //System.out.println(alb.getId() + " : " + alb.getName() + " - by " + alb.getAlbumArtist() + ". Released in  " + alb.getAlbumRelease() + ". Price: " + alb.getDailyRent() + " SEK. Average user rating: " + alb.getUserRating() + " Total Profit generated: " + alb.getProfitPerItem() + " SEK.");
        }
    }

    @Override
    public void profitsComparison() {
     //   Collections.sort(Main.aLm, Media.ProfitsComparator);
        for (Media alb : Main.aLm) {
            System.out.toString();
            //System.out.println(alb.getId() + " : " + alb.getName() + " - by " + alb.getAlbumArtist() + ". Released in  " + alb.getAlbumRelease() + ". Price: " + alb.getDailyRent() + " SEK. Average user rating: " + alb.getUserRating() + " Total Profit generated: " + alb.getProfitPerItem() + " SEK.");
        }
    }

    @Override
    public void rentalsComparison() {
       // Collections.sort(Main.aLm, Media.RentalsComparator);
        for (Media alb : Main.aLm) {
            System.out.toString();
            //System.out.println(alb.getId() + " : " + alb.getName() + " - by " + alb.getAlbumArtist() + ". Released in  " + alb.getAlbumRelease() + ". Price: " + alb.getDailyRent() + " SEK. Average user rating: " + alb.getUserRating() + " Total Profit generated: " + alb.getProfitPerItem() + " SEK.");
        }
    }

    @Override
    public  void userReview(int id, String review) {
        for (Media alb : Main.aLm) {
            if (id == alb.getId()) {//Maybe delete all these extra checks
                alb.setReview(review);
            }
        }
    }

    @Override
    public void storeReceipt(int id, int daysRented, int customerId) throws IOException {
        for (Media alb : Main.aLm){
            if(id == alb.getId()){
                File letter = new File("TransactionOfId" + id + ".txt");
                FileWriter fw = new FileWriter(letter);
                PrintWriter pw = new PrintWriter(fw);
                pw.println("Album  " + id + ".");
                pw.println("Rented and returned by Customer: " + customerId + ".");
                pw.println("Rented for " + daysRented + " ");
                pw.println("Rent expense " + returnDailyFee(id) * daysRented + " SEK.");//Better ??
                pw.println("Rented a total of " + alb.getTimesRented() + " times.");
                pw.println("User rating: " + alb.getUserRating() + ".");
                pw.println("User review: " + alb.getReview() + ".");
                pw.close();
            }

        }
    }
    @Override
    public void storeHistory(int id, int daysRented, int cId) throws IOException {
        for (Media alb : Main.aLm){
            if (id == alb.getId()){
                for (Person cust : Main.aLp){
                    if(cId == cust.getId()){
                        if (cust.getCustomerMembership().equalsIgnoreCase("silver")){
                            File letter = new File("TransactionHistory.txt");
                            FileWriter fw = new FileWriter(letter,true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(cId +";"+id+";"+ alb.getName() +";" +(returnDailyFee(id) * daysRented*0.9));
                            pw.close();
                        }if (cust.getCustomerMembership().equalsIgnoreCase("gold")){
                            File letter = new File("TransactionHistory.txt");
                            FileWriter fw = new FileWriter(letter,true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(cId +";"+id+";"+ alb.getName() +";" +(returnDailyFee(id) * daysRented*0.85));
                            pw.close();
                        }if(cust.getCustomerMembership().equalsIgnoreCase("premium")){
                            File letter = new File("TransactionHistory.txt");
                            FileWriter fw = new FileWriter(letter,true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(cId +";"+id+";"+ alb.getName() +";" +(returnDailyFee(id) * daysRented*0.75));
                            pw.close();
                        }else{
                            File letter = new File("TransactionHistory.txt");
                            FileWriter fw = new FileWriter(letter,true);
                            PrintWriter pw = new PrintWriter(fw);
                            pw.println(cId +";"+id+";"+ alb.getName() +";" +(returnDailyFee(id) * daysRented));
                            pw.close();

                        }
                    }
                }
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
            while (input.hasNext())
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
        for(Media alb : Main.aLm){
            if (id == alb.getId()){
                double toPay = alb.getDailyRent()*daysRented;
                alb.setProfitPerItem(alb.getProfitPerItem()+toPay);
            }
        }
    }
    @Override
    public void viewAll() {
        for(Media alb : Main.aLm){
            System.out.println(alb.toString());
        }
    }

    public void setProfitAlbum(double profitAlbum) {
        this.profitAlbum = profitAlbum;
    }

    public double getProfitAlbum() {
        return profitAlbum;
    }

    public double getTotalUserRating() {
        return totalUserRating;
    }

    public void setTotalUserRating(double totalUserRating) {
        this.totalUserRating = totalUserRating;
    }

    @Override
    protected String getGameGenre() {
        return getGameGenre();
    }


    @Override
    public int compareTo(Media o) {
        return 0;
    }
}
