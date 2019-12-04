package group44.entities.MovableObjects;

import group44.entities.Cells.Ground;
import group44.entities.Cells.StepableCell;
import group44.game.CollisionCheckResult;
import group44.game.Level;

/**
 * Represents a straight walking enemy in the game.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class StraightWalkingEnemy extends Enemy {

    /**
     * Creates a new instance of {@link StraightWalkingEnemy}.
     *
     * @param level     - The {@link Level} where the {@link StraightWalkingEnemy} is located.
     * @param name      - Title of the {@link StraightWalkingEnemy}.
     * @param positionX - Position X of the {@link StraightWalkingEnemy}.
     * @param positionY - Position Y of the {@link StraightWalkingEnemy}.
     * @param velocityX - Velocity X of the {@link StraightWalkingEnemy}.
     * @param velocityY - Velocity Y of the {@link StraightWalkingEnemy}.
     * @param imagePath - Path to the Image representing the {@link StraightWalkingEnemy} on the screen.
     */
    public StraightWalkingEnemy(Level level, String name, int positionX, int positionY, int velocityX, int velocityY,
            String imagePath) {
        super(level, name, positionX, positionY, velocityX, velocityY, imagePath);
    }

    /**
     * Computes the velocity for the next move of the {@link StraightWalkingEnemy}.
     */
    @Override
    protected void computeVelocity() {
        StepableCell stepableCell = this.getNextStepableCellInVelocity(this, this.getVelocityX(), this.getVelocityY());
        if ((stepableCell instanceof Ground) == false) {
            this.turnAround();
        }
    }

    /**
     * Interacts with the colliding object.
     *
     * @param result - the {@link CollisionCheckResult} with the collision status.
     */
	@Override
	protected void onCollided(CollisionCheckResult result) {
		if (result.getCollidingObject() instanceof Player) {
			((Player) result.getCollidingObject()).die(this);
        } else {
            this.turnAround();
        }
	}
}