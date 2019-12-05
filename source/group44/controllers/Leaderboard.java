package group44.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import group44.Constants;
import group44.game.Level;
import group44.models.Profile;
import group44.models.Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a leaderboard of the game.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Leaderboard {
	private static ObservableList<Record> records = FXCollections.observableArrayList(new ArrayList<Record>());

	/**
	 * Returns an {@link ObservableList} of records.
	 *
	 * @return profiles
	 */
	public static ObservableList<Record> getProfiles() {
		return Leaderboard.records;
	}

	/**
	 * Adds or updates a record in the {@link Leaderboard} if the time is
	 * better.
	 *
	 * @param profile
	 *            - {@link Profile} of the user
	 * @param levelId
	 *            - Id of the {@link Level} the user finished
	 * @param time
	 *            - time taken to finish the {@link Level}
	 */
	public static void addOrUpdate(Profile profile, int levelId, long time) {
		Record record = null;

		for (Record item : Leaderboard.records) {
			if (item.getProfile().getId() == profile.getId() && item.getLevelId() == levelId) {
				record = item;
				break;
			}
		}

		if (record == null) {
			Leaderboard.records.add(new Record(profile, levelId, time));
		} else if (record.getTime() > time) {
			record.setTime(time);
		}
	}

	/**
	 * Returns up to 3 top records for the level.
	 *
	 * @param levelId
	 *            - id of the {@link Level} for which we want the records
	 * @return array of up to 3 top records
	 */
	public static Record[] getTopThreeRecords(int levelId) {
		ArrayList<Record> levelRecords = new ArrayList<>();

		for (Record item : Leaderboard.records) {
			if (item.getLevelId() == levelId) {
				levelRecords.add(item);
			}
		}

		Collections.sort(levelRecords);

		Record[] top3 = new Record[(levelRecords.size() < 3) ? levelRecords.size() : 3];
		for (int i = 0; i < 3; i++) {
			top3[i] = levelRecords.get(i);
		}

		return top3;
	}

	/**
	 * Indicates whether the used scored in top 3 for a specific level.
	 *
	 * @param profile
	 *            - the user {@link Profile}.
	 * @param levelId
	 *            - the level id.
	 * @return true if user is in top 3; otherwise false.
	 */
	public static boolean isInTopThreeRecors(Profile profile, int levelId) {
		Record[] records = Leaderboard.getTopThreeRecords(levelId);
		for (Record item : records) {
			if (item.getProfile().getId() == profile.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an id the highest {@link Level} achieved by user.
	 *
	 * @param profileId
	 *            - profile Id of the user.
	 * @return the id of the highest level; 0 if user did not finish any level.
	 */
	public static int getAchievedLevel(int profileId) {
		int level = 0;

		for (Record record : Leaderboard.records) {
			if (record.getProfile().getId() == profileId) {
				if (level < record.getLevelId()) {
					level = record.getLevelId();
				}
			}
		}

		return level;
	}

	/**
	 * Loads records from the file.
	 *
	 * @param path
	 *            - path to a file with records
	 */
	public static void load(String path) {
		ArrayList<Record> loadedRecords = null;
		Scanner fileScanner = null;
		File file = new File(path);

		try {
			fileScanner = new Scanner(file);
			loadedRecords = Leaderboard.load(fileScanner);
		} catch (Exception e) {
			System.out.println("Failed to load records from file (" + path + ").");
			loadedRecords = new ArrayList<Record>();
		} finally {
			if (fileScanner != null) {
				fileScanner.close();
			}
			for (Record item : loadedRecords) {
				Leaderboard.records.add(item);
			}
		}
	}

	/**
	 * Loads stored records.
	 *
	 * @param fileScanner
	 *            - scanner of the file where the records are stored
	 * @return a list of loaded {@link Record}s
	 */
	private static ArrayList<Record> load(Scanner fileScanner) {
		ArrayList<Record> loadedRecords = new ArrayList<>();

		while (fileScanner.hasNextLine()) {
			Record r = Leaderboard.parseRecord(new Scanner(fileScanner.nextLine()));
			if (r != null) {
				loadedRecords.add(r);
			}
		}

		return loadedRecords;
	}

	/**
	 * Creates a new {@link Record} from the Scanner.
	 *
	 * @param scanner
	 *            - scanner with the serialised record
	 * @return created {@link Record}; null if associated {@link Profile} was
	 *         not found
	 */
	private static Record parseRecord(Scanner scanner) {
		Record record = null;
		scanner.useDelimiter(",");

		try {
			int profileId = scanner.nextInt();
			int levelId = scanner.nextInt();
			long time = scanner.nextLong();

			Profile profile = ProfileManager.getProfile(profileId);
			if (profile != null) {
				record = new Record(profile, levelId, time);
			} else {
				System.out.println("Unable to parse a Record. Profile(id=" + profileId + ") not found!");
			}
		} catch (Exception e) {
			System.out.println("Unable to parse a Profile.\n" + e.getMessage());
		}

		return record;
	}

	/**
	 * Saves the records in the {@link Leaderboard} to the default file.
	 */
	public static void save() {
		save(Constants.FILE_RECORDS);
	}

	/**
	 * Saves all records in the {@link Leaderboard}.
	 *
	 * @param path
	 *            - path to the file where to store the profiles
	 */
	public static void save(String path) {
		File file = new File(path);
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(file);
			Leaderboard.save(writer);
		} catch (Exception e) {
			System.out.println("Unable to save records.\n" + e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * Saves records using provided writer.
	 *
	 * @param writer
	 *            - {@link PrintWriter} to use when saving r cords
	 */
	private static void save(PrintWriter writer) {
		for (Record record : Leaderboard.records) {
			writer.println(record.toString());
		}
	}
}