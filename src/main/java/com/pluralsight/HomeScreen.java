package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       LedgerManager ledger = new LedgerManager();
        String userInput = "";
        while(!userInput.trim().equalsIgnoreCase("X")) {
            homeDisplay();
            userInput = scanner.nextLine().trim().toUpperCase();
            switch (userInput) {
                case "D":
                    ledger.enterDeposit(scanner);

                    break;

                case "P":
                    ledger.enterPayment(scanner);

                    break;

                case "L":
                    ledger.ledgerMenu(scanner);
                    break;
                case "X":
                    System.out.println("Exiting...");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again...");

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