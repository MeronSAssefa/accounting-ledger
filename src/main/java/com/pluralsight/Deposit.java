package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Deposit {

    static Scanner scanner = new Scanner(System.in);
    public static void addDeposit(){
        System.out.println("--Add Deposit Screen--");

        System.out.println("Enter the description: ");
        String description = scanner.nextLine().strip();

        System.out.println("Enter the Vendor name: ");
        String vendor = scanner.nextLine().strip();

        System.out.println("Enter the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if(amount < 0 ){
            amount = amount *-1;
        }

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        String depositLine = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
        String transactionFile = "transactions.csv";

       try{
           FileWriter fileWriter = new FileWriter(transactionFile, true);
           BufferedWriter writer = new BufferedWriter(fileWriter);

           writer.write(depositLine);
           writer.newLine();

           writer.close();

           System.out.println("Deposit saved!");

       }
       catch (Exception ex) {
           System.out.println("Error Writing to the file ");
       }
    }
}
