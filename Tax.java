/**
 * The {@code Tax} class represents tax-related data for a ward in a specific year.
 * <p>
 * It stores information such as:
 * <ul>
 *   <li>The year of the record</li>
 *   <li>The property value assessment</li>
 *   <li>The municipal taxes collected</li>
 *   <li>The percentage contribution to total taxes</li>
 * </ul>
 * </p>
 *
 * Student Name: Nasim Bidel
 * Course: CST8132 Object Oriented Programming
 * Date: Mar 13, 2026
 */

public class Tax {
    private final int year;
    private final double currentValueAssessment;
    private final double municipalTaxes;
    private final float percentOfTotal;

    /**
     * constructs a Tax object with the specified values.
     *
     * @param year - the year of the tax record
     * @param currentValueAssessment the total property value assessment for the ward in that year
     * @param municipalTaxes the amount of municipal taxes collected for that ward in that year
     * @param percentOfTotal the percentage contribution of this ward to the total taxes.
     */

    public Tax(int year, double currentValueAssessment, double municipalTaxes,
               float percentOfTotal) {
        this.year = year;
        this.currentValueAssessment = currentValueAssessment;
        this.municipalTaxes = municipalTaxes;
        this.percentOfTotal=percentOfTotal;

    }

    /**
     * Returns the year of this tax record.
     *
     * @return the year
     */
    public int getYear(){
        return year;
    }

    /**
     * Retursn the property value assessment for the ward in this year
     *
     * @return the current value assessment
     */

    public double getCurrentValueAssessment(){
        return currentValueAssessment;
    }

    /**
     * Returns the municipal taxes collected for the ward in this year.
     *
     * @return the municipal tax amount
     */
    public double getMunicipalTaxes(){
        return municipalTaxes;
    }

    /**
     * Returns the percentage contribution of this ward to the total taxes
     *
     * @return the percentage of total taxes
     */
    public float getPercentOfTotal(){
        return percentOfTotal;
    }
}
