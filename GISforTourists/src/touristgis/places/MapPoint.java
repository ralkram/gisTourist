package touristgis.places;

import java.io.Serializable;

import com.esri.map.JMap;

/**
 * Defines a map point.
 */
public abstract class MapPoint implements Serializable {
  /**
   * Default serial UID.
   */
  private static final long serialVersionUID = -1203646979347248360L;

  /**
   * The latitude of point.
   */
  protected Double latitude;

  /**
   * The longitude of point.
   */
  protected Double longitude;

  public MapPoint(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  /**
   * Adds a point on map.
   *
   * @param map The reference to map.
   */
  public abstract void addOnMap(JMap map);
}