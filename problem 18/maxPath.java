import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class maxPath {

    public static ArrayList<ArrayList<Integer>> grid = new ArrayList<>();

    public static void main(String[] args) {

        try {
            Scanner scan = new Scanner(new File("grid"));

            for (int i = 0; i < 15; ++i) {
                String line = scan.nextLine();
                ArrayList<Integer> row = new ArrayList<>();
                Scanner scan2 = new Scanner(line);
                for (int j = 0; j < i + 1; ++j) {
                    String num = scan2.next();
                    if (num.charAt(0) == '0') {
                        num = num.substring(1);
                    }
                    row.add(Integer.parseInt(num));
                }
                grid.add(row);
            }
            for (ArrayList<Integer> row : grid) {
                System.out.println(row.toString());
            }

            System.out.println(maxPath(0, 0, 0));

        } catch (FileNotFoundException e) {
            System.out.println("FNF");
        }

    }

    public static int maxPath(int row, int col, int sum) {
        sum += grid.get(row).get(col);

        if (row == 14) {
            return sum;
        }
        return Math.max(maxPath(row + 1, col, sum), maxPath(row + 1, col + 1, sum));
    }
}