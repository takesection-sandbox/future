package com.pigumer.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class CallableFunction implements Callable<Integer> {
    private static final Logger logger = LoggerFactory.getLogger(CallableFunction.class);

    private final Integer message;

    public CallableFunction(Integer message) {
        this.message = message;
    }

    @Override
    public Integer call() throws Exception {
        Integer r = message * 2;
        logger.info(String.valueOf(r));
        return r;
    }
}
