import java.util.*;

/**
 * ============================================================================
 * Practice Template: Chess Game System (LLD)
 * Difficulty: Hard | Target Companies: Google, Amazon, Uber, Microsoft
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Design a full-featured Chess game for two players (White and Black) on an 8x8 board.
 * The system must validate moves for each piece, handle path collisions,
 * enforce turn alternation, and support move history with Undo/Redo (Command Pattern).
 *
 * 📥 REQUIREMENTS:
 * 1. 8x8 Board with alternating colored cells (`Spot`).
 * 2. Piece Hierarchy (Polymorphism):
 *    - King: Moves 1 square in any direction.
 *    - Queen: Straight or diagonal in any distance.
 *    - Rook: Straight horizontal/vertical.
 *    - Bishop: Diagonal only.
 *    - Knight: L-shape jump (2+1).
 *    - Pawn: 1 square forward (2 on start), captures diagonally.
 * 3. Collision: Sliding pieces cannot jump over other pieces. Destination cannot hold friendly piece.
 * 4. Turn Alternation: White moves first; players alternate.
 * 5. Move History: Maintain a log of moves using the Command Pattern.
 *
 * 💡 INTERVIEW HINTS:
 * - Keep Piece movement validation in the Piece subclass (`canMove(Board board, Spot start, Spot end)`).
 * - Keep board collision checking in `Board.isPathClear(Spot start, Spot end)`.
 * - Use the Command Pattern for `Move` with `execute()` and `undo()`.
 */
public class DesignChess {

    // =========================================================================
    // 1. ENUMS & BASIC TYPES
    // =========================================================================

    public enum Color {
        WHITE, BLACK
    }

    public enum GameStatus {
        ACTIVE, WHITE_WIN, BLACK_WIN, STALEMATE
    }

    // =========================================================================
    // 2. SPOT (CELL ON THE 8x8 BOARD)
    // =========================================================================

    public static class Spot {
        private final int row;
        private final int col;
        private Piece piece;

        public Spot(int row, int col, Piece piece) {
            this.row = row;
            this.col = col;
            this.piece = piece;
        }

        public int getRow() { return row; }
        public int getCol() { return col; }
        public Piece getPiece() { return piece; }
        public void setPiece(Piece piece) { this.piece = piece; }
        public boolean isOccupied() { return piece != null; }

        @Override
        public String toString() {
            char colChar = (char) ('a' + col);
            int rowNum = 8 - row;
            return "" + colChar + rowNum;
        }
    }

    // =========================================================================
    // 3. PIECE HIERARCHY (POLYMORPHISM)
    // =========================================================================

    public static abstract class Piece {
        private final Color color;
        private boolean killed = false;

        public Piece(Color color) {
            this.color = color;
        }

        public Color getColor() { return color; }
        public boolean isKilled() { return killed; }
        public void setKilled(boolean killed) { this.killed = killed; }

        /**
         * Validates whether this piece can legally move from start to end on the board.
         */
        public abstract boolean canMove(Board board, Spot start, Spot end);
        public abstract String getSymbol();
    }

    public static class Knight extends Piece {
        public Knight(Color color) { super(color); }

        @Override
        public boolean canMove(Board board, Spot start, Spot end) {
            // TODO: Validate Knight's L-shape move:
            // 1. Destination cannot contain a friendly piece
            // 2. |dRow| == 2 && |dCol| == 1 OR |dRow| == 1 && |dCol| == 2
            return false;
        }

        @Override
        public String getSymbol() { return getColor() == Color.WHITE ? "♘" : "♞"; }
    }

    public static class Rook extends Piece {
        public Rook(Color color) { super(color); }

        @Override
        public boolean canMove(Board board, Spot start, Spot end) {
            // TODO: Validate Rook move:
            // 1. Must be horizontal (start.row == end.row) or vertical (start.col == end.col)
            // 2. Destination cannot contain a friendly piece
            // 3. Path between start and end must be clear (board.isPathClear)
            return false;
        }

        @Override
        public String getSymbol() { return getColor() == Color.WHITE ? "♖" : "♜"; }
    }

    public static class Bishop extends Piece {
        public Bishop(Color color) { super(color); }

        @Override
        public boolean canMove(Board board, Spot start, Spot end) {
            // TODO: Validate Bishop move:
            // 1. Must be diagonal (|dRow| == |dCol|)
            // 2. Destination cannot contain a friendly piece
            // 3. Path must be clear
            return false;
        }

        @Override
        public String getSymbol() { return getColor() == Color.WHITE ? "♗" : "♝"; }
    }

    public static class Queen extends Piece {
        public Queen(Color color) { super(color); }

        @Override
        public boolean canMove(Board board, Spot start, Spot end) {
            // TODO: Validate Queen move (straight or diagonal, path clear)
            return false;
        }

        @Override
        public String getSymbol() { return getColor() == Color.WHITE ? "♕" : "♛"; }
    }

    public static class King extends Piece {
        public King(Color color) { super(color); }

        @Override
        public boolean canMove(Board board, Spot start, Spot end) {
            // TODO: Validate King move (|dRow| <= 1 && |dCol| <= 1, no friendly piece on end)
            return false;
        }

        @Override
        public String getSymbol() { return getColor() == Color.WHITE ? "♔" : "♚"; }
    }

    public static class Pawn extends Piece {
        public Pawn(Color color) { super(color); }

        @Override
        public boolean canMove(Board board, Spot start, Spot end) {
            // TODO: Validate Pawn move:
            // - White moves up (dRow = -1), Black moves down (dRow = +1)
            // - 1 step forward into empty spot
            // - 2 steps forward from starting rank into empty spot with intermediate empty
            // - 1 step diagonal capture into spot occupied by enemy piece
            return false;
        }

        @Override
        public String getSymbol() { return getColor() == Color.WHITE ? "♙" : "♟"; }
    }

    // =========================================================================
    // 4. BOARD & PATH COLLISION CHECKER
    // =========================================================================

    public static class Board {
        private final Spot[][] boxes = new Spot[8][8];

        public Board() {
            resetBoard();
        }

        public Spot getSpot(int row, int col) {
            return boxes[row][col];
        }

        public void resetBoard() {
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    boxes[r][c] = new Spot(r, c, null);
                }
            }

            // Black Major Pieces (Row 0)
            boxes[0][0].setPiece(new Rook(Color.BLACK));
            boxes[0][1].setPiece(new Knight(Color.BLACK));
            boxes[0][2].setPiece(new Bishop(Color.BLACK));
            boxes[0][3].setPiece(new Queen(Color.BLACK));
            boxes[0][4].setPiece(new King(Color.BLACK));
            boxes[0][5].setPiece(new Bishop(Color.BLACK));
            boxes[0][6].setPiece(new Knight(Color.BLACK));
            boxes[0][7].setPiece(new Rook(Color.BLACK));
            for (int c = 0; c < 8; c++) boxes[1][c].setPiece(new Pawn(Color.BLACK));

            // White Major Pieces (Row 7)
            for (int c = 0; c < 8; c++) boxes[6][c].setPiece(new Pawn(Color.WHITE));
            boxes[7][0].setPiece(new Rook(Color.WHITE));
            boxes[7][1].setPiece(new Knight(Color.WHITE));
            boxes[7][2].setPiece(new Bishop(Color.WHITE));
            boxes[7][3].setPiece(new Queen(Color.WHITE));
            boxes[7][4].setPiece(new King(Color.WHITE));
            boxes[7][5].setPiece(new Bishop(Color.WHITE));
            boxes[7][6].setPiece(new Knight(Color.WHITE));
            boxes[7][7].setPiece(new Rook(Color.WHITE));
        }

        /**
         * Returns true if all squares strictly between start and end are empty.
         */
        public boolean isPathClear(Spot start, Spot end) {
            // TODO: Calculate step directions (stepRow = signum(end.row - start.row), etc.)
            // Walk from start towards end, checking if any box along the way isOccupied().
            return true;
        }
    }

    // =========================================================================
    // 5. COMMAND PATTERN: MOVE & HISTORY
    // =========================================================================

    public static class Move {
        private final Spot start;
        private final Spot end;
        private final Piece pieceMoved;
        private final Piece pieceKilled;

        public Move(Spot start, Spot end) {
            this.start = start;
            this.end = end;
            this.pieceMoved = start.getPiece();
            this.pieceKilled = end.getPiece();
        }

        public void execute() {
            // TODO: Implement move execution:
            // 1. If pieceKilled != null, set pieceKilled.setKilled(true)
            // 2. Set end.setPiece(pieceMoved)
            // 3. Clear start.setPiece(null)
        }

        public void undo() {
            // TODO: Restore start and end pieces
        }

        public Spot getStart() { return start; }
        public Spot getEnd() { return end; }
        public Piece getPieceMoved() { return pieceMoved; }
        public Piece getPieceKilled() { return pieceKilled; }
    }

    // =========================================================================
    // 6. CHESS GAME CONTROLLER
    // =========================================================================

    private final Board board;
    private Color currentTurn;
    private GameStatus status;
    private final List<Move> moveHistory;

    public DesignChess() {
        this.board = new Board();
        this.currentTurn = Color.WHITE;
        this.status = GameStatus.ACTIVE;
        this.moveHistory = new ArrayList<>();
    }

    /**
     * Attempts to execute a move from (startRow, startCol) to (endRow, endCol).
     */
    public boolean makeMove(int startRow, int startCol, int endRow, int endCol) {
        // TODO: Implement move workflow:
        // 1. Verify game is ACTIVE
        // 2. Retrieve start and end spots
        // 3. Verify start has a piece and piece.getColor() == currentTurn
        // 4. Verify piece.canMove(board, start, end)
        // 5. Create Move, execute it, append to moveHistory
        // 6. Switch turn (White -> Black -> White) and return true
        return false;
    }

    public Board getBoard() { return board; }
    public Color getCurrentTurn() { return currentTurn; }
    public GameStatus getStatus() { return status; }
    public List<Move> getMoveHistory() { return Collections.unmodifiableList(moveHistory); }

    // =========================================================================
    // 7. VERIFICATION TEST HARNESS (Run to test your code!)
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Testing: Chess Game System ===");

        DesignChess game = new DesignChess();

        // Test 1: White Pawn moves e2 to e4 (Row 6, Col 4 to Row 4, Col 4)
        boolean m1 = game.makeMove(6, 4, 4, 4);
        if (m1) {
            System.out.println("  [PASS] Test 1: White pawn advanced e2 to e4.");
        } else {
            System.out.println("  [TODO] Test 1: Pawn movement / makeMove() not implemented yet.");
        }

        // Test 2: Black Pawn moves e7 to e5 (Row 1, Col 4 to Row 3, Col 4)
        boolean m2 = game.makeMove(1, 4, 3, 4);
        if (m2) {
            System.out.println("  [PASS] Test 2: Black pawn responded from e7 to e5.");
        } else {
            System.out.println("  [TODO] Test 2: Black turn response not implemented yet.");
        }

        // Test 3: White Knight jumps to f3 (Row 7, Col 6 to Row 5, Col 5)
        boolean m3 = game.makeMove(7, 6, 5, 5);
        if (m3) {
            System.out.println("  [PASS] Test 3: White Knight jumped to f3.");
        } else {
            System.out.println("  [TODO] Test 3: Knight move validation not implemented yet.");
        }
    }
}
