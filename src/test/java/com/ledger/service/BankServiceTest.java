package com.ledger.service;

import com.ledger.entity.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    @Test
    public void should_return_null_when_loan_is_not_with_bank() {
        BankService bankService = new BankService();
        Loan loan = new Loan("IDIDI", "Dale", 10000, 3, 7);

        Loan fetchedLoan = bankService.getLoan(loan.getBankName(), loan.getUserName());
        assertNull(fetchedLoan);
    }

    @Test
    public void should_add_loan_to_the_bank() {
        BankService bankService = new BankService();
        String[] tokens = "LOAN MBI Harry 10000 3 7".split(" ");
        bankService.processLoanRequest(tokens);
        Loan loan = bankService.getLoan("MBI", "Harry");
        assertEquals("MBI", loan.getBankName());
        assertEquals("Harry", loan.getUserName());
        assertEquals(10000, loan.getPrincipalAmount());
        assertEquals(2100, loan.getInterestAmount());
        assertEquals(337, loan.getEmi());
        assertEquals(3, loan.getTenure());
    }

    @Test
    public void should_return_balance_message_for_give_emiNumber() {
        BankService bankService = new BankService();
        String[] loanTokens = "LOAN MBI Harry 10000 3 7".split(" ");
        bankService.processLoanRequest(loanTokens);
        String[] balanceTokens = "BALANCE MBI Harry 12".split(" ");
        String message = bankService.processBalanceRequest(balanceTokens);
        assertEquals("MBI Harry 4044 24", message);
    }

    @Test
    public void should_return_balance_message_for_give_emiNumber_and_lumpsum_payment() {
        BankService bankService = new BankService();
        String[] loanTokens = "LOAN MBI Harry 10000 3 7".split(" ");
        bankService.processLoanRequest(loanTokens);
        String[] paymentTokens = "PAYMENT MBI Harry 5000 10".split(" ");
        bankService.processLumpsumPaymentRequest(paymentTokens);
        String[] balanceTokens = "BALANCE MBI Harry 12".split(" ");
        String message = bankService.processBalanceRequest(balanceTokens);
        assertEquals("MBI Harry 9044 10", message);
    }
}