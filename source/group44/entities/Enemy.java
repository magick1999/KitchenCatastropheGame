package group44.entities;

import group44.game.Level;

/**
 * Abstract class for all enemies.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class Enemy extends MovableObject {

    /**
     * Creates a new Enemy in a {@link Level} at position and velocity.
     * 
     * @param level     - The {@link Level} where the {@link Enemy} is located.
     * @param title     - The name of the {@link Enemy}.
     * @param positionX - Position X of the {@link Enemy}.
     * @param positionY - Position Y of the {@link Enemy}.
     * @param velocityX - Velocity X of the {@link Enemy}.
     * @param velocityY - Velocity Y of the {@link Enemy}.
     * @param imagePath - Path to the Image representing the {@link Enemy} on the
     *                  screen.
     */
    public Enemy(Level level, String title, int positionX, int positionY, int velocityX, int velocityY,
            String imagePath) {
        super(level, title, positionX, positionY, velocityX, velocityY, imagePath);
    }

    /**
     * Computes the velocity of {@link Enemy}.
     */
    protected abstract void computeVelocity();
}