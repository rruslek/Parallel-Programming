import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static final int THREADS = 10;
    public static final int COUNT = 2;
    public static final int PROC = 4;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(COUNT);
        MySemaphore mySemaphore =  new MySemaphore(COUNT);
        //runTask(semaphore);
        runTask(mySemaphore);
    }

    private static void runTask(Semaphore semaphore) {
        ExecutorService es = Executors.newFixedThreadPool(THREADS);

        List<Callable<String>> tasks = new ArrayList<>();
        List<Future<String>> results = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            tasks.add(() -> {
                String threadName = Thread.currentThread().getName();
                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println("Thread " + threadName + " acquired semaphore");
                semaphore.release();
                System.out.println("Thread " + threadName + " released semaphore");
                return "Thread " + threadName + " done";
            });
        }
        // invoke tasks
        try {
            results = es.invokeAll(tasks);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        //shutdown
        es.shutdown();
    }
}