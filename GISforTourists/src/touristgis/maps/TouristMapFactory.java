package touristgis.maps;

import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import touristgis.Main;
import touristgis.places.PlaceLoader;
import touristgis.places.PlaceManager;

public class TouristMapFactory implements MapFactory {

  @Override
  public Map getMap(String mapName) {

    TouristMap map = new TouristMap("", "resources/" + mapName + "/" + mapName + ".tpk");

    try {
      map.setPlacesLoader(new PlaceManager("resources/" + mapName + "/places.ser"));
    }
    catch (ClassNotFoundException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return map;
  }

}
