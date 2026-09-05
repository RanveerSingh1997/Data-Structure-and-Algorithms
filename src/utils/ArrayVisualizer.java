package utils;

import java.util.Collection;
import java.util.List;

/**
 * ============================================================================
 * Utility: ArrayVisualizer & Algorithmic State Renderer
 * ============================================================================
 *
 * Provides ASCII terminal visualization for:
 *  1. Two Pointers (pointers L and R converging or chasing)
 *  2. Sliding Window (window box [======] over array or string)
 *  3. Binary Search (low, mid, high pointers with target inspection)
 *  4. Monotonic Stack (element progression and current stack frames)
 *
 * Run directly to see visual demos:
 *   java -cp out utils.ArrayVisualizer
 */
public class ArrayVisualizer {

    // ANSI Colors for enhanced terminal visualization (with automatic fallback)
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String AMBER = "\u001B[38;5;214m";
    public static final String AMBER_BOLD = "\u001B[1;38;5;214m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";

    private static boolean useColor = true;

    public static void setUseColor(boolean enabled) {
        useColor = enabled;
    }

    private static String color(String c, String text) {
        return useColor ? (c + text + RESET) : text;
    }

    /**
     * Visualizes two pointers over an integer array.
     */
    public static void printTwoPointers(int[] arr, int left, int right, String annotation) {
        if (arr == null || arr.length == 0) return;

        int n = arr.length;
        int[] widths = new int[n];
        for (int i = 0; i < n; i++) {
            widths[i] = Math.max(String.valueOf(arr[i]).length(), String.valueOf(i).length());
        }

        // 1. Indices
        StringBuilder sbIdx = new StringBuilder("Index:  ");
        for (int i = 0; i < n; i++) {
            sbIdx.append(String.format("%" + widths[i] + "d ", i));
        }

        // 2. Array Values
        StringBuilder sbVal = new StringBuilder("Array: [");
        for (int i = 0; i < n; i++) {
            String valStr = String.format("%" + widths[i] + "d", arr[i]);
            if (i == left || i == right) {
                sbVal.append(color(BOLD + CYAN, valStr));
            } else {
                sbVal.append(valStr);
            }
            if (i < n - 1) sbVal.append(", ");
        }
        sbVal.append("]");

        // 3. Pointer Arrows
        StringBuilder sbPtr = new StringBuilder("        ");
        for (int i = 0; i < n; i++) {
            if (i == left && i == right) {
                sbPtr.append(color(BOLD + MAGENTA, "^"));
                appendSpaces(sbPtr, widths[i] + (i < n - 1 ? 1 : 0));
            } else if (i == left) {
                sbPtr.append(color(BOLD + GREEN, "^"));
                appendSpaces(sbPtr, widths[i] + (i < n - 1 ? 1 : 0));
            } else if (i == right) {
                sbPtr.append(color(BOLD + RED, "^"));
                appendSpaces(sbPtr, widths[i] + (i < n - 1 ? 1 : 0));
            } else {
                appendSpaces(sbPtr, widths[i] + (i < n - 1 ? 2 : 1));
            }
        }

        // 4. Pointer Labels
        StringBuilder sbLabel = new StringBuilder("        ");
        for (int i = 0; i < n; i++) {
            if (i == left && i == right) {
                sbLabel.append(color(BOLD + MAGENTA, "LR"));
                appendSpaces(sbLabel, Math.max(0, widths[i] - 1) + (i < n - 1 ? 1 : 0));
            } else if (i == left) {
                sbLabel.append(color(BOLD + GREEN, "L"));
                appendSpaces(sbLabel, widths[i] + (i < n - 1 ? 1 : 0));
            } else if (i == right) {
                sbLabel.append(color(BOLD + RED, "R"));
                appendSpaces(sbLabel, widths[i] + (i < n - 1 ? 1 : 0));
            } else {
                appendSpaces(sbLabel, widths[i] + (i < n - 1 ? 2 : 1));
            }
        }

        System.out.println(color(AMBER_BOLD, "┌── Two Pointers Snapshot ────────────────────────────────────────────────"));
        System.out.println(sbIdx.toString());
        System.out.println(sbVal.toString());
        System.out.println(sbPtr.toString());
        System.out.println(sbLabel.toString());
        if (annotation != null && !annotation.isEmpty()) {
            System.out.println(color(BOLD, "ℹ State: ") + annotation);
        }
        System.out.println(color(AMBER_BOLD, "└─────────────────────────────────────────────────────────────────────────"));
    }

    /**
     * Visualizes a sliding window [left .. right] over a String.
     */
    public static void printSlidingWindow(String s, int left, int right, String annotation) {
        if (s == null || s.isEmpty()) return;

        int n = s.length();
        StringBuilder sbIdx = new StringBuilder("Index:  ");
        StringBuilder sbVal = new StringBuilder("Chars: [");
        StringBuilder sbBox = new StringBuilder("        ");

        for (int i = 0; i < n; i++) {
            sbIdx.append(String.format("%2d ", i));
            char c = s.charAt(i);
            if (i >= left && i <= right) {
                sbVal.append(color(BOLD + GREEN, " " + c + " "));
            } else {
                sbVal.append(" " + c + " ");
            }

            if (i == left && i == right) {
                sbBox.append(color(BOLD + CYAN, "[=]"));
            } else if (i == left) {
                sbBox.append(color(BOLD + CYAN, "[=="));
            } else if (i == right) {
                sbBox.append(color(BOLD + CYAN, "==]"));
            } else if (i > left && i < right) {
                sbBox.append(color(BOLD + CYAN, "==="));
            } else {
                sbBox.append("   ");
            }
        }
        sbVal.append("]");

        String sub = (left >= 0 && right < n && left <= right) ? s.substring(left, right + 1) : "";

        System.out.println(color(AMBER_BOLD, "┌── Sliding Window Snapshot ──────────────────────────────────────────────"));
        System.out.println(sbIdx.toString());
        System.out.println(sbVal.toString());
        System.out.println(sbBox.toString());
        System.out.println(color(BOLD, "Current Window: ") + "\"" + color(BOLD + AMBER, sub) + "\" (length = " + sub.length() + ")");
        if (annotation != null && !annotation.isEmpty()) {
            System.out.println(color(BOLD, "ℹ Step Info: ") + annotation);
        }
        System.out.println(color(AMBER_BOLD, "└─────────────────────────────────────────────────────────────────────────"));
    }

    /**
     * Visualizes Binary Search with low, mid, high markers.
     */
    public static void printBinarySearch(int[] arr, int low, int mid, int high, int target, String annotation) {
        if (arr == null || arr.length == 0) return;

        int n = arr.length;
        int[] widths = new int[n];
        for (int i = 0; i < n; i++) {
            widths[i] = Math.max(String.valueOf(arr[i]).length(), String.valueOf(i).length());
        }

        StringBuilder sbIdx = new StringBuilder("Index:  ");
        StringBuilder sbVal = new StringBuilder("Array: [");
        for (int i = 0; i < n; i++) {
            sbIdx.append(String.format("%" + widths[i] + "d ", i));
            String valStr = String.format("%" + widths[i] + "d", arr[i]);
            if (i == mid) {
                sbVal.append(color(BOLD + AMBER, valStr));
            } else if (i >= low && i <= high) {
                sbVal.append(color(BOLD + YELLOW, valStr));
            } else {
                sbVal.append(color("\u001B[90m", valStr)); // dimmed
            }
            if (i < n - 1) sbVal.append(", ");
        }
        sbVal.append("]");

        StringBuilder sbPtr = new StringBuilder("        ");
        for (int i = 0; i < n; i++) {
            if (i == mid) {
                sbPtr.append(color(BOLD + AMBER, "M"));
            } else if (i == low) {
                sbPtr.append(color(BOLD + BLUE, "L"));
            } else if (i == high) {
                sbPtr.append(color(BOLD + RED, "H"));
            } else {
                sbPtr.append(" ");
            }
            appendSpaces(sbPtr, widths[i] + (i < n - 1 ? 1 : 0));
        }

        System.out.println(color(AMBER_BOLD, "┌── Binary Search Snapshot ───────────────────────────────────────────────"));
        System.out.println(sbIdx.toString());
        System.out.println(sbVal.toString());
        System.out.println(sbPtr.toString());
        System.out.println(color(BOLD, "Target: ") + target + " | low=" + low + ", mid=" + mid + " (arr[mid]=" + (mid >= 0 && mid < n ? arr[mid] : "?") + "), high=" + high);
        if (annotation != null && !annotation.isEmpty()) {
            System.out.println(color(BOLD, "ℹ Decision: ") + annotation);
        }
        System.out.println(color(AMBER_BOLD, "└─────────────────────────────────────────────────────────────────────────"));
    }

    /**
     * Visualizes Monotonic Stack status during progression.
     */
    public static void printStackState(int[] arr, int currentIndex, List<?> stack, String annotation) {
        System.out.println(color(AMBER_BOLD, "┌── Monotonic Stack Snapshot ─────────────────────────────────────────────"));
        if (arr != null && currentIndex >= 0 && currentIndex < arr.length) {
            System.out.println("Processing index " + color(BOLD + AMBER, String.valueOf(currentIndex))
                    + " with value " + color(BOLD + YELLOW, String.valueOf(arr[currentIndex])));
        }
        System.out.println("Stack (top to bottom):");
        if (stack == null || stack.isEmpty()) {
            System.out.println("   |   [ EMPTY ]   |");
        } else {
            for (int i = stack.size() - 1; i >= 0; i--) {
                String item = String.valueOf(stack.get(i));
                System.out.printf("   | %-17s | %s%n", item, (i == stack.size() - 1 ? "<- TOP" : ""));
            }
        }
        System.out.println("   +-------------------+");
        if (annotation != null && !annotation.isEmpty()) {
            System.out.println(color(BOLD, "ℹ Action: ") + annotation);
        }
        System.out.println(color(AMBER_BOLD, "└─────────────────────────────────────────────────────────────────────────"));
    }

    private static void appendSpaces(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }
    }

    /**
     * Demo runnable showcasing all visualizations.
     */
    public static void main(String[] args) {
        System.out.println(BOLD + "\n=================================================================");
        System.out.println("       ArrayVisualizer: Algorithmic State Visualizer Demos       ");
        System.out.println("=================================================================\n" + RESET);

        // 1. Two Pointers Demo (Container with Most Water)
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        printTwoPointers(heights, 1, 8, "Left height 8 > Right height 7. Area = min(8, 7) * 7 = 49. Move R left.");

        System.out.println();

        // 2. Sliding Window Demo
        String text = "pwwkew";
        printSlidingWindow(text, 2, 4, "Window 'wke' has all distinct characters. Max length so far = 3.");

        System.out.println();

        // 3. Binary Search Demo
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        printBinarySearch(rotated, 0, 3, 6, 0, "Left half [4..7] is sorted. Target 0 is NOT in [4..7], so search right half.");

        System.out.println();

        // 4. Monotonic Stack Demo
        List<String> demoStack = List.of("idx:1 (temp:74)", "idx:2 (temp:75)");
        printStackState(new int[]{73, 74, 75, 71, 69, 72, 76, 73}, 5, demoStack, "Current 72 popped 69 and 71. 72 < 75, so stop popping and push index 5.");
    }
}
