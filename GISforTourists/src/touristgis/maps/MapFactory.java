package touristgis.maps;
/**
 * Interface of a map factory object.
 */
public interface MapFactory {
  /**
   * Factory method for creating a map.
   * @param mapName the name of the map to be created.
   * @return an object implementing the {@link Map} interface.
   */
  Map getMap(String mapName);
}
