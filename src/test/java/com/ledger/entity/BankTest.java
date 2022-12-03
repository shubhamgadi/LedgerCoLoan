package com.ledger.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private static Bank bank;

    @BeforeAll
    public static void initialize() {
        bank = new Bank("IDID");
        bank.addLoan(new Loan("IDIDI", "Dale", 10000, 3, 7));
    }

    @Test
    public void should_return_loan_for_given_user() {
        Loan loan = bank.getLoanFor("Dale");
        assertNotNull(loan);
    }

    @Test
    public void should_return_null_loan_when_user_does_not_have_loan() {
        assertNull(bank.getLoanFor("Ana"));
    }

    @Test
    public void should_add_loan_in_bank() {
        assertNull(bank.getLoanFor("Kite"));
        bank.addLoan(new Loan("IDIDI", "Kite", 10000, 3, 7));
        Loan loan = bank.getLoanFor("Kite");
        assertNotNull(loan);
    }
}