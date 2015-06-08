package touristgis.maps;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import touristgis.Main;
import touristgis.places.PlaceLoader;
import touristgis.places.PlaceManager;

/**
 * A tourist map factory implementing {@link MapFactory} interface that creates maps allocating the files based on the model shown
 * in the DeploymentView.
 *  @see <a href="http://ralkram.github.io/gisTourist/modeldoc/diagrams/File%20Relations_ul_AAAAAAFNoWPjLj62hDE=.svg">DeploymentViewFiles</a>
 *
 */
public class TouristMapFactory implements MapFactory {

  @Override
  public Map getMap(String mapName) {

    TouristMap map = new TouristMap("resources/" + mapName + "/routing/runtime" + mapName + ".geodatabase",
        "resources/" + mapName + "/" + mapName + ".tpk");

    try {
      try{
      map.setPlacesLoader(new PlaceManager("resources/" + mapName + "/places.ser"));
      }catch(FileNotFoundException e){
      }
    }
    catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }

    return map;
  }

}
