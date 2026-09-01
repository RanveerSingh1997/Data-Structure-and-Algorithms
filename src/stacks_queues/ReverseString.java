package stacks_queues;

import utils.StackTemplate;

public class ReverseString {
    public static String reverString(String str) {
        StackTemplate<Character> stackList = new StackTemplate<>();
        for (int i = 0; i < str.length(); i++) {
            stackList.push(str.charAt(i));
        }
        StringBuilder reverseString = new StringBuilder();
        while (!stackList.isEmpty()) {
            reverseString.append(stackList.pop());
        }
        return reverseString.toString();
    }

    static void main(String[] args) {
        System.out.println(reverString("ABCDER"));
    }
}
