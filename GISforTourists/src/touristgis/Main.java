package touristgis;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import touristgis.maps.TouristMap;
import touristgis.places.*;

public class Main {

  private static TouristMap testMap = new TouristMap("", "resources/sanfrancisco/sanfrancisco.tpk");

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

    testMap.setPlacesLoader(new PlaceManager("resources/sanfrancisco/places.ser"));
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
