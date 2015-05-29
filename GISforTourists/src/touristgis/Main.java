package touristgis;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import touristgis.maps.TouristMap;
import touristgis.places.*;
import touristgis.places.facilities.*;
import touristgis.places.touristplaces.*;

public class Main {

  private static TouristMap testMap = new TouristMap("", "resources/sanfrancisco.tpk");

  public JComponent createUI() throws Exception {
    JComponent contentPane = testMap.getMapGUI();

    return contentPane;
  }

  public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          Main mapExtentApp = new Main();
          JFrame appWindow = mapExtentApp.createWindow();
          appWindow.add(mapExtentApp.createUI());
          appWindow.setVisible(true);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    Thread.sleep(2000);

    testMap.setPlacesLoader(new PlaceManager("resources/places.ser"));
  }

  private JFrame createWindow() {
    JFrame window = new JFrame("Basic Map Application");
    window.setSize(1000, 600);
    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().setLayout(new BorderLayout());
    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent windowEvent) {
        super.windowClosing(windowEvent);
      }
    });
    return window;
  }
}
