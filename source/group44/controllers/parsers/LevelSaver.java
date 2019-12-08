package group44.controllers.parsers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import group44.Constants;
import group44.game.Level;

public class LevelSaver {
	private static final String LEVEL_HEADER_PATTERN = "%d,%d,%d,%d";

	public static void save(Level level, int profileId) throws IOException {
		File file = new File(String.format(Constants.FOLDER_LEVELS + Constants.FILE_LEVEL_TEMP_PATTERN, level.getId(), profileId));
		if (file.exists() == false) {
			file.createNewFile();
		}

		PrintWriter writer = new PrintWriter(file);
	}
}
