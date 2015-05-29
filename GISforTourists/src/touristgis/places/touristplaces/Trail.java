package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Trail extends Place {
  private String description;

  /**
     *
     */
  public Trail(String name, Double latitude, Double longitude, String description) {
    super("Trail", name, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}