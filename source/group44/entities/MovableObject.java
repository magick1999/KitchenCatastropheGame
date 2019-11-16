package group44.entities;

/**
 * Abstract classes from which inherits all objects able to move.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 */
public abstract class MovableObject extends LevelObject {
    private int velocityX;
    private int velocityY;

    /**
     * Returns the velocity of the object on the X axis.
     * 
     * @return velocity X
     */
    public int getVelocityX() {
        return this.velocityX;
    }

    /**
     * Sets the velocity on the X axis.
     * 
     * @param vx - velocity X
     */
    public void setVelocityX(int vx) {
        this.velocityX = vx;
    }

    /**
     * Returns the velocity of the object on the Y axis.
     * 
     * @return velocity Y
     */
    public int getVelocityY() {
        return this.velocityY;
    }

    /**
     * Sets the velocity on the Y axis.
     * 
     * @param vy - velocity Y
     */
    public void setVelocityY(int vy) {
        this.velocityY = vy;
    }

    /**
     * Moves the object.
     */
    public abstract void move();
}
