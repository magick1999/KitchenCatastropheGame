package test;

import java.io.FileNotFoundException;
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
	private static int DISPLAY_SIZE = 7;
	private static final String LEVELS = "source/group44/data/levels/";

	private static int WIDTH = 20;
	private static int HEIGHT = 20;

	private static String DOOR_KEY_BLUE = "Blue door";
	private static String DOOR_KEY_GOLD = "Gold door";

	private static String PLAYER_NAME = "Tomas";
	private static int PLAYER_VECTOR_X = 0;
	private static int PLAYER_VECTOR_Y = 0;

	private static String PARSE_PATTERN_CELL = "%s,%d,%d,%s";

	private static String PARSE_PATTERN_DOOR_KEY = "%s,%s,%d,%d,%s,%s,%d";
	private static String PARSE_PATTERN_DOOR_TOKEN = "%s,%s,%d,%d,%s";

	private static String PARSE_PATTERN_COLLECTABLE_NOTKEYS = ",%s,%s";
	private static String PARSE_PATTERN_COLLECTABLE_KEY = ",%s,%d";

	private static String PARSE_PATTERN_PLAYER = ",%s,%s,%d,%d,%d,%d,%s";

	private static String BASE_PATH_IMAGE = "group44/resources/";

	private static String PATH_IMAGE_WALL = BASE_PATH_IMAGE + "WallCounter.png";
	private static String PATH_IMAGE_GROUND = BASE_PATH_IMAGE + "cells/floor.png";
	private static String PATH_IMAGE_WATER = BASE_PATH_IMAGE + "cells/water.png";
	private static String PATH_IMAGE_FIRE = BASE_PATH_IMAGE + "cells/fire.png";
	private static String PATH_IMAGE_GOAL = BASE_PATH_IMAGE + "cells/goal.png";

	private static String PATH_IMAGE_DOOR_KEY_BLUE = BASE_PATH_IMAGE + "cells/blueDoor.png";
	private static String PATH_IMAGE_DOOR_KEY_GOLD = BASE_PATH_IMAGE + "cells/goldDoor.png";
	private static String PATH_IMAGE_DOOR_KEY_GREEN = BASE_PATH_IMAGE + "cells/greenDoor.png";

	private static String PATH_IMAGE_COLLECATBLE_FLIPPERS = BASE_PATH_IMAGE + "cells/flippers.png";
	private static String PATH_IMAGE_COLLECATBLE_FIRE_BOOTS = BASE_PATH_IMAGE + "cells/fireBoots.png";

	private static String PATH_IMAGE_PLAYER = BASE_PATH_IMAGE + "ChefDownWalk/Front1.png";

	public static void main(String[] args)
			throws FileNotFoundException, IllegalArgumentException, CollisionException, ParsingException {
		JFXPanel jfxPanel = new JFXPanel();
		generateLevelFile01(LEVELS + "level_001.txt");
		generateLevelFile02(LEVELS + "level_002.txt");

		if (true) {
			LevelManager.load();
			Level level1 = LevelManager.load(1);
			System.out.println("\n" + level1.getId());

			for (LevelInfo info : LevelManager.getLevelInfos()) {
				if (info.getId() == 1) {
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

	}

	private static void generateBorders(PrintWriter writer) {
		// TOP BORDER
		for (int x = 0; x < WIDTH; x++) {
			writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, x, 0, PATH_IMAGE_WALL));
		}

		// BOTTOM BORDER
		for (int x = 0; x < WIDTH; x++) {
			writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, x, HEIGHT - 1, PATH_IMAGE_WALL));
		}

		// LEFT BORDER
		for (int y = 1; y < WIDTH - 1; y++) {
			writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, 0, y, PATH_IMAGE_WALL));
		}

		// RIGHT BORDER
		for (int y = 1; y < WIDTH - 1; y++) {
			writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, HEIGHT - 1, y, PATH_IMAGE_WALL));
		}
	}

	public static void generateLevelFile01(String path) throws FileNotFoundException {
		int id = 1;
		int WIDTH = 20;
		int HEIGHT = 20;

		PrintWriter writer = new PrintWriter(path);

		writer.println(String.format("%d,%d,%d", id, WIDTH, HEIGHT));
		generateBorders(writer);

		// GROUND [1,1]-[3,18]
		for (int x = 1; x < 4; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {
				writer.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND)); // GROUND

				if (x == 1 && y == 1) {
					// ADD PLAYER
					writer.println(String.format(PARSE_PATTERN_PLAYER, Constants.TYPE_PLAYER, PLAYER_NAME, x, y,
							PLAYER_VECTOR_X, PLAYER_VECTOR_Y, PATH_IMAGE_PLAYER));
					continue;
				}
				writer.println(); // add NEW LINE
			}
		}

		// WATER,FIRE,WATER
		for (int x = 4; x < 7; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {
				if (y == 9) {
					writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
					continue;
				}
				if (x == 5) {
					writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_FIRE, x, y, PATH_IMAGE_FIRE));
				} else {
					writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WATER, x, y, PATH_IMAGE_WATER));
				}
			}
		}

		// GROUND [7,1]-[18,18]
		for (int x = 7; x < WIDTH - 1; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {
				// GOAL
				if (x == 18 && y == 18) {
					writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GOAL, x, y, PATH_IMAGE_GOAL));
					continue;
				}
				writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
			}
		}

		writer.close();
	}

	public static void generateLevelFile02(String path) throws FileNotFoundException {
		int id = 2;

		PrintWriter writer = new PrintWriter(path);

		writer.println(String.format("%d,%d,%d", id, WIDTH, HEIGHT));
		generateBorders(writer);

		// GROUND [1,1]-[3,18]
		for (int x = 1; x < 4; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {
				writer.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND)); // GROUND

				if (x == 1 && y == 1) {
					// ADD PLAYER
					writer.println(String.format(PARSE_PATTERN_PLAYER, Constants.TYPE_PLAYER, PLAYER_NAME, x, y,
							PLAYER_VECTOR_X, PLAYER_VECTOR_Y, PATH_IMAGE_PLAYER));
					continue;
				} else if (x == 1 && y == 3) {
					writer.println(String.format(PARSE_PATTERN_COLLECTABLE_KEY, Constants.TYPE_KEY, KeyType.BLUE.getKeyCode()));
					continue;
				} else if (x == 1 && y == 4) {
					writer.println(String.format(PARSE_PATTERN_COLLECTABLE_KEY, Constants.TYPE_KEY, KeyType.GOLD.getKeyCode()));
					continue;
				} else if (x == 1 && y == 18) {
					writer.println(String.format(PARSE_PATTERN_COLLECTABLE_NOTKEYS, Constants.TYPE_FLIPPERS,
							PATH_IMAGE_COLLECATBLE_FLIPPERS));
					continue;
				} else if (x == 3 && y == 18) {
					writer.println(String.format(PARSE_PATTERN_COLLECTABLE_NOTKEYS, Constants.TYPE_FIRE_BOOTS,
							PATH_IMAGE_COLLECATBLE_FIRE_BOOTS));
					continue;
				}
				writer.println(); // add NEW LINE
			}
		}

		// WATER,FIRE,WATER
		for (int x = 4; x < 7; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {
				if (x == 4 && y == 9) {
					// BLUE DOOR
					// PARSE_PATTERN_DOOR_KEY = "%s,%s,%d,%d,%s,%s,%d";
					writer.println(String.format(PARSE_PATTERN_DOOR_KEY, Constants.TYPE_KEY_DOOR, DOOR_KEY_BLUE, x, y, PATH_IMAGE_DOOR_KEY_BLUE, PATH_IMAGE_GROUND, KeyType.BLUE.getKeyCode()));
					continue;
				} else if (x == 5 && y == 9) {
					// BLUE DOOR
					writer.println(String.format(PARSE_PATTERN_DOOR_KEY, Constants.TYPE_KEY_DOOR, DOOR_KEY_GOLD, x, y, PATH_IMAGE_DOOR_KEY_GOLD, PATH_IMAGE_GROUND, KeyType.GOLD.getKeyCode()));
					continue;
				} else if (y == 9) {
					writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
					continue;
				}

				if (x == 5) {
					writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_FIRE, x, y, PATH_IMAGE_FIRE));
				} else {
					writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WATER, x, y, PATH_IMAGE_WATER));
				}
			}
		}

		// GROUND [7,1]-[18,18]
		for (int x = 7; x < WIDTH - 1; x++) {
			for (int y = 1; y < HEIGHT - 1; y++) {
				// GOAL
				if (x == 18 && y == 18) {
					writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GOAL, x, y, PATH_IMAGE_GOAL));
					continue;
				}
				writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
			}
		}

		writer.close();
	}
}
