package group44;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.control.Label;
import java.util.Date;
import java.util.TimeZone; 

/**
 * This Class creates the stopwatch to track how long a player spends on a level
 * @author Jordan Price
 */
public class GTimer {
	//Formats the LONG to output in a time format.
	static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS"); 
	//Boolean whether the timer is running
	private static boolean timing = false;
	//Last time calculated
	private static Date prevTime;
	//Current calculated time
	private static Date currentTime;
	//Running total of time taken
	private static long currentTimeTaken;
	//Creates the thread for the timer to run in.
	Timer timer = new Timer("Stopwatch");
	
	/**
	 * This method starts the timer.
	 * 
	 * @param time passes in the timeLabel Label in from the MainGameWindowController
	 */	
	public void startTimer(Label time) {
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		prevTime = new Date();
		timing = true;
		currentTimeTaken = 0;
		TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
            	//Checks if timing is true
            	if (timing == true) {
    				currentTime = new Date();//takes the current time
    				//Finds difference between current time and the previous time and adds it to the running total time
    				currentTimeTaken +=  (currentTime.getTime() - prevTime.getTime());
    				prevTime = currentTime;//Sets the current time to the previous time
    				String output = formatter.format(currentTimeTaken);//Sets the formatted output of the time
    				
    				//Tells javafx thread to update the label with the new output ASAP
    				Platform.runLater(new Runnable() {
    				    @Override public void run() {
    				    	time.setText(output);
    				    }
    				});
    			
            	}
            }
        };
       
        //Starts the timer.
        timer.scheduleAtFixedRate(timerTask, 0, 100);//this line starts the timer at the same time its executed
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
		//Need a call here to send to leaderboard or profile?
		
	}

	
}
