package se.kth.iv1350.amazingpos.model;

import se.kth.iv1350.amazingpos.integration.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Is responsible for finding the correct items descriptions
 * that store sells.
 */
public class ItemRegistry {
    private List<ItemDescription> itemsForSale = new ArrayList<>();

    /**
     * Creates a instance of a dummy list of
     * available items for sale in the store.
     */
    public ItemRegistry() {
        addItems();
    }

    /**
     * Get the value of itemsForSale
     *
     * @return the value of itemsForSale
     */
    public List<ItemDescription> getItemsForSale() {
        return this.itemsForSale;
    }

    /**
     * Search for a item matching the specified itemIdentifier.
     *
     * @param itemIdentifier This String contains the identifier
     *  of specific item.
     *
     * @return <code>ItemDescription</code> If itemIdentifier we are looking after
     * exist.
     *
     * @throws ItemNotExistException if no itemIdentifier is matched with searched identifier
     */
    public ItemDescription findItem(String itemIdentifier) throws ItemNotExistException  {
        if (!checkIfItemAlreadyExist(itemIdentifier)) {
            throw new ItemNotExistException (itemIdentifier);
        }
            ItemDescription validItem = new ItemDescription();
            return validItem.ItemDescription(itemIdentifier);
    }

    /**
     * Search for a item matching the specified itemIdentifier.
     *
     * @param itemIdentifier This String contains the identifier
     *  of specific item.
     *
     * @return <code>true</code> if a item with the same
     * identifier as <code>itemIdentifier</code> was found,
     * <code>false</code> if no such item with the specified
     * identifier was found.
     *
     * @throws RuntimeException if the itemIdentifier equals to null,
     * the database calling will be failed.
     */
    private boolean checkIfItemAlreadyExist(String itemIdentifier) {
        if (itemIdentifier == null)
            throw new RuntimeException("Failed to connect to database.");

        for (ItemDescription item : itemsForSale){
            if (item.getItemId().equals(itemIdentifier))
                return true;
        }
            return false;

    }

    private void  addItems(){
        itemsForSale.add(new ItemDescription("apple", 0, 1, 1 ));
        itemsForSale.add(new ItemDescription("milk", 0, 1, 1 ));
        itemsForSale.add(new ItemDescription("juice", 0, 1, 1 ));
        itemsForSale.add(new ItemDescription("banana", 0, 1, 1 ));
        itemsForSale.add(new ItemDescription("Bread", 0, 1, 1 ));
    }

}
