package com.pluralsight;


import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
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
                    System.out.println("Press 0 to go back");
                    scanner.nextLine();
                    break;
                case "D":
                    System.out.println("Deposits:");
                    depositTransactions();
                    System.out.println("Press 0 to go back");
                    scanner.nextLine();
                    break;

                case "P":
                    System.out.println("Payments:");
                    paymentTransactions();
                    System.out.println("Press 0 to go back");
                    scanner.nextLine();
                    break;
                case "R":
                    reportsMenu(scanner);
                    break;
                case "H":
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
        try {
            double amount = scanner.nextDouble();
            while (amount <= 0) {
                System.out.println("Invalid input, deposit must be positive. Try again...");
                System.out.println("Enter deposit amount:");
                amount = scanner.nextDouble();
            }
            System.out.println("Enter the vendor:");
            scanner.nextLine();
            String vendor = scanner.nextLine();
            System.out.println("Enter the description:");
            String description = scanner.nextLine();
            LocalTime time = LocalTime.now();
            LocalDate date = LocalDate.now();
            System.out.println("Adding deposit...");
            Transaction t1 = new Transaction(date, time, description, vendor, amount);
            System.out.println(t1.transactionString(fmtDate,fmtTime));
            this.transactions.add(t1);
            appendToCsv("transactions.csv", t1);
        }
        catch (Exception e) {
            scanner.nextLine();
            System.out.println("Error! Try again...");
        }

    }
    public void enterPayment(Scanner scanner) {
        System.out.println("Enter payment amount:");
        try {
            double amount = scanner.nextDouble();
            while (amount >= 0) {
                System.out.println("Invalid input, payment must be negative. Try again...");
                System.out.println("Enter payment amount:");
                amount = scanner.nextDouble();
            }
            System.out.println("Enter the vendor:");
            scanner.nextLine();
            String vendor = scanner.nextLine();
            System.out.println("Enter the description:");
            String description = scanner.nextLine();
            LocalTime time = LocalTime.now();
            LocalDate date = LocalDate.now();
            System.out.println("Adding payment...");
            Transaction t1 = new Transaction(date, time, description, vendor, amount);
            System.out.println(t1.transactionString(fmtDate,fmtTime));
            this.transactions.add(t1);
            appendToCsv("transactions.csv", t1);

        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Error! Try again...");
        }
    }
    public void allTransactions() {
        sortTransactions(transactions);
        for(Transaction t: transactions) {
            System.out.printf(t.transactionString(fmtDate, fmtTime));
        }
    }
    public void depositTransactions() {
        sortTransactions(transactions);
        for(Transaction t: transactions) {
            if(t.getAmount()>0) {
                System.out.printf(t.transactionString(fmtDate, fmtTime));
            }
        }
    }
    public void paymentTransactions() {
        sortTransactions(transactions);
        for(Transaction t: transactions) {
            if(t.getAmount()<0) {
                System.out.printf(t.transactionString(fmtDate, fmtTime));
            }

        }
    }
    public void reportsMenu(Scanner scanner) {
        int userInput=-1;
        while(userInput!=0) {
           reportsDisplay();
           try {
               userInput = scanner.nextInt();
           } catch (Exception e){
               scanner.nextLine();
           }
            switch (userInput) {
                case 1:
                    System.out.println("Month to Date:");
                    monthToDate();
                    scanner.nextLine();
                    System.out.println("Press 0 to go back");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Previous Month:");
                    previousMonth();
                    scanner.nextLine();
                    System.out.println("Press 0 to go back");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Year to Date:");
                    yearToDate();
                    scanner.nextLine();
                    System.out.println("Press 0 to go back");
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Previous Year:");
                    previousYear();
                    scanner.nextLine();
                    System.out.println("Press 0 to go back");
                    scanner.nextLine();
                    break;
                case 5:
                    System.out.println("===========================");
                    System.out.println("      Search by vendor     ");
                    System.out.println("===========================");
                    searchByVendor(scanner);
                    System.out.println("Press 0 to go back");
                    scanner.nextLine();
                    break;
                case 6:
                    String choice="1";
                    System.out.println("===========================");
                    System.out.println("       Custom Search       ");
                    System.out.println("===========================");
                        customSearch(scanner);
                        System.out.println("Press 0 to go back");
                        scanner.nextLine();
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
                System.out.printf(t.transactionString(fmtDate, fmtTime));
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
                System.out.printf(t.transactionString(fmtDate, fmtTime));
            }
        }

    }
    public void yearToDate() {
        sortTransactions(transactions);
        LocalDate date = LocalDate.now();
        Year currentYear = Year.from(date);
        for(Transaction t: transactions) {
            if (Year.from(t.getDate()).equals(currentYear)) {
                System.out.printf(t.transactionString(fmtDate, fmtTime));
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
                System.out.printf(t.transactionString(fmtDate, fmtTime));
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
                System.out.printf(t.transactionString(fmtDate, fmtTime));
            }
        }

    }
    public void customSearch(Scanner scanner) {
        scanner.nextLine();
        String startDate;
        String endDate;
        String description;
        String vendor;
        String amount;
        LocalDate localStartDate = null;
        LocalDate localEndDate = null;
        boolean startDateFound;
        boolean endDateFound;
        boolean descriptionFound;
        boolean vendorFound;
        boolean amountFound=true;
        System.out.println("Fill in desired search values below");
        System.out.println("Start date:");
        startDate = scanner.nextLine();
        System.out.println("End date:");
        endDate = scanner.nextLine();
        if (!startDate.isBlank() && !endDate.isBlank()) {
            try {
                localStartDate = LocalDate.parse(startDate);
                localEndDate = LocalDate.parse(endDate);
            } catch (Exception e) {
                System.out.println("Error! Dates are not in the correct format");
            }
        }
        System.out.println("Description:");
        description = scanner.nextLine();
        System.out.println("Vendor:");
        vendor = scanner.nextLine();
        System.out.println("Amount:");
        amount = scanner.nextLine();

        sortTransactions(transactions);
        System.out.println("Results:");
        for(Transaction t: transactions) {
            if(localStartDate == null) {
                startDateFound = true;
            }
            else {
                if(t.getDate().isAfter(localStartDate)) {
                    startDateFound = true;
                }
                else{
                    startDateFound = false;
                }
            }
            if(localEndDate == null) {
                endDateFound = true;
            }
            else {
                if(t.getDate().isBefore(localEndDate)) {
                    endDateFound = true;
                }
                else{
                    endDateFound = false;
                }
            }
            if(description.isBlank()) {
                descriptionFound = true;
            }
            else {
                if(t.getDescription().equalsIgnoreCase(description)) {
                    descriptionFound = true;
                }
                else{
                    descriptionFound = false;
                }
            }
            if(vendor.isBlank()) {
                vendorFound = true;
            }
            else {
                if(t.getVendor().equalsIgnoreCase(vendor)) {
                    vendorFound = true;
                }
                else{
                    vendorFound = false;
                }
            }
            if(amount.isBlank()) {
                amountFound = true;
            }
            else {
                try {
                    double amountvalue = Double.parseDouble(amount);

                    if (t.getAmount() == amountvalue) {
                        amountFound = true;
                    } else {
                        amountFound = false;
                    }
                } catch (Exception e){
                    System.out.println("Error! Amount in wrong format");
                    amountFound = true;
                    }
            }
            if (startDateFound && endDateFound && descriptionFound && vendorFound && amountFound) {
                System.out.printf(t.transactionString(fmtDate, fmtTime));
            }
        }

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
    public void appendToCsv(String fileName, Transaction entry) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.append(entry.transactionStringCompacted(fmtDate, fmtTime));
            bufWriter.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sortTransactions(ArrayList<Transaction> transactions) {
        transactions.sort((o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
        Collections.reverse(transactions);
    }

}
