package group44.entities.movableObjects;

import group44.entities.LevelObject;
import group44.entities.cells.Cell;
import group44.entities.cells.Door;
import group44.entities.cells.Fire;
import group44.entities.cells.StepableCell;
import group44.entities.cells.Wall;
import group44.entities.cells.Water;
import group44.game.CollisionCheckResult;
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
	 * @param level
	 *            - The {@link Level} where the {@link Enemy} is located.
	 * @param title
	 *            - The name of the {@link Enemy}.
	 * @param positionX
	 *            - Position X of the {@link Enemy}.
	 * @param positionY
	 *            - Position Y of the {@link Enemy}.
	 * @param velocityX
	 *            - Velocity X of the {@link Enemy}.
	 * @param velocityY
	 *            - Velocity Y of the {@link Enemy}.
	 * @param imagePath
	 *            - Path to the Image representing the {@link Enemy} on the
	 *            screen.
	 */
	public Enemy(Level level, String title, int positionX, int positionY, int velocityX, int velocityY,
			String imagePath) {
		super(level, title, positionX, positionY, velocityX, velocityY, imagePath);
	}

	/**
	 * Computes the velocity of {@link Enemy}.
	 */
	protected abstract void computeVelocity();

	/**
	 * Moves the {@link Enemy} in the velocity direction.
	 */
	@Override
	public void move() {
		this.computeVelocity();

		StepableCell currentCell = this.getStepableCellAtMovableObjectPosition(this);
		StepableCell nextCell = this.getNextStepableCellInVelocity(this, this.getVelocityX(), this.getVelocityY());

		// Check if the move can be done; if not, do not move
		if (nextCell != null && !this.isObstacle(this.getPositionX() + this.getVelocityX(), this.getPositionY() + this.getVelocityY())) {
			CollisionCheckResult collisionResult = nextCell.stepOn(this);
			if (collisionResult.isColliding()) {
				// Colliding; stepOn was NOT successful
				this.onCollided(collisionResult);
			} else {
				// Not colliding; stepOn was successful
				currentCell.stepOff();

				// If the movableObject on the nextCell is not equal to this =>
				// Teleporter - position is already set
				if (nextCell.getMovableObject() == this) {
					this.setPosition(nextCell.getPositionX(), nextCell.getPositionY());
				}
			}
		}
	}

	/**
	 * Method executed when some other {@link LevelObject} tries to kill the
	 * {@link Enemy}. The enemy will die if he can't protect himself.
	 *
	 * @param object
	 *            - the {@link LevelObject} trying to kill the {@link Player}.
	 */
	@Override
	public void die(LevelObject object) {
		if (object instanceof Fire) {
			super.die(object);
		} else if (object instanceof Water) {
			super.die(object);
		}
	}

	/**
	 * Turns the {@link Enemy} around.
	 */
	protected void turnAround() {
		this.setVelocityX(this.getVelocityX() * -1);
		this.setVelocityY(this.getVelocityY() * -1);
	}

	/**
	 * Indicates whether on the position X and Y is an obstacle which Enemy
	 * can't step on.
	 *
	 * @param x
	 *            - position X.
	 * @param y
	 *            - position Y.
	 * @return true if there is an obstacle; otherwise false.
	 */
	protected boolean isObstacle(int x, int y) {
		Cell[][] cells = this.getLevel().getGrid();

		if (cells[x][y] instanceof Wall || cells[x][y] instanceof Water || cells[x][y] instanceof Fire
				|| (cells[x][y] instanceof Door && ((Door) cells[x][y]).isOpen() == false)) {
			return true;

		}
		return false;
	}
}