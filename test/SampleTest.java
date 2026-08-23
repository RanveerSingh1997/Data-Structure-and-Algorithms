import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Sample JUnit Test.
 * Note: Place your cursor on @Test or import statements, hit Alt + Enter (or Option + Enter),
 * and select "Add JUnit 5.8.1 to classpath" to download the testing library.
 */
public class SampleTest {

    // A sample solution method to test
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    @Test
    public void testTwoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};
        
        assertArrayEquals(expected, twoSum(nums, target), "The twoSum method should return the correct indices.");
    }

    @Test
    public void testTwoSumNoSolution() {
        int[] nums = {1, 2, 3};
        int target = 10;
        int[] expected = {};

        assertArrayEquals(expected, twoSum(nums, target), "Should return empty array when no solution exists.");
    }
}
