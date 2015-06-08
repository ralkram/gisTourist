package touristgis.maps.directions.impl;

import java.util.Set;

import touristgis.maps.directions.ClosestPlaceCalculator;
import touristgis.maps.directions.RoutingCalculator;
import touristgis.places.MapPoint;
import touristgis.places.Place;

/**
 * Class used to calculate point-to-point or point-to-facility directions.
 */
public class DirectionsCalculatorImpl implements RoutingCalculator, ClosestPlaceCalculator {
  @Override
  public void solve(MapPoint startLocation, Set<Place> availableFacilities, String geodatabaseLocation) {

  }

  @Override
  public void solve(Set<MapPoint> stops, String geodatabaseLocation) {

  }
}
