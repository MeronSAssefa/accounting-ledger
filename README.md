# Accounting Ledger App
![Home Screen](images/home-screen.png)
## Description
This is a java console application that allows user to track deposits and payments.
The program reads and writes data from a CSV file and lets users view transactions in different ways.
## Project Structure

 ``` text
accounting-ledger/
├── images/
│   └── home-screen.png
├── src/
│   └── main/
│       └── java/
│           └── com/pluralsight/
│               ├── Accounting.java      # Main app (menus & navigation)
│               ├── Transaction.java     # Transaction object (data model)
│               ├── Deposit.java         # Handles deposits
│               └── Debit.java           # Handles payments
│
├── pom.xml              # Project configuration (Maven)
├── transactions.csv     # Stores all transactions
└── README.md           # Project documentation
 ```
## Program Flow 
``` text
User Input → Home Screen
├── Add Deposit → Save to CSV
├── Make Payment → Save to CSV
└── Ledger Screen
     ├── View All
     ├── View Deposits
     ├── View Payments
     └── Reports
        ├── Month To Date
        ├── Previous Month
        ├── Year To Date
        ├── Previous Year
        └── Search by Vendor
```

## Interesting code 
``` text
System.out.print("Save this deposit? (Y/N): ");
String confirm = scanner.nextLine().strip().toUpperCase();

if (!confirm.equals("Y")) {
    System.out.println("Payment canceled.");
    return;
}
```

## Challenges encountered

One challenge I faced was when I was working on the report features like Previous Month and Previous Year.  
At first, nothing was showing up, and I thought my code was broken.

Later, I realized that my `transactions.csv` file only had data from April 2026, so there were no transactions from other months or years to display.  
After adding more sample data, the reports started working correctly.
