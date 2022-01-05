package se.kth.iv1350.amazingpos.integration;

import se.kth.iv1350.amazingpos.model.Sale;
import java.util.ArrayList;
import java.util.List;

/**
 *  This dummy implementation of external accounting system.
 */
public class AccountingSystem {
    private List<Sale> AccountingSystem = new ArrayList<>();

    /**
     * Creates a new instance representing a external accounting system.
     */
    AccountingSystem(){}

    /**
     * In the end of every sale, the last sale will be
     * memorized here.
     *
     * @param sale The last sale that was performed
     * will be saved with previous sales.
     */

    public void updateAccounting(Sale sale) {
        AccountingSystem.add(sale);
    }
}
