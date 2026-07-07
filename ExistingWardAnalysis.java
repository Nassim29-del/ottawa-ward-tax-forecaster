import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
/**
 * The {@code ExistingWardAnalysis} class performs a forecast analysis
 * for wards that already have historical data in the system.
 *
 * <p>
 * It allows the user to select an existing ward and a future year,
 * then predicts the ward's value assessment, municipal taxes,
 * and percent of total using linear regression.
 * </p>
 *
 * <p>
 * This class extends the {@link Analysis} class and implements
 * the {@link #runAnalysis(Scanner)} method.
 * </p>
 *
 * <pre>
 * Example usage:
 * Scanner input = new Scanner(System.in);
 * Analysis analysis = new ExistingWardAnalysis();
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
public class ExistingWardAnalysis extends Analysis{

    /**
     * Runs the analysis for an existing ward.
     *
     * <p>
     * Steps:
     * <ol>
     *   <li>Displays all wards in the dataset</li>
     *   <li>Prompts the user to select a ward</li>
     *   <li>Prompts the user to enter a forecast year</li>
     *   <li>Collects historical data points</li>
     *   <li>Performs linear regression</li>
     *   <li>Displays predicted results</li>
     * </ol>
     * </p>
     *
     * @param input the {@link Scanner} used for user input
     */
    @Override
    protected void runAnalysis(Scanner input) {
        if (wards == null || wards.isEmpty()){
            System.out.println("No data has been read");
            return;
        }
        
        
        System.out.println("Existing ward analysis");
        //loop throw words list and print each word
        for (int i =0; i < wards.size(); i++){
        	Ward ward = wards.get(i);
            System.out.println((i + 1) + " : " + ward.getNameEnglish());
        }
        System.out.println("Which ward are you analysing? ");
        int userInput = input.nextInt();
        input.nextLine();
        
        Ward selectWard = wards.get(userInput -1);  // to get 0-based index

        System.out.println("What year do you want to forecast?");
        int forecast = input.nextInt();
        input.nextLine();

        //collect data points from regression class
        List<Point> valueAssessmentPoints = new ArrayList<>();
        List<Point> municipalTaxPoints   = new ArrayList<>();
        List<Point> percentOfTotalPoints   = new ArrayList<>();

        for (Tax tax : selectWard.getTaxes()){
            double year = tax.getYear();
            valueAssessmentPoints.add(new Point(year, tax.getCurrentValueAssessment()));
            municipalTaxPoints.add(new Point(year, tax.getMunicipalTaxes()));
            percentOfTotalPoints.add(new Point(year, tax.getPercentOfTotal()));
        }
        
        // Perform regression
        Regression valueAssessmentReg = new Regression(valueAssessmentPoints);
        Regression municipalReg = new Regression(municipalTaxPoints);
        Regression percentRed = new Regression(percentOfTotalPoints);

        double predictedValue = valueAssessmentReg.predict(forecast);
        double predictedTax = municipalReg.predict(forecast);
        double predictedPercent = percentRed.predict(forecast);

        // diplay result
        System.out.println("------------------------------------------------");
        System.out.println("For the year " + forecast + " the predicted values of " + selectWard.getNameEnglish() + " ward are:");
        System.out.printf("     Value Assessment: $ %,.2f%n", predictedValue);
        System.out.printf("     Municipal Tax: $ %,.2f%n", predictedTax);
        System.out.printf("     Percent of total: %.2f%%%n", predictedPercent);
        System.out.println("------------------------------------------------");





    }
}
