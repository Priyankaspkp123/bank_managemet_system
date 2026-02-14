# Bank Management System

A console-based banking application built with Java using OOP principles.

## Features
- Create new accounts (Saving/Current)
- View account details
- Deposit money
- Withdraw money with balance validation
- Transfer funds between accounts
- Check balance
- Delete accounts
- Display all accounts

## How to Run

### Console Version
```cmd
javac *.java
java BankApp
```

### GUI Version
```cmd
javac *.java
java BankGUI
```

## Project Structure
- `Account.java` - Account entity with encapsulation
- `Bank.java` - Core banking operations
- `BankApp.java` - Console application with menu
- `BankGUI.java` - GUI application using Swing

## OOP Concepts Used
- Encapsulation (private fields, getters)
- Exception handling (validation)
- Collections (HashMap for account storage)
- Clean separation of concerns
