import java.util.*;

/**
 * ============================================================================
 * Practice Template: Connect Four Game System (LLD)
 * Difficulty: Medium | Target Companies: Google, Microsoft, Meta, Amazon
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Connect Four is a two-player connection board game played on a grid (typically 7 columns x 6 rows).
 * Players take turns dropping colored discs (RED and YELLOW) into columns.
 * Discs fall to the lowest unoccupied space in the selected column (gravity mechanic).
 * The first player to connect four consecutive discs horizontally, vertically,
 * or diagonally wins the game.
 *
 * 📥 REQUIREMENTS:
 * 1. Configurable grid (R rows x C columns, standard 6x7).
 * 2. Gravity Drop: Disc occupies lowest available row in the selected column.
 * 3. Validation: Reject drops into full columns or when game is already finished.
 * 4. Win Evaluation: Detect 4 consecutive discs in any of the 4 directions:
 *    - Horizontal (← →)
 *    - Vertical (↑ ↓)
 *    - Diagonal Positive (↙ ↗)
 *    - Diagonal Negative (↖ ↘)
 * 5. Turn Alternation: Players alternate turns until a win or draw occurs.
 *
 * 💡 INTERVIEW HINTS:
 * - Check win condition only centered around the newly placed piece in O(1) time rather than scanning the entire board.
 * - Track the current fill height for each column in an `int[] colHeights` array.
 */
public class DesignConnectFour {

    // =========================================================================
    // 1. ENUMS & BASIC TYPES
    // =========================================================================

    public enum DiscColor {
        RED, YELLOW, EMPTY
    }

    public enum GameStatus {
        IN_PROGRESS, RED_WON, YELLOW_WON, DRAW
    }

    public static class Player {
        private final String name;
        private final DiscColor color;

        public Player(String name, DiscColor color) {
            this.name = name;
            this.color = color;
        }

        public String getName() { return name; }
        public DiscColor getColor() { return color; }

        @Override
        public String toString() { return name + " (" + color + ")"; }
    }

    // =========================================================================
    // 2. BOARD & CELL REPRESENTATION
    // =========================================================================

    public static class Board {
        private final int rows;
        private final int cols;
        private final DiscColor[][] grid;
        private final int[] colHeights;

        public Board(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.grid = new DiscColor[rows][cols];
            this.colHeights = new int[cols];
            for (int r = 0; r < rows; r++) {
                Arrays.fill(grid[r], DiscColor.EMPTY);
            }
        }

        public boolean isColumnFull(int col) {
            // TODO: Return true if colHeights[col] >= rows
            return false;
        }

        /**
         * Drops a disc into column applying gravity.
         *
         * @return row index where disc landed, or -1 if invalid/full
         */
        public int dropDisc(int col, DiscColor color) {
            // TODO: Implement gravity drop:
            // 1. Check bounds and if column is full
            // 2. Compute landing row (rows - 1 - colHeights[col])
            // 3. Assign color into grid[row][col], increment colHeights[col]
            // 4. Return row
            return -1;
        }

        public DiscColor getCell(int r, int c) {
            if (r < 0 || r >= rows || c < 0 || c >= cols) return DiscColor.EMPTY;
            return grid[r][c];
        }

        public int getRows() { return rows; }
        public int getCols() { return cols; }
    }

    // =========================================================================
    // 3. GAME CONTROLLER
    // =========================================================================

    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentTurn;
    private GameStatus status;
    private final int targetWinCount;
    private int totalMovesPlayed;

    public DesignConnectFour(int rows, int cols, int targetWinCount) {
        this.board = new Board(rows, cols);
        this.player1 = new Player("Player 1", DiscColor.RED);
        this.player2 = new Player("Player 2", DiscColor.YELLOW);
        this.currentTurn = player1;
        this.status = GameStatus.IN_PROGRESS;
        this.targetWinCount = targetWinCount;
        this.totalMovesPlayed = 0;
    }

    public DesignConnectFour() {
        this(6, 7, 4);
    }

    /**
     * Drops a disc in the selected column for the active player.
     *
     * @return true if move was successfully played; false otherwise
     */
    public boolean play(int col) {
        // TODO: Implement turn play logic:
        // 1. Check status == IN_PROGRESS
        // 2. Drop disc on board -> int row = board.dropDisc(col, currentTurn.getColor())
        // 3. If row == -1, return false
        // 4. Increment totalMovesPlayed++
        // 5. If checkWin(row, col, currentTurn.getColor()): set status to won, return true
        // 6. If board is full: set status = DRAW, return true
        // 7. Alternate currentTurn to next player, return true
        return false;
    }

    /**
     * Checks if the disc at (row, col) created targetWinCount consecutive discs in any of the 4 directions.
     */
    public boolean checkWin(int row, int col, DiscColor color) {
        // TODO: Count consecutive discs in:
        // - Horizontal (0, 1)
        // - Vertical (1, 0)
        // - Diagonal Down-Right (1, 1)
        // - Diagonal Up-Right (1, -1)
        // Return true if any direction >= targetWinCount
        return false;
    }

    public GameStatus getStatus() { return status; }
    public Board getBoard() { return board; }
    public Player getCurrentTurn() { return currentTurn; }

    // =========================================================================
    // 4. VERIFICATION TEST HARNESS (Run to test your code!)
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== Testing: Connect Four Game System ===");

        DesignConnectFour game = new DesignConnectFour(6, 7, 4);

        // Test 1: Simulate horizontal win for Player 1 (RED) in columns 0, 1, 2, 3
        game.play(0); // P1
        game.play(0); // P2
        game.play(1); // P1
        game.play(1); // P2
        game.play(2); // P1
        game.play(2); // P2
        game.play(3); // P1 (Should win!)

        if (game.getStatus() == GameStatus.RED_WON) {
            System.out.println("  [PASS] Test 1: Player 1 (RED) won horizontally in row 5.");
        } else {
            System.out.println("  [TODO] Test 1: play() / checkWin() not implemented yet.");
        }

        // Test 2: Reject move when game is already finished
        boolean invalidMove = game.play(4);
        if (!invalidMove) {
            System.out.println("  [PASS] Test 2: Rejected move after game finished.");
        } else {
            System.out.println("  [TODO] Test 2: Game status check not implemented yet.");
        }
    }
}
