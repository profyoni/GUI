public class Singleton {
    private static Singleton theInstance = null;
    private Singleton() {
        // private so noone but within this class can construct it
    }
    public static Singleton getInstance() {
       if (theInstance == null) {
           theInstance = new Singleton();
       }
        return theInstance;
    }
}
