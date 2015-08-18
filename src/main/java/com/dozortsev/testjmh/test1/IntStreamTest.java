package com.dozortsev.testjmh.test1;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@State(Scope.Thread)
@Threads(1)
@Fork(1)
public class IntStreamTest {

    private static final int STEP = 2;

    private static final int START_VALUE = 1;
    private static final int END_VALUE = 1_000_000;

    @Benchmark public void measureRange() {
        IntStream.range(START_VALUE, END_VALUE).filter(el -> el % 2 == 1).sum();
    }

    @Benchmark public void measureIterate() {
        IntStream.iterate(START_VALUE, el -> el + STEP).limit(END_VALUE / STEP).sum();
    }
}