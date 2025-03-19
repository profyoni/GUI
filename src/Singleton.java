public class Singleton {
    private static Singleton theInstance = null;
    private Singleton() {
        // private so no one but within this class can construct it
    }
    public static Singleton getInstance() {
        synchronized (Singleton.class) {
            if (theInstance == null) {
                theInstance = new Singleton();
            }
            return theInstance;
        }
    }
}
