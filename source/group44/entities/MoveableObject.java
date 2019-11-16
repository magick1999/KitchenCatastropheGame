package group44.entities;

abstract class MoveableObject extends LevelObject {
    private int velocityX;
    private int velocityY;

    public int getVelocityX() {
        return this.velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return this.velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public abstract void move();
}
