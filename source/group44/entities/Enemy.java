package group44.entities;

import group44.game.CollisionCheckResult;
import group44.game.Level;

/**
 * Abstract class for all enemies.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class Enemy extends MovableObject {
    private static final String ON_STEPPED_MESSAGE = "%s stepped on %s at position [%d,%d].";

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

    /**
     * Moves the {@link Enemy} in the velocity direction.
     */
    @Override
    public void move() {
        this.computeVelocity();

        StepableCell currentCell = this.getStepableCellAtMovableObjectPosition(this);
        StepableCell nextCell = this.getNextStepableCellInVelocity(this, this.getVelocityX(), this.getVelocityY());

        if (nextCell != null) {
            CollisionCheckResult collisionResult = nextCell.stepOn(this);
            if (collisionResult.getIsColliding() && this.isAlive()) {
                // Colliding; stepOn was NOT successful
                this.onCollided((MovableObject) collisionResult.getCollidingObject());
            } else {
                // Not colliding; stepOn was successful
                currentCell.stepOff();
                this.setPosition(nextCell.getPositionX(), nextCell.getPositionY());
                this.onCellStepped(nextCell);
            }
        }
    }

    /**
     * Method executed when some other {@link LevelObject} tries to kill the
     * {@link Enemy}. The enemy will die if he can't protect himself.
     * 
     * @param object - the {@link LevelObject} trying to kill the {@link Player}.
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
     * Interacts with the {@link StepableCell} on which the {@link Enemy} stepped.
     * 
     * @param cell - Cell on which the {@link Enemy} stepped.
     */
    @Override
    protected void onCellStepped(StepableCell cell) {
        System.out.printf(Enemy.ON_STEPPED_MESSAGE, this.getTitle(), cell.getTitle(), cell.getPositionX(),
                cell.getPositionY());
    }

    /**
     * Turns the {@link Enemy} around.
     */
    protected void turnAround() {
        this.setVelocityX(this.getVelocityX() * -1);
        this.setVelocityY(this.getVelocityY() * -1);
    }
}