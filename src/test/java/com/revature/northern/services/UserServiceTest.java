package com.revature.northern.services;

import com.revature.northern.daos.UserDAO;
import com.revature.northern.models.User;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class UserServiceTest  {
    private UserService sut;
    private final UserDAO mockUserDAO = mock(UserDAO.class);

    //private UserService sut; // sut = system under test
    //private final UserDAO mockUserDao = mock(UserDAO.class);

    // Testing a methods inside the UserService class


        /*
        Common JUnit annotations:
            - @Test (marks a method as a test case)
            - @Ignore (tells JUnit to skip this test case)
            - @Before (logic that runs once before every test case)
            - @After (logic that runs once after every test case)
            - @BeforeClass (logic that runs only once before all test cases)
            - @AfterClass (logic that runs only once after all test cases)
     */

    //sut = System Under Test
    //Create 1st a  junk constructor with @Before annotation
    @Before
    public void setup() {
        sut = new UserService(mockUserDAO);
    }


    //Method 1: [username] - testing if username is valid or not
    @Test
    public void test_isValidUsername_givenCorrectUsername() {
        //3 [A]
        //1. Arrange
        String validUsername = "osman8080"; // initialize the user you want to check
        //2. Act
        boolean flag = sut.isValidUsername(validUsername);
        //3. Assert
        Assert.assertTrue(flag);
    }

    //Method 2 [invalidUsername] - testing invalid username
    @Test(expected = InvalidUserException.class)
    public void test_isNotValidUsername_givenOnlyNumbers() {
        //Arrange
        //create a variable
        String invalidUsername = "osman"; //invalid username - this should fail, if username is less than 8 characters long
        // Act
        sut.isValidUsername(invalidUsername);
    }

    //Method - class expecting Exception - invalid username and password
    @Test(expected = InvalidUserException.class)
    public void test_login_invalidLoginGivenIncorrectCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        String invalidUsername = "invalid";
        String invalidPassword = "invalid";
        when(mockUserDAO.getUserByUsernameAndPassword(invalidUsername, invalidPassword)).thenReturn(null);
        // Act
        sut.login(invalidUsername, invalidPassword);
    }


    //@Test - email if is valid or not
    @Test
    public void test_isValidEmail_givenCorrectEmail() {
        //A1 - Arrange
        String isValidEmail = "osman@gmail.com";
        //A2 - Act
        boolean flag = sut.isValidEmail(isValidEmail);
        //A3 - Asset
        Assert.assertTrue(flag);

    }


    @Test
    public void test_login_validLoginGivenCorrectCredentials() {
        // Arrange
        UserService spiedSut = Mockito.spy(sut);
        String validUsername = "osman8080";
        String validPassword = "osman8080";
        when(spiedSut.isValidUsername(validUsername)).thenReturn(true);
        when(spiedSut.isValidPassword(validPassword)).thenReturn(true);
        when(mockUserDAO.getUserByUsernameAndPassword(validUsername, validPassword))
        .thenReturn(new User());

        // Act
        User user = spiedSut.login(validUsername, validPassword);

        // Assert
        Assert.assertNotNull(user);
        verify(mockUserDAO, times(1)).getUserByUsernameAndPassword(validUsername, validPassword);
    }


    //Expects Exception, [InvalidUserException] class
    @Test(expected = InvalidUserException.class)
    public void test_isValidEmail_givenInvalidEmail() {
        String isInValidEmail = "mahmoud@";
        boolean flag = sut.isValidEmail(isInValidEmail);
        Assert.assertTrue(flag);
    }
}
