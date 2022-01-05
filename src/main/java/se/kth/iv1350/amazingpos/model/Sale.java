package se.kth.iv1350.amazingpos.model;


import se.kth.iv1350.amazingpos.integration.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one particular sale process,
 * by one particular customer and payed with one payment.
 */
public class Sale {
    public int totalPriceOfSale;
    private double discountRate = 1.0;
    private LocalDateTime saleTime = LocalDateTime.now();
    private List<ItemDescription> soldItems = new ArrayList<>();
    private List<ItemDescription> sortedListOfSoldItems = new ArrayList<>();
    SaleInformation saleInformation = new SaleInformation();
    private List<SaleObserver> saleObservers = new ArrayList<>();
    /**
     * Creates a new instance, and saves the time of the sale.
     */
    public Sale(){
        getTimeOfSale();
    }

    public static LocalDateTime getTimeOfSale(){
        return LocalDateTime.now();
    }

    /**
     * If the item is valid in itemRegistry, countItem will get the specific
     * item's description as argument, a string contains the sale information,
     * will be returned to view interface. SoldItems is an arrayList will save
     * all scanned item in.
     *
     * @param validItem Contains a new instance of itemDescription.
     * @param quantity Of the specific item.
     * @return A string shows price, VAT, quantity och items name.
     */
    public String countItem(ItemDescription validItem, int quantity) {
        validItem.setQuantity(quantity);
        soldItems.add(validItem);
        return saleInformation.SaleInformation(validItem, quantity);
    }

    /**
     * Will sort the items with same identifier and it will add them and their
     * quantities in one specific index in a smaller list.
     * @return A new sorted items List.
     */
    private List<ItemDescription> sortAndAddSameItemsTogetherInNewList() {
        for (int i = 0; soldItems.size() > i; i++)
        {
            for (int j = 1 + i; soldItems.size() > j; j++)
                if (soldItems.get(i).getItemId().equals
                        (soldItems.get(j).getItemId()))
                {
                    soldItems.get(i).setQuantity(
                            soldItems.get(i).getQuantity() +
                                    soldItems.get(j).getQuantity());
                    soldItems.get(j).setQuantity(0);
                }
            if(soldItems.get(i).getQuantity()!=0) {
                sortedListOfSoldItems.add(soldItems.get(i));
            }
        }
        return sortedListOfSoldItems;
    }

    /**
     * Applies the discount on the current sale if the customer is
     * eligible for that.
     *
     *  @param eligibleCustomer Customer is eligible for discount.
     */
    public void applyDiscount(DiscountRegistry eligibleCustomer){
        if(eligibleCustomer.getDiscountRate()<1) {
            this.discountRate = eligibleCustomer.getDiscountRate();
        }
        saleInformation.includeDiscount(this.discountRate);
    }

    /**
     * Updates the total price and total VAT amount after
     * scanning all item, by adding the prices of all
     * scanned items together, to obtain the total cost of sale.
     *
     * @return Total price and total VAT before discount calculation.
     */
    public String updateTotalPriceAndVAT() {
        int totalVAT = 0; int totalPrice = 0;
        for (ItemDescription item : soldItems){
            totalVAT +=((item.getPrice()*item.getTaxRate()*item.getQuantity()));
            totalPrice +=((item.getPrice()*item.getQuantity()));
        }
        totalPriceOfSale = (totalPrice + totalVAT);
        return ("total VAT: "+ totalVAT +"\t total price: "+(totalPrice+totalVAT));
    }

    /**
     * Get the value of sortedListOfSoldItems
     *
     * @return the value of sortedListOfSoldItems
     */
    public List<ItemDescription> getSortedListOfSoldItems(){
        return this.sortAndAddSameItemsTogetherInNewList();
    }

    /**
     * This sale is paid using the specified payment.
     *
     * @param payment The payment used to pay this sale.
     */
    public void pay(SalePayment payment) {
        payment.calculateTotalSale(this);
    }

    /**
     * Prints a receipt for the current sale on the
     * specified printer.
     */
    public void printReceipt(Printer printer) {
        Receipt receipt = new Receipt(this);
        printer.printReceipt(receipt);
        notifyObservers();
    }

    /**
     * sends sale information to external accounting system (for accounting)
     * and external inventory system (to update inventory).
     *
     * @param accountingSystem Accounting system
     * @param inventorySystem Inventory  system
     */
    public void updateExternalSystem(AccountingSystem accountingSystem,
                                     InventorySystem inventorySystem) {
        accountingSystem.updateAccounting(this);
        inventorySystem.updateInventory( this);
    }

    /**

     * calculates the total price including discount.
     *
     * @return A string with total price after applying the discount
     *  on the current sale, that will be printed in receipt.
     */
    public double getTotalPriceAfterDiscount(){
        if(this.discountRate <1 && this.discountRate >0) {
            return (totalPriceOfSale -(this.discountRate * totalPriceOfSale));
        }
        return totalPriceOfSale;
    }

    /**
     * Get the value of DiscountAmount
     *
     * @return the value of DiscountAmount or zero
     */
    public double getDiscountAmount() {
        if(this.discountRate < 1 && this.discountRate > 0) {
            return (this.discountRate * (double) totalPriceOfSale);
        }
        return 0;
    }

    /**
     * Get the value of DiscountRate
     *
     * @return the value of DiscountRate
     */
    public double getDiscountRate() {
        return  this.discountRate;
    }

    /**
     * Update the total revenue in the observers that have been notified,
     * when the total amount has been paid for a new sale
     *
     */
    private void notifyObservers() {
        for (SaleObserver obs : saleObservers) {
            obs.updateTotalRevenue(getTotalPriceAfterDiscount());
        }
    }

    /**
     * all The observers will be notified when the total amount has been paid for purchases.
     *
     * @param obs The observer to notify.
     */
    public void addSaleObserver(List<SaleObserver> obs) {
        saleObservers.addAll(obs);
    }
}
