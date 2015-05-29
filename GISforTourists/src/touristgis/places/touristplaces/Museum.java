package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Museum extends Place {
  private String description;

  /**
     *
     */
  public Museum(String name, Double latitude, Double longitude, String description) {
    super("Museum", name, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}