package com.pigumer.common;

import java.util.function.Function;

public class HogeHogeFunction implements Function<String, Integer> {
    @Override
    public Integer apply(String s) {
        return s.length();
    }
}
