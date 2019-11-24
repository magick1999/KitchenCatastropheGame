
/**
 * This class gets, decodes and returns a special message of the day.
 * @author Jordan Price
 * @version 1.0
 *
 */

import java.io.*;
import java.net.*;

/**
 * The Class MOTD.
 */
public class MOTDParser {

	/**
	 * Gets the MOTD.
	 *
	 * @return message The decoded message of the day
	 */
	public static String getMOTD() {
		String code = getHtml("http://cswebcat.swan.ac.uk/puzzle");//Passes url to getHTML
		String decoded = decode(code);//decodes the code given from the URL
		String message = getHtml("http://cswebcat.swan.ac.uk/message?solution=" + decoded);//passes the url and the decoded code to the checker website.
		return message;

	}

	/**
	 * Gets the output from the html.
	 *
	 * @param website 
	 * @return output the output that the website gives you.
	 */
	private static String getHtml(String website) {
		String output = null; //Creates a default output
		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(website); //Encodes the passes in string as a URL
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //Opens the connection to the website
			conn.setRequestMethod("GET"); //Tells the website that we're GETTING information
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));//Creates a reader
			String line;
			//Reads the website
			while ((line = rd.readLine()) != null) { 
				result.append(line);
			}
			rd.close(); //Closes the reader
			output = result.toString(); //Changes the output to string.

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * Decode.
	 *
	 * @param message The coded message.
	 * @return message The decoded message
	 */
	private static String decode(String message) {
		char[] messageChars = message.toCharArray(); //Converts string to a character array

		for (int i = 0; i < message.length(); i++) { //Iterates through the character array
			char c = message.charAt(i);
			if ((i & 1) == 0) {//If even
				if (c == 90) {
					messageChars[i] = 65;//Loop from Z back to A
				} else {
					messageChars[i] = c += 1;//Increase character value by 1
				}
			} else {
				if (c == 65) {//Loop from A back to Z
					messageChars[i] = 90;
				} else {
					messageChars[i] = c -= 1;//Decrease character value by 1
				}
			}
		}
		message = String.valueOf(messageChars);//Converts from character array back to a string.

		return message;
	}
}
