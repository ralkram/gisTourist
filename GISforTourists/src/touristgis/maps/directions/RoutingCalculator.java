package touristgis.maps.directions;

import java.util.Set;

import touristgis.places.MapPoint;

/**
 *
 */
public interface RoutingCalculator {
  /**
   * @param stops
   * @param geodatabaseLocation
   * @param observer
   */
  public void solve(Set<MapPoint> stops, String geodatabaseLocation, Object observer);
}