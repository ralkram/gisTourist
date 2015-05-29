package touristgis.places;

import java.io.Serializable;

import com.esri.map.JMap;

/**
 *
 */
public abstract class MapPoint implements Serializable {
  /**
     *
     */
  public MapPoint() {
  }

  /**
     *
     */
  protected Double latitude;

  /**
     *
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
   * @param map
   */
  public abstract void addOnMap(JMap map);
}