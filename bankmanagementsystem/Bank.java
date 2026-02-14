import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;
    private int accountCounter;

    public Bank() {
        accounts = new HashMap<>();
        accountCounter = 1000;
    }

    public String createAccount(String holderName, String accountType, double initialBalance) {
        String accountNumber = "ACC" + (++accountCounter);
        Account account = new Account(accountNumber, holderName, accountType, initialBalance);
        accounts.put(accountNumber, account);
        return accountNumber;
    }

    public Account getAccount(String accountNumber) throws IllegalArgumentException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return account;
    }

    public void deposit(String accountNumber, double amount) throws IllegalArgumentException {
        Account account = getAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws IllegalArgumentException {
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
    }

    public void transfer(String fromAccount, String toAccount, double amount) throws IllegalArgumentException {
        Account from = getAccount(fromAccount);
        Account to = getAccount(toAccount);
        from.withdraw(amount);
        to.deposit(amount);
    }

    public boolean deleteAccount(String accountNumber) {
        return accounts.remove(accountNumber) != null;
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("\n=== All Accounts ===");
        for (Account account : accounts.values()) {
            System.out.println(account);
        }
    }

    public String getAllAccountsAsString() {
        if (accounts.isEmpty()) {
            return "No accounts found.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Account account : accounts.values()) {
            sb.append(account.toString()).append("\n");
        }
        return sb.toString();
    }
}
