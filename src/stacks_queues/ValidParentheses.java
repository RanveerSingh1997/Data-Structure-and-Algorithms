package stacks_queues;

import utils.StackTemplate;

import java.util.List;

public class ValidParentheses {
    public boolean isValid(String s) {
        List<Character> openParentheses = List.of('(', '{', '[');
        StackTemplate<Character> stack = new StackTemplate<>();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (openParentheses.contains(s.charAt(i))) {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                if (current == ')' && stack.peek() != '(') {
                    return false;
                }

                if (current == '}' && stack.peek() != '{') {
                    return false;
                }

                if (current == ']' && stack.peek() != '[') {
                    return false;
                }
                stack.pop();
            }

        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses solver = new ValidParentheses();

        String[] testCases = {"()", "()[]{}", "(]", "([)]", "{[]}", "[", "]", ""};
        boolean[] expected = {true, true, false, false, true, false, false, true};

        System.out.println("=== Testing ValidParentheses ===");
        for (int i = 0; i < testCases.length; i++) {
            boolean result = solver.isValid(testCases[i]);
            System.out.printf("Input: %-8s | Expected: %-5b | Output: %-5b | %s%n",
                    "\"" + testCases[i] + "\"", expected[i], result, (result == expected[i] ? "PASS" : "FAIL"));
        }
    }
}
