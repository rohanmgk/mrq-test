import org.example.BingoStripGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Unit tests for the BingoStripGenerator class.
 * These tests verify that the Bingo strip generation logic is working as expected
 */
public class BingoStripGeneratorTest {

    private static final BingoStripGenerator testObj = new BingoStripGenerator();

    /**
     * Test that the Bingo strip contains exactly 6 tickets.
     */
    @Test
    void check6TicketsInStrip() {
        var strip = testObj.generateStrip();
        assertEquals(5 + 1, strip.size());
    }

    /**
     * Test that each ticket has 3 rows and 9 columns.
     */
    @Test
    void check3RowsAnd9ColumnsInEachTicket() {
        var strip = testObj.generateStrip();
        assertEquals(6, strip.size());
        for (String[][] ticket: strip) {
            assertEquals(3, ticket.length);  // 3 rows
            for (String[] values : ticket) {
                assertEquals(9, values.length);  // 9 columns
            }
        }
    }

    /**
     * Test that the full Bingo strip contains exactly 90 numbers (from 1 to 90).
     * This verifies that all numbers between 1 and 90 are used exactly once.
     */
    @Test
    void checkFullStripContainsTotal90Numbers() {
        long totalCount = 0;
        var strip = testObj.generateStrip();
        assertEquals(6, strip.size());
        for (String[][] ticket: strip) {
            assertEquals(3, ticket.length);
            for (String[] values : ticket) {
                totalCount += Arrays.stream(values).filter(Objects::nonNull).count();  // Count non-null values
            }
        }
        assertEquals(90, totalCount);  // Ensure total count is 90
    }

    /**
     * Test that the Bingo strip contains all unique numbers from 1 to 90, and only those numbers.
     * This ensures that no number is repeated and that the correct numbers are used.
     */
    @Test
    void checkFullStripContainsUniqueNumbersFrom1To90() {
        var expectedNumbers = IntStream.rangeClosed(1, 90)
                .boxed()
                .map(String::valueOf)
                .collect(Collectors.toCollection(TreeSet::new));  // Create a set of expected numbers
        var actualNumbers = new TreeSet<String>();  // To store actual numbers from the strip
        var strip = testObj.generateStrip();
        assertEquals(6, strip.size());
        for (String[][] ticket: strip) {
            assertEquals(3, ticket.length);
            for (String[] values : ticket) {
                var numbers = Arrays.stream(values).filter(Objects::nonNull).toList();
                actualNumbers.addAll(numbers);  // Add numbers to actual numbers set
            }
        }
        assertEquals(expectedNumbers, actualNumbers);  // Ensure that expected and actual sets match
    }

    /**
     * Test that each row in every ticket contains exactly 5 numbers and 4 empty spots.
     * This ensures the correct distribution of numbers and empty spots in each row.
     */
    @Test
    void checkEachRowContains5NumbersAnd4Empty() {
        var strip = testObj.generateStrip();
        assertEquals(6, strip.size());
        for (String[][] ticket: strip) {
            assertEquals(3, ticket.length);
            for (String[] values : ticket) {
                assertEquals(5, Arrays.stream(values).filter(Objects::nonNull).count());  // 5 numbers per row
                assertEquals(4, Arrays.stream(values).filter(Objects::isNull).count());    // 4 empty spots per row
            }
        }
    }

    /**
     * Test that the values in each column of the Bingo ticket come from the correct column range.
     * This ensures that the numbers in each column are from the appropriate range (e.g., column 0 has numbers from 1-9).
     */
    @Test
    void checkColumnsValues() {
        var columnValues = Map.of(
                0, List.of("1", "2", "3", "4", "5", "6", "7", "8", "9"),
                1, List.of("10", "11", "12", "13", "14", "15", "16", "17", "18", "19"),
                2, List.of("20", "21", "22", "23", "24", "25", "26", "27", "28", "29"),
                3, List.of("30", "31", "32", "33", "34", "35", "36", "37", "38", "39"),
                4, List.of("40", "41", "42", "43", "44", "45", "46", "47", "48", "49"),
                5, List.of("50", "51", "52", "53", "54", "55", "56", "57", "58", "59"),
                6, List.of("60", "61", "62", "63", "64", "65", "66", "67", "68", "69"),
                7, List.of("70", "71", "72", "73", "74", "75", "76", "77", "78", "79"),
                8, List.of("80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90"));

        var strip = testObj.generateStrip();
        assertEquals(6, strip.size());
        for (String[][] ticket: strip) {
            assertEquals(3, ticket.length);
            for (String[] values : ticket) {
                assertEquals(9, values.length);  // Ensure 9 columns per ticket
                for (int col = 0; col < values.length; col++) {
                    var value = values[col];
                    assertTrue(value == null || columnValues.get(col).contains(value));  // Ensure value is within allowed range for that column
                }
            }
        }
    }

    /**
     * Test that no column contains more than two empty spots.
     * This ensures that there are not too many empty spots in any column, maintaining the correct distribution.
     */
    @Test
    void checkNoThreeBlanksInTicketColumn() {
        var strip = testObj.generateStrip();
        assertEquals(6, strip.size());
        for (String[][] ticket: strip) {
            assertEquals(3, ticket.length);
            var blankColumnCount = new HashMap<Integer, Integer>();
            for (String[] values : ticket) {
                for (int col = 0; col < values.length; col++) {
                    if (values[col] == null && blankColumnCount.get(col) == null) {
                        blankColumnCount.put(col, 1);  // Track blank spots in each column
                    }
                    else if (values[col] == null && blankColumnCount.get(col) != null) {
                        blankColumnCount.put(col, blankColumnCount.get(col) + 1);  // Increment blank spot count for the column
                    }
                }
            }
            var maxBlankColumnCount = Collections.max(blankColumnCount.values());  // Find the column with the most blank spots
            assertTrue(maxBlankColumnCount < 3);  // Ensure that no column has more than 2 blank spots
        }
    }
}
