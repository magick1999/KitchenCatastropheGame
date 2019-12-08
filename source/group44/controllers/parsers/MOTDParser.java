package group44.controllers.parsers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The class for getting the Message of the day.
 *
 * @author Jordan Price, Tomas Svejnoha
 * @version 1.0
 *
 */
public class MOTDParser {

    /**
     * Gets the MOTD.
     *
     * @return message The decoded message of the day
     */
    public static String getMOTD() {
        // Passes URL to getHTML
        String code = getHtml("http://cswebcat.swan.ac.uk/puzzle");
        // decodes the code given from the
        String decoded = decode(code);
        // Passes the URL and the decoded code to the checker web-site.
        String message = getHtml(
                "http://cswebcat.swan.ac.uk/message?solution=" + decoded);
        return message;

    }

    /**
     * Gets a string from the inputed website and returns it.
     *
     * @param website
     *            - the url of the website to be read.
     * @return output - the output that the BurfferedReader has given.
     *
     */
    private static String getHtml(final String website) {
        String output = null; // Creates a default output
        try {
            StringBuilder result = new StringBuilder();
            // Encodes the passes in string as a URL
            URL url = new URL(website);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            // Reads the web-site
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            bufferedReader.close();
            output = result.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * Performs the algorithm to decrypt the code.
     *
     * @param message
     *            - The code collected from the website.
     * @return message - The updated and decoded code after performing the
     *         algorithm.
     */
    private static String decode(String message) {
        char[] messageChars = message.toCharArray(); // Converts string
                                                     // to a
                                                     // character
                                                     // array

        for (int i = 0; i < message.length(); i++) { // Iterates through
                                                     // the character
                                                     // array
            char c = message.charAt(i);
            if ((i & 1) == 0) { // If even
                if (c == 90) {
                    // Loop from Z back to A
                    messageChars[i] = 65;
                } else {
                    // Increase character value by 1
                    messageChars[i] = c += 1;
                }
            } else {
                if (c == 65) {
                    // Loop from A back to Z
                    messageChars[i] = 90;
                } else {
                    // Decrease character value by 1
                    messageChars[i] = c -= 1;
                }
            }
        }
        // Converts from
        // character array back
        // to a string.
        message = String.valueOf(messageChars);

        return message;
    }
}
