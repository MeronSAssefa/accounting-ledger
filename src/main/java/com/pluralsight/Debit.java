package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Debit {
   static Scanner scanner = new Scanner(System.in);
    public static void makePayment(){
        System.out.println("--Make Payment screen--");

        System.out.println("Enter description: ");
        String description = scanner.nextLine().strip();

        System.out.println("Enter the Vendor name: ");
        String vendor = scanner.nextLine().strip();

        System.out.println("Enter the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if(amount > 0){
            amount = amount * -1;
        }
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String paymentLine= date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
        String transactionFile = "transactions.csv";

        System.out.println();
        System.out.println("Please confirm this payment:");
        System.out.println("--------------------------------");
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Description: " + description);
        System.out.println("Vendor: " + vendor);
        System.out.println("Amount: $" + amount);
        System.out.println("--------------------------------");

        System.out.print("Save this payment? (Y/N): ");
        String confirm = scanner.nextLine().strip().toUpperCase();

        if (!confirm.equals("Y")) {
            System.out.println("Payment canceled.");
            return;
        }

        try{
            FileWriter fileWriter = new FileWriter(transactionFile, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(paymentLine);
            writer.newLine();

            writer.close();

            System.out.println("Payment saved!");

        }
        catch (Exception ex) {
            System.out.println("Error Writing to the file ");
        }


    }
}
