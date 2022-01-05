package se.kth.iv1350.amazingpos.model;


/**
 * Represents an amount of money. Instances are immutable.
 */
public final class Amount {
    private final  int amount;

    /**
     * Creates a new instance, representing the specified
     * amount.
     *
     * @param amount The amount represented by the newly
     * created instance.
     */
    public Amount(int amount) {
        this.amount = amount;
    }

    /**
     * Creates a new instance with zero amount.
     */
    public Amount() {
        this.amount = 0;
    }

    /**
     * Get the value of amount.
     *
     * @return The value of amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Subtracts the specified {@link Amount} from
     * this object and returns an {@link Amount}
     * instance with the result.
     *
     * @param other The <code>Amount</code> to subtract.
     * @return The result is the difference of the subtraction.
     */
    public Amount minus(Amount other) {
        return new Amount(amount - other.amount);
    }

    /**
     * adds the specified {@link Amount} with
     * this object and returns an {@link Amount}
     * instance with the sum of addition.
     *
     * @param other The <code>Amount</code> to add.
     * @return The result is the sum of the addition.
     */
    public Amount plus(Amount other){
        return new Amount(this.amount + other.amount);
    }

    /**
     * Two <code>Amount</code>s are equal if they represent the
     * same amount.
     *
     * @param other The <code>Amount</code> to compare with this
     * amount.
     * @return <code>true</code> if the specified amount is equal
     * to this amount, <code>false</code> if it is not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Amount))
        {
            return false;
        }
        Amount otherAmount = (Amount) other;
        return amount == otherAmount.amount;
    }
    /**
     * converts the <code>Amount</code> into a <code>String</code> object.
     *
     * @return <code>Amount</code> as a <code>String</code>.
     */
    @Override
    public String toString() {
        return Integer.toString(amount);
    }


}
