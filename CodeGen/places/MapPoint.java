
import java.util.*;

/**
 * 
 */
public abstract class MapPoint {

    /**
     * 
     */
    public MapPoint() {
    }

    /**
     * 
     */
    private Double latitude;

    /**
     * 
     */
    private Double longitude;

    /**
     * @param name 
     * @param iconImageLocation 
     * @param latitude 
     * @param longitude
     */
    public void MapPoint(String name, String iconImageLocation, Double latitude, Double longitude) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Double getLatitude() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Double getLongitude() {
        // TODO implement here
        return null;
    }

    /**
     * @param map
     */
    public void addOnMap(JMap map) {
        // TODO implement here
    }

}