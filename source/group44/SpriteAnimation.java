package group44;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class changes the movable object image each time interpolate is called to create an animation.
 * It also changes the position of movable object each time interpolate is called.
 * @author Mihai
 */
public class SpriteAnimation extends Transition {
	//Random resources loaded to test the animation
	private Image player = new Image("group44/resources/player.png");
	private Image wall = new Image("group44/resources/default_silver_sand.png");
    //The object on which the animation will be performed.
    private final ImageView imageView;
    //The number of textures to be cycled through.
    private final int count;
    //The container for the images.
    ArrayList<Image> images = new ArrayList<Image>();
    //The x coordinate modifier.
    private double byX;
    //Images for the animations.
    private Image[][] animations = new Image[5][4];
    //The y coordinate modifier.
    private double byY;
    //Current image displayed.
    private int imageCounter=0;
    //The starting x position.
    private double startingX;
    //The starting y position.
    private double startingY;
    //This showcases the orientation of the player.
    int orientation;
    //Sets the speed of the animation.
    int speed = 1;
    public SpriteAnimation(
            ImageView imageView,
            Duration duration, double x, double y,int orientation) {
    	//Loading image container.
        images.add(wall);
        images.add(player);
        images.add(wall);
        images.add(player);
        images.add(wall);
        images.add(player);
        this.imageView = imageView;
        startingX = imageView.getX();
        startingY = imageView.getY();
        this.count = 3;
        this.orientation=orientation;
        this.byX = x;
        this.byY = y;
        loadAnimations();
        //Stating of how long one cycle of this animation should last.
        setCycleDuration(duration);
        //Setting the animation behavior.
        setInterpolator(Interpolator.LINEAR);
    }
    
    private void loadAnimations() {
        animations[0][0] = wall;
        animations[0][1] = player;
        animations[0][2] = wall;
        animations[0][3] = player;
        animations[1][0] = wall;
        animations[1][1] = player;
        animations[1][2] = wall;
        animations[1][3] = player;
        animations[2][0] = new Image("/group44/resources/ChefLeftWalk/ChefLeftWalk1.png");
        animations[2][1] = new Image("/group44/resources/ChefLeftWalk/ChefLeftWalk2.png");
        animations[2][2] = new Image("/group44/resources/ChefLeftWalk/ChefLeftWalk3.png");
        animations[2][3] = new Image("/group44/resources/ChefLeftWalk/ChefLeftWalk4.png");
        animations[3][0] = new Image("/group44/resources/ChefRightWalk/ChefRightWalk1.png");
        animations[3][1] = new Image("/group44/resources/ChefRightWalk/ChefRightWalk2.png");
        animations[3][2] = new Image("/group44/resources/ChefRightWalk/ChefRightWalk3.png");
        animations[3][3] = new Image("/group44/resources/ChefRightWalk/ChefRightWalk4.png");
    }
    /**
     * This method is called frequently to update the animation.
     * @param k is a fraction in between 0 and 1 that showcases the progress of the animation.
     */
    protected void interpolate(double k) {
    	//Move the player.
        if (speed % 3 == 0 || k==1) {
            imageView.setX(startingX + (k * byX));
            imageView.setY(startingY + (k * byY));
            //Change the image.
            if (imageCounter <= count) {
                imageView.setImage(animations[orientation][imageCounter]);
                imageCounter++;
            } else {
                imageView.setImage(animations[orientation][0]);
                imageCounter = 1;
            }
        }
        speed++;
    }
}