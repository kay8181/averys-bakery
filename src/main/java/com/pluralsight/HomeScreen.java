package com.pluralsight;

public class HomeScreen {
    public static void main(String[] args) {
       homeDisplay();
       LedgerManager ledger = new LedgerManager();
       ledger.ledgerDisplay();
       ledger.reportsDisplay();
       ledger.customSearch();
    }
    public static void homeDisplay() {
        System.out.println("===========================");
        System.out.println("         Home Screen       ");
        System.out.println("===========================");
        System.out.println("What would you like to do?");
        System.out.println("D. Add a deposit\nP. Make a payment\nL. Ledger\nX. Exit");

    }
}