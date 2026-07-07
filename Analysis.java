import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code Analysis} class is an abstract base class for all ward analysis types.
 * <p>
 * It is responsible for:
 * <ul>
 *   <li>Reading and loading ward and tax data from CSV files</li>
 *   <li>Storing the data in a shared static list of {@link Ward} objects</li>
 *   <li>Providing a common structure for different analysis implementations</li>
 * </ul>
 * </p>
 *
 * <p>
 * The loaded data is used for analytical purposes such as linear regression
 * to study trends in municipal taxes and property assessments.
 * </p>
 *
 * @author Nasim Bidel
 * @version 1.0
 * @since March 13, 2026
 *
 * Course: CST8132 Object Oriented Programming  
 * Program: CET-CS-Level 2  
 * Professor: Fedor Ilitchev
 *
 * @see Ward
 * @see Tax
 * @see Regression
 */
public abstract class Analysis {

    // Shared list of all wards loaded from dataset
    protected static List<Ward> wards = new ArrayList<>();

    // Path to CSV files – update this to match your current directory
    protected static final String PATH = "C:\\Users\\nasim\\OneDrive\\Documents\\Assignment 2\\src\\";

    /**
     * Reads ward and tax data from CSV files located at the given path.
     *
     * @param path the directory path where the CSV files are located
     * @return true if both files are successfully read, false otherwise
     */
    public static boolean read(String path) {

        // Check if Wards.csv exists
        File wardsFile = new File(path + "Wards.csv");
        if (!wardsFile.exists()) {
            System.out.println("Error: Wards.csv not found at: " + wardsFile.getAbsolutePath());
            return false;
        }

        // Check if Taxes.csv exists
        File taxesFile = new File(path + "Taxes.csv");
        if (!taxesFile.exists()) {
            System.out.println("Error: Taxes.csv not found at: " + taxesFile.getAbsolutePath());
            return false;
        }

        // Read Wards.csv
        try (BufferedReader br = new BufferedReader(new FileReader(wardsFile))) {
            String line;
            br.readLine(); // skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 5) continue; // changed to 5 for full columns
                
                int id = Integer.parseInt(values[0].trim());
                String nameEnglish = values[1].trim();
                String nameFrench = values[2].trim();
                String sectorEnglish = values[3].trim();
                String sectorFrench = values[4].trim();

                wards.add(new Ward(id, nameEnglish, nameFrench, sectorEnglish, sectorFrench));
            }
            System.out.println("Wards.csv successfully read from: " + path);
        } catch (IOException e) {
            System.out.println("Error reading Wards.csv: " + e.getMessage());
            return false;
        }

        // Read Taxes.csv
        try (BufferedReader br = new BufferedReader(new FileReader(taxesFile))) {
            String line;
            br.readLine(); // skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 5) continue; // skip invalid lines

                int wardId = Integer.parseInt(values[0].trim()); 
                int year = Integer.parseInt(values[1].trim());
                double cva = Double.parseDouble(values[2].trim());
                double taxes = Double.parseDouble(values[3].trim());
                float total = Float.parseFloat(values[4].trim());

                Tax tax = new Tax(year, cva, taxes, total);

                // Match tax to ward using wardId
                for (Ward ward : wards) {
                    if (ward.getId() == wardId) {  // now compares int to int
                        ward.getTaxes().add(tax);
                        break; // found the ward, no need to keep looping
                    }
                }
            }
            System.out.println("Taxes.csv successfully read from: " + path);
        } catch (IOException e) {
            System.out.println("Error reading Taxes.csv: " + e.getMessage());
            return false;
        }

        return true;
    }

    // Abstract method to be implemented by subclasses
    protected abstract void runAnalysis(Scanner input);
}