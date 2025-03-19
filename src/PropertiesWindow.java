import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWindow extends JFrame {
    public static void main(String[] args) {
        PropertiesWindow window = new PropertiesWindow();
    }
    public PropertiesWindow() {
        super("Properties");

        setSize(600, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                Properties prop = new Properties();
                try {
                    prop.load(new FileInputStream("window.properties"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                int x = Integer.parseInt(prop.getProperty("LOCATION_X"));
                int y = Integer.parseInt(prop.getProperty("LOCATION_Y"));

                PropertiesWindow.this.setLocation(x,y);




            }
            public void windowClosing(WindowEvent e) {
                Point location = PropertiesWindow.this.getLocation();
                int x = location.x;
                int y = location.y;

                Properties prop = new Properties();
                prop.setProperty("LOCATION_X", String.valueOf(x));
                prop.setProperty("LOCATION_Y", "" + y);

                try {
                    prop.store(new FileOutputStream("window.properties"), null);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                System.exit(0);
            }
        });
    }
}
