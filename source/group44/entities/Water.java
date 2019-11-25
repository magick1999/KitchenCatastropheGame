package group44.entities;

/**
 * Class for the Fire Cell
 * 
 * @author Ami Mason, Jordan Price
 * @version 1.1
 */

public class Water extends StepableCell {
	
	/**
     * Creates a new {@link Fire}.
     * @param positionX - Position X in the game
     * @param positionY - Position Y in the game
     * @param size      - Size of the cell on the screen
     * @param imagePath - Image path of the instance
     */
	
	public Water(int positionX, int positionY, int size, String imagePath) {
		super("Fire", positionX, positionY, size, imagePath);
	}
	
	/**
     * Checks if object {@link MovableObject} is has flippers.
     * 
     * @param object - {@link MovableObject} that steps on the cell.
     */

	private boolean hasFlippers(MovableObject object) {
        boolean result = false;
		try {
			result = object.getFlippers();
		} catch (Exception e) {
		}
		return result;
        /*Calls the moveable object and checks if it has fire boots
         (Needs to be implemented in player class I guess)*/
    }
	
	
	/**
     * Places object on the {@link StepableCell} if it has fire boots.
     * 
     * @param object - {@link MovableObject} that steps on the cell.
     */
	@Override 
	public void stepOn(MovableObject object) {
		if (this.getMovableObject() == null) {
            this.setMovableObject(object);//Sets the moveable object onto this object
            if (!hasFlippers(object)) { //Checks if moveable object has flippers.
            	try {
            		object.kill();//Kills object
        		} catch (Exception e) {
        			//Incase object doesn't have a way to be killed.
        		}
            }
            
		} else {
                    throw new UnsupportedOperationException("Not implemented exception");
                }	
    }       
}