package org.kata;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.kata.model.DiceCombination;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Yatzy1Test {


    @ParameterizedTest
    @MethodSource("shouldTestChanceProvider")
    public void shouldTestChance(DiceCombination combination, int expectedScore) {
        assertEquals(expectedScore, new Yatzy1(combination).chance());
    }

    private static Stream<Arguments> shouldTestChanceProvider() {
        return Stream.of(
                Arguments.of(new DiceCombination(2, 3, 4, 5, 1), 15),
                Arguments.of(new DiceCombination(3, 3, 4, 5, 1), 16)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "4, 4, 4, 4, 4, 50",
            "6, 6, 6, 6, 6, 50",
            "6, 6, 6, 6, 3, 0"
    })
    public void yatzyShouldReturnExpectedScore(int d1, int d2, int d3, int d4, int d5, int expectedScore) {
        assertEquals(expectedScore, new Yatzy1(new DiceCombination(d1, d2, d3, d4, d5)).yatzy());
    }

    @ParameterizedTest
    @MethodSource("onesProvider")
    public void shouldTestOnes(DiceCombination combination, int expectedScore) {
        assertEquals(expectedScore, new Yatzy1(combination).ones());
    }

    public static Stream<Arguments> onesProvider() {
        return Stream.of(
                Arguments.of(new DiceCombination(1, 2, 3, 4, 5), 1),
                Arguments.of(new DiceCombination(1, 2, 1, 4, 5), 2),
                Arguments.of(new DiceCombination(6, 2, 2, 4, 5), 0),
                Arguments.of(new DiceCombination(1, 2, 1, 1, 1), 4)
        );
    }

    @ParameterizedTest
    @MethodSource("shouldTestTwosProvider")
    public void shouldTestTwos(DiceCombination combination, int expectedScore) {
        assertEquals(expectedScore, new Yatzy1(combination).twos());
    }

    public static Stream<Arguments> shouldTestTwosProvider() {
        return Stream.of(
                Arguments.of(new DiceCombination(1, 2, 3, 2, 6), 4),
                Arguments.of(new DiceCombination(2, 2, 2, 2, 2), 10)
        );
    }

    @ParameterizedTest
    @MethodSource("shouldTestThreesProvider")
    public void shouldTestThrees(DiceCombination combination, int expectedScore) {
        assertEquals(expectedScore, new Yatzy1(combination).threes());
    }

    public static Stream<Arguments> shouldTestThreesProvider() {
        return Stream.of(
                Arguments.of(new DiceCombination(1, 2, 3, 2, 3), 6),
                Arguments.of(new DiceCombination(2, 3, 3, 3, 3), 12)
        );
    }

    @ParameterizedTest
    @MethodSource("shouldTestFoursProvider")
    public void shouldTestFours(DiceCombination combination, int expectedScore) {
        assertEquals(expectedScore, new Yatzy1(combination).fours());
    }

    public static Stream<Arguments> shouldTestFoursProvider() {
        return Stream.of(
                Arguments.of(new DiceCombination(4, 4, 4, 5, 5), 12),
                Arguments.of(new DiceCombination(4, 4, 5, 5, 5), 8),
                Arguments.of(new DiceCombination(4, 5, 5, 5, 5), 4)
        );
    }

    @ParameterizedTest
    @MethodSource("shouldTestFivesProvider")
    public void shouldTestFives(DiceCombination combination, int expectedScore) {
        assertEquals(expectedScore, new Yatzy1(combination).fives());
    }

    public static Stream<Arguments> shouldTestFivesProvider() {
        return Stream.of(
                Arguments.of(new DiceCombination(4, 4, 4, 5, 5), 10),
                Arguments.of(new DiceCombination(4, 4, 5, 5, 5), 15),
                Arguments.of(new DiceCombination(4, 5, 5, 5, 5), 20)
        );
    }

    @ParameterizedTest
    @MethodSource("shouldTestSixesProvider")
    public void shouldTestSixes(DiceCombination combination, int expectedScore) {
        assertEquals(expectedScore, new Yatzy1(combination).sixes());
    }

    public static Stream<Arguments> shouldTestSixesProvider() {
        return Stream.of(
                Arguments.of(new DiceCombination(4, 4, 4, 5, 5), 0),
                Arguments.of(new DiceCombination(4, 4, 6, 5, 5), 6),
                Arguments.of(new DiceCombination(6, 5, 6, 6, 5), 18)
        );
    }
    @Test
    public void one_pair() {
        assertEquals(6, Yatzy1.score_pair(3, 4, 3, 5, 6));
        assertEquals(10, Yatzy1.score_pair(5, 3, 3, 3, 5));
        assertEquals(12, Yatzy1.score_pair(5, 3, 6, 6, 5));
    }

    @Test
    public void two_Pair() {
        assertEquals(16, Yatzy1.two_pair(3, 3, 5, 4, 5));
        assertEquals(16, Yatzy1.two_pair(3, 3, 5, 5, 5));
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, Yatzy1.three_of_a_kind(3, 3, 3, 4, 5));
        assertEquals(15, Yatzy1.three_of_a_kind(5, 3, 5, 4, 5));
        assertEquals(9, Yatzy1.three_of_a_kind(3, 3, 3, 3, 5));
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, Yatzy1.four_of_a_kind(3, 3, 3, 3, 5));
        assertEquals(20, Yatzy1.four_of_a_kind(5, 5, 5, 4, 5));
        assertEquals(9, Yatzy1.three_of_a_kind(3, 3, 3, 3, 3));
    }

    @Test
    public void smallStraight() {
        assertEquals(15, Yatzy1.smallStraight(1, 2, 3, 4, 5));
        assertEquals(15, Yatzy1.smallStraight(2, 3, 4, 5, 1));
        assertEquals(0, Yatzy1.smallStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void largeStraight() {
        assertEquals(20, Yatzy1.largeStraight(6, 2, 3, 4, 5));
        assertEquals(20, Yatzy1.largeStraight(2, 3, 4, 5, 6));
        assertEquals(0, Yatzy1.largeStraight(1, 2, 2, 4, 5));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy1.fullHouse(6, 2, 2, 2, 6));
        assertEquals(0, Yatzy1.fullHouse(2, 3, 4, 5, 6));
    }
}
