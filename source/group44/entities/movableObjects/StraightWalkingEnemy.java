package group44.entities.movableObjects;

import group44.Constants;
import group44.entities.cells.Ground;
import group44.entities.cells.StepableCell;
import group44.game.CollisionCheckResult;
import group44.game.Level;

/**
 * Represents a straight walking enemy in the game.
 *
 * @author Tomas Svejnoha.
 * @version 1.0
 */
public class StraightWalkingEnemy extends Enemy {

    /**
     * Creates a new instance of {@link StraightWalkingEnemy}.
     *
     * @param level
     *            The {@link Level} where the {@link StraightWalkingEnemy} is
     *            located.
     * @param name
     *            Title of the {@link StraightWalkingEnemy}.
     * @param positionX
     *            Position X of the {@link StraightWalkingEnemy}.
     * @param positionY
     *            Position Y of the {@link StraightWalkingEnemy}.
     * @param velocityX
     *            Velocity X of the {@link StraightWalkingEnemy}.
     * @param velocityY
     *            Velocity Y of the {@link StraightWalkingEnemy}.
     * @param imagePath
     *            Path to the Image representing the
     *            {@link StraightWalkingEnemy} on the screen.
     */
    public StraightWalkingEnemy(Level level, String name, int positionX,
            int positionY, int velocityX, int velocityY, String imagePath) {
        super(level, name, positionX, positionY, velocityX, velocityY,
                imagePath);
    }

    /**
     * Computes the velocity for the next move of the
     * {@link StraightWalkingEnemy}.
     */
    @Override
    protected void computeVelocity() {
        StepableCell stepableCell = this.getNextStepableCellInVelocity(this,
                this.getVelocityX(), this.getVelocityY());
        if ((stepableCell instanceof Ground) == false) {
            this.turnAround();
        }
    }

    /**
     * Interacts with the colliding object.
     *
     * @param result
     *            the {@link CollisionCheckResult} with the collision status.
     */
    @Override
    protected void onCollided(CollisionCheckResult result) {
        if (result.getCollidingObject() instanceof Player) {
            ((Player) result.getCollidingObject()).die(this);
        } else {
            this.turnAround();
        }
    }

    /**
     * Returns the string representation of a Straight Walking Enemy.
     *
     * @return the string representation of a Straight Walking Enemy.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.TYPE_STRAIGHT_WALKING_ENEMY);
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);

        builder.append(this.getTitle());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getPositionX());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getPositionY());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getVelocityX());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getVelocityY());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getImagePath());

        return builder.toString();
    }
}
