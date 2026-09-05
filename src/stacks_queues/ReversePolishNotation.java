package stacks_queues;


import utils.StackTemplate;

public class ReversePolishNotation {
    static void main() {
        ReversePolishNotation reversePolishNotation = new ReversePolishNotation();
        System.out.println(reversePolishNotation.evalRPN(new String[]{"1", "2", "+", "3", "*", "4", "-"}));
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
