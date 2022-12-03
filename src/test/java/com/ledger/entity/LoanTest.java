package com.ledger.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoanTest {

    @Test
    public void should_create_Loan_correctly() {
        Loan loan = new Loan("IDIDI", "Dale", 10000, 3, 7);
        assertEquals("IDIDI", loan.getBankName());
        assertEquals("Dale", loan.getUserName());
        assertEquals(10000, loan.getPrincipalAmount());
        assertEquals(7, loan.getRateOfInterest());
        assertEquals(3, loan.getTenure());
        assertEquals(2100, loan.getInterestAmount());
        assertEquals(337, loan.getEmi());
    }

    @Test
    public void should_return_payed_amount_for_given_emiNumber() {
        Loan loan = new Loan("IDIDI", "Dale", 10000, 3, 7);
        assertEquals(3033, loan.getPayedAmount(9));
    }

    @Test
    public void should_return_payed_amount_for_given_emiNumber_and_lumpsum_payments() {
        Loan loan = new Loan("IDIDI", "Dale", 10000, 3, 7);
        LumpSumPayment payment = new LumpSumPayment(10, 5000);
        loan.lumpSumPayment(payment);
        assertEquals(8370, loan.getPayedAmount(10));
    }

    @Test
    public void should_return_pending_amount_for_given_emiNumber() {
        Loan loan = new Loan("IDIDI", "Dale", 10000, 3, 7);
        assertEquals(9067, loan.getPendingAmount(9));
    }

    @Test
    public void should_return_pending_amount_for_given_emiNumber_and_lumpsum_payments_with_same_emiNumber() {
        Loan loan = new Loan("IDIDI", "Dale", 10000, 3, 7);
        LumpSumPayment payment = new LumpSumPayment(10, 5000);
        loan.lumpSumPayment(payment);
        assertEquals(3730, loan.getPendingAmount(10));
    }

    @Test
    public void should_return_pending_amount_for_given_emiNumber_and_lumpsum_payments_with_different_emiNumber() {
        Loan loan = new Loan("IDIDI", "Dale", 10000, 3, 7);
        LumpSumPayment payment = new LumpSumPayment(9, 5000);
        loan.lumpSumPayment(payment);
        assertEquals(3730, loan.getPendingAmount(10));
    }
}