package group44.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.CODESET_INCOMPATIBLE;

import group44.models.LevelInfo;
import group44.Constants;
import group44.controllers.parsers.LevelParser;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.game.Level;

/**
 * Manages and loads Levels.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class LevelManager {
	private static final String LEVEL_TEMP_FILE_PATTERN = "%d-%d.txt"; // "LEVEL_ID-PROFILE_ID.txt"
	private static final String LEVEL_HEADER_PATTERN = "%d,%d,%d,%d";

	private static final String LEVEL_FILE_PATTERN = "^level_[0-9]+\\.txt$";
	private static final String ERROR_LEVELID_NOT_FOUND = "Unable to find level with id=%d.";
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
	 * Returns a number of available levels.
	 *
	 * @return number of levels.
	 */
	public static int countAvailableLevels() {
		return LevelManager.levelInfos.size();
	}

	/**
	 * Loads level information from the default folder.
	 *
	 * @return array of {@link LevelInfo}s.
	 */
	public static ArrayList<LevelInfo> load() {
		load(Constants.FOLDER_LEVELS);
		return LevelManager.getLevelInfos();
	}

	/**
	 * Loads metadata about levels in a folder.
	 *
	 * @param directory
	 *            - directory containing all {@link Level} files
	 */
	private static void load(String directory) {
		LevelManager.levelInfos.clear();
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

			if (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();

				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");
				int id = lineScanner.nextInt();
				int width = lineScanner.nextInt();
				int height = lineScanner.nextInt();
				lineScanner.close();

				levelInfo = new LevelInfo(id, file);
			}
		} catch (Exception e) {
			System.out.println("LevelManager: Error while loading file (" + file.getAbsolutePath()
					+ ") with profiles.\n" + e.getMessage());
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
	 *            - information about the {@link Level} to load.
	 * @return the loaded {@link Level}.
	 *
	 * @throws CollisionException
	 *             when two cells are at the same position.
	 * @throws ParsingException
	 *             when trying to parse invalid data type.
	 * @throws FileNotFoundException
	 *             when level file is not found.
	 */
	public static Level load(LevelInfo levelInfo) throws FileNotFoundException, CollisionException, ParsingException {
		return LevelParser.parseLevel(levelInfo);
	}

	/**
	 * Returns a loaded {@link Level}.
	 *
	 * @param levelInfo
	 *            - information about the {@link Level} to load.
	 * @return the loaded {@link Level}.
	 *
	 * @throws CollisionException
	 *             when two cells are at the same position.
	 * @throws ParsingException
	 *             when trying to parse invalid data type.
	 * @throws FileNotFoundException
	 *             when level file is not found.
	 * @throws IllegalArgumentException
	 *             when Level with levelId is not found.
	 *
	 */
	public static Level load(int levelId)
			throws FileNotFoundException, CollisionException, ParsingException, IllegalArgumentException {
		LevelInfo levelInfo = getLevelInfo(levelId);
		if (levelInfo == null) {
			throw new IllegalArgumentException(String.format(ERROR_LEVELID_NOT_FOUND, levelId));
		}

		return LevelParser.parseLevel(levelInfo);
	}

	/**
	 * Looks for a level with id in the loaded levels.
	 *
	 * @param id
	 *            - id of a level to load.
	 * @return the {@link LevelInfo} for the level with id.
	 */
	private static LevelInfo getLevelInfo(int id) {
		for (LevelInfo levelInfo : levelInfos) {
			if (levelInfo.getId() == id) {
				return levelInfo;
			}
		}
		return null;
	}

	/**
	 * Saves the current state of the level.
	 *
	 * @param level
	 *            - level to save.
	 * @param profileId
	 *            - profile id of the user.
	 */
	public static void save(Level level, int profileId) {

	}

	/**
	 * Loads the saved level.
	 *
	 * @param levelId
	 *            - id of the level to load.
	 * @param profileId
	 *            - id of the user who saved the game.
	 * @return the loaded {@link Level}.
	 *
	 * @throws ParsingException
	 *             when two cells are at the same position.
	 * @throws CollisionException
	 *             when trying to parse invalid data type.
	 * @throws FileNotFoundException
	 *             when level file is not found.
	 */
	public static Level resume(int levelId, int profileId)
			throws FileNotFoundException, CollisionException, ParsingException {
		return LevelParser.parseLevel(getLevelInfoForFile(levelId, profileId));
	}

	/**
	 * Deletes saved level for profile id.
	 *
	 * @param levelId
	 *            - id of the level.
	 * @param profileId
	 *            - id of the profile.
	 */
	public static void deleteTempData(int levelId, int profileId) {
		if (hasUnfinishedLevel(levelId, profileId)) {

		}
	}

	/**
	 * Indicates whether the player has an unfinished level with id.
	 *
	 * @param levelId
	 *            - id of the level.
	 * @param profileId
	 *            - id of the profile.
	 * @return true if there is a level with id saved; otherwise false.
	 */
	public static boolean hasUnfinishedLevel(int levelId, int profileId) {
		return false;
	}

	/**
	 * Returns a {@link LevelInfo} for
	 *
	 * @param levelId
	 *            - id of the level we want to load.
	 * @param profileId
	 *            - id of the profile for which we want to load.
	 * @return
	 */
	private static LevelInfo getLevelInfoForFile(int levelId, int profileId) {
		String file = String.format(LEVEL_TEMP_FILE_PATTERN, levelId, profileId);
		return new LevelInfo(levelId, new File(Constants.FOLDER_LEVELS + file));
	}
}