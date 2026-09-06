package Stacks_Queues;

import Utils.StackTemplate;

public class ReverseString {
    public static String reverseString(String str) {
        if (str == null) return null;
        StackTemplate<Character> stackList = new StackTemplate<>();
        for (int i = 0; i < str.length(); i++) {
            stackList.push(str.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!stackList.isEmpty()) {
            sb.append(stackList.pop());
        }
        return sb.toString();
    }

    // Kept for backward compatibility
    public static String reverString(String str) {
        return reverseString(str);
    }

    static void main(String[] args) {
        System.out.println(reverseString("ABCDER"));
    }
}
