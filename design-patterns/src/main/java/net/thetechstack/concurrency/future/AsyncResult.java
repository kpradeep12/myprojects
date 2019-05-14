package net.thetechstack.concurrency.future;

public interface AsyncResult<T> {
    void await() throws InterruptedException;
}
