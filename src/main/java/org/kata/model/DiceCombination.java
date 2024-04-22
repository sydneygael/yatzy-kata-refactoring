package org.kata.model;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record DiceCombination(int d1, int d2, int d3, int d4, int d5) {

    public DiceCombination {
        if (d1 < 1 || d2 < 1 || d3 < 1 || d4 < 1 || d5 < 1) {
            throw new IllegalArgumentException("Dice values cannot be less than 1.");
        }
    }

    public int sum() {
        return d1 + d2 + d3 + d4 + d5;
    }

    public int yatzy() {
        var countingGroup = Stream.of(d1, d2, d3, d4, d5)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return countingGroup
                .values()
                .stream()
                .filter(d -> d == 5)
                .findFirst()
                .map(e -> 50)
                .orElse(0);
    }
}
