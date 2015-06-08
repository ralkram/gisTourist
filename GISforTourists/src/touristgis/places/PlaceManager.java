package touristgis.places;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.esri.map.JMap;

/**
 * Class used to manage the places.
 */
public class PlaceManager implements PlaceRepository, PlaceLoader {
  /**
   * Location of the places.
   */
  private String placesBinLocation;

  /**
   * The list with places.
   */
  private List<Place> places;

  /**
   * Class constructor.
   *
   * @param placesBinLocation The location of places.
   * @throws IOException If an I/O error occurs while reading stream header.
   * @throws ClassNotFoundException If the class of a serialized object cannot be found.
   */
  public PlaceManager(String placesBinLocation) throws ClassNotFoundException, IOException {
    this.placesBinLocation = placesBinLocation;

    places = readPlaces();

  }

  @Override
  public void populateMap(JMap map) {
    for (Place place : places) {
      place.addOnMap(map);
    }
  }

  @Override
  public List<Place> getPlaces(Predicate<Place> criteria) {
    List<Place> returnedValue = new ArrayList<Place>();

    for (Place place : places) {
      if (criteria.test(place)) {
        returnedValue.add(place);
      }
    }

    return returnedValue;
  }

  /**
   * Loads the places from external file.
   *
   * @return The list of places.
   * @throws IOException If an I/O error occurs while reading stream header.
   * @throws ClassNotFoundException If the class of a serialized object cannot be found.
   */
  @SuppressWarnings("unchecked")
  private List<Place> readPlaces() throws IOException, ClassNotFoundException {

    List<Place> readPlaces = null;

    InputStream file = new FileInputStream(placesBinLocation);
    InputStream buffer = new BufferedInputStream(file);
    ObjectInput input = new ObjectInputStream(buffer);

    try {
      readPlaces = (List<Place>) input.readObject();
    }
    finally {
      input.close();
    }

    return readPlaces;
  }
}