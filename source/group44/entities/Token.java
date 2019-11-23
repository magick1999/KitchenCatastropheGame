package group44.entities;

import javafx.scene.image.Image;

public class Token extends CollectibleItem {

    /**
     * Creates a Token object, sets its title and image.
     */
    public Token() {
        this.setTitle("token");
        String tokenPath = "https://www.pngfind.com/pngs/m/77-776016_mario-coin-super-mario-bros-3-coin-hd.png";
        Image token = new Image(tokenPath, 10, 10, true, true);
        this.setImage(token);
    }

}
