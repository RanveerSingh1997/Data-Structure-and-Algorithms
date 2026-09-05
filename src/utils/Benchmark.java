package utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * ============================================================================
 * Utility: Micro-Benchmark & Memory Profiler
 * ============================================================================
 *
 * Designed for DSA interview prep to empirically evaluate:
 *  1. Execution runtime (Mean time per op, Total time, Ops/sec).
 *  2. Memory footprint & heap allocations (Bytes, KB, MB).
 *  3. Head-to-head algorithm comparisons (e.g. StringBuilder vs Stack,
 *     int[128] vs HashMap, ArrayDeque vs java.util.Stack).
 */
public class Benchmark {

    public static class BenchmarkResult {
        public final String name;
        public final long totalNanos;
        public final double meanMillis;
        public final double opsPerSec;
        public final long memoryBytes;

        public BenchmarkResult(String name, long totalNanos, int iterations, long memoryBytes) {
            this.name = name;
            this.totalNanos = totalNanos;
            this.meanMillis = (totalNanos / 1_000_000.0) / iterations;
            this.opsPerSec = (iterations / (totalNanos / 1_000_000_000.0));
            this.memoryBytes = memoryBytes;
        }

        public String getFormattedMemory() {
            if (memoryBytes < 1024) return memoryBytes + " B";
            if (memoryBytes < 1024 * 1024) return String.format("%.2f KB", memoryBytes / 1024.0);
            return String.format("%.2f MB", memoryBytes / (1024.0 * 1024.0));
        }
    }

    /**
     * Measures runtime and memory consumption of a task over N iterations.
     * Includes an automatic JVM warmup phase.
     *
     * @param name       human-readable name of the approach
     * @param task       the task runnable
     * @param iterations number of iterations to measure
     * @return BenchmarkResult containing time and memory metrics
     */
    public static BenchmarkResult run(String name, Runnable task, int iterations) {
        // 1. Warmup phase (allows JIT compiler to optimize bytecode)
        int warmupRuns = Math.min(iterations / 5, 2000);
        for (int i = 0; i < warmupRuns; i++) {
            task.run();
        }

        // 2. Memory measurement prep
        System.gc();
        try {
            Thread.sleep(20);
        } catch (InterruptedException ignored) {}

        long memBefore = getUsedMemory();
        long startTime = System.nanoTime();

        // 3. Timed execution phase
        for (int i = 0; i < iterations; i++) {
            task.run();
        }

        long endTime = System.nanoTime();
        long memAfter = getUsedMemory();

        long elapsedNanos = endTime - startTime;
        long memDelta = Math.max(0, memAfter - memBefore);

        return new BenchmarkResult(name, elapsedNanos, iterations, memDelta);
    }

    /**
     * Compares two approaches side-by-side and prints an ASCII comparison report.
     *
     * @param name1      name of first approach
     * @param task1      first approach runnable
     * @param name2      name of second approach
     * @param task2      second approach runnable
     * @param iterations number of executions
     */
    public static void compare(String name1, Runnable task1, String name2, Runnable task2, int iterations) {
        BenchmarkResult r1 = run(name1, task1, iterations);
        BenchmarkResult r2 = run(name2, task2, iterations);

        System.out.println("================================================================================");
        System.out.println("                         PERFORMANCE BENCHMARK REPORT                           ");
        System.out.println("================================================================================");
        System.out.printf("Iterations: %,d runs each%n%n", iterations);
        System.out.printf("%-24s | %-12s | %-16s | %-12s%n", "Approach", "Mean Time", "Ops / Sec", "Memory Delta");
        System.out.println("-------------------------+--------------+------------------+------------");
        System.out.printf("%-24s | %9.4f ms | %13.0f/s | %10s%n", r1.name, r1.meanMillis, r1.opsPerSec, r1.getFormattedMemory());
        System.out.printf("%-24s | %9.4f ms | %13.0f/s | %10s%n", r2.name, r2.meanMillis, r2.opsPerSec, r2.getFormattedMemory());
        System.out.println("-------------------------+--------------+------------------+------------");

        if (r1.totalNanos < r2.totalNanos) {
            double speedup = (double) r2.totalNanos / r1.totalNanos;
            System.out.printf("🏆 WINNER: %s is %.2fx FASTER than %s!%n", r1.name, speedup, r2.name);
        } else {
            double speedup = (double) r1.totalNanos / r2.totalNanos;
            System.out.printf("🏆 WINNER: %s is %.2fx FASTER than %s!%n", r2.name, speedup, r1.name);
        }
        System.out.println("================================================================================\n");
    }

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    // ========================================================================
    // 🧪 DEMO RUNNER
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== Benchmark Demo: Comparing Classic DSA Implementations ===\n");

        // Comparison 1: int[128] Array vs HashMap for Sliding Window / Lookup
        int iterations = 100_000;
        compare(
                "int[128] Direct Array",
                () -> {
                    int[] lookup = new int[128];
                    lookup['a'] = 1;
                    lookup['z'] = 26;
                    int x = lookup['a'] + lookup['z'];
                },
                "HashMap<Character, Integer>",
                () -> {
                    Map<Character, Integer> map = new HashMap<>();
                    map.put('a', 1);
                    map.put('z', 26);
                    int x = map.get('a') + map.get('z');
                },
                iterations
        );

        // Comparison 2: ArrayDeque vs java.util.Stack
        compare(
                "ArrayDeque (Modern Stack)",
                () -> {
                    Deque<Integer> dq = new ArrayDeque<>();
                    for (int i = 0; i < 50; i++) dq.push(i);
                    while (!dq.isEmpty()) dq.pop();
                },
                "java.util.Stack (Legacy)",
                () -> {
                    Stack<Integer> st = new Stack<>();
                    for (int i = 0; i < 50; i++) st.push(i);
                    while (!st.isEmpty()) st.pop();
                },
                iterations
        );
    }
}
