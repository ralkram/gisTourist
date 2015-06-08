package touristgis.places;

import java.util.List;
import java.util.function.Predicate;

/**
 * A container for places.
 */
public interface PlaceRepository {
  /**
   * Return a list of places.
   *
   * @param criteria The criteria.
   * @return The list of places.
   */
  public List<Place> getPlaces(Predicate<Place> criteria);
}