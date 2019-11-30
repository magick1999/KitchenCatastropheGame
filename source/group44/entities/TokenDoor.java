package group44.entities;

import java.util.ArrayList;

import group44.game.Level;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class is for the token doors where the player needs to have a certain
 * amount of tokens to be able to open it.
 * 
 * @author Amy Mason
 * @version 1.0
 * 
 */
public class TokenDoor extends Door {
	private int tokensNeeded;

	/**
	 * This creates a new {@link KeyDoor} and associates a unlocking
	 * {@link Key.KeyType} with it.
	 * 
	 * @param level        - The {@link Level} where the {@link TokenDoor} is
	 *                     located.
	 * @param title        - title of the object.
	 * @param positionX    - position x in the game.
	 * @param positionY    - position y in the game.
	 * @param imagePath    - Path to the Image representing the {@link Door} in the
	 *                     game.
	 * @param tokensNeeded - number of tokens needed to open the door.
	 */
	public TokenDoor(Level level, String title, int positionX, int positionY, String imagePath, int tokensNeeded) {
		super(level, title, positionX, positionY, imagePath);
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
