package group44.game.interfaces;

/**
 * An inteface for classes inheriting from {@link group44.entities.LevelObject}
 * whose behaviour is time dependent.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public interface ITimeReactive {
    /**
     * Method executed on every time tick.
     */
    void timeTick();
}