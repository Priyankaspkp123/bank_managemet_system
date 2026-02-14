import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankGUI extends JFrame {
    private Bank bank;
    private JTextArea outputArea;
    
    public BankGUI() {
        bank = new Bank();
        setTitle("Bank Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(41, 128, 185));
        JLabel titleLabel = new JLabel("Bank Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton createBtn = createButton("Create Account");
        JButton viewBtn = createButton("View Account");
        JButton depositBtn = createButton("Deposit Money");
        JButton withdrawBtn = createButton("Withdraw Money");
        JButton transferBtn = createButton("Transfer Money");
        JButton balanceBtn = createButton("Check Balance");
        JButton deleteBtn = createButton("Delete Account");
        JButton showAllBtn = createButton("Show All Accounts");
        JButton clearBtn = createButton("Clear Output");
        JButton exitBtn = createButton("Exit");
        
        buttonPanel.add(createBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(balanceBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(showAllBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(exitBtn);
        
        add(buttonPanel, BorderLayout.WEST);
        
        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Button Actions
        createBtn.addActionListener(e -> createAccount());
        viewBtn.addActionListener(e -> viewAccount());
        depositBtn.addActionListener(e -> deposit());
        withdrawBtn.addActionListener(e -> withdraw());
        transferBtn.addActionListener(e -> transfer());
        balanceBtn.addActionListener(e -> checkBalance());
        deleteBtn.addActionListener(e -> deleteAccount());
        showAllBtn.addActionListener(e -> showAllAccounts());
        clearBtn.addActionListener(e -> outputArea.setText(""));
        exitBtn.addActionListener(e -> System.exit(0));
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setFocusPainted(false);
        return button;
    }

    
    private void createAccount() {
        JTextField nameField = new JTextField();
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Saving", "Current"});
        JTextField balanceField = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Account Holder Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Account Type:"));
        panel.add(typeBox);
        panel.add(new JLabel("Initial Balance:"));
        panel.add(balanceField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Create Account", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String type = (String) typeBox.getSelectedItem();
                double balance = Double.parseDouble(balanceField.getText());
                
                String accountNumber = bank.createAccount(name, type, balance);
                outputArea.append("✓ Account created successfully!\n");
                outputArea.append("Account Number: " + accountNumber + "\n\n");
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        }
    }
    
    private void viewAccount() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter Account Number:");
        if (accountNumber != null && !accountNumber.trim().isEmpty()) {
            try {
                Account account = bank.getAccount(accountNumber);
                outputArea.append("=== Account Details ===\n");
                outputArea.append(account.toString() + "\n\n");
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        }
    }
    
    private void deposit() {
        JTextField accountField = new JTextField();
        JTextField amountField = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Account Number:"));
        panel.add(accountField);
        panel.add(new JLabel("Deposit Amount:"));
        panel.add(amountField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Deposit Money", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String accountNumber = accountField.getText().trim();
                double amount = Double.parseDouble(amountField.getText());
                
                bank.deposit(accountNumber, amount);
                Account account = bank.getAccount(accountNumber);
                outputArea.append("✓ Deposit successful!\n");
                outputArea.append("New Balance: $" + String.format("%.2f", account.getBalance()) + "\n\n");
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        }
    }
    
    private void withdraw() {
        JTextField accountField = new JTextField();
        JTextField amountField = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Account Number:"));
        panel.add(accountField);
        panel.add(new JLabel("Withdrawal Amount:"));
        panel.add(amountField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Withdraw Money", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String accountNumber = accountField.getText().trim();
                double amount = Double.parseDouble(amountField.getText());
                
                bank.withdraw(accountNumber, amount);
                Account account = bank.getAccount(accountNumber);
                outputArea.append("✓ Withdrawal successful!\n");
                outputArea.append("New Balance: $" + String.format("%.2f", account.getBalance()) + "\n\n");
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        }
    }
    
    private void transfer() {
        JTextField fromField = new JTextField();
        JTextField toField = new JTextField();
        JTextField amountField = new JTextField();
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("From Account:"));
        panel.add(fromField);
        panel.add(new JLabel("To Account:"));
        panel.add(toField);
        panel.add(new JLabel("Transfer Amount:"));
        panel.add(amountField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Transfer Money", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String fromAccount = fromField.getText().trim();
                String toAccount = toField.getText().trim();
                double amount = Double.parseDouble(amountField.getText());
                
                bank.transfer(fromAccount, toAccount, amount);
                outputArea.append("✓ Transfer successful!\n");
                outputArea.append("From: " + fromAccount + " → To: " + toAccount + "\n");
                outputArea.append("Amount: $" + String.format("%.2f", amount) + "\n\n");
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        }
    }
    
    private void checkBalance() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter Account Number:");
        if (accountNumber != null && !accountNumber.trim().isEmpty()) {
            try {
                Account account = bank.getAccount(accountNumber);
                outputArea.append("Account: " + accountNumber + "\n");
                outputArea.append("Balance: $" + String.format("%.2f", account.getBalance()) + "\n\n");
            } catch (Exception ex) {
                showError("Error: " + ex.getMessage());
            }
        }
    }
    
    private void deleteAccount() {
        String accountNumber = JOptionPane.showInputDialog(this, "Enter Account Number to Delete:");
        if (accountNumber != null && !accountNumber.trim().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete account " + accountNumber + "?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (bank.deleteAccount(accountNumber)) {
                    outputArea.append("✓ Account deleted successfully!\n\n");
                } else {
                    showError("Account not found.");
                }
            }
        }
    }
    
    private void showAllAccounts() {
        outputArea.append("=== All Accounts ===\n");
        outputArea.append(bank.getAllAccountsAsString());
        outputArea.append("\n");
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankGUI());
    }
}
