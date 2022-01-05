package se.kth.iv1350.amazingpos.integration;

import org.junit.jupiter.api.*;
import se.kth.iv1350.amazingpos.model.ItemRegistry;
import static org.junit.jupiter.api.Assertions.*;

class ItemNotExistExceptionTest {
    private ItemRegistry instance;

    @BeforeEach
    public void setUp()  {
        instance = new ItemRegistry();
    }

    @AfterEach
    public void tearDown() {
        instance= null;
    }

    @Test
    void testGetItemNotExist(){
            String unavailableItemId = "non-existing-item";
            try {
                instance.findItem(unavailableItemId);
                fail("this itemIdentifier was found");
            } catch (ItemNotExistException   ex) {
               assertTrue(ex.getMessage().contains(ex.getItemNotExist()), "this instance of itemDescription was found");
                assertEquals(ex.getItemNotExist(), unavailableItemId, "this itemIdentifier was found");
            }
    }

    @Test
    void testGetMessage() {
        String unavailableItemId = "non-existing-item";
        String expResult = "the Item --> "+unavailableItemId+" <-- does not exist in our inventory catalog " ;
        try {
            instance.findItem(unavailableItemId);
           fail("this itemIdentifier was found");
        } catch (ItemNotExistException ex) {
             assertTrue(ex.getMessage().contains(unavailableItemId), "this instance of itemDescription was found");
             assertEquals(ex.getMessage(), expResult, "this itemIdentifier was found");
        }
    }

    @Test
    void testGetLocalizedMessage(){
        String unavailableItemId = "unavailableItemId";
        String expResult = "the Item --> "+ unavailableItemId + " <-- does not exist in our inventory catalog ";
        try {
            instance.findItem(unavailableItemId);
            fail("this itemIdentifier was found");
        } catch (ItemNotExistException ex) {
            assertEquals(ex.getLocalizedMessage(), expResult , "this instance of itemDescription was found");
            assertTrue(ex.getLocalizedMessage().contains(unavailableItemId), "this itemIdentifier was found");
        }
    }
}