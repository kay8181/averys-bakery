package com.pluralsight;


import java.util.Date;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class LedgerManager {

    public LedgerManager() {
    }

    public static void ledgerDisplay() {
        System.out.println("===========================");
        System.out.println("           Ledger          ");
        System.out.println("===========================");
        //System.out.println("What would you like to do?");
        System.out.println("A. All\nD. Deposits\nP. Payments\nR. Reports\nH. Home");
    }

    public static void reportsDisplay() {
            System.out.println("===========================");
            System.out.println("       Reports/Search      ");
            System.out.println("===========================");
            //System.out.println("What would you like to do?");
            System.out.println("1. Month To Date\n2. Previous Month\n3. Year To Date\n" +
                    "4. Previous Year\n5. Search by Vendor\n6. Custom Search\n0. Back\nH. Home");
    }
    public static void customSearch() {
        System.out.println("===========================");
        System.out.println("           Search          ");
        System.out.println("===========================");
        //System.out.println("What would you like to do?");
        System.out.println("1. Start Date\n2. End Date\n3. Description\n4. Vendor\n5. Amounts");

    }
    public static void enterDebit(Scanner scanner) {
        System.out.println("Enter debit amount:");
        double amount=scanner.nextDouble();
        System.out.println("Enter the vendor");
        String vendor= scanner.nextLine();
        System.out.println("Enter the description");
        String description= scanner.nextLine();
        System.out.println("Enter the time");
        LocalTime time = LocalTime.now();
        System.out.println("Enter the date");
        LocalDate date = LocalDate.now();
        Transaction t1 = new Transaction(date, time, description, vendor, amount);
    }
    public static void enterPayment(Scanner scanner) {
        System.out.println("Enter the payment amount:");
        System.out.println("Enter the vendor");
        System.out.println("Enter the description");
        System.out.println("Enter the time");
        System.out.println("Enter the date");
         scanner.nextLine();

    }

}
