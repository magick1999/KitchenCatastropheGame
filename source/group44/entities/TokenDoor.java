package group44.entities;

import java.util.ArrayList;

import group44.game.Level;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class is for the token doors where the player needs to 
 * have a certain amount of tokens to be able to open it.
 * @author Amy Mason
 * @version 1.0
 * 
 */
public class TokenDoor extends Door {
	private int tokensNeeded;

	/**
	 * This creates a new {@link KeyDoor} and associates a unlocking {@link Key.KeyType} with it. 
	 * @param tokensNeeded - number of tokens needed to open the door.
	 * @param title - title of the object.
	 * @param positionX - position x in the game.
	 * @param positionY - position y in the game.
	 * @param size - size of the cell on screen.
	 * @param imagePath - Image path of the instance
	 */
	public TokenDoor (Level level, int tokensNeeded, String title, int positionX, int positionY, int size, String imagePath) {
		super(level, title, positionX, positionY, size, imagePath);
		this.tokensNeeded = tokensNeeded;
	}
	
	@Override
	public void open(ArrayList<CollectableItem> items) {
		if (items != null && !items.isEmpty()) {
			int tokenCount = 0;
			for(CollectableItem item : items) {
				//check item is token and not key
				if(item instanceof Token) {
					tokenCount++;
				}
			}			
			if(tokenCount>=tokensNeeded) {
				this.isOpen = true;
			}
		}
	}
}
