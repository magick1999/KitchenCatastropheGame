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
    protected String title;
    private Color backgroundColor;
    private Color foregroundColor;
    private Image image;
    private int size; // Size 'a' of the "grid" cell.
    private int positionX;
    private int positionY;

    /**
     * Creates a new instance of LevelObject
     */
    public LevelObject() {
        this.setBackgroundColor(Color.RED);
        this.setForegroundColor(Color.BLACK);
        //default constructor of some level object.
        String basicPath = "https://p1.hiclipart.com/preview/180/349/86/super-mario-icons-super-mario-star-illustration.jpg";
        Image basic = new Image(basicPath, 10, 10, true, true);
        this.setImage(basic); //Sets the image of the collectible to a default star.
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
     * Gets the title of an object.
     *
     * @return the title of the object
     */
    public String getTitle() {
        return title;
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
        // TODO: Check for collisions

        this.positionX = x;
        this.positionY = y;
    }

    /**
     * Draws the object.
     */
    public abstract void draw(GraphicsContext gc);
}
