package touristgis.places.facilities;

import touristgis.places.Place;

/**
 *
 */
public class Institution extends Place {
  /**
   * 
   */
  private static final long serialVersionUID = 3798505013441244180L;

  /**
     *
     */
  private String specialization;

  private String description;

  /**
     *
     */
  public Institution(String name, Double latitude, Double longitude, String description, String specialization) {
    super("Institution", name, latitude, longitude);
    this.description = description;
    this.specialization = specialization;
  }

  @Override
  protected String getDescription() {
    return "Specialization: " + specialization + "\n" + description;
  }
}