
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
/**
 * The {@code NewWardAnalysis} class performs predictive analysis
 * for new wards that do not yet have historical tax data.
 *
 * <p>
 * It allows the user to select a sector (type of ward) and input
 * an estimated value assessment, then predicts the expected
 * municipal taxes using linear regression based on existing wards
 * within the same sector.
 * </p>
 *
 * <p>
 * This class extends {@link Analysis} and implements the
 * {@link #runAnalysis(Scanner)} method.
 * </p>
 *
 * <pre>
 * Example usage:
 * Scanner input = new Scanner(System.in);
 * Analysis analysis = new NewWardAnalysis();
 * analysis.runAnalysis(input);
 * </pre>
 *
 * @author Nasim Bidel
 * @version 1.0
 * @since March 13, 2026
 *
 * Course: CST8132 Object Oriented Programming  
 * Program: CET-CS-Level 2  
 * Professor: Fedor Ilitchev
 *
 * @see Analysis
 * @see Ward
 * @see Tax
 * @see Regression
 */

public class NewWardAnalysis extends Analysis{
	/**
     * Runs the analysis for a new ward.
     *
     * <p>
     * Steps:
     * <ol>
     *   <li>Displays all unique sectors available</li>
     *   <li>Prompts the user to select a sector</li>
     *   <li>Prompts the user to enter an estimated value assessment</li>
     *   <li>Collects data from wards in the selected sector</li>
     *   <li>Performs linear regression</li>
     *   <li>Displays predicted municipal taxes with a range</li>
     * </ol>
     * </p>
     *
     * @param input the {@link Scanner} used for user input
     */
	 
    @Override
    protected void runAnalysis(Scanner input) {
        if (wards == null || wards.isEmpty()){
            System.out.println("No data has been read.");
            return;
        }
        System.out.println("New ward analysis");

        //collect unique sectors.
        List<String> sectors = new ArrayList<>();
        for (Ward w : wards){
            if (!sectors.contains(w.getSectorEnglish())){
                sectors.add(w.getSectorEnglish());
            }
        }

        //display sectors
        for (String sector : sectors){
            System.out.println(sector);
        }

        //Get user for sector
        System.out.println("What type of ward is this: ");
        String userInput = input.nextLine();

        System.out.println("What is the estimated value assessment? ");
        double estimatedValue  = input.nextDouble();
        input.nextLine();

        List<Point> points = new ArrayList<>();
        for (Ward w : wards){
            if (w.getSectorEnglish().equalsIgnoreCase(userInput)){
                for (Tax tax : w.getTaxes()){
                    points.add(new Point(tax.getCurrentValueAssessment(),
                            tax.getMunicipalTaxes()));
                }
            }
        }
        if (points.isEmpty()) {
            System.out.println("No data found for sector: " + userInput);
                return;
            }
        

    Regression regression = new Regression(points);
    double predictedTax   = regression.predict(estimatedValue);

    // Calculate range using standard error
    double stdErr    = regression.slopeStdErr();
    double lowerBound = predictedTax - stdErr;
    double upperBound = predictedTax + stdErr;

    // Display results
    System.out.println("------------------------------------------------");
    System.out.printf(
            "The %s value assessment of $%,.2f, the municipal taxes would be between $%,.2f and $%,.2f%n",
            userInput.toLowerCase(),
            estimatedValue,
            lowerBound,
            upperBound
    );
    System.out.println("------------------------------------------------");
    }
}

