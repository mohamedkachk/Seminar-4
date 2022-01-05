package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.model.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
    private ByteArrayOutputStream printoutContent;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        originalSysOut = System.out;
        printoutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printoutContent));
    }

    @AfterEach
    void tearDown() {
        printoutContent = null;
        System.setOut(originalSysOut);
    }
    @Test
    void testPrintReceipt() {
        int quantity = 1;
        int taxRate = 1;
        int price = 1;
        String itemId = "apple";
        ItemDescription item = new ItemDescription(itemId, quantity, taxRate, price );
        Sale sale = new Sale();
        sale.countItem(item, quantity);
        sale.updateTotalPriceAndVAT();
        Receipt receipt = new Receipt(sale);
        Printer instance = new Printer();
        instance.printReceipt(receipt);
        String expResult = "---------------------RECEIPT---------------------\n"+
                sale.getTimeOfSale().toLocalDate().toString() +
                "\nItems: "+
                "\n"+itemId + " \t" +"quantity: " + quantity + " \t"+
                "taxRate: " + (price*taxRate*quantity) + "  \t"+
                "price: " + ((quantity*price)+(price*taxRate*quantity)) +" \t"+
                "\ntotal price without discount: "+ sale.getTotalPriceAfterDiscount()+" kr"+
                "\ndiscount amount: "+ sale.getDiscountAmount()+" kr"+
                "\n----------------------END----------------------\n";
        String result = printoutContent.toString();
        assertTrue(result.contains(expResult),"Wrong string of receipt have been printed out.");
        assertTrue(result.contains(sale.getTimeOfSale().toLocalDate().toString()),"Wrong time of sale.");
        assertTrue(result.contains(Integer.toString(sale.getTimeOfSale().getMonthValue())),"Wrong sale month.");
        assertTrue(result.contains(Integer.toString(sale.getTimeOfSale().getYear())),"Wrong sale year.");
        assertTrue(result.contains(expResult),"Wrong string of receipt have been printed out");
    }

}