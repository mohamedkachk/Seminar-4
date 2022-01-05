package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CashCounterTest {
    private Controller contr;
    private CashCounter instance;
    SalePayment salePayment;

    @BeforeEach
    void setUp() {
        instance = new CashCounter();
        salePayment = new SalePayment(new Amount(100), instance);
        contr = new Controller(new SystemCreator(),
                new RegistryCreator(),new PaymentCreator());
        contr.startSale();
    }

    @AfterEach
    void tearDown() {
        contr = null;
        instance = null;
        salePayment =null;
    }

    @Test
    void testAddCash() throws ItemNotExistException, DatabaseNotAvailableException {
        String itemId = "apple";
        int quantity = 4;
        contr.scanItem(itemId, quantity);
        contr.showTotalPriceAndVAT();
        contr.getSale().pay(salePayment);
        Amount expResult = salePayment.getTotalCost();
        Amount actualResult = instance.getBalance();
        assertEquals(expResult, actualResult,"Wrong instance of balance.");
    }
}