package Arrays;

//You are given a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:
//
//Each row must contain the digits 1-9 without duplicates.
//Each column must contain the digits 1-9 without duplicates.
//Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
//Return true if the Sudoku board is valid, otherwise return false

import java.util.HashSet;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board1 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] board2 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '7', '7', '9'}
        };

        char[][] board3 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'5', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        char[][] board4 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '5', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        char[][] board5 = new char[][]{
                {'1', '2', '3', '.', '.', '.', '.', '.', '.'},
                {'4', '5', '6', '.', '.', '.', '.', '.', '.'},
                {'7', '8', '1', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '2', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '3', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '4', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '5', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '6', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '7'}
        };

        char[][] board6 = new char[][]{
                {'1', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '4', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '5', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '6', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '7', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '8', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '9'}
        };
        System.out.println(isValidSudoku(board1));
        System.out.println(isValidSudoku(board2));
        System.out.println(isValidSudoku(board3));
        System.out.println(isValidSudoku(board4));
        System.out.println(isValidSudoku(board5));
        System.out.println(isValidSudoku(board6));
    }

    public static boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int box = (i / 3) * 3 + (j / 3);
                    System.out.println(" NUMBER " + board[i][j] + " BOX " + box);
                    if (!seen.add(board[i][j] + "at row" + i) ||
                            !seen.add(board[i][j] + "at col" + j) ||
                            !seen.add(board[i][j] + "at box" + box)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


//    public static boolean isValidSudoku(char[][] board) {
//        HashSet[] rows = new HashSet[9];
//        HashSet[] columns = new HashSet[9];
//        HashSet[] boxes = new HashSet[9];
//        for (int i = 0; i < 9; i++) {
//            rows[i] = new HashSet<>();
//            columns[i] = new HashSet<>();
//            boxes[i] = new HashSet<>();
//        }
//
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                char value = board[i][j];
//                if (value == '.') {
//                    continue;
//                }
//                int box = (i / 3) * 3 + (j / 3);
//                if (rows[i].contains(value) || columns[j].contains(value) || boxes[box].contains(value)) {
//                    System.out.println(Arrays.toString(rows));
//                    System.out.println(Arrays.toString(columns));
//                    System.out.println(Arrays.toString(boxes));
//                    return false;
//                }
//                rows[i].add(value);
//                columns[j].add(value);
//                boxes[box].add(value);
//
//            }
//        }
//        return true;
//    }

//    public static boolean isValidSudoku(char[][] board) {
//        for (int i = 0; i < board.length; i++) {
//            HashSet<Character> hashSetH = new HashSet<>();
//            HashSet<Character> hashSetV = new HashSet<>();
//            for (int j = 0; j < board.length; j++) {
//                char c1 = board[i][j];
//                char c2 = board[j][i];
//                if (c1 != '.') {
//                    if (hashSetH.contains(c1)) {
//                        return false;
//                    }
//                    hashSetH.add(c1);
//
//                }
//                if (c2 != '.') {
//                    if (hashSetV.contains(c2)) {
//                        return false;
//                    }
//                    hashSetV.add(c2);
//                }
//            }
//        }
//
//        for (int i = 0; i < board.length; i++) {
//            HashSet<Character> hashSet = new HashSet<>();
//            int startingRow = (i / 3) * 3;
//            int startingCol = (i % 3) * 3;
//            for (int j = 0; j < 3; j++) {
//                for (int k = 0; k < 3; k++) {
//                    char c = board[startingRow + j][startingCol + k];
//                    if (c != '.') {
//                        if (hashSet.contains(c)) {
//                            return false;
//                        }
//                        hashSet.add(c);
//                    }
//                }
//            }
//        }
//        return true;
//    }
}
