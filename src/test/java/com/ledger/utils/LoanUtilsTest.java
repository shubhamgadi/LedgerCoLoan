package com.ledger.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanUtilsTest {

    @Test
    public void should_return_EMI_amount() {
        double emi = LoanUtils.INSTANCE.calculateEMI(36000, 3);
        assertEquals(1000, emi);
    }

    @Test
    public void should_return_ceiling_value_of_the_EMI() {
        double emi = LoanUtils.INSTANCE.calculateEMI(36100, 3);
        assertEquals(1003, emi);
    }

    @Test
    public void should_return_interest_amount() {
        double interest = LoanUtils.INSTANCE.calculateInterest(1000, 7, 3);
        assertEquals(210, interest);
    }

    @Test
    public void should_return_number_of_EMI() {
        double numberOfEmi = LoanUtils.INSTANCE.numberOfEmiFor(1000, 10);
        assertEquals(100, numberOfEmi);
    }

    @Test
    public void should_return_ceiling_of_number_of_EMI() {
        double numberOfEmi = LoanUtils.INSTANCE.numberOfEmiFor(1050, 100);
        assertEquals(11, numberOfEmi);
    }
}