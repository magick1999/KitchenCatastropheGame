package group44.entities;

import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class LevelObject {
    private Color backgroundColor;
    private Color foregroundColor;
    private Image image;
    private int size; // Size 'a' of the "grid" cell.
    private int positionX; // Do we need that?
    private int positionY;
    private Level level; // The object needs to move itself in the array. Queue in the Level maintaining
                         // movements or direct modification of the 2D array?

    public LevelObject() {

    }

    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public Color getForegroundColor() {
        return this.foregroundColor;
    }

    public void setForegroundColor(Color color) {
        this.foregroundColor = color;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public void setPositionX(int x) {
        this.positionX = x;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int y) {
        this.positionY = y;
    }
}
