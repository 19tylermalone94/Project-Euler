import java.util.ArrayList;
import java.util.List;

public class Reciprocal {

    public static void main(String[] args) {
        ArrayList<Integer> maxCycle = new ArrayList<>(List.of(0, 0));
        for (int i = 2; i < 1000; ++i) {
            int cycleLength = cycleLength(i, 1, new ArrayList<Integer>());
            maxCycle = (cycleLength > maxCycle.get(0)) ? new ArrayList<Integer>(List.of(cycleLength, i)) : maxCycle;
        }
        System.out.println(maxCycle.get(1));
    }

    public static int cycleLength(int n, int r, ArrayList<Integer> remainders) {
        if (r < n) {
            return cycleLength(n, r * 10, remainders);
        }

        // terminating decimals have no cycles
        if (r % n == 0) {
            return 0;
        }

        // if a repeat exists, there must be a cycle. Return distance between repeats.
        if (remainders.contains(r % n)) {
            return remainders.size() - remainders.indexOf(r % n);
        }

        remainders.add(r % n);
        return cycleLength(n, r % n, remainders);
    }

}