import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmicableChains {

    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        for (int n = 4; n <= 1000000; ++n) {
            map.put(n, divisorSum(n));
        }
        List<Integer> chain = longestChain();
        Collections.sort(chain);
        System.out.println(chain.get(0));
    }

    static List<Integer> longestChain() {
         List<Integer> maxChain = new ArrayList<>();
         for (int n : map.keySet()) {
            List<Integer> chain = new ArrayList<>();
            int num = n;
            
            do {
                chain.add(num);
                if (!map.containsKey(num) || num == 1) {
                    chain.clear();
                    break;
                }
                num = map.get(num);
            } while (!chain.contains(num));

            if (chain.size() > maxChain.size() && num == chain.get(0)) {
                maxChain = chain;
            }
         }
        return maxChain;
    }

    static int divisorSum(int n) {
        int sum = 1;
        for (int i = 2; i * i <= n; ++i) {
            sum += n % i == 0 ? i + (n / i) : 0;
        }
        return sum;
    }
}