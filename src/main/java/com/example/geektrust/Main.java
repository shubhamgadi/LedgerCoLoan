package com.example.geektrust;

import com.ledger.service.BankService;
import lombok.Generated;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Generated
public class Main {
    private static final BankService bankService = new BankService();
    private static final int COMMAND = 0;

    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(args[0]));
            for (String line : allLines) {
                String[] tokens = line.trim().split(" ");
                String command = tokens[COMMAND];
                if ("LOAN".equals(command)) {
                    bankService.processLoanRequest(tokens);
                } else if ("PAYMENT".equals(command)) {
                    bankService.processLumpsumPaymentRequest(tokens);
                } else if ("BALANCE".equals(command)) {
                    String message = bankService.processBalanceRequest(tokens);
                    System.out.println(message);
                } else {
                    System.out.println("Invalid line");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
