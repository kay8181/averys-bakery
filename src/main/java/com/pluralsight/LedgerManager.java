package com.pluralsight;

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


}
