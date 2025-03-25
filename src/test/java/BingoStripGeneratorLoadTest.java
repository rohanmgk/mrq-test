import org.example.BingoStripGenerator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Load test for the BingoStripGenerator class.
 * This test evaluates the performance of the `generateStrip()` method by measuring the time it takes to generate 10,000 strips
 */
public class BingoStripGeneratorLoadTest {
    private static final BingoStripGenerator testObj = new BingoStripGenerator();

    /**
     * Test to measure the time taken to generate 10,000 Bingo strips.
     * It verifies that the time taken to generate all the strips is less than 1 second.
     */
    @Test
    void testTimeToGenerate10kStrips() {
        // Record the start time of the test
        var startTime = System.currentTimeMillis();

        // Generate 10,000 Bingo strips
        for (int i = 0; i < 10000; i++) {
            var strip = testObj.generateStrip(); // Generate a Bingo strip
            assertEquals(6, strip.size());  // Ensure that each strip contains 6 tickets
        }

        // Record the end time of the test
        var endTime = System.currentTimeMillis();

        // Calculate the elapsed time
        long elapsedTime = endTime - startTime;
        System.out.println("Time taken to generate 10k strips: " + elapsedTime + " ms");

        // Assert that the time taken to generate 10,000 strips is less than 1 second (1000 ms)
        assertTrue(elapsedTime < 1000, "Time taken exceeds 1 second for generating 10,000 strips.");
    }
}
