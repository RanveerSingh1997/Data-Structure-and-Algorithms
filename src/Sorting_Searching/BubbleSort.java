package Sorting_Searching;

import Utils.LinkedList;
import Utils.Node;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {4, 3, 5, 1, 2, 6, 8, 7, 9};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println("-----------SORTING USING LINKED LIST----------");
        LinkedList list = new LinkedList();
        list.addElements(new int[]{4, 2, 6, 5, 1, 3});
        bubbleSort(list);
        list.printList();
    }


    public static void bubbleSort(LinkedList list) {
        if (list.getLength() <= 1) {
            return;
        }
        int i = list.getLength() - 1;
        while (i > 0) {
            Node temp = list.getHead();
            int j = 0;
            while (j < i) {
                Node nextNode = temp.get_next();
                if (temp.get_value() > nextNode.get_value()) {
                    int tempValue = temp.get_value();
                    temp.set_value(nextNode.get_value());
                    nextNode.set_value(tempValue);
                }
                temp = temp.get_next();
                j++;
            }
            i--;
        }
    }

    private static void bubbleSort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
}
