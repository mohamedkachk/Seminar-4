package se.kth.iv1350.amazingpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.integration.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale instance;
    private String itemId = "apple";
    private int quantityZero = 0;
    private int taxRate = 1;
    private int price = 1;
    private int itemQuantity = 1;
    private double discountRate = 0.05;
    private ItemDescription validItem;
    private DiscountRegistry eligibleCustomer;
    private ByteArrayOutputStream printoutContent;
    private PrintStream originalSysOut;
    private Controller contr;

    @BeforeEach
    void setUp() {
        instance = new Sale();
        validItem = new ItemDescription(itemId, quantityZero, taxRate, price);
        eligibleCustomer = new DiscountRegistry(null, discountRate);
        originalSysOut = System.out;
        printoutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printoutContent));

        contr = new Controller(new SystemCreator(),
                new RegistryCreator(),new PaymentCreator());
    }

    @AfterEach
    void tearDown() {
        instance = null;
        validItem = null;
        eligibleCustomer = null;
        printoutContent  = null;
        System.setOut(originalSysOut);
        contr = null;
    }

    @Test
    public void testCountItem() {
        String expResult = itemId+" \tquantity: "+itemQuantity+" \ttaxRate: "+taxRate+"  \tprice: "+(price+taxRate)+" \t";
        String actualResult = instance.countItem(validItem, itemQuantity);
        assertEquals(expResult, actualResult,"Wrong printout, the actual printout does not match the expected Result.");
        assertTrue(actualResult.contains(expResult),"Wrong printout, the actual printout does not match the expected Result.");
    }

    @Test
    public void testApplyDiscount() {
        discountRate = discountRate*100;
        instance.applyDiscount(eligibleCustomer);
        String expResult =("discount rate: "+((int)discountRate)+"%\n");
        String actualResult = printoutContent.toString();
        assertEquals(expResult, actualResult,
                "Wrong printout, the actual printout does not match the expected Result.");
        assertTrue(actualResult.contains(expResult),
                "Wrong printout, the actual printout does not match the expected Result.");
    }

    @Test
    public void testUpdateTotalPriceAndVAT() {
        int totalVAT = itemQuantity*taxRate*price;
        int totalPrice = itemQuantity*price;
        instance.countItem(validItem, itemQuantity);
        String expResult = ("total VAT: "+ totalVAT +"\t total price: "+(totalPrice+totalVAT));
        String actualResult = instance.updateTotalPriceAndVAT();
        assertEquals(expResult, actualResult,"Wrong printout of total price and VAT.");
        assertTrue(actualResult.contains(expResult),"Wrong printout of total price and VAT.");
    }

    @Test
    public void testSortAndAddSameItemsTogetherInNewList() throws ItemNotExistException, DatabaseNotAvailableException {
        contr.startSale();
        int quantity = 4;
        contr.scanItem(itemId,quantity); // item1  quantity = 4
        contr.scanItem(itemId,quantity); // item2  quantity = 4
        contr.scanItem(itemId,quantity); // item3  quantity = 4
        contr.showTotalPriceAndVAT();
        List<ItemDescription> soldItems = contr.getSale().getSortedListOfSoldItems();
        int expResult = quantity + quantity + quantity;      // expResult = 4 + 4 + 4
        int actualResult = soldItems.get(0).getQuantity();   // actualResult = 12
        assertEquals(expResult, actualResult,"Wrong quantity.");
    }

    @Test
    public void testPrintReceipt() {
        instance.countItem(validItem, itemQuantity);
        instance.updateTotalPriceAndVAT();
        Receipt receipt = new Receipt(instance);
        Printer printer = new Printer();
        printer.printReceipt(receipt);
        String expResult = "---------------------RECEIPT---------------------\n"+
                instance.getTimeOfSale().toLocalDate().toString() +
                "\nItems: "+
                "\n"+itemId + " \t" +"quantity: " + itemQuantity + " \t"+
                "taxRate: " + (price*taxRate*itemQuantity) + "  \t"+
                "price: " + (int)instance.getTotalPriceAfterDiscount() +" \t"+
                "\ntotal price without discount: "+ instance.getTotalPriceAfterDiscount()+" kr"+
                "\ndiscount amount: "+ instance.getDiscountAmount()+" kr"+
                "\n----------------------END----------------------\n";
        String result = printoutContent.toString();
        assertTrue(result.contains(expResult),"Wrong string of receipt have been printed out.");
    }
}