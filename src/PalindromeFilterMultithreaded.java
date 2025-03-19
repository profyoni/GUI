import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PalindromeFilterMultithreaded {
    public String[] filterPalindromes(String[] list) {
        List<String> al = Collections.synchronizedList(new ArrayList<String>());
        int THREADS_TOTAL = 2;
        int t1_start = 0, t1_end = list.length / 2;
        int t2_start = t1_end, t2_end = list.length;

        // In Java 19+, ExecutorService implements AutoCloseable
        // The try-with-resources will properly await termination
        try (ExecutorService executor = Executors.newFixedThreadPool(THREADS_TOTAL)) {
            executor.submit(new PartialPal(list, t1_start, t1_end, al));
            executor.submit(new PartialPal(list, t2_start, t2_end, al));

            // In Java 19+, close() on ExecutorService will call shutdown() and awaitTermination()
        }

        return al.toArray(new String[0]); // Using empty array is more efficient in modern Java
    }

    // Make the method static so it can be accessed from the inner class
    private static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Example usage
        String[] testStrings = {"radar", "hello", "level", "world", "madam", "civic"};
        PalindromeFilterMultithreaded filter = new PalindromeFilterMultithreaded();
        String[] palindromes = filter.filterPalindromes(testStrings);

        System.out.println("Palindromes found:");
        for (String palindrome : palindromes) {
            System.out.println(palindrome);
        }
    }

    private class PartialPal implements Runnable {
        private String[] list;
        private int start, end;
        List<String> al;

        public PartialPal(String[] list, int start, int end, List<String> al) {
            this.list = list;
            this.start = start;
            this.end = end;
            this.al = al;
        }

        public void run() {
            for (int i = start; i < end; i++) {
                if (isPalindrome(list[i])) {
                    al.add(list[i]);
                }
            }
        }
    }
}