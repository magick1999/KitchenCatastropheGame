package group44.controllers.parsers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import group44.Constants;
import group44.entities.cells.*;
import group44.entities.collectableItems.CollectableItem;
import group44.entities.collectableItems.FireBoots;
import group44.entities.collectableItems.Flippers;
import group44.entities.collectableItems.Key;
import group44.entities.collectableItems.Key.KeyType;
import group44.entities.collectableItems.Token;
import group44.entities.movableObjects.*;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.game.Level;
import group44.models.LevelInfo;

/**
 * Parses the level file into {@link Level}.
 *
 * @author Tomas Svejnoha, Amy Mason.
 * @version 1.0
 */
public class LevelParser {
	/**
	 * Parses level file and adds LevelObjects into level.
	 *
	 * @param info
	 *            - the {@link LevelInfo}.
	 * @return the parsed {@link Level}.
	 *
	 * @throws CollisionException
	 *             when two cells are at the same position.
	 * @throws ParsingException
	 *             when trying to parse invalid data type.
	 * @throws FileNotFoundException
	 *             when level file is not found.
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
	 * @param level
	 *            - the level to parse.
	 * @param fileScanner
	 *            - scanner for the level file.
	 *
	 * @throws CollisionException
	 *             when two cells are at the same position.
	 * @throws ParsingException
	 *             when trying to parse invalid data type.
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
	 * @param level
	 *            - the level where the Cell is located.
	 * @param scanner
	 *            - scanner with the serialised cell.
	 *
	 * @return the parsed {@link Cell}
	 *
	 * @throws ParsingException
	 *             when trying to parse invalid data type.
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
		case Constants.TYPE_KEY_DOOR:
			return parseKeyDoorEntry(level, scanner);
		case Constants.TYPE_TOKEN_DOOR:
			return parseTokenDoorEntry(level, scanner);
		default:
			throw new ParsingException(scanner.nextLine());
		}
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level
	 *            - the level where the Cell is located.
	 * @param scanner
	 *            - scanner with the serialised cell.
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
	 * @param level
	 *            - the level where the Cell is located.
	 * @param scanner
	 *            - scanner with the serialised cell.
	 *
	 * @return the serialised {@link Ground} as a type {@link Cell}.
	 */
	private static Cell parseGroundEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();
		MovableObject movableObject = null;
		CollectableItem collectableItem = null;

		if (scanner.hasNext()) {
			String type = scanner.next();

			if (type.equals(Constants.TYPE_PLAYER)) {
				movableObject = parsePlayerEntry(level, scanner);
			} else if (type.equals(Constants.TYPE_DUMB_TARGETING_ENEMY)) {
				movableObject = parseDumbTargetingEnemyEntry(level, scanner);
			} else if (type.equals(Constants.TYPE_STRAIGHT_WALKING_ENEMY)) {
				movableObject = parseStraightWalkingEnemyEntry(level, scanner);
			} else if (type.equals(Constants.TYPE_WALL_FOLLOWING_ENEMY)) {
				movableObject = parseWallFollowingEnemyEntry(level, scanner);
			} else if (type.equals(Constants.TYPE_FIRE_BOOTS)) {
				collectableItem = parseFireBootsEntry(level, scanner);
			} else if (type.equals(Constants.TYPE_FLIPPERS)) {
				collectableItem = parseFlipperEntry(level, scanner);
			} else if (type.equals(Constants.TYPE_KEY)) {
				collectableItem = parseKeyEntry(level, scanner);
			} else if (type.equals(Constants.TYPE_TOKEN)) {
				collectableItem = parseTokenEntry(level, scanner);
			}
		}

		if (movableObject != null) {
			return new Ground(level, positionX, positionY, imagePath, movableObject);
		} else if (collectableItem != null) {
			return new Ground(level, positionX, positionY, imagePath, collectableItem);
		} else {
			return new Ground(level, positionX, positionY, imagePath);
		}
	}

	/**
	 * Retrieves the Key Type.
	 *
	 * @param keyTypeID
	 *            - the key ID, specific to Key Type.
	 * @return - the Key Type.
	 */
	private static KeyType getKeyType(int keyTypeID) {
		switch (keyTypeID) {
		case 1:
			return KeyType.RED;
		case 2:
			return KeyType.BLUE;
		case 3:
			return KeyType.GREEN;
		case 4:
			return KeyType.GOLD;
		default:
			return KeyType.RED;
		}
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level
	 *            - the level where the Cell is located.
	 * @param scanner
	 *            - scanner with the serialised cell.
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
	 * @param level
	 *            - the level where the Cell is located.
	 * @param scanner
	 *            - scanner with the serialised cell.
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
	 * @param level
	 *            - the level where the Cell is located.
	 * @param scanner
	 *            - scanner with the serialised Cell.
	 *
	 * @return the serialised {@link Key Door} as a type {@link Cell}.
	 */
	private static Cell parseKeyDoorEntry(Level level, Scanner scanner) {
		String title = scanner.next();
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String lockedImagePath = scanner.next();
		String unlockedImagePath = scanner.next();
		KeyType unlockingKey = getKeyType(scanner.nextInt());

		return new KeyDoor(level, title, positionX, positionY, lockedImagePath, unlockedImagePath, unlockingKey);
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level
	 *            - the level where the Cell is located.
	 * @param scanner
	 *            - scanner with the serialised Cell.
	 *
	 * @return the serialised {@link Token Door} as a type {@link Cell}.
	 */
	private static Cell parseTokenDoorEntry(Level level, Scanner scanner) {
		String title = scanner.next();
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String lockedImagePath = scanner.next();
		String unlockedImagePath = scanner.next();
		int tokensNeeded = scanner.nextInt();

		return new TokenDoor(level, title, positionX, positionY, lockedImagePath, unlockedImagePath, tokensNeeded);
	}

	/**
	 * Parses the line into cell.
	 *
	 * @param level
	 *            - the level where the Cell is located.
	 * @param scanner
	 *            - scanner with the serialised cell.
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
	 * @param level
	 *            - the level where the player is located.
	 * @param scanner
	 *            - scanner with the serialised player.
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

	/**
	 * Parses the Dumb Targeting Enemy on the scanned line.
	 *
	 * @param level
	 *            - the level where the enemy is located.
	 * @param scanner
	 *            - scanner with the serialised enemy.
	 *
	 * @return the serialised {@link DumbTargetingEnemy} as a type of
	 *         {@link MovableObject}.
	 */
	private static MovableObject parseDumbTargetingEnemyEntry(Level level, Scanner scanner) {
		String name = scanner.next();
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new DumbTargetingEnemy(level, name, positionX, positionY, imagePath);
	}

	/**
	 * Parses the Straight Walking Enemy on the scanned line.
	 *
	 * @param level
	 *            - the level where the enemy is located.
	 * @param scanner
	 *            - scanner with the serialised enemy.
	 *
	 * @return the serialised {@link StraightWalkingEnemy} as a type of
	 *         {@link MovableObject}.
	 */
	private static MovableObject parseStraightWalkingEnemyEntry(Level level, Scanner scanner) {
		String name = scanner.next();
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		int velocityX = scanner.nextInt();
		int velocityY = scanner.nextInt();
		String imagePath = scanner.next();

		return new StraightWalkingEnemy(level, name, positionX, positionY, velocityX, velocityY, imagePath);
	}

	/**
	 * Parses the Wall Following Enemy on the scanned line.
	 *
	 * @param level
	 *            - the level where the enemy is located.
	 * @param scanner
	 *            - scanner with the serialised enemy.
	 *
	 * @return the serialised {@link WallFollowingEnemy} as a type of
	 *         {@link MovableObject}.
	 */
	private static MovableObject parseWallFollowingEnemyEntry(Level level, Scanner scanner) {
		String name = scanner.next();
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new WallFollowingEnemy(level, name, positionX, positionY, imagePath);
	}

	/**
	 * Parses the line into Collectable item - Fire Boots.
	 *
	 * @param level
	 *            - the level where the item is located.
	 * @param scanner
	 *            - scanner with the serialised fire boots.
	 *
	 * @return the serialised {@link FireBoots} as a type
	 *         {@link CollectableItem}.
	 */
	private static CollectableItem parseFireBootsEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new FireBoots(level, positionX, positionY, imagePath);
	}

	/**
	 * Parses the line into Collectable item - Flippers.
	 *
	 * @param level
	 *            - the level where the item is located.
	 * @param scanner
	 *            - scanner with the serialised flippers.
	 *
	 * @return the serialised {@link Flippers} as a type
	 *         {@link CollectableItem}.
	 */
	private static CollectableItem parseFlipperEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new Flippers(level, positionX, positionY, imagePath);
	}

	/**
	 * Parses the line into Collectable item - Key.
	 *
	 * @param level
	 *            - the level where the item is located.
	 * @param scanner
	 *            - scanner with the serialised key.
	 *
	 * @return the serialised {@link Key} as a type {@link CollectableItem}.
	 */
	private static CollectableItem parseKeyEntry(Level level, Scanner scanner) {
		int keyTypeID = scanner.nextInt();
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		KeyType keyType = getKeyType(keyTypeID);

		return new Key(level, keyType, positionX, positionY);
	}

	/**
	 * Parses the line into Collectable item - Token.
	 *
	 * @param level
	 *            - the level where the item is located.
	 * @param scanner
	 *            - scanner with the serialised token.
	 *
	 * @return the serialised {@link Token} as a type {@link CollectableItem}.
	 */
	private static CollectableItem parseTokenEntry(Level level, Scanner scanner) {
		int positionX = scanner.nextInt();
		int positionY = scanner.nextInt();
		String imagePath = scanner.next();

		return new Token(level, positionX, positionY, imagePath);
	}
}
