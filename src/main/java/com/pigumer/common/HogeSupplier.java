package com.pigumer.common;

import java.util.function.Supplier;

public class HogeSupplier implements Supplier<String> {

    @Override
    public String get() {
        return "Hoge";
    }
}
