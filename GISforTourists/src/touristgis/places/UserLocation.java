package touristgis.places;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.esri.map.JMap;

/**
 * User location class.
 */
public class UserLocation extends MapPoint {
  /**
   * Default serial UID.
   */
  private static final long serialVersionUID = 621044583870669382L;
  /**
   * The image marker.
   */
  private static BufferedImage imageMarker;
  /**
   * The image location name.
   */
  private static String iconImageLocation = "userimg.png";

  static {
    try {
      imageMarker = ImageIO.read(new URL(iconImageLocation));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Class contructor.
   */
  public UserLocation(Double latitude, Double longitude) {
    super(latitude, longitude);
  }

  @Override
  public void addOnMap(JMap map) {
    map.addMarkerGraphic(latitude, longitude, null, null, null, null, imageMarker);
  }
}