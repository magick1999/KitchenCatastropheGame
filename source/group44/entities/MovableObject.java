package group44.entities;

import group44.game.Level;

/**
 * Abstract classes from which inherits all objects able to move.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class MovableObject extends LevelObject {
    private int velocityX;
    private int velocityY;
    private Level level;

    /**
     * Creates a new {@link MovableObject}.
     * 
     * @param level     - The {@link Level} where the object is located
     * @param title     - Title of the object
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param velocityX - Velocity X of the instance
     * @param velocityY - Velocity Y of the instance
     * @param imagePath - Image path of the instance
     */
    public MovableObject(Level level, String title, int positionX, int positionY, int velocityX, int velocityY,
            String imagePath) {
        super(title, positionX, positionY, imagePath);

        this.level = level;
        this.setVelocityX(velocityX);
        this.setVelocityY(velocityY);
    }

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
     * Returns the {@link Level} in which the {@link MovableObject} is located.
     * 
     * @return the {@link Level}
     */
    protected Level getLevel() {
        return this.level;
    }

    /**
     * Moves the object.
     */
    public abstract void move();
}
