package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Hotel extends Place {
  private String description;

  /**
     *
     */
  public Hotel(String name, Double latitude, Double longitude, String description, int rating) {
    super("Hotel", name, latitude, longitude);
    this.description = description;
    this.rating = rating;
  }

  /**
     *
     */
  private int rating;

  @Override
  protected String getDescription() {
    return rating + " star hotel\n" + description;
  }
}