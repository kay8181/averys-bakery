package com.pluralsight;


import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class LedgerManager {
    private ArrayList<Transaction> transactions;
    public LedgerManager() {
        transactions = new ArrayList<>();
    }
    public void ledgerMenu(Scanner scanner) {
        String userInput = "";
        while(!userInput.trim().equalsIgnoreCase("H")) {
            ledgerDisplay();
            userInput = scanner.nextLine().trim().toUpperCase();
            switch (userInput) {
                case "A":
                    System.out.println("All:");
                    allTransactions();

                    break;

                case "D":
                    System.out.println("Deposits:");

                    break;

                case "P":
                    System.out.println("Payments:");

                    break;
                case "R":
                    System.out.println("Reports:");
                    break;
                case "H":
                    System.out.println("Returning to home screen...");
                    return;
                default:
                    System.out.println("Invalid option. Try again...");


            }
        }
    }

     public void ledgerDisplay() {
        System.out.println("===========================");
        System.out.println("           Ledger          ");
        System.out.println("===========================");
        //System.out.println("What would you like to do?");
        System.out.println("A. All\nD. Deposits\nP. Payments\nR. Reports\nH. Home");
    }

    public void reportsDisplay() {
            System.out.println("===========================");
            System.out.println("       Reports/Search      ");
            System.out.println("===========================");
            //System.out.println("What would you like to do?");
            System.out.println("1. Month To Date\n2. Previous Month\n3. Year To Date\n" +
                    "4. Previous Year\n5. Search by Vendor\n6. Custom Search\n0. Back\nH. Home");
    }
    public void customSearch() {
        System.out.println("===========================");
        System.out.println("           Search          ");
        System.out.println("===========================");
        //System.out.println("What would you like to do?");
        System.out.println("1. Start Date\n2. End Date\n3. Description\n4. Vendor\n5. Amounts");

    }
    public void enterDeposit(Scanner scanner) {
        System.out.println("Enter deposit amount:");
        double amount=scanner.nextDouble();
        while(amount<=0) {
            System.out.println("Invalid input, deposit must be positive. Try again...");
            System.out.println("Enter deposit amount:");
            amount=scanner.nextDouble();
        }

        System.out.println("Enter the vendor");
        scanner.nextLine();
        String vendor= scanner.nextLine();
        System.out.println("Enter the description");
        String description= scanner.nextLine();
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        System.out.println("Adding deposit...");
        Transaction t1 = new Transaction(date, time, description, vendor, amount);
        this.transactions.add(t1);

    }
    public void enterPayment(Scanner scanner) {
        System.out.println("Enter payment amount:");
        double amount=scanner.nextDouble();
        while(amount>=0) {
            System.out.println("Invalid input, payment must be negative. Try again...");
            System.out.println("Enter payment amount:");
            amount = scanner.nextDouble();
        }
        System.out.println("Enter the vendor");
        scanner.nextLine();
        String vendor= scanner.nextLine();
        System.out.println("Enter the description");
        String description= scanner.nextLine();
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        System.out.println("Adding payment...");
        Transaction t1 = new Transaction(date, time, description, vendor, amount);
        this.transactions.add(t1);


    }
    public void allTransactions() {
        for(Transaction t: transactions) {
            System.out.println(t.getAmount() + t.getDescription());
        }

    }

}
