package group44.entities.cells;

import group44.Constants;
import group44.entities.collectableItems.CollectableItem;
import group44.entities.collectableItems.Key;
import group44.entities.collectableItems.TokenAccumulator;
import group44.entities.movableObjects.MovableObject;
import group44.game.CollisionCheckResult;
import group44.game.CollisionCheckResult.CollisionCheckResultType;
import group44.game.Level;

/**
 * This class is for the token doors where the player needs to have a certain
 * amount of tokens to be able to open it.
 *
 * @author Amy Mason, Tomas Svejnoha
 * @version 1.0
 */
public class TokenDoor extends Door {
    /** Number of Tokens needed to open the door. */
    private int tokensNeeded;

    /**
     * This creates a new {@link KeyDoor} and associates a unlocking
     * {@link Key.KeyType} with it.
     *
     * @param level
     *            - The {@link Level} where the {@link KeyDoor} is located.
     * @param title
     *            - Title of the {@link Door}.
     * @param positionX
     *            - Position X in the game.
     * @param positionY
     *            - Position Y in the game.
     * @param lockedImagePath
     *            - Path to the Image representing locked door in the game.
     * @param unlockedImagePath
     *            - Path to the Image representing unlocked door in the game.
     * @param tokensNeeded
     *            - number of tokens needed to open the door.
     */
    public TokenDoor(Level level, String title, int positionX, int positionY,
            String lockedImagePath, String unlockedImagePath,
            int tokensNeeded) {
        super(level, title, positionX, positionY, lockedImagePath,
                unlockedImagePath);

        this.tokensNeeded = tokensNeeded;
    }

    /**
     * Opens the door if a {@link TokenAccumulator} with the right amount of
     * tokens were used.
     *
     * @param item
     *            - the TokenAccumulator to use.
     * @return true if the door was opened; otherwise false.
     */
    @Override
    public boolean open(CollectableItem item) {
        if (this.isOpen() == false && item instanceof TokenAccumulator
                && ((TokenAccumulator) item)
                        .getTokensCount() >= this.tokensNeeded) {
            try {
                ((TokenAccumulator) item).use(this.tokensNeeded);
                this.open();
            } catch (Exception e) {
                return false;
            }
        }

        return this.isOpen();
    }

    /**
     * Places {@link MovableObject} on the {@link TokenDoor}. If then door is
     * locked, {@link CollisionCheckResult} with the door as a colliding object
     * is returned. Otherwise, returns a successful
     * {@link CollisionCheckResult}.
     *
     * @param object
     *            - {@link MovableObject} that steps on the cell.
     *
     * @return the {@link CollisionCheckResult} with information about the
     *         action result.
     */
    @Override
    public CollisionCheckResult stepOn(MovableObject object) {
        if (this.isOpen() == false) {
            return new CollisionCheckResult(
                    CollisionCheckResultType.NotEnoughTokens, this);
        }
        this.setMovableObject(object);
        return new CollisionCheckResult(CollisionCheckResultType.Successful);
    }

    /**
     * Returns a string representation of the {@link TokenDoor}.
     *
     * @return a string representation of the {@link TokenDoor}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(Constants.TYPE_TOKEN_DOOR);
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getTitle());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getPositionX());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getPositionY());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getImagePath());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.getUnlockedImagePath());
        builder.append(Constants.LEVEL_OBJECT_DELIMITER);
        builder.append(this.tokensNeeded);

        if (this.getMovableObject() != null) {
            builder.append(",");
            builder.append(this.getMovableObject().toString());
        }

        return builder.toString();
    }
}
