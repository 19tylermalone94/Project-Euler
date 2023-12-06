import java.util.HashSet;

public class PermMultiple {

    public static void main(String[] args) {
        for (int n = 100; true; ++n) {
            HashSet<Integer> nSet = numToSet(n);
            boolean isSpecial = true;
            for (int k = 2; k <= 6; ++k) {
                isSpecial = nSet.equals(numToSet(k * n));
                if (!isSpecial) break;
            }
            if (isSpecial) {
                System.out.println(n);
                return;
            }
        }
    }

    static HashSet<Integer> numToSet(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n > 0) {
            set.add(n % 10);
            n /= 10;
        }
        return set;
    }

}