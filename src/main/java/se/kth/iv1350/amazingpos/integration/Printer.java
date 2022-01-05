package se.kth.iv1350.amazingpos.integration;

import se.kth.iv1350.amazingpos.model.*;

/**
 * The interface to the printer, used for all printouts initiated
 * by this program.
 */
public class Printer {
    private Receipt receipt;

    /**
     * Prints the specified receipt. This dummy implementation
     * prints to <code>System.out</code> instead of a printer.
     *
     * @param receipt A receipt for the current sale.
     *
     */
    public void printReceipt(Receipt receipt) {
        this.receipt =  receipt;
        System.out.println(receipt.createReceiptString());

    }
}
