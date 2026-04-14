/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.login;

import java.util.Scanner;

/**
 * Login class
 * Handles user registration and login validation
 */
public class Login {

    // Variables to store registered user details
    private String storedUsername;
    private String storedPassword;
    private String storedCellPhone;

    /**
     * Checks if the username is valid
     * Rules:
     * - Must not be null
     * - Must contain an underscore (_)
     * - Must not be longer than 5 characters
     */
    public boolean checkUserName(String username) {
        return username != null &&
               username.contains("_") &&
               username.length() <= 5;
    }

    /**
     * Checks password complexity
     * Rules:
     * - At least 8 characters long
     * - Must contain:
     *   ✔ One uppercase letter
     *   ✔ One number
     *   ✔ One special character
     */
    public boolean checkPasswordComplexity(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }

            if (Character.isDigit(c)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Validates South African cell phone number
     * Rules:
     * - Must start with +27
     * - Must be exactly 12 characters long (+27 + 9 digits)
     * - Must contain only digits after '+'
     */
    public boolean checkCellPhoneNumber(String cellPhone) {

        if (cellPhone == null || !cellPhone.startsWith("+27")) {
            return false;
        }

        // Must be exactly +27 followed by 9 digits
        if (cellPhone.length() != 12) {
            return false;
        }

        // Check digits after '+'
        String digits = cellPhone.substring(1);

        for (char c : digits.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Registers a user if all validations pass
     */
    public String registerUser(String username, String password, String cellPhone) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; must contain '_' and be max 5 characters.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password must be at least 8 characters, with a capital letter, number, and special character.";
        }

        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number must start with +27 and be 12 characters long.";
        }

        storedUsername = username;
        storedPassword = password;
        storedCellPhone = cellPhone;

        return "Registration successful!";
    }

    /**
     * Checks if login details match stored user details
     * FIX: Made PUBLIC so tests can access it
     */
    public boolean loginUser(String username, String password) {
        return storedUsername != null &&
               username.equals(storedUsername) &&
               password.equals(storedPassword);
    }

    /**
     * Returns login result message
     */
    public String returnLoginStatus(String username, String password) {

        if (loginUser(username, password)) {
            return "Welcome " + username + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    /**
     * Main method - program entry point
     */
    public static void main(String[] args) {

        Login login = new Login();
        Scanner input = new Scanner(System.in);

        // ================= REGISTRATION =================
        System.out.println("---- REGISTRATION ----");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell phone (+27...): ");
        String cellPhone = input.nextLine();

        String result = login.registerUser(username, password, cellPhone);
        System.out.println(result);

        // ================= LOGIN =================
        System.out.println("\n---- LOGIN ----");

        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        System.out.println(login.returnLoginStatus(loginUsername, loginPassword));

        input.close();
    }
}