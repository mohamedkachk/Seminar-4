package se.kth.iv1350.amazingpos.startUp;

import se.kth.iv1350.amazingpos.controller.*;
import se.kth.iv1350.amazingpos.integration.*;
import se.kth.iv1350.amazingpos.veiw.*;

import java.io.IOException;

/**
 * Contains the <code>main</code> method. Performs all startup
 * of the application.
 */
public class Main {

    /**
     * Starts the application.
     * @param args The application does not take any command
     * line parameters.
     */
    public static void main(String[] args) throws IOException {
        SystemCreator creator = new SystemCreator();
        RegistryCreator registryCreat = new RegistryCreator();
        PaymentCreator paymentCreat = new PaymentCreator();
        Controller contr= new Controller(creator, registryCreat, paymentCreat);
        View view = new View(contr);
        view.sampleExecution();
    }
}
