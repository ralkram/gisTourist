package touristgis.places.touristplaces;

import touristgis.places.Place;

public class Landmark extends Place {
  private static final long serialVersionUID = 6925883714103836811L;
  private String description;

  public Landmark(String name, Double latitude, Double longitude, String description) {
    super("Landmark", name, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}