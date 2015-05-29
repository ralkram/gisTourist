package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Theatre extends Place {
  private static String landmarkIconImageLocation = "resources/theatre.png";
  private String description;

  /**
     *
     */
  public Theatre(String name, Double latitude, Double longitude, String description) {
    super(name, landmarkIconImageLocation, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}