package group44.entities;

import java.util.ArrayList;
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
	 * @param level        - The {@link Level} where the {@link Door} is located
	 * @param unlockingKey - key used to unlock the door
	 * @param title        - Title of the {@link Door}
	 * @param positionX    - Position X in the game
	 * @param positionY    - Position Y in the game
	 * @param imagePath    - Path to the Image representing the {@link Door} in the
	 *                     game
	 */
	public KeyDoor(Level level, Key.KeyType unlockingKey, String title, int positionX, int positionY, int size,
			String imagePath) {
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
		if (item instanceof Key && ((Key) item).getKeyCode() == this.unlockingKey.getKeyCode()) {
			this.setIsOpen(true);
		}
		return this.isOpen();
	}

	/**
	 * Interacts with {@link MovableObject} that stepped on the cell.
	 * 
	 * @param object - The {@link MovableObject} that stepped on cell.
	 */
	@Override
	protected void onStepped(MovableObject object) {
		System.out.println("KeyDoor.onStepped(object).");
	}
}
