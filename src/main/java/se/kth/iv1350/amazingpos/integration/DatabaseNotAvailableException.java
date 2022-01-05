package se.kth.iv1350.amazingpos.integration;

/**
 * Thrown when the database calling is failed while the app operates in controller.
 */
public class DatabaseNotAvailableException extends Exception {

    /**
     * Creates a new instance with a message specifying the error has been occurred.
     *
     * @param message the detailed message describes the error.
     */
    public DatabaseNotAvailableException(String message) {
        super(message);
    }

    /**
     * Creates a new instance with a message specifying the error has been occurred and
     * root cause.
     *
     * @param message the detailed message describes the error.
     * @param throwable the exception that was thrown
     */
    public DatabaseNotAvailableException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
