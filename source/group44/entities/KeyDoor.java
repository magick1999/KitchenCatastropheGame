package group44.entities;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * This class is for the key doors where the player needs a certain coloured 
 * key is needed to be able to open the door. 
 * This class also enherits from the superclass Door.
 * @author Amy Mason
 * @version 1.0
 * 
 */
public class KeyDoor extends Door {
	private Key.KeyType unlockingKey;

	/**
	 * This creates a new instance of {@link KeyDoor} and associates it with 
	 * an unlocking {@link KeyType}.
	 * @param unlockingKey - key used to unlock the door
	 * @param title - title of the object
	 * @param positionX - position x in the game
	 * @param positionY - position y in the game
	 * @param size - size of the cell on screen
	 * @param imagePath - Image path of the instance
	 */
	public KeyDoor (Key.KeyType unlockingKey, String title, int positionX, int positionY, int size, String imagePath) {
		super(title, positionX, positionY, size, imagePath);
		this.unlockingKey = unlockingKey;
	}

	@Override
	public void open(CollectableItem item) {
		// TODO Auto-generated method stub
	}

}
