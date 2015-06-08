package touristgis.places;

import com.esri.map.JMap;

/**
 * Defines the method used to populate the map with places.
 */
public interface PlaceLoader {
  /**
   * Populates the map with places.
   *
   * @param map The reference to map.
   */
  public void populateMap(JMap map);
}