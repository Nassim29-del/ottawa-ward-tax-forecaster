import java.util.Scanner;
/**
 * The {@code WardManager} class controls the main user interface
 * of the Ward Analysis Tool.
 *
 * <p>
 * It provides a menu-driven system that allows the user to:
 * <ul>
 *   <li>Read ward and tax data from CSV files</li>
 *   <li>Select the type of analysis to perform</li>
 *   <li>Run the selected analysis</li>
 *   <li>Exit the application</li>
 * </ul>
 * </p>
 *
 * <p>
 * This class manages user input and delegates processing to
 * specific {@link Analysis} implementations such as
 * {@link ExistingWardAnalysis} and {@link NewWardAnalysis}.
 * </p>
 *
 * @author Nasim Bidel
 * @version 1.0
 * @since March 13, 2026
 *
 *
 * @see Analysis
 * @see ExistingWardAnalysis
 * @see NewWardAnalysis
 */
public class WardManager {
	/**
	 * The currently selected analysis type
	 */
	private Analysis analysis = null;
	 /**
     * Displays the main menu and processes user input.
     *
     * <p>
     * The menu runs in a loop until the user chooses to quit.
     * Based on the user's selection, it will:
     * <ul>
     *   <li>Read data files</li>
     *   <li>Change the analysis type</li>
     *   <li>Execute the selected analysis</li>
     *   <li>Exit the program</li>
     * </ul>
     * </p>
     */
	
	public void menu() {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("Main Menu");
			System.out.println("(R)ead File");
			System.out.println("(C)hange analysis  type ");
			System.out.println("(A)nalyse ");
			System.out.println("(Q)uit ");
			System.out.println("Select action:  ");
			String action = sc.nextLine().toUpperCase();
			
			switch(action) {
			
			case ("R"):
			    Analysis.read(Analysis.PATH);
			    break;
			case ("C"):
				Change(sc); break;
			
			case ("A" ):
				analyses(sc); break;
			
			case ("Q"):
				sc.close(); return;
			
			default:
				System.out.println("Invalid option");
				break;
			
			}
		}	
			
}
	/**
     * Allows the user to select the type of analysis to perform.
     *
     * <p>
     * The user can choose between:
     * <ul>
     *   <li>Existing ward analysis</li>
     *   <li>New ward prediction</li>
     *   <li>No change</li>
     * </ul>
     * </p>
     *
     * @param scanner the {@link Scanner} used to read user input
     * @return {@code true} if the analysis type was changed,
     *         {@code false} otherwise
     */
		private  boolean Change(Scanner scanner) {
		
			while(true) {
				System.out.println("Analysis type:");
				System.out.println("1: Forecast existing ward data");
				System.out.println("2: Predict new ward municipal taxes");
				System.out.println("0: Do not change analysis type");
				System.out.println("Please select analysis type: ");
				 String input = scanner.nextLine().trim(); // read as string

			        int type;
			        try {
			            type = Integer.parseInt(input); // try converting to int
			        } catch (NumberFormatException e) {
			            System.out.println("Invalid option"); // handle non-integer input
			            continue; // ask again
			        }

				
				if (type == 1) {
					analysis = new ExistingWardAnalysis();
					return true;
				} else if ( type == 2) {
					analysis = new NewWardAnalysis();
					return true;
				} else if ( type == 0) {
					return false;
				} else {
					System.out.println("Invalid option");
				}
			}
	}

	    /**
	     * Executes the currently selected analysis.
	     *
	     * <p>
	     * This method ensures that:
	     * <ul>
	     *   <li>An analysis type has been selected</li>
	     *   <li>Data has been loaded</li>
	     * </ul>
	     * before running the analysis.
	     * </p>
	     *
	     * @param scanner the {@link Scanner} used for user input
	     */
		
		private void analyses(Scanner scanner) {
			if (analysis == null) {
				System.out.println("No analysis type selected");
				return;
			}
			
			if (Analysis.wards.isEmpty()) {
				System.out.println("No data has been read");
				return;
			}
			
			analysis.runAnalysis(scanner);
		}
	
	
	

}
