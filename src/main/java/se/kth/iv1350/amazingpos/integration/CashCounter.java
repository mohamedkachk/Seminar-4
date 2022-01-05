package se.kth.iv1350.amazingpos.integration;

import se.kth.iv1350.amazingpos.model.*;

/**
 *  This dummy implementation of a Cash counter.
 */
public class CashCounter {
    private SalePayment payment;
    private Amount balance ;

    /**
     * Creates a new instance representing a Cash counter with zero balance.
     */
    public CashCounter(){
        this.balance = new Amount(0) ;
    }

    /**
     * Updates the balance of the cash register where the payment was
     * performed
     * @param payment One instance of class SalePayment represents
     * one specific payment for one specific sale.
     *
     */
    public void addCash(SalePayment payment ){
        this.payment = payment;
        balance = balance.plus(payment.getTotalCost());

    }

    /**
     * Gets the value of balance.
     *
     * @return The current amount of balance.
     */
    public Amount getBalance() {
        return balance;
    }
}
