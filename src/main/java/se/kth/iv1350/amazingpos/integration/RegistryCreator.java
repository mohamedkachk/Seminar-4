package se.kth.iv1350.amazingpos.integration;

/**
 * This class is responsible for instantiating discount registry
 * and customer registry.
 */
public class RegistryCreator {
    private DiscountRegistry discountRegistry = new DiscountRegistry();
    private CustomerRegistry customerRegistry = new CustomerRegistry();

    /**
     * Get the value of discountRegistry
     *
     * @return the value of discountRegistry
     */
    public DiscountRegistry getDiscountRegistry() {
        return discountRegistry;
    }

    /**
     * Get the value of customerRegistry
     *
     * @return the value of customerRegistry
     */
    public CustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

}
