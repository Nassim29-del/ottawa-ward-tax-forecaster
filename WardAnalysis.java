/**
 * Ward Analysis Tool
 *
 * Entry point for the ward tax analysis application. This program uses
 * linear regression to forecast municipal tax values for existing wards
 * and to predict tax ranges for proposed new wards, based on historical
 * Open Ottawa tax data.
 *
 * Responsibilities of this class are intentionally minimal: display the
 * application header, delegate all work to a WardManager, and print a
 * closing message.
 *
 * @author Nasim Bidel
 * @version 1.0
 */
public class WardAnalysis {

    /**
     * Application entry point.
     *
     * Displays the header, creates the WardManager that drives the
     * program, runs the main menu loop, and prints the closing message.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Application header
        System.out.println("Ward analysis tool");
        System.out.println("------------------");

        // WardManager owns the menu loop and coordinates the analyses
        WardManager wm = new WardManager();
        wm.menu();

        // Closing message
        System.out.println("Program written by Nasim Bidel");
    }
}