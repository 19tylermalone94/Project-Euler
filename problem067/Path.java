import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Path {

    static ArrayList<ArrayList<Integer>> grid = new ArrayList<>() {
        {
            try {
                Scanner scan = new Scanner(new File("triangle.txt"));
                while (scan.hasNext()) {
                    String line = scan.nextLine();
                    ArrayList<Integer> row = new ArrayList<>();
                    Scanner tokenize = new Scanner(line);
                    while (tokenize.hasNext()) {
                        row.add(tokenize.nextInt());
                    }
                    add(row);
                }

            } catch (IOException e) {
                //
            }
        }
    };

    public static void main(String[] args) {
        for (int i = grid.size() - 2; i >= 0; --i) {
            for (int j = 0; j < grid.get(i).size(); ++j) {
                grid.get(i).set(j, grid.get(i).get(j) + Math.max(grid.get(i + 1).get(j), grid.get(i + 1).get(j + 1)));
            }
        }
        System.out.println(grid.get(0).get(0));
    }

}