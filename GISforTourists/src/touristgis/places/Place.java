package touristgis.places;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import com.esri.map.JMap;

/**
 *
 */
public abstract class Place extends MapPoint implements Serializable {

  /**
    * 
    */
  private static final long serialVersionUID = 1L;

  private final String iconImageLocation;

  private String placeImageLocation;

  private String name;

  /**
   * @param name
   * @param iconImageLocation
   * @param latitude
   * @param longitude
   */
  public Place(String name, String iconImageLocation, Double latitude, Double longitude) {
    super(latitude, longitude);
    this.iconImageLocation = iconImageLocation;
    this.name = name;
  }

  public String getPlaceImageLocation() {
    return placeImageLocation;
  }

  public void setPlaceImageLocation(String placeImageLocation) {
    this.placeImageLocation = placeImageLocation;
  }

  /**
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * @return
   */
  protected abstract String getDescription();

  /**
   * @param map
   */
  @Override
  public final void addOnMap(JMap map) {

    BufferedImage imageMarker = null;

    try {

      URL url = Place.class.getProtectionDomain().getCodeSource().getLocation();
      imageMarker = ImageIO.read(new URL(url.toString() + iconImageLocation));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    map.addMarkerGraphic(latitude, longitude, name, getDescription(), placeImageLocation, null, imageMarker);
  }
}