package group44;

public final class Constants {
	private Constants() {
	}

	// Data - Folders
	public static final String FOLDER_LEVELS = "source/group44/data/levels/";

	// Data - Files
	public static final String FILE_RECORDS = "source/group44/data/records.txt";
	public static final String FILE_PROFILES = "source/group44/data/profiles.txt";
	public static final String FILE_LEVEL_TEMP_PATTERN = "%d-%d.txt"; // "LEVEL_ID-PROFILE_ID.txt"

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	// The dimensions of the canvas
	public static final int CANVAS_WIDTH = 725;
	public static final int CANVAS_HEIGHT = 550;

	// The size of each cell
	public static int GRID_CELL_WIDTH = 60;
	public static int GRID_CELL_HEIGHT = 60;

	// Drawing constants
	public static int LEVEL_DISPLAY_SIZE = 7; // Must be odd and greater or
												// equal 3

	// Parsing constants
	public static final String TYPE_WALL = "wall";
	public static final String TYPE_GROUND = "ground";
	public static final String TYPE_WATER = "water";
	public static final String TYPE_FIRE = "fire";
	public static final String TYPE_GOAL = "goal";
	public static final String TYPE_PLAYER = "player";
	public static final String TYPE_FIRE_BOOTS = "fireBoots";
	public static final String TYPE_FLIPPERS = "flippers";
	public static final String TYPE_KEY = "key";
	public static final String TYPE_TOKEN = "token";
	public static final String TYPE_DUMB_TARGETING_ENEMY = "dumbTargetingEnemy";
	public static final String TYPE_STRAIGHT_WALKING_ENEMY = "straightWalkingEnemy";
	public static final String TYPE_WALL_FOLLOWING_ENEMY = "wallFollowingEnemy";
	public static final String TYPE_SMART_TARGETING_ENEMY = "smartTargetingEnemy";
	public static final String TYPE_TOKEN_DOOR = "tokenDoor";
	public static final String TYPE_KEY_DOOR = "keyDoor";
	public static final String TYPE_TELEPORTER = "teleporter";
	public static final String TYPE_TELEPORTER_LINK = "teleporterLink";
}
