package group44;

import javafx.scene.paint.Color;

public abstract class Cell {
    private int xPos;
    private int yPos;
    private Color color;

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
    }
}
