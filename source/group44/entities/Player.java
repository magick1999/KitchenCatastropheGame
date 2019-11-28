package group44.entities;

import java.util.ArrayList;

import group44.game.CollisionCheckResult;
import group44.game.Level;
import group44.game.interfaces.IKeyReactive;
import javafx.scene.input.KeyEvent;

/**
 * Represents a player in the game.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Player extends MovableObject implements IKeyReactive {
    private ArrayList<CollectableItem> itinerary;

    /**
     * Creates a new instance of {@link Player} at specific position in a specific
     * {@link Level}.
     * 
     * @param level     - The {@link Level} where the {@link Player} is located.
     * @param name      - The name of the {@link Player}.
     * @param positionX - Position X of the {@link Player}.
     * @param positionY - Position Y of the {@link Player}.
     * @param velocityX - Velocity X of the {@link Player}.
     * @param velocityY - Velocity Y of the {@link Player}.
     * @param imagePath - Path to the Image representing the {@link Player} on the
     *                  screen.
     */
    public Player(Level level, String name, int positionX, int positionY, int velocityX, int velocityY,
            String imagePath) {
        super(level, name, positionX, positionY, velocityX, velocityY, imagePath);
    }

    /**
     * Moves the {@link Player} in the velocity direction.
     */
    @Override
    public void move() {
        StepableCell currentCell = this.getStepableCellAtMovableObjectPosition(this);
        StepableCell nextCell = this.getNextStepableCellInVelocity(this, this.getVelocityX(), this.getVelocityY());

        // Check if the move can be done; if not, do not move
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
     * Method invoked after the {@link Player} collided with another
     * {@link MovableObject}.
     * 
     * @param object - the colliding {@link MovableObject}
     */
    @Override
    protected void onCollided(MovableObject object) {
        System.out.println(
                this.getTitle() + " collided with " + object.getTitle() + " and " + this.getTitle() + " did nothing.");
    }

    /**
     * Method invoked after the {@link Player} stepped on {@link StepableCell}.
     * 
     * @param cell - {@link StepableCell} the {@link Player} stepped on
     */
    @Override
    protected void onCellStepped(StepableCell cell) {
        if (cell instanceof Ground) {
            Ground ground = ((Ground) cell);
            if (ground.hasCollectableItem()) {
                this.itinerary.add(ground.collect());
            }
        }
    }

    @Override
    public void keyDown(KeyEvent event) {
        switch (event.getCode()) {
        case LEFT:
            this.setVelocityX(-1);
            this.setVelocityY(0);
            break;
        case RIGHT:
            this.setVelocityX(1);
            this.setVelocityY(0);
            break;
        case UP:
            this.setVelocityX(0);
            this.setVelocityY(-1);
            break;
        case DOWN:
            this.setVelocityX(0);
            this.setVelocityY(1);
            break;
    }
}