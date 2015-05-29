package touristgis.places;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Predicate;

import touristgis.places.facilities.Hospital;
import touristgis.places.facilities.Institution;
import touristgis.places.touristplaces.Hotel;
import touristgis.places.touristplaces.Landmark;
import touristgis.places.touristplaces.Museum;

import com.esri.map.JMap;

/**
 *
 */
public class PlaceManager implements PlaceRepository, PlaceLoader {
  /**
     *
     */
  private String placesBinLocation;

  /**
     *
     */
  private List<Place> places;

  /**
   * @param placesBinLocation
   * @throws IOException
   * @throws ClassNotFoundException
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

  private List<Place> readPlaces() throws IOException, ClassNotFoundException {

    // List<Place> readPlaces = new ArrayList<Place>();

    InputStream file = new FileInputStream(placesBinLocation);
    InputStream buffer = new BufferedInputStream(file);
    ObjectInput input = new ObjectInputStream(buffer);

    List<Place> readPlaces = (List<Place>) input.readObject();
    //
    // for (Place place : readPlaces) {
    // System.out.println(place.getName() + " " + place.getDescription() + " " + place.getLatitude() + " "
    // + place.getPlaceImageLocation());
    // }

    // -122.520, 37.8365, -122.3023, 37.6985
    // readPlaces.add(new Hospital("Spitalul 2", 37.7365, -122.5, "Some hospital"));
    // readPlaces.get(0).setPlaceImageLocation("file:.///resources/sanfrancisco/01_b.jpg");
    // readPlaces.add(new Hospital("Spitalul 3", 37.8365, -122.5, "Some other hospital"));
    // readPlaces.add(new Institution("Scoala nr. 22", 37.7565, -122.4, "A school like any other.", "teaching"));
    // readPlaces.add(new Museum("Museum 101", 37.7565, -122.43, "A night at the museum II"));
    // readPlaces.add(new Landmark("Mausoleum", 37.7965, -122.47, "Here someone important was burried."));
    // readPlaces.add(new Hotel("Grand hotel", 37.7865, -122.42, "This is the most important hotel in town.", 5));

    return readPlaces;
  }
}