package touristgis.maps;

import javax.swing.JComponent;

/**
 *
 */
public interface Map {
  /**
   * @return
   */
  public JComponent getMapGUI();

  /**
   * @return
   */
  public String getMapName();
}