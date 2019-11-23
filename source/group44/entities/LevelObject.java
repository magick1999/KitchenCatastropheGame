package group44.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

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
    private int size; // Size 'a' of the "grid" cell.
    private int positionX;
    private int positionY;

    /**
     * Creates a new instance of {@link LevelObject} with default colors.
     * 
     * @param title     - Title
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     */
    public LevelObject(String title, int positionX, int positionY, int size) {
        this.setTitle(title);
        this.setPosition(positionX, positionY);
        this.setSize(size);
        // Default colors
        this.setBackgroundColor(Color.RED);
        this.setForegroundColor(Color.BLACK);
    }

    /**
     * Creates a new instance of {@link LevelObject}.
     * 
     * @param title     - Title
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     * @param imagePath - Image path of the instance
     */
    public LevelObject(String title, int positionX, int positionY, int size, String imagePath) {
        this(title, positionX, positionY, size);
        this.setImage(new Image(imagePath, size, size, true, true));
    }

    /**
     * Creates a new instance of {@link LevelObject}.
     * 
     * @param title           - Title
     * @param positionX       - Position X in the game
     * @param positionY       - Position Y in the game
     * @param size            - Size of the cell on the screen
     * @param foregroundColor - Foreground color
     * @param backgroundColor - Background color
     */
    public LevelObject(String title, int positionX, int positionY, int size, Color foregroundColor,
            Color backgroundColor) {
        this(title, positionX, positionY, size);
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

    /**
     * Returns a size of the object.
     * 
     * @return a size of the object.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Sets the size of the object.
     * 
     * @param size - size of the object.
     */
    public void setSize(int size) {
        this.size = size;
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
     * Draws the object.
     * 
     * @param gc - {@link GraphicsContext} used to draw the object
     */
    public abstract void draw(GraphicsContext gc);
}
