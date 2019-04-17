package com.pigumer.future15;

import com.pigumer.common.HogeHogeFunction;
import com.pigumer.common.HogeSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class App {
    private static Logger logger = LoggerFactory.getLogger("main");

    public static void main(String[] args) throws Exception {
        Executor executor = Executors.newFixedThreadPool(1);

        FutureTask<String> hogeTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String hoge = new HogeSupplier().get();
                logger.info(hoge);
                return hoge;
            }
        });


        logger.info("start");
        executor.execute(hogeTask);
        String hoge = hogeTask.get();
        logger.info(hoge);

        FutureTask<Integer> hogehogeTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Integer n = new HogeHogeFunction().apply(hoge);
                logger.info(String.valueOf(n));
                return n;
            }
        });
        executor.execute(hogehogeTask);
        Integer n = hogehogeTask.get();
        logger.info(String.valueOf(n));

        logger.info("final");

        ((ExecutorService) executor).shutdown();
    }
}
