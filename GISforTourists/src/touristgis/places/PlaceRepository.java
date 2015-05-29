package touristgis.places;

import java.util.List;
import java.util.function.Predicate;

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