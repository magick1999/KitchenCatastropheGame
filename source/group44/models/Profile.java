package group44.models;

import group44.controllers.Leaderboard;

/**
 * Represents a user profile.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Profile {
    private int id;
    private String username;

    /**
     * Creates a new instance of {@link Profile}.
     *
     * @param id       - Id of the new profile
     * @param username - the username
     */
    public Profile(int id, String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * Returns the {@link Profile} id.
     *
     * @return the profile id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the username of the {@link Profile}.
     *
     * @return username of the profile
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the Id of the level the user achieved.
     *
     * @return the level id
     */
    public int getAchievedLevel() {
        return Leaderboard.getAchievedLevel(this.getId());
    }

    /**
     * Returns a string representation of the profile.
     *
     * @return {@link Profile} converted to {@link String}
     */
    @Override
    public String toString() {
        return this.getId() + "," + this.getUsername();
    }
}