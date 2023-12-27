import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.stream.*;

public class SudokuSolver {

    public static void main(String[] args) {
        List<String> content = readFile("input.txt");
        int sum = 0;
        for (String s : content) {
            List<Integer> board = s.lines().flatMap(line -> Arrays.stream(line.trim().split(""))).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
            solve(board, 0, 0);
            sum += board.get(0)*100 + board.get(1)*10 + board.get(2);
            for (int row = 0; row < 9; ++row) {
                for (int col = 0; col < 9; ++col) {
                    System.out.print(board.get(row * 9 + col) + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println(sum);
    }

    static boolean solve(List<Integer> board, int row, int col) {
        if (row == 9) {
            return !board.contains(0);
        }

        if (board.get(row*9 + col) != 0) {
            return solve(board, col + 1 == 9 ? row + 1 : row, (col + 1) % 9);
        }

        for (int element = 1; element <= 9; ++element) {
            if (canPlace(row, col, element, board)) {
                int e = board.get(row*9 + col);
                board.set(row*9 + col, element);
                if (solve(board, col + 1 == 9 ? row + 1 : row, (col + 1) % 9)) {
                    return true;
                }
                board.set(row*9 + col, e);
            }
        }
        return false;
    }

    static boolean canPlace(int row, int col, int element, List<Integer> board) {
        // check row
        for (int j = 0; j < 9; ++j) {
            if (j == col) continue;
            if (board.get(row*9 + j) == element) {
                return false;
            }
        }

        //check col
        for (int i = 0; i < 9; ++i) {
            if (i == row) continue;
            if (board.get(i*9 + col) == element) {
                return false;
            }
        }

        // check block
        int bRow = row < 3 ? 0 : row < 6 ? 3 : 6; 
        int bCol = col < 3 ? 0 : col < 6 ? 3 : 6;
        for (int i = bRow; i < bRow + 3; ++i) {
            for (int j = bCol ;j < bCol + 3; ++j) {
                if (i == row && j == col) continue;
                if (board.get(i*9 + j) == element) {
                    return false;
                }
            }
        }
        return true;
    }

    static List<String> readFile(String fileName) {
        try {
            return Arrays.stream(new String(Files.readAllBytes(Paths.get(fileName))).split("Grid \\d+\\n")).filter(s -> !s.isBlank()).toList();
        } catch (IOException e) {
            return null;
        }
    }
}