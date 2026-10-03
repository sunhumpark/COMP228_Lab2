public class BankAccount {
    private long accountNumber;
    private String name;
    private double balance;

    public BankAccount(long accountNumber, String name, double balance) {
        if (String.valueOf(accountNumber).length() != 9) {
            throw new IllegalArgumentException("Account number must be exactly 9 digits.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.printf("Deposit successful. New balance: $%.2f%n", balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: $" + String.format("%.2f", balance));
        }
        balance -= amount;
        System.out.printf("Withdrawal successful. New balance: $%.2f%n", balance);
    }

    public void displayAccount() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.printf("Balance: $%.2f%n", balance);
    }
}
