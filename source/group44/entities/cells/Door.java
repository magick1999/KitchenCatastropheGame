package group44.entities.cells;

import group44.entities.collectableItems.CollectableItem;
import group44.entities.movableObjects.MovableObject;
import group44.game.Level;
import javafx.scene.image.Image;

/**
 * Super class for all Door classes.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public abstract class Door extends StepableCell {
	private boolean isOpen;
	private String unlockedImagePath;
	private Image unlockedImage;

	/**
	 * Creates a new {@link Door}.
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
	 */
	public Door(Level level, String title, int positionX, int positionY, String lockedImagePath,
			String unlockedImagePath) {
		super(level, title, positionX, positionY, lockedImagePath);

		this.unlockedImagePath = unlockedImagePath;
		this.unlockedImage = new Image(unlockedImagePath, true);
	}

	/**
	 * Returns a path to the image representing the unlocked door in the game.
	 *
	 * @return a path to the image representing the unlocked door in the game.
	 */
	protected String getUnlockedImagePath() {
		return this.unlockedImagePath;
	}

	/**
	 * Opens the door.
	 *
	 * @param item
	 *            - The opening {@link CollectableItem}.
	 * @return true if the door was opened; otherwise false.
	 */
	public abstract boolean open(CollectableItem item);

	/**
	 * Returns open state of the door.
	 *
	 * @return true if the door is open, otherwise false.
	 */
	public boolean isOpen() {
		return this.isOpen;
	}

	/**
	 * Changes the state of the door to open.
	 */
	protected void open() {
		this.isOpen = true;
		this.setImage(this.unlockedImage);
	}

	/**
	 * Interacts with {@link MovableObject} that stepped on the {@link Door}.
	 *
	 * @param object
	 *            - The {@link MovableObject} that stepped on cell.
	 */
	@Override
	protected void onStepped(MovableObject object) {

	}
}