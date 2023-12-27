import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class BiggestBig {

    public static void main(String[] args) {
        long max = 0;
        int line = 0;
        List<String> content = readFile("input.txt");
        for (int i = 0; i < content.size(); ++i) {
            String s = content.get(i);
            int[] pair = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
            long num = (long)(pair[1] * Math.log(pair[0]));
            if (num > max) {
                max = num;
                line = i;
            }
        }
        System.out.println(line);
    }

    static List<String> readFile(String fileName) {
        try {
            return Files.lines(Paths.get(fileName)).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}