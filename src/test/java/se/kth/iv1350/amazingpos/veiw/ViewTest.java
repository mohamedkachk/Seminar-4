package se.kth.iv1350.amazingpos.veiw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.integration.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ViewTest {
    private ByteArrayOutputStream printoutContent;
    private PrintStream originalSysOut;
    private View instance;
    private Controller contr;

    @BeforeEach
    public void setUp() throws IOException {
        originalSysOut = System.out;
        printoutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printoutContent));
        contr = new Controller(new SystemCreator(),
                new RegistryCreator(),new PaymentCreator());
        instance = new View(contr);

    }

    @AfterEach
    public void tearDown() {
        printoutContent = null;
        System.setOut(originalSysOut);
        instance = null;
        contr = null;
    }

    @Test
    public void testSampleExecution() {
        instance.sampleExecution();
        String expResult = "New sale is started.\n";
        String actualResult = printoutContent.toString();
        assertTrue(actualResult.contains(expResult),"UI did not start correctly.");
        String expResult1 = "discount rate: ";
        String actualResult1 = printoutContent.toString();
        assertTrue(actualResult1.contains(expResult1),"UI did not start correctly.");
        String expResult2 = "---------------------RECEIPT---------------------";
        String expResult3= "apple";
        String actualResult3 = printoutContent.toString();
        assertTrue(actualResult3.contains(expResult3),"UI did not start correctly.");
        String expResult4 = "----------------------END----------------------";
    }
}