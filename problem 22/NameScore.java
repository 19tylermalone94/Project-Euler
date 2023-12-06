import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NameScore {

    public static ArrayList<String> getNames(String file) {
        try {
            ArrayList<String> names = new ArrayList<>();
            Scanner fileReader = new Scanner(new File(file));
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine().replaceAll("\"", "").replaceAll(",", " ");
                Scanner scan = new Scanner(line);
                while (scan.hasNext()) {
                    names.add(scan.next());
                }
            }
            Collections.sort(names);
            return names;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int nameScore(String name, int index) {
        int nameScore = 0;
        for (int i = 0; i < name.length(); ++i) {
            nameScore += name.charAt(i) - 'A' + 1;
        }
        return nameScore * index;
    }
    public static void main(String[] args) {
        ArrayList<String> names = getNames("names.txt");
        long nameScoreSum = 0;
        for (int i = 0; i < names.size(); ++i) {
            nameScoreSum += nameScore(names.get(i), i + 1);
        }
        System.out.println(nameScoreSum);
    }

}