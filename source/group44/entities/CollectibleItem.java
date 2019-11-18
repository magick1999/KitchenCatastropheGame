package group44.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Abstract class for all collectible items in the game.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class CollectibleItem extends LevelObject {
    protected String name;

    /**
     * Constructs a basic collectible object and sets image to a basic star icon.
     */
    public CollectibleItem(){
        String basicPath = "https://p1.hiclipart.com/preview/180/349/86/super-mario-icons-super-mario-star-illustration.jpg";
        Image basic = new Image(basicPath, 10, 10, true, true);
        this.setImage(basic); //Sets the image of the collectible to a default star.
    }

    /**
     * Draws collectible using drawImage.
     * @param gc The graphics context that is being drawn to.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.getImage(), this.getPositionX(), this.getPositionY());
    }
}
