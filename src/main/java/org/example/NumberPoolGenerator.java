package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

/**
 * The NumberPoolGenerator class is responsible for generating a pool of Bingo numbers.
 * It creates a Map where each key is a column number (0-8), and the value is a deque of numbers
 * corresponding to the range of numbers for that column (e.g., 1-9 for column 0, 10-19 for column 1, etc.).
 * The numbers in each deque are shuffled to ensure randomness.
 */
public class NumberPoolGenerator {

    /**
     * Generates a Map of Bingo number pools, where each key represents a column and the corresponding
     * value is a deque of shuffled numbers.
     *
     * @return A Map where the key is an integer (representing the column) and the value is a deque of strings
     *         representing the Bingo numbers for that column.
     */
    public static Map<Integer, Deque<String>> getNumbersPool() {
        // Mapping of columns (0-8) to their corresponding number ranges
        return Map.of(
                0, getDeque(1, 2, 3, 4, 5, 6, 7, 8, 9), // Column 0: Numbers 1-9
                1, getDeque(10, 11, 12, 13, 14, 15, 16, 17, 18, 19), // Column 1: Numbers 10-19
                2, getDeque(20, 21, 22, 23, 24, 25, 26, 27, 28, 29), // Column 2: Numbers 20-29
                3, getDeque(30, 31, 32, 33, 34, 35, 36, 37, 38, 39), // Column 3: Numbers 30-39
                4, getDeque(40, 41, 42, 43, 44, 45, 46, 47, 48, 49), // Column 4: Numbers 40-49
                5, getDeque(50, 51, 52, 53, 54, 55, 56, 57, 58, 59), // Column 5: Numbers 50-59
                6, getDeque(60, 61, 62, 63, 64, 65, 66, 67, 68, 69), // Column 6: Numbers 60-69
                7, getDeque(70, 71, 72, 73, 74, 75, 76, 77, 78, 79), // Column 7: Numbers 70-79
                8, getDeque(80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90) // Column 8: Numbers 80-90
        );
    }

    /**
     * Creates a shuffled Deque from a given set of numbers.
     * The numbers are first boxed into Strings and shuffled to ensure randomness.
     *
     * @param values The values to be included in the deque. These are the numbers for a specific Bingo column.
     * @return A shuffled Deque of Strings representing the Bingo numbers.
     */
    private static Deque<String> getDeque(int... values) {
        var list = Arrays.stream(values)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(list); // Shuffle the list to ensure randomness
        return new ArrayDeque<>(list);
    }
}
