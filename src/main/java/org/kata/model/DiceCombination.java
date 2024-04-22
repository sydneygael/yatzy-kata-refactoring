package org.kata.model;

public record DiceCombination(int d1, int d2, int d3, int d4, int d5) {

    public DiceCombination {
        if (d1 < 1 || d2 < 1 || d3 < 1 || d4 < 1 || d5 < 1) {
            throw new IllegalArgumentException("Dice values cannot be less than 1.");
        }
    }
    public int sum() {
        return d1 + d2 + d3 + d4 + d5;
    }
}
