package group44.controllers.parsers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import group44.Constants;
import group44.entities.cells.*;
import group44.entities.movableObjects.*;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.game.Level;
import group44.models.LevelInfo;

/**
 * Parses the level file into {@link Level}.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class LevelParser {
	/**
	 * Parses level file and adds LevelObjects into level.
	 *
	 * @param info - the {@link LevelInfo}.
	 * @return the parsed {@link Level}.
	 *
	 * @throws CollisionException when two cells are at the same position.
	 * @throws ParsingException when trying to parse invalid data type.
	 * @throws FileNotFoundException when level file is not found.
	 */
	public static Level parseLevel(LevelInfo info) throws CollisionException, ParsingException, FileNotFoundException {
		Scanner fileScanner = null;
		Level level = null;

		try {
			fileScanner = new Scanner(info.getFile());

			Scanner lineScanner = new Scanner(fileScanner.nextLine());
			lineScanner.useDelimiter(",");

			int id = lineScanner.nextInt();
			int width = lineScanner.nextInt();
			int height = lineScanner.nextInt();
			lineScanner.close();

			level = new Level(id, width, height, Constants.LEVEL_DISPLAY_SIZE);
			parseLevel(level, fileScanner);

		} finally {
			if (fileScanner != null) {
				fileScanner.close();
			}
		}

		return level;
	}

	/**
	 * Parses level file and adds LevelObjects into level.
	 *
	 * @param level - the level to parse.
	 * @param fileScanner - scanner for the level file.
	 *
	 * @throws CollisionException when two cells are at the same position.
	 * @throws ParsingException when trying to parse invalid data type.
	 */
	private static void parseLevel(Level level, Scanner fileScanner) throws CollisionException, ParsingException {
		while (fileScanner.hasNextLine()) {
			Cell cell = parseEntry(level, new Scanner(fileScanner.nextLine()));
			level.addCell(cell.getPositionX(), cell.getPositionY(), cell);
		}
	}

	/**
	 * Parses Cell on the line.
	 *
	 * @param level - the level where the Cell is located.
	 * @param scanner - scanner with the serialised cell.
	 *
	 * @return the parsed {@link Cell}
	 *
	 * @throws ParsingException when trying to parse invalid data type.
	 */
	private static Cell parseEntry(Level level, Scanner scanner) throws ParsingException {
		scanner.useDelimiter(",");
		String type = scanner.next();

		switch (type) {
		case Constants.TYPE_WALL:
			return parseWallEntry(level, scanner);
		case Constants.TYPE_GROUND:
			return parseGroundEntry(level, scanner);
		case Constants.TYPE_WATER:
			return parseWaterEntry(level, scanner);
		case Constants.TYPE_FIRE:
			return parseFireEntry(level, scanner);
		case Constants.TYPE_GOAL:
			return parseGoalEntry(level, scanner);
		default:
			throw new ParsingException(scanner.nextLine());
		}
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level - the level where the Cell is located.
	 * @param scanner - scanner with the serialised cell.
	 *
	 * @return the serialised {@link Wall} as a type {@link Cell}.
	 */
	private static Cell parseWallEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new Wall(level, positionX, positionY, imagePath);
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level - the level where the Cell is located.
	 * @param scanner - scanner with the serialised cell.
	 *
	 * @return the serialised {@link Ground} as a type {@link Cell}.
	 */
	private static Cell parseGroundEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();
		MovableObject movableObject = null;

		if (scanner.hasNext()) {
			String type = scanner.next();

			if (type.equals(Constants.TYPE_PLAYER)) {
				movableObject = parsePlayerEntry(level, scanner);
			}
		}

		if (movableObject != null) {
			return new Ground(level, positionX, positionY, imagePath, movableObject);
		} else {
			return new Ground(level, positionX, positionY, imagePath);
		}
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level - the level where the Cell is located.
	 * @param scanner - scanner with the serialised cell.
	 *
	 * @return the serialised {@link Water} as a type {@link Cell}.
	 */
	private static Cell parseWaterEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new Water(level, positionX, positionY, imagePath);
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level - the level where the Cell is located.
	 * @param scanner - scanner with the serialised cell.
	 *
	 * @return the serialised {@link Fire} as a type {@link Cell}.
	 */
	private static Cell parseFireEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new Fire(level, positionX, positionY, imagePath);
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level - the level where the Cell is located.
	 * @param scanner - scanner with the serialised cell.
	 *
	 * @return the serialised {@link Goal} as a type {@link Cell}.
	 */
	private static Cell parseGoalEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new Goal(level, positionX, positionY, imagePath);
	}

	/**
	 * Parses the player on the scanned line.
	 *
	 * @param level - the level where the player is located.
	 * @param scanner - scanner with the serialised player.
	 *
	 * @return the serialised {@link Player} as a type of {@link MovableObject}.
	 */
	private static MovableObject parsePlayerEntry(Level level, Scanner scanner) {
		String name = scanner.next();
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		int velocityX = scanner.nextInt();
		int velocityY = scanner.nextInt();
		String imagePath = scanner.next();

		return new Player(level, name, positionX, positionY, velocityX, velocityY, imagePath);
	}
}