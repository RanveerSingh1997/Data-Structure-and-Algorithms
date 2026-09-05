package test;

import Hashing.RomanToInteger;
import arrays.Anagram;
import arrays.ContainsDuplicate;
import arrays.TopKFrequentElements;
import arrays.TwoSum;
import linked_list.*;
import stacks_queues.ReverseString;
import stacks_queues.SortStack;
import stacks_queues.ValidParentheses;
import utils.DoublyLinkedList;
import utils.LinkedList;
import utils.Node;
import utils.StackTemplate;

import java.util.Arrays;

public class AllTestsRunner {

    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("      RUNNING DSA SOLUTION TESTS          ");
        System.out.println("==========================================\n");

        testRomanToInteger();
        testTwoSum();
        testContainsDuplicate();
        testValidParentheses();
        testReverseString();
        testSortStack();
        testLinkedListOperations();
        testDoublyLinkedListOperations();
        testReverseLinkedList();
        testFindMiddleNode();
        testFindLoop();
        testRemoveDuplicates();

        System.out.println("\n==========================================");
        System.out.printf("Results: %d PASSED, %d FAILED%n", testsPassed, testsFailed);
        System.out.println("==========================================");
        if (testsFailed > 0) {
            System.exit(1);
        }
    }

    private static void assertEquals(Object expected, Object actual, String testName) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            System.out.println("  [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.printf("  [FAIL] %s - Expected: %s, Got: %s%n", testName, expected, actual);
            testsFailed++;
        }
    }

    private static void assertArrayEquals(int[] expected, int[] actual, String testName) {
        if (Arrays.equals(expected, actual)) {
            System.out.println("  [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.printf("  [FAIL] %s - Expected: %s, Got: %s%n", testName, Arrays.toString(expected), Arrays.toString(actual));
            testsFailed++;
        }
    }

    private static void assertTrue(boolean condition, String testName) {
        if (condition) {
            System.out.println("  [PASS] " + testName);
            testsPassed++;
        } else {
            System.err.println("  [FAIL] " + testName);
            testsFailed++;
        }
    }

    private static void assertFalse(boolean condition, String testName) {
        assertTrue(!condition, testName);
    }

    private static void testRomanToInteger() {
        System.out.println("Testing RomanToInteger:");
        RomanToInteger r = new RomanToInteger();
        assertEquals(1, r.romanToInteger("I"), "Roman 'I' == 1");
        assertEquals(3, r.romanToInteger("III"), "Roman 'III' == 3");
        assertEquals(4, r.romanToInteger("IV"), "Roman 'IV' == 4");
        assertEquals(9, r.romanToInteger("IX"), "Roman 'IX' == 9");
        assertEquals(58, r.romanToInteger("LVIII"), "Roman 'LVIII' == 58");
        assertEquals(1994, r.romanToInteger("MCMXCIV"), "Roman 'MCMXCIV' == 1994");
    }

    private static void testTwoSum() {
        System.out.println("\nTesting TwoSum:");
        TwoSum ts = new TwoSum();
        assertArrayEquals(new int[]{0, 1}, ts.twoSum(new int[]{2, 7, 11, 15}, 9), "TwoSum [2,7,11,15], target 9");
        assertArrayEquals(new int[]{1, 2}, ts.twoSum(new int[]{3, 2, 4}, 6), "TwoSum [3,2,4], target 6");
        assertArrayEquals(new int[]{0, 1}, ts.twoSum(new int[]{3, 3}, 6), "TwoSum [3,3], target 6");
        assertArrayEquals(new int[]{}, ts.twoSum(new int[]{1, 2, 3}, 10), "TwoSum no solution");
    }

    private static void testContainsDuplicate() {
        System.out.println("\nTesting ContainsDuplicate:");
        ContainsDuplicate cd = new ContainsDuplicate();
        assertTrue(cd.containsDuplicate(new int[]{1, 2, 3, 1}), "ContainsDuplicate with duplicates");
        assertFalse(cd.containsDuplicate(new int[]{1, 2, 3, 4}), "ContainsDuplicate without duplicates");
        assertTrue(cd.containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}), "ContainsDuplicate multi duplicates");
    }

    private static void testValidParentheses() {
        System.out.println("\nTesting ValidParentheses:");
        ValidParentheses vp = new ValidParentheses();
        assertTrue(vp.isValid("()"), "Valid '()'");
        assertTrue(vp.isValid("()[]{}"), "Valid '()[]{}'");
        assertFalse(vp.isValid("(]"), "Invalid '(]'");
        assertFalse(vp.isValid("([)]"), "Invalid '([)]'");
        assertTrue(vp.isValid("{[]}"), "Valid '{[]}'");
        assertFalse(vp.isValid("["), "Single opening bracket '['");
        assertFalse(vp.isValid("]"), "Single closing bracket ']'");
    }

    private static void testReverseString() {
        System.out.println("\nTesting ReverseString:");
        assertEquals("FEDCBA", ReverseString.reverseString("ABCDEF"), "Reverse 'ABCDEF'");
        assertEquals("", ReverseString.reverseString(""), "Reverse empty string");
    }

    private static void testSortStack() {
        System.out.println("\nTesting SortStack:");
        StackTemplate<Integer> s = new StackTemplate<>();
        s.push(3);
        s.push(1);
        s.push(4);
        s.push(2);
        SortStack.sortStack(s);
        // Top should be lowest: 1, 2, 3, 4
        assertEquals(1, s.pop(), "Sorted top element is 1");
        assertEquals(2, s.pop(), "Next sorted element is 2");
        assertEquals(3, s.pop(), "Next sorted element is 3");
        assertEquals(4, s.pop(), "Bottom element is 4");
    }

    private static void testLinkedListOperations() {
        System.out.println("\nTesting LinkedList:");
        LinkedList list = new LinkedList();
        list.addElements(new int[]{10, 20, 30});
        assertEquals(3, list.getLength(), "Length after adding 3 elements");
        assertTrue(list.insert(1, 15), "Insert at middle index 1 returns true");
        assertEquals(15, list.get(1).get_value(), "Value at index 1 is 15");
        Node removed = list.remove(1);
        assertEquals(15, removed.get_value(), "Removed value is 15");
        assertEquals(null, list.remove(10), "Remove invalid index returns null");
    }

    private static void testDoublyLinkedListOperations() {
        System.out.println("\nTesting DoublyLinkedList:");
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.append(10);
        dll.append(20);
        dll.append(30);
        assertTrue(dll.insert(1, 15), "DoublyLinkedList insert returns true");
        assertEquals(15, dll.get(1).get_value(), "DLL value at index 1 is 15");
        Node removed = dll.remove(1);
        assertEquals(15, removed.get_value(), "DLL removed value is 15");
        assertEquals(20, dll.get(1).get_value(), "DLL index 1 is now 20");
        assertEquals(3, dll.getLength(), "DLL length is 3");
    }

    private static void testReverseLinkedList() {
        System.out.println("\nTesting Reverse LinkedList:");
        Reverse rev = new Reverse();
        Node original = Node.fromArray(new int[]{1, 2, 3, 4, 5});
        Node reversed = rev.reverseList(original);
        assertEquals("5 -> 4 -> 3 -> 2 -> 1", reversed.toString(), "Reversed Node list");

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.set_next(n2);
        n2.set_next(n3);
        Node revIter = rev.reverseIterative(n1);
        assertEquals(3, revIter.get_value(), "Iterative reversed head value");
    }

    private static void testFindMiddleNode() {
        System.out.println("\nTesting FindMiddleNode:");
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.set_next(n2);
        n2.set_next(n3);
        n3.set_next(n4);
        n4.set_next(n5);

        Node mid = FindMiddleNode.findMiddleNode(n1);
        assertEquals(3, mid.get_value(), "Middle of 1->2->3->4->5 is 3");
    }

    private static void testFindLoop() {
        System.out.println("\nTesting FindLoop:");
        FindLoop fl = new FindLoop();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.set_next(n2);
        n2.set_next(n3);
        assertFalse(fl.hasLoop(n1), "No loop in acyclic list");

        n3.set_next(n1); // create cycle
        assertTrue(fl.hasLoop(n1), "Loop detected in cyclic list");
    }

    private static void testRemoveDuplicates() {
        System.out.println("\nTesting RemoveDuplicates:");
        RemoveDuplicates rd = new RemoveDuplicates();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(2);
        Node n4 = new Node(3);
        n1.set_next(n2);
        n2.set_next(n3);
        n3.set_next(n4);

        rd.removeDuplicates(n1);
        assertEquals(1, n1.get_value(), "First node value");
        assertEquals(2, n1.get_next().get_value(), "Second node value");
        assertEquals(3, n1.get_next().get_next().get_value(), "Third node value (duplicate 2 removed)");
        assertEquals(null, n1.get_next().get_next().get_next(), "List terminates after 3");
    }
}
