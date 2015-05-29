
import java.util.*;

/**
 * 
 */
public abstract abstract class Place extends MapPoint implements Serializable {

    /**
     * 
     */
    public Place() {
    }

    /**
     * 
     */
    protected final String placeType;

    /**
     * 
     */
    private final String placeImageLocation;

    /**
     * 
     */
    private String name;

    /**
     * A dictionary mapping the placeType as key and markerImage as value.
     */
    private static Dictionary<String,String> iconImageDictionary;

    /**
     * 
     */
    private static final String markerConfigurationPath = "markerconfig.xml";

    /**
     * 
     */
    private static void initializeMarkerImageDictionary() {
        // TODO implement here
    }

    /**
     * @param name 
     * @param iconImageLocation 
     * @param latitude 
     * @param longitude
     */
    public void Place(String name, String iconImageLocation, Double latitude, Double longitude) {
        // TODO implement here
    }

    /**
     * @return
     */
    protected String getIconImageLocation() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    protected Set<String> getPlaceImageLocation() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    protected abstract String getDescription();

    /**
     * @param map
     */
    public final void addOnMap(JMap map) {
        // TODO implement here
    }

}