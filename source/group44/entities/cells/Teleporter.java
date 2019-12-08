package group44.entities.cells;

import group44.entities.LevelObject;
import group44.entities.movableObjects.MovableObject;
import group44.game.Level;

/**
 * Represents a teleporter in the game. The {@link Teleporter} will teleport the
 * player to some {@link StepableCell} around the linked {@link Teleporter}.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Teleporter extends StepableCell {
    private Teleporter linkedTeleporter;

    /**
     * Creates a new instance of {@link Teleporter} with linked {@link Teleporter}.
     *
     * @param level            - The {@link Level} where the {@link Teleporter} is located.
     * @param title            - The title of the {@link Teleporter}.
     * @param positionX        - The position X in the game.
     * @param positionY        - The position Y in the game.
     * @param imagePath        - Path to the Image representing {@link Teleporter} in the game.
     */
    public Teleporter(Level level, String title, int positionX, int positionY, String imagePath) {
        super(level, title, positionX, positionY, imagePath);
    }

    /**
     * Links the teleporter with another instance of Teleporter.
     *
     * @param linkedTeleporter - the teleporter to link with.
     */
    public void setLinkedTeleporter(Teleporter linkedTeleporter) {
		this.linkedTeleporter = linkedTeleporter;
	}

    /**
     * Teleports the {@link MovableObject} to the linked teleporter.
     *
     * @param object - {@link MovableObject} that stepped on the {@link Teleporter}.
     */
    @Override
    protected void onStepped(MovableObject object) {
        if (this.teleport(object)) {
            this.stepOff();
        }
    }

    /**
     * Teleports the {@link MovableObject} to the linked {@link Teleporter} if there
     * is some.
     *
     * @param object - the {@link MovableObject} to teleport.
     * @return true if the teleportation was successful; false otherwise.
     */
    private Boolean teleport(MovableObject object) {
        if (this.linkedTeleporter != null) {
            LevelObject[][] surroudingArea = this.getSurroundingArea(this.linkedTeleporter);

            for (int x = 0; x < surroudingArea.length; x++) {
                for (int y = 0; y < surroudingArea[0].length; y++) {
                    LevelObject levelObject = surroudingArea[x][y];

                    if (levelObject instanceof Ground && ((Ground) surroudingArea[x][y]).isSteppedOn() == false) {
                    	StepableCell stepableCell = (Ground) surroudingArea[x][y];
                        stepableCell.stepOn(object);
                        object.setPosition(stepableCell.getPositionX(), stepableCell.getPositionY());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the {@link LevelObject}s surrounding the {@link LevelObject} passed
     * as a parameter.
     *
     * @param object - the surrounded {@link LevelObject}.
     * @return an array of {@link LevelObject}s surrounding the object passed as a parameter.
     */
    private LevelObject[][] getSurroundingArea(LevelObject object) {
    	int gridWidth = this.getLevel().getGridWidth();
    	int gridHeight = this.getLevel().getGridHeight();

        LevelObject[][] grid = this.getLevel().getGrid();
        LevelObject[][] area;

        if (object.getPositionX() == 0 && object.getPositionY() == 0) {
            area = new LevelObject[2][2];

            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 2; y++) {
                    area[x][y] = grid[x][y];
                }
            }
        } else if (object.getPositionX() == 0 && object.getPositionY() == gridHeight - 1) {
            area = new LevelObject[2][2];

            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 2; y++) {
                    area[x][y] = grid[x][gridHeight - 2 + y];
                }
            }
        } else if (object.getPositionX() == gridWidth - 1 && object.getPositionY() == gridHeight - 1) {
            area = new LevelObject[2][2];

            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 2; y++) {
                    area[x][y] = grid[gridWidth - 2 + x][gridHeight - 2 + y];
                }
            }
        } else if (object.getPositionX() == gridWidth - 1 && object.getPositionY() == 0) {
            area = new LevelObject[2][2];

            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 2; y++) {
                    area[x][y] = grid[gridWidth - 2 + x][y];
                }
            }
        } else if (object.getPositionX() == 0) {
            area = new LevelObject[2][3];

            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 3; y++) {
                    area[x][y] = grid[x][object.getPositionY() - 1 + y];
                }
            }
        } else if (object.getPositionX() == gridWidth - 1) {
            area = new LevelObject[2][3];

            for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 3; y++) {
                    area[x][y] = grid[object.getPositionX() - 1 + x][object.getPositionY() - 1 + y];
                }
            }
        } else if (object.getPositionY() == 0) {
            area = new LevelObject[3][2];

            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 2; y++) {
                    area[x][y] = grid[object.getPositionX() - 1 + x][object.getPositionY() + y];
                }
            }
        } else if (object.getPositionY() == gridHeight - 1) {
            area = new LevelObject[3][2];

            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 2; y++) {
                    area[x][y] = grid[object.getPositionX() - 1 + x][object.getPositionY() - 1 + y];
                }
            }
        } else {
            area = new LevelObject[3][3];

            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    area[x][y] = grid[object.getPositionX() - 1 + x][object.getPositionY() - 1 + y];
                }
            }
        }

        return area;
    }
}