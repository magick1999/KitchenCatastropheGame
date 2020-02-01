package group44.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import group44.Constants;
import group44.exceptions.UsernameTakenException;
import group44.models.Profile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Manages, loads, and saves user profiles.
 *
 * @author Tomas Svejnoha
 * @version 1.0
 */
public class ProfileManager {
    /** Collection of all user profiles. */
    private static ObservableList<Profile> profiles = FXCollections
            .observableArrayList(new ArrayList<>());

    /**
     * Returns an {@link ObservableList} of profiles.
     *
     * @return an observable list of profiles.
     */
    public static ObservableList<Profile> getProfiles() {
        return ProfileManager.profiles;
    }

    /**
     * Returns a {@link Profile} with id.
     *
     * @param id
     *            id to find.
     * @return {@link Profile} if match was found; otherwise null.
     */
    public static Profile getProfile(final int id) {
        Profile profile = null;

        for (Profile item : ProfileManager.profiles) {
            if (item.getId() == id) {
                profile = item;
            }
        }

        return profile;
    }

    /**
     * Returns a {@link Profile} with username.
     *
     * @param username
     *            username to find.
     *
     * @return {@link Profile} if match is found; otherwise null.
     */
    public static Profile getProfile(final String username) {
        Profile profile = null;

        for (Profile item : ProfileManager.profiles) {
            if (item.getUsername().equals(username)) {
                profile = item;
            }
        }

        return profile;
    }

    /**
     * Creates a new {@link Profile} with username, if the username is not
     * already taken. The achieved level id for the new profile is 0.
     *
     * @param username
     *            username to be used.
     *
     * @throws UsernameTakenException
     *             if the username is already taken.
     *
     * @return the created {@link Profile}.
     */
    public static Profile register(final String username)
            throws UsernameTakenException {
        if (ProfileManager.exists(username)) {
            throw new UsernameTakenException(username);
        } else {
            int maxId = ProfileManager.getMaxId();
            Profile profile = new Profile(++maxId, username);
            ProfileManager.profiles.add(profile);

            ProfileManager.save();

            return profile;
        }
    }

    /**
     * Deletes a profile by id.
     *
     * @param profileId
     *            if of profile to delete.
     */
    public static void delete(final int profileId) {
        Profile profile = getProfile(profileId);

        if (profile != null) {
            ProfileManager.profiles.remove(profile);
            ProfileManager.save();
        }
    }

    /**
     * Deletes a profile by username.
     *
     * @param username
     *            username of profile to delete.
     */
    public static void delete(final String username) {
        Profile profile = getProfile(username);

        if (profile != null) {
            ProfileManager.profiles.remove(profile);
            ProfileManager.save();
        }
    }

    /**
     * Check if a {@link Profile} with username already exists.
     *
     * @param username
     *            the username to check.
     *
     * @return true if the username is already taken; otherwise false.
     */
    private static Boolean exists(final String username) {
        for (Profile profile : ProfileManager.profiles) {
            if (profile.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the maximum id in the known profiles.
     *
     * @return the maximum id.
     */
    private static int getMaxId() {
        int maxId = 0;

        for (Profile profile : ProfileManager.profiles) {
            if (maxId < profile.getId()) {
                maxId = profile.getId();
            }
        }

        return maxId;
    }

    /**
     * Loads the profiles from the default file.
     *
     * @return an observable list of loaded profiles.
     */
    public static ObservableList<Profile> load() {
        ProfileManager.getProfiles().clear();
        ProfileManager.load(Constants.FILE_PROFILES);
        return ProfileManager.getProfiles();
    }

    /**
     * Loads profiles from specified file.
     *
     * @param path
     *            path where the file with profiles is located.
     */
    private static void load(final String path) {
        ArrayList<Profile> loadedProfiles = null;
        Scanner fileScanner = null;
        File file = new File(path);

        try {
            fileScanner = new Scanner(file);
            loadedProfiles = ProfileManager.load(fileScanner);
        } catch (Exception e) {
            System.out.println("File (" + path + ") with profiles not found!");
            loadedProfiles = new ArrayList<Profile>();
        } finally {
            if (fileScanner != null) {
                fileScanner.close();
            }
            for (Profile item : loadedProfiles) {
                ProfileManager.profiles.add(item);
            }
        }
    }

    /**
     * Loads stored profiles.
     *
     * @param fileScanner
     *            scanner of the file where the profiles are stored.
     *
     * @return a list of loaded {@link Profile}s.
     */
    private static ArrayList<Profile> load(Scanner fileScanner) {
        ArrayList<Profile> loadedProfiles = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            Profile p = ProfileManager
                    .parseProfile(new Scanner(fileScanner.nextLine()));
            if (p != null) {
                loadedProfiles.add(p);
            }
        }

        return loadedProfiles;
    }

    /**
     * Creates a new {@link Profile} from the scanner.
     *
     * @param scanner
     *            - scanner with the serialised profile.
     *
     * @return {@link Profile} created from values in scanner; null if there is
     *         some exception.
     */
    private static Profile parseProfile(Scanner scanner) {
        Profile newProfile = null;
        scanner.useDelimiter(",");

        try {
            int id = scanner.nextInt();
            String username = scanner.next();

            newProfile = new Profile(id, username);
        } catch (Exception e) {
            System.out.println("Unable to parse a Profile.\n" + e.getMessage());
        }

        return newProfile;
    }

    /**
     * Saves the profiles to the default file.
     */
    public static void save() {
        ProfileManager.save(Constants.FILE_PROFILES);
    }

    /**
     * Saves managed profiles.
     *
     * @param path
     *            path to the file where to store the profiles.
     */
    private static void save(final String path) {
        File file = new File(path);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file);
            ProfileManager.save(writer);
        } catch (Exception e) {
            System.out.println("Unable to save profiles.\n" + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Saves profiles using provided writer.
     *
     * @param writer
     *            {@link PrintWriter} to use when saving profiles.
     */
    private static void save(PrintWriter writer) {
        for (Profile profile : ProfileManager.profiles) {
            writer.println(profile.toString());
        }
    }
}
