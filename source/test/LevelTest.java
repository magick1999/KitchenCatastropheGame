package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import group44.Constants;
import group44.controllers.LevelManager;
import group44.controllers.parsers.LevelParser;
import group44.entities.cells.Cell;
import group44.exceptions.CollisionException;
import group44.exceptions.ParsingException;
import group44.game.Level;
import group44.models.LevelInfo;
import javafx.embed.swing.JFXPanel;

public class LevelTest {
	private static int DISPLAY_SIZE = 7;
	private static final String LEVELS = "source/group44/data/levels/";

	private static String PLAYER_NAME = "Tomas";
	private static int PLAYER_VECTOR_X = 0;
	private static int PLAYER_VECTOR_Y = 0;

	private static String PARSE_PATTERN_CELL = "%s,%d,%d,%s";
	private static String PARSE_PATTERN_PLAYER = "%s,%s,%d,%d,%d,%d,%s";

	private static String BASE_PATH_IMAGE = "source/group44/resources/";

	private static String PATH_IMAGE_WALL = BASE_PATH_IMAGE + "WallCounter.png";
	private static String PATH_IMAGE_GROUND = BASE_PATH_IMAGE + "cells/floor.png";
	private static String PATH_IMAGE_WATER = BASE_PATH_IMAGE + "cells/water.png";
	private static String PATH_IMAGE_FIRE = BASE_PATH_IMAGE + "cells/fire.png";
	private static String PATH_IMAGE_GOAL = BASE_PATH_IMAGE + "cells/goal.png";
	private static String PATH_IMAGE_PLAYER = BASE_PATH_IMAGE + "ChefDownWalk/Front1.png";

	public static void main(String[] args) throws FileNotFoundException {
		JFXPanel jfxPanel = new JFXPanel();
		generateLevelFile01("source/group44/data/levels/level_001.txt");

		//File f = new File(PATH_IMAGE_GROUND);
		//System.out.println(f.exists());
		//System.out.println(f.getAbsolutePath());

		if (true) {
			LevelManager.load(LEVELS);

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

	public static void createLevel(int id, int width, int height) {
		Level level = new Level(id, width, height, DISPLAY_SIZE);
	}

	public static void generateLevelFile01(String path) throws FileNotFoundException {
		int id = 1;
		int width = 20;
		int height = 20;

		PrintWriter writer = new PrintWriter(path);

		writer.println(String.format("%d,%d,%d", id, width, height));

		// TOP BORDER
		for (int x = 0; x < width; x++) {
			writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, x, 0, PATH_IMAGE_WALL));
		}

		// BOTTOM BORDER
		for (int x = 0; x < width; x++) {
			writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, x, height - 1, PATH_IMAGE_WALL));
		}

		// LEFT BORDER
		for (int y = 1; y < height - 1; y++) {
			writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, 0, y, PATH_IMAGE_WALL));
		}

		// RIGHT BORDER
		for (int y = 1; y < height - 1; y++) {
			writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_WALL, width - 1, y, PATH_IMAGE_WALL));
		}

		// GROUND [1,1]-[3,18]
		for (int x = 1; x < 4; x++) {
			for (int y = 1; y < height - 2; y++) {
				if (x == 1 && y == 1) {
					writer.print(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
					writer.println(String.format("," + PARSE_PATTERN_PLAYER, Constants.TYPE_PLAYER, PLAYER_NAME, x, y,
							PLAYER_VECTOR_X, PLAYER_VECTOR_Y, PATH_IMAGE_PLAYER));
					continue;
				}
				writer.println(String.format(PARSE_PATTERN_CELL, Constants.TYPE_GROUND, x, y, PATH_IMAGE_GROUND));
			}
		}

		// WATER,FIRE,WATER
		for (int x = 4; x < 7; x++) {
			for (int y = 1; y < height - 2; y++) {
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
		for (int x = 7; x < width - 1; x++) {
			for (int y = 1; y < height - 1; y++) {
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
