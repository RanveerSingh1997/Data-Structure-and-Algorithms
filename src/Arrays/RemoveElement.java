package Arrays;

//Problem Description
//You are given an integer array nums and an integer val.
//Your task is to:
//Remove all occurrences of val from nums in-place.
//Return the new length k of the array after removal.
//The removal must be done without using extra arrays.
//After you return k:
//The first k elements of nums should contain the values that are not equal to val.
//The order of these k elements does not matter.
//Anything stored in nums after index k - 1 can be ignored and is not checked.
//You only need to ensure:
//k is correct.
//The first k positions contain elements not equal to val.
//Method Signature
//public static int removeElement(int[] nums, int val)

//Example 1
//Input
//nums = [3, 2, 2, 3], val = 3
//Valid output:
//New length: k = 2
//First k elements: [2, 2]

//The array in memory might look like [2, 2, 2, 3] or [2, 2, 3, 3] afterward.
//Both are fine because only the first 2 elements matter.
//Example 2
//Input
//nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4], val = 1
//Elements equal to 1 should be removed.
//One valid result:
//New length: k = 7
//First k elements: [-2, -3, 4, -1, 2, -5, 4]
//The array might look like:
//        [-2, -3, 4, -1, 2, -5, 4, -5, 4]
//Here:
//First 7 elements are all not equal to 1.
//The last 2 elements are ignored.
//How it works (conceptual)
//You can solve this with a write index:
//Scan nums from left to right.
//Each time you see a value not equal to val, write it at the current write position and move the write position forward.
//At the end, the write position is your k.
//This keeps everything in-place and only cares about the first k elements.
public class RemoveElement {

}
