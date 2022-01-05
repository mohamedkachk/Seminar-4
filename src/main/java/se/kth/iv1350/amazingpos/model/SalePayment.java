package se.kth.iv1350.amazingpos.model;


import se.kth.iv1350.amazingpos.integration.CashCounter;

/**
 * Represents one specific payment for one specific sale. The
 * sale can be payed with cash or by credit.
 */
public class SalePayment {
    private Amount totalCost;
    private Amount paidAmt;
    private CashCounter cashCounter;

    /**
     * Creates a new instance. The customer will pay a specific amount
     * of currency.
     *
     * @param paidAmt The amount of money that was handed over
     * by the customer.
     *
     * @param cashCounter is used to refer to class cashCounter
     * to update the balance.
     */
    public SalePayment(Amount paidAmt, CashCounter cashCounter) {
        this.cashCounter = cashCounter;
        this.paidAmt = paidAmt;
    }

    /**
     * Calculates the total cost of the specified sale.
     *
     * @param sale The sale for which the customer
     * is paying.
     */
    public void calculateTotalSale(Sale sale){
        totalCost = new Amount((int)sale.getTotalPriceAfterDiscount());
        cashCounter.addCash(this);
    }

    /**
     * Get the value of totalCost
     *
     * @return the value of totalCost
     */
    public Amount getTotalCost() {
        return totalCost;
    }

    /**
     * @return The amount of change the customer shall have.
     */
    public Amount getChange(){
        return paidAmt.minus(totalCost);
    }

    /**
     * Get the value of paidAmt
     *
     * @return the value of paidAmt
     */
    public Amount getPaidAmt(){
        return paidAmt;
    }
}
