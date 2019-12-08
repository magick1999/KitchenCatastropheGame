package group44.entities.collectableItems;

import java.util.ArrayList;

import group44.Constants;
import group44.exceptions.NotEnoughTokensException;

/**
 * Represents an accumulator for {@link Token}s.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class TokenAccumulator extends CollectableItem {
    private static final String EXCEPTION_NOT_ENOUGH_TOKENS_EXCEPTION_MESSAGE = "User tried to use more tokens than were collected.";

    private ArrayList<Token> accumulatedTokens;

    /**
     * Creates a new instance of {@link TokenAccumulator}.
     */
    public TokenAccumulator() {
	super(null, Constants.TITLE_TOKEN_ACCUMULATOR);
	this.accumulatedTokens = new ArrayList<>();
    }

    /**
     * Add {@link Token} to the accumulator if it's not already there.
     *
     * @param token
     *            - the collected {@link Token}.
     */
    public void addToken(Token token) {
	if (this.isCollected(token) == false) {
	    this.accumulatedTokens.add(token);
	}
    }

    /**
     * Checks if the token was already collected or not.
     *
     * @param token
     *            - the collected {@link Token}.
     * @return true if the token is already collected, false otherwise.
     */
    private boolean isCollected(Token token) {
	for (Token item : this.accumulatedTokens) {
	    if (item == token) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Uses certain number of tokens in the accumulator.
     *
     * @param count
     *            - the number of tokens to use.
     * @throws NotEnoughTokensException
     *             if user tried to use more tokens than were collected.
     */
    public void use(int count) throws NotEnoughTokensException {
	if (this.getTokensCount() < count) {
	    throw new NotEnoughTokensException(
		    TokenAccumulator.EXCEPTION_NOT_ENOUGH_TOKENS_EXCEPTION_MESSAGE);
	}
	for (int i = 0; i < count; i++) {
	    this.accumulatedTokens.remove(0);
	}
    }

    /**
     * Returns a number of tokens in the accumulator.
     *
     * @return number of tokens.
     */
    public int getTokensCount() {
	return this.accumulatedTokens.size();
    }

    /**
     * Returns a string representation of the {@link TokenAccumulator}.
     *
     * @return a string representation of the {@link TokenAccumulator}.
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();

	if (accumulatedTokens.size() > 0) {
	    if (accumulatedTokens.size() == 1) {
		builder.append(accumulatedTokens.get(0).toString());
	    } else {
		builder.append(accumulatedTokens.get(0).toString());

		for (int i = 1; i < accumulatedTokens.size(); i++) {
		    builder.append(Constants.LEVEL_OBJECT_DELIMITER);
		    builder.append(accumulatedTokens.get(i).toString());
		}
	    }
	}

	return builder.toString();
    }
}