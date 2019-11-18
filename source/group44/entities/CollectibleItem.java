package group44.entities;

import javafx.scene.image.Image;

/**
 * Abstract class for all collectible items in the game.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public abstract class CollectibleItem extends LevelObject {
    private String name;

    public CollectibleItem(){
        String basicPath = "https://p1.hiclipart.com/preview/180/349/86/super-mario-icons-super-mario-star-illustration.jpg";
        Image basic = new Image(basicPath);
        this.setImage(basic); //Sets the image of the collectible to a default star.
    }
}
