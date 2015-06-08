package touristgis.places;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.esri.map.JMap;

/**
 *
 */
public class UserLocation extends MapPoint {
  /**
   * 
   */
  private static final long serialVersionUID = 621044583870669382L;
  /**
   * 
   */
  private static BufferedImage imageMarker;
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
     *
     */
  public UserLocation(Double latitude, Double longitude) {
    super(latitude, longitude);
  }

  /**
   * @param map
   */
  @Override
  public void addOnMap(JMap map) {
    map.addMarkerGraphic(latitude, longitude, null, null, null, null, imageMarker);
  }
}