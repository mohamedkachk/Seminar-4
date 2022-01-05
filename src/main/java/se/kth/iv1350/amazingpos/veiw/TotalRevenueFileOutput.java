package se.kth.iv1350.amazingpos.veiw;

import se.kth.iv1350.amazingpos.model.SaleObserver;
import java.io.*;

/**
 * Prints total revenue to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private PrintWriter logStream;
    private Double revenue = 0.0;


    /**
     * Creates a new instance and also creates a new total-revenue file.
     * An existing total-revenue file will be deleted.
     */
    public TotalRevenueFileOutput() {
        try {
            logStream = new PrintWriter(new FileWriter("Total-Revenue.txt"), true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * The specified total revenue of sold items is logged to a total-Revenue file.
     *
     * @param total The amount that will be added to the total revenue
     * that will be printed to the total-revenue.
     */
    @Override
    public void updateTotalRevenue(double total){
        this.revenue += total;
        logStream.println("Total Revenue: "+  revenue + " kr");
    }
}
