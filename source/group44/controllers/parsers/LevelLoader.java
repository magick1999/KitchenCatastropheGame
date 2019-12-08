package group44.controllers.parsers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import group44.Constants;
import group44.entities.cells.Cell;
import group44.entities.cells.Fire;
import group44.entities.cells.Goal;
import group44.entities.cells.Ground;
import group44.entities.cells.KeyDoor;
import group44.entities.cells.StepableCell;
import group44.entities.cells.Teleporter;
import group44.entities.cells.TokenDoor;
import group44.entities.cells.Wall;
import group44.entities.cells.Water;
import group44.entities.collectableItems.CollectableItem;
import group44.entities.collectableItems.FireBoots;
import group44.entities.collectableItems.Flippers;
import group44.entities.collectableItems.Key;
import group44.entities.collectableItems.Key.KeyType;
import group44.entities.collectableItems.Token;
import group44.entities.movableObjects.DumbTargetingEnemy;
import group44.entities.movableObjects.MovableObject;
import group44.entities.movableObjects.Player;
import group44.entities.movableObjects.SmartTargetingEnemy;
import group44.entities.movableObjects.StraightWalkingEnemy;
import group44.entities.movableObjects.WallFollowingEnemy;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.game.Level;
import group44.models.LevelInfo;

/**
 * Loads and parses the level file into {@link Level}.
 *
 * @author Tomas Svejnoha, Amy Mason
 * @version 1.0
 */
public class LevelLoader {
    private static final String ERROR_MESSAGE_TELEPORTER_NOT_FOUND = "%s,%d,%d";

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
    public static Level parseLevel(LevelInfo info)
            throws CollisionException, ParsingException, FileNotFoundException {
        Scanner fileScanner = null;
        Level level = null;

        try {
            fileScanner = new Scanner(info.getFile());

            Scanner lineScanner = new Scanner(fileScanner.nextLine());
            lineScanner.useDelimiter(",");

            int id = lineScanner.nextInt();
            int width = lineScanner.nextInt();
            int height = lineScanner.nextInt();
            int time = lineScanner.nextInt();
            lineScanner.close();

            level = new Level(id, width, height, Constants.LEVEL_DISPLAY_SIZE,
                    time);
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
    private static void parseLevel(Level level, Scanner fileScanner)
            throws CollisionException, ParsingException {
        while (fileScanner.hasNextLine()) {
            parseEntry(level, new Scanner(fileScanner.nextLine()));
        }
    }

    /**
     * Parses line entry and adds LevelObjects into level.
     *
     * @param level
     *            - the level where the entry is located.
     * @param scanner
     *            - scanner with the serialised entry.
     *
     * @throws ParsingException
     *             when trying to parse invalid data type.
     * @throws CollisionException
     *             when two cells are at the same position.
     */
    private static void parseEntry(Level level, Scanner scanner)
            throws ParsingException, CollisionException {
        scanner.useDelimiter(",");
        String type = scanner.next();

        Cell cell = null;

        switch (type) {
        case Constants.TYPE_WALL:
            cell = parseWallEntry(level, scanner);
            break;
        case Constants.TYPE_GROUND:
            cell = parseGroundEntry(level, scanner);
            break;
        case Constants.TYPE_WATER:
            cell = parseWaterEntry(level, scanner);
            break;
        case Constants.TYPE_FIRE:
            cell = parseFireEntry(level, scanner);
            break;
        case Constants.TYPE_GOAL:
            cell = parseGoalEntry(level, scanner);
            break;
        case Constants.TYPE_KEY_DOOR:
            cell = parseKeyDoorEntry(level, scanner);
            break;
        case Constants.TYPE_TOKEN_DOOR:
            cell = parseTokenDoorEntry(level, scanner);
            break;
        case Constants.TYPE_TELEPORTER:
            cell = parseTeleporterEntry(level, scanner);
            break;
        case Constants.TYPE_TELEPORTER_LINK:
            parseTeleporterLink(level, scanner);
        }

        if (cell != null) {
            level.addCell(cell.getPositionX(), cell.getPositionY(), cell);
        } else if (type.equals(Constants.TYPE_TELEPORTER_LINK)) {
            // DO nothing
        } else {
            throw new ParsingException(type + " " + scanner.nextLine());
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
     * @return the serialised {@link Ground} as a type of {@link StepableCell}.
     */
    private static StepableCell parseGroundEntry(Level level, Scanner scanner) {
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
            } else if (type.equals(Constants.TYPE_SMART_TARGETING_ENEMY)) {
                movableObject = parseSmartTargetingEnemyEntry(level, scanner);
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
            return new Ground(level, positionX, positionY, imagePath,
                    movableObject);
        } else if (collectableItem != null) {
            return new Ground(level, positionX, positionY, imagePath,
                    collectableItem);
        } else {
            return new Ground(level, positionX, positionY, imagePath);
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
     * @return the serialised {@link Water} as a type of {@link StepableCell}.
     */
    private static StepableCell parseWaterEntry(Level level, Scanner scanner) {
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String imagePath = scanner.next();

        MovableObject movableObject = null;
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
            } else if (type.equals(Constants.TYPE_SMART_TARGETING_ENEMY)) {
                movableObject = parseSmartTargetingEnemyEntry(level, scanner);
            }
        }

        StepableCell stepableCell = new Water(level, positionX, positionY,
                imagePath);

        if (movableObject != null) {
            stepableCell.stepOn(movableObject);
        }
        return stepableCell;
    }

    /**
     * Parses the line into cell.
     *
     * @param level
     *            - the level where the Cell is located.
     * @param scanner
     *            - scanner with the serialised cell.
     *
     * @return the serialised {@link Fire} as a type of {@link StepableCell}.
     */
    private static StepableCell parseFireEntry(Level level, Scanner scanner) {
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String imagePath = scanner.next();

        MovableObject movableObject = null;
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
            } else if (type.equals(Constants.TYPE_SMART_TARGETING_ENEMY)) {
                movableObject = parseSmartTargetingEnemyEntry(level, scanner);
            }
        }

        StepableCell stepableCell = new Fire(level, positionX, positionY,
                imagePath);

        if (movableObject != null) {
            stepableCell.stepOn(movableObject);
        }
        return stepableCell;
    }

    /**
     * Parses the line into cell.
     *
     * @param level
     *            - the level where the Cell is located.
     * @param scanner
     *            - scanner with the serialised Cell.
     *
     * @return the serialised {@link Key Door} as a type of
     *         {@link StepableCell}.
     */
    private static StepableCell parseKeyDoorEntry(Level level,
            Scanner scanner) {
        String title = scanner.next();
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String lockedImagePath = scanner.next();
        String unlockedImagePath = scanner.next();
        KeyType unlockingKey = getKeyType(scanner.nextInt());

        MovableObject movableObject = null;
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
            } else if (type.equals(Constants.TYPE_SMART_TARGETING_ENEMY)) {
                movableObject = parseSmartTargetingEnemyEntry(level, scanner);
            }
        }

        StepableCell stepableCell = new KeyDoor(level, title, positionX,
                positionY, lockedImagePath, unlockedImagePath, unlockingKey);

        if (movableObject != null) {
            stepableCell.stepOn(movableObject);
        }
        return stepableCell;
    }

    /**
     * Parses the line into cell.
     *
     * @param level
     *            - the level where the Cell is located.
     * @param scanner
     *            - scanner with the serialised Cell.
     *
     * @return the serialised {@link Token Door} as a type of
     *         {@link StepableCell}.
     */
    private static StepableCell parseTokenDoorEntry(Level level,
            Scanner scanner) {
        String title = scanner.next();
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String lockedImagePath = scanner.next();
        String unlockedImagePath = scanner.next();
        int tokensNeeded = scanner.nextInt();

        MovableObject movableObject = null;
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
            } else if (type.equals(Constants.TYPE_SMART_TARGETING_ENEMY)) {
                movableObject = parseSmartTargetingEnemyEntry(level, scanner);
            }
        }

        StepableCell stepableCell = new TokenDoor(level, title, positionX,
                positionY, lockedImagePath, unlockedImagePath, tokensNeeded);

        if (movableObject != null) {
            stepableCell.stepOn(movableObject);
        }
        return stepableCell;
    }

    /**
     * Parses the line into cell.
     *
     * @param level
     *            - the level where the Cell is located.
     * @param scanner
     *            - scanner with the serialised cell.
     *
     * @return the serialised {@link Goal} as a type of {@link StepableCell}.
     */
    private static StepableCell parseGoalEntry(Level level, Scanner scanner) {
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String imagePath = scanner.next();

        MovableObject movableObject = null;
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
            } else if (type.equals(Constants.TYPE_SMART_TARGETING_ENEMY)) {
                movableObject = parseSmartTargetingEnemyEntry(level, scanner);
            }
        }

        StepableCell stepableCell = new Goal(level, positionX, positionY,
                imagePath);

        if (movableObject != null) {
            stepableCell.stepOn(movableObject);
        }
        return stepableCell;
    }

    /**
     * Parses the line into cell.
     *
     * @param level
     *            - the level where the StepableCell is located.
     * @param scanner
     *            - scanner with the serialised cell.
     *
     * @return the serialised {@link Teleporter} as a type of
     *         {@link StepableCell}.
     */
    private static StepableCell parseTeleporterEntry(Level level,
            Scanner scanner) {
        String title = scanner.next();
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String imagePath = scanner.next();

        MovableObject movableObject = null;
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
            } else if (type.equals(Constants.TYPE_SMART_TARGETING_ENEMY)) {
                movableObject = parseSmartTargetingEnemyEntry(level, scanner);
            }
        }

        StepableCell stepableCell = new Teleporter(level, title, positionX,
                positionY, imagePath);

        if (movableObject != null) {
            stepableCell.stepOn(movableObject);
        }
        return stepableCell;
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
    private static MovableObject parsePlayerEntry(Level level,
            Scanner scanner) {
        String name = scanner.next();
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        int velocityX = scanner.nextInt();
        int velocityY = scanner.nextInt();
        String imagePath = scanner.next();

        Player player = new Player(level, name, positionX, positionY, velocityX,
                velocityY, imagePath);

        while (scanner.hasNext()) {
            String type = scanner.next();

            if (type.equals(Constants.TYPE_FIRE_BOOTS)) {
                player.addToInventory(parseFireBootsEntry(level, scanner));
            } else if (type.equals(Constants.TYPE_FLIPPERS)) {
                player.addToInventory(parseFlipperEntry(level, scanner));
            } else if (type.equals(Constants.TYPE_KEY)) {
                player.addToInventory(parseKeyEntry(level, scanner));
            } else if (type.equals(Constants.TYPE_TOKEN)) {
                player.addToInventory(parseTokenEntry(level, scanner));
            }
        }

        return player;
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
    private static MovableObject parseDumbTargetingEnemyEntry(Level level,
            Scanner scanner) {
        String name = scanner.next();
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String imagePath = scanner.next();

        return new DumbTargetingEnemy(level, name, positionX, positionY,
                imagePath);
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
    private static MovableObject parseStraightWalkingEnemyEntry(Level level,
            Scanner scanner) {
        String name = scanner.next();
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        int velocityX = scanner.nextInt();
        int velocityY = scanner.nextInt();
        String imagePath = scanner.next();

        return new StraightWalkingEnemy(level, name, positionX, positionY,
                velocityX, velocityY, imagePath);
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
    private static MovableObject parseWallFollowingEnemyEntry(Level level,
            Scanner scanner) {
        String name = scanner.next();
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String imagePath = scanner.next();

        return new WallFollowingEnemy(level, name, positionX, positionY,
                imagePath);
    }

    //
    /**
     * Parses the Smart Targeting Enemy on the scanned line.
     *
     * @param level
     *            - the level where the enemy is located.
     * @param scanner
     *            - scanner with the serialised enemy.
     *
     * @return the serialised {@link SmartTargetingEnemy} as a type of
     *         {@link MovableObject}.
     */
    private static MovableObject parseSmartTargetingEnemyEntry(Level level,
            Scanner scanner) {
        String name = scanner.next();
        int positionX = scanner.nextInt();
        int positionY = scanner.nextInt();
        String imagePath = scanner.next();

        return new SmartTargetingEnemy(level, name, positionX, positionY,
                imagePath);
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
    private static CollectableItem parseFireBootsEntry(Level level,
            Scanner scanner) {
        String imagePath = scanner.next();

        return new FireBoots(level, imagePath);
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
    private static CollectableItem parseFlipperEntry(Level level,
            Scanner scanner) {
        String imagePath = scanner.next();

        return new Flippers(level, imagePath);
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
        KeyType keyType = getKeyType(keyTypeID);

        return new Key(level, keyType);
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
    private static CollectableItem parseTokenEntry(Level level,
            Scanner scanner) {
        String imagePath = scanner.next();

        return new Token(level, imagePath);
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
     * Links teleporters together.
     *
     * @param level
     *            - the level where the item is located.
     * @param scanner
     *            - scanner with the serialised token.
     * @throws ParsingException
     *             when Teleporter is not found.
     */
    private static void parseTeleporterLink(Level level, Scanner scanner)
            throws ParsingException {
        int x1 = scanner.nextInt();
        int y1 = scanner.nextInt();

        Cell cell01 = level.getGrid()[x1][y1];

        if (cell01 instanceof Teleporter == false) {
            throw new ParsingException(
                    String.format(ERROR_MESSAGE_TELEPORTER_NOT_FOUND,
                            Constants.TYPE_TELEPORTER_LINK, x1, y1));
        }

        int x2 = scanner.nextInt();
        int y2 = scanner.nextInt();

        Cell cell02 = level.getGrid()[x2][y2];

        if (cell02 instanceof Teleporter == false) {
            throw new ParsingException(
                    String.format(ERROR_MESSAGE_TELEPORTER_NOT_FOUND,
                            Constants.TYPE_TELEPORTER_LINK, x2, y2));
        }

        Teleporter teleporter01 = (Teleporter) cell01;
        Teleporter teleporter02 = (Teleporter) cell02;

        teleporter01.setLinkedTeleporter(teleporter02);
        teleporter02.setLinkedTeleporter(teleporter01);
    }
}
