/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
   import java.sql.*;
public class acountservice {
  public void createAccount(String name, String pin) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO accounts (name, pin_hash) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, PasswordUtils.hashPin(pin));
            ps.executeUpdate();
            System.out.println("✅ Account created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccount(int accountNo, String pin) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM accounts WHERE account_no=? AND pin_hash=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountNo);
            ps.setString(2, PasswordUtils.hashPin(pin));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                    rs.getInt("account_no"),
                    rs.getString("name"),
                    rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
}
