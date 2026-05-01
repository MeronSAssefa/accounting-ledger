package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Accounting {

    static Scanner scanner =  new Scanner(System.in);
    static ArrayList<Transaction> transactions = new ArrayList<>();


    public static void main(String[] args){

        transactions = loadTransactions();
        homeScreen();
    }

    public static void homeScreen(){
        boolean running = true;

        while(running) {
            System.out.println("=================================");
            System.out.println("      ACCOUNTING LEDGER APP      ");
            System.out.println("=================================");
            System.out.println("| D) Add Deposit                |");
            System.out.println("| P) Make Payment               |");
            System.out.println("| L) Ledger                     |");
            System.out.println("| X) Exit                       |");
            System.out.println("=================================");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().strip().toUpperCase();

            switch (choice) {
                case "D":
                    Deposit.addDeposit();
                    transactions = loadTransactions(); //refresh list so it can show up immediately
                    break;

                case "P":
                    Debit.makePayment();
                    transactions = loadTransactions();
                    break;

                case "L":
                    ledgerScreen();
                    break;

                case "X":
                    System.out.println("You have exited!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }

    public static void ledgerScreen(){
        boolean ledgerRunning = true;

          while(ledgerRunning){
            System.out.println("Ledger Screen");
            System.out.println("A) All");
            System.out.println("D) deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.println("Choose an option: ");
            String choice = scanner.nextLine().strip().toUpperCase();

            switch (choice) {

                case "A":
                    for(Transaction transaction : transactions){
                        printTransaction(transaction);
                    }
                    break;

                case "D":
                    for(Transaction transaction : transactions){
                        if (transaction.getAmount() > 0){
                           printTransaction(transaction);
                        }
                    }
                    break;

                case "P":
                    for(Transaction transaction : transactions){
                        if(transaction.getAmount() < 0){
                            printTransaction(transaction);
                        }
                    }
                    break;

                case "R":
                    reportScreen();
                    break;

                case "H":
                    ledgerRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
    public static void reportScreen(){
        System.out.println("-Reports Screen-");
        System.out.println("1) Month To Date");//transaction from this month
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search By Vendor");
        System.out.println("0) Back");

        System.out.println("choose an option");
        String choice = scanner.nextLine().strip();
        LocalDate today = LocalDate.now();

        switch(choice){
            case "1":
                for(Transaction transaction : transactions) {
                    LocalDate transactionDate = LocalDate.parse(transaction.getDate());

                    if(transactionDate.getMonth() == today.getMonth() &&
                       transactionDate.getYear() == today.getYear()){

                       printTransaction(transaction);
                    }

                }
                break;
            case"2":
                LocalDate lastMonth = today.minusMonths(1);
                for(Transaction transaction: transactions){

                    LocalDate transactionDate = LocalDate.parse(transaction.getDate());

                    if(transactionDate.getMonth() == lastMonth.getMonth() &&
                       transactionDate.getYear() == lastMonth.getYear()){

                       printTransaction(transaction);
                    }
                }

                break;
            case"3":
                for(Transaction transaction: transactions){
                    LocalDate transactionDate = LocalDate.parse(transaction.getDate());

                    if(transactionDate.getYear() == today.getYear()){
                        printTransaction(transaction);
                    }
                }
                break;
            case"4":
                LocalDate lastYear = today.minusYears(1);

                for(Transaction transaction : transactions){
                    LocalDate transactionDate =LocalDate.parse(transaction.getDate());

                    if(lastYear.getYear() == transactionDate.getYear()){
                       printTransaction(transaction);
                    }
                }
                break;
            case"5":
                System.out.println("Enter Vendor name: ");
                String vendorName = scanner.nextLine().strip();

                for(Transaction transaction: transactions)
                    if(transaction.getVendor().equalsIgnoreCase(vendorName)){
                        printTransaction(transaction);
                    }
                break;
            case"0":
                break;
            default:
                System.out.println("Invalid Choice");
                break;

        }
    }
    // created a method to avoid redundancy
    public static void printTransaction(Transaction transaction) {
        System.out.println(
                        transaction.getDate() + " | " +
                        transaction.getTime() + " | " +
                        transaction.getDescription() + " | " +
                        transaction.getVendor() + " | " +
                        transaction.getAmount()
        );
    }
    public static ArrayList<Transaction> loadTransactions(){
         String transactionFile = "transactions.csv";


         try{
            FileReader fileReader = new FileReader(transactionFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            line = reader.readLine();

            while(line != null){
                String[] columns = line.split("\\|");

                String date = columns[0];
                String time = columns[1];
                String description = columns[2];
                String vendor = columns[3];
                double amount = Double.parseDouble(columns[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);

                transactions.add(transaction);
                line =reader.readLine();

            }


        } catch (Exception ex) {
            System.out.println("Error reading your file: "+ ex.getMessage());
        }
      return transactions;
    }

}
