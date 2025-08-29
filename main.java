/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
import java.util.Scanner;
public class main {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountService accountService = new AccountService();
        TransactionService txnService = new TransactionService();

        while (true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Set PIN: ");
                    String pin = sc.nextLine();
                    accountService.createAccount(name, pin);
                    break;

                case 2:
                    System.out.print("Enter Account No: ");
                    int accNo = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    sc.nextLine();
                    String loginPin = sc.nextLine();
                    var account = accountService.getAccount(accNo, loginPin);
                    if (account != null) {
                        System.out.println("✅ Welcome " + account.getName());
                        System.out.println("Balance: " + account.getBalance());
                        System.out.println("1. Deposit\n2. Withdraw");
                        int op = sc.nextInt();
                        if (op == 1) {
                            System.out.print("Amount: ");
                            txnService.deposit(account.getAccountNo(), sc.nextDouble());
                        } else if (op == 2) {
                            System.out.print("Amount: ");
                            txnService.withdraw(account.getAccountNo(), sc.nextDouble());
                        }
                    } else {
                        System.out.println("❌ Invalid credentials");
                    }
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }

}
