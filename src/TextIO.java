import java.io.*;

public class TextIO {
    public static void main(String[] args) {
        // Write to file
//        try {
//            FileWriter writer = new FileWriter("test.txt");
//            writer.write("Hello World\nThis is line 2");
//            writer.close();
//            System.out.println("Successfully wrote to file");
//        } catch (IOException e) {
//            System.out.println("An error occurred while writing");
//            e.printStackTrace();
//        }

        // try with resources  / Autocloseable
        try (FileWriter writer = new FileWriter("test.txt")) {
            writer.write("Hello World\nThis is line 2");
            System.out.println("Successfully wrote to file");
        } catch (IOException e) {
            System.out.println("An error occurred while writing");
            e.printStackTrace();
        }

        // Read from file
        try {
            FileReader reader = new FileReader("test.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading");
            e.printStackTrace();
        }
    }
}