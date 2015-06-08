package touristgis.places;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.esri.map.JMap;

/**
 *
 */
public abstract class Place extends MapPoint implements Serializable {
  /**
   * Default serial UID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The type of place.
   */
  protected final String placeType;

  /**
   * The place image location.
   */
  private String placeImageLocation;

  /**
   * The name of the location.
   */
  private String name;

  /**
   * A dictionary mapping the placeType as key and markerImage as value.
   */
  protected static Dictionary<String, String> iconImageDictionary;

  /**
   * The name of the configuration file.
   */
  private static final String markerConfigurationPath = "markerconfig.xml";

  static {
    iconImageDictionary = new Hashtable<String, String>();

    try {
      initializeMarkerImageDictionary();
    }
    catch (SAXException | IOException | ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads the list of places from xml external file.
   *
   * @throws IOException If any IO errors occur.
   * @throws SAXException If any parse errors occur.
   * @throws ParserConfigurationException When f is null.
   */
  private static void initializeMarkerImageDictionary() throws SAXException, IOException, ParserConfigurationException {
    File fXmlFile = new File(markerConfigurationPath);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(fXmlFile);

    Element root = doc.getDocumentElement();
    for (int i = 0; i < root.getChildNodes().getLength(); i++) {
      Node node = root.getChildNodes().item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        iconImageDictionary.put(node.getAttributes().getNamedItem("PlaceType").getNodeValue(), node.getTextContent());
      }
    }
  }

  /**
   * Class constructor.
   *
   * @param placeType The type of place.
   * @param name The name of place.
   * @param latitude Latitude of location.
   * @param longitude Longitude of location.
   */
  public Place(String placeType, String name, Double latitude, Double longitude) {
    super(latitude, longitude);
    this.placeType = placeType;
    this.placeImageLocation = "";
    this.name = name;
  }

  public String getPlaceImageLocation() {
    return placeImageLocation;
  }

  public void setPlaceImageLocation(String placeImageLocation) {
    this.placeImageLocation = placeImageLocation;
  }

  public String getName() {
    return name;
  }

  /**
   * Returns the description of the place.
   *
   * @return The description.
   */
  protected abstract String getDescription();

  @Override
  public final void addOnMap(JMap map) {

    BufferedImage imageMarker = null;

    try {

      URL url = Place.class.getProtectionDomain().getCodeSource().getLocation();
      imageMarker = ImageIO.read(new URL(url.toString() + iconImageDictionary.get(placeType)));
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    map.addMarkerGraphic(latitude, longitude, name, getDescription(), placeImageLocation, null, imageMarker);
  }
}