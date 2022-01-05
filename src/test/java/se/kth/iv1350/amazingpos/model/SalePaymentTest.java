package se.kth.iv1350.amazingpos.model;

import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.integration.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SalePaymentTest {
    private Controller contr;
    private SalePayment instance;
    Amount amount100;

    @BeforeEach
    void setUp() {
        amount100 = new Amount(100);
        instance = new SalePayment(amount100,new CashCounter());
        contr = new Controller(new SystemCreator(),
                new RegistryCreator(),new PaymentCreator());
        contr.startSale();
    }

    @AfterEach
    void tearDown() {
        instance = null;
        contr = null;
    }

    @Test
    void testCalculateTotalSale() throws ItemNotExistException, DatabaseNotAvailableException {
        String itemId = "apple";
        int quantity = 4;
        contr.scanItem(itemId, quantity);
        contr.showTotalPriceAndVAT();
        instance.calculateTotalSale(contr.getSale());
        int expResult = (int)contr.getSale().getTotalPriceAfterDiscount();
        int actualResult = instance.getTotalCost().getAmount();
        assertEquals(actualResult,expResult,"Wrong total price");
    }

    @Test
    void testGetChange() throws ItemNotExistException, DatabaseNotAvailableException {
        String itemId = "apple";
        int quantity = 4;
        contr.scanItem(itemId, quantity);
        contr.showTotalPriceAndVAT();
        instance.calculateTotalSale(contr.getSale());
        Amount  expResult = instance.getPaidAmt().minus(instance.getTotalCost());
        Amount actualResult = instance.getChange();
        assertEquals(actualResult,expResult,"Wrong amount of change");
    }
}