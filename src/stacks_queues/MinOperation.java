package stacks_queues;

import java.util.Stack;

public class MinOperation {
    static void main() {
        MinOperation minOperation = new MinOperation();
        System.out.println(minOperation.minOperations(new String[]{"d1/", "d2/", "../", "d3/", "../", "d4/", "../", "d5/"}));
        System.out.println(minOperation.minOperations(new String[]{"d1/", "../", "../", "../"}));
        System.out.println(minOperation.minOperations(new String[]{"d1/", "d2/", "../", "d21/", "./"}));
    }

    public int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();
        for (String str : logs) {
            if (str.equals("../")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (str.equals("./")) {
                continue;
            } else {
                stack.push(str);
            }
        }
        return stack.size();
    }
}
