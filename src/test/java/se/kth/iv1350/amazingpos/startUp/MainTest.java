package se.kth.iv1350.amazingpos.startUp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private ByteArrayOutputStream printoutContent;
    private PrintStream originalSysOut;
    private Main instance;


    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        printoutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printoutContent));
        instance = new Main();
    }

    @AfterEach
    public void tearDown() {
        printoutContent = null;
        System.setOut(originalSysOut);
        instance = null;
    }

    @Test
    public void testMain() throws IOException {
        String [] args = null;
        instance.main(args);
        String expResult = "New sale is started.\n";
        String actualResult = printoutContent.toString();
        assertTrue(actualResult.contains(expResult),"UI did not start correctly.");
    }
}