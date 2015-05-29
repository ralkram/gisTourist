package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Trail extends Place {
  private static String trailIconImageLocation = "resources/trail.png";
  private String description;

  /**
     *
     */
  public Trail(String name, Double latitude, Double longitude, String description) {
    super(name, trailIconImageLocation, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}