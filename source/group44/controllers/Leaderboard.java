package group44.controllers;

import java.util.ArrayList;
import java.util.Collections;
import group44.game.Level;
import group44.models.Profile;
import group44.models.Record;

/**
 * Represents a leaderboard of the game.
 * 
 * @author Tomas Svejnoha, Rowan Aldean
 * @version 1.0
 */
public class Leaderboard {
    private ArrayList<Record> records;

    /**
     * Creates a new instance of {@link Leaderboard}.
     */
    public Leaderboard() {
        this.records = new ArrayList<>();
    }

    /**
     * Adds or updates a record in the {@link Leaderboard} if the time is better.
     * 
     * @param profile - {@link Profile} of the user
     * @param levelId - Id of the {@link Level} the user finished
     * @param time    - time taken to finish the {@link Level}
     */
    public void addOrUpdate(Profile profile, int levelId, long time) {
        Record record = null;

        for (Record item : this.records) {
            if (item.getProfile().getId() == profile.getId() && item.getLevelId() == levelId) {
                record = item;
                break;
            }
        }

        if (record == null) {
            this.records.add(new Record(profile, levelId, time));
        } else if (record.getTime() > time) {
            record.setTime(time);
        }
    }

    /**
     * Returns up to 3 top records for the level.
     * 
     * @param levelId - id of the {@link Level} for which we want the records
     * @return array of up to 3 top records
     */
    public Record[] getTopThreeRecords(int levelId) {
        ArrayList<Record> levelRecords = new ArrayList<>();

        for (Record item : this.records) {
            if (item.getLevelId() == levelId) {
                levelRecords.add(item);
            }
        }

        Collections.sort(levelRecords);

        Record[] top3 = new Record[(levelRecords.size() < 3) ? levelRecords.size() : 3];
        for (int i = 0; i < 3; i++) {
            top3[i] = levelRecords.get(i);
        }

        return top3;
    }
}