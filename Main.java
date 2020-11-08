
package com.company;
import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Media> aLm = new ArrayList<>();//Polymorphic, changed all classes to a polymorphic structure
    static ArrayList<Person> aLp = new ArrayList<>();
    static int totalMedia;
    static int totalAlbum;
    static double totalProfitAlbum;
    static int totalGame;
    static double totalProfit;
    static int totalCustomers;
    static int totalEmployees;


    public static void main(String[] args) throws Exception {

        Scanner keyboard = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Main Menu:");
            System.out.println("Welcome to DART, your good old game rental system. The competition has no steam to keep up!");
            System.out.println("");
            System.out.println("Please specify your role by entering one of the options given:");
            System.out.println("1. Enter “M” for Manager");
            System.out.println("2. Enter “E” for Employee");
            System.out.println("3. Enter “C” for Customer");
            System.out.println("4. Enter “X” to exit system ");

            switch (choice = keyboard.next().toUpperCase()) { //Without  keyboard.next() here I had an infinite Loop as it took the assumed variable for choice
                case "M":
                    ManagerFunction();
                    break;
                case "E":
                    EmployeeFunction();
                    break;
                case "C":
                    CustomerFunction();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }
        } while (!choice.equals("X"));
    }

    private static void ManagerFunction() throws Exception {

        String pass = "admin1234";
        System.out.println("Please enter password: ");
        Scanner keyboard = new Scanner(System.in);
        String userPass = keyboard.next();
        String ManagerChoice;

        if (pass.equals(userPass)) {
            do {
                Person m = new Manager(0, "");
                Person p = new Customer(0,"TEST", "", false, 0,0, false,0);
                Person q = new Employee(0, "TEST",0,0,0,0);
                Media g = new Album(0, "TEST", "",0, 0, 0, false, 0, "", 0);
                Media K = new Game(0, "TEST", "", 0, false, 0, 0, "",0);
                System.out.println();
                System.out.println("Manager Screen - Type one of the options below: ");
                System.out.println("1. Add an employee");
                System.out.println("2. View all employees");
                System.out.println("3. Remove employee.");
                System.out.println("4. View Metrics");
                System.out.println("5. Import Data");
                System.out.println("6. Return to Main Menu");
                ManagerChoice = String.valueOf(keyboard.next());

                switch (ManagerChoice) {
                    case "1":
                        try {
                            System.out.print("Employee ID: ");
                            int employeeId = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.print("Employee Name: ");
                            String employeeName = keyboard.nextLine();
                            System.out.print("Employee Birth Year: ");
                            int birthYear = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.print("Employee Gross Salary: ");
                            int grossSalary = keyboard.nextInt();
                            keyboard.nextLine();
                            if (grossSalary < 0) {
                                throw new SalaryCannotBeNegativeException("Invalid data. Employee salary cannot be negative.");
                            } else {
                                int age = 2020 - birthYear;
                                double netSalary;
                                if (grossSalary >= 100000) {
                                    if (age <= 22) {
                                        netSalary = (grossSalary * 0.70) + 4000;
                                    } else if (age >= 30) {
                                        netSalary = (grossSalary * 0.70) + 7500;
                                    } else {
                                        netSalary = (grossSalary * 0.70) + 6000;
                                    }
                                } else {
                                    if (age <= 22) {
                                        netSalary = grossSalary + 4000;
                                    } else if (age >= 30) {
                                        netSalary = grossSalary + 7500;
                                    } else {
                                        netSalary = grossSalary + 6000;
                                    }
                                }
                                Person e = new Employee(employeeId, employeeName, birthYear, age, grossSalary, netSalary);
                                q.storePerson(e);
                                System.out.println("Employee " + employeeName + " registered.");
                            }

                        } catch (NameCannotBeEmptyException exception) {
                            System.out.println(exception.getMessage());
                        } catch (SalaryCannotBeNegativeException exception) {
                            System.out.println(exception.getMessage());
                        } catch (InputMismatchException exception) {
                            System.out.println("Please enter a valid number. ");
                            keyboard.nextLine();
                        } finally {
                            System.out.println();
                        }
                        break;

                    case "2":
                        m.viewAll();// Requires no iteration
                        System.out.println();

                        break;

                    case "3":
                        try {
                            System.out.println("Please type Id of Employee you wish to delete: ");
                            int employeeId = keyboard.nextInt();
                            keyboard.nextLine();
                            if (q.idComparison(employeeId)) {
                                System.out.println("Employee with Id:  " + employeeId + " deleted.");
                                System.out.println();
                                q.removePerson(q, employeeId);

                            } else {
                                System.out.println("No Employee with matching Id found.");
                                System.out.println();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number. ");
                            keyboard.nextLine();
                        }
                        break;

                    case "4":
                        do {
                            try {//ISSUE with looping twice
                                System.out.println("Which Metrics would you like to view? ");
                                System.out.println("1. Most Profitable Item.");
                                System.out.println("2. Most Rented item.");
                                System.out.println("3. Most Profitable Customer.");
                                System.out.println("4. View product receipts.");
                                System.out.println("5. Return to Manager Menu.");
                                ManagerChoice = String.valueOf(keyboard.next());
                                System.out.println();

                                switch (ManagerChoice){
                                    case "1":
                                        Collections.sort(Main.aLm, g.ProfitsComparator);
                                        System.out.println("Highest Grossing item is: ");
                                        System.out.println(g.showProfitsComparisonItems());
                                        System.out.println("Profit generated: ");
                                        System.out.println(g.profitsComparisonItems());
                                        System.out.println();
                                        System.out.println("Press any key to continue.");
                                        break;

                                    case "2":
                                        Collections.sort(Main.aLm, g.RentalsComparator);
                                        System.out.println("Most rented item is: ");
                                        System.out.println(g.showRentalsComparisonItems());
                                        System.out.println("Times rented: ");
                                        System.out.println(g.rentalsComparisonItems());
                                        System.out.println();
                                        System.out.println("Press any key to continue.");
                                        break;

                                    case "3":
                                        Collections.sort(Main.aLp, p.ProfitsComparator);
                                        System.out.println("Most Profitable Customer is: ");
                                        System.out.println(p.CustomerProfitsComparison());
                                        System.out.println();
                                        System.out.println("Listing Customers from most profitable to least profitable: ");
                                        for (Person str : Main.aLp) {
                                            System.out.println(str.getId() + " : " + str.getName() + ". Membership level: " + str.getCustomerMembership() + " Profit generated from customer " + str.getProfitPerCustomer() + " SEK.");
                                        }
                                        System.out.println();
                                        System.out.println("Press any key to continue.");
                                        break;

                                    case "4":
                                        try {
                                            System.out.print("Enter Items Id: ");
                                            int albumId = keyboard.nextInt();
                                            keyboard.nextLine();
                                            if (g.idComparison(albumId)) {
                                                g.openReceipt(albumId);
                                            } else {
                                                System.out.println("No matching Item found.");
                                                System.out.println();
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Please enter a valid number. ");
                                            keyboard.nextLine();
                                        } catch (FileNotFoundException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        System.out.println("Press any key to continue.");
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } while (!keyboard.next().equals("5"));
                    {
                        System.out.println("Returning to Manager Menu.");
                        System.out.println();
                    }
                    break;
                    case "5"://Import Data
                        System.out.println("Please write the name of the file you would like to import: ");
                        String filename = keyboard.next();
                        m.importData(filename);
                        break;
                }
            } while (!ManagerChoice.equals("6"));
            {
                System.out.println("Returning to Main Menu");
                System.out.println();
            }
        } else {
            System.out.println("Invalid Password. Returning to Main Menu");
            System.out.println();
        }

    }
    private static void CustomerFunction() throws Exception {
        int daysRented;
        Person p = new Customer(0,"TEST", "", false, 0,0, false,0);
        Person q = new Employee(0, "TEST",0,0,0,0);
        Media g = new Album(0, "TEST", "",0, 0, 0, true, 0, "", 0);
        Media K = new Game(0, "TEST", "", 0, true, 0, 0, "",0);
        Scanner keyboard = new Scanner(System.in);
        String CustomerChoice;
        do {
            System.out.println("Customer Screen - Type one of the options below: ");
            System.out.println("1. Rent a game");
            System.out.println("2. Return a game");
            System.out.println("3. Rent an album");
            System.out.println("4. Return an album");
            System.out.println("5. Product enquiry"); // Check and send
            System.out.println("6. Members menu");// Check membership, credits and request upgrade
            System.out.println("7. Return to Main Menu");

            CustomerChoice = keyboard.next();
            switch (CustomerChoice) {
                case "1":
                    try {
                        System.out.print("Id of the game you would like to rent: ");
                        int gameId = keyboard.nextInt();
                        keyboard.nextLine();
                        System.out.print("Are you a member? Y/N: ");
                        CustomerChoice = keyboard.next();
                        int customerId;
                        if (CustomerChoice.equalsIgnoreCase("y")) {
                            System.out.println("Please enter your Customer Id: ");
                            customerId = keyboard.nextInt();//check if customer exists
                            keyboard.nextLine();
                            if (p.idComparison(customerId)) {
                                if (((Customer) p).checkNumRented(customerId)) {
                                    if (K.idComparison(gameId)) {
                                        if (K.compareRentStatus(gameId, true)) {
                                            System.out.println("Game with Id:  " + gameId + " rented.");
                                            System.out.println();
                                            K.rentMedia(gameId);
                                            ((Customer) p).increaseNumRented(customerId);
                                            K.increaseTimesRented(gameId);
                                        } else {
                                            System.out.println("Game already rented by another customer.");
                                            System.out.println();
                                        }
                                    } else {
                                        System.out.println("No game with matching Id found.");
                                        System.out.println();
                                    }
                                }
                            } else {
                                System.out.println("Customer Id not found.");
                                System.out.println();
                            }
                        }
                        if (CustomerChoice.equalsIgnoreCase("n")) {
                            if (K.idComparison(gameId)) {
                                if (K.compareRentStatus(gameId, true)) {
                                    System.out.println("Game with Id:  " + gameId + " rented.");
                                    System.out.println();
                                    K.rentMedia(gameId);
                                    K.increaseTimesRented(gameId);

                                } else {
                                    System.out.println("Game already rented by another.");
                                    System.out.println();
                                }
                            } else {
                                System.out.println("No game with matching Id found.");
                                System.out.println();
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number. ");
                        keyboard.nextLine();
                    }catch (NullPointerException e){
                        System.out.println("Employees may not rent games.");
                    }
                    break;
            case "2":
                    try {
                        System.out.print("Id of game you would like to return: ");
                        int gameId = keyboard.nextInt();
                        keyboard.nextLine();
                        System.out.print("How many days did you rent the game for: ");
                        daysRented = keyboard.nextInt();
                        keyboard.nextLine();
                        if (daysRented <= 0) {
                            throw new DaysRentedException("Invalid operation. Upon returning an item, the number of days rented must be positive.");
                        } else {
                            System.out.print("Are you a member? Y/N: ");
                            CustomerChoice = keyboard.next();
                            int customerId;

                            if (CustomerChoice.equalsIgnoreCase("y")) {
                                System.out.print("Please enter your Customer Id: ");
                                customerId = keyboard.nextInt();
                                keyboard.nextLine();
                                if (p.idComparison(customerId)) {
                                    if (K.idComparison(gameId)) {
                                        if (K.compareRentStatus(gameId, false)) {
                                            //pay with credit policy
                                            if (((Customer) p).customerCredits(customerId)) {
                                                System.out.println("You have 5 credits or more, would you like to use them instead of payment? Y/N: ");
                                                CustomerChoice = keyboard.next();
                                                if (CustomerChoice.equalsIgnoreCase("y")) {
                                                    K.returnMedia(gameId);
                                                    K.profitPerMedia(gameId, daysRented);
                                                    ((Customer) p).payWithCredits(customerId);
                                                    System.out.println("You have successfully paid using 5 credits");
                                                    ((Customer) p).viewCredits(customerId);
                                                    ((Customer) p).decreaseNumRented(customerId);
                                                    System.out.print("Would you like to leave a rating? Y/N: ");
                                                    CustomerChoice = keyboard.next();
                                                    if (CustomerChoice.equalsIgnoreCase("y")) {
                                                        System.out.print("What rating between 0 and 5 would you give the product?: ");
                                                        int userRating = keyboard.nextInt();
                                                        keyboard.nextLine();
                                                        while (userRating > 5 || userRating < 0) {
                                                            System.out.print("Please enter a valid number (0 - 5) : ");
                                                            userRating = keyboard.nextInt();
                                                            keyboard.nextLine();
                                                        }
                                                        K.userRatingMedia(gameId, userRating);
                                                        K.averageUserRating(gameId);
                                                        System.out.print("Would you like to leave a review? Y/N: ");
                                                        CustomerChoice = keyboard.next();
                                                        if (CustomerChoice.equalsIgnoreCase("y")) {
                                                            System.out.println("Please leave a short review of the product: ");
                                                            String review = keyboard.nextLine();
                                                            keyboard.nextLine();
                                                            K.userReview(gameId, review);
                                                            System.out.println();
                                                            System.out.println("Thank you for your rating and review!");
                                                            System.out.println();
                                                            K.storeReceipt(gameId, daysRented, customerId);
                                                            K.storeHistory(gameId, daysRented, customerId);

                                                        } else {
                                                            System.out.println("Thank you for your rating!");
                                                            System.out.println();
                                                            K.storeReceipt(gameId, daysRented, customerId);
                                                            K.storeHistory(gameId, daysRented, customerId);
                                                        }
                                                    } else {
                                                        K.storeReceipt(gameId, daysRented, customerId);
                                                        K.storeHistory(gameId, daysRented, customerId);
                                                    }

                                                } else {
                                                    System.out.println("Game with Id:  " + gameId + " returned.");
                                                    ((Customer) p).paymentConditionsMember(customerId, daysRented, K.returnDailyFee(gameId));
                                                    K.returnMedia(gameId);
                                                    K.profitPerMedia(gameId, daysRented);
                                                    ((Customer) p).decreaseNumRented(customerId);
                                                    System.out.print("Would you like to leave a rating? Y/N: ");
                                                    CustomerChoice = keyboard.next();
                                                    if (CustomerChoice.equalsIgnoreCase("y")) {
                                                        System.out.print("What rating between 0 and 5 would you give the product?: ");
                                                        int userRating = keyboard.nextInt();
                                                        keyboard.nextLine();
                                                        while (userRating > 5 || userRating < 0) {
                                                            System.out.print("Please enter a valid number (0 - 5) : ");
                                                            userRating = keyboard.nextInt();
                                                            keyboard.nextLine();
                                                        }
                                                        K.userRatingMedia(gameId, userRating);
                                                        K.averageUserRating(gameId);
                                                        System.out.print("Would you like to leave a review? Y/N: ");
                                                        CustomerChoice = keyboard.next();
                                                        if (CustomerChoice.equalsIgnoreCase("y")) {
                                                            System.out.println("Please leave a short review of the product: ");
                                                            String review = keyboard.nextLine();
                                                            keyboard.next();
                                                            K.userReview(gameId, review);
                                                            System.out.println();
                                                            System.out.println("Thank you for your rating and review!");
                                                            System.out.println();
                                                            K.storeReceipt(gameId, daysRented, customerId);
                                                            K.storeHistory(gameId, daysRented, customerId);

                                                        } else {
                                                            System.out.println("Thank you for your rating!");
                                                            System.out.println();
                                                            K.storeReceipt(gameId, daysRented, customerId);
                                                            K.storeHistory(gameId, daysRented, customerId);
                                                        }
                                                    } else {
                                                        K.storeReceipt(gameId, daysRented, customerId);
                                                        K.storeHistory(gameId, daysRented, customerId);
                                                    }
                                                }
                                            } else {
                                                System.out.println("Game with Id:  " + gameId + " returned.");
                                                ((Customer) p).paymentConditionsMember(customerId, daysRented, K.returnDailyFee(gameId));
                                                K.returnMedia(gameId);
                                                K.profitPerMedia(gameId, daysRented);
                                                ((Customer) p).decreaseNumRented(customerId);
                                                System.out.print("Would you like to leave a rating? Y/N: ");
                                                CustomerChoice = keyboard.next();
                                                if (CustomerChoice.equalsIgnoreCase("y")) {
                                                    System.out.print("What rating between 0 and 5 would you give the product?: ");
                                                    int userRating = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                    while (userRating > 5 || userRating < 0) {
                                                        System.out.print("Please enter a valid number (0 - 5) : ");
                                                        userRating = keyboard.nextInt();
                                                        keyboard.nextLine();
                                                    }
                                                    K.userRatingMedia(gameId, userRating);
                                                    K.averageUserRating(gameId);
                                                    System.out.print("Would you like to leave a review? Y/N: ");
                                                    CustomerChoice = keyboard.next();
                                                    if (CustomerChoice.equalsIgnoreCase("y")) {
                                                        System.out.println("Please leave a short review of the product: ");
                                                        String review = keyboard.next();
                                                        keyboard.nextLine();
                                                        K.userReview(gameId, review);
                                                        System.out.println();
                                                        System.out.println("Thank you for your rating and review!");
                                                        System.out.println();
                                                        K.storeReceipt(gameId, daysRented, customerId);
                                                        K.storeHistory(gameId, daysRented, customerId);
                                                    } else {
                                                        System.out.println("Thank you for your rating!");
                                                        System.out.println();
                                                        K.storeReceipt(gameId, daysRented, customerId);
                                                        K.storeHistory(gameId, daysRented, customerId);
                                                    }
                                                }else{
                                                    K.storeReceipt(gameId, daysRented, customerId);
                                                    K.storeHistory(gameId, daysRented, customerId);
                                                }
                                            }
                                        } else {
                                            System.out.println("Game already returned.");
                                            System.out.println();
                                        }
                                    } else {
                                        System.out.println("No Game with matching Id found.");
                                        System.out.println();
                                    }
                                } else {
                                    System.out.println("Customer Id not found.");
                                    System.out.println();
                                }
                            }
                            if (CustomerChoice.equalsIgnoreCase("n")) {
                                if (K.idComparison(gameId)) {
                                    if (K.compareRentStatus(gameId, false)) {
                                        System.out.println("Game with Id: " + gameId + " returned.");
                                        ((Customer) p).paymentConditionsNonMember(daysRented, K.returnDailyFee(gameId));
                                        K.returnMedia(gameId);
                                        K.profitPerMedia(gameId, daysRented);
                                        System.out.print("Would you like to leave a rating? Y/N: ");
                                        CustomerChoice = keyboard.next();
                                        if (CustomerChoice.equalsIgnoreCase("y")) {
                                            System.out.print("What rating between 0 and 5 would you give the product?: ");
                                            int userRating = keyboard.nextInt();
                                            //keyboard.nextLine();
                                            while (userRating > 5 || userRating < 0) {
                                                System.out.print("Please enter a valid number (0 - 5) : ");
                                                userRating = keyboard.nextInt();
                                                keyboard.nextLine();
                                            }
                                            K.userRatingMedia(gameId, userRating);
                                            K.averageUserRating(gameId);
                                            System.out.print("Would you like to leave a review? Y/N: ");
                                            CustomerChoice = keyboard.next();
                                            if (CustomerChoice.equalsIgnoreCase("y")) {
                                                System.out.println("Please leave a short review of the product: ");
                                                String review = keyboard.next();
                                                keyboard.nextLine();
                                                K.userReview(gameId, review);
                                                System.out.println();
                                                System.out.println("Thank you for your rating and review!");
                                                System.out.println();

                                            } else {
                                                System.out.println("Thank you for your rating!");
                                                System.out.println();
                                            }
                                        }
                                    } else {
                                        System.out.println("Game already returned.");
                                        System.out.println();
                                    }
                                } else {
                                    System.out.println("No game with matching Id found.");
                                    System.out.println();
                                }
                            }
                        }
                    } catch (DaysRentedException exception) {
                        System.out.println();
                        System.out.println(exception.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number. ");
                        keyboard.nextLine();
                    } finally {
                        System.out.println();
                    }
                    break;

            case "3":
                    try {
                        System.out.print("Id of the album you would like to rent: ");
                        int albumsId = keyboard.nextInt();// have nextLine after nexts and nextInts to clear "cache"
                        keyboard.nextLine();

                        System.out.print("Are you a member? Y/N: ");
                        CustomerChoice = keyboard.next();
                        int customerId;
                        if (CustomerChoice.equalsIgnoreCase("y")) {
                            System.out.println("Please enter your Customer Id: ");
                            customerId = keyboard.nextInt();//check if customer exists
                            keyboard.nextLine();
                            if (p.idComparison(customerId)) {
                                if (((Customer) p).checkNumRented(customerId)) {
                                    if (g.idComparison(albumsId)) {
                                        if (g.compareRentStatus(albumsId, true)) {
                                            System.out.println("Album with Id:  " + albumsId + " rented.");
                                            System.out.println();
                                            g.rentMedia(albumsId);
                                            ((Customer) p).increaseNumRented(customerId);
                                            g.increaseTimesRented(albumsId);
                                        } else {
                                            System.out.println("Album already rented by another p.");
                                            System.out.println();
                                        }
                                    } else {
                                        System.out.println("No album with matching Id found.");
                                        System.out.println();
                                    }
                                }
                            } else {
                                System.out.println("Customer Id not found.");
                                System.out.println();
                            }
                        }
                        if (CustomerChoice.equalsIgnoreCase("n")) {
                            if (g.idComparison(albumsId)) {
                                if (g.compareRentStatus(albumsId, true)) {
                                    System.out.println("Album with Id:  " + albumsId + " rented.");
                                    System.out.println();
                                    g.rentMedia(albumsId);
                                    g.increaseTimesRented(albumsId);
                                } else {
                                    System.out.println("Album already rented by another p.");
                                    System.out.println();
                                }
                            } else {
                                System.out.println("No album with matching Id found.");
                                System.out.println();
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number. ");
                        keyboard.nextLine();
                    }catch(NullPointerException e){
                        System.out.println("Employees may not rent albums.");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
            case "4":
                    try {
                        System.out.print("Id of the album you would like to return: ");
                        int albumsId = keyboard.nextInt();
                        keyboard.nextLine();
                        System.out.print("How many days did you rent the album for?: ");
                        daysRented = keyboard.nextInt();
                        keyboard.nextLine();
                        if (daysRented <= 0) {
                            throw new DaysRentedException("Invalid operation. Upon returning an item, the number of days rented must be positive.");
                        } else {
                            System.out.print("Are you a member? Y/N: ");
                            CustomerChoice = keyboard.next();
                            int customerId;
                            if (CustomerChoice.equalsIgnoreCase("y")) {
                                System.out.print("Please enter your Customer Id: ");
                                customerId = keyboard.nextInt();
                                keyboard.nextLine();
                                if (p.idComparison(customerId)) {
                                    if (g.idComparison(albumsId)) {
                                        if (g.compareRentStatus(albumsId, false)) {
                                            if (((Customer) p).customerCredits(customerId)) {
                                                System.out.println("You have 5 credits or more, would you like to use them instead of payment? Y/N: ");
                                                CustomerChoice = keyboard.next();
                                                if (CustomerChoice.equalsIgnoreCase("y")) {
                                                    g.returnMedia(albumsId);
                                                    g.profitPerMedia(albumsId, daysRented);
                                                    ((Customer) p).payWithCredits(customerId);
                                                    System.out.println("You have successfully paid using 5 credits");
                                                    ((Customer) p).viewCredits(customerId);
                                                    ((Customer) p).decreaseNumRented(customerId);
                                                    System.out.print("Would you like to leave a rating? Y/N: ");
                                                    CustomerChoice = keyboard.next();
                                                    if (CustomerChoice.equalsIgnoreCase("y")) {
                                                        System.out.print("What rating between 0 and 5 would you give the product?: ");
                                                        int userRating = keyboard.nextInt();
                                                        keyboard.nextLine();
                                                        while (userRating > 5 || userRating < 0) {
                                                            System.out.print("Please enter a valid number (0 - 5) : ");
                                                            userRating = keyboard.nextInt();
                                                            keyboard.nextLine();
                                                        }
                                                        g.userRatingMedia(albumsId, userRating);
                                                        g.averageUserRating(albumsId);
                                                        System.out.print("Would you like to leave a review? Y/N: ");
                                                        CustomerChoice = keyboard.next();
                                                        if (CustomerChoice.equalsIgnoreCase("y")) {
                                                            System.out.println("Please leave a short review of the product: ");
                                                            String review = keyboard.next();
                                                            keyboard.nextLine();
                                                            g.userReview(albumsId, review);
                                                            System.out.println();
                                                            System.out.println("Thank you for your rating and review!");
                                                            System.out.println();
                                                            g.storeReceipt(albumsId, daysRented, customerId);
                                                            g.storeHistory(albumsId, daysRented, customerId);

                                                        } else {
                                                            System.out.println("Thank you for your rating!");
                                                            System.out.println();
                                                            g.storeReceipt(albumsId, daysRented, customerId);
                                                            g.storeHistory(albumsId, daysRented, customerId);

                                                        }
                                                    } else {
                                                        g.storeReceipt(albumsId, daysRented, customerId);
                                                        g.storeHistory(albumsId, daysRented, customerId);

                                                    }
                                                } else {
                                                    System.out.println("Album with Id:  " + albumsId + " returned.");
                                                    ((Customer) p).paymentConditionsMember(customerId, daysRented, g.returnDailyFee(albumsId));
                                                    g.returnMedia(albumsId);
                                                    g.profitPerMedia(albumsId, daysRented);
                                                    ((Customer) p).decreaseNumRented(customerId);
                                                    System.out.print("Would you like to leave a rating? Y/N: ");
                                                    CustomerChoice = keyboard.next();
                                                    if (CustomerChoice.equalsIgnoreCase("y")) {
                                                        System.out.print("What rating between 0 and 5 would you give the product?: ");
                                                        int userRating = keyboard.nextInt();
                                                        keyboard.nextLine();
                                                        while (userRating > 5 || userRating < 0) {
                                                            System.out.print("Please enter a valid number (0 - 5) : ");
                                                            userRating = keyboard.nextInt();
                                                            keyboard.nextLine();
                                                        }
                                                        g.userRatingMedia(albumsId, userRating);
                                                        g.averageUserRating(albumsId);
                                                        System.out.print("Would you like to leave a review? Y/N: ");
                                                        CustomerChoice = keyboard.next();
                                                        if (CustomerChoice.equalsIgnoreCase("y")) {
                                                            System.out.println("Please leave a short review of the product: ");
                                                            String review = keyboard.next();
                                                            keyboard.nextLine();
                                                            g.userReview(albumsId, review);
                                                            System.out.println();
                                                            System.out.println("Thank you for your rating and review!");
                                                            System.out.println();
                                                            g.storeReceipt(albumsId, daysRented, customerId);
                                                            g.storeHistory(albumsId, daysRented, customerId);


                                                        } else {
                                                            System.out.println("Thank you for your rating!");
                                                            System.out.println();
                                                            g.storeReceipt(albumsId, daysRented, customerId);
                                                            g.storeHistory(albumsId, daysRented, customerId);
                                                        }
                                                    } else {
                                                        g.storeReceipt(albumsId, daysRented, customerId);
                                                        g.storeHistory(albumsId, daysRented, customerId);
                                                    }
                                                    }
                                            } else {
                                                System.out.println("Album with Id:  " + albumsId + " returned.");
                                                ((Customer) p).paymentConditionsMember(customerId, daysRented, g.returnDailyFee(albumsId));
                                                g.returnMedia(albumsId);
                                                g.profitPerMedia(albumsId, daysRented);
                                                ((Customer) p).decreaseNumRented(customerId);
                                                System.out.print("Would you like to leave a rating? Y/N: ");
                                                CustomerChoice = keyboard.next();
                                                if (CustomerChoice.equalsIgnoreCase("y")) {
                                                    System.out.print("What rating between 0 and 5 would you give the product?: ");
                                                    int userRating = keyboard.nextInt();
                                                    keyboard.nextLine();
                                                    while (userRating > 5 || userRating < 0) {
                                                        System.out.print("Please enter a valid number (0 - 5) : ");
                                                        userRating = keyboard.nextInt();
                                                        keyboard.nextLine();
                                                    }
                                                    g.userRatingMedia(albumsId, userRating);
                                                    g.averageUserRating(albumsId);
                                                    System.out.print("Would you like to leave a review? Y/N: ");
                                                    CustomerChoice = keyboard.next();
                                                    if (CustomerChoice.equalsIgnoreCase("y")) {
                                                        System.out.println("Please leave a short review of the product: ");
                                                        String review = keyboard.nextLine();
                                                        keyboard.nextLine();
                                                        g.userReview(albumsId, review);
                                                        System.out.println();
                                                        System.out.println("Thank you for your rating and review!");
                                                        System.out.println();
                                                        g.storeReceipt(albumsId, daysRented, customerId);
                                                        g.storeHistory(albumsId, daysRented, customerId);

                                                    } else {
                                                        System.out.println("Thank you for your rating!");
                                                        System.out.println();
                                                        g.storeReceipt(albumsId, daysRented, customerId);
                                                        g.storeHistory(albumsId, daysRented, customerId);

                                                    }
                                                } else {
                                                    g.storeReceipt(albumsId, daysRented, customerId);
                                                    g.storeHistory(albumsId, daysRented, customerId);

                                                }
                                            }
                                        } else {
                                            System.out.println("Album already returned.");
                                            System.out.println();

                                        }
                                    } else {
                                        System.out.println("No album with matching Id found.");
                                        System.out.println();
                                    }
                                } else {
                                    System.out.println("Customer Id not found.");
                                    System.out.println();
                                }
                            }
                        }
                        if (CustomerChoice.equalsIgnoreCase("n")) {
                            if (g.idComparison(albumsId)) {
                                if (g.compareRentStatus(albumsId, false)) {
                                    System.out.println("Album with Id: " + albumsId + " returned.");
                                    ((Customer) p).paymentConditionsNonMember(daysRented, g.returnDailyFee(albumsId));
                                    g.returnMedia(albumsId);
                                    g.profitPerMedia(albumsId, daysRented);
                                    System.out.print("Would you like to leave a rating? Y/N: ");
                                    CustomerChoice = keyboard.next();
                                    if (CustomerChoice.equalsIgnoreCase("y")) {
                                        System.out.print("What rating between 0 and 5 would you give the product?: ");
                                        int userRating = keyboard.nextInt();
                                        keyboard.nextLine();
                                        while (userRating > 5 || userRating < 0) {
                                            System.out.print("Please enter a valid number (0 - 5) : ");
                                            userRating = keyboard.nextInt();
                                            keyboard.nextLine();
                                        }
                                        g.userRatingMedia(albumsId, userRating);
                                        g.averageUserRating(albumsId);
                                        System.out.print("Would you like to leave a review? Y/N: ");
                                        CustomerChoice = keyboard.next();
                                        if (CustomerChoice.equalsIgnoreCase("y")) {
                                            System.out.println("Please leave a short review of the product: ");
                                            String review = keyboard.next();
                                            keyboard.nextLine();
                                            g.userReview(albumsId, review);
                                            System.out.println();
                                            System.out.println("Thank you for your rating and review!");
                                            System.out.println();

                                        } else {
                                            System.out.println("Thank you for your rating!");
                                            System.out.println();

                                        }
                                    }
                                } else {
                                    System.out.println("Album already returned.");
                                    System.out.println();
                                }
                            } else {
                                System.out.println("No Album with matching Id found.");
                                System.out.println();
                            }
                        } else {
                            System.out.print("Please select a valid option.");
                        }
                    } catch (DaysRentedException exception) {
                        System.out.println(exception.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number. ");
                        keyboard.nextLine();
                    } finally {
                        System.out.println();
                    }
                    break;

            case "5":
                    System.out.println();
                do {
                    System.out.println("What would you like to do?:");
                    System.out.println("1. Check average ratings.");
                    System.out.println("2. Search.");
                    System.out.println("3. View by sorted ratings.");
                    System.out.println("4. Return to Customer Menu.");
                    System.out.println();
                    CustomerChoice = keyboard.next();
                    switch (CustomerChoice) {
                        case "1":
                            try {
                                System.out.println("Search for an Album or a Game.");
                                System.out.print("A/G: ");
                                CustomerChoice = keyboard.next();
                                if (CustomerChoice.equalsIgnoreCase("a")) {
                                    System.out.print("Enter Album Id: ");
                                    int albumId = keyboard.nextInt();
                                    keyboard.nextLine();
                                    if (g.idComparison(albumId)) {
                                        g.viewAverageUserRatings(albumId);
                                    } else {
                                        System.out.println("No matching album found.");
                                        System.out.println();
                                    }

                                }
                                if (CustomerChoice.equalsIgnoreCase("g")) {
                                    System.out.print("Enter Game Id: ");
                                    int gameId = keyboard.nextInt();
                                    keyboard.nextLine();
                                    if (K.idComparison(gameId)) {
                                        K.viewAverageUserRatings(gameId);
                                    } else {
                                        System.out.println("No matching game found.");
                                        System.out.println();
                                    }
                                } else {

                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number. ");
                                keyboard.nextLine();
                            }
                            break;
                        case "2":
                            try {
                                System.out.println("Search for an Album or a Game.");
                                System.out.print("A/G: ");
                                CustomerChoice = keyboard.next();
                                if (CustomerChoice.equalsIgnoreCase("a")) {
                                    System.out.println("Search Album by release year.");
                                    System.out.print("Enter release year: ");
                                    int albumsRelease = keyboard.nextInt();
                                    keyboard.nextLine();
                                    System.out.println();
                                    if (((Album) g).releaseComparisonAlbum(albumsRelease)) {
                                        System.out.println();
                                    } else {
                                        System.out.println("No matching album found.");
                                        System.out.println();
                                    }
                                }
                                if (CustomerChoice.equalsIgnoreCase("g")) {
                                    System.out.println("Search by game genre. ");
                                    System.out.print("Enter Game genre: ");

                                    String gameGenre = keyboard.next();
                                    if (((Game) K).genreComparisonGame(gameGenre)) {
                                        System.out.println();
                                    } else {
                                        System.out.println("No matching game found.");
                                        System.out.println();
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number. ");
                                keyboard.nextLine();
                            }
                            break;
                        case "3":
                            Comparator<Media> ratingsComparator = g.RatingsComparator;
                            Collections.sort(Main.aLm, ratingsComparator);
                            System.out.println("Showing items sorted by average user ratings.");
                            g.viewAll();
                            System.out.println();
                            break;
                        default:
                            break;
                    }
                } while (!keyboard.next().equals("4"));//avoids conflict with next 6

                break;
            case "6":
                    try {
                        System.out.print("Please enter your Customer Id: ");
                        int customerId = keyboard.nextInt();
                        keyboard.nextLine();
                        if (p.idComparison(customerId)) {
                            do {
                                System.out.println("What would you like to do?:");
                                System.out.println("1. Check Membership status.");
                                System.out.println("2. Check credits balance.");
                                System.out.println("3. Request Membership Upgrade.");
                                System.out.println("4. Send a Message to another Customer.");
                                System.out.println("5. Check Mail.");
                                System.out.println("6. Return to Customer Menu.");
                                System.out.println();
                                CustomerChoice = keyboard.next();

                                switch (CustomerChoice) {
                                    case "1":
                                        ((Customer) p).viewMembership(customerId);
                                        break;
                                    case "2":
                                        ((Customer) p).viewCredits(customerId);
                                        break;
                                    case "3":
                                        do {
                                            System.out.print("Do you want to request a membership upgrade? Y/N: ");
                                            CustomerChoice = keyboard.next();
                                            if (CustomerChoice.equalsIgnoreCase("y")) {
                                                ((Customer) p).requestUpgrade(customerId);
                                                System.out.println("Request sent.");
                                                System.out.println();
                                                break;
                                            }
                                        } while (!CustomerChoice.equalsIgnoreCase("n"));
                                        break;
                                    case "4":
                                        try {
                                            System.out.print("Would you like to send a message? Y/N: ");
                                            CustomerChoice = keyboard.next();

                                            if (CustomerChoice.equalsIgnoreCase("y")) {
                                                //Check if recipient exists
                                                System.out.print("Enter recipients Customer Id: ");
                                                int recCustomerId = keyboard.nextInt();
                                                keyboard.nextLine();
                                                if (p.idComparison(recCustomerId)) {
                                                    System.out.println("What would you like to send to customer " + recCustomerId + "?");
                                                    File letter = new File("MsgTo" + recCustomerId + ".txt");
                                                    FileWriter fw = new FileWriter(letter);
                                                    PrintWriter pw = new PrintWriter(fw);
                                                    pw.println("To Customer " + recCustomerId);
                                                    pw.println();
                                                    pw.println(keyboard.nextLine());
                                                    pw.println();
                                                    pw.println("From Customer " + customerId);
                                                    pw.close();
                                                    System.out.println();
                                                    ((Customer) p).hasMailTrue(recCustomerId);

                                                } else {
                                                    System.out.println("No customer with matching Id found.");
                                                    System.out.println();
                                                }
                                            } else {
                                                System.out.print("Please enter a valid input. Y/N: ");
                                                System.out.println();
                                            }
                                        } catch (InputMismatchException e) {
                                            System.out.println("Please enter a valid number. ");
                                            keyboard.nextLine();
                                        }
                                        break;
                                    case "5":
                                        if (((Customer) p).unreadMail(customerId)) {
                                            try {
                                                String filename = "MsgTo" + customerId + ".txt";
                                                File in = new File(filename);
                                                Scanner input = new Scanner(in);
                                                while (input.hasNext())//Displayed only first letter(character)
                                                {
                                                    System.out.println(input.nextLine());
                                                }
                                                ((Customer) p).hasMailFalse(customerId);
                                            } catch (FileNotFoundException exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                        } else {
                                            System.out.println("You have no unread mail. ");
                                            System.out.println();
                                        }
                                        break;
                                }
                            } while (!CustomerChoice.equals("6"));
                            System.out.println("Returning to Customer Menu.");
                            System.out.println();

                        } else {
                            System.out.println("Customer Account required for these features.");
                            System.out.println();
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number. ");
                        keyboard.nextLine();
                    }
                    break;
            }
        } while (!CustomerChoice.equals("7"));
        System.out.println("Returning to Main Menu");
        System.out.println();

    }
    public static void EmployeeFunction() throws Exception {
        Scanner keyboard = new Scanner(System.in);
        String passE = "password123";
        System.out.println("Please enter password: ");
        String userPass = keyboard.next();
        String EmployeeChoice;

        if (passE.equals(userPass)) {
            do {
                Person p = new Customer(0,"TEST", "", false, 0,0, false,0);
                Person q = new Employee(0, "TEST",0,0,0,0);
                Media g = new Album(0, "TEST", "",0, 0, 0, false, 0, "", 0);
                Media K = new Game(0, "TEST", "",0, false, 0,0,"", 0);
                System.out.println();//ADDed new functionality here
                System.out.println("Employee Screen - Type one of the options below: ");
                System.out.println("1. Register a game");
                System.out.println("2. Remove a game");
                System.out.println("3. Register an album");
                System.out.println("4. Remove an album");
                System.out.println("5. Register a customer");
                System.out.println("6. Remove a customer");
                System.out.println("7. Update customer membership");
                System.out.println("8. Show total rent profit");
                System.out.println("9. View all items");
                System.out.println("10. Return to Main Menu");
                EmployeeChoice = keyboard.next();

                switch (EmployeeChoice) {
                    case "1":
                        try {
                            System.out.print("Game ID: ");
                            int gameId = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.print("Title: ");
                            String gameTitle = keyboard.next();
                            System.out.print("Genre: ");
                            String genre = keyboard.next();
                            System.out.print("Daily Fee: ");
                            int dailyFee = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.print("Rent Status: ");
                            Boolean rentStatus = keyboard.nextBoolean();
                            System.out.println();

                            Media k = new Game(gameId, gameTitle, genre, dailyFee, rentStatus, 0, 0, "", 0);
                            K.storeMedia(k);
                            System.out.println("Game " + gameId + " registered.");

                        } catch (NameCannotBeEmptyException | RentCannotBeNegativeException exception) {
                            System.out.println(exception.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number. ");
                            keyboard.nextLine();
                        } finally {
                            System.out.println();
                        }
                        break;
                    case "2":
                        try {
                            System.out.println("Enter Game id that you wish to delete: ");
                            int gameId = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.println();
                            if (g.idComparison(gameId)) {
                                System.out.println("Game with Id:  " + gameId + " deleted.");
                                System.out.println();
                                g.removeMedia(g, gameId);
                            } else {
                                System.out.println("No game with matching Id found.");
                                System.out.println();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number. ");
                            keyboard.nextLine();
                        }
                        break;
                    case "3":
                        try {
                            System.out.print("Album ID: ");
                            int albumsId = keyboard.nextInt();//nextLine
                            keyboard.nextLine();
                            System.out.print("Title: ");
                            String albumsTitle = keyboard.next();
                            System.out.print("Artist: ");
                            String albumsArtist = keyboard.next();
                            System.out.print("Release year: ");
                            int albumsRelease = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.print("Daily Fee: ");
                            int dailyFee = keyboard.nextInt();//nextLine
                            keyboard.nextLine();
                            System.out.print("Rent Status: ");
                            Boolean rentStatus = keyboard.nextBoolean();
                            System.out.println();
                            Media a = new Album(albumsId, albumsTitle, albumsArtist, albumsRelease, dailyFee, 0, rentStatus, 0, "", 0);
                            g.storeMedia(a);
                            System.out.println("Album " + albumsTitle + " Registered.");

                        } catch (NameCannotBeEmptyException | RentCannotBeNegativeException exception) {
                            System.out.println(exception.getMessage());
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number. ");
                            keyboard.nextLine();
                        } finally {
                            System.out.println();
                        }


                        break;
                    case "4":
                        try {
                            System.out.println("Enter the albums id that you wish to delete: ");
                            int albumsId = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.println();
                            if (g.idComparison(albumsId)) {
                                System.out.println("Album with Id:  " + albumsId + " deleted.");
                                System.out.println();
                                g.removeMedia(g, albumsId);
                            } else {
                                System.out.println("No album with matching Id found.");
                                System.out.println();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number. ");
                            keyboard.nextLine();
                        }

                        break;
                    case "5":
                        try {
                            System.out.print("Customer Id: ");
                            int customerId = keyboard.nextInt();
                            keyboard.nextLine();
                            System.out.print("Customer name: ");
                            String customerName = keyboard.next();
                            keyboard.nextLine();
                            System.out.print("Customer Membership level: ");
                            while (!keyboard.hasNext("regular") && !keyboard.hasNext("silver") && !keyboard.hasNext("gold") && !keyboard.hasNext("premium") && !keyboard.hasNext("Regular") && !keyboard.hasNext("Silver") && !keyboard.hasNext("Gold") && !keyboard.hasNext("Premium")) {
                                keyboard.nextLine();
                                System.out.println("Please enter Regular, Silver, Gold, or Premium for membership levels.");
                                System.out.println("Customer Membership level: ");
                            }
                            String customerMembership = keyboard.next();
                            Person c = new Customer(customerId, customerName, customerMembership, false, 0, 0, false, 0);//initialize upgrade to false
                            c.storePerson(c);
                            System.out.println("Customer " + customerName + " stored in system.");
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number. ");
                            keyboard.nextLine();
                        }
                        break;
                    case "6":
                        try {
                            System.out.println("Enter Customer's id that you wish to delete: ");
                            int customerId = keyboard.nextInt();
                            keyboard.nextLine();

                            if (q.idComparison(customerId)) {
                                System.out.println("Customer with Id:  " + customerId + " deleted.");
                                System.out.println();
                                q.removePerson(q, customerId);
                            } else {
                                System.out.println("No Customer with matching Id found.");
                                System.out.println();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number. ");
                            keyboard.nextLine();
                        }
                        break;
                    case "7": //Customer upgrade
                        do {
                            System.out.println("1. View Customers who wish to upgrade");//Have a Boolean attribute in customer object that indicates if they have requested an upgrade, then check that value here
                            System.out.println("2. Return to Employee menu");
                            EmployeeChoice = keyboard.next();
                            if (EmployeeChoice.equals("1")) {
                                ((Customer) p).getUpgradeList(true);
                                do {
                                    System.out.print("Would you like to upgrade a customer? Y/N: ");
                                    EmployeeChoice = keyboard.next();

                                    if (EmployeeChoice.equalsIgnoreCase("y")) {
                                        try {
                                            System.out.print("Enter Id of customer you wish to upgrade: ");
                                            int customerId = keyboard.nextInt();
                                            keyboard.nextLine();
                                            ((Customer) p).employeeUpgrade(customerId);
                                            break;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Please enter a valid number. ");
                                            keyboard.nextLine();
                                        }
                                    }
                                } while (!EmployeeChoice.equalsIgnoreCase("n"));
                            } else {
                                System.out.println("Please enter a valid option.");
                                System.out.println();
                            }
                        } while (!EmployeeChoice.equals("2"));
                    {
                        System.out.println("Returning to Employee menu");
                        System.out.println();
                    }
                    break;
                case "8":
                        System.out.println(((Album) g).showCombinedProfits());
                        break;
                case "9":
                        g.viewAll();//

                        System.out.println();
                        break;
                }
            } while (!EmployeeChoice.equals("10"));
            {
                System.out.println("Returning to Main Menu");
                System.out.println();
            }
        } else {
            System.out.println();
            System.out.println("Invalid Password. Returning to Main Menu");
            System.out.println();
        }

    }
}

//Daniel Dumville (Let's play DARTs)