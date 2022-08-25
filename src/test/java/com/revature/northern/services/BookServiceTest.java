package com.revature.northern.services;

import com.revature.northern.daos.BookDAO;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import static org.mockito.Mockito.mock;

public class BookServiceTest {
    //step 1. inject the BookDAO
    private BookService sut;
    private final BookDAO mockbookDAO = mock(BookDAO.class);


    @Before
    public void setUp() {
        sut = new BookService(mockbookDAO);
    }


    //Method 1: [isValidISBN] - testing if isValidISBN is valid or not
    @Test
    public void test_isValidISBN_givenCorrectISBN() {
        //[A] -1 Arrange
        String isValidISBN = "1245646000";
        //[A] -2 Act
        boolean flag = sut.isValidISBN(isValidISBN);
        //[A] -3 Assert
        Assert.assertTrue(flag);
    }


    //@Test - test for invalid isbn
    @Test(expected = InvalidUserException.class)
    public void test_inValidISBN_givenInCorrectISBN() {
        //[A] -1 Arrange
        String isValidISBN = "120";
        //[A] -2 Act
        boolean flag = sut.isValidISBN(isValidISBN);
        //[A] -3 Assert
        Assert.assertTrue(flag);
    }


    //@Test - check book title
    @Test
    public void test_isValidTitile_given_CorrectTitle() {
        //A1 - Arrange
        String isValidTitle = "Java";
        //A2 - Act
        boolean flag = sut.isValidTitle(isValidTitle);
        //A3 - Asset
        Assert.assertTrue(flag);

    }


    //@Test - check book author name

    @Test
    public void test_isValidAuthorName_givenCorrectAuthorName() {
        //A1- Arrange
        String isValidAuthorName = "John Doe";
        //A2 - Act
        boolean flag = sut.isValidAuthorName(isValidAuthorName);

        //A3 - Assert
        Assert.assertTrue(flag);

    }


    //@Test - check book Published Date  or year
    @Test
    public void test_isValidPublishedDate_givenCorrectPublishedDate() {
        //A1 - Arrange
        String isValidPublishedDate = "02/24/2021";
        //A2 - Act
        boolean flag = sut.isValidPublishedDate(isValidPublishedDate);
        //A3 - Assert
        Assert.assertTrue(flag);

    }


    //@Test - check book price
    @Test
    public void test_isValidBookPrice_givenCorrectBookPrice() {
        //A1 - Arrange
        double isValidBookPrice = 32.99;
        //A2 - Act
        boolean flag = sut.isValidPrice(isValidBookPrice);
        //A3 - Assert
        Assert.assertTrue(flag);

    }


    //@Test - check book publisher name
    @Test
    public void test_isValidBookPublisherName_givenBookPublisherName() {
        //A1 - Arrange
        String isValidPublisher = "Amazon";
        //A2 - Act
        boolean flag = sut.isValidPublisher(isValidPublisher);
        //A3 - Asset
        Assert.assertTrue(flag);

    }

}