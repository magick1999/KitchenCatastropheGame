package group44.exceptions;

/**
 * Exception raised when trying to parse invalid data type.
 *
 * @author Tomas Svejnoha.
 * @version 1.0
 */
public class ParsingException extends Exception {
    private static final long serialVersionUID = -900351075811069764L;

    /**
     * Creates a new instance of {@link ParsingException}.
     *
     * @param message
     *            error message
     */
    public ParsingException(String message) {
        super(message);
    }
}
