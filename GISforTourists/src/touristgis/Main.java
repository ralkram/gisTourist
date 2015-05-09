package touristgis;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import touristgis.maps.TouristMap;
import touristgis.places.PlaceManager;

public class Main {

  private static TouristMap testMap = new TouristMap("", "sanfrancisco.tpk");

  public JComponent createUI() throws Exception {
    JComponent contentPane = testMap.getMapGUI();

    return contentPane;
  }

  public static void main(String[] args) throws InterruptedException {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          // instance of this application
          Main mapExtentApp = new Main();
          // create the UI, including the map, for the application.
          JFrame appWindow = mapExtentApp.createWindow();
          appWindow.add(mapExtentApp.createUI());
          appWindow.setVisible(true);
        }
        catch (Exception e) {
          // on any error, display the stack trace.
          e.printStackTrace();
        }
      }
    });

    Thread.sleep(2000);

    testMap.setPlacesLoader(new PlaceManager("places.ser"));
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
