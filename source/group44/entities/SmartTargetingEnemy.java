package group44.entities;

import group44.game.Level;

/**
 * Represents a smart targeting enemy in the game.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class SmartTargetingEnemy extends Enemy {

	public SmartTargetingEnemy(Level level, String name, int positionX, int positionY, String imagePath) {
		super(level, name, positionX, positionY, 0, 0, imagePath);
	}

	/**
	 * Computes velocity for the {@link SmartTargetingEnemy}.
	 */
	@Override
	protected void computeVelocity() {
		// TODO Auto-generated method stub
	}

	/**
	 * Method invoked after the {@link SmartTargetingEnemy} collided with another
	 * {@link MovableObject}.
	 *
	 * @param object - the colliding {@link MovableObject}
	 */
	@Override
	protected void onCollided(MovableObject object) {
		if (object instanceof Player) {
			object.die(this);
		} else if (object instanceof Enemy) {
			this.turnAround();
		}
	}
}
