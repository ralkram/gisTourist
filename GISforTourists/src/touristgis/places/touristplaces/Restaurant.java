package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Restaurant extends Place {
  private String description;

  /**
     *
     */
  public Restaurant(String name, Double latitude, Double longitude, String description) {
    super("Restaurant", name, latitude, longitude);

    this.description = description;
  }

  /**
  * Constructor for allowing restaurant subclass to set their placeType for a distinct marker icon.
  */
  Restaurant(String restaurantType, String name, Double latitude, Double longitude, String description) {
    super(restaurantType, name, latitude, longitude);

    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}