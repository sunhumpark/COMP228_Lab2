import java.util.InputMismatchException;
import java.util.Scanner;

public class MainDriver {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[3];

        System.out.println("=== Simple Banking System ===");

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = createAccount(i + 1);
        }

        for (int i = 0; i < accounts.length; i++) {
            System.out.println("\n=== Transactions for Account " + (i + 1) + " ===");
            accounts[i].displayAccount();
            processDeposit(accounts[i]);
            processWithdrawal(accounts[i]);
        }

        System.out.println("\n=== Final Account Information ===");
        for (BankAccount account : accounts) {
            account.displayAccount();
            System.out.println();
        }

        scanner.close();
        System.out.println("Program finished.");
    }

    private static BankAccount createAccount(int accountIndex) {
        while (true) {
            try {
                System.out.println("\nEnter information for Account " + accountIndex + ":");
                System.out.print("Enter 9-digit account number: ");
                long accountNumber = scanner.nextLong();
                scanner.nextLine();

                System.out.print("Enter account holder name: ");
                String name = scanner.nextLine();

                System.out.print("Enter opening balance: $");
                double balance = scanner.nextDouble();
                scanner.nextLine();

                return new BankAccount(accountNumber, name, balance);
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter the correct data type.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Account information has been processed.");
            }
        }
    }

    private static void processDeposit(BankAccount account) {
        boolean completed = false;
        while (!completed) {
            try {
                System.out.print("Enter deposit amount: $");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                account.deposit(amount);
                completed = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Deposit amount must be a number.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Deposit transaction has been processed.");
            }
        }
    }

    private static void processWithdrawal(BankAccount account) {
        boolean completed = false;
        while (!completed) {
            try {
                System.out.print("Enter withdrawal amount: $");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                account.withdraw(amount);
                completed = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Withdrawal amount must be a number.");
                scanner.nextLine();
            } catch (IllegalArgumentException | InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Withdrawal transaction has been processed.");
            }
        }
    }
}
