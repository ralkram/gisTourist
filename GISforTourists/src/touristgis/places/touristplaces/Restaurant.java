package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Restaurant extends Place {
  private static String landmarkIconImageLocation = "resources/restaurant.png";
  private String description;

  /**
     *
     */
  public Restaurant(String name, Double latitude, Double longitude, String description) {
    super(name, landmarkIconImageLocation, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}