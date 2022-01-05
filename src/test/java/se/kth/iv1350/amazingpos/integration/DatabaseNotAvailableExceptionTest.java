package se.kth.iv1350.amazingpos.integration;


import org.junit.jupiter.api.*;
import se.kth.iv1350.amazingpos.controller.Controller;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseNotAvailableExceptionTest {
    private Controller instanceContr;
    String unavailableItemId = null;

    @BeforeEach
    void setUp() {
        instanceContr = new Controller(new SystemCreator(),
                new RegistryCreator(),new PaymentCreator());
    }

    @AfterEach
    void tearDown() {
      instanceContr = null;
    }


    @Test
    void testGetMessage() {
        String expResult ="could not access the database.";
        try {
            instanceContr.scanItem(unavailableItemId , 1);
            fail("The database is accessible.");
        } catch (DatabaseNotAvailableException | ItemNotExistException ex) {
        assertTrue(ex.getMessage().contains(expResult), "Wrong message was thrown");
        assertEquals(ex.getMessage(), expResult, "the database is active");
        }
    }

    @Test
    void testGetLocalizedMessage(){
        String expResult = "could not access the database.";
        try {
            instanceContr.scanItem(unavailableItemId , 1);
            fail("The database is accessible.");
        } catch (DatabaseNotAvailableException | ItemNotExistException ex) {
            assertEquals(ex.getLocalizedMessage(), expResult , "Wrong message was thrown");
            assertTrue(ex.getLocalizedMessage().contains(expResult), "the database is active");
        }
    }

    @Test
    void testGetCause() {
        String expResult ="java.lang.RuntimeException: Failed to connect to database.";
        try {
            instanceContr.scanItem(unavailableItemId, 1);
            fail("The database is accessible.");
        }catch ( ItemNotExistException | DatabaseNotAvailableException ex) {
            assertEquals(ex.getCause().toString(), expResult , "Wrong message was thrown");
            assertTrue(ex.getCause().toString().contains(expResult), "the database is active");
        }
    }

}