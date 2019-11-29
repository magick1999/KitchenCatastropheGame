package group44.models;

import group44.game.Level;

/**
 * Represents all meta-data information about {@link Level}.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class LevelInfo {
    private int id;
    private int width;
    private int height;

    /**
     * Creates a new instance of {@link LevelInfo}.
     * 
     * @param id     - id of the {@link Level}
     * @param width  - width of the {@link Level}
     * @param height - height of the {@link Level}
     */
    public LevelInfo(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns an id of the level.
     * 
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the width of the level.
     * 
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the level.
     * 
     * @return height
     */
    public int getHeight() {
        return this.height;
    }
}