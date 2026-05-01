package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       LedgerManager ledger = new LedgerManager();
        starterScreen();
        scanner.nextLine();
        String userInput = "";
        while(!userInput.trim().equalsIgnoreCase("X")) {
            homeDisplay();
            String choice="1";
            userInput = scanner.nextLine().trim().toUpperCase();
            switch (userInput) {
                case "D":
                    while(choice.equals("1")) {
                        ledger.enterDeposit(scanner);
                        System.out.println("Press 1 to add another deposit");
                        System.out.println("Or press 0 to go back");
                        choice = scanner.nextLine();
                    }
                    break;

                case "P":
                    while(choice.equals("1")) {
                        ledger.enterPayment(scanner);
                        System.out.println("Press 1 to add another payment");
                        System.out.println("Or press 0 to go back");
                        choice = scanner.nextLine();
                    }
                    break;

                case "L":
                    ledger.ledgerMenu(scanner);
                    break;
                case "X":
                    System.out.println("Exiting...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
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
    public static void starterScreen() {
        FileReader reader = null;
        try {
            reader = new FileReader("starterscreen.txt");
            BufferedReader bufReader = new BufferedReader(reader);
            String line;
            while (true) {
                if ((line = bufReader.readLine()) == null){
                    break;
                }
                System.out.println(line);
                Thread.sleep(150);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}