package group44.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import group44.exceptions.UsernameTakenException;
import group44.models.Profile;

/**
 * Manages, loads, and saves user profiles.
 * 
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class ProfileManager {
    private static final String PROFILE_FILE_PATH = ""; // TODO: Add path
    private static ProfileManager instance;

    private ArrayList<Profile> profiles;

    /**
     * Creates a new instance of {@link ProfileManager} and tries to load the stored
     * {@link Profile}s.
     */
    private ProfileManager() {
        this.profiles = this.load(ProfileManager.PROFILE_FILE_PATH);
    }

    /**
     * Returns the singleton instance of {@link ProfileManager}. If the instance
     * does not exit, it creates one. When creating the instance,
     * {@link ProfileManager} also tries to load the stored {@link Profile}s.
     * 
     * @return the singleton instance of {@link ProfileManager}
     */
    public static ProfileManager getInstance() {
        if (ProfileManager.instance == null) {
            ProfileManager.instance = new ProfileManager();
        }
        return ProfileManager.instance;
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
            Profile profile = new Profile(++max_id, username, 0);
            this.profiles.add(profile);
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

    /**
     * Loads the profiles from specified file.
     * 
     * @param path - path where the file with profiles is located
     * @return a list of loaded {@link Profile}s
     */
    private ArrayList<Profile> load(String path) {
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(path);
            return this.load(fileScanner);
        } catch (Exception e) {
            System.out.println("File (" + path + ") with profiles not found!");
            return new ArrayList<Profile>();
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }

    /**
     * Loads stored profiles.
     * 
     * @param fileScanner - scanner of the file where the profiles are stored
     * @return a list of loaded {@link Profile}s
     */
    private ArrayList<Profile> load(Scanner fileScanner) {
        ArrayList<Profile> loadedProfiles = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            Profile p = this.parseProfile(new Scanner(fileScanner.nextLine()));
            if (p != null) {
                loadedProfiles.add(p);
            }
        }

        return loadedProfiles;
    }

    /**
     * Creates a new {@link Profile} from the scanner.
     * 
     * @param scanner - scanner with the serialised profile
     * @return {@link Profile} created from values in scanner
     */
    private Profile parseProfile(Scanner scanner) {
        Profile newProfile = null;
        scanner.useDelimiter(",");

        try {
            int id = scanner.nextInt();
            String username = scanner.next();
            int achievedLevel = scanner.nextInt();

            newProfile = new Profile(id, username, achievedLevel);
        } catch (Exception e) {
            System.out.println("Unable to parse a Profile.\n" + e.getMessage());
        }

        return newProfile;
    }

    /**
     * Saves managed profiles.
     */
    public void save() {
        File file = new File(ProfileManager.PROFILE_FILE_PATH);

        try {
            PrintWriter writer = new PrintWriter(file);
            this.save(writer);
        } catch (Exception e) {
            System.out.println("Unable to save profiles.\n" + e.getMessage());
        }
    }

    /**
     * Saves profiles using provided scanner.
     * 
     * @param writer - {@link java.io.Writer} to use when saving profiles
     */
    private void save(PrintWriter writer) {
        for (Profile profile : this.profiles) {
            writer.println(profile.toString());
        }
    }
}