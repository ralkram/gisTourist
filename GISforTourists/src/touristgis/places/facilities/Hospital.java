package touristgis.places.facilities;

import touristgis.places.Place;

public class Hospital extends Place {
  private static final long serialVersionUID = -3105116371840046670L;
  private String description;

  public Hospital(String name, Double latitude, Double longitude, String description) {
    super("Hospital", name, latitude, longitude);
    this.description = description;
  }

  @Override
  protected String getDescription() {
    return description;
  }
}