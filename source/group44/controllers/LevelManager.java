package group44.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import group44.models.LevelInfo;
import group44.Constants;
import group44.controllers.parsers.LevelObjectParser;
import group44.game.Level;

/**
 * Manages and loads Levels.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class LevelManager {
	private static final String LEVEL_FILE_PATTERN = "^level_[0-9]+\\.txt$";
	private static ArrayList<LevelInfo> levelInfos = new ArrayList<>();

	/**
	 * Returns information about available levels.
	 *
	 * @return A list of {@link LevelInfo}
	 */
	public static ArrayList<LevelInfo> getLevelInfos() {
		return LevelManager.levelInfos;
	}

	/**
	 * Loads metadata about levels in a folder.
	 *
	 * @param directory
	 *            - directory containing all {@link Level} files
	 */
	public static void load(String directory) {
		for (File item : LevelManager.getLevelFiles(new File(directory))) {
			LevelInfo info = LevelManager.getLevelInfo(item);
			if (info != null) {
				LevelManager.levelInfos.add(info);
			}
		}
	}

	/**
	 * Returns a list of files containing definitions of {@link Level}s.
	 *
	 * @param directory
	 *            - the directory with {@link Level}s
	 * @return the list of {@link Level} files
	 */
	private static ArrayList<File> getLevelFiles(File directory) {
		ArrayList<File> levelFiles = new ArrayList<>();

		if (directory.isDirectory()) {
			for (File file : directory.listFiles()) {
				if (file.getName().toLowerCase().matches(LevelManager.LEVEL_FILE_PATTERN)) {
					levelFiles.add(file);
				}
			}
		}

		return levelFiles;
	}

	/**
	 * Returns a {@link LevelInfo} of the {@link Level}.
	 *
	 * @param file
	 *            - {@link File} containing the {@link Level} definition.
	 * @return the information about the {@link Level}; null if not found
	 */
	private static LevelInfo getLevelInfo(File file) {
		LevelInfo levelInfo = null;
		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(file);
			fileScanner.useDelimiter(",");

			int id = fileScanner.nextInt();
			int width = fileScanner.nextInt();
			int height = fileScanner.nextInt();

			levelInfo = new LevelInfo(id, width, height, file);
		} catch (Exception e) {
			System.out.println("Error while loading file (" + file.getAbsolutePath() + ") with profiles.");
		} finally {
			if (fileScanner != null) {
				fileScanner.close();
			}
		}
		return levelInfo;
	}

	/**
	 * Returns a loaded {@link Level}.
	 *
	 * @param levelInfo
	 *            - information about the {@link Level} to load
	 * @return the loaded {@link Level}
	 */
	public static Level load(LevelInfo levelInfo) {
		Level level = new Level(levelInfo.getId(), levelInfo.getWidth(), levelInfo.getHeight(), Constants.GRID_DISPLAY_SIZE);
		LevelManager.readLevelObjects(levelInfo.getFile(), level);

		return level;
	}

	private static void readLevelObjects(File file, Level level) {
		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void readLevelObjects(Scanner fileScanner, Level level) {
		while (fileScanner.hasNextLine()) {

			Scanner lineScanner = new Scanner(fileScanner.nextLine());
			String type = lineScanner.next();


		}
	}
}