package se.kth.iv1350.amazingpos.model;

import java.util.List;


/**
 * Represents one receipt, the receipt of one sale.
 */
public class Receipt {
    private final Sale sale;

    /**
     * Creates a new instance.
     *
     * @param sale The sale proved by this receipt.
     */
    public Receipt(Sale sale) {
        this.sale = sale;
    }

    /**
     * Creates a completed string with the whole content
     * of the receipt.
     *
     * @return The completed receipt string.
     */
    public String createReceiptString() {
        List<ItemDescription> soldItems = sale.getSortedListOfSoldItems();
        StringBuilder builder = new StringBuilder();

        appendLine(builder, "---------------------RECEIPT---------------------");
        appendLine(builder,  sale.getTimeOfSale().toLocalDate().toString());
        appendLine(builder, "Items: ");

        for (ItemDescription soldItem: soldItems)
            appendLine(builder, sale.saleInformation.SaleInformation(soldItem,
                    soldItem.getQuantity()));

        if(sale.getDiscountAmount()!=0){
            appendLine(builder, "total price include discount: "+
                    sale.getTotalPriceAfterDiscount()+" kr");
        }else{
            appendLine(builder, "total price without discount: "+
                    sale.getTotalPriceAfterDiscount()+" kr");
        }
        appendLine(builder, "discount amount: "+ sale.getDiscountAmount()+" kr");
        appendLine(builder, "----------------------END----------------------");
        return builder.toString();
    }

    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }
    private void endSection(StringBuilder builder) {
        builder.append("\n");
    }


}
