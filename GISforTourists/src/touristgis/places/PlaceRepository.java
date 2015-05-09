package touristgis.places;

import java.util.List;

import javax.sql.rowset.Predicate;

/**
 *
 */
public interface PlaceRepository {
  /**
   * @param criteria
   * @return
   */
  public List<Place> getPlaces(Predicate<Place> criteria);
}