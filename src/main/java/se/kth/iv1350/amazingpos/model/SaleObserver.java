package se.kth.iv1350.amazingpos.model;

/**
 * A listener interface for receiving that total Revenue when a payment have been made.
 * The class that is interested in such notification implements this interface, and
 * the object created with that class is registered with sale items.
 * all The observers will be notified when the total amount has been paid for purchases.
 *
 */

public interface SaleObserver {
    /**
     * Invoked when a sale has been paid.
     *
     * @param total The amount of sold items.
    */
    void updateTotalRevenue(double total);
}
