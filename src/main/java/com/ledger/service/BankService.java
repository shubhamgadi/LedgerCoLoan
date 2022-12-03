package com.ledger.service;

import com.ledger.entity.Bank;
import com.ledger.entity.Loan;
import com.ledger.entity.LumpSumPayment;
import com.ledger.utils.LoanUtils;

import java.util.HashMap;
import java.util.Map;

public class BankService {

    private final int BANK_NAME = 1;
    private final int USER_NAME = 2;
    private final int PRINCIPAL = 3;
    private final int TENURE = 4;
    private final int RATE_OF_INTEREST = 5;
    private final int BALANCE_REQUEST_EMI_NUMBER = 3;
    private final int PAYMENT_REQUEST_EMI_NUMBER = 4;

    private final Map<String, Bank> banks = new HashMap<>();

    private void addLoan(Loan loan) {
        banks.computeIfAbsent(loan.getBankName(), e -> new Bank(loan.getBankName())).addLoan(loan);
    }

    public Loan getLoan(String bankName, String userName) {
        if (banks.containsKey(bankName)) {
            return banks.get(bankName).getLoanFor(userName);
        }
        return null;
    }

    public String processBalanceRequest(String[] tokens) {
        Loan loan = getLoan(tokens[BANK_NAME], tokens[USER_NAME]);
        int emiNumber = Integer.parseInt(tokens[BALANCE_REQUEST_EMI_NUMBER]);
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(loan.getBankName()).append(" ");
        messageBuilder.append(loan.getUserName()).append(" ");
        messageBuilder.append(loan.getPayedAmount(emiNumber));

        int pendingEMI = (int) LoanUtils.INSTANCE.numberOfEmiFor(loan.getPendingAmount(emiNumber), loan.getEmi());
        messageBuilder.append(" ").append(pendingEMI);
        return messageBuilder.toString();
    }

    public void processLumpsumPaymentRequest(String[] tokens) {
        Loan loan = getLoan(tokens[BANK_NAME], tokens[USER_NAME]);
        int emiNumber = Integer.parseInt(tokens[PAYMENT_REQUEST_EMI_NUMBER]);
        int lumpsumAmount = Integer.parseInt(tokens[PRINCIPAL]);
        LumpSumPayment lumpSumPayment = new LumpSumPayment(emiNumber, lumpsumAmount);
        loan.lumpSumPayment(lumpSumPayment);
    }

    public void processLoanRequest(String[] tokens) {
        Loan loan = new Loan(tokens[BANK_NAME], tokens[USER_NAME],
                Integer.parseInt(tokens[PRINCIPAL]),
                Integer.parseInt(tokens[TENURE]),
                Double.parseDouble(tokens[RATE_OF_INTEREST]));
        addLoan(loan);
    }
}
