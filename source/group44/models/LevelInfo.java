package group44.models;

import java.io.File;

import group44.game.Level;

/**
 * Represents all meta-data information about {@link Level}.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class LevelInfo {
    private int id;
    private File file;

    /**
     * Creates a new instance of {@link LevelInfo}.
     *
     * @param id
     *            - id of the {@link Level}.
     * @param file
     *            - file containing the {@link Level} definition.
     */
    public LevelInfo(int id, File file) {
	this.id = id;
	this.file = file;
    }

    /**
     * Returns an id of the level.
     *
     * @return the level id.
     */
    public int getId() {
	return this.id;
    }

    /**
     * Returns the file of the level.
     *
     * @return the file containing the {@link Level} definition.
     */
    public File getFile() {
	return this.file;
    }
}