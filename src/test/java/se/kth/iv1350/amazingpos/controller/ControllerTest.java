package se.kth.iv1350.amazingpos.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.integration.*;
import se.kth.iv1350.amazingpos.model.*;
import se.kth.iv1350.amazingpos.veiw.View;

class ControllerTest {
    private ByteArrayOutputStream printoutContent;
    private PrintStream originalSysOut;
    private Controller instance;
    private View instanceView;

    @BeforeEach
    public void setUp() throws IOException {
        originalSysOut = System.out;
        printoutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printoutContent));
        instance = new Controller(new SystemCreator(),
                new RegistryCreator(),new PaymentCreator());
        instance.startSale();
        instanceView = new View(instance);
    }

    @AfterEach
    public void tearDown() {
        printoutContent  = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    public void testStartSale() {
        instance.startSale();
    }

    @Test
        void testTrueScanItem() throws ItemNotExistException, DatabaseNotAvailableException {
        String itemId = "apple";
        int quantity = 4;
        String actualResult = instance.scanItem(itemId, quantity);
        String expResult = ""+itemId+" \tquantity: "+quantity+" \ttaxRate: 4  \tprice: 8 ";;
        assertTrue(actualResult.contains(expResult),"Wrong printout.");
    }
    @Test
        void testFalseScanItem() throws DatabaseNotAvailableException {
        String itemId = "ap  ple"; // Wrong itemID
        int quantity = 4;
        String expResult = printoutContent.toString();
        try {
            instance.scanItem(itemId, quantity);
        } catch (ItemNotExistException ex) {
            assertNotEquals(expResult,  itemId, "Wrong printout.");
            assertTrue(ex.getLocalizedMessage().contains(itemId), "this itemIdentifier was found");
        }
    }
    @Test
        void testUnavailableItemScanItem() throws  DatabaseNotAvailableException {
        String itemId = "car"; // unavailable item
        int quantity = 4;
        String expResult = "the Item --> "+ itemId + " <-- does not exist in our inventory catalog ";
        try {
          instance.scanItem(itemId, quantity);
        } catch (ItemNotExistException ex) {
            assertEquals(ex.getLocalizedMessage(), expResult , "this instance of itemDescription was found");
            assertTrue(ex.getLocalizedMessage().contains(itemId), "this itemIdentifier was found");
        }
    }

    @Test
        void testShowTotalPriceAndVAT() throws ItemNotExistException, DatabaseNotAvailableException {
        String itemId = "apple";
        int quantity = 4;
        instance.scanItem(itemId, quantity);
        int taxRate = 1;
        int price = 1;
        String expResult = "total VAT: " + (quantity*taxRate)
                +"\t total price: "+((quantity*price)+(quantity*taxRate));
        String actualResult = instance.showTotalPriceAndVAT();
        assertEquals(expResult, actualResult,"Wrong printout of total price and total VAT.");
        assertTrue(actualResult.contains(expResult),"Wrong printout of total price and total VAT.");
    }

    @Test
     void testDiscount() {
        String customerID = "11111";
        instance.discount(customerID);
        double discountRate = instance.getSale().getDiscountRate()*100;
        String expResult = "discount rate: "+((int)discountRate)+"%\n";
        String actualResult = printoutContent.toString();
        assertEquals(expResult, actualResult,"Wrong printout of discount rate.");
        assertTrue(actualResult.contains(expResult),"Wrong printout of discount rate.");
    }
    @Test
      void testDiscountWithWrongCustomerID() {
        String customerID = "00000"; // Wrong customerID
        instance.discount(customerID);
        String expResult ="this customer's ID does not exist";
        String actualResult = printoutContent.toString();
        assertTrue(actualResult.contains(expResult),"Wrong printout of discount rate.");
    }

    @Test
     void testPay() throws ItemNotExistException, DatabaseNotAvailableException {
        String itemId = "apple";
        int quantity = 4;
        instance.scanItem(itemId, quantity);
        int taxRate = 1;
        int price = 1;
        instance.showTotalPriceAndVAT();
        String customerID = "11111";
        instance.discount(customerID);
        Sale sale = instance.getSale();
        double discountRate = instance.getSale().getDiscountRate()*100;
        instance.pay(new Amount(100));
        String expResult = "discount rate: "+((int)discountRate)+"%\n"+
                "---------------------RECEIPT---------------------\n"+
                sale.getTimeOfSale().toLocalDate().toString() +
                "\nItems: "+
                "\n"+itemId + " \t" +"quantity: " + quantity + " \t"+
                "taxRate: " + (price*taxRate*quantity) + "  \t"+
                "price: " + ((quantity*price)+(price*taxRate*quantity)) +" \t"+
                "\ntotal price include discount: "+ sale.getTotalPriceAfterDiscount()+" kr"+
                "\ndiscount amount: "+ sale.getDiscountAmount()+" kr"+
                "\n----------------------END----------------------\n";

        String actualResult = printoutContent.toString();
        assertTrue(actualResult.contains(expResult), "Wrong  instance of itemId, customerID or paid amount, check if instances are correct.");
        assertTrue(actualResult.contains(itemId),"Wrong itemId.");
        assertTrue(actualResult.contains(Integer.toString((int)discountRate)),"Wrong customerID.");
        assertTrue(actualResult.contains(Double.toString(sale.getDiscountAmount())),"Wrong customerID.");
    }

    @Test
    void testPaidAmountLowerThanTotalPrice() throws ItemNotExistException {
        String itemId = "apple";

        try {
            instanceView.scanItemView(itemId, 5);
            instance.pay(new Amount(-1));
        } catch (IllegalStateException ex) {
            assertTrue(ex.toString().contains("paid amount is lower than total price"),    "Wrong printout.");
        }
    }

}