import java.util.List;
import java.util.ArrayList;
/**
 * The {@code Ward} class represents a city ward and its associated data.
 *
 * <p>
 * It stores the ward's name in both English and French, its sector type,
 * and a list of tax records over multiple years.
 * </p>
 *
 * <p>
 * Each {@code Ward} contains multiple {@link Tax} objects representing
 * historical tax data. This class is immutable except for the list of taxes.
 * </p>
 *
 * @author Nasim Bidel
 * @version 1.0
 * @since March 13, 2026
 *
 */

public class Ward {
	private int id;
    private final String nameEnglish;
    private final String nameFrench;
    private final String sectorEnglish;
    private final String sectorFrench;
    private final List<Tax> taxes;

    /**
     * Constructs a Ward object with the specified names and sector.
     *
     * @param nameEnglish the English name of ward.
     * @param nameFrench the French name of ward.
     * @param sectorEnglish the English name of the ward's sector
     * @param sectorFrench the French name of the ward's sector
     */
    public Ward(int id,String nameEnglish, String nameFrench,  String sectorEnglish ,String sectorFrench){
        this.id = id;
    	this.nameEnglish = nameEnglish;
        this.nameFrench = nameFrench;
        this.sectorEnglish = sectorEnglish;
        this.sectorFrench=sectorFrench;
        this.taxes = new ArrayList<>();
    }

    /**
     * Returns the English name of the ward.
     *
     * @return the English ward name
     */
    public String getNameEnglish(){
        return nameEnglish;
    }

    /**
     * Return the French name of the ward.
     *
     * @return the French ward name
     */
    public String getNameFrench(){
        return nameFrench;
    }

    /**
     * Returns the English sector (type) of the ward.
     *
     * @return the English sector name
     */
    public String getSectorEnglish(){
        return sectorEnglish;
    }

    /**
     * Returns the French sector (type) of the ward
     *
     * @return the French sector name
     */
    public String getSectorFrench(){
        return sectorFrench;
    }

    /**
     * Returns the list of tax records associated with this ward.
     *
     * @return a list of tax objects representing historical data
     */
    public List<Tax> getTaxes(){
        return taxes;
    }
    
    public int getId() {
    	return id;
    }

}

