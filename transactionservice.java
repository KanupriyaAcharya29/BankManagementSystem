/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
import java.sql.*;
public class transactionservice {
     public void deposit(int accountNo, double amount) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            String updateBalance = "UPDATE accounts SET balance = balance + ? WHERE account_no=?";
            PreparedStatement ps1 = conn.prepareStatement(updateBalance);
            ps1.setDouble(1, amount);
            ps1.setInt(2, accountNo);
            ps1.executeUpdate();

            String insertTxn = "INSERT INTO transactions (account_no, type, amount) VALUES (?, 'DEPOSIT', ?)";
            PreparedStatement ps2 = conn.prepareStatement(insertTxn);
            ps2.setInt(1, accountNo);
            ps2.setDouble(2, amount);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("✅ Deposit successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void withdraw(int accountNo, double amount) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            String checkBalance = "SELECT balance FROM accounts WHERE account_no=?";
            PreparedStatement psCheck = conn.prepareStatement(checkBalance);
            psCheck.setInt(1, accountNo);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getDouble("balance") >= amount) {
                String updateBalance = "UPDATE accounts SET balance = balance - ? WHERE account_no=?";
                PreparedStatement ps1 = conn.prepareStatement(updateBalance);
                ps1.setDouble(1, amount);
                ps1.setInt(2, accountNo);
                ps1.executeUpdate();

                String insertTxn = "INSERT INTO transactions (account_no, type, amount) VALUES (?, 'WITHDRAW', ?)";
                PreparedStatement ps2 = conn.prepareStatement(insertTxn);
                ps2.setInt(1, accountNo);
                ps2.setDouble(2, amount);
                ps2.executeUpdate();

                conn.commit();
                System.out.println("✅ Withdraw successful.");
            } else {
                System.out.println("❌ Insufficient funds.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
