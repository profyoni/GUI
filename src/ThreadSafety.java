import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyThread extends Thread {
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            ThreadSafety.list.add(i);
        }
    }
}


class MyTask implements Runnable {
    private String threadName;
    MyTask(String name) {
        threadName = name;
    }
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            System.out.println( String.format("%s : %d", threadName, i));
        }
    }
}

public class ThreadSafety {
    volatile int x;
    private synchronized void  setX(int i){ x = i; } // AtomicInt
    public static List<Integer> list = Collections.synchronizedList( new ArrayList<>());
    public static void main(String[] args) throws InterruptedException {

        //  Java19
        try (ExecutorService executor = Executors.newFixedThreadPool(11)) {

            for (int i = 0; i < 10; i++) {
                executor.submit(new MyTask(((char)('A' + i))+""));
            }

        }

// Pre Java19
        ExecutorService executor = Executors.newFixedThreadPool(2);

//        executor.submit(new MyTask());
//        executor.submit(new MyTask());
//
//        // pause until all threads complete
//        while (! executor.isTerminated()) {
//            // pause
//            for (int i = 0 ;i<999_999_999;i++ ) // spin loop
//            {
//                // do something
//            }
//            executor.shutdown();
//            Thread.sleep(100);
//        }


        // OLD WAY
//        MyThread t1 = new MyThread();
//        MyThread t2 = new MyThread();
//
//        t1.start(); // creates an OS thread and it then calls run() IN THAT thread
//        t2.start();
//
//        //Thread.sleep(10);
//        t1.join(); // pauses the current MAIN thread until t1's run method exits
//        t2.join();

        System.out.println(list.size() + " should be 2M");

    }
}
