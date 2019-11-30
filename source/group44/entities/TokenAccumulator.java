package group44.entities;

import java.util.ArrayList;

import group44.exceptions.NotEnoughTokensException;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents an accumulator for {@link Token}s.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class TokenAccumulator extends CollectableItem {
    private static final String NOTENOUGHTOKENSEXCEPTION_MESSAGE = "User tried to use more tokens than were collected.";

    private ArrayList<Token> accumulatedTokens;

    /**
     * Creates a new instance of {@link TokenAccumulator}.
     */
    public TokenAccumulator() {
        super(null, "Token Accumulator", 0, 0, "");
        this.accumulatedTokens = new ArrayList<>();
    }

    /**
     * Draws the object in {@link GraphicsContext}.
     * 
     * @param gc     - {@link GraphicsContext} used to draw the object.
     * @param x      - The X coordinate in the {@link GraphicsContext} where to draw
     *               the {@link LevelObject}.
     * @param y      - The Y coordinate in the {@link GraphicsContext} where to draw
     *               the {@link LevelObject}.
     * @param width  - The width of the {@link LevelObject} in the
     *               {@link GraphicsContext}.
     * @param height - The height of the {@link LevelObject} in the
     *               {@link GraphicsContext}.
     */
    @Override
    public void draw(GraphicsContext gc, double x, double y, double width, double height) {
        System.out.println(this.getTitle() + ": TokenAccumulator.draw(...)"); // Do not draw
    }

    /**
     * Add {@link Token} to the accumulator if it's not already there.
     * 
     * @param token - the collected {@link Token}.
     */
    public void addToken(Token token) {
        if (this.isCollected(token) == false) {
            this.accumulatedTokens.add(token);
        }
    }

    /**
     * Checks if the token was already collected or not.
     * 
     * @param token - the collected {@link Token}.
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
     * @param count - the number of tokens to use
     * @throws NotEnoughTokensException if user tried to use more tokens than were
     *                                  collected
     */
    public void use(int count) throws NotEnoughTokensException {
        if (this.getTokensCount() < count) {
            throw new NotEnoughTokensException(TokenAccumulator.NOTENOUGHTOKENSEXCEPTION_MESSAGE);
        }
        for (int i = 0; i < count; i++) {
            this.accumulatedTokens.remove(0);
        }
    }

    /**
     * Returns a number of tokens in the accumulator.
     * 
     * @return number of tokens
     */
    public int getTokensCount() {
        return this.accumulatedTokens.size();
    }
}