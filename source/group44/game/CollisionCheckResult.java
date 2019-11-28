package group44.game;

import group44.entities.LevelObject;

/**
 * Class containg results from collision check.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class CollisionCheckResult {
    private LevelObject collidingObject;

    /**
     * Creates a new instance of {@link CollisionCheckResult}.
     * 
     * @param collidingObject
     */
    public CollisionCheckResult(LevelObject collidingObject) {
        this.collidingObject = collidingObject;
    }

    /**
     * Returns a boolean indicating whether the object is colliding or not.
     * 
     * @return true if the object is colliding; false otherwise
     */
    public Boolean getIsColliding() {
        return this.collidingObject != null;
    }

    /**
     * Returns the LevelObject the object is colliding with.
     * 
     * @return Collided object
     */
    public LevelObject getCollidingObject() {
        return this.collidingObject;
    }
}