package group44.entities.cells;

import group44.entities.LevelObject;
import group44.entities.collectableItems.CollectableItem;
import group44.entities.movableObjects.MovableObject;
import group44.game.Level;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a ground in the game.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Ground extends StepableCell {
	private static final String CELL_NAME = "Ground";

    private CollectableItem collectableItem;

    /**
     * Creates a new instance of {@link Ground} at a specific location in the {@link Level}.
     *
     * @param level     - The {@link Level} where the {@link Ground} is located.
     * @param positionX - Position X of the {@link Ground} in the {@link Level}.
     * @param positionY - Position X of the {@link Ground} in the {@link Level}.
     * @param imagePath - Path to the Image representing {@link Ground} in the game.
     */
    public Ground(Level level, int positionX, int positionY, String imagePath) {
        super(level, CELL_NAME, positionX, positionY, imagePath);
    }

    /**
     * Creates a new instance of {@link Ground} at a specific location in the {@link Level}.
     *
     * @param level           - The {@link Level} where the {@link Goal} is located.
     * @param positionX       - Position X of the {@link Ground} in the {@link Level}.
     * @param positionY       - Position Y of the {@link Ground} in the {@link Level}.
     * @param imagePath       - Path to the Image representing the {@link Ground} in the game.
     * @param collectableItem - {@link CollectableItem} placed on the {@link Ground}.
     */
    public Ground(Level level, int positionX, int positionY, String imagePath, CollectableItem collectableItem) {
        this(level, positionX, positionY, imagePath);
        this.collectableItem = collectableItem;
    }

    /**
     * Creates a new {@link Ground}.
     *
     * @param level       - The {@link Level} where the object is located.
     * @param positionX   - Position X in the game.
     * @param positionY   - Position Y in the game.
     * @param imagePath   - Image path of the instance.
     * @param steppedItem - The {@link MovableObject} on the cell.
     */
    public Ground(Level level, int positionX, int positionY, String imagePath, MovableObject steppedItem) {
        super(level, CELL_NAME, positionX, positionY, imagePath, steppedItem);
    }

    /**
     * Returns {@link CollectableItem} placed on the {@link Ground}.
     *
     * @return {@link CollectableItem} if present; otherwise null.
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
     * @return true if there is {@link CollectableItem}; false otherwise.
     */
    public Boolean hasCollectableItem() {
        return this.collectableItem != null;
    }

    /**
     * Draws the {@link StepableCell} with any {@link MovableObject} or {@link CollectableItem} on it in {@link GraphicsContext}.
     *
     * @param gc     - {@link GraphicsContext} used to draw the object.
     * @param x      - The X coordinate in the {@link GraphicsContext} where to draw
     *               the {@link LevelObject}.
     * @param y      - The Y coordinate in the {@link GraphicsContext} where to draw
     *               the {@link LevelObject}.
     * @param width  - The width of the {@link LevelObject} in the
     *               {@link GraphicsContext}.
     * @param height - The height of the {@link LevelObject} in the
     *               {@link GraphicsContext}.
     */
    @Override
    public void draw(GraphicsContext gc, double x, double y, double width, double height) {
    	super.draw(gc, x, y, width, height);

    	if (this.getMovableObject() == null && this.collectableItem != null) {
    		this.collectableItem.draw(gc, x, y, width, height);
    	}
    }

    /**
     * Interacts with {@link MovableObject} that stepped on the {@link Ground}.
     *
     * @param object - The {@link MovableObject} that stepped on {@link Ground}.
     */
    @Override
    protected void onStepped(MovableObject object) {

    }
}