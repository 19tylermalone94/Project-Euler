import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class PathSum {

    static int[][] readFile(String fileName) {
        try {
            return Files.lines(Paths.get(fileName)).map(line -> Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);    
                } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] matrix = readFile("input.txt");
        System.out.println("Minimal path sum: " + findMinimalPathSum(matrix));
    }

    public static int findMinimalPathSum(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] +=matrix[i - 1][0];
        }
        for (int j = 1; j < matrix[0].length; j++) {
            matrix[0][j] += matrix[0][j - 1];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] += Math.min(matrix[i - 1][j], matrix[i][j - 1]);
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }
}
