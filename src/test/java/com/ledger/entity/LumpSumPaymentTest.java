package com.ledger.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LumpSumPaymentTest {
    @Test
    public void should_create_lumpsum_correctly() {
        LumpSumPayment payment = new LumpSumPayment(10, 5000);
        assertEquals(5000, payment.getAmount());
        assertEquals(10, payment.getEmiNumber());
    }
}