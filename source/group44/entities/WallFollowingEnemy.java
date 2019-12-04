package group44.entities;

import group44.game.Level;

/**
 * Represents a wall following enemy in the game.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class WallFollowingEnemy extends Enemy {

	/**
	 * Creates a new instance of {@link WallFollowingEnemy}.
	 *
	 * @param level
	 *            - The {@link Level} where the {@link WallFollowingEnemy} is
	 *            located.
	 * @param name
	 *            - Name of the {@link WallFollowingEnemy}.
	 * @param positionX
	 *            - Position X of the {@link WallFollowingEnemy}.
	 * @param positionY
	 *            - Position Y of the {@link WallFollowingEnemy}.
	 * @param imagePath
	 *            - Path to the Image representing the
	 *            {@link WallFollowingEnemy} on the screen.
	 */
	public WallFollowingEnemy(Level level, String name, int positionX, int positionY, String imagePath) {
		super(level, name, positionX, positionY, 0, 0, imagePath);
	}

	/**
	 * Method invoked after the {@link WallFollowingEnemy} collided with another
	 * {@link MovableObject}.
	 *
	 * @param object
	 *            - the colliding {@link MovableObject}
	 */
	@Override
	protected void onCollided(MovableObject object) {
		if (object instanceof Player) {
			object.die(this);
		} else if (object instanceof Enemy) {
			this.turnAround();
		}
	}

	/**
	 * Computes the velocity of {@link WallFollowingEnemy}.
	 */
	@Override
	protected void computeVelocity() {
		// NOT moving, try to set velocity
		if (this.getVelocityX() == 0 && this.getVelocityY() == 0) {
			this.tryToSetVelocity();
			return;
		}

		// Moving horizontally
		if (this.getVelocityY() == 0) {
			// TOP FREE, obstacle TOP LEFT || RIGHT
			if (this.isObstacleTop() == false && (this.isObstacleTopLeft() || this.isObstacleTopRight())) {
				this.setDirectionUp();
			}
			// BOTTOM FREE, obstacle BOTTOM LEFT || RIGHT
			else if (this.isObstacleBottom() == false
					&& (this.isObstacleBottomLeft() || this.isObstacleBottomRight())) {
				this.setDirectionDown();
			}
			// else
			else {
				// moving LEFT, obstacle LEFT, right FREE
				if (this.getVelocityX() == -1 && this.isObstacleLeft() && this.isObstacleRight() == false) {
					this.setDirectionRight();
				}
				// moving RIGHT, obstacle RIGHT, left FREE
				else if (this.getVelocityX() == 1 && this.isObstacleLeft() == false && this.isObstacleRight()) {
					this.setDirectionLeft();
				}
				// obstacle LEFT, obstacle RIGHT
				else if (this.isObstacleLeft() && this.isObstacleRight()) {
					this.doNotMove();
				}
			}
		}

		// Moving vertically
		else {
			// LEFT FREE, obstacle TOP LEFT || BOTTOM LEFT
			if (this.isObstacleLeft() == false && (this.isObstacleTopLeft() || this.isObstacleBottomLeft())) {
				this.setDirectionLeft();
			}
			// RIGHT FREE, obstacle TOP RIGHT || BOTTOM RIGHT
			else if (this.isObstacleRight() == false && (this.isObstacleTopRight() || this.isObstacleBottomRight())) {
				this.setDirectionRight();
			}
			// else
			else {
				// moving UP, obstacle UP, bottom FREE
				if (this.getVelocityY() == -1 && this.isObstacleTop() && this.isObstacleBottom() == false) {
					this.setDirectionDown();
				}
				// moving DOWN, obstacle DOWN, top FREE
				else if (this.getVelocityY() == 1 && this.isObstacleBottom() && this.isObstacleTop() == false) {
					this.setDirectionUp();
				}
				// obstacle UP, obstacle DOWN
				else if (this.isObstacleTop() && this.isObstacleBottom()) {
					this.doNotMove();
				}
			}
		}
	}

	/**
	 * Tries to set the velocity if the {@link WallFollowingEnemy} is not
	 * moving.
	 */
	private void tryToSetVelocity() {
		if (this.isObstacleTop()) {
			if (this.isObstacleRight()) {
				if (this.isObstacleBottom()) {
					if (this.isObstacleLeft() == false) {
						this.setDirectionLeft();
					}
				} else {
					this.setDirectionDown();
				}
			} else {
				this.setDirectionRight();
			}
		} else {
			this.setDirectionUp();
		}
	}

	/**
	 * Sets movement direction to left.
	 */
	private void setDirectionLeft() {
		this.setVelocityX(-1);
		this.setVelocityY(0);
	}

	/**
	 * Sets movement direction to right.
	 */
	private void setDirectionRight() {
		this.setVelocityX(1);
		this.setVelocityY(0);
	}

	/**
	 * Sets movement direction to up.
	 */
	private void setDirectionUp() {
		this.setVelocityX(0);
		this.setVelocityY(-1);
	}

	/**
	 * Sets movement direction to down.
	 */
	private void setDirectionDown() {
		this.setVelocityX(0);
		this.setVelocityY(1);
	}

	/**
	 * Sets velocity to zero.
	 */
	private void doNotMove() {
		this.setVelocityX(0);
		this.setVelocityY(0);
	}

	/**
	 * Indicates whether the {@link WallFollowingEnemy} has an obstacle on the
	 * left side.
	 *
	 * @return true if yes; otherwise false.
	 */
	private boolean isObstacleLeft() {
		LevelObject[][] map = this.getLevel().getGrid();
		LevelObject cell = map[this.getPositionX() - 1][this.getPositionY()];
		return (cell instanceof Ground) == false;
	}

	/**
	 * Indicates whether the {@link WallFollowingEnemy} has an obstacle on the
	 * right side.
	 *
	 * @return true if yes; otherwise false.
	 */
	private boolean isObstacleRight() {
		LevelObject[][] map = this.getLevel().getGrid();
		LevelObject cell = map[this.getPositionX() + 1][this.getPositionY()];
		return (cell instanceof Ground) == false;
	}

	/**
	 * Indicates whether the {@link WallFollowingEnemy} has an obstacle above
	 * it.
	 *
	 * @return true if yes; otherwise false.
	 */
	private boolean isObstacleTop() {
		LevelObject[][] map = this.getLevel().getGrid();
		LevelObject cell = map[this.getPositionX()][this.getPositionY() - 1];
		return (cell instanceof Ground) == false;
	}

	/**
	 * Indicates whether the {@link WallFollowingEnemy} has an obstacle below
	 * it.
	 *
	 * @return true if yes; otherwise false.
	 */
	private boolean isObstacleBottom() {
		LevelObject[][] map = this.getLevel().getGrid();
		LevelObject cell = map[this.getPositionX()][this.getPositionY() + 1];
		return (cell instanceof Ground) == false;
	}

	/**
	 * Indicates whether the {@link WallFollowingEnemy} has an obstacle on the
	 * top-left side.
	 *
	 * @return true if yes; otherwise false.
	 */
	private boolean isObstacleTopLeft() {
		LevelObject[][] map = this.getLevel().getGrid();
		LevelObject cell = map[this.getPositionX() - 1][this.getPositionY() - 1];
		return (cell instanceof Ground) == false;
	}

	/**
	 * Indicates whether the {@link WallFollowingEnemy} has an obstacle on the
	 * top-right side.
	 *
	 * @return true if yes; otherwise false.
	 */
	private boolean isObstacleTopRight() {
		LevelObject[][] map = this.getLevel().getGrid();
		LevelObject cell = map[this.getPositionX() + 1][this.getPositionY() - 1];
		return (cell instanceof Ground) == false;
	}

	/**
	 * Indicates whether the {@link WallFollowingEnemy} has an obstacle on the
	 * bottom-left side.
	 *
	 * @return true if yes; otherwise false.
	 */
	private boolean isObstacleBottomLeft() {
		LevelObject[][] map = this.getLevel().getGrid();
		LevelObject cell = map[this.getPositionX() - 1][this.getPositionY() + 1];
		return (cell instanceof Ground) == false;
	}

	/**
	 * Indicates whether the {@link WallFollowingEnemy} has an obstacle on the
	 * bottom-right side.
	 *
	 * @return true if yes; otherwise false.
	 */
	private boolean isObstacleBottomRight() {
		LevelObject[][] map = this.getLevel().getGrid();
		LevelObject cell = map[this.getPositionX() + 1][this.getPositionY() + 1];
		return (cell instanceof Ground) == false;
	}
}
