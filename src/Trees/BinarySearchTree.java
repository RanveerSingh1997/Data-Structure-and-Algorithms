package Trees;

import Utils.TreeNode;

public class BinarySearchTree {
    private TreeNode root;

    static void main() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(47);
        binarySearchTree.insert(21);
        binarySearchTree.insert(76);
        binarySearchTree.insert(18);
        binarySearchTree.insert(52);
        binarySearchTree.insert(82);
        binarySearchTree.insert(27);

        System.out.println(binarySearchTree.root.left.right.val);
        //False
        System.out.println(binarySearchTree.contains(28));
        //True
        System.out.println(binarySearchTree.contains(82));
    }

    public boolean insert(int value) {
        TreeNode newNode = new TreeNode(value);
        if (root == null) {
            root = newNode;
            return true;
        }
        TreeNode temp = root;
        while (true) {
            if (newNode.val == temp.val) return false;
            if (newNode.val < temp.val) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {
        TreeNode temp = root;
        while (temp != null) {
            if (value < temp.val) {
                temp = temp.left;
            } else if (value > temp.val) {
                temp = temp.right;
            } else {
                return true;
            }
        }
        return false;
    }
}
