package net.thetechstack.concurrency.future;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

public class ThreadAsyncExecutor implements AsyncExecutor {

    @Override
    public <T> AsyncResult<T> submit(Callable<T> task, BiConsumer<T, Optional<Exception>> callback) {
        CompletableResult<T> result = new CompletableResult<>(callback);
        new Thread(() -> {
            try {
                result.setValue(task.call());
            } catch (Exception exc) {
                result.setException(exc);
            }
        }).start();
        return result;
    }

    private class CompletableResult<T> implements AsyncResult<T> {
        private static final int RUNNING = 1;
        private static final int FAILED = 2;
        private static final int COMPLETED = 3;
        private volatile int state = RUNNING;
        private final Object lock;
        private BiConsumer<T, Optional<Exception>> callback;

        CompletableResult(BiConsumer<T, Optional<Exception>> callback) {
            lock = new Object();
            this.callback = callback;
        }

        void setValue(T value) {
            this.state = COMPLETED;
            callback.accept(value, Optional.empty());
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        void setException(Exception exception) {
            this.state = FAILED;
            callback.accept(null, Optional.of(exception));
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        public void await() throws InterruptedException {
            synchronized (lock) {
                while (!isDone()) {
                    lock.wait();
                }
            }
        }

        private boolean isDone() {
            return state > RUNNING;
        }
    }
}
