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

    public static int findMinimalPathSum(int[][] matrix) {
        int[][] costMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            costMatrix[i][0] = matrix[i][0];
        }
        for (int col = 1; col < matrix[0].length; col++) {
            costMatrix[0][col] = costMatrix[0][col - 1] + matrix[0][col];
            for (int row = 1; row < matrix.length; row++) {
                costMatrix[row][col] = Math.min(costMatrix[row - 1][col], costMatrix[row][col - 1]) + matrix[row][col];
            }
            for (int row = matrix.length - 2; row >= 0; row--) {
                costMatrix[row][col] = Math.min(costMatrix[row][col], costMatrix[row + 1][col] + matrix[row][col]);
            }
        }
        int minPathSum = costMatrix[0][matrix[0].length - 1];
        for (int row = 1; row < matrix.length; row++) {
            if (costMatrix[row][matrix[0].length - 1] < minPathSum) {
                minPathSum = costMatrix[row][matrix[0].length - 1];
            }
        }
        return minPathSum;
    }

    public static void main(String[] args) {
        int[][] matrix = readFile("input.txt");        
        System.out.println("Minimal path sum is: " + findMinimalPathSum(matrix));
    }
}
