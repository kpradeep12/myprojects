package net.thetechstack.concurrency.future;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class App {
    public static void main(String[] args) throws InterruptedException {
        AsyncExecutor executor = new ThreadAsyncExecutor();

        System.out.println("-- Main thread started --");

        AsyncResult<Boolean> task1 = executor.submit(task(true, 3), (val, exc) -> {
            System.out.println("Result of task1 -> " + val);
            exc.ifPresent(System.err::println);
        });
        task1.await();

        AsyncResult<String> task2 = executor.submit(task("Hello", 5), (val, exc) -> {
            System.out.println("Result of task2 -> " + val);
            exc.ifPresent(System.err::println);
        });

        AsyncResult<Integer> task3 = executor.submit(taskWithExeption(3, 2), (val, exc) -> {
            System.out.println("Result of task3 -> " + val);
            exc.ifPresent(System.err::println);
        });

        System.out.println("-- Main thread complete --");
    }

    private static <T> Callable<T> task(T value, int seconds) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return value;
        };
    }
    private static <T> Callable<T> taskWithExeption(T value, int seconds) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
                throw new Exception("execution failed");
            } catch (InterruptedException e) {
                throw e;
            }
        };
    }
}
