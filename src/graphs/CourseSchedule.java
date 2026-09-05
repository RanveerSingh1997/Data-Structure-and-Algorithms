package graphs;

/**
 * ============================================================================
 * Problem: Course Schedule
 * LeetCode #207 | Difficulty: Medium
 * Company: Google Interview Question (Topological Sort / Cycle Detection)
 * Link: https://leetcode.com/problems/course-schedule/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
 * you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 *
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: numCourses = 2, prerequisites = [[1, 0]]
 *   Output: true
 *   Explanation: There are a total of 2 courses to take.
 *                To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2 (Deadlock / Cycle):
 *   Input: numCourses = 2, prerequisites = [[1, 0], [0, 1]]
 *   Output: false
 *   Explanation: Course 1 requires course 0, but course 0 requires course 1.
 *                A circular dependency exists, so you can never finish both courses.
 *
 * Example 3:
 *   Input: numCourses = 4, prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
 *   Output: true
 *   Explanation: 0 -> 1 -> 3 and 0 -> 2 -> 3. No cycle exists.
 *
 * ⚙️ CONSTRAINTS:
 *  - 1 <= numCourses <= 2000
 *  - 0 <= prerequisites.length <= 5000
 *  - prerequisites[i].length == 2
 *  - 0 <= ai, bi < numCourses
 *  - All the pairs prerequisites[i] are unique.
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - This is fundamentally a Directed Graph Cycle Detection problem.
 *  - Approach A: Kahn's Algorithm (BFS with in-degrees).
 *    1. Build adjacency list: `prereq -> course`.
 *    2. Calculate in-degree for every course.
 *    3. Enqueue all courses with inDegree == 0.
 *    4. While queue is not empty, poll course, decrement neighbors' in-degrees.
 *       If a neighbor's in-degree reaches 0, enqueue it.
 *    5. If total courses visited == numCourses, then return true!
 *  - Approach B: DFS with 3-Coloring (0 = unvisited, 1 = visiting, 2 = visited).
 */
public class CourseSchedule {

    /**
     * Determines whether all courses can be finished without cyclical dependencies.
     *
     * @param numCourses    total number of courses
     * @param prerequisites list of [course, prerequisite] pairs
     * @return true if all courses can be finished; false otherwise
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // TODO: Implement your solution here
        return false;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        CourseSchedule solver = new CourseSchedule();

        System.out.println("=== Testing: LeetCode 207 - Course Schedule ===");

        // Test 1: [[1, 0]] -> true
        int[][] prereq1 = {{1, 0}};
        boolean res1 = solver.canFinish(2, prereq1);
        System.out.println("Test 1 Result: " + res1 + " | Expected: true");
        if (res1) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [[1, 0], [0, 1]] -> false (cycle)
        int[][] prereq2 = {{1, 0}, {0, 1}};
        boolean res2 = solver.canFinish(2, prereq2);
        System.out.println("Test 2 Result: " + res2 + " | Expected: false");
        if (!res2) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Diamond DAG [[1,0],[2,0],[3,1],[3,2]] -> true
        int[][] prereq3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        boolean res3 = solver.canFinish(4, prereq3);
        System.out.println("Test 3 Result: " + res3 + " | Expected: true");
        if (res3) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
