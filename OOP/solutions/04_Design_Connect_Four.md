# 🔴🟡 Solution & Architecture: Connect Four Game System

**Problem**: Design a 2-player Connect Four grid game with gravity disc drop and win checking.  
**Difficulty**: Medium | **Target Companies**: Google, Microsoft, Meta, Amazon  
**Practice Template**: [`OOP/src/DesignConnectFour.java`](../src/DesignConnectFour.java)

---

## 1. Requirements & Clarifications

### Functional Requirements:
1. **Grid Mechanics**: Standard 6 rows $\times$ 7 columns (or customizable $R \times C$).
2. **Gravity Drop**: Player chooses a column; disc drops to the lowest unoccupied row.
3. **Move Validation**: Disallow drops into full columns; reject moves once game has concluded.
4. **$O(1)$ Win Detection**: Check if the newly placed disc forms 4 consecutive discs in any of the 4 axes:
   - Horizontal ($\leftrightarrow$)
   - Vertical ($\updownarrow$)
   - Diagonal Positive ($\nearrow$)
   - Diagonal Negative ($\searrow$)
5. **Draw Detection**: All $R \times C$ slots filled without a 4-in-a-row alignment.

---

## 2. Core Algorithmic Optimization: $O(1)$ Win Checking

Instead of scanning all $6 \times 7 = 42$ cells across the entire board on every turn ($O(R \times C)$), check **only** the newly placed disc at $(r, c)$ in the 4 directions up to 3 steps away:

```text
       \   |   /
        \  |  /
    ----- (r, c) -----
        /  |  \
       /   |   \
```

### Counting Consecutive Pieces in a Direction:
```java
private int countConsecutive(int row, int col, int dRow, int dCol, DiscColor color) {
    int count = 1; // The newly placed piece itself

    // Forward direction (+dRow, +dCol)
    int r = row + dRow, c = col + dCol;
    while (board.getCell(r, c) == color) {
        count++;
        r += dRow;
        c += dCol;
    }

    // Opposite direction (-dRow, -dCol)
    r = row - dRow;
    c = col - dCol;
    while (board.getCell(r, c) == color) {
        count++;
        r -= dRow;
        c -= dCol;
    }

    return count;
}
```

---

## 3. Gravity Placement Implementation

Track the number of discs in each column using an `int[] colHeights` array:

```java
public int dropDisc(int col, DiscColor color) {
    if (col < 0 || col >= cols || colHeights[col] >= rows) {
        return -1; // Column full or invalid
    }
    int row = rows - 1 - colHeights[col]; // Bottom-up index
    grid[row][col] = color;
    colHeights[col]++;
    return row;
}
```

---

## 4. Key Interview Traps

1. **Boundary Checking**: When walking along diagonals, ensure row and column indices never throw `ArrayIndexOutOfBoundsException`.
2. **Turn Alternation**: Do not switch turns if the move was invalid (e.g., column full).
3. **Immutability of Game Status**: Once `status != IN_PROGRESS`, subsequent `play()` calls must immediately return `false`.
