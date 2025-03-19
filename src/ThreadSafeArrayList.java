public class ThreadSafeArrayList<T> {
    private T[] bs;
    private int ip;

    public ThreadSafeArrayList(){
        this(10); // telescoping c-tor
    }
    public ThreadSafeArrayList(int initialCapacity) {
        bs = (T[]) new Object[initialCapacity];
    }

    public boolean add(T t) {
        if (ip >= bs.length) {
            grow();
        }
        bs[ip] = t;
        ip++;
        return true;
    }

    private synchronized void grow() { //TODO
        T [] newbs = (T[]) new Object[bs.length * 2 + 1];
        System.arraycopy(bs, 0, newbs, 0, bs.length);
        bs = newbs;
    }

    public T get(int index) {
        if (index < 0 || index >= bs.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + bs.length);
        }
        return bs[index];
    }
}
