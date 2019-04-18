package com.pigumer.future8_1;

import com.pigumer.common.CallableFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class App {
    private static final Logger logger = LoggerFactory.getLogger("main");
    private static final Executor executor = Executors.newFixedThreadPool(2);

    private static void send(Integer message) {
        try {
            CompletableFuture<Integer> task = new CompletableFuture<>();
            task.supplyAsync(() -> {
                        try {
                            int r = new CallableFunction(message).call();
                            logger.info(String.format("%d - 1: %d", message, r));
                            return r;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    },
                    executor).thenApplyAsync(n -> {
                try {
                    int r = new CallableFunction(n).call();
                    logger.info(String.format("%d - 2: %d", message, r));
                    return r;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, executor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        IntStream.range(1, 4).forEach(n -> send(n));

        Thread.sleep(10000);
        ((ExecutorService) executor).shutdown();
    }
}
