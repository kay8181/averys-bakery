package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        //homeDisplay();
       LedgerManager ledger = new LedgerManager();
       //ledger.ledgerDisplay();
       //ledger.reportsDisplay();
       //ledger.customSearch();
        String userInput = "";
        while(!userInput.trim().equalsIgnoreCase("X")) {
            homeDisplay();
            userInput = scanner.nextLine().trim().toUpperCase();
            switch (userInput) {
                case "D":
                    System.out.println("You chose: Add a deposit");
                    ledger.enterDeposit(scanner);

                    break;

                case "P":
                    System.out.println("You chose: Make a payment");
                    ledger.enterPayment(scanner);
                    break;

                case "L":
                    System.out.println("You chose: Ledger");
                    ledger.ledgerDisplay();
                    break;
                case "X":
                    System.out.println("Exiting");
                    return;
                default:
                    System.out.println("Invalid option");


            }

        }

    }
    public static void homeDisplay() {
        System.out.println("===========================");
        System.out.println("         Home Screen       ");
        System.out.println("===========================");
        System.out.println("What would you like to do?");
        System.out.println("D. Add a deposit\nP. Make a payment\nL. Ledger\nX. Exit");

    }

}