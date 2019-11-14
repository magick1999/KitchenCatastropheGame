package group44.entities;

public abstract class Cell extends LevelObject {
    private int positionX;
    private int positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setYPos(int yPos) {
        this.positionY = yPos;
    }

}
