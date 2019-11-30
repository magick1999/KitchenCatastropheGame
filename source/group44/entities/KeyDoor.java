package group44.entities;

import group44.game.Level;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a door for which the player needs a certain coloured key to be
 * able to open the door.
 * 
 * @author Amy Mason, Tomas Svejnoha
 * @version 1.0
 * 
 */
public class KeyDoor extends Door {
	private Key.KeyType unlockingKey;

	/**
	 * This creates a new instance of {@link KeyDoor} and associates it with an
	 * unlocking {@link KeyType}.
	 * 
	 * @param level        - The {@link Level} where the {@link KeyDoor} is located
	 * @param title        - Title of the {@link Door}
	 * @param positionX    - Position X in the game
	 * @param positionY    - Position Y in the game
	 * @param imagePath    - Path to the Image representing the {@link Door} in the
	 *                     game
	 * @param unlockingKey - key used to unlock the door
	 */
	public KeyDoor(Level level, String title, int positionX, int positionY, String imagePath,
			Key.KeyType unlockingKey) {
		super(level, title, positionX, positionY, imagePath);
		this.unlockingKey = unlockingKey;
	}

	/**
	 * Opens the door if the right Key is used.
	 * 
	 * @param item - the Key to use.
	 * @return true if the door was opened; otherwise false.
	 */
	@Override
	public boolean open(CollectableItem item) {
		if (this.isOpen() == false && item instanceof Key
				&& ((Key) item).getKeyCode() == this.unlockingKey.getKeyCode()) {
			this.setIsOpen(true);
		}
		return this.isOpen();
	}
}
