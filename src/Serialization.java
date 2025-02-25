import java.io.*;
import java.util.ArrayList;

class Person implements Serializable
 {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class Serialization {
    public static void read(){
        String filePath = "person.dat";

        try ( // try with resources
                FileInputStream fileIn = new FileInputStream(filePath);
                BufferedInputStream bufferIn = new BufferedInputStream(fileIn);
                ObjectInputStream objectIn = new ObjectInputStream(bufferIn)
        ) {
            ArrayList<Person> persons = (ArrayList<Person>) objectIn.readObject(); // Reading object
            System.out.println("Object read from file: " + persons);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
      //  write();
        read();
    }

        public static void write(){
        ArrayList<Person> pal = new ArrayList<Person>();
        for (int i = 0; i < 100; i++) {
            Person p = new Person("P" + i, i);
            pal.add(p);
        }
        //Person person = new Person("John Doe", 30);
        String filePath = "person.dat";

        try (
                FileOutputStream fileOut = new FileOutputStream(filePath);
                BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
                ObjectOutputStream objectOut = new ObjectOutputStream(bufferOut)
        ) {
            objectOut.writeObject(pal); // Writing object
            System.out.println("Object written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
