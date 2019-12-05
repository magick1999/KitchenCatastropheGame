package group44.exceptions;

/**
 * Represents an exception when collision occurs.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class CollisionException extends Exception {
    private static final long serialVersionUID = -935361923121137557L;

    /**
     * Constructs a new CollisionException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the {@link #getMessage()} method.
     */
    public CollisionException(String message) {
        super(message);
    }
}