package group44.game;

/**
 * Represents a Position(X,Y).
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Position {
    private int x;
    private int y;

    /**
     * Creates a new instatnce of {@link Position}.
     * 
     * @param x
     *            - the X coordinate
     * @param y
     *            - the Y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the X coordinate of the {@link Position}.
     * 
     * @return the X coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the Y coordinate of the {@link Position}.
     * 
     * @return the Y coordinate
     */
    public int getY() {
        return this.y;
    }
}
