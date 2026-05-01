
package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

    public class CustomSearch {

        public static void search(ArrayList<Transaction> transactions, Scanner scanner) {

            System.out.println("=== Custom Search ===");
           //ask  until they enter
            System.out.print("Start Date (YYYY-MM-DD) or press Enter: ");
            String startDateInput = scanner.nextLine().strip();

            System.out.print("End Date (YYYY-MM-DD) or press Enter: ");
            String endDateInput = scanner.nextLine().strip();

            System.out.print("Description or press Enter: ");
            String descriptionInput = scanner.nextLine().strip();

            System.out.print("Vendor or press Enter: ");
            String vendorInput = scanner.nextLine().strip();

            System.out.print("Amount or press Enter: ");
            String amountInput = scanner.nextLine().strip();

            boolean found = false;

            for (Transaction transaction : transactions) {
                boolean matches = true;

                LocalDate transactionDate = LocalDate.parse(transaction.getDate());

                if (!startDateInput.isEmpty()) {
                    LocalDate startDate = LocalDate.parse(startDateInput);

                    if (transactionDate.isBefore(startDate)) {
                        matches = false;
                    }
                }

                if (!endDateInput.isEmpty()) {
                    LocalDate endDate = LocalDate.parse(endDateInput);

                    if (transactionDate.isAfter(endDate)) {
                        matches = false;
                    }
                }

                if (!descriptionInput.isEmpty()) {
                    if (!transaction.getDescription().toLowerCase().contains(descriptionInput.toLowerCase())) {
                        matches = false;
                    }
                }

                if (!vendorInput.isEmpty()) {
                    if (!transaction.getVendor().equalsIgnoreCase(vendorInput)) {
                        matches = false;
                    }
                }

                if (!amountInput.isEmpty()) {
                    double amount = Double.parseDouble(amountInput);

                    if (transaction.getAmount() != amount) {
                        matches = false;
                    }
                }

                if (matches) {
                    Accounting.printTransaction(transaction);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No transactions found.");
            }
        }
    }

