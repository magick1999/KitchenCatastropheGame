package group44.entities;

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

	/**
	 * Opens the door if a {@link TokenAccumulator} with the right amount of tokens
	 * were used.
	 * 
	 * @param item - the TokenAccumulator to use.
	 * @return true if the door was opened; otherwise false.
	 */
	@Override
	public boolean open(CollectableItem item) {
		if (this.isOpen() == false && item instanceof TokenAccumulator
				&& ((TokenAccumulator) item).getTokensCount() >= this.tokensNeeded) {
			try {
				((TokenAccumulator) item).use(this.tokensNeeded);
				this.setIsOpen(true);
			} catch (Exception e) {
				// DO nothing
			}
		}

		return this.isOpen();
	}
}
