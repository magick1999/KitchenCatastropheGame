package group44.entities.MovableObjects;

import group44.game.Level;
import group44.game.Position;

/**
 * Represents an {@link Enemy} which is trying to minimise the distance to
 * {@link Player}.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class DumbTargetingEnemy extends Enemy {

    /**
     * Creates a new instance of {@link DumbTargetingEnemy}.
     * 
     * @param level     - the {@link Level} where the {@link DumbTargetingEnemy} is
     *                  located
     * @param name      - name of the {@link DumbTargetingEnemy}
     * @param positionX - position X in the game
     * @param positionY - position Y in the game
     * @param imagePath - path to the Image representing the
     *                  {@link DumbTargetingEnemy}
     */
    public DumbTargetingEnemy(Level level, String name, int positionX, int positionY, String imagePath) {
        super(level, name, positionX, positionY, 0, 0, imagePath);
    }

    /**
     * Computes a new velocity for the {@link DumbTargetingEnemy}.
     */
    @Override
    protected void computeVelocity() {
        Position playerPosition = this.getLevel().getPlayerPosition();

        if (playerPosition.getX() < this.getPositionX()) {
            this.setVelocityX(-1);
        } else if (playerPosition.getX() == this.getPositionX()) {
            this.setVelocityX(0);
        } else {
            this.setVelocityX(1);
        }

        if (playerPosition.getY() < this.getPositionY()) {
            this.setVelocityY(-1);
        } else if (playerPosition.getY() == this.getPositionY()) {
            this.setVelocityY(0);
        } else {
            this.setVelocityY(1);
        }
    }

    /**
     * Interacts with the colliding {@link MovableObject}.
     * 
     * @param object - the {@link MovableObject} the {@link Enemy} collided with.
     */
    @Override
    protected void onCollided(MovableObject object) {
        if (object instanceof Player) {
            object.die(this);
        }
    }
}