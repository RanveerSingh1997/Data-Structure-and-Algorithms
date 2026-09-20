package Sorting_Searching;

import Utils.LinkedList;
import Utils.Node;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        selectionSort(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println("-----------Selection Sorting Using Linked List------------");
        LinkedList linkedList = new LinkedList();
        linkedList.addElements(nums);
        selectionSort(linkedList);
        linkedList.printList();
    }

    private static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
    }


    /// Solving using linked list with sorted and unsorted list constraints
    private static void selectionSort(LinkedList list) {
        if (list.getLength() < 2) {
            return;
        }
        LinkedList sorted = new LinkedList();
        while (list.getLength() > 0) {
            Node temp = list.getHead();
            int minValue = temp.get_value();
            int minIndex = 0;
            int currentIndex = 0;
            while (temp != null) {
                if (temp.get_value() < minValue) {
                    minValue = temp.get_value();
                    minIndex = currentIndex;
                }
                temp = temp.get_next();
                currentIndex++;
            }
            sorted.append(minValue);
            list.remove(minIndex);
        }

        while (sorted.getLength() > 0) {
            Node temp = sorted.getHead();
            list.append(temp.get_value());
            sorted.remove(0);
        }
    }
}
