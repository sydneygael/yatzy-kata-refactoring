package org.kata.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record DiceCombination(int d1, int d2, int d3, int d4, int d5) {

    public DiceCombination {
        if (d1 < 1 || d2 < 1 || d3 < 1 || d4 < 1 || d5 < 1) {
            throw new IllegalArgumentException("Dice values cannot be less than 1.");
        }
    }

    public int sum() {
        return streamOfDices()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int yatzy() {
        return counts().containsValue(5L) ? 50 : 0;
    }

    private Map<Integer, Long> counts() {
        return streamOfDices()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private Stream<Integer> streamOfDices() {
        return Stream.of(d1, d2, d3, d4, d5);
    }

    public int countDice(int dice) {
        return counts().getOrDefault(dice, 0L).intValue();
    }

    public List<Integer> getNumberOfDiceGreaterThan(int n) {
        return counts().entrySet().stream()
                .filter(entry -> entry.getValue() >= n)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public List<Integer> findPairs() {
        return getNumberOfDiceGreaterThan(2);
    }

    private Set<Integer> getDistinctValues() {
        return streamOfDices().collect(Collectors.toSet());
    }

    public boolean isSmallStraight() {
        var distinctValues = getDistinctValues();
        return IntStream.rangeClosed(1, 5).allMatch(distinctValues::contains);
    }

    public boolean isLargeStraight() {
        var distinctValues = getDistinctValues();
        return IntStream.rangeClosed(2, 6).allMatch(distinctValues::contains);
    }

    public boolean isFullHouse() {
        boolean hasThreeOfAKind = !getNumberOfDiceGreaterThan(3).isEmpty();
        boolean hasTwoOfAKind = !findPairs().isEmpty();
        boolean isNotYates = yatzy() == 0;
        return hasThreeOfAKind && hasTwoOfAKind && isNotYates;
    }
}
