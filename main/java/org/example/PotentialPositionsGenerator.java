package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The PotentialPositionsGenerator class is responsible for generating potential positions for Bingo tickets.
 * These positions represent possible placements of numbers within each Bingo ticket, organized into three rows
 * for each ticket. The positions are shuffled to introduce randomness
 */
public class PotentialPositionsGenerator {

    /**
     * Generates a list of potential positions for Bingo numbers. Each set of positions is represented as a 2D array,
     * where each sub-array contains a list of positions corresponding to a specific group in the ticket.
     * The generated list is shuffled to ensure randomness.
     *
     * @return A list of 2D arrays representing potential positions for Bingo tickets.
     *         Each element in the list is an array of 3 sub-arrays, each containing positions for a specific group.
     */
    public static List<int[][]> getPotentialPositions() {
        var positions = new ArrayList<int[][]>();

        // Define static potential positions for each ticket
        positions.add(new int[][]{new int[]{1, 2, 5, 7, 8}, new int[]{1, 3, 4, 6, 8}, new int[]{0, 3, 4, 5, 7}});
        positions.add(new int[][]{new int[]{2, 3, 5, 6, 8}, new int[]{0, 1, 4, 5, 8}, new int[]{2, 3, 4, 6, 7}});
        positions.add(new int[][]{new int[]{1, 3, 4, 5, 8}, new int[]{0, 1, 4, 5, 7}, new int[]{0, 2, 6, 7, 8}});
        positions.add(new int[][]{new int[]{0, 3, 4, 6, 7}, new int[]{0, 1, 2, 5, 8}, new int[]{1, 2, 6, 7, 8}});
        positions.add(new int[][]{new int[]{3, 4, 6, 7, 8}, new int[]{0, 2, 4, 5, 7}, new int[]{1, 2, 3, 6, 8}});
        positions.add(new int[][]{new int[]{0, 1, 2, 4, 6}, new int[]{1, 2, 3, 5, 6}, new int[]{0, 3, 5, 7, 8}});

        // Shuffle the list to randomize the order of potential positions
        Collections.shuffle(positions);
        return positions;
    }
}
