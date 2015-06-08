package touristgis.maps.directions;

import java.util.Set;

import touristgis.places.MapPoint;
import touristgis.places.Place;

/**
 * Defines the method used to calculate the path from a location to the closest facility.
 */
public interface ClosestPlaceCalculator {

  /**
   * Calculates the path from a location to the closest facility.
   *
   * @param startLocation The point of the start location.
   * @param availableFacilities The list of facilities.
   * @param geodatabaseLocation Geodatabase location path.
   */
  public void solve(MapPoint startLocation, Set<Place> availableFacilities, String geodatabaseLocation);
}