package net.thetechstack.concurrency.future;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;

public interface AsyncExecutor {
    <T> AsyncResult<T> submit(Callable<T> task, BiConsumer<T, Optional<Exception>> callback);
}
