package touristgis.places.touristplaces;

import touristgis.places.Place;

public class Restaurant extends Place {
  private static final long serialVersionUID = -1141995444227108171L;
  private String description;

  public Restaurant(String name, Double latitude, Double longitude, String description) {
    super("Restaurant", name, latitude, longitude);

    this.description = description;
  }

  public Restaurant(String restaurantType, String name, Double latitude, Double longitude, String description) {
    super(restaurantType, name, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}