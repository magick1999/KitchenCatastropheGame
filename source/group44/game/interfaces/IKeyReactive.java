package group44.game.interfaces;

/**
 * An interface of methods {@link group44.entities.LevelObject}s need to
 * implement to handle user input.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public interface IKeyReactive {
    /**
     * Handles the KeyDown event.
     * 
     * @param event - the {@link KeyEvent}
     */
    public void keyDown(KeyEvent event);
}