package se.kth.iv1350.amazingpos.controller;

import se.kth.iv1350.amazingpos.integration.*;
import se.kth.iv1350.amazingpos.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the application’s only controller class. All
 * calls to the model pass through here.
 */
public class Controller {
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private DiscountRegistry discountRegistry;
    private CustomerRegistry customerRegistry;
    private Printer printer;
    private CashCounter cashCounter;
    private ItemRegistry itemRegistry;
    private Sale sale;
    private ItemDescription validItem;
    private List<SaleObserver> saleObserver = new ArrayList<>();
    /**
     * Creates a new instance.
     *
     * @param systemCreator Used to get all classes that handle external database
     * calls.
     * @param registryCreator Used to get all classes that handle customers database
     *
     * @param paymentCreator Used to get all classes that are needed to end the sale
     *                        like cash Counter and interface to printer.
     */
    public Controller(SystemCreator systemCreator, RegistryCreator registryCreator, PaymentCreator paymentCreator) {
        this.accountingSystem = systemCreator.getAccountingSystem();
        this.inventorySystem = systemCreator.getInventorySystem();
        this.discountRegistry = registryCreator.getDiscountRegistry();
        this.customerRegistry = registryCreator.getCustomerRegistry();
        this.cashCounter = paymentCreator.getCashCounter();
        this.printer = paymentCreator.getPrinter();
        this.itemRegistry = new ItemRegistry();
    }

    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale() {
        this.sale = new Sale();
        sale.addSaleObserver(saleObserver);

    }
    /**
     * Search for an item matching the specified itemIdentifier.
     *
     * @param itemIdentifier This item we are looking after if it
     * exists in our item registry then it will be added to sale.
     * @param quantity The amount of that item.
     *
     * @return A string describes the specified item if the item exists.
     *
     * @throws ItemNotExistException if no itemIdentifier was found.
     * @throws RuntimeException if unable to access itemRegistry to search for some item.
     * @throws DatabaseNotAvailableException if the database cannot be accessed.
     */
    public String scanItem(String itemIdentifier, int quantity) throws ItemNotExistException, DatabaseNotAvailableException {
       try{
        validItem = itemRegistry.findItem(itemIdentifier);
        return sale.countItem(validItem, quantity);
       }catch (RuntimeException exception){
           throw new DatabaseNotAvailableException("could not access the database.", exception );
       }
    }

    /**
     *  Shows the total price with VAT rate
     *  for all item in the end of item list.
     *
     * @return A string contains the total price
     * and total VAt.
     */
    public String showTotalPriceAndVAT() {
        return sale.updateTotalPriceAndVAT();
    }
    /**
     *  @param customerID represents a customer that
     *  we looking after in customer registry. If customer exists
     *  , it will be sended as eligible customer for discount to DiscountRegistry.
     *
     * @return A string contains the percentage rate of whole sale.
     */
    public void discount(String customerID) {
        CustomerRegistry existingCust = customerRegistry.customerControl(customerID);
        DiscountRegistry eligibleCustomer = discountRegistry.discountControl(existingCust);
        sale.applyDiscount(eligibleCustomer);
    }

    /**
     * Handles sale payment. Updates the balance of the cash counter
     * where the payment was performed. Calculates change. Prints the
     * receipt. Updates the inventory system and accounting system.
     *
     * @param paidAmt The paid amount.
     *
     * @throws IllegalStateException if the paid amount is lower
     * than total price.
     */
    public void pay(Amount paidAmt) {
        if ((paidAmt.getAmount() - sale.getTotalPriceAfterDiscount()) < 0) {
            throw new IllegalStateException("The paid amount is lower than total price");
        }
            SalePayment payment = new SalePayment(paidAmt, cashCounter);
            sale.pay(payment);
            sale.printReceipt(printer);
            sale.updateExternalSystem(accountingSystem, inventorySystem);
    }

    /**
     * Get the value of sale
     *
     * @return The current sale.
     */
    public Sale getSale() {
        return  this.sale;
    }

    /**
     * The observer will be notified when the total amount has been paid for purchases.
     *
     * @param obs The observer to notify.
     */
    public void addSaleObserver(SaleObserver obs) {
         saleObserver.add(obs);
    }
}
