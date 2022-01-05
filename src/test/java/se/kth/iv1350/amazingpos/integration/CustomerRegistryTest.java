package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRegistryTest {
    private ByteArrayOutputStream printoutContent;
    private PrintStream originalSysOut;
    private CustomerRegistry instance;
    private String customerID;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        printoutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printoutContent));
        instance = new CustomerRegistry();
    }

    @AfterEach
    void tearDown() {
        printoutContent  = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    public void testCustomerControl() {
        customerID = "11111";
        String expResult = customerID;
        String actualResult = instance.customerControl(customerID).getCustomerID();
        assertTrue(actualResult.contains(expResult),"Customer's ID does not exist.");
        assertEquals(expResult, actualResult,"No such customer's ID available.");
    }

    @Test
    public void testCustomerControlIfCustomerID_Is_Wrong() {
        customerID = "1";
        CustomerRegistry expResult = null;
        CustomerRegistry actualResult = instance.customerControl(customerID);
        assertEquals(expResult, actualResult,"Customer's ID exists.");
    }
}