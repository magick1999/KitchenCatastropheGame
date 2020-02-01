package group44.entities;

import group44.game.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Abstract class from which all other classes in the game inherit.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class LevelObject {
    /** Title of the {@link LevelObject}. */
    private String title;
    /** Path to the image representing the {@link LevelObject} in the game. */
    private String imagePath;
    /** Image representing the {@link LevelObject} in the game. */
    private Image image;
    /** {@link Level} where the {@link LevelObject} is located. */
    private Level level;
    /** Position X of the {@link LevelObject}. */
    private int positionX;
    /** Position Y of the {@link LevelObject}. */
    private int positionY;

    /**
     * Creates a new {@link LevelObject}.
     *
     * @param level
     *            The {@link Level} in which the {@link LevelObject} exists.
     * @param title
     *            Title of the object.
     * @param positionX
     *            Position X in the game.
     * @param positionY
     *            Position Y in the game.
     */
    public LevelObject(Level level, String title, int positionX,
            int positionY) {
        this.level = level;
        this.setTitle(title);
        this.setPosition(positionX, positionY);
    }

    /**
     * Creates a new {@link LevelObject}.
     *
     * @param level
     *            The {@link Level} in which the {@link LevelObject} exists.
     * @param title
     *            Title of the object.
     * @param positionX
     *            Position X in the game.
     * @param positionY
     *            Position Y in the game.
     * @param imagePath
     *            Image path of the instance.
     */
    public LevelObject(Level level, String title, int positionX, int positionY,
            String imagePath) {
        this(level, title, positionX, positionY);
        this.imagePath = imagePath;
        this.setImage(new Image(imagePath));
    }

    /**
     * Gets the title of an object.
     *
     * @return the title of the object.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title of an object.
     *
     * @param newTitle
     *            the title to set.
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Returns the path to the image.
     *
     * @return the image path.
     */
    protected String getImagePath() {
        return this.imagePath;
    }

    /**
     * Returns an image of the object.
     *
     * @return the image of the object.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Sets an image of the object.
     *
     * @param image
     *            the image.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Returns the {@link Level} in which the object is located.
     *
     * @return the {@link Level}.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Returns the X coordinate of an object.
     *
     * @return the x coordinate of an object.
     */
    public int getPositionX() {
        return this.positionX;
    }

    /**
     * Returns the y coordinate of an object.
     *
     * @return the y coordinate of an object.
     */
    public int getPositionY() {
        return this.positionY;
    }

    /**
     * Sets object position to x and y.
     *
     * @param x
     *            X coordinate.
     * @param y
     *            Y coordinate.
     */
    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    /**
     * Draws the object in {@link GraphicsContext} if there is an Image
     * associated.
     *
     * @param gc
     *            {@link GraphicsContext} used to draw the object.
     * @param x
     *            The X coordinate in the {@link GraphicsContext} where to draw
     *            the {@link LevelObject}.
     * @param y
     *            The Y coordinate in the {@link GraphicsContext} where to draw
     *            the {@link LevelObject}.
     * @param width
     *            The width of the {@link LevelObject} in the
     *            {@link GraphicsContext}.
     * @param height
     *            The height of the {@link LevelObject} in the
     *            {@link GraphicsContext}.
     */
    public void draw(GraphicsContext gc, double x, double y, double width,
            double height) {
        if (this.getImage() != null) {
            gc.drawImage(this.getImage(), x, y, width, height);
        }
    }
}
