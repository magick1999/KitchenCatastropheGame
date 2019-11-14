package group44;

public abstract class Cell {
    private int xPos;
    private int yPos;
    private int[] color = new int[3]; //RGB stored in array

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

    public int[] getColor() {
        return color;
    }

    public void setColor(int red, int green, int blue) {
        this.color[0] = red;
        this.color[1] = green;
        this.color[2] = blue;
    }
}
