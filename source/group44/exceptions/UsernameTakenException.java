package group44.exceptions;

import group44.models.Profile;

/**
 * Represents an exception raised when trying to register a new {@link Profile}
 * with already existing username.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class UsernameTakenException extends Exception {
    private static final long serialVersionUID = -2669174231705636936L;

    /**
     * Constructs a new UsernameTakenException with the specified detail
     * message.
     *
     * @param message
     *            the detail message. The detail message is saved for later
     *            retrieval by the {@link #getMessage()} method.
     */
    public UsernameTakenException(String message) {
        super(message);
    }
}
