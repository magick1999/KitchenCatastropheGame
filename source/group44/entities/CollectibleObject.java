package group44.entities;

public abstract class CollectibleObject extends LevelObject{
    private int xPos;
    private int yPos;
    private boolean collected; //if obj iscollected == true -> add to inventory.

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    //many collectible objects will be made and handled at any given time thus must track which are still to be shown.
    public boolean isCollected() {
        return collected;
    }
}
