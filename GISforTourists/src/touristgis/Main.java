package touristgis;

import java.awt.EventQueue;
import java.io.IOException;

import touristgis.maps.TouristMapFactory;

public class Main {
  public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException {

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ApplicationView frame = new ApplicationView();
          frame.getListModel().addElement("San Francisco");
          frame.getListModel().addElement("San Diego");

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
