import java.util.Scanner;

public class BankApp {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Welcome to Bank Management System ===");
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        viewAccount();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        withdraw();
                        break;
                    case 5:
                        transfer();
                        break;
                    case 6:
                        checkBalance();
                        break;
                    case 7:
                        deleteAccount();
                        break;
                    case 8:
                        bank.displayAllAccounts();
                        break;
                    case 9:
                        System.out.println("Thank you for using Bank Management System!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Bank Menu ===");
        System.out.println("1. Create Account");
        System.out.println("2. View Account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transfer");
        System.out.println("6. Check Balance");
        System.out.println("7. Delete Account");
        System.out.println("8. Show All Accounts");
        System.out.println("9. Exit");
    }


    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account type (Saving/Current): ");
        String type = scanner.nextLine();
        double initialBalance = getDoubleInput("Enter initial balance: ");
        
        String accountNumber = bank.createAccount(name, type, initialBalance);
        System.out.println("Account created successfully! Account Number: " + accountNumber);
    }

    private static void viewAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = bank.getAccount(accountNumber);
        System.out.println("\n" + account);
    }

    private static void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        double amount = getDoubleInput("Enter deposit amount: ");
        
        bank.deposit(accountNumber, amount);
        System.out.println("Deposit successful! New balance: $" + 
                String.format("%.2f", bank.getAccount(accountNumber).getBalance()));
    }

    private static void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        double amount = getDoubleInput("Enter withdrawal amount: ");
        
        bank.withdraw(accountNumber, amount);
        System.out.println("Withdrawal successful! New balance: $" + 
                String.format("%.2f", bank.getAccount(accountNumber).getBalance()));
    }

    private static void transfer() {
        System.out.print("Enter source account number: ");
        String fromAccount = scanner.nextLine();
        System.out.print("Enter destination account number: ");
        String toAccount = scanner.nextLine();
        double amount = getDoubleInput("Enter transfer amount: ");
        
        bank.transfer(fromAccount, toAccount, amount);
        System.out.println("Transfer successful!");
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = bank.getAccount(accountNumber);
        System.out.println("Current balance: $" + String.format("%.2f", account.getBalance()));
    }

    private static void deleteAccount() {
        System.out.print("Enter account number to delete: ");
        String accountNumber = scanner.nextLine();
        
        if (bank.deleteAccount(accountNumber)) {
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Invalid input. " + prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Invalid input. " + prompt);
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }
}
