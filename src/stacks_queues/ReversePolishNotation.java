package stacks_queues;

import utils.StackTemplate;

/**
 * ============================================================================
 * Problem: Evaluate Reverse Polish Notation
 * LeetCode #150 | Difficulty: Medium
 * Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * You are given an array of strings `tokens` that represents an arithmetic expression in a
 * Reverse Polish Notation (Postfix Notation).
 *
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *   - The valid operators are '+', '-', '*', and '/'.
 *   - Each operand may be an integer or another expression.
 *   - The division between two integers always truncates toward zero.
 *   - There will not be any division by zero.
 *   - The input represents a valid arithmetic expression in a reverse polish notation.
 *
 * 📥 EXAMPLES:
 *   Example 1: tokens = ["2","1","+","3","*"] -> ((2 + 1) * 3) -> 9
 *   Example 2: tokens = ["4","13","5","/","+"] -> (4 + (13 / 5)) -> 6
 *   Example 3: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"] -> 22
 *
 * ⚙️ CONSTRAINTS:
 *   - 1 <= tokens.length <= 10^4
 *   - tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 *
 * ⏱️ COMPLEXITY:
 *   - Time Complexity: O(N), where N is tokens.length.
 *   - Space Complexity: O(N) for the operand stack.
 */
public class ReversePolishNotation {

    public static void main(String[] args) {
        ReversePolishNotation solver = new ReversePolishNotation();
        System.out.println("=== Testing: LeetCode 150 - Evaluate Reverse Polish Notation ===");
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println("Test 1: " + solver.evalRPN(tokens1) + " (Expected: 9)");

        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println("Test 2: " + solver.evalRPN(tokens2) + " (Expected: 6)");
    }

    public int evalRPN(String[] tokens) {
        StackTemplate<Integer> stack = new StackTemplate<>();
        for (String str : tokens) {
            if (!stack.isEmpty()) {
                boolean m = str.equals("*");
                boolean d = str.equals("/");
                boolean a = str.equals("+");
                boolean s = str.equals("-");
                boolean operators = m || d || a || s;
                if (operators) {
                    int result;
                    int value1 = stack.pop();
                    int value2 = stack.pop();
                    if (m) {
                        result = value2 * value1;
                    } else if (d) {
                        result = value2 / value1;
                    } else if (a) {
                        result = value2 + value1;
                    } else {
                        result = value2 - value1;
                    }
                    stack.push(result);
                } else {
                    stack.push(Integer.parseInt(str));
                }
            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }
}
