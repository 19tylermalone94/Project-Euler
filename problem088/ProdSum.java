import java.util.*;

public class ProdSum {

    static Map<Integer, List<List<Integer>>> factorMap = getFactorSetsUpTo(24000);

    static Map<Integer, List<List<Integer>>> getFactorSetsUpTo(int n) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            map.put(i, getFactors(i));
        }
        return map;
    }

    static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        getFactorsHelper(n, 2, new ArrayList<>(), result);
        return result;
    }

    static void getFactorsHelper(int n, int start, List<Integer> current, List<List<Integer>> result) {
        if (n == 1) {
            if (!current.isEmpty()) {
                result.add(new ArrayList<>(current));
            }
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                current.add(i);
                getFactorsHelper(n / i, i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

    static int findMinimalProSum(int k) {
        for (int n = 1; n <= 2 * k; n++) {
            for (List<Integer> factors : factorMap.get(n)) {
                if (sum(factors) + k - factors.size() == n) {
                    return n;
                }
            }
        }
        return -1;
    }

    static int sum(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int k = 2; k <= 12000; ++k) {
            int num = findMinimalProSum(k);
            if (set.add(num)) {
                sum += num;
            }
        }
        System.out.println(sum);
    }
}
