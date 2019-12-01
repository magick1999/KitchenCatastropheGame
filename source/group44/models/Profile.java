package group44.models;

/**
 * Represents a user profile.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class Profile {
    private int id;
    private String username;
    private int achievedLevel;

    /**
     * Creates a new instance of {@link Profile}.
     * 
     * @param id       - Id of the new profile
     * @param username - the username
     */
    public Profile(int id, String username, int achievedLevel) {
        this.id = id;
        this.setUsername(username);
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
     * Sets the username of the {@link Profile}.
     * 
     * @param username - new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the Id of the level the user achieved.
     * 
     * @return the level id
     */
    public int getAchievedLevel() {
        return this.achievedLevel;
    }

    /**
     * Sets the achieved level to the new value. Keeps the old value if the new
     * achieved level is lower than the actual achieved level.
     * 
     * @param achievedLevel - the achieved level
     */
    public void setAchievedLevel(int achievedLevel) {
        if (this.achievedLevel < achievedLevel) {
            this.achievedLevel = achievedLevel;
        }
    }

    /**
     * Returns a string representation of the profile.
     * 
     * @return {@link Profile} converted to {@link String}
     */
    @Override
    public String toString() {
        return this.getId() + "," + this.getUsername() + "," + this.getAchievedLevel();
    }
}