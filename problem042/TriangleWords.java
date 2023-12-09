import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class TriangleWords {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        try {
            // Read all lines from a file as a stream and add them to the ArrayList
            String content = new String(Files.readAllBytes(Paths.get("words.txt")));
            words.addAll(new ArrayList<String>(List.of(content.substring(1, content.length() - 1).split("\",\""))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numTriangle = 0;
        for (String word : words) {
            int wordSum = 0;
            for (int i = 0; i < word.length(); ++i) {
                wordSum += word.charAt(i) - 'A' + 1;
            }
            if (isTriangle(wordSum)) {
                ++numTriangle;
            }
        }
        System.out.println(numTriangle);
    }

    // 0 = 0.5n^2 + 0.5n - x
    static boolean isTriangle(int x) {
            double sol = Math.max((-1 - Math.sqrt(1 + 8 * x)) / 2, (-1 + Math.sqrt(1 + 8 * x)) / 2);
            return sol == (int)sol;
        }

}
