
import java.util.Scanner;

public class BankAccount {
    private double balance;
    private StringBuilder transactionHistory;

    public BankAccount() {
        balance = 0.0;
        transactionHistory = new StringBuilder();
    }

    // @Override
    // public String toString()
    // {
    // return
    // }
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited successfully");
            transactionHistory.append("Deposited: ").append(amount).append("\n");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn successfully");
            transactionHistory.append("Withdrew: ").append(amount).append("\n");
        }
    }

    public String getTransactionHistory() {
        return transactionHistory.toString();
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        System.out.println("Initial balance: " + account.getBalance());
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Deposit\n2.Withdraw\n3.View Transaction History\n4. View Current Balance\n5. Exit");
            System.out.println("enter your choice");
            int choice = sc.nextInt();
            double amt = 0.0;
            if (choice == 1 || choice == 2) {
                System.out.println("Enter your amount:");
                amt = sc.nextDouble();
            }

            switch (choice) {
                case 1:
                    account.deposit(amt);
                    break;
                case 2:
                    account.withdraw(amt);
                    break;
                case 3:
                    System.out.println("Transaction History:");
                    System.out.println(account.getTransactionHistory());
                    break;
                case 4:
                    System.out.println(account.getBalance());
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid input provided...Try again");
            }
        }
    }
}