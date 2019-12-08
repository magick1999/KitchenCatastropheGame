package group44.game;

/**
 * Represents an area between two coordinates.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Area {
    /** The X of the first coordinate. */
    private int x1;
    /** The X of the second coordinate. */
    private int x2;
    /** The Y of the first coordinate. */
    private int y1;
    /** The Y of the second coordinate. */
    private int y2;

    /**
     * Creates a new {@link Area}.
     *
     * @param x1
     *            - The X of the first coordinate
     * @param y1
     *            - The Y of the first coordinate
     * @param x2
     *            - The X of the second coordinate
     * @param y2
     *            - The Y of the second coordinate
     */
    public Area(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Gets the X of the first coordinate.
     *
     * @return the X1 coordinate
     */
    public int getX1() {
        return this.x1;
    }

    /**
     * Gets the X of the second coordinate.
     *
     * @return the X2 coordinate
     */
    public int getX2() {
        return this.x2;
    }

    /**
     * Gets the Y of the first coordinate.
     *
     * @return the Y1 coordinate
     */
    public int getY1() {
        return this.y1;
    }

    /**
     * Gets the Y of the second coordinate.
     *
     * @return the Y2 coordinate
     */
    public int getY2() {
        return this.y2;
    }
}
