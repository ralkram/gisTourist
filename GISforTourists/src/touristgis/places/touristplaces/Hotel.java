package touristgis.places.touristplaces;

import touristgis.places.Place;

/**
 *
 */
public class Hotel extends Place {
  private static String hotelIconImageLocation = "resources/hotel.png";
  private String description;

  /**
     *
     */
  public Hotel(String name, Double latitude, Double longitude, String description, int rating) {
    super(name, hotelIconImageLocation, latitude, longitude);
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