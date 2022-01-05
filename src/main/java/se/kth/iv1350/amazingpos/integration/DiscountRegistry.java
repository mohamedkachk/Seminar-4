package se.kth.iv1350.amazingpos.integration;

/**
 * Represents a discount registry.
 */
public class DiscountRegistry {
    private double discountRate;
    private String customerID;
    private final double NO_DISCOUNT_RATE = 1.0;

    /**
     * Creates a new instance of discount registry.
     */
    public DiscountRegistry(){
    }

    /**
     * Creates a new instance of discount registry that
     * applies the discount's rules on a specific customer.
     *
     * @param customerID Customer's identifier
     * @param discountRate Represents the rate of discount
     */
    public DiscountRegistry(String customerID, double discountRate) {
        this.customerID = customerID;
        this.discountRate = discountRate;
    }

    /**
     * Applies the discount on a specific customer based on age
     *
     * @param existingCustomer A customer is registered in the database of
     * customer registry
     *
     * @return A new instance of discountRegistry contains the discount rate and
     * customer's ID otherwise return an instance with null customer's ID
     */
    public DiscountRegistry discountControl(CustomerRegistry existingCustomer) {
        if(existingCustomer!=null){
            int age = existingCustomer.getAge();
            age = age / 10;
            double discountRate;
            switch (age) {
                case (1): discountRate = 1;
                    break;
                case 2: discountRate = 2;
                    break;
                case 3: discountRate = 3;
                    break;
                case 4: discountRate = 4;
                    break;
                case 5: discountRate = 5;
                    break;
                case 6: discountRate = 6;
                    break;
                case 7: discountRate = 7;
                    break;
                default: discountRate = 10;
                    break;
            }
            this.discountRate = discountRate / 100;
            return new DiscountRegistry(this.customerID, this.discountRate);
        }
        return new DiscountRegistry(null, 1);
    }

    /**
     * Get the value of discountRate
     *
     * @return the value of discountRate
     */
    public double getDiscountRate() {
        return this.discountRate;
    }
}
