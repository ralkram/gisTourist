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
  private final String iconImageLocation;

  /**
     *
     */
  private final Set<String> placeImageLocations;

  /**
     *
     */
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
    this.placeImageLocations = new HashSet<String>();

  }

  /**
   * @return
   */
  protected Set<String> getPlaceImageLocations() {
    return new HashSet<String>(placeImageLocations);
  }

  /**
   * @param placeImageLocation
   */
  public void addPlaceImageLocation(String placeImageLocation) {
    placeImageLocations.add(placeImageLocation);
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
      System.out.println(url.toString());
      imageMarker = ImageIO.read(new URL(url.toString() + iconImageLocation));
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    map.addMarkerGraphic(latitude, longitude, name, getDescription(), null, null, imageMarker);
  }
}