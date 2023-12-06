import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LPGrid {

    public static void main(String[] args) {

        try {
            int[] grid = new int[20 * 20];
            Scanner scan = new Scanner(new File("grid"));
            for (int row = 0; row < 20; ++row) {
                for (int col = 0; col < 20; ++ col) {
                    String token = scan.next();
                    if (token.charAt(0) == '0') {
                        token = token.substring(1);
                    }
                    grid[(row * 20) + col] = Integer.parseInt(token);
                }
            }
            printArray(grid);
            System.out.println("Largest product of four adjacent elements: " + findLP(grid));
        } catch (FileNotFoundException e) {
            System.out.println("bye");
        }
    }

    public static long findLP(int[] grid) {
        long largestProduct = 0;

        // horizontal
        for (int row = 0; row < 20; ++row) {
            for (int col = 0; col < 16; ++col) {
                long product = grid[(row * 20) + col] * grid[(row * 20) + col + 1] * grid[(row * 20) + col + 2] * grid[(row * 20) + col + 3];
                largestProduct = Math.max(largestProduct, product);
            }
        }

        // vertical 
        for (int row = 0; row < 16; ++row) {
            for (int col = 0; col < 20; ++col) {
                long product = grid[(row * 20) + col] * grid[((row + 1) * 20) + col] * grid[((row + 2) * 20) + col] * grid[((row + 3) * 20) + col];
                largestProduct = Math.max(largestProduct, product);
            }
        }
        
        // diagonal right
        for (int row = 0; row < 16; ++row) {
            for (int col = 0; col < 16; ++ col) {
                int a = grid[((row + 0) * 20) + col + 0];
                int b = grid[((row + 1) * 20) + col + 1];
                int c = grid[((row + 2) * 20) + col + 2];
                int d = grid[((row + 3) * 20) + col + 3];
                long product = a * b * c * d;
                largestProduct = Math.max(largestProduct, product);
            }
        }

         // diagonal left
        for (int row = 0; row < 16; ++row) {
            for (int col = 0; col < 16; ++ col) {
                int a = grid[((row + 3) * 20) + col + 0];
                int b = grid[((row + 2) * 20) + col + 1];
                int c = grid[((row + 1) * 20) + col + 2];
                int d = grid[((row + 0) * 20) + col + 3];
                long product = a * b * c * d;
                largestProduct = Math.max(largestProduct, product);
            }
        }
        return largestProduct;
    }

    public static void printArray(int[] grid) {
        for (int row = 0; row < 20; ++row) {
            for (int col = 0; col < 20; ++col) {
                System.out.print(grid[(row * 20) + col] + " ");
            }
            System.out.println();
        }
    }

}