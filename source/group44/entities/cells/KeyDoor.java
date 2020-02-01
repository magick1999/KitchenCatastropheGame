package group44.entities.cells;

import group44.Constants;
import group44.entities.collectableItems.CollectableItem;
import group44.entities.collectableItems.Key;
import group44.entities.collectableItems.Key.KeyType;
import group44.entities.movableObjects.MovableObject;
import group44.game.CollisionCheckResult;
import group44.game.CollisionCheckResult.CollisionCheckResultType;
import group44.game.Level;

/**
 * Represents a door for which the player needs a certain coloured key to be
 * able to open the door.
 *
 * @author Amy Mason, Tomas Svejnoha
 * @version 1.0
 */
public class KeyDoor extends Door {
    /** Unlocking key for the door. */
    private Key.KeyType unlockingKey;

    /**
     * This creates a new instance of {@link KeyDoor} and associates it with an
     * unlocking {@link KeyType}.
     *
     * @param level
     *            The {@link Level} where the {@link KeyDoor} is located.
     * @param title
     *            Title of the {@link Door}.
     * @param positionX
     *            Position X in the game.
     * @param positionY
     *            Position Y in the game.
     * @param lockedImagePath
     *            Path to the Image representing locked door in the game.
     * @param unlockedImagePath
     *            Path to the Image representing unlocked door in the game.
     * @param unlockingKey
     *            Key used to unlock the door.
     */
    public KeyDoor(Level level, String title, int positionX, int positionY,
            String lockedImagePath, String unlockedImagePath,
            Key.KeyType unlockingKey) {
        super(level, title, positionX, positionY, lockedImagePath,
                unlockedImagePath);

        this.unlockingKey = unlockingKey;
    }

    /**
     * Returns the unlocking {@link KeyType} for the {@link KeyDoor}.
     *
     * @return the unlocking key type.
     */
    public KeyType getUnlockingKeyType() {
        return this.unlockingKey;
    }

    /**
     * Opens the door if the right Key is used.
     *
     * @param item
     *            the Key to use.
     *
     * @return true if the door was opened; otherwise false.
     */
    @Override
    public boolean open(CollectableItem item) {
        if (this.isOpen() == false && item instanceof Key && ((Key) item)
                .getKeyCode() == this.unlockingKey.getKeyCode()) {
            this.open();
        }
        return this.isOpen();
    }

    /**
     * Places {@link MovableObject} on the {@link KeyDoor}. If then door is
     * locked, {@link CollisionCheckResult} with the door as a colliding object
     * is returned. Otherwise, returns a successful
     * {@link CollisionCheckResult}.
     *
     * @param object
     *            {@link MovableObject} that steps on the cell.
     *
     * @return a result of the step action in the {@link CollisionCheckResult}.
     */
    @Override
    public CollisionCheckResult stepOn(MovableObject object) {
        if (this.isOpen() == false) {
            return new CollisionCheckResult(CollisionCheckResultType.MissingKey,
                    this);
        }
        this.setMovableObject(object);
        return new CollisionCheckResult(CollisionCheckResultType.Successful);
    }

    /**
     * Returns a string representation of the {@link KeyDoor}.
     *
     * @return a string representation of the {@link KeyDoor}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Constants.TYPE_KEY_DOOR);
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
        builder.append(this.getUnlockingKeyType().getKeyCode());

        if (this.getMovableObject() != null) {
            builder.append(",");
            builder.append(this.getMovableObject().toString());
        }

        return builder.toString();
    }
}
