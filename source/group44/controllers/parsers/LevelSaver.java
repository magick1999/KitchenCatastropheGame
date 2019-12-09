package group44.controllers.parsers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import group44.entities.cells.Cell;
import group44.game.Level;

/**
 * Parses and saves the level into specified file.
 *
 * @author Tomas Svejnoha.
 * @version 1.0
 */
public final class LevelSaver {
    /** Header pattern of the Level file. */
    private static final String LEVEL_HEADER_PATTERN = "%d,%d,%d,%d";

    private LevelSaver() {

    }

    /**
     * Saves the level into file.
     *
     * @param level
     *            the level to save.
     * @param path
     *            path to the file.
     * @throws IOException
     *             when something goes terribly wrong.
     */
    public static void save(Level level, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }

        PrintWriter writer = new PrintWriter(file);
        writer.printf(LEVEL_HEADER_PATTERN + "\n", level.getId(),
                level.getGridWidth(), level.getGridHeight(), level.getTime());

        for (int x = 0; x < level.getGridWidth(); x++) {
            for (int y = 0; y < level.getGridHeight(); y++) {
                Cell cell = level.getGrid()[x][y];
                writer.print(cell.toString() + "\n");
            }
        }

        writer.close();
    }
}
