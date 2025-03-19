import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class UsingProperties {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        //saveProps(props);
        readProps(props);
    }

    private static void saveProps(Properties props) throws IOException {
        props.setProperty("Location_x","100");
        props.setProperty("Location_y","200");

        props.store( new FileOutputStream("props.properties"), null);
    }
    private static void readProps(Properties props) throws IOException {
        props.load( new FileInputStream("props.properties"));
        System.out.println(props.getProperty("Location_x"));
        System.out.println(props.getProperty("Location_y"));
    }
}
