package group44.entities.CollectableItems;

import group44.entities.LevelObject;
import group44.game.Level;

/**
 * Abstract class for all collectable items in the game.
 *
 * @author Tomas Svejnoha, Amy Mason
 * @version 1.0
 */
public abstract class CollectableItem extends LevelObject {

    /**
     * Creates a new {@link CollectableItem}.
     *
     * @param level     - The {@link Level} where the {@link CollectableItem} is
     *                  located.
     * @param title     - Title of the {@link CollectableItem}.
     * @param positionX - Position X in the game.
     * @param positionY - Position Y in the game.
     */
    public CollectableItem(Level level, String title, int positionX, int positionY) {
        super(level, title, positionX, positionY);
    }

    /**
     * Creates a new {@link CollectableItem}.
     *
     * @param level     - The {@link Level} where the {@link CollectableItem} is
     *                  located.
     * @param title     - Title of the {@link CollectableItem}.
     * @param positionX - Position X in the game.
     * @param positionY - Position Y in the game.
     * @param imagePath - Path the the Image representing {@link CollectableItem} in
     *                  the game.
     */
    public CollectableItem(Level level, String title, int positionX, int positionY, String imagePath) {
        super(level, title, positionX, positionY, imagePath);
    }
}
