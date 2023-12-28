import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Containment {

    public static void main(String[] args) {
        List<String> content = readFile("input.txt");
        int count = 0;
        for (String s : content) {
            int[] ss = Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();

            int[] o = {0, 0};
            int[] a = {ss[0], ss[1]};
            int[] b = {ss[2], ss[3]};
            int[] c = {ss[4], ss[5]};

            double abc = getArea(a, b, c);

            double oab = getArea(o, a, b);
            double obc = getArea(o, b, c);
            double oca = getArea(o, c, a);

            if (oab + obc + oca - abc < Double.MIN_VALUE) {
                ++count;
            }
        }
        System.out.println(count);
    }

    static double getArea(int[] a, int[] b, int[] c) {
        return 0.5 * Math.abs(a[0] * (b[1] - c[1]) + b[0] * (c[1] - a[1]) + c[0] * (a[1] - b[1]));
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