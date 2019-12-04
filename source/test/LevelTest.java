package test;

import group44.Constants;
import group44.game.Level;

public class LevelTest {
	private static int DISPLAY_SIZE = 7;

	private static String PLAYER_NAME = "Tomas";
	private static int PLAYER_VECTOR_X = 0;
	private static int PLAYER_VECTOR_Y = 0;

	private static String PARSE_PATTERN_CELL = "%s,%d,%d,%s";
	private static String PARSE_PATTERN_PLAYER = "%s,%s,%d,%d,%d,%d,%s";

	private static String PATH_IMAGE_WALL = "source/group44/resources/WallCounter.png";
	private static String PATH_IMAGE_GROUND = "source/group44/resources/cells/floor.png";
	private static String PATH_IMAGE_WATER = "source/group44/resources/cells/water.png";
	private static String PATH_IMAGE_FIRE = "source/group44/resources/cells/fire.png";
	private static String PATH_IMAGE_GOAL = "source/group44/resources/cells/goal.png";
	private static String PATH_IMAGE_PLAYER = "source/group44/resources/ChefDownWalk/Front1.png";

	public static void main(String[] args) {
		generateLevelFile01();
	}

	public static void createLevel(int id, int width, int height) {
		Level level = new Level(id, width, height, DISPLAY_SIZE);
	}

	public static void generateLevelFile01() {
		int id = 1;
		int width = 20;
		int height = 20;

		System.out.println(String.format("%d,%d,%d", id, width, height));

		// TOP BORDER
		for (int x = 0; x < width; x++) {
			System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, x, 0, PATH_IMAGE_WALL));
		}

		// BOTTOM BORDER
		for (int x = 0; x < width; x++) {
			System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, x, height - 1, PATH_IMAGE_WALL));
		}

		// LEFT BORDER
		for (int y = 1; y < height - 1; y++) {
			System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, 0, y, PATH_IMAGE_WALL));
		}

		// RIGHT BORDER
		for (int y = 1; y < height - 1; y++) {
			System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, width - 1, y, PATH_IMAGE_WALL));
		}

		// GROUND [1,1]-[3,18]
		for (int x = 1; x < 4; x++) {
			for (int y = 1; y < height - 2; y++) {
				if (x == 1 && y == 1) {
					System.out.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
					System.out.println(String.format("," + PARSE_PATTERN_PLAYER, Constants.TYPE_PLAYER, PLAYER_NAME, x, y,
							PLAYER_VECTOR_X, PLAYER_VECTOR_Y, PATH_IMAGE_PLAYER));
					continue;
				}
				System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
			}
		}

		// WATER,FIRE,WATER
		for (int x = 4; x < 7; x++) {
			for (int y = 1; y < height - 2; y++) {
				if (y == 9) {
					System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
				}
				if (x == 5) {
					System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_FIRE, x, y, PATH_IMAGE_FIRE));
				} else {
					System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WATER, x, y, PATH_IMAGE_WATER));
				}
			}
		}

		// GROUND [7,1]-[18,18]
		for (int x = 7; x < width - 1; x++) {
			for (int y = 1; y < height - 1; y++) {
				// GOAL
				if (x == 18 && y == 18) {
					System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GOAL, x, y, PATH_IMAGE_GOAL));
					continue;
				}
				System.out.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
			}
		}
	}
}
