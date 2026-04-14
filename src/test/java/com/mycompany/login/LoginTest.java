/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.login;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    public LoginTest() { }

    /**
     * Test of checkUserName method.
     */
    @Test
    public void testCheckUserName() {
        Login instance = new Login();

        // Valid case
        String username = "kyl_1";
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);

        // Invalid: too long and no underscore
        username = "kyle!!!!!!!";
        expResult = false;
        result = instance.checkUserName(username);
        assertEquals(expResult, result);

        // Invalid: null
        username = null;
        expResult = false;
        result = instance.checkUserName(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPasswordComplexity method.
     */
    @Test
    public void testCheckPasswordComplexity() {
        Login instance = new Login();

        // Valid password
        String password = "Ch&&sec@ke99!";
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);

        // Invalid: no complexity
        password = "password";
        expResult = false;
        result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);

        // Invalid: null
        password = null;
        expResult = false;
        result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkCellPhoneNumber method.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        Login instance = new Login();

        // Valid case
        String phone = "+27838968976";
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(phone);
        assertEquals(expResult, result);

        // Invalid format
        phone = "08966553";
        expResult = false;
        result = instance.checkCellPhoneNumber(phone);
        assertEquals(expResult, result);

        // Null
        phone = null;
        expResult = false;
        result = instance.checkCellPhoneNumber(phone);
        assertEquals(expResult, result);
    }

    /**
     * Test of registerUser method.
     */
    @Test
    public void testRegisterUser() {
        Login instance = new Login();

        // Valid registration
        String result = instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String expResult = "Registration successful!";
        assertEquals(expResult, result);

        // Invalid username
        result = instance.registerUser("kyle!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username is not correctly formatted"));

        // Invalid password
        result = instance.registerUser("kyl_1", "password", "+27838968976");
        assertTrue(result.contains("Password must be at least 8 characters"));

        // Invalid phone
        result = instance.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertTrue(result.contains("Cell phone number must start with +27"));
    }

    /**
     * Test of returnLoginStatus method.
     */
    @Test
    public void testReturnLoginStatus() {
        Login instance = new Login();

        // Successful login
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String expResult = "Welcome kyl_1, it is great to see you again.";
        String result = instance.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals(expResult, result);

        // Failed login
        expResult = "Username or password incorrect, please try again.";
        result = instance.returnLoginStatus("wrong", "wrong");
        assertEquals(expResult, result);
    }
}