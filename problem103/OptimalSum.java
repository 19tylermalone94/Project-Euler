import java.util.List;
import java.util.ArrayList;

public class OptimalSum {

    public static void main(String[] args) {
        int size = 7;
        System.out.println(optSum(new ArrayList<>(), size));
    }

    static boolean optSum(List<Integer> set, int size) {
        if (set.size() == size) {
            System.out.println(set + " " + set.stream().reduce(0, Integer::sum));
            return isOpt(set);
        }

        if (set.isEmpty()) {
            for (int n = 1; true; ++n) {
                set.add(n);
                if (isOpt(set) && optSum(set, size)) {
                    return true;
                }
                set.remove(set.size() - 1);
            }
        }

        for (int n = set.get(set.size() - 1) + 1; n < set.get(set.size() - 1) + 10; ++n) {
            set.add(n);
            if (isOpt(set) && optSum(set, size)) {
                return true;    
            }
            set.remove(set.size() - 1);
        }
        return false;
    }

    static List<List<Integer>> powerSet(List<Integer> set) {
        List<List<Integer>> pSet = new ArrayList<>();
        int n = set.size();

        for (int i = 1; i < (1 << n); ++i) {
            List<Integer> subSet = new ArrayList<>();

            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) != 0) {
                    subSet.add(set.get(j));
                }
            }
            pSet.add(subSet);
        }
        return pSet;
    }

    static boolean isOpt(List<Integer> set) {
        List<List<Integer>> pSet = powerSet(set);
        for (int i = 0; i < pSet.size(); ++i) {
            List<Integer> a = pSet.get(i);
            for (int j = 0; j < pSet.size(); ++j) {
                if (i == j) continue;
                List<Integer> b = pSet.get(j);
                boolean disjoint = true;
                for (int n = 0; n < b.size(); ++n) {
                    if (a.contains(b.get(n))) {
                        disjoint = false;
                        break;
                    }
                }
                if (disjoint) {
                    int aSum = a.stream().reduce(0, Integer::sum);
                    int bSum = b.stream().reduce(0, Integer::sum);
                    if (aSum == bSum) return false;
                    if (a.size() < b.size() && aSum >= bSum) return false;
                    if (b.size() < a.size() && bSum >= aSum) return false;
                }
            }
        }
        return true;
    }
 
}