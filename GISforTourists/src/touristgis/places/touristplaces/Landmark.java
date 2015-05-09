package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Landmark extends Place {
  private static String landmarkIconImageLocation = "landmark.png";
  private String description;

  /**
     *
     */
  public Landmark(String name, Double latitude, Double longitude, String description) {
    super(name, landmarkIconImageLocation, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}