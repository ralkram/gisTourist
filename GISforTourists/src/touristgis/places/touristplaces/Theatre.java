package touristgis.places.touristplaces;

import touristgis.places.Place;

public class Theatre extends Place {
  private static final long serialVersionUID = 7819598493894841272L;
  private String description;

  public Theatre(String name, Double latitude, Double longitude, String description) {
    super("Theatre", name, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}