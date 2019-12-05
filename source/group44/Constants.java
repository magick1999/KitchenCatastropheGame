package group44;

public final class Constants {
    private Constants(){}
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    // The dimensions of the canvas
    public static final int CANVAS_WIDTH = 725;
    public static final int CANVAS_HEIGHT = 550;

    // The size of each cell
    public static int GRID_CELL_WIDTH = 60;
    public static int GRID_CELL_HEIGHT = 60;

    // Drawing constants
    public static int LEVEL_DISPLAY_SIZE = 7; // Must be odd and greater or equal 3

    // Parsing constants
    public static final String TYPE_WALL = "wall";
    public static final String TYPE_GROUND = "ground";
    public static final String TYPE_WATER = "water";
    public static final String TYPE_FIRE = "fire";
    public static final String TYPE_GOAL = "goal";
    public static final String TYPE_PLAYER = "player";
}
