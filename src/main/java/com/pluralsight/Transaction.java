package com.pluralsight;

import java.sql.Time;
import java.util.Date;

public class Transaction {
    Date date;
    Time time;
    String description;
    String vendor;
    double amount;

    public Transaction(Date date, Time time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }
    public Time getTime() {
        return time;
    }
    public String getDescription() {
        return description;
    }
    public String getVendor() {
        return vendor;
    }
    public double getAmount() {
        return amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
