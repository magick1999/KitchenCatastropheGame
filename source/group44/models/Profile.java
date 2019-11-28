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

    /**
     * Creates a new instance of {@link Profile}.
     * 
     * @param id       - Id of the new profile
     * @param username - the username
     */
    public Profile(int id, String username) {
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
}