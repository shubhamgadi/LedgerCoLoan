package com.ledger.entity;

import lombok.Generated;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bank {
    private final String name;
    private final Map<String, Loan> loans = new HashMap<>();

    public Bank(String name) {
        this.name = name;
    }

    public Loan getLoanFor(String userName) {
        return loans.get(userName);
    }

    public void addLoan(Loan loan) {
        loans.put(loan.getUserName(), loan);
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name);
    }

    @Generated
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
