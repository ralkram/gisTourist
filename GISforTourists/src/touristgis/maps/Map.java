package touristgis.maps;

import javax.swing.JComponent;

/**
 *Interface for a map object.
 */
public interface Map {
  /**
   * Allows the caller to get the GUI of the map as a {@link JComponent}. This separates the map operations logic from the calling view.
   * @return a {@link JComponent} containing the map view.
   */
  public JComponent getMapGUI();

  /**
   * Gets the name of the map.
   * @return a string representing the map name.
   */
  public String getMapName();
}