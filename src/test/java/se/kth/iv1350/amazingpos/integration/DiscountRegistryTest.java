package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.model.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class DiscountRegistryTest {
    private CustomerRegistry existingCustomer;
    private int age = 35;
    private String name = "Erik";
    private String address = "Stockholm";
    private String customerID="11111";
    private int telephoneNumber = 1;
    private DiscountRegistry instance;
    private Sale sale;
    private ByteArrayOutputStream printoutContent;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        printoutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printoutContent));
        instance = new DiscountRegistry();
        existingCustomer = new CustomerRegistry
                ( name, address, customerID, telephoneNumber, age);
        sale= new Sale();
    }

    @AfterEach
    void tearDown() {
        printoutContent  = null;
        System.setOut(originalSysOut);
        instance = null;
        existingCustomer = null;
        sale = null;
    }

    @Test
    public void testDiscountControl() {
        instance = instance.discountControl(existingCustomer);
        sale.applyDiscount(instance);
        double discountRate = instance.getDiscountRate()*100;
        String expResult = "discount rate: "+((int)discountRate)+"%\n";
        String actualResult = printoutContent.toString();
        assertEquals(expResult, actualResult,"Wrong printout of discount rate.");
        assertTrue(actualResult.contains(expResult),"Wrong printout of discount rate.");
    }

    @Test
    public void  testDiscountControlIfThereIsNoExistingCustomer() {
        instance = instance.discountControl(null);
        sale.applyDiscount(instance);
        double discountRate = 0;
        String expResult = "discount rate: "+((int)discountRate)+"%\n";
        String actualResult = printoutContent.toString();
        assertEquals(expResult, actualResult,"Wrong printout of discount rate.");
        assertTrue(actualResult.contains(expResult),"Wrong printout of discount rate.");
        double expDiscountRate = instance.getDiscountRate();
        double actualDiscountRate = sale.getDiscountRate();
        assertEquals(expDiscountRate, actualDiscountRate,
                "The actual discount rate does not match the expected discount rate.");

    }
}