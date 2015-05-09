package touristgis.maps;

import java.util.Set;

import touristgis.places.MapPoint;
import touristgis.places.Place;

/**
 *
 */
public interface ClosestPlaceCalculator {

  /**
   * @param startLocation
   * @param availableFacilities
   * @param geodatabaseLocation
   * @param observer
   */
  public void solve(MapPoint startLocation, Set<Place> availableFacilities, String geodatabaseLocation, Object observer);
}