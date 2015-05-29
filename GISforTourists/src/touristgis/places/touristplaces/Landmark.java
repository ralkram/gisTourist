package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Landmark extends Place {
  private String description;

  /**
     *
     */
  public Landmark(String name, Double latitude, Double longitude, String description) {
    super("Landmark", name, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}