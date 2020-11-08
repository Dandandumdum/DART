package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public abstract class Media implements Comparable<Media> {
    private int id;
    private String name;
    private double dailyRent;
    private Boolean rentStatus;
    private double profit;
    private double userRating;
    private int totalUnits;
    private int timesRented;
    private double totalUserRating;
    private String review;
    private double profitPerItem;


    public Media(int id, String name, double dailyRent, double userRating, Boolean rentStatus, int timesRented, String review, double profitPerItem){

        this.setId(id);
        this.setName(name);
        this.setDailyRent(dailyRent);
        this.setUserRating(userRating);
        this.setRentStatus(rentStatus);
        this.setTimesRented(timesRented);
        this.setReview(review);
        this.setProfitPerItem(profitPerItem);
    }

    public  static Comparator<Media> RatingsComparator = new Comparator<Media>() {
        public int compare(Media o1, Media o2) {
            double Rating1 = o1.getUserRating();
            double Rating2 = o2.getUserRating();
            return (int) (Rating2 - Rating1);
        }
    };

    public  Comparator<Media> ProfitsComparator = new Comparator<Media>() {
            public int compare(Media o1, Media o2) {
                double Profits1 = o1.getProfitPerItem();
                double Profits2 = o2.getProfitPerItem();
                return (int) (Profits2 - Profits1);
            }
        };

    public static Comparator<Media> RentalsComparator = new Comparator<Media>() {
        public int compare(Media a1, Media a2) {
            double Rentals1 = a1.getTimesRented();
            double Rentals2 = a2.getTimesRented();
            return (int) (Rentals2 - Rentals1);
        }
    };
    public double ratingsComparisonItems(){
        return Main.aLm.stream().max(Comparator.comparing(Media :: getTotalUserRating)).get().getTotalUserRating();
    }
    public String showRatingsComparisonItems(){
        return Main.aLm.stream().max(Comparator.comparing(Media :: getTotalUserRating)).get().toString();
    }
    public void showAllRatingsComparisonItems(){
        System.out.println(Main.aLm.toString());
       // return Main.aLm.toString();
    }
   public double profitsComparisonItems(){
        return Main.aLm.stream().max(Comparator.comparing(Media :: getProfitPerItem)).get().getProfitPerItem();
    }
    public String showProfitsComparisonItems(){
       return Main.aLm.stream().max(Comparator.comparing(Media :: getProfitPerItem)).get().toString();
    }
    public double rentalsComparisonItems(){
        return Main.aLm.stream().max(Comparator.comparing(Media :: getTimesRented)).get().getTimesRented();
    }
    public String showRentalsComparisonItems(){
       return Main.aLm.stream().max(Comparator.comparing(Media :: getTimesRented)).get().toString();
    }
    public abstract  void storeHistory(int id, int daysRented, int cId) throws IOException;

    public  abstract void storeMedia(Media g);

    public abstract void removeMedia( Media g, int id);

    public abstract double returnDailyFee(int id);

    public abstract boolean idComparison(int id);

    public abstract boolean compareRentStatus(int id, boolean rentStatus);

    public abstract void rentMedia(int id);

    public abstract void returnMedia(int id);

    public abstract void userRatingMedia(int id, double userRating);

    public abstract void increaseTimesRented(int id);

    public abstract void averageUserRating(int id);

    public abstract void viewAverageUserRatings(int id);

    public abstract void ratingsComparison();

    public abstract void profitsComparison();

    public abstract void rentalsComparison();

    public abstract void userReview(int id, String review);

    public abstract void storeReceipt(int id, int daysRented, int customerId) throws IOException;

    public abstract void openReceipt(int id) throws FileNotFoundException;

    public abstract void profitPerMedia(int id, double daysRented);

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

    public double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(double dailyRent) {
        this.dailyRent = dailyRent;
    }

    public Boolean getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(Boolean rentStatus) {
        this.rentStatus = rentStatus;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    public void rentAlbum(Media r) {
        setRentStatus(false);
    }

    public int getTimesRented() {
        return timesRented;
    }

    public void setTimesRented(int timesRented) {
        this.timesRented = timesRented;
    }

    public double getTotalUserRating() {
        return totalUserRating;
    }

    public void setTotalUserRating(double totalUserRating) {
        this.totalUserRating = totalUserRating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public double getProfitPerItem() {
        return profitPerItem;
    }

    public void setProfitPerItem(double profitPerItem) {
        this.profitPerItem = profitPerItem;
    }

    protected abstract String getGameGenre();

    protected abstract int getAlbumRelease();
}
