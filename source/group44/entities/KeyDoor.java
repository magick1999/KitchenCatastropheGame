package group44.entities;
import java.util.ArrayList;

import group44.game.Level;
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
	private boolean open;

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
	public KeyDoor (Level level, Key.KeyType unlockingKey, String title, int positionX, int positionY, int size, String imagePath) {
		super(level, title, positionX, positionY, size, imagePath);
		this.unlockingKey = unlockingKey;
	}

	@Override
	public void open(ArrayList <CollectableItem> items) {
		if (items != null && !items.isEmpty()) {
			for(CollectableItem item : items) {
				//check item is key and not token 
				if(item instanceof Key) {
					//casting item so that is thought of as a key and not a collectableItem
					if (((Key) item).getKeyCode()==unlockingKey.getKeyCode()) {
						this.open = true;
					}
				}
			}			
		}
	}

	@Override
	protected void onStepped(MovableObject object) {
		// TODO Auto-generated method stub
		
	}
}
