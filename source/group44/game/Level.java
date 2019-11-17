package group44.game;

import group44.entities.LevelObject;

/**
 * Level maintains all data and event handling in the game.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Level {
    private LevelObject[][] grid;

    /**
     * Checks whether the object is colliding or not.
     * 
     * @param obj - object for to check the collision.
     * 
     * @return result of the collision check.
     */
    public CollisionCheckResult checkCollision(LevelObject obj) {
        if (this.isColiding(obj)) {
            return new CollisionCheckResult(grid[obj.getPositionX()][obj.getPositionY()]);
        }
        return new CollisionCheckResult(null);
    }

    /**
     * Checks whether the object is colliding or not.
     * 
     * @param obj - object for which to check the collision.
     * @return true if the object is colliding, otherwise false
     */
    private Boolean isColiding(LevelObject obj) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }
}