package group44.exceptions;

import group44.entities.TokenDoor;
import group44.entities.CollectableItems.Token;

/**
 * Represents an exception raised when trying to open {@link TokenDoor} without
 * having enough {@link Token}s.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class NotEnoughTokensException extends Exception {
    private static final long serialVersionUID = -6212954209661729140L;

    /**
     * Constructs a new NotEnoughTokensException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for later
     *                retrieval by the {@link #getMessage()} method.
     */
    public NotEnoughTokensException(String message) {
        super(message);
    }
}