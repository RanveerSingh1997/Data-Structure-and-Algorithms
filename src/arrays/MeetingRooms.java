package arrays;

import java.util.Arrays;

/**
 * ============================================================================
 * Problem: Meeting Rooms
 * LeetCode #252 | Difficulty: Easy
 * Company: Google Interview Question (Foundational Interval Problem)
 * Link: https://leetcode.com/problems/meeting-rooms/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given an array of meeting time intervals where intervals[i] = [start_i, end_i],
 * determine if a person could attend all meetings (i.e. no two meetings overlap).
 *
 * Note: A meeting ending at time T does NOT conflict with a meeting starting at time T.
 * For example, [1, 5] and [5, 10] do not conflict.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: intervals = [[0, 30], [5, 10], [15, 20]]
 *   Output: false
 *   Explanation: [0, 30] conflicts with [5, 10] and [15, 20].
 *
 * Example 2:
 *   Input: intervals = [[7, 10], [2, 4]]
 *   Output: true
 *   Explanation: [2, 4] ends before [7, 10] starts, so both can be attended.
 *
 * Example 3:
 *   Input: intervals = [[1, 5], [5, 10], [10, 15]]
 *   Output: true
 *   Explanation: Back-to-back meetings at the exact boundary times do not conflict.
 *
 * ⚙️ CONSTRAINTS:
 *  - 0 <= intervals.length <= 10^4
 *  - intervals[i].length == 2
 *  - 0 <= start_i < end_i <= 10^6
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - If meetings were sorted by their start times, where could an overlap possibly occur?
 *  - After sorting, what condition between adjacent intervals `i-1` and `i` indicates an overlap?
 *  - Google follow-up: "How many conference rooms would be needed to host all meetings?"
 *    (LeetCode 253 - Meeting Rooms II).
 */
public class MeetingRooms {

    /**
     * Checks if a person can attend all scheduled meetings without conflict.
     *
     * @param intervals array of [start, end] meeting intervals
     * @return true if all meetings can be attended; false otherwise
     */
    public boolean canAttendMeetings(int[][] intervals) {
        // TODO: Implement your solution here
        return false;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        MeetingRooms solver = new MeetingRooms();

        System.out.println("=== Testing: LeetCode 252 - Meeting Rooms ===");

        // Test 1: [[0, 30], [5, 10], [15, 20]] -> false
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        boolean res1 = solver.canAttendMeetings(intervals1);
        System.out.println("Test 1 Result: " + res1 + " | Expected: false");
        if (!res1) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [[7, 10], [2, 4]] -> true
        int[][] intervals2 = {{7, 10}, {2, 4}};
        boolean res2 = solver.canAttendMeetings(intervals2);
        System.out.println("Test 2 Result: " + res2 + " | Expected: true");
        if (res2) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Touching boundaries [[1, 5], [5, 10]] -> true
        int[][] intervals3 = {{1, 5}, {5, 10}};
        boolean res3 = solver.canAttendMeetings(intervals3);
        System.out.println("Test 3 Result: " + res3 + " | Expected: true");
        if (res3) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
