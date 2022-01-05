package se.kth.iv1350.amazingpos.model;

/**
 * Represents the data of a sale.
 */
public class SaleInformation {

    private ItemDescription itemDesc;
    private int quantity;
    private int taxRate;
    private int price;
    private double discountRate;
    private String itemId;

    /**
     * Creates a new instance representing a particular item.
     *
     * @param itemDesc an instance of ItemDescription contains
     * all information about a specific item.
     * @param quantity The quantity of the item.
     *
     * @return A string contains item's information like VAT rate,
     * total price (including VAT) and quantity of a item.
     */
    public String SaleInformation(ItemDescription itemDesc, int quantity) {
        this.itemDesc = itemDesc;
        this.quantity = quantity;
        this.itemId = itemDesc.getItemId();
        this.taxRate = itemDesc.getTaxRate();
        this.price = itemDesc.getPrice();
        return this.toString();
    }

    /**
     * Creates a line of string with item name,
     * quantity, tax rate and price including VAT.
     *
     * @return A string contains item's information.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(itemId + " \t" +"quantity: " + quantity +" \t");
        builder.append("taxRate: " + (price*taxRate*quantity) + "  \t");
        builder.append("price: " + ((quantity*price)+(price*taxRate*quantity)) +" \t");
        return builder.toString();
    }

    /**
     * Prints out discount rate.
     *
     * @param discountRate The discount rate for
     * one specific customer.
     */
    public void includeDiscount(double discountRate){
        if(discountRate < 1){
            this.discountRate = discountRate;
            this.discountRate = (100*this.discountRate);
        }
        System.out.print("discount rate: "+((int)this.discountRate)+"%\n");
    }
}
