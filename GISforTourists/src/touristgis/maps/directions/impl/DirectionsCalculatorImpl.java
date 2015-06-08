package touristgis.maps.directions.impl;

import java.util.Set;

import touristgis.maps.directions.ClosestPlaceCalculator;
import touristgis.maps.directions.RoutingCalculator;
import touristgis.places.MapPoint;
import touristgis.places.Place;

public class DirectionsCalculatorImpl implements RoutingCalculator, ClosestPlaceCalculator {
  @Override
  public void solve(MapPoint startLocation, Set<Place> availableFacilities, String geodatabaseLocation, Object observer) {

  }

  @Override
  public void solve(Set<MapPoint> stops, String geodatabaseLocation, Object observer) {

  }
}
