package touristgis.places;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.Predicate;

import touristgis.places.facilities.Hospital;
import touristgis.places.facilities.Institution;
import touristgis.places.touristplaces.Hotel;
import touristgis.places.touristplaces.Landmark;

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
   */
  public PlaceManager(String placesBinLocation) {
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

  private List<Place> readPlaces() {
    List<Place> readPlaces = new ArrayList<Place>();

    // -122.520, 37.8365, -122.3023, 37.6985
    readPlaces.add(new Hospital("Spitalul 2", 37.7365, -122.5, "Some hospital"));
    readPlaces.add(new Hospital("Spitalul 3", 37.8365, -122.5, "Some other hospital"));
    readPlaces.add(new Institution("Scoala nr. 22", 37.7565, -122.4, "A school like any other.", "teaching"));
    readPlaces.add(new Landmark("Pyramid", 37.7565, -122.43, "A great pyramid from ancient times."));
    readPlaces.add(new Landmark("Mausoleum", 37.7965, -122.47, "Here someone important was burried."));
    readPlaces.add(new Hotel("Grand hotel", 37.7865, -122.42, "This is the most important hotel in town.", 5));

    return readPlaces;
  }

  @Override
  public List<Place> getPlaces(touristgis.places.Predicate<Place> criteria) {
    return null;
  }
}