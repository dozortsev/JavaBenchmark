package com.dozortsev.testjmh.test1;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.SingleShotTime)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@State(Scope.Thread)
@Threads(1)
@Fork(1)
public class StringConcat {

    private static final String EMPTY = "";
    private static final String TEST_LINE = "Lorem ipsum dolor sit amet consectetur adipisicing elit Sequi vitae";

    private final String[] snippets = new String[10_000];

    @Setup public void setUp() {
        for (int i = 0; i < snippets.length; i++) {
            snippets[i] = TEST_LINE;
        }
    }

    private String concat1(String... snippets) {
        String result = EMPTY;
        for (String snippet : snippets) {
            result += snippet;
        }
        return result;
    }

    private String concat2(String... snippets) {
        String result = EMPTY;
        for (String snippet : snippets) {
            result = result.concat(snippet);
        }
        return result;
    }

    private String concat3(String... snippets) {
        StringBuilder result = new StringBuilder();
        for (String snippet : snippets) {
            result.append(snippet);
        }
        return result.toString();
    }

    private String concat4(String... snippets) {
        return String.join(EMPTY, snippets);
    }

    @Benchmark public void concat1() {
        concat1(snippets);
    }

    @Benchmark public void concat2() {
        concat2(snippets);
    }

    @Benchmark public void concat3() {
        concat3(snippets);
    }

    @Benchmark public void concat4() {
        concat4(snippets);
    }
}