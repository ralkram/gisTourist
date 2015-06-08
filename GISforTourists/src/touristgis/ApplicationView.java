package touristgis;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ListModel;

import touristgis.maps.Map;
import touristgis.maps.MapFactory;
import touristgis.maps.TouristMapFactory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ApplicationView extends JFrame {

  private JPanel contentPane;
  private JList list;
  

  private MapFactory mapFactory = new TouristMapFactory();
  private JPanel mapPanel;
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ApplicationView frame = new ApplicationView();
          frame.setVisible(true);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public ApplicationView() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    DefaultListModel listModel = new DefaultListModel();
    listModel.addElement("sanfrancisco");
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
        }
      }
    });
    listPanel.add(list);
    
    mapPanel = new JPanel();
    splitPane.setRightComponent(mapPanel);
    GroupLayout gl_mapPanel = new GroupLayout(mapPanel);
    gl_mapPanel.setHorizontalGroup(
      gl_mapPanel.createParallelGroup(Alignment.LEADING)
        .addGap(0, 318, Short.MAX_VALUE)
    );
    gl_mapPanel.setVerticalGroup(
      gl_mapPanel.createParallelGroup(Alignment.LEADING)
        .addGap(0, 249, Short.MAX_VALUE)
    );
    mapPanel.setLayout(gl_mapPanel);
    splitPane.setDividerLocation(100);
  }

}
