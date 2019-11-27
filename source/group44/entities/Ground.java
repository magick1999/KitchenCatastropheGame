package group44.entities;

import group44.game.Level;

/**
 * Represents a ground in the game.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Ground extends StepableCell {
    private CollectableItem collectableItem;

    /**
     * Creates a new instance of {@link Ground} at a specific location in the
     * {@link Level}
     * 
     * @param level     - The {@link Level} where the {@link Ground} is located
     * @param positionX - Position X of the {@link Ground} in the {@link Level}
     * @param positionY - Position X of the {@link Ground} in the {@link Level}
     * @param imagePath - Path to the Image representing {@link Ground} in the game
     */
    public Ground(Level level, int positionX, int positionY, String imagePath) {
        super(level, "Ground", positionX, positionY, imagePath);
    }

    /**
     * Creates a new instance of {@link Ground} at a specific location in the
     * {@link Level}.
     * 
     * @param level           - The {@link Level} where the {@link Goal} is located
     * @param positionX       - Position X of the {@link Ground} in the
     *                        {@link Level}
     * @param positionY       - Position Y of the {@link Ground} in the
     *                        {@link Level}
     * @param imagePath       - Path to the Image representing the {@link Ground} in
     *                        the game
     * @param collectableItem - {@link CollectableItem} placed on the {@link Ground}
     */
    public Ground(Level level, int positionX, int positionY, String imagePath, CollectableItem collectableItem) {
        this(level, positionX, positionY, imagePath);
        this.collectableItem = collectableItem;
    }

    /**
     * Returns {@link CollectableItem} placed on the {@link Ground}.
     * 
     * @return {@link CollectableItem} if present; otherwise null
     */
    public CollectableItem collect() {
        CollectableItem item = this.collectableItem;
        this.collectableItem = null;
        return item;
    }

    /**
     * Indicates whether there is some {@link CollectableItem} on the {@link Ground}
     * or not.
     * 
     * @return true if there is {@link CollectableItem}; false otherwise
     */
    public Boolean hasCollectableItem() {
        return this.collectableItem != null;
    }

    /**
     * Interacts with {@link MovableObject} that stepped on the {@link Ground}.
     * 
     * @param object - The {@link MovableObject} that stepped on {@link Ground}.
     */
    @Override
    protected void onStepped(MovableObject object) {
        System.out.println(object.getTitle() + " stepped on the " + this.getTitle());
    }
}