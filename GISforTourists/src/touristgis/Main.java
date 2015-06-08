package touristgis;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import touristgis.maps.TouristMap;
import touristgis.maps.TouristMapFactory;
import touristgis.places.*;

public class Main {
  public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException {

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ApplicationView frame = new ApplicationView();
          frame.getListModel().addElement("sanfrancisco");
          frame.getListModel().addElement("sandiego");
          
          frame.setMapFactory(new TouristMapFactory());
          
          frame.setVisible(true);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
