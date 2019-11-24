package group44.entities;

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
	 * This is the Token Door constructor, which can be called whenever the object needs to be
	 * created - once the details have been read in from the textfile.
	 * @param tokensNeeded - number of tokens needed to open the door.
	 * @param title - title of the object.
	 * @param positionX - position x in the game.
	 * @param positionY - position y in the game.
	 * @param size - size of the cell on screen.
	 * @param imagePath - Image path of the instance
	 */
	public TokenDoor (int tokensNeeded, String title, int positionX, int positionY, int size, String imagePath) {
		super(title, positionX, positionY, size, imagePath);
		this.tokensNeeded = tokensNeeded;
	}

	
	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}

}
