package touristgis.places.facilities;

import touristgis.places.Place;

/**
 *
 */
public class Hospital extends Place {
  private static String hospitalIconImageLocation = "hospital.png";
  private String description;

  /**
     *
     */
  public Hospital(String name, Double latitude, Double longitude, String description) {
    super(name, hospitalIconImageLocation, latitude, longitude);
    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}