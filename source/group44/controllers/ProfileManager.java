package group44.controllers;

import java.util.ArrayList;

import group44.exceptions.UsernameTakenException;
import group44.models.Profile;

/**
 * Manages user profiles.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class ProfileManager {
    private ArrayList<Profile> profiles;

    /**
     * Creates a new instance of {@link ProfileManager}.
     */
    public ProfileManager() {
        this.profiles = new ArrayList<>();
    }

    /**
     * Creates a new instance of {@link ProfileManager}.
     * 
     * @param profiles - profiles to be stored in the {@link ProfileManager}
     */
    public ProfileManager(ArrayList<Profile> profiles) {
        this();
        this.profiles = profiles;
    }

    /**
     * Returns the collection of {@link Profile}s
     * 
     * @return profiles
     */
    public ArrayList<Profile> getProfiles() {
        return this.profiles;
    }

    /**
     * Creates a new {@link Profile} with username, if the username is not already
     * taken. The achieved level id for the new profile is 0.
     * 
     * @param username - username to be used
     * @throws UsernameTakenException if the username is already taken
     * @return the created {@link Profile}
     */
    public Profile register(String username) throws UsernameTakenException {
        if (this.exists(username)) {
            throw new UsernameTakenException(username);
        } else {
            int max_id = this.getMaxId();
            Profile profile = new Profile(max_id, username, 0);
            this.getProfiles().add(profile);
            return profile;
        }
    }

    /**
     * Check if a {@link Profile} with username already exists.
     * 
     * @param username - the username to check
     * @return true if the username is already taken; otherwise false
     */
    private Boolean exists(String username) {
        for (Profile profile : this.profiles) {
            if (profile.getUsername() == username) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the maximum id in the known profiles.
     * 
     * @return the maximum id
     */
    private int getMaxId() {
        int maxId = 0;

        for (Profile profile : this.profiles) {
            if (maxId < profile.getId()) {
                maxId = profile.getId();
            }
        }

        return maxId;
    }
}