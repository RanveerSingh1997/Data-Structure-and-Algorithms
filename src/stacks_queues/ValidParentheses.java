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

}
