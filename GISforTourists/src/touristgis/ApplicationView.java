package touristgis;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import touristgis.maps.Map;
import touristgis.maps.MapFactory;

/**
 * Main application view. Extends a JFrame and is run from main. Allows the user to select maps to view.
 */
public class ApplicationView extends JFrame {
  /**
   * Default serial UID.
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JList<String> list;
  private DefaultListModel<String> listModel = new DefaultListModel<>();
  private Component currentMapGUI;

  /**
   * Method for accessing the map list model.
   *
   * @return a list model allowing map names to be added.
   */
  public DefaultListModel<String> getListModel() {
    return listModel;
  }

  private MapFactory mapFactory;

  /**
   * Method for accessing the currently set map factory.
   *
   * @return the currently set map factory.
   */
  public MapFactory getMapFactory() {
    return mapFactory;
  }

  /**
   * Method for setting the map factory.
   *
   * @param mapFactory a map factory to set the ApplicationView to.
   */
  public void setMapFactory(MapFactory mapFactory) {
    this.mapFactory = mapFactory;
  }

  private JPanel mapPanel;

  /**
   * Creates the frame.
   */
  public ApplicationView() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new GridLayout(0, 1, 0, 0));

    JSplitPane splitPane = new JSplitPane();
    splitPane.setLastDividerLocation(300);
    contentPane.add(splitPane);

    JPanel listPanel = new JPanel();
    splitPane.setLeftComponent(listPanel);
    listPanel.setLayout(new GridLayout(0, 1, 0, 0));

    list = new JList<>(listModel);

    list.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
          String mapName = list.getSelectedValue();
          Map newMap = mapFactory.getMap(mapName);

          if (currentMapGUI != null) {
            mapPanel.remove(currentMapGUI);
          }

          currentMapGUI = newMap.getMapGUI();
          mapPanel.add(currentMapGUI);
          mapPanel.validate();
          mapPanel.repaint();
        }
      }
    });

    list.addMouseListener(new MouseAdapter() {
      private Component currentMapGUI;

      @Override
      public void mouseClicked(MouseEvent evt) {
        @SuppressWarnings("unchecked")
        JList<String> list = (JList<String>) evt.getSource();

        if (evt.getClickCount() == 2) {
          String mapName = list.getSelectedValue();
          Map newMap = mapFactory.getMap(mapName);

          if (currentMapGUI != null) {
            mapPanel.remove(currentMapGUI);
          }

          currentMapGUI = newMap.getMapGUI();
          mapPanel.add(currentMapGUI);
          mapPanel.validate();
          mapPanel.repaint();
        }
      }
    });
    listPanel.add(list);

    mapPanel = new JPanel();
    splitPane.setRightComponent(mapPanel);
    mapPanel.setLayout(new BorderLayout(0, 0));
    splitPane.setDividerLocation(100);
  }
}
