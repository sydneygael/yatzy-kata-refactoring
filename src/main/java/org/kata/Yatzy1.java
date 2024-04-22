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

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
            return 20;
        return 0;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;


        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}



