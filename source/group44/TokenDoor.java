
/**
 * This class is for the token doors where the player needs to 
 * have a certain amount of tokens to be able to open it.
 * 
 * @author Amy Mason
 * @version 1.0
 * 
 */
public class TokenDoor extends Door {
	private int tokensNeeded; 
	
	/**
	 * This is the Door constructor, which can be called whenever the object needs to be
	 * created - once the details have been read in from the textfile.
	 * @param locked
	 */
	public TokenDoor (int tokensNeeded) {
		this.tokensNeeded = tokensNeeded;
		this.isOpen = false;
	}

	
}
