package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {
//    the Future class represents a future result of an asynchronous computation
// – a result that will eventually appear in the Future after the processing is complete.
    //    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public Future<Integer> calculate(Integer input) {
        return executorService.submit(() -> { //call method
            Thread.sleep(1000);
            return input * input;
        });
    }

    private static Future<Integer> future = new SquareCalculator().calculate(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        lala();
    }

    private static void lala() throws InterruptedException, ExecutionException {
        SquareCalculator squareCalculator = new SquareCalculator();

        Future<Integer> future1 = squareCalculator.calculate(10);
        Future<Integer> future2 = squareCalculator.calculate(100);

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println(result1 + " and " + result2);
    }

    private static void ceva() throws InterruptedException, ExecutionException {
        while (!future.isDone()) {
            System.out.println("Calculating ...");
            Thread.sleep(300);
        }
        Integer result = future.get(); //get() will block the execution until the task is complete.
        System.out.println("Result: " + result);

        boolean canceled = future.cancel(true);
    }
}
