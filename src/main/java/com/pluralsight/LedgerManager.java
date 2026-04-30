package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LedgerManager {
    DateTimeFormatter fmtDate;
    DateTimeFormatter fmtTime;
    private ArrayList<Transaction> transactions;
    public LedgerManager() {
        fmtDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fmtTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        transactions = loadTransactions("transactions.csv");
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
                    depositTransactions();
                    break;

                case "P":
                    System.out.println("Payments:");
                    paymentTransactions();
                    break;
                case "R":
                    reportsMenu(scanner);
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
            System.out.println("1. Month to Date\n2. Previous Month\n3. Year to Date\n" +
                    "4. Previous Year\n5. Search by Vendor\n6. Custom Search\n0. Back");
    }
    public void customSearchDisplay() {
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
        sortTransactions(transactions);
        for(Transaction t: transactions) {
            System.out.printf(t.toString(fmtDate, fmtTime));
        }
    }
    public void depositTransactions() {
        sortTransactions(transactions);
        for(Transaction t: transactions) {
            if(t.getAmount()>0) {
                System.out.printf(t.toString(fmtDate, fmtTime));
            }
        }
    }
    public void paymentTransactions() {
        sortTransactions(transactions);
        for(Transaction t: transactions) {
            if(t.getAmount()<0) {
                System.out.printf(t.toString(fmtDate, fmtTime));
            }

        }
    }
    public void reportsMenu(Scanner scanner) {
        int userInput=-1;
        while(userInput!=0) {
           reportsDisplay();
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    System.out.println("Month to Date:");
                    monthToDate();
                    break;
                case 2:
                    System.out.println("Previous Month:");
                    previousMonth();
                    break;
                case 3:
                    System.out.println("Year to Date:");
                    yearToDate();
                    break;
                case 4:
                    System.out.println("Previous Year:");
                    previousYear();
                    break;
                case 5:
                    System.out.println("===========================");
                    System.out.println("      Search by vendor     ");
                    System.out.println("===========================");
                    searchByVendor(scanner);
                    break;
                case 6:
                    System.out.println("Custom Search:");
                    customSearch();
                    break;
                case 0:
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Invalid option. Try again...");
            }

        }
    }
    public void monthToDate() {
        sortTransactions(transactions);
        LocalDate date = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(date);
        for(Transaction t: transactions) {
            if(YearMonth.from(t.getDate()).equals(currentMonth)) {
                System.out.printf(t.toString(fmtDate, fmtTime));
            }
        }

    }
    public void previousMonth() {
        sortTransactions(transactions);
        LocalDate date = LocalDate.now();
        YearMonth currentMonth = YearMonth.from(date);
        YearMonth previousMonth = currentMonth.minusMonths(1);
        for(Transaction t: transactions) {
            if (YearMonth.from(t.getDate()).equals(previousMonth)) {
                System.out.printf(t.toString(fmtDate, fmtTime));
            }
        }

    }
    public void yearToDate() {
        sortTransactions(transactions);
        LocalDate date = LocalDate.now();
        Year currentYear = Year.from(date);
        for(Transaction t: transactions) {
            if (Year.from(t.getDate()).equals(currentYear)) {
                System.out.printf(t.toString(fmtDate, fmtTime));
            }
        }

    }
    public void previousYear() {
        sortTransactions(transactions);
        LocalDate date = LocalDate.now();
        Year currentYear = Year.from(date);
        Year previousYear = currentYear.minusYears(1);
        for(Transaction t: transactions) {
            if (Year.from(t.getDate()).equals(previousYear)) {
                System.out.printf(t.toString(fmtDate, fmtTime));
            }
        }

    }
    public void searchByVendor(Scanner scanner){
        System.out.println("Enter vendor name:");
        scanner.nextLine();
        String userInput = scanner.nextLine();
        sortTransactions(transactions);
        for(Transaction t: transactions) {
            if (t.getVendor().equalsIgnoreCase(userInput)) {
                System.out.printf(t.toString(fmtDate, fmtTime));
            }
        }

    }
    public void customSearch() {

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public ArrayList<Transaction> loadTransactions(String fileName) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line;
            bufReader.readLine();
            while ((line = bufReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    LocalTime time= LocalTime.parse(parts[1].trim());
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts [4].trim());

                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
            bufReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;

    }
    public void sortTransactions(ArrayList<Transaction> transactions) {
        transactions.sort((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
        Collections.reverse(transactions);
    }
}
