package test;

import java.util.ArrayList;

import group44.controllers.Leaderboard;
import group44.controllers.LevelManager;
import group44.controllers.ProfileManager;
import group44.exceptions.UsernameTakenException;
import group44.models.LevelInfo;
import group44.models.Profile;
import group44.models.Record;

public class ManagersTest {
    private static final String PROFILES = "source/group44/data/profiles.txt";
    private static final String LEADERBOARD = "source/group44/data/records.txt";
    private static final String LEVELS = "source/group44/data/levels/";

    public static void main(String[] args) {
	ProfileManager.load();
	Leaderboard.load();
	LevelManager.load();

	printProfile(ProfileManager.getProfile(1));
	printProfile(ProfileManager.getProfile(7));
	printProfile(ProfileManager.getProfile("Tomas"));
	System.out.println("-------------------------------------\n");
	try {
	    ProfileManager.register("Anna");
	} catch (UsernameTakenException e) {
	    e.printStackTrace();
	}
	ProfileManager.save();

	// ***********************************************************
	// ***********************************************************
	// ***********************************************************

	// printRecords(Leaderboard.getTopThreeRecords(1));
	System.out.println("-------------------------------------\n");
	Leaderboard.addOrUpdate(ProfileManager.getProfile(7).getId(), 1, 2000);
	// printRecords(Leaderboard.getTopThreeRecords(1));
	System.out.println("-------------------------------------\n");
	Leaderboard.save();

	// ***********************************************************
	// ***********************************************************
	// ***********************************************************

	printLevelInfos(LevelManager.getLevelInfos());
	System.out.println("-------------------------------------\n");
    }

    private static void printProfile(Profile profile) {
	System.out.println(profile.getId());
	System.out.println(profile.getUsername());
	System.out.println(profile.getAchievedLevel());
	System.out.println();
    }

    private static void printRecords(Record[] records) {
	for (Record item : records) {
	    System.out.println(item.getLevelId());
	    System.out.println(item.getProfile().getUsername());
	    System.out.println(item.getTime());
	    System.out.println();
	}
    }

    private static void printLevelInfos(ArrayList<LevelInfo> infos) {
	for (LevelInfo levelInfo : infos) {
	    System.out.println(levelInfo.getId());
	    System.out.println(levelInfo.getFile().getPath());
	    System.out.println();
	}
    }
}
