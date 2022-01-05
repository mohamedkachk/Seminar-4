package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.controller.Controller;
import static org.junit.jupiter.api.Assertions.*;

class InventorySystemTest {
    private InventorySystem instance;
    private Controller contr;

    @BeforeEach
    void setUp() {
        instance = new InventorySystem();
        contr = new Controller(new SystemCreator(),
                new RegistryCreator(),new PaymentCreator());
    }

    @AfterEach
    void tearDown() {
        instance = null;
        contr = null;
    }

    @Test
    public void  testUpdateInventory() throws ItemNotExistException, DatabaseNotAvailableException {
        contr.startSale();
        String itemId = "apple";
        int quantity = 20;
        contr.scanItem(itemId, quantity);
        contr.showTotalPriceAndVAT();
        int expResult = instance.getInventoryItems().get(0).getQuantity() - quantity;
        // decrease quantity of "apple" items in inventory
        instance.updateInventory(contr.getSale());
        int actualResult =instance.getInventoryItems().get(0).getQuantity();
        assertEquals(expResult, actualResult, "negative quantity," +
                " or more than 1000 items are not allowed");
    }
}