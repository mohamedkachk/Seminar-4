package se.kth.iv1350.amazingpos.model;

import java.util.List;

/**
 * Represents the data of an ItemDescription.
 */
public class ItemDescription {
    private int quantity;
    private int taxRate;
    private int price;
    private String itemId;

    /**
     * Creates a new instance representing a particular item.
     *
     * @param itemId The ID of the item.
     * @param quantity The quantity of the item.
     * @param taxRate The standard VAT rate of the item
     * @param price The price paid to buy the item.
     */
    public  ItemDescription(String itemId, int quantity, int taxRate, int price) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.taxRate = taxRate;
        this.price = price;
    }

    /**
     *  Creates a instance <code>ItemDescription</code>.
     */
    public ItemDescription() {
    }

    /**
     *  Search for a item matching the specified itemIdentifier.
     *
     * @param itemIdentifier The item's identifier of item we looking after.
     * @return <code>ItemDescription</code> If ItemDescription we looking after
     *  exist, otherwise return <code>null</code> if any item match
     *  the specified itemIdentifier.
     */
    public ItemDescription ItemDescription(String itemIdentifier) {
        List<ItemDescription> items = new ItemRegistry().getItemsForSale();
        for (ItemDescription item : items)
            if (item.getItemId().equals(itemIdentifier)){
                return new ItemDescription(item.itemId, item.quantity, item.taxRate, item.price);
            }
        return null;
    }

    /**
     * Get the value of itemId
     *
     * @return the value of itemId
     */
    public String getItemId(){
        return this.itemId;
    }

    /**
     * Get the value of taxRate
     *
     * @return the value of taxRate
     */
    public int getTaxRate(){
        return this.taxRate;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public int getPrice(){
        return this.price;
    }

    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * sets the quantity of a Specific item.
     *
     * @param quantity The new value of the quantity.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
