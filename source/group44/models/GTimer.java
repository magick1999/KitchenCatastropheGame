package group44.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * This Class creates the stopwatch to track how long a player spends on a
 * level.
 *
 * @author Jordan Price, Tomas Svejnoha
 */
public class GTimer {
    // Formats the LONG to output in a time format.
    static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
    // Boolean whether the timer is running
    private static boolean timing = false;
    // Last time calculated
    private static Date prevTime;
    // Current calculated time
    private static Date currentTime;
    // Running total of time taken
    private static long currentTimeTaken;
    // Creates the thread for the timer to run in.
    private Timer timer = new Timer("Stopwatch");

    /**
     * This method starts the timer.
     *
     * @param time
     *            passes in the timeLabel Label in from the
     *            MainGameWindowController.
     * @param startTime
     *            resume or start time of the level.
     */
    public void startTimer(Label time, long startTime) { // TODO: EDIT HERE
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        prevTime = new Date();
        timing = true;
        currentTimeTaken = startTime;
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                // Checks if timing is true
                if (timing == true) {
                    // takes the current time
                    currentTime = new Date();
                    // Finds difference between current time
                    // and the previous time and adds it to the running total
                    // time
                    currentTimeTaken += (currentTime.getTime()
                            - prevTime.getTime());
                    prevTime = currentTime;
                    String output = formatter.format(currentTimeTaken);

                    // Tells javafx thread to update the
                    // label with the new
                    // output ASAP
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            time.setText(output);
                        }
                    });

                }
            }
        };

        // Starts the timer.
        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }

    /**
     * This method pauses the timer.
     */
    public void pauseTimer() {
        timing = false;
    }

    /**
     * This method resumes the timer.
     */
    public void resumeTimer() {
        prevTime = new Date();
        timing = true;

    }

    /**
     * This method stops the timer.
     */
    public void stopTimer() {
        timing = false;
        timer.cancel();
        // Need a call here to send to leaderboard or profile?

    }

    /**
     * Returns the time taken by the player to finish the level.
     *
     * @return the time taken.
     */
    public static long getCurrentTimeTaken() {
        return currentTimeTaken;
    }
}
