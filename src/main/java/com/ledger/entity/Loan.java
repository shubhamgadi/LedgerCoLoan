package com.ledger.entity;

import java.util.ArrayList;
import java.util.List;

import static com.ledger.utils.LoanUtils.INSTANCE;

public class Loan {
    private final String bankName;
    private final String userName;
    private final int principalAmount;
    private final int interestAmount;
    private final int tenure;
    private final double rateOfInterest;
    private final int emi;
    private final List<LumpSumPayment> lumpSumPayments = new ArrayList<>();

    public Loan(String bankName, String userName, int principalAmount, int tenure, double rateOfInterest) {
        this.bankName = bankName;
        this.userName = userName;
        this.principalAmount = principalAmount;
        this.tenure = tenure;
        this.rateOfInterest = rateOfInterest;
        this.interestAmount = (int) INSTANCE.calculateInterest(principalAmount, rateOfInterest, tenure);
        this.emi = (int) INSTANCE.calculateEMI(this.principalAmount + this.interestAmount, tenure);
    }

    public String getBankName() {
        return bankName;
    }

    public String getUserName() {
        return userName;
    }

    public int getPrincipalAmount() {
        return principalAmount;
    }

    public int getTenure() {
        return tenure;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public int getEmi() {
        return emi;
    }

    public int getInterestAmount() {
        return interestAmount;
    }

    public boolean lumpSumPayment(LumpSumPayment payment) {
        return this.lumpSumPayments.add(payment);
    }

    public int getPayedAmount(int emiNumber) {
        int payedAmount = this.emi * emiNumber;
        for (LumpSumPayment lumpSumPayment : lumpSumPayments) {
            if (lumpSumPayment.getEmiNumber() <= emiNumber) {
                payedAmount += lumpSumPayment.getAmount();
            }
        }
        return payedAmount;
    }

    public int getPendingAmount(int emiNumber) {
        return this.interestAmount + this.principalAmount - getPayedAmount(emiNumber);
    }
}
