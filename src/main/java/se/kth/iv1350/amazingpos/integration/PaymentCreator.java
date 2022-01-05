package se.kth.iv1350.amazingpos.integration;


/**
 * This class is responsible for instantiating printer
 * and cash counter.
 */
public class PaymentCreator {
    private Printer printer = new Printer();
    private CashCounter cashCounter = new CashCounter();

    /**
     * Get the value of printer
     *
     * @return the value of printer
     */
    public Printer getPrinter() {
        return printer;
    }

    /**
     * Get the value of cashCounter
     *
     * @return the value of cashCounter
     */
    public CashCounter getCashCounter() {
        return cashCounter;
    }

}
