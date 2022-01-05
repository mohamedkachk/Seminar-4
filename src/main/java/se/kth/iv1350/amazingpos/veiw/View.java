package se.kth.iv1350.amazingpos.veiw;

import se.kth.iv1350.amazingpos.controller.*;
import se.kth.iv1350.amazingpos.integration.*;
import se.kth.iv1350.amazingpos.model.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;

/**
 * This program has no "actual" view, instead, this class is a
 * placeholder for the entire view to run fake execution.
 */
public class View {
    LocalDate Time = LocalDateTime.now().toLocalDate();
    private PrintWriter log_File;
    private Controller contr;

    /**
     *  A new instance is created, that represent a view and uses the
     *  specified controller for all calls to other layers.
     *
     * @param @param contr The controller that is used for all calls
     * to other layers to perform system operations.
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileOutput());
        log_File = new PrintWriter(new FileWriter("log-Error.txt"), true);
    }

    /**
     * Creates a simple execution that can take inputs from
     * user for calling and running all system operations
     */
    public void sampleExecution (){
        System.out.println("New sale is started.\n");
        contr.startSale();
       try {
           scanItemView("apple", 4 );
           scanItemView("milk", 1 );
           scanItemView( "non-existing-item", 4 );
           scanItemView(null, 4 );


           contr.showTotalPriceAndVAT();
           System.out.println(contr.showTotalPriceAndVAT());
           contr.discount("11111");
           Amount paidAmount = new Amount(100);
           contr.pay(paidAmount);

       } catch (ItemNotExistException e){
           System.out.println( " This item does not Exist in the store");
       }catch (RuntimeException e){
           System.out.println(" Database is not accessible now, please try again ");
       }
    }

    /**
     * Creates a simple execution that can be called from
     * sampleExecution for calling and running all system operations
     *
     * @param itemIdentifier The id of searched item.
     * @param quantity The number of searched item.
     */
   public void scanItemView(String itemIdentifier, int quantity)throws ItemNotExistException {
       try {
           String saleIfo = contr.scanItem(itemIdentifier, quantity);
           System.out.println(saleIfo);
       } catch (ItemNotExistException e){
           System.out.println(
                   "\n------------------- Error for user -----------------" +
                   "\n"+Time+ " Error: The item does not exist in system\n"+
                   "---------------------------------------------------- ");
           logException(e);
       }catch (DatabaseNotAvailableException e){
           System.out.println(
                   "\n------------------------------- Error for user ----------------------------" +
                   "\n"+Time+ " Error: The database cannot be accessed now, please try again later\n"+
                   "--------------------------------------------------------------------------- ");
          logException(e);
       }
   }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exception the exception that shall be thrown
     */
    public void logException(Exception exception){
        String str = "*********** Error for developer **************";
        System.out.println("\n" + str);
        log_File.println(str);
        str = Time + " Exception was thrown: "+ exception.getMessage();
        System.out.println(str);
        log_File.println(str);
        exception.printStackTrace(System.out);
        exception.printStackTrace(log_File);
        str = "******************* END *********************\n";
        log_File.println(str);
        System.out.println(str);
    }
}