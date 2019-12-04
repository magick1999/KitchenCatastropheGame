package group44.game;

import group44.entities.LevelObject;

/**
 * Class containing results from collision check.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class CollisionCheckResult {
    private LevelObject collidingObject;
    private CollisionCheckResultType type;

    /**
     * Creates a new instance of {@link CollisionCheckResult}.
     *
     * @param type - {@link CollisionCheckResultType} of the collision.
     */
    public CollisionCheckResult(CollisionCheckResultType type) {
    	this.type = type;
    }

    /**
     * Creates a new instance of {@link CollisionCheckResult}.
     *
     * @param type - {@link CollisionCheckResultType} of the collision.
     * @param collidingObject - the colliding {@link LevelObject}.
     */
    public CollisionCheckResult(CollisionCheckResultType type, LevelObject collidingObject) {
    	this(type);
        this.collidingObject = collidingObject;
    }

    /**
     * Returns a boolean indicating whether the object is colliding or not.
     *
     * @return true if the object is colliding; false otherwise.
     */
    public Boolean isColliding() {
    	return this.type != CollisionCheckResultType.Successful;
    }

    /**
     * Returns the LevelObject the object is colliding with.
     *
     * @return Collided object.
     */
    public LevelObject getCollidingObject() {
        return this.collidingObject;
    }

    /**
     * Returns a type of the {@link CollisionCheckResult}.
     *
     * @return the {@link CollisionCheckResultType}.
     */
    public CollisionCheckResultType getType() {
    	return this.type;
    }

    /**
     * Represents a result type of the collision.
     *
     * @author Tomáš Švejnoha
     * @version 1.0
     */
    public enum CollisionCheckResultType {
    	Successful,

    	Enemy,
    	Player,

    	MissingKey,
    	NotEnoughTokens
    }
}