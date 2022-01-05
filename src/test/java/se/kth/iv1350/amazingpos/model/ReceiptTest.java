package se.kth.iv1350.amazingpos.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    @Test
    void testCreateReceiptString() {
        int quantity = 1;
        int taxRate = 1;
        int price = 1;
        String itemId = "apple";
        ItemDescription item = new ItemDescription(itemId, quantity, taxRate, price );
        Sale sale = new Sale();
        sale.countItem(item, quantity);
        sale.updateTotalPriceAndVAT();
        Receipt receipt = new Receipt(sale);
        String expResult = "---------------------RECEIPT---------------------\n"+
                sale.getTimeOfSale().toLocalDate().toString() +
                "\nItems: "+
                "\n"+itemId + " \t" +"quantity: " + quantity + " \t"+
                "taxRate: " + (price*taxRate*quantity) + "  \t"+
                "price: " + ((quantity*price)+(price*taxRate*quantity)) +" \t"+
                "\ntotal price without discount: "+ sale.getTotalPriceAfterDiscount()+" kr"+
                "\ndiscount amount: "+ sale.getDiscountAmount()+" kr"+
                "\n----------------------END----------------------\n";

        String result = receipt.createReceiptString();
        assertTrue(result.contains(expResult),"Wrong string of receipt have been printed out.");
        assertTrue(result.contains(sale.getTimeOfSale().toLocalDate().toString()),"Wrong time of sale.");
        assertTrue(result.contains(Integer.toString(sale.getTimeOfSale().getMonthValue())),"Wrong sale month.");
        assertTrue(result.contains(Integer.toString(sale.getTimeOfSale().getYear())),"Wrong sale year.");
        assertTrue(result.contains(expResult),"Wrong string of receipt have been printed out");
    }

}