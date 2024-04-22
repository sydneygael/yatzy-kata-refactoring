package org.kata;

import org.kata.model.DiceCombination;

public class Yatzy1 {

    private final DiceCombination diceCombination;

    public Yatzy1(DiceCombination diceCombination) {
        this.diceCombination = diceCombination;
    }


    public int chance() {
        return diceCombination.sum();
    }

    public int yatzy() {
        return diceCombination.yatzy();
    }

    public int ones() {
        return diceCombination.countDice(1);
    }

    public int twos() {
        return diceCombination.countDice(2) * 2;
    }

    public int threes() {
        return diceCombination.countDice(3) * 3;
    }

    public int fours() {
        return diceCombination.countDice(4) * 4;
    }

    public int fives() {
        return diceCombination.countDice(5) * 5;
    }

    public int sixes() {
        return diceCombination.countDice(6) * 6;
    }

    public int pairs() {
        var pairs = diceCombination.findPairs();
        if (pairs.isEmpty()) {
            return 0;
        }
        return pairs.get(0) * 2;
    }

    public int twoPairs() {
        var pairs = diceCombination.findPairs();
        if (pairs.size() >= 2) {
            return pairs.stream()
                    .mapToInt(pair -> pair * 2)
                    .sum();
        }
        return 0;
    }

    public int threeOfAKind() {
        return diceCombination.numberOfDiceGreaterThan(3)
                .stream()
                .findFirst()
                .map(n -> n * 3)
                .orElse(0);
    }

    public int fourOfAKind() {
        return diceCombination.numberOfDiceGreaterThan(4)
                .stream()
                .findFirst()
                .map(n -> n * 4)
                .orElse(0);
    }

    public int smallStraight() {
        return diceCombination.isSmallStraight() ? 15 : 0;
    }

    public int largeStraight() {
        return diceCombination.isLargeStraight() ? 20 : 0;
    }

    public int fullHouse() {
        return diceCombination.isFullHouse() ? diceCombination.sum() : 0;
    }
}



