package se.kth.iv1350.amazingpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.integration.DatabaseNotAvailableException;
import se.kth.iv1350.amazingpos.integration.ItemNotExistException;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegistryTest {
    private ItemRegistry instance;
    private String itemId = "apple";
    String unavailableItemId = "car";
    private int quantity = 1;
    private int taxRate = 1;
    private int price = 1;
    private ItemDescription itemDescription;

    @BeforeEach
    void setUp() {
        instance = new ItemRegistry();
        itemDescription = new ItemDescription(itemId, quantity, taxRate, price);
    }

    @AfterEach
    void tearDown() {
        instance = null;
        itemDescription = null;
    }

    @Test
    public void testFindItem() throws ItemNotExistException {
        String expResult = itemDescription.getItemId();
        String actualResult = instance.findItem(itemId).getItemId();
        assertEquals(expResult, actualResult,"Wrong itemIdentifier or this item is unavailable or invalid!");
    }

    @Test
    public void testFindItemw() throws ItemNotExistException {
        String expResult = itemDescription.getItemId();
        String actualResult = instance.findItem(itemId).getItemId();
        assertEquals(expResult, actualResult,"Wrong itemIdentifier or this item is unavailable or invalid!");
    }

    @Test
    public void testNotFindItem() {
        try {
           instance.findItem(unavailableItemId);
        } catch (ItemNotExistException ex) {
        assertTrue(ex.getMessage().contains(unavailableItemId), "Wrong itemIdentifier");
        assertEquals(ex.getItemNotExist(),(unavailableItemId), "two itemIdentifiers are not equal");
        }
    }
}