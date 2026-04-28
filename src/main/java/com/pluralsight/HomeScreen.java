package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactions = new ArrayList<>();
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
                    System.out.println("Enter deposit amount:");
                    //bufWriter.write(today.format(fmt) + " launch"+ "\n");
                    break;

                case "P":
                    System.out.println("You chose: Make a payment");
                    //bufWriter.write(today.format(fmt) + " search: " + searchTerm + "\n");
                    break;

                case "L":
                    System.out.println("You chose: Ledger");
                    //bufWriter.write(today.format(fmt) + " exit" + "\n");
                    break;
                case "X":
                    System.out.println("Exiting");
                    //bufWriter.write(today.format(fmt) + " exit" + "\n");
                    return;
                default:
                    System.out.println("Invalid option");
                    //bufWriter.write(today.format(fmt) + " exit" + "\n");

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