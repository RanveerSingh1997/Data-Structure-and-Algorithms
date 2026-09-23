import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ============================================================================
 * Practice Template: Connect Four Game System (LLD)
 * Difficulty: Medium | Target Companies: Google, Microsoft, Meta, Amazon
 * ============================================================================
 * <p>
 * 📖 PROBLEM DESCRIPTION:
 * Connect Four is a two-player connection board game played on a grid (typically 7 columns x 6 rows).
 * Players take turns dropping colored discs (RED and YELLOW) into columns.
 * Discs fall to the lowest unoccupied space in the selected column (gravity mechanic).
 * The first player to connect four consecutive discs horizontally, vertically,
 * or diagonally wins the game.
 * <p>
 * 📥 REQUIREMENTS:x
 * 1. Configurable grid (R rows x C columns, standard 6x7).
 * 2. Gravity Drop: Disc occupies lowest available row in the selected column.
 * 3. Validation: Reject drops into full columns or when game is already finished.
 * 4. Win Evaluation: Detect 4 consecutive discs in any of the 4 directions:
 * - Horizontal (← →)
 * - Vertical (↑ ↓)
 * - Diagonal Positive (↙ ↗)
 * - Diagonal Negative (↖ ↘)
 * 5. Turn Alternation: Players alternate turns until a win or draw occurs.
 * <p>
 * 💡 INTERVIEW HINTS:
 * - Check win condition only centered around the newly placed piece in O(1) time rather than scanning the entire board.
 * - Track the current fill height for each column in an `int[] colHeights` array.
 */

public class DesignConnectFour {

    public static void main(String[] args) {
        Grid grid = new Grid(6, 7);
        Game game = new Game(grid, 4, 10);
        game.play();
    }


    enum GridPosition {
        EMPTY, YELLOW, RED
    }

    static class Grid {
        private final int rows;
        private final int columns;
        private int[][] grid;

        public Grid(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            initGrid();
        }

        public void initGrid() {
            this.grid = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < rows; j++) {
                    grid[i][j] = GridPosition.EMPTY.ordinal();
                }
            }
        }

        int[][] getGrid() {
            return this.grid;
        }

        int getColumnCount() {
            return this.columns;
        }

        public int placePiece(int column, GridPosition piece) {
            if (column < 0 || column >= this.columns) {
                throw new Error("Invalid Column");
            }
            if (piece == GridPosition.EMPTY) {
                throw new Error("Invalid Piece");
            }
            for (int row = this.rows - 1; row >= 0; row--) {
                if (this.grid[row][column] == GridPosition.EMPTY.ordinal()) {
                    this.grid[row][column] = piece.ordinal();
                    return row;
                }
            }
            return -1;
        }

        public boolean checkWin(int connectN, int row, int col, GridPosition piece) {
            int count = 0;
            ///Horizontal Winning Check
            for (int c = 0; c < this.columns; c++) {
                if (grid[row][c] == piece.ordinal()) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == connectN) {
                    return true;
                }
            }

            ///Vertical Winning Check
            count = 0;
            for (int c = 0; c < this.rows; c++) {
                if (grid[c][col] == piece.ordinal()) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == connectN) {
                    return true;
                }
            }

            ///Diagonal Winning Check
            count = 0;
            for (int c = 0; c < this.rows; c++) {
                int r = row + col - c;
                if (r >= 0 && r < this.columns && grid[c][r] == piece.ordinal()) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == connectN) {
                    return true;
                }
            }

            ///Diagonal Winning Check
            count = 0;
            for (int c = 0; c < this.rows; c++) {
                int r = col - row + c;
                if (r >= 0 && r < this.columns && grid[c][r] == piece.ordinal()) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == connectN) {
                    return true;
                }
            }


            return false;
        }
    }

    static class Player {
        private final String name;
        private final GridPosition piece;

        public Player(String name, GridPosition piece) {
            this.name = name;
            this.piece = piece;
        }

        public String getName() {
            return this.name;
        }

        public GridPosition getPieceColor() {
            return this.piece;
        }
    }

    static class Game {
        static Scanner input = new Scanner(System.in);
        private final int connectN;
        private final Grid grid;
        private final Player[] players;
        private final int targetScore;
        private final Map<String, Integer> score;


        Game(Grid grid, int connectN, int targetScore) {
            this.grid = grid;
            this.connectN = connectN;
            this.targetScore = targetScore;
            this.players = new Player[]{
                    new Player("Player 1", GridPosition.RED),
                    new Player("Player 2", GridPosition.YELLOW),
            };
            this.score = new HashMap<>();
            for (Player player : this.players) {
                this.score.put(player.getName(), 0);
            }
        }

        private void printBoard() {
            System.out.println("-----------Board--------");
            int[][] grid = this.grid.getGrid();
            for (int[] ints : grid) {
                StringBuilder row = new StringBuilder();
                for (int piece : ints) {
                    if (piece == GridPosition.EMPTY.ordinal()) {
                        row.append("0");
                    } else if (piece == GridPosition.YELLOW.ordinal()) {
                        row.append("Y");
                    } else {
                        row.append("R");
                    }
                }
                System.out.println(row);
            }
            System.out.println();
        }

        private int[] playMove(Player player) {
            printBoard();
            System.out.println(player.getName() + "'s turn");
            int colCnt = this.grid.getColumnCount();
            System.out.println("Enter column between 0 and" + (colCnt - 1) + "to add piece:");
            int moveColumn = input.nextInt();
            int moveRow = this.grid.placePiece(moveColumn, player.getPieceColor());
            return new int[]{moveRow, moveColumn};
        }


        private Player playRound() {
            while (true) {
                for (Player player : this.players) {
                    int[] pos = playMove(player);
                    int row = pos[0];
                    int col = pos[1];
                    GridPosition pieceColor = player.getPieceColor();
                    if (this.grid.checkWin(this.connectN, row, col, pieceColor)) {
                        this.score.put(player.getName(), this.score.get(player.getName() + 1));
                        return player;
                    }
                }
            }
        }

        public void play() {
            int maxScore = 0;
            Player winner = null;
            while (maxScore < this.targetScore) {
                winner = playRound();
                System.out.println(winner.getName() + "won the round");
                maxScore = Math.max(this.score.get(winner.getName()), maxScore);
                this.grid.initGrid(); //reset grid
            }
            System.out.println(winner.getName() + "Won the game");
        }
    }
}