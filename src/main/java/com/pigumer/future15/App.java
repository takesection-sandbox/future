package com.pigumer.future15;

import com.pigumer.common.CallableFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class App {
    private static final Logger logger = LoggerFactory.getLogger("main");
    private static final Executor executor = Executors.newFixedThreadPool(2);

    private static void send(Integer message) {
        try {
            FutureTask<Integer> task1 = new FutureTask<>(
                    new CallableFunction(message)
            );
            executor.execute(task1);
            Integer n = task1.get();
            logger.info(String.format("%d - 1: %d", message, n));
            FutureTask<Integer> task2 = new FutureTask<>(
                    new CallableFunction(n)
            );
            executor.execute(task2);
            Integer n2 = task2.get();
            logger.info(String.format("%d - 2: %d", message, n2));
        } catch (Exception e) {
            logger.error("error", e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        IntStream.range(1, 100).forEach(n -> {
            logger.info(String.format("send %d", n));
            send(n);
        });

        ((ExecutorService) executor).shutdown();
    }
}
