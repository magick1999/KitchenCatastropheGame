package group44.game;

/**
 * Represents a status of the game completion.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public enum LevelFinishStatus {
	PlayerDied, // Player died on SteppableCell
	PlayerKilled, // Player killed by Enemy
	GoalReached // Player reached the goal
}
