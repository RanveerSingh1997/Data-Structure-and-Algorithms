package Trees;

import Utils.StackTemplate;
import Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private TreeNode root;

    public static void main(String[] args) {
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

        System.out.println(binarySearchTree.rContains(82));

        System.out.println(binarySearchTree.rInsert(90));
        System.out.println(binarySearchTree.rInsert(10));

        System.out.println("BFS            " + binarySearchTree.BFS());
        System.out.println("DFS Pre Order  " + binarySearchTree.DFSPreOrder());
        System.out.println("DFS Post Order " + binarySearchTree.DFSPostOrder());
        System.out.println("DFS In Order " + binarySearchTree.DFSInorder());
        System.out.println("IS VALID B TREE " + binarySearchTree.isValidBST());
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

    public boolean rContains(TreeNode currentNode, int value) {
        if (currentNode == null) return false;
        if (currentNode.val == value) return true;
        if (value < currentNode.val) {
            return rContains(currentNode.left, value);
        } else {
            return rContains(currentNode.right, value);
        }
    }

    public boolean rContains(int value) {
        return rContains(root, value);
    }

    private TreeNode rInsert(TreeNode currentNode, int value) {
        if (currentNode == null) return new TreeNode(value);
        if (value < currentNode.val) {
            currentNode.left = rInsert(currentNode.left, value);
        } else {
            currentNode.right = rInsert(currentNode.right, value);
        }
        return currentNode;
    }

    public TreeNode rInsert(int value) {
        if (root == null) return root = new TreeNode(value);
        return rInsert(root, value);
    }

    private TreeNode rDelete(TreeNode currentNode, int value) {
        if (currentNode == null) return null;
        if (value < currentNode.val) {
            currentNode.left = rDelete(currentNode.left, value);
        } else if (value > currentNode.val) {
            currentNode.right = rDelete(currentNode.right, value);
        } else {
            if (currentNode.left == null && currentNode.right == null) {
                return null;
            } else if (currentNode.left == null) {
                currentNode = currentNode.right;
            } else if (currentNode.right == null) {
                currentNode = currentNode.left;
            } else {
                int subTreeMin = minValue(currentNode.right);
                currentNode.val = subTreeMin;
                currentNode.right = rDelete(currentNode.right, subTreeMin);
            }
        }
        return currentNode;
    }

    public int minValue(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    public TreeNode rDelete(int value) {
        if (root == null) return null;
        return rDelete(root, value);
    }

    public ArrayList<Integer> BFS() {
        TreeNode current = root;
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        queue.add(current);
        while (!queue.isEmpty()) {
            current = queue.remove();
            result.add(current.val);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return result;
    }


    public ArrayList<Integer> DFSPreOrder() {
        ArrayList<Integer> results = new ArrayList<>();
        class Traverse {
            Traverse(TreeNode currentNode) {
                results.add(currentNode.val);
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
            }
        }
        new Traverse(root);
        return results;
    }


    public ArrayList<Integer> DFSPostOrder() {
        ArrayList<Integer> results = new ArrayList<>();
        class Traverse {
            Traverse(TreeNode currentNode) {
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
                results.add(currentNode.val);
            }
        }
        new Traverse(root);
        return results;
    }

    public ArrayList<Integer> DFSInorder() {
        ArrayList<Integer> results = new ArrayList<>();
        class Traverse {
            Traverse(TreeNode currentNode) {
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                results.add(currentNode.val);
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
            }
        }
        new Traverse(root);
        return results;
    }

    public StackTemplate<Integer> DFSInorderWithStack() {
        StackTemplate<Integer> results = new StackTemplate<>();
        class Traverse {
            Traverse(TreeNode currentNode) {
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                results.push(currentNode.val);
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
            }
        }
        new Traverse(root);
        return results;
    }


    public boolean isValidBST() {
        ArrayList<Integer> result = DFSInorder();
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) > result.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public int kthSmallest(int k) {
        StackTemplate<Integer> result = DFSInorderWithStack();
        System.out.println("STACK DFS" + result);
        int currentSize = result.size() - k;
        while (currentSize > 0) {
            result.pop();
            currentSize--;
        }
        return result.peek();
    }
}
