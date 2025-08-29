/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package passwordutils;
import java.security.MessageDigest;
/**
 *
 * @author ASUS
 */
public class java {
     public static String hashPin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(pin.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) hexString.append(String.format("%02x", b));
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
