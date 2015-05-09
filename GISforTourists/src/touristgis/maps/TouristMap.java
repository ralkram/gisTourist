package touristgis.maps;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import touristgis.places.PlaceLoader;
import touristgis.places.PlaceRepository;

import com.esri.client.local.ArcGISLocalTiledLayer;
import com.esri.map.JMap;
import com.esri.map.LayerList;

/**
 *
 */
public class TouristMap implements Map {
  /**
     *
     */
  private String geodatabaseLocation;

  /**
     *
     */
  private String mapTilePackageLocation;

  /**
     *
     */
  private ClosestPlaceCalculator directionsClosestPlaceCalculator;

  /**
     *
     */
  private RoutingCalculator directionsRoutingCalculator;

  /**
     *
     */
  private PlaceRepository placesRepository;

  /**
     *
     */
  private PlaceLoader placesLoader;

  private JMap map;

  /**
   * @param geodatabaseLocation
   * @param mapTilePackageLocation
   */
  public TouristMap(String geodatabaseLocation, String mapTilePackageLocation) {
    this.geodatabaseLocation = geodatabaseLocation;
    this.mapTilePackageLocation = mapTilePackageLocation;

    map = new JMap();

    final ArcGISLocalTiledLayer baseLayer = new ArcGISLocalTiledLayer(mapTilePackageLocation);

    LayerList layers = map.getLayers();
    layers.add(baseLayer);
  }

  /**
   * @param arg
   */
  public void setPlacesLoader(PlaceLoader arg) {
    this.placesLoader = arg;

    map.removeAllMarkerGraphics();
    this.placesLoader.populateMap(map);
  }

  /**
   * @param arg
   */
  public void setPlacesRepository(PlaceRepository arg) {
    this.placesRepository = arg;
  }

  /**
   * @param arg
   */
  public void setDirectionsRoutingCalculator(RoutingCalculator arg) {
    this.directionsRoutingCalculator = arg;
  }

  /**
   * @param arg
   */
  public void setDirectionsClosestPlaceCalculator(ClosestPlaceCalculator arg) {
    this.directionsClosestPlaceCalculator = arg;
  }

  @Override
  public JComponent getMapGUI() {
    JPanel contentPane = new JPanel(new BorderLayout());

    contentPane.add(map);
    return contentPane;
  }

  @Override
  public String getMapName() {
    return "";
  }
}