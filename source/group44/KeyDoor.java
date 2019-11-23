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
	private Color colourKeyNeeded;
	//might want colour if they're not images.
	//private Color doorColour;
	
	/**
	 * This is the Door constructor, which can be called whenever the object needs to be
	 * created - once the details have been read in from the textfile.
	 * @param colourKeyNeeded
	 */
	public KeyDoor (Color colourKeyNeeded) {
		this.colourKeyNeeded = colourKeyNeeded;
		this.isOpen = false;
	}
}
