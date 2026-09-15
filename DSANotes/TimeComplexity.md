# Big O Time & Space Complexity Cheat Sheet

## Common Data Structures

| Data Structure | Access | Search | Insertion | Deletion | Space Complexity |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Array** | `O(1)` | `O(N)` | `O(N)` | `O(N)` | `O(N)` |
| **Stack / Queue** | `O(N)` | `O(N)` | `O(1)` | `O(1)` | `O(N)` |
| **Singly Linked List**| `O(N)` | `O(N)` | `O(1)` | `O(1)` | `O(N)` |
| **Doubly Linked List**| `O(N)` | `O(N)` | `O(1)` | `O(1)` | `O(N)` |
| **Hash Table** | N/A | `O(1)` | `O(1)` | `O(1)` | `O(N)` |
| **Binary Search Tree**| `O(log N)` | `O(log N)` | `O(log N)` | `O(log N)` | `O(N)` |
| **Min/Max Heap** | N/A | `O(N)` | `O(log N)` | `O(log N)` | `O(N)` |
| **Trie** | `O(L)` | `O(L)` | `O(L)` | `O(L)` | `O(N * L)` |

*Note: For Hash Table, worst-case can be `O(N)` if there are many collisions. For BST, worst case is `O(N)` if skewed. `L` is the length of the string.*

## Sorting Algorithms

| Algorithm | Best | Average | Worst | Space Complexity |
| :--- | :--- | :--- | :--- | :--- |
| **Merge Sort** | `O(N log N)` | `O(N log N)` | `O(N log N)` | `O(N)` |
| **Quick Sort** | `O(N log N)` | `O(N log N)` | `O(N^2)` | `O(log N)` |
| **Heap Sort** | `O(N log N)` | `O(N log N)` | `O(N log N)` | `O(1)` |
| **Bubble/Insertion**| `O(N)` | `O(N^2)` | `O(N^2)` | `O(1)` |
| **Counting Sort** | `O(N + K)` | `O(N + K)` | `O(N + K)` | `O(K)` |

## Graph Algorithms
Where `V` is Vertices and `E` is Edges.

- **DFS / BFS**: `O(V + E)` Time, `O(V)` Space
- **Dijkstra's (w/ Min Heap)**: `O((V + E) log V)` Time, `O(V)` Space
- **Topological Sort**: `O(V + E)` Time, `O(V)` Space
- **Kruskal's MST**: `O(E log E)` Time, `O(V)` Space
