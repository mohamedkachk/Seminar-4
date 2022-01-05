package se.kth.iv1350.amazingpos.veiw;

import se.kth.iv1350.amazingpos.model.SaleObserver;

/**
 * Shows the total revenue of the all sold items
 */
public class TotalRevenueView implements SaleObserver {
    private Double revenue = 0.0;


    /**
     * The specified total revenue will be shown on display.
     *
     * @param total The amount of sold items that will be added to the total revenue
     * that will be printed on display.
     */
    @Override
    public void updateTotalRevenue(double total){
        this.revenue += total;
        System.out.println("######################" +
                   "\nTotal Revenue: " + revenue + " kr\n" +
                           "######################");
    }
}
