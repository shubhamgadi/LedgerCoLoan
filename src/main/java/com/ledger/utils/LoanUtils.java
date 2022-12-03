package com.ledger.utils;

public enum LoanUtils {
    INSTANCE;

    public double calculateEMI(double amount, double years) {
        return Math.ceil(amount / (years * 12));
    }

    public double calculateInterest(double amount, double rateOfInterest, double years) {
        return amount * years * rateOfInterest / 100d;
    }

    public double numberOfEmiFor(double amount, double emi) {
        return Math.ceil(amount / emi);
    }
}
