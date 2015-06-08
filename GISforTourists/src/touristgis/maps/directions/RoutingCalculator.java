package touristgis.maps.directions;

import java.util.Set;

import touristgis.places.MapPoint;

/**
 * Defines the method used to calculate the path between multiple points.
 */
public interface RoutingCalculator {
  /**
   * Calculates the directions for a list of points.
   *
   * @param stops The list of stops.
   * @param geodatabaseLocation Geodatabase location path.
   */
  public void solve(Set<MapPoint> stops, String geodatabaseLocation);
}