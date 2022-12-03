package com.ledger.entity;

import lombok.Generated;

import java.util.Objects;

public class LumpSumPayment {
    private final double emiNumber;
    private final double amount;

    public LumpSumPayment(double emiNumber, double amount) {
        this.emiNumber = emiNumber;
        this.amount = amount;
    }

    public double getEmiNumber() {
        return emiNumber;
    }

    public double getAmount() {
        return amount;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LumpSumPayment that = (LumpSumPayment) o;
        return emiNumber == that.emiNumber && amount == that.amount;
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(emiNumber, amount);
    }

}
