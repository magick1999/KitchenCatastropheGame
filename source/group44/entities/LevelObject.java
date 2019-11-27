package group44.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import group44.game.Level;

/**
 * Abstract class from which all other classes in the game inherit.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class LevelObject {
    private String title;
    private Color backgroundColor;
    private Color foregroundColor;
    private Image image;
    private Level level;
    private int positionX; // Position X in the game array
    private int positionY; // Position Y in the game array

    /**
     * Creates a new {@link LevelObject} with default colors.
     * 
     * @param level     - The {@link Level} in which the {@link LevelObject} exists
     * @param title     - Title of the object
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     */
    public LevelObject(Level level, String title, int positionX, int positionY) {
        this.level = level;
        this.setTitle(title);
        this.setPosition(positionX, positionY);
        // Default colors
        this.setBackgroundColor(Color.RED);
        this.setForegroundColor(Color.BLACK);
    }

    /**
     * Creates a new {@link LevelObject}.
     * 
     * @param level     - The {@link Level} in which the {@link LevelObject} exists
     * @param title     - Title of the object
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param imagePath - Image path of the instance
     */
    public LevelObject(Level level, String title, int positionX, int positionY, String imagePath) {
        this(level, title, positionX, positionY);
        this.setImage(new Image(imagePath, true));
    }

    /**
     * Creates a new {@link LevelObject}.
     * 
     * @param level           - The {@link Level} in which the {@link LevelObject}
     *                        exists
     * @param title           - Title of the object
     * @param positionX       - Position X in the game
     * @param positionY       - Position Y in the game
     * @param foregroundColor - Foreground color
     * @param backgroundColor - Background color
     */
    public LevelObject(Level level, String title, int positionX, int positionY, Color foregroundColor,
            Color backgroundColor) {
        this(level, title, positionX, positionY);
        this.setForegroundColor(foregroundColor);
        this.setBackgroundColor(backgroundColor);
    }

    /**
     * Gets the title of an object.
     *
     * @return the title of the object
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title of an object.
     *
     * @param title - the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the background color of the object.
     * 
     * @return the background color of the object
     */
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Sets a background color of an object.
     * 
     * @param color - background color
     */
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    /**
     * Returns the foreground color of the object.
     * 
     * @return the foreground color of the object
     */
    public Color getForegroundColor() {
        return this.foregroundColor;
    }

    /**
     * Sets the foreground color of the object.
     * 
     * @param color - foreground color
     */
    public void setForegroundColor(Color color) {
        this.foregroundColor = color;
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
     * @param image - the image
     */
    public void setImage(Image image) {
        this.image = image;
    }

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
     * @param x - X coordinate
     * @param y - Y coordinate
     */
    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    /**
     * Draws the object in {@link GraphicsContext}.
     * 
     * @param gc     - {@link GraphicsContext} used to draw the object
     * @param x      - The X coordinate in the {@link GraphicsContext} where to draw
     *               the {@link LevelObject}
     * @param y      - The Y coordinate in the {@link GraphicsContext} where to draw
     *               the {@link LevelObject}
     * @param width  - The width of the {@link LevelObject} in the
     *               {@link GraphicsContext}
     * @param height - The height of the {@link LevelObject} in the
     *               {@link GraphicsContext}
     */
    public void draw(GraphicsContext gc, double x, double y, double width, double height) {
        gc.drawImage(this.getImage(), x, y, width, height);
    }
}
