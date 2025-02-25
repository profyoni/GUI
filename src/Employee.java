import java.io.*;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private transient String password; // Sensitive - won't be serialized
    private int age;
    private Address address;

    // Constructor
    public Employee(String name, String password, int age, Address address) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.address = address;
    }

    // Custom serialization method
    private void writeObject(ObjectOutputStream out) throws IOException {
        // Call the default serialization for the non-transient fields
        out.defaultWriteObject();

        // Custom encryption for password (simplified example)
        if (password != null) {
            out.writeObject(encrypt(password));
        }

        // We could add additional custom serialization logic here
    }

    // Custom deserialization method
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Call the default deserialization for the non-transient fields
        in.defaultReadObject();

        // Custom decryption for password (simplified example)
        String encryptedPwd = (String) in.readObject();
        this.password = decrypt(encryptedPwd);

        // Validate the deserialized state
        validateState();
    }

    private void validateState() throws InvalidObjectException {
        if (name == null || name.isEmpty()) {
            throw new InvalidObjectException("Invalid name");
        }
        if (age < 0 || age > 150) {
            throw new InvalidObjectException("Invalid age");
        }
    }

    // Simplified encryption/decryption methods
    private String encrypt(String data) {
        // Implementation of encryption
        return data + "_encrypted";
    }

    private String decrypt(String data) {
        // Implementation of decryption
        return data.replace("_encrypted", "");
    }

    // Support class
    public static class Address implements Serializable {
        private static final long serialVersionUID = 1L;
        private String street;
        private String city;

        public Address(String street, String city) {
            this.street = street;
            this.city = city;
        }
    }

    // Example usage
    public static void main(String[] args) {
        try {
            // Create an employee
            Address address = new Address("123 Main St", "Boston");
            Employee emp = new Employee("John Doe", "secret123", 30, address);

            // Serialize
            FileOutputStream fileOut = new FileOutputStream("employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(emp);
            out.close();
            fileOut.close();

            // Deserialize
            FileInputStream fileIn = new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Employee empDeserialized = (Employee) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}