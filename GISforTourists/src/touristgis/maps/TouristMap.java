package touristgis.maps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import touristgis.maps.directions.ClosestPlaceCalculator;
import touristgis.maps.directions.RoutingCalculator;
import touristgis.places.PlaceLoader;
import touristgis.places.PlaceManager;
import touristgis.places.PlaceRepository;

import com.esri.client.local.ArcGISLocalTiledLayer;
import com.esri.core.geometry.Envelope;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
import com.esri.core.symbol.TextSymbol;
import com.esri.core.tasks.na.NAFeaturesAsFeature;
import com.esri.core.tasks.na.Route;
import com.esri.core.tasks.na.RouteParameters;
import com.esri.core.tasks.na.RouteResult;
import com.esri.core.tasks.na.RouteTask;
import com.esri.map.GraphicsLayer;
import com.esri.map.JMap;
import com.esri.toolkit.overlays.DrawingCompleteEvent;
import com.esri.toolkit.overlays.DrawingCompleteListener;
import com.esri.toolkit.overlays.DrawingOverlay;
import com.esri.toolkit.overlays.DrawingOverlay.DrawingMode;

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

  /**
   *
   */
  private JMap map;

  // -------------------------
  // New
  // -------------------------
  // private JMap map;
  private JComponent contentPane;
  private NAFeaturesAsFeature stops = new NAFeaturesAsFeature();
  private GraphicsLayer graphicsLayer;
  private DrawingOverlay myDrawingOverlay;
  private JToolBar toolBar;
  private JComboBox<String> citiesList;
  private JToggleButton toggleButtonAddStop;
  private JButton routeButton;
  private JButton resetButton;
  private int selectedMapId = 0;
  private int numStops = 0;

  // -------------------------
  // New
  // -------------------------

  /**
   * @param geodatabaseLocation
   * @param mapTilePackageLocation
   */
  public TouristMap(String geodatabaseLocation, String mapTilePackageLocation) {
    this.geodatabaseLocation = geodatabaseLocation;
    this.mapTilePackageLocation = mapTilePackageLocation;

    // map = new JMap();
    //
    // final ArcGISLocalTiledLayer baseLayer = new ArcGISLocalTiledLayer(mapTilePackageLocation);
    //
    // LayerList layers = map.getLayers();
    // layers.add(baseLayer);
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
    contentPane = new JPanel(new BorderLayout());

    // create components
    map = createMap(selectedMapId);
    toolBar = createToolBar(myDrawingOverlay);

    // add them to our content pane
    contentPane.add(map, BorderLayout.CENTER);
    contentPane.add(toolBar, BorderLayout.NORTH);

    try {
      setPlacesLoader(new PlaceManager("resources/sanfrancisco/places.ser"));
    }
    catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }

    return contentPane;
  }

  @Override
  public String getMapName() {
    return "";
  }

  /**
   * Creates the map, sets the initial extent.
   *
   * @return a map.
   */
  private JMap createMap(int mapId) {
    final String[] cities = { "resources/sandiego/SanDiego.tpk", "resources/sanfrancisco/SanFrancisco.tpk" };
    final JMap jMap = new JMap();
    jMap.setShowingEsriLogo(false);

    ArcGISLocalTiledLayer tiledLayer = new ArcGISLocalTiledLayer(cities[mapId]);
    jMap.getLayers().add(tiledLayer);
    jMap.setExtent(new Envelope(-13054452, 3847753, -13017762, 3866957.78));

    // graphics layer for our stop/barrier/route graphics
    graphicsLayer = new GraphicsLayer();
    jMap.getLayers().add(graphicsLayer);

    // create drawing overlay, add to map, and activate
    myDrawingOverlay = new DrawingOverlay();
    myDrawingOverlay.setActive(true);
    myDrawingOverlay.addDrawingCompleteListener(new DrawingCompleteListener() {
      @Override
      public void drawingCompleted(DrawingCompleteEvent arg0) {
        if (toggleButtonAddStop.isSelected()) {
          Graphic graphic = (Graphic) myDrawingOverlay.getAndClearFeature();
          graphicsLayer.addGraphic(graphic);
          if (graphic.getAttributeValue("type").equals("Stop")) {
            numStops++;
            stops.addFeature(graphic);
            graphicsLayer.addGraphic(new Graphic(graphic.getGeometry(), new TextSymbol(12, String.valueOf(numStops),
                Color.WHITE)));
          }
        }
      }
    });
    jMap.addMapOverlay(myDrawingOverlay);

    return jMap;
  }

  private JToolBar createToolBar(final DrawingOverlay drawingOverlay) {
    JToolBar toolBarNew = new JToolBar();
    toolBarNew.setLayout(new FlowLayout(FlowLayout.CENTER));
    toolBarNew.setFloatable(false);

    final String[] cities = { "San Diego", "San Francisco" };
    citiesList = new JComboBox<>(cities);
    citiesList.setSelectedIndex(selectedMapId);
    citiesList.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unchecked")
        JComboBox<String> citiesList = (JComboBox<String>) e.getSource();
        selectedMapId = citiesList.getSelectedIndex();

        contentPane.remove(map);
        contentPane.remove(toolBarNew);
        map = createMap(citiesList.getSelectedIndex());
        JToolBar toolbarTemp = createToolBar(myDrawingOverlay);
        contentPane.add(map, BorderLayout.CENTER);
        contentPane.add(toolbarTemp, BorderLayout.NORTH);
        contentPane.validate();
        contentPane.repaint();
        resetButton.doClick();

        try {
          setPlacesLoader(new PlaceManager("resources/sanfrancisco/places.ser"));
        }
        catch (ClassNotFoundException | IOException exception) {
          exception.printStackTrace();
        }
      }
    });
    toolBarNew.add(citiesList);

    // stop
    toggleButtonAddStop = new JToggleButton(" Add a stop ");
    toggleButtonAddStop.setFocusPainted(false);
    toggleButtonAddStop.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();

        if (button.isSelected()) {
          HashMap<String, Object> attributes = new HashMap<>();
          attributes.put("type", "Stop");
          drawingOverlay.setUp(DrawingMode.POINT, new SimpleMarkerSymbol(Color.RED, 25, Style.CIRCLE), attributes);
        }
        else {
          drawingOverlay.setUp(DrawingMode.NONE, new SimpleMarkerSymbol(Color.RED, 25, Style.CIRCLE), null);
        }
      }
    });
    toolBarNew.add(toggleButtonAddStop);

    // solve
    routeButton = new JButton(" Solve route ");
    routeButton.setFocusPainted(false);
    routeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        doRouting(citiesList.getSelectedIndex());
      }
    });
    toolBarNew.add(routeButton);

    // reset
    resetButton = new JButton("  Reset  ");
    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        graphicsLayer.removeAll();
        stops.clearFeatures();
        numStops = 0;
      }
    });
    toolBarNew.add(resetButton);

    return toolBarNew;
  }

  private void doRouting(int cityGeoDatabaseId) {
    final String[] citiesGeoDatabase = { "resources/sandiego/Routing/RuntimeSanDiego.geodatabase",
        "resources/sanfrancisco/Routing/RuntimeSanFrancisco.geodatabase" };
    RouteResult result = null;
    RouteParameters parameters = null;

    try {
      RouteTask task = RouteTask.createLocalRouteTask(citiesGeoDatabase[cityGeoDatabaseId], "Streets_ND");
      parameters = task.retrieveDefaultRouteTaskParameters();
      parameters.setOutSpatialReference(map.getSpatialReference());
      stops.setSpatialReference(map.getSpatialReference());
      parameters.setStops(stops);
      // opposite of 'preserve order of stops'
      parameters.setFindBestSequence(false);
      result = task.solve(parameters);
      showResult(result);
    }
    catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(contentPane, wrap("An error has occured. " + e.getLocalizedMessage()), "",
          JOptionPane.WARNING_MESSAGE);
    }
  }

  private void showResult(RouteResult result) {
    if (result != null) {
      // display the top route on the map as a graphic
      Route topRoute = result.getRoutes().get(0);
      Graphic routeGraphic = new Graphic(topRoute.getRouteGraphic().getGeometry(),
          new SimpleLineSymbol(Color.RED, 2.0f));
      graphicsLayer.addGraphic(routeGraphic);
    }
  }

  private String wrap(String str) {
    // create a HTML string that wraps text when longer
    return "<html><p style='width:200px;'>" + str + "</html>";
  }
}