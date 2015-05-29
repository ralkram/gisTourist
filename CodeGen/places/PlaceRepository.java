
import java.util.*;

/**
 * 
 */
public interface PlaceRepository {

    /**
     * @param criteria 
     * @return
     */
    public Set<Place> getPlaces(Predicate<Place> criteria);

}