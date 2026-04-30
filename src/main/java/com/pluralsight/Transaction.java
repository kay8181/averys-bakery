package com.pluralsight;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private LocalDateTime dateTime;
    private String description;
    private String vendor;
    private double amount;


    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.dateTime = LocalDateTime.of(date, time);
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
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

    public String toString(DateTimeFormatter fmtDate, DateTimeFormatter fmtTime) {
        String formattedDate = date.format(fmtDate);
        String formattedTime = time.format(fmtTime);
        return String.format("%s | %s | %s | %s | $%.2f\n", formattedDate, formattedTime, description,
                vendor, amount);
    }

}
