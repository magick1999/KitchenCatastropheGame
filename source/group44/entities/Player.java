package group44.entities;

import java.util.ArrayList;

import group44.game.CollisionCheckResult;
import group44.game.Level;
import javafx.scene.input.KeyEvent;

/**
 * Represents a player in the game.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Player extends MovableObject {
    private ArrayList<CollectableItem> inventory;

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

        this.inventory = new ArrayList<>();
        this.inventory.add(new TokenAccumulator());
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
     * If the {@link StepableCell} is an instance of {@link Ground}, the
     * {@link Player} will collect any {@link CollectableItem} on the cell.
     *
     * @param cell - {@link StepableCell} the {@link Player} stepped on.
     */
    @Override
    protected void onCellStepped(StepableCell cell) {
        if (cell instanceof Ground) {
            Ground ground = ((Ground) cell);
            if (ground.hasCollectableItem()) {
                CollectableItem item = ground.collect(); // Collect the CollectableItem if the is any
                if (item instanceof Token) {
                    this.getTokenAccumulator().addToken((Token) item); // If token, add to TokenAccumulator
                } else {
                    this.inventory.add(item); // else, add to inventory
                }
            }
        }
    }

    /**
     * Method executed when some other {@link LevelObject} tries to kill the
     * {@link Player}. The player will die if he can't protect himself.
     *
     * @param object - the {@link LevelObject} trying to kill the {@link Player}.
     */
    @Override
    public void die(LevelObject object) {
        if (object instanceof Enemy) {
            super.die(object);
        } else if (object instanceof Fire && this.hasFireBoots() == false) {
            super.die(object);
        } else if (object instanceof Water && this.hasFlippers() == false) {
            super.die(object);
        }
    }

    /**
     * Checks if {@link Player} has {@link FireBoots} in inventory.
     *
     * @return true if the playes has them; false otherwise.
     */
    private boolean hasFireBoots() {
        for (CollectableItem item : this.inventory) {
            if (item instanceof FireBoots) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if {@link Player} has {@link Flippers} in inventory.
     *
     * @return true if the playes has them; false otherwise.
     */
    private boolean hasFlippers() {
        for (CollectableItem item : this.inventory) {
            if (item instanceof Flippers) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the velocity of the {@link Player} based on the arrow pressed.
     *
     * @param event - the {@link KeyEvent}.
     */
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

    /**
     * Returns a {@link TokenAccumulator} from the inventory the {@link Player} has.
     * The method creates one and adds it to the inventory if {@link Player} does
     * not have it.
     *
     * @return the {@link TokenAccumulator}.
     */
    private TokenAccumulator getTokenAccumulator() {
        for (CollectableItem item : this.inventory) {
            if (item instanceof TokenAccumulator) {
                return (TokenAccumulator) item;
            }
        }

        TokenAccumulator accumulator = new TokenAccumulator();
        this.inventory.add(accumulator);
        return accumulator;
    }
}