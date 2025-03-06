import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            ThreadSafety.list.add(i);
        }
    }
}

public class ThreadSafety {
    public static List<Integer> list = Collections.synchronizedList( new ArrayList<>());
    public static void main(String[] args) throws InterruptedException {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start(); // creates an OS thread and it then calls run() IN THAT thread
        t2.start();

        //Thread.sleep(10);
        t1.join();
        t2.join();

        System.out.println(list.size() + " should be 2M");

    }
}
