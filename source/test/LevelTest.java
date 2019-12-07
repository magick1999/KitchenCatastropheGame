package test;

import java.awt.PrintGraphics;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import group44.Constants;
import group44.controllers.LevelManager;
import group44.entities.collectableItems.Key.KeyType;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.game.Level;
import group44.models.LevelInfo;
import javafx.embed.swing.JFXPanel;

public class LevelTest {
	private static final String LEVELS = "source/group44/data/levels/";

	private static String FILE_HEADER_PATTERN = "%d,%d,%d,%d";
	private static int WIDTH = 20;
	private static int HEIGHT = 20;
	private static int TIME = 0;

	private static String DOOR_KEY_BLUE = "Blue door";
	private static String DOOR_KEY_GOLD = "Gold door";
	private static String DOOR_KEY_GREEN = "Green door";
	private static String DOOR_KEY_RED = "Red door";
	private static String DOOR_KEY_TOKEN = "Token door";

	private static String ENEMY_NAME_SMART_TARGETING = "Smart targeting enemy";
	private static String ENEMY_NAME_WALL_FOLLOWING = "Wall following enemy";
	private static String ENEMY_NAME_STRAIGHT_WALKING = "Straight walking enemy";
	private static String ENEMY_NAME_DUMB_TARGETING = "Dumb targeting enemy";

	private static String PLAYER_NAME = "Tomas";
	private static int PLAYER_VECTOR_X = 0;
	private static int PLAYER_VECTOR_Y = 0;

	private static String TELEPORTER_NAME = "Teleporter";

	private static String PARSE_PATTERN_CELL = "%s,%d,%d,%s";
	private static String PARSE_PATTERN_CELL_TITLE = "%s,%s,%d,%d,%s";
	private static String PARSE_PATTERN_TELEPORTER_LINK = "%s,%d,%d,%d,%d";

	private static String PARSE_PATTERN_DOOR = "%s,%s,%d,%d,%s,%s,%d";

	private static String PARSE_PATTERN_COLLECTABLE_NOTKEYS = ",%s,%s";
	private static String PARSE_PATTERN_COLLECTABLE_KEY = ",%s,%d";

	private static String PARSE_PATTERN_PLAYER = ",%s,%s,%d,%d,%d,%d,%s";
	private static String PARSE_PATTERN_ENEMY = ",%s,%s,%d,%d,%s";
	private static String PARSE_PATTERN_ENEMY_VELOCITY = ",%s,%s,%d,%d,%d,%d,%s";

	private static String BASE_PATH_IMAGE = "group44/resources/";

	private static String PATH_IMAGE_WALL = BASE_PATH_IMAGE + "WallCounter.png";
	private static String PATH_IMAGE_GROUND = BASE_PATH_IMAGE + "cells/floor.png";
	private static String PATH_IMAGE_WATER = BASE_PATH_IMAGE + "cells/water.png";
	private static String PATH_IMAGE_FIRE = BASE_PATH_IMAGE + "cells/fire.png";
	private static String PATH_IMAGE_GOAL = BASE_PATH_IMAGE + "cells/goal.png";
	private static String PATH_IMAGE_TELEPORTER = BASE_PATH_IMAGE + "cells/teleporter.png";

	private static String PATH_IMAGE_DOOR_KEY_BLUE = BASE_PATH_IMAGE + "cells/blueDoor.png";
	private static String PATH_IMAGE_DOOR_KEY_GOLD = BASE_PATH_IMAGE + "cells/goldDoor.png";
	private static String PATH_IMAGE_DOOR_KEY_GREEN = BASE_PATH_IMAGE + "cells/greenDoor.png";
	private static String PATH_IMAGE_DOOR_KEY_RED = BASE_PATH_IMAGE + "cells/redDoor.png";

	private static String PATH_IMAGE_DOOR_TOKEN = BASE_PATH_IMAGE + "cells/tokenDoor.png";

	private static String PATH_IMAGE_COLLECATBLE_FLIPPERS = BASE_PATH_IMAGE + "cells/flippers.png";
	private static String PATH_IMAGE_COLLECATBLE_FIRE_BOOTS = BASE_PATH_IMAGE + "cells/fireBoots.png";
	private static String PATH_IMAGE_COLLECATBLE_TOKEN = BASE_PATH_IMAGE + "cells/token.png";

	private static String PATH_IMAGE_PLAYER = BASE_PATH_IMAGE + "ChefDownWalk/Front1.png";

	private static String PATH_IMAGE_SMART_TARGETING_ENEMY = BASE_PATH_IMAGE + "Enemies/Egg/mrEggFront.png";
	private static String PATH_IMAGE_WALL_FOLLOWING_ENEMY = BASE_PATH_IMAGE + "Enemies/Hotdog/mrHotDogFront.png";
	private static String PATH_IMAGE_STRAIGHT_WALKING_ENEMY = BASE_PATH_IMAGE + "Enemies/Carrot/mrCarrotFront.png";
	private static String PATH_IMAGE_DUMB_TARGETTING_ENEMY = BASE_PATH_IMAGE + "Enemies/Pickle/mrPickleFront.png";

	public static void main(String[] args)
			throws FileNotFoundException, IllegalArgumentException, CollisionException, ParsingException {
		JFXPanel jfxPanel = new JFXPanel();

		generateLevelFile01(LEVELS + "level_001.txt", 11, 11, 0);
		generateLevelFile02(LEVELS + "level_002.txt", 0);
		generateLevelFile03(LEVELS + "level_003.txt", 0);
		generateLevelFile04(LEVELS + "level_004.txt", 0);

		System.out.println("Levels generated");

		if (true) {
			for (LevelInfo info : LevelManager.getLevelInfos()) {
				try {
					Level level = LevelManager.load(info);
					System.out.println(level.getId());
				} catch (FileNotFoundException | CollisionException | ParsingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void generateBorders(PrintWriter writer, int width, int height) {
		// TOP BORDER
		for (int x = 0; x < width; x++) {
			printWall(writer, x, 0);
			writer.println();
		}

		// BOTTOM BORDER
		for (int x = 0; x < width; x++) {
			printWall(writer, x, height - 1);
			writer.println();
		}

		// LEFT BORDER
		for (int y = 1; y < width - 1; y++) {
			printWall(writer, 0, y);
			writer.println();
		}

		// RIGHT BORDER
		for (int y = 1; y < width - 1; y++) {
			printWall(writer, height - 1, y);
			writer.println();
		}
	}

	public static void generateLevelFile01(String path, int width, int height, int time) throws FileNotFoundException {
		int id = 1;

		PrintWriter writer = new PrintWriter(path);

		writer.println(String.format(FILE_HEADER_PATTERN, id, width, height, time));
		generateBorders(writer, width, height);

		for (int x = 1; x < width - 1; x++) {
			for (int y = 1; y < height - 1; y++) {
				if (x == 1 && y == 1) {
					printPlayer(writer, x, y);
				} else if ((x == 4 || x == 6) && height / 2 != y) {
					printWater(writer, x, y);
				} else if ((x == 5) && height / 2 != y) {
					printFire(writer, x, y);
				} else if (x == 9 && y == 9) {
					printGoal(writer, x, y);
				} else {
					printGround(writer, x, y);
				}

				writer.println(); // add NEW LINE
			}
		}

		writer.close();
	}

	public static void generateLevelFile02(String path, int time) throws FileNotFoundException {
		int id = 2;

		PrintWriter writer = new PrintWriter(path);

		writer.println(String.format(FILE_HEADER_PATTERN, id, WIDTH, HEIGHT, TIME));
		generateBorders(writer, WIDTH, HEIGHT);

		for (int x = 1; x < WIDTH - 1; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {

				if (x == 1 && y == 9) {
					printBlueKey(writer, x, y); // Ground + BlueKey
				} else if (x == 1 && y == 18) {
					printPlayer(writer, x, y); // Ground + Player
				} else if (x == 3 && y == 7) {
					printGoldKey(writer, x, y); // Ground + BlueKey
				} else if (x == 4 && y == 9) {
					printBlueKeyDoor(writer, x, y); // Blue Key Door
				} else if ((x == 4 || x == 6) && y != 9) {
					printWater(writer, x, y); // Water
				} else if (x == 5 && y == 6) {
					printToken(writer, x, y); // Token
				} else if (x == 5 && y == 9) {
					printGoldKeyDoor(writer, x, y); // Gold Key Door
				} else if (x == 5 && y != 9) {
					printFire(writer, x, y); // Fire
				} else if (x == 6 && y == 9) {
					printToken(writer, x, y); // Token
				} else if (x == 7 && y == 1) {
					printToken(writer, x, y); // Ground + Token
				} else if (x == 7 && y == 10) {
					printToken(writer, x, y); // Ground + Token
				} else if ((x == 8 || x == 9 || x == 10) && y != 9) {
					printWall(writer, x, y); // Wall
				} else if (x == 8 && y == 9) {
					printTokenDoor(writer, x, y, 2);
				} else if (x == 9 && y == 9) {
					printFireBoots(writer, x, y);
				} else if (x == 10 && y == 9) {
					printTokenDoor(writer, x, y, 2);
				} else if (x == 11 && y == 1) {
					printRedKey(writer, x, y);
				} else if (x == 12 && y >= 3 && y <= 16) {
					printWall(writer, x, y);
				} else if (x >= 13 && x <= 15 && y >= 3 && y <= 4) {
					printWall(writer, x, y);
				} else if (x == 13 && y == 5) {
					printGreenKey(writer, x, y);
				} else if (x == 14 && y == 1) {
					printFlippers(writer, x, y);
				} else if (x == 15 && y == 5) {
					printFire(writer, x, y);
				} else if (x == 15 && y >= 6 && y <= 18) {
					printWall(writer, x, y);
				} else if (x == 16 && y == 14) {
					printRedKeyDoor(writer, x, y);
				} else if (x >= 16 && x <= 18 && y == 15) {
					printWater(writer, x, y);
				} else if (x == 17 && y >= 12 && y <= 18 && y != 15) {
					printWall(writer, x, y);
				} else if (x == 18 && y == 12) {
					printWall(writer, x, y);
				} else if (x == 18 && y == 13) {
					printToken(writer, x, y);
				} else if (x == 18 && y == 14) {
					printGreenKeyDoor(writer, x, y);
				} else if (x == 18 && y == 16) {
					printTokenDoor(writer, x, y, 1);
				} else if (x == 18 && y == 18) {
					printGoal(writer, x, y);
				} else {
					printGround(writer, x, y); // GROUND
				}
				writer.println(); // add NEW LINE
			}
		}

		writer.close();
	}

	public static void generateLevelFile03(String path, int time) throws FileNotFoundException {
		int id = 3;

		PrintWriter writer = new PrintWriter(path);

		writer.println(String.format(FILE_HEADER_PATTERN, id, WIDTH, HEIGHT, time));
		generateBorders(writer, WIDTH, HEIGHT);

		// GROUND [1,1]-[3,18]
		for (int x = 1; x < WIDTH - 1; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {

				if (x == 2 && y == 9) {
					printPlayer(writer, x, y);
				} else if ((x == 4 || x == 6) && y != 9) {
					printWater(writer, x, y); // Water
				} else if (x == 5 && y != 9) {
					printFire(writer, x, y); // Fire
				} else if (x == 7 && y == 1) {
					printStraightWalkingEnemy(writer, x, y, 0, 1);
				} else if (x == 7 && y == 11) {
					printToken(writer, x, y); // Ground + Token
				} else if (x == 8 && y == 9) {
					printToken(writer, x, y); // Ground + Token
				} else if (x == 10 && y == 9) {
					printTokenDoor(writer, x, y, 2);
				} else if ((x >= 8 && x <= 10) && y != 9) {
					printWall(writer, x, y); // Wall
				} else if (x == 11 && y == 1) {
					printRedKey(writer, x, y);
				} else if (x == 11 && y == 18) {
					printDumbTargetingEnemy(writer, x, y);
				} else if (x >= 11 && x <= 12 && y == 10) {
					printWall(writer, x, y);
				} else if (x >= 13 && x <= 14 && y == 3) {
					printWall(writer, x, y);
				} else if (x == 14 && y == 1) {
					printFlippers(writer, x, y);
				} else if (x == 14 && y == 9) {
					printGreenKey(writer, x, y);
				} else if (x == 15 && y >= 3 && y <= 4) {
					printWall(writer, x, y);
				} else if (x == 15 && y >= 5 && y <= 7) {
					printWater(writer, x, y);
				} else if (x == 15 && y >= 7 && y <= 18) {
					printWall(writer, x, y);
				} else if (x == 16 && y == 12) {
					printRedKeyDoor(writer, x, y);
				} else if (x == 16 && y == 14) {
					printToken(writer, x, y);
				} else if (x >= 16 && x <= 18 && y == 15) {
					printWater(writer, x, y);
				} else if (x == 16 && y >= 16 && y <= 18) {
					printWall(writer, x, y);
				} else if (x == 18 && y >= 12 && y <= 14) {
					printWall(writer, x, y);
				} else if (x == 17 && y >= 12 && y <= 18 && y != 15) {
					printWall(writer, x, y);
				} else if (x == 18 && y == 1) {
					printWallFollowingEnemy(writer, x, y);

				} else if (x == 18 && y == 16) {
					printTokenDoor(writer, x, y, 1);
				} else if (x == 18 && y == 18) {
					printGoal(writer, x, y);
				} else {
					printGround(writer, x, y); // GROUND
				}
				writer.println(); // add NEW LINE
			}
		}

		writer.close();
	}

	public static void generateLevelFile04(String path, int time) throws FileNotFoundException {
		int id = 4;
		PrintWriter writer = new PrintWriter(path);

		writer.println(String.format(FILE_HEADER_PATTERN, id, WIDTH, HEIGHT, time));
		generateBorders(writer, WIDTH, HEIGHT);

		// GROUND [1,1]-[3,18]
		for (int x = 1; x < WIDTH - 1; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {

				if (x == 1 && y == 18) {
					printPlayer(writer, x, y); // Ground + Player
				} else if ((x == 4 || x == 6) && (y != 9 || y != 3)) {
					printWater(writer, x, y); // Water
				} else if (x == 5 && y != 9) {
					printFire(writer, x, y); // Fire
				} else if (x == 7 && y == 16) {
					printTeleporter(writer, x, y);
				} else if ((x >= 8 && x <= 10) && y != 9) {
					printWall(writer, x, y); // Wall
				} else if ((x >= 11 && x <= 14) && (y == 2 || y == 8)) {
					printWall(writer, x, y);
				} else if (x == 11 && y == 7) {
					printTeleporter(writer, x, y);
				} else if (x == 12 && y >= 10 && y <= 17) {
					printWall(writer, x, y);
				} else if ((x == 12 || x == 14) && y == 5) {
					printWall(writer, x, y);
				} else if (x == 13 && y == 5) {
					printFire(writer, x, y);
				} else if (x >= 13 && x <= 16 && y == 10) {
					printWall(writer, x, y);
				} else if (x == 17 && y >= 2 && y <= 18) {
					printWall(writer, x, y);
				} else if (x == 18 && y == 16) {
					printSmartTargetingEnemy(writer, x, y);
				} else if (x == 18 && y == 18) {
					printGoal(writer, x, y);
				} else {
					printGround(writer, x, y); // GROUND
				}
				writer.println(); // add NEW LINE
			}
		}

		printTeleportersLink(writer, 7, 16, 11, 7);

		writer.close();
	}

	// Non steppable cells
	private static void printWall(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, x, y, PATH_IMAGE_WALL));
	}

	// Steppable cells
	private static void printGround(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
	}

	private static void printWater(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WATER, x, y, PATH_IMAGE_WATER));
	}

	private static void printFire(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_FIRE, x, y, PATH_IMAGE_FIRE));
	}

	private static void printGoal(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GOAL, x, y, PATH_IMAGE_GOAL));
	}

	private static void printBlueKeyDoor(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_DOOR, Constants.TYPE_KEY_DOOR, DOOR_KEY_BLUE, x, y,
				PATH_IMAGE_DOOR_KEY_BLUE, PATH_IMAGE_GROUND, KeyType.BLUE.getKeyCode()));
	}

	private static void printGoldKeyDoor(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_DOOR, Constants.TYPE_KEY_DOOR, DOOR_KEY_GOLD, x, y,
				PATH_IMAGE_DOOR_KEY_GOLD, PATH_IMAGE_GROUND, KeyType.GOLD.getKeyCode()));
	}

	private static void printGreenKeyDoor(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_DOOR, Constants.TYPE_KEY_DOOR, DOOR_KEY_GREEN, x, y,
				PATH_IMAGE_DOOR_KEY_GREEN, PATH_IMAGE_GROUND, KeyType.GREEN.getKeyCode()));
	}

	private static void printRedKeyDoor(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_DOOR, Constants.TYPE_KEY_DOOR, DOOR_KEY_RED, x, y,
				PATH_IMAGE_DOOR_KEY_RED, PATH_IMAGE_GROUND, KeyType.RED.getKeyCode()));
	}

	private static void printTokenDoor(PrintWriter writer, int x, int y, int tokens) {
		writer.print(String.format(PARSE_PATTERN_DOOR, Constants.TYPE_TOKEN_DOOR, DOOR_KEY_TOKEN, x, y,
				PATH_IMAGE_DOOR_TOKEN, PATH_IMAGE_GROUND, tokens));
	}

	private static void printTeleporter(PrintWriter writer, int x, int y) {
		writer.print(String.format(PARSE_PATTERN_CELL_TITLE, Constants.TYPE_TELEPORTER, TELEPORTER_NAME, x, y,
				PATH_IMAGE_TELEPORTER));
	}

	// Collectable Items
	private static void printBlueKey(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_COLLECTABLE_KEY, Constants.TYPE_KEY, KeyType.BLUE.getKeyCode()));
	}

	private static void printGoldKey(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_COLLECTABLE_KEY, Constants.TYPE_KEY, KeyType.GOLD.getKeyCode()));
	}

	private static void printGreenKey(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_COLLECTABLE_KEY, Constants.TYPE_KEY, KeyType.GREEN.getKeyCode()));
	}

	private static void printRedKey(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_COLLECTABLE_KEY, Constants.TYPE_KEY, KeyType.RED.getKeyCode()));
	}

	private static void printToken(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(
				String.format(PARSE_PATTERN_COLLECTABLE_NOTKEYS, Constants.TYPE_TOKEN, PATH_IMAGE_COLLECATBLE_TOKEN));
	}

	// Movable Objects

	private static void printFireBoots(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_COLLECTABLE_NOTKEYS, Constants.TYPE_FIRE_BOOTS,
				PATH_IMAGE_COLLECATBLE_FIRE_BOOTS));
	}

	private static void printFlippers(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_COLLECTABLE_NOTKEYS, Constants.TYPE_FLIPPERS,
				PATH_IMAGE_COLLECATBLE_FLIPPERS));
	}

	// Movable objects
	private static void printWallFollowingEnemy(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_ENEMY, Constants.TYPE_WALL_FOLLOWING_ENEMY, ENEMY_NAME_WALL_FOLLOWING,
				x, y, PATH_IMAGE_WALL_FOLLOWING_ENEMY));
	}

	// ENEMY_NAME_SMART_TARGETING
	private static void printSmartTargetingEnemy(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_ENEMY, Constants.TYPE_SMART_TARGETING_ENEMY,
				ENEMY_NAME_SMART_TARGETING, x, y, PATH_IMAGE_SMART_TARGETING_ENEMY));
	}

	private static void printStraightWalkingEnemy(PrintWriter writer, int x, int y, int vx, int vy) {
		// ",%s,%s,%d,%d,%d,%d,%s"
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_ENEMY_VELOCITY, Constants.TYPE_STRAIGHT_WALKING_ENEMY,
				ENEMY_NAME_STRAIGHT_WALKING, x, y, vx, vy, PATH_IMAGE_STRAIGHT_WALKING_ENEMY));
	}

	private static void printDumbTargetingEnemy(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_ENEMY, Constants.TYPE_DUMB_TARGETING_ENEMY, ENEMY_NAME_DUMB_TARGETING,
				x, y, PATH_IMAGE_DUMB_TARGETTING_ENEMY));
	}

	private static void printPlayer(PrintWriter writer, int x, int y) {
		printGround(writer, x, y);
		writer.print(String.format(PARSE_PATTERN_PLAYER, Constants.TYPE_PLAYER, PLAYER_NAME, x, y, PLAYER_VECTOR_X,
				PLAYER_VECTOR_Y, PATH_IMAGE_PLAYER));
	}

	// Others
	private static void printTeleportersLink(PrintWriter writer, int x1, int y1, int x2, int y2) {
		writer.print(String.format(PARSE_PATTERN_TELEPORTER_LINK, Constants.TYPE_TELEPORTER_LINK, x1, y1, x2, y2));
	}
}
