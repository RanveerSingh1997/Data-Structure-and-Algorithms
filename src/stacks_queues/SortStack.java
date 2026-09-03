package stacks_queues;

import utils.StackTemplate;

public class SortStack {
    static void main(String[] args) {
        StackTemplate<Integer> stack;

        System.out.println("These tests confirm sortStack sorts");
        System.out.println("the stack so the TOP is the LOWEST value.");
        System.out.println("printStack() shows the stack from top");
        System.out.println("to bottom (smallest to largest).");
        System.out.println();

        // Test 1: Empty stack
        System.out.println("Test 1: Empty Stack");
        stack = new StackTemplate<>();
        sortStack(stack);
        System.out.println("Expected (top to bottom): empty");
        stack.printStack();
        System.out.println();

        // Test 2: Single element
        System.out.println("Test 2: Single Element");
        stack = new StackTemplate<>();
        stack.push(5);
        sortStack(stack);
        System.out.println("Expected (top to bottom): 5");
        stack.printStack();
        System.out.println();

        // Test 3: Unsorted stack
        System.out.println("Test 3: Unsorted Stack");
        stack = new StackTemplate<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        sortStack(stack);
        System.out.println("Expected (top to bottom): 1, 2, 3, 4");
        stack.printStack();
        System.out.println();

        // Test 4: Already sorted
        System.out.println("Test 4: Already Sorted Stack");
        stack = new StackTemplate<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        sortStack(stack);
        System.out.println("Expected (top to bottom): 1, 2, 3, 4");
        stack.printStack();
        System.out.println();

        // Test 5: Reverse sorted
        System.out.println("Test 5: Reverse Sorted Stack");
        stack = new StackTemplate<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        sortStack(stack);
        System.out.println("Expected (top to bottom): 1, 2, 3, 4");
        stack.printStack();
        System.out.println();

        // Test 6: With duplicates
        System.out.println("Test 6: With Duplicates");
        stack = new StackTemplate<>();
        stack.push(3);
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        sortStack(stack);
        System.out.println("Expected (top to bottom): 1, 1, 2, 3, 3");
        stack.printStack();
        System.out.println();

        // Test 7: With negatives
        System.out.println("Test 7: With Negatives");
        stack = new StackTemplate<>();
        stack.push(-1);
        stack.push(3);
        stack.push(-5);
        stack.push(2);
        sortStack(stack);
        System.out.println("Expected (top to bottom): -5, -1, 2, 3");
        stack.printStack();
        System.out.println();

        /*
            EXPECTED OUTPUT:
            ----------------
            These tests confirm sortStack sorts
            the stack so the TOP is the LOWEST value.
            printStack() shows the stack from top
            to bottom (smallest to largest).

            Test 1: Empty Stack
            Expected (top to bottom): empty

            Test 2: Single Element
            Expected (top to bottom): 5
            5

            Test 3: Unsorted Stack
            Expected (top to bottom): 1, 2, 3, 4
            1
            2
            3
            4

            Test 4: Already Sorted Stack
            Expected (top to bottom): 1, 2, 3, 4
            1
            2
            3
            4

            Test 5: Reverse Sorted Stack
            Expected (top to bottom): 1, 2, 3, 4
            1
            2
            3
            4

            Test 6: With Duplicates
            Expected (top to bottom): 1, 1, 2, 3, 3
            1
            1
            2
            3
            3

            Test 7: With Negatives
            Expected (top to bottom): -5, -1, 2, 3
            -5
            -1
            2
            3
        */

    }

    public static void sortStack(StackTemplate<Integer> stack) {
        StackTemplate<Integer> tempStack = new StackTemplate<>();
        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            while (!tempStack.isEmpty() && current < tempStack.peek()) {
                stack.push(tempStack.pop());
            }
            tempStack.push(current);
        }
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }
}
