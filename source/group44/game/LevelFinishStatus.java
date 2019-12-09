package group44.game;

/**
 * Represents a status of the game completion.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public enum LevelFinishStatus {
    /** Player died on SteppableCell. */
    PlayerDied,
    /** Player killed by Enemy. */
    PlayerKilled,
    /** Player reached the goal. */
    GoalReached
}
