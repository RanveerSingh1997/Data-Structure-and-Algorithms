# ♟️ Solution & Architecture: Chess Game System

**Problem**: Design a full-featured Chess game for two players on an 8x8 board.  
**Difficulty**: Hard | **Target Companies**: Google, Amazon, Uber, Microsoft  
**Practice Template**: [`OOP/src/DesignChess.java`](../src/DesignChess.java)

---

## 1. Requirements & Clarifications

### Functional Requirements:
1. **8x8 Grid**: Cells (`Spot`) with ranks (1–8) and files (a–h).
2. **Piece Rules**:
   - `King`: 1 step in any direction.
   - `Queen`: Any distance diagonally or orthogonally.
   - `Rook`: Orthogonal lines (horizontal/vertical).
   - `Bishop`: Diagonal lines.
   - `Knight`: L-shape jump ($|dx|=2, |dy|=1$ or $|dx|=1, |dy|=2$).
   - `Pawn`: 1 step forward, 2 steps from starting rank, diagonal capture.
3. **Collision Detection**: Sliding pieces (Rook, Bishop, Queen) cannot jump over pieces.
4. **Command Pattern**: Moves recorded as objects to support move history, undo, and replay.
5. **Turn Alternation**: White moves first; players strictly alternate.

---

## 2. Core Architectural Decisions

### Polymorphic Movement Validation
Instead of putting a giant `switch (piece.getType())` inside `Board` or `Game`, each piece subclass implements its own validation:

```java
public abstract class Piece {
    public abstract boolean canMove(Board board, Spot start, Spot end);
}
```

### Path Clearance Delegation
The piece asks the `Board` whether the path between `start` and `end` is clear:

```java
public boolean isPathClear(Spot start, Spot end) {
    int stepRow = Integer.compare(end.getRow(), start.getRow());
    int stepCol = Integer.compare(end.getCol(), start.getCol());

    int currRow = start.getRow() + stepRow;
    int currCol = start.getCol() + stepCol;

    while (currRow != end.getRow() || currCol != end.getCol()) {
        if (boxes[currRow][currCol].isOccupied()) {
            return false; // Intermediate obstacle found
        }
        currRow += stepRow;
        currCol += stepCol;
    }
    return true;
}
```

---

## 3. Command Pattern for Moves

```java
public class Move {
    private final Spot start;
    private final Spot end;
    private final Piece pieceMoved;
    private final Piece pieceKilled;

    public void execute() {
        if (pieceKilled != null) pieceKilled.setKilled(true);
        end.setPiece(pieceMoved);
        start.setPiece(null);
    }

    public void undo() {
        start.setPiece(pieceMoved);
        end.setPiece(pieceKilled);
        if (pieceKilled != null) pieceKilled.setKilled(false);
    }
}
```

---

## 4. Key Interview Traps

1. **Knight Can Jump**: Never check `isPathClear` for a Knight! It is the only piece allowed to leap over other pieces.
2. **Friendly Capture**: Always ensure `end.getPiece().getColor() != this.getColor()` before permitting a move.
3. **Pawn Forward vs Capture**: A Pawn can only move straight into an **empty** spot, and can only move diagonally if an **enemy piece** is present.
