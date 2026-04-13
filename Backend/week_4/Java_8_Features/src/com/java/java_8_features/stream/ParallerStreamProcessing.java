package com.java.java_8_features.stream;

import java.util.Arrays;
import java.util.List;

public class ParallerStreamProcessing {

    public static void main(String[] args) {

        List<Integer> numbers =
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers
                .parallelStream()
                .forEach(n -> {
                    System.out.println(
                            "Number : " + n +
                                    " | Thread : " + Thread.currentThread().getName()
                    );
                });
    }
}

