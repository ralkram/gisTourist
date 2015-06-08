package touristgis.places.facilities;

import touristgis.places.Place;

public class PoliceStation extends Place {
  private static final long serialVersionUID = -2362998797480293787L;

  public PoliceStation(String name, Double latitude, Double longitude) {
    super("PoliceStation", name, latitude, longitude);
  }

  @Override
  protected String getDescription() {
    return "a police station";
  }
}