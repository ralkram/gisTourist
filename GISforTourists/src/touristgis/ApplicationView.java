package touristgis;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import touristgis.maps.Map;
import touristgis.maps.MapFactory;
import touristgis.maps.TouristMapFactory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ApplicationView extends JFrame {

  private JPanel contentPane;
  private JList list;
  
  private DefaultListModel  listModel = new DefaultListModel();
  

  public DefaultListModel getListModel() {
    return listModel;
  }

  private MapFactory mapFactory = new TouristMapFactory();
  private JPanel mapPanel;

  /**
   * Create the frame.
   */
  public ApplicationView() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setBounds(100, 100, 450, 300);
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
    
    list = new JList(listModel);

    list.addKeyListener(new KeyAdapter() {
      private Component currentMapGUI;

      @Override
      public void keyPressed(KeyEvent arg0) {
        if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
          String mapName = (String) list.getSelectedValue();
          Map newMap = mapFactory.getMap(mapName);
          
          try{
          mapPanel.remove(currentMapGUI);
          }catch(NullPointerException e){ 
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
