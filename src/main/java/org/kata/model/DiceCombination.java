package org.kata.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        return counts().values()
                .stream()
                .filter(d -> d == 5)
                .findFirst()
                .map(e -> 50)
                .orElse(0);
    }

    private Map<Integer, Long> counts() {
        return Stream.of(d1, d2, d3, d4, d5)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public int countDice(int dice) {
        return counts().getOrDefault(dice, 0L).intValue();
    }

    public List<Integer> numberOfDiceGreaterThan(int n) {
        return counts().entrySet().stream()
                .filter(entry -> entry.getValue() >= n)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public List<Integer> findPairs() {
        return numberOfDiceGreaterThan(2);
    }
}
