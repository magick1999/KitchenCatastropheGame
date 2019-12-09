package group44.entities.movableObjects;

import group44.Constants;
import group44.game.CollisionCheckResult;
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
     * @param level
     *            the {@link Level} where the {@link DumbTargetingEnemy} is
     *            located.
     * @param name
     *            name of the {@link DumbTargetingEnemy}.
     * @param positionX
     *            position X in the game.
     * @param positionY
     *            position Y in the game.
     * @param imagePath
     *            path to the Image representing the {@link DumbTargetingEnemy}.
     */
    public DumbTargetingEnemy(Level level, String name, int positionX,
            int positionY, String imagePath) {
        super(level, name, positionX, positionY, 0, 0, imagePath);
    }

    /**
     * Computes a new velocity for the {@link DumbTargetingEnemy}.
     */
    @Override
    protected void computeVelocity() {
        this.setVelocityX(0);
        this.setVelocityY(0);

        Position playerPosition = this.getLevel().getPlayerPosition();

        int differenceX = Math.abs(this.getPositionX() - playerPosition.getX());
        int differenceY = Math.abs(this.getPositionY() - playerPosition.getY());

        if (differenceX == differenceY
                && this.getPositionX() > playerPosition.getX()
                && this.getPositionY() > playerPosition.getY()) {
            if (this.isObstacle(this.getPositionX() - 1,
                    this.getPositionY()) == false) {
                this.setVelocityX(-1);
            } else if (this.isObstacle(this.getPositionX(),
                    this.getPositionY() - 1) == false) {
                this.setVelocityY(-1);
            }
        } else if (differenceX == differenceY
                && this.getPositionX() > playerPosition.getX()
                && this.getPositionY() < playerPosition.getY()) {
            if (this.isObstacle(this.getPositionX() - 1,
                    this.getPositionY()) == false) {
                this.setVelocityX(-1);
            } else if (this.isObstacle(this.getPositionX(),
                    this.getPositionY() + 1) == false) {
                this.setVelocityY(1);
            }
        } else if (differenceX == differenceY
                && this.getPositionX() < playerPosition.getX()
                && this.getPositionY() > playerPosition.getY()) {
            if (this.isObstacle(this.getPositionX() + 1,
                    this.getPositionY()) == false) {
                this.setVelocityX(1);
            } else if (this.isObstacle(this.getPositionX(),
                    this.getPositionY() - 1) == false) {
                this.setVelocityY(-1);
            }
        } else if (differenceX == differenceY
                && this.getPositionX() < playerPosition.getX()
                && this.getPositionY() < playerPosition.getY()) {
            if (this.isObstacle(this.getPositionX() + 1,
                    this.getPositionY()) == false) {
                this.setVelocityX(1);
            } else if (this.isObstacle(this.getPositionX(),
                    this.getPositionY() + 1) == false) {
                this.setVelocityY(1);
            }

        } else if (differenceY == 0 || differenceX > differenceY) {
            // On the same horizontal
            if (playerPosition.getX() < this.getPositionX()) {
                this.setVelocityX(-1);
            } else {
                this.setVelocityX(1);
            }
        } else if (differenceX == 0 || differenceY > differenceX) {
            if (playerPosition.getY() < this.getPositionY()) {
                this.setVelocityY(-1);
            } else {
                this.setVelocityY(1);
            }
        }
    }

    /**
     * Interacts with the colliding {@link MovableObject}.
     *
     * @param result
     *            the {@link CollisionCheckResult} with the collision status.
     */
    @Override
    protected void onCollided(CollisionCheckResult result) {
        if (result.getCollidingObject() instanceof Player) {
            ((Player) result.getCollidingObject()).die(this);
        }
    }

    /**
     * Returns a string representation of a Dumb Targeting Enemy.
     *
     * @return the string repsesentation of a Dumb Targeting Enemy.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(Constants.TYPE_DUMB_TARGETING_ENEMY);
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);

        builder.append(this.getTitle());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);

        builder.append(this.getPositionX());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);

        builder.append(this.getPositionY());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);

        builder.append(this.getImagePath());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);

        return builder.toString();
    }
}
