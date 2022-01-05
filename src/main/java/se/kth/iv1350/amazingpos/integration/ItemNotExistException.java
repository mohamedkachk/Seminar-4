package se.kth.iv1350.amazingpos.integration;

/**
 * Thrown when the specified itemIdentifier
 * could not be found in itemRegistry.
 */
public class ItemNotExistException extends Exception{
private String itemIdentifier;
    /**
     * Creates a new instance with a message specifying the itemIdentifier could not be found,
     * @param message contains the itemIdentifier that could not be found.
     */
    public ItemNotExistException(String message) {
        super("the Item --> "+ message+ " <-- does not exist in our inventory catalog ");
        this.itemIdentifier = message;
    }

    /**
     * Creates a new instance with a message specifying the error has been occurred and
     * root cause.
     *
     * @param message the detailed message describes the error.
     * @param throwable the exception that was thrown
     */
    public ItemNotExistException(String message, Throwable throwable) {
        super(message, throwable);
    }
    /**
     * @return the itemIdentifier that does not exist.
     */
    public String getItemNotExist() {
        return itemIdentifier;
    }
}
