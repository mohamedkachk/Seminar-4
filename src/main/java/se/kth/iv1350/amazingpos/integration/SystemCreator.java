package se.kth.iv1350.amazingpos.integration;

/**
 * This class is responsible for instantiating accounting system
 * and inventory system.
 */
public class SystemCreator {
    private AccountingSystem accountingSystem = new AccountingSystem();
    private InventorySystem  inventorySystem = new InventorySystem();

    /**
     * Get the value of  accountingSystem
     *
     * @return the value of accountingSystem
     */
    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    /**
     * Get the value of  inventorySystem
     *
     * @return the value of inventorySystem
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }
}

