package group44.game.interfaces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * An interface for {@link group44.game.Level}.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public interface ILevel {
    /**
     * Draws the game.
     * 
     * @param gc - {@link GraphicsContext} to which the game is drawn
     */
    public void draw(GraphicsContext gc);

    /**
     * Handles the KeyDown event
     * 
     * @param event - the {@link KeyEvent}
     */
    public void keyDown(KeyEvent event);
}