package com.revature.northern.services;

import com.revature.northern.daos.OrderDAO;
import com.revature.northern.utils.custom_exceptions.InvalidUserException;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import org.junit.Assert;


import static org.mockito.Mockito.mock;

public class OrderServiceTest {

    private OrderService sut;
    private final OrderDAO mockOrderDAO = mock(OrderDAO.class);

    @Before
    public void setup() {
        sut = new OrderService(mockOrderDAO);
    }


    //Test order name
    @Test
    public void test_isValidOrderName_givenCorrectOrderName() {
        //A1 - Arrange
        String isValidOrderName = "Java";
        //A2 - Act
        boolean flag = sut.isValidName(isValidOrderName);
        //A3 - Assert
        Assert.assertTrue(flag);
    }


    //Test quantity
    @Test
    public void test_isValidQuantity_givenCorrectQuantity() {
        //A1 - Arrange
        int isValidQuantity = 3;
        //A2 - Act
        boolean flag = sut.isValidQuantity(isValidQuantity);
        //A3 - Assert
        Assert.assertTrue(flag);

    }

    //Test for invalid quantity
    @Test(expected = InvalidUserException.class)
    public void test_isValidQuantity_givenInCorrectQuantity() {
        int inValidQuantity = 0;
        boolean flag = sut.isValidQuantity(inValidQuantity);
        Assert.assertTrue(flag);

    }

}