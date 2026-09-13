package Heap;

public class KthSmallestElement {

    public static Integer findKthSmallest(int[] array, int target) {
        Heap heap = new Heap();
        for (int num : array) {
            heap.insert(num);
            if (heap.getHeap().size() > target) {
                heap.remove();
            }
        }

        return heap.remove();
    }

    public static void main(String[] args) {
        // Test case 1e
        int[] nums1 = {7, 10, 4, 3, 20, 15};
        int k1 = 3;
        System.out.println("Test case 1:");
        System.out.println("Expected output: 7");
        System.out.println("Actual output: " + findKthSmallest(nums1, k1));
        System.out.println();

        // Test case 2
        int[] nums2 = {2, 1, 3, 5, 6, 4};
        int k2 = 2;
        System.out.println("Test case 2:");
        System.out.println("Expected output: 2");
        System.out.println("Actual output: " + findKthSmallest(nums2, k2));
        System.out.println();

        // Test case 3
        int[] nums3 = {9, 3, 2, 11, 7, 10, 4, 5};
        int k3 = 5;
        System.out.println("Test case 3:");
        System.out.println("Expected output: 7");
        System.out.println("Actual output: " + findKthSmallest(nums3, k3));
        System.out.println();


            /*
                EXPECTED OUTPUT:
                ----------------
                Test case 1:
                Expected output: 7
                Actual output: 7

                Test case 2:
                Expected output: 2
                Actual output: 2

                Test case 3:
                Expected output: 7
                Actual output: 7

            */

    }
}
