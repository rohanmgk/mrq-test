package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The BingoStripGenerator class is responsible for generating a strip of Bingo tickets.
 * It uses a number pool and potential positions to generate a list of tickets where each ticket
 * consists of 3 rows and 9 columns, with numbers placed in specific positions.
 */
public class BingoStripGenerator {

    // Constant values for Bingo strip configuration
    private static final int COLUMNS = 9;          // Number of columns in each ticket
    private static final int ROWS = 3;             // Number of rows in each ticket
    private static final int TOTAL_TICKETS_IN_STRIP = 6;  // Total number of tickets to generate in the strip

    /**
     * Generates a Bingo strip consisting of multiple Bingo tickets.
     *
     * @return A list of Bingo tickets, where each ticket is represented as a 2D array of strings.
     */
    public List<String[][]> generateStrip() {
        // Get the number pool and potential positions for each ticket
        var numbersPool = NumberPoolGenerator.getNumbersPool();
        var potentialPositions = PotentialPositionsGenerator.getPotentialPositions();

        // Generate the tickets using a stream and return the result as a list
        return IntStream.range(0, TOTAL_TICKETS_IN_STRIP)
                .mapToObj(index -> generateTicket(numbersPool, potentialPositions, index))
                .collect(Collectors.toList());
    }

    /**
     * Generates a single Bingo ticket based on the provided numbers pool and potential positions.
     *
     * @param numbersPool A map of column positions to a deque of numbers that can be used in the ticket.
     * @param potentialPositions A list of potential positions for each Bingo ticket.
     * @param ticketNo The ticket number to generate (used for selecting positions).
     * @return A 2D array representing the Bingo ticket, with numbers placed in specific positions.
     */
    private String[][] generateTicket(Map<Integer, Deque<String>> numbersPool, List<int[][]> potentialPositions, int ticketNo) {
        var ticket = new String[ROWS][COLUMNS];  // Create a new ticket array
        var ticketPositions = potentialPositions.get(ticketNo);  // Get the potential positions for this ticket

        // Fill the ticket with numbers based on the positions
        for (int row = 0; row < ROWS; row++) {
            var rowPositions = ticketPositions[row];  // Get positions for the current row
            for (int rowPosition : rowPositions) {
                // Pop a number from the corresponding position's number pool and assign it to the ticket
                ticket[row][rowPosition] = numbersPool.get(rowPosition).pop();
            }
        }
        return ticket;
    }

    /**
     * The main method to run the Bingo ticket generation and print the resulting strip to the console.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main(String[] args) {
        // Call the prettyPrintBingoStrip method to generate and display the Bingo strip
        prettyPrintBingoStrip();
    }

    /**
     * Pretty prints the generated Bingo strip to the console, formatting each ticket and displaying it
     * with a clear separation between tickets.
     */
    private static void prettyPrintBingoStrip() {
        var ticketGenerator = new BingoStripGenerator();  // Create an instance of BingoStripGenerator
        var strip = ticketGenerator.generateStrip();  // Generate the Bingo strip

        // Loop through each ticket in the strip and print it
        for (String[][] ticket : strip) {
            System.out.println("----------------------------TICKET START----------------------------");

            // Loop through each row in the ticket
            for (String[] rows : ticket) {
                // Loop through each value in the row
                for (String value : rows) {
                    if (value == null) {
                        System.out.print("        ");  // Print empty spaces for null values (empty positions)
                        continue;
                    }
                    // Format the number to have a fixed width and print it
                    var formattedValue = String.format("%-8d", Integer.parseInt(value));
                    System.out.printf(formattedValue);
                }
                System.out.println();  // Move to the next row
            }
            System.out.println("----------------------------TICKET END------------------------------");
            System.out.println();  // Print an extra newline between tickets for better readability
        }
    }
}
