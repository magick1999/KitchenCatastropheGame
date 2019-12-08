package group44.entities.movableObjects;

import java.util.LinkedList;
import java.util.Queue;

import group44.entities.cells.Cell;
import group44.entities.cells.StepableCell;
import group44.entities.movableObjects.smartTargetingEnemy.CellPathInfo;
import group44.game.CollisionCheckResult;
import group44.game.Level;

/**
 * Represents a smart targeting enemy in the game.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class SmartTargetingEnemy extends Enemy {
	private CellPathInfo[][] cellPathInfos;
	private boolean isInitialised;

	/**
	 * Creates a new instance of {@link SmartTargetingEnemy}.
	 *
	 * @param level
	 *            - level where the {@link SmartTargetingEnemy} is located.
	 * @param title
	 *            - title of the enemy.
	 * @param positionX
	 *            - position X in the game.
	 * @param positionY
	 *            - position Y in the game.
	 * @param imagePath
	 *            - path to the image representing the enemy in the game.
	 */
	public SmartTargetingEnemy(Level level, String title, int positionX, int positionY, String imagePath) {
		super(level, title, positionX, positionY, 0, 0, imagePath);

		this.cellPathInfos = new CellPathInfo[level.getGridWidth()][level.getGridHeight()];
	}

	/**
	 * Initialises the values for the instance.
	 */
	private void initialise() {
		for (int x = 0; x < cellPathInfos.length; x++) {
			for (int y = 0; y < cellPathInfos[0].length; y++) {
				this.cellPathInfos[x][y] = new CellPathInfo(this.getLevel().getGrid()[x][y]);
			}
		}
	}

	/**
	 * Computes the velocity of the next move.
	 */
	@Override
	protected void computeVelocity() {
		if (this.isInitialised == false) {
			this.initialise();
			this.isInitialised = true;
		}

		this.resetCellInfos();

		int targetX = this.getLevel().getPlayer().getPositionX();
		int targetY = this.getLevel().getPlayer().getPositionY();

		// Calculate cost to the target cell
		boolean found = this.calculateCosts(targetX, targetY);

		if (found) {
			// Get next cell to step
			CellPathInfo nextCell = this.nextCellToStep(targetX, targetY);

			// Reset velocity
			this.setVelocityX(0);
			this.setVelocityY(0);

			// Set velocity
			if (nextCell.getX() < this.getPositionX() && nextCell.getY() == this.getPositionY()) {
				// GO LEFT
				this.setVelocityX(-1);
			} else if (nextCell.getX() > this.getPositionX() && nextCell.getY() == this.getPositionY()) {
				// GO RIGHT
				this.setVelocityX(1);
			} else if (nextCell.getX() == this.getPositionX() && nextCell.getY() < this.getPositionY()) {
				// GO UP
				this.setVelocityY(-1);
			} else if (nextCell.getX() == this.getPositionX() && nextCell.getY() > this.getPositionY()) {
				// GO DOWN
				this.setVelocityY(1);
			}
		} else {
			// Choose valid direction for the next move.
			// Not getting closer to the player.
			this.chooseDirection();
		}

	}

	/**
	 * Sets the {@link CellPathInfo}s to the default state.
	 */
	private void resetCellInfos() {
		for (int x = 0; x < cellPathInfos.length; x++) {
			for (int y = 0; y < cellPathInfos[0].length; y++) {
				this.cellPathInfos[x][y].reset();
			}
		}
	}

	/**
	 * Finds the shortest path to the player in the game.
	 *
	 * @param targeX
	 *            - position X of the player.
	 * @param targeY
	 *            - position Y of the player.
	 * @return true there is a path; otherwise false.
	 */
	private boolean calculateCosts(int targetX, int targetY) {
		Queue<CellPathInfo> queue = new LinkedList<>();
		boolean isReached = false;

		CellPathInfo currentCell = this.cellPathInfos[this.getPositionX()][this.getPositionY()];
		currentCell.setCost(0); // JUST FIRST CELL!!! - DANGEROUS METHOD
		queue.add(currentCell);

		while (queue.isEmpty() == false) {
			currentCell = queue.poll();

			isReached = (currentCell.getX() == targetX) && (currentCell.getY() == targetY);

			// LEFT
			if (isReached == false && this.isObstacle(currentCell.getX() - 1, currentCell.getY()) == false) {
				CellPathInfo left = this.cellPathInfos[currentCell.getX() - 1][currentCell.getY()];
				if (left != currentCell.getParent()) {
					if (left.setParent(currentCell)) {
						queue.add(left);
					}
				}
			}
			// RIGHT
			if (isReached == false && this.isObstacle(currentCell.getX() + 1, currentCell.getY()) == false) {

				CellPathInfo right = this.cellPathInfos[currentCell.getX() + 1][currentCell.getY()];
				if (right != currentCell.getParent()) {
					if (right.setParent(currentCell)) {
						queue.add(right);
					}
				}
			}
			// TOP
			if (isReached == false && this.isObstacle(currentCell.getX(), currentCell.getY() - 1) == false) {

				CellPathInfo top = this.cellPathInfos[currentCell.getX()][currentCell.getY() - 1];
				if (top != currentCell.getParent()) {
					if (top.setParent(currentCell)) {
						queue.add(top);
					}
				}
			}
			// BOTTOM
			if (isReached == false && this.isObstacle(currentCell.getX(), currentCell.getY() + 1) == false) {

				CellPathInfo bottom = this.cellPathInfos[currentCell.getX()][currentCell.getY() + 1];
				if (bottom != currentCell.getParent()) {
					if (bottom.setParent(currentCell)) {
						queue.add(bottom);
					}
				}
			}

			if (isReached) {
				queue.clear();
			}
		}

		return isReached;
	}

	/**
	 * Returns the next cell to step by the the {@link SmartTargetingEnemy}.
	 *
	 * @param targetX
	 *            - position X of the player.
	 * @param targetY
	 *            - position Y of the player.
	 * @return the next cell to step.
	 */
	private CellPathInfo nextCellToStep(int targetX, int targetY) {
		CellPathInfo currentCell = this.cellPathInfos[targetX][targetY];

		while (currentCell.getParent().getParent() != null) {
			currentCell = currentCell.getParent();
		}

		return currentCell;
	}

	/**
	 * Chooses valid direction of the next move. The {@link SmartTargetingEnemy}
	 * is not necessarily getting closer to the player.
	 */
	private void chooseDirection() {
		// LEFT
		if (this.isObstacle(this.getPositionX() - 1, this.getPositionY()) == false) {
			this.setVelocityX(-1);
		}
		// RIGHT
		if (this.isObstacle(this.getPositionX() + 1, this.getPositionY()) == false) {
			this.setVelocityX(1);
		}
		// TOP
		if (this.isObstacle(this.getPositionX(), this.getPositionY() - 1) == false) {
			this.setVelocityY(-1);
		}
		// BOTTOM
		if (this.isObstacle(this.getPositionX(), this.getPositionY() + 1) == false) {
			this.setVelocityY(1);
		}
	}

	/**
	 * Method executed when enemy collides with another LevelObject.
	 *
	 * @param result
	 *            - information about the collision.
	 */
	@Override
	protected void onCollided(CollisionCheckResult result) {
		if (result.getCollidingObject() instanceof Player) {
			((Player) result.getCollidingObject()).die(this);
		}
	}
}
