package se.kth.iv1350.amazingpos.integration;

import se.kth.iv1350.amazingpos.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This dummy implementation of external inventory system.
 *
 * Contains all calls to the data store with performed sales;
 */
public class InventorySystem {
    private List<ItemDescription> inventoryItems = new ArrayList<>();

    /**
     * Creates a new instance of a fake inventory system that contains
     * available items to sell.
     */
    InventorySystem(){
        addItems();
    }

    /**
     * Updates the quantity of available items in the inventory.
     *
     * @param sale Will be used to get quantity of sold items to
     * update inventory
     */
    public void updateInventory(Sale sale){
        List<ItemDescription> soldItems = sale.getSortedListOfSoldItems();
        for (ItemDescription soldItem: soldItems) {
            for (ItemDescription inventoryItem : inventoryItems)
                if (inventoryItem.getItemId().equals(soldItem.getItemId()));{
                decreaseQuantityOfItem(soldItem);
                soldItem.setQuantity(0);
            }
        }
    }

    /**
     * Decreases the quantity of one specific item that
     * is available in the inventory.
     *
     * @param soldItem representing one specific sold
     * item that must be subtracted from inventory.
     */
    private void decreaseQuantityOfItem(ItemDescription soldItem){
        for (ItemDescription inventoryItem : inventoryItems)
            if (inventoryItem.getItemId().equals(soldItem.getItemId()))
                inventoryItem.setQuantity(decreaseQuantity(inventoryItem, soldItem));
    }

    /**
     * Will perform the decreasing of quantity for available tem in inventory
     *
     * @param inventoryItem Will be decreased by sold item's quantity
     * @param soldItem Will be subtracted from inventory's item
     * @return The difference of available quantity after subtraction operation
     */
    private int decreaseQuantity(ItemDescription inventoryItem, ItemDescription soldItem ){
        if ((inventoryItem.getQuantity()-soldItem.getQuantity()) < 0 )
            return 0;
        return(inventoryItem.getQuantity()-soldItem.getQuantity());
    }

    /**
     * Simulates available items in inventory that
     */
    private void addItems() {
        inventoryItems.add(new ItemDescription("apple", 1000, 1, 1 ));
        inventoryItems.add(new ItemDescription("milk", 1000, 1, 1 ));
        inventoryItems.add(new ItemDescription("juice", 1000, 1, 1 ));
        inventoryItems.add(new ItemDescription("banana", 1000, 1, 1 ));
        inventoryItems.add(new ItemDescription("Bread", 1000, 1, 1 ));
        inventoryItems.add(new ItemDescription("chocolate", 1000, 1, 1));
    }

    public List<ItemDescription> getInventoryItems(){
        return this.inventoryItems;
    }
}
