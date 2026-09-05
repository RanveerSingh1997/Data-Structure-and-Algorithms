package stacks_queues;

import java.util.Stack;

public class RemoveStarsFromString {

    static void main() {
        RemoveStarsFromString removeStarsFromString = new RemoveStarsFromString();
        System.out.println(removeStarsFromString.removeStars("leee*tc***DE"));
        System.out.println(removeStarsFromString.removeStars("leet**cod*e"));
        System.out.println(removeStarsFromString.removeStars("erase*****"));

    }

    /// We can Optmise it without constraint using String Builder Direclty and Applying
    ///        for (char c : s.toCharArray()) {
    ///             if (c == '*') {
    ///                 result.deleteCharAt(result.length() - 1);
    ///             } else {
    ///                 result.append(c);
    ///             }
    ///         }
    public String removeStars(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '*' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }

}
