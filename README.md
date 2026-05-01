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
│
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
└── README.md            # Project documentation