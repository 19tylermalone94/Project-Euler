import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cubes {

    static void search() {
        long min = 10000000;
        long max = 100000000;
        while (true) {
            ArrayList<Long> cubes = new ArrayList<>();
            long i = 1;
            while (i * i * i < min) {
            ++i;
            }
            for (long n = i; n * n * n < max; ++n) {
                cubes.add(n * n * n);
            }

            for (Long a : cubes) {
                List<Long> perms = new ArrayList<Long>();
                perms.add(a);
                for (Long b : cubes) {
                    if (a == b) continue;
                    if (hasSameDigits(a, b)) perms.add(b);
                }
                if (perms.size() == 5) {
                    System.out.println(perms.toString());
                    return;
                }
            }
            min *= 10;
            max *= 10;
        }
    }

    static boolean hasSameDigits(long a, long b) {
        ArrayList<String> aList = new ArrayList<>(List.of(String.valueOf(a).split("")));
        ArrayList<String> bList = new ArrayList<>(List.of(String.valueOf(b).split("")));
        Collections.sort(aList);
        Collections.sort(bList);
        return aList.equals(bList);
    }

    public static void main(String[] args) {
        search();
    }

}