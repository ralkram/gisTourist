package touristgis.places.facilities;

import touristgis.places.Place;

/**
 *
 */
public class PoliceStation extends Place {
  private static String policeStationIconImageLocation = "policestation.png";

  /**
     *
     */
  public PoliceStation(String name, Double latitude, Double longitude) {
    super(name, policeStationIconImageLocation, latitude, longitude);
  }

  @Override
  protected String getDescription() {
    return "a police station";
  }
}