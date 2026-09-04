package stacks_queues;

import java.util.Stack;

public class MakeStringGreat {

    static void main() {
        MakeStringGreat makeStringGreat = new MakeStringGreat();
        System.out.println(makeStringGreat.makeGood("leEeetcode"));
        System.out.println(makeStringGreat.makeGood("abBAcC"));

    }

    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty()) {
                boolean isBad = Character.toLowerCase(stack.peek()) == Character.toLowerCase(c) && Character.isLowerCase(stack.peek()) != Character.isLowerCase(c);
                if (isBad) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder string = new StringBuilder();
        while (!stack.isEmpty()) {
            string.append(stack.pop());
        }
        return string.reverse().toString();
    }
}
