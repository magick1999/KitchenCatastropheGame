package group44.entities;

import javafx.scene.canvas.GraphicsContext;

/**
 * Class for the Fire Cell
 * 
 * @author Jordan Price
 * @version 1.0
 */

public class Fire extends StepableCell {
	
	public Fire(String title, int positionX, int positionY, int size, String imagePath) {
		super(title, positionX, positionY, size, imagePath);
	}

	/**
    	* Checks if object {@link MovableObject} is able to walk on the fire.
     	* 
     	* @param object - {@link MovableObject} that steps on the cell.
     	*/
	private boolean isWalkable(MovableObject object) {
        boolean result = false;
		try {
			result = object.getFireBoots();
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
            if (isWalkable(object)) { //Checks if fire is walkable with boots.
            	if (this.getMovableObject() == null) {
                    this.setMovableObject(object);//Sets the moveable object onto this object
            	} else {
                    throw new UnsupportedOperationException("Not implemented exception");
                }
            } else {
            	object.kill();//maybe?
            }
            
        
    }
	
	@Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
    }

}
