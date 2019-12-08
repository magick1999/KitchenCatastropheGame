package group44.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import group44.controllers.Leaderboard;

/**
 * Represents a Record in the {@link Leaderboard}.
 *
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class Record implements Comparable<Record> {
    /** The profile. */
    private Profile profile;
    /** The id of the level. */
    private int levelId;
    /** The time taken. */
    private long time;

    /**
     * Creates a new istance of {@link Record}.
     *
     * @param profile
     *            - {@link Profile} linked to the record
     * @param levelId
     *            - Id of the associated {@link Level}
     * @param time
     *            - time taken to finish the level
     */
    public Record(Profile profile, int levelId, long time) {
        this.profile = profile;
        this.levelId = levelId;
        this.setTime(time);
    }

    /**
     * Returns the associated {@link Profile}.
     *
     * @return the associated profile
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Returns Id of the associated {@link Level}.
     *
     * @return the level id
     */
    public int getLevelId() {
        return this.levelId;
    }

    /**
     * Returns the time taken by the user to finish the {@link Level}.
     *
     * @return the time taken
     */
    public long getTime() {
        return this.time;
    }

    /**
     * Updates the time taken to finish the {@link Level}.
     *
     * @param time
     *            - the new time taken
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Compares {@link Record}s based on the time taken to finish the level.
     *
     * @param o
     *            - the {@link Record} to compare
     */
    @Override
    public int compareTo(Record o) {
        return Long.compare(this.getTime(), o.getTime());
    }

    /**
     * Returns a string representation of the record.
     *
     * @return {@link Record} converted to {@link String}.
     */
    @Override
    public String toString() {
        return this.timeString() + " - " + this.getProfile().getUsername();
    }

    /**
     * Formats a record time into string representation.
     *
     * @return time in a format of mm:ss.
     */
    public String timeString() {
        Date date = new Date(this.getTime());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }
}
