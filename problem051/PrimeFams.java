import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PrimeFams {

    static HashMap<Integer, ArrayList<ArrayList<ArrayList<Integer>>>> comboMap = new HashMap<>();
    
    public static void main(String[] args) {
        loadMap();
        for (long n = 50000; true; ++n) {
            for (int j = 1; j < len(n, 0) - 1; ++j) {
                for (ArrayList<Integer> slots : comboMap.get(len(n, 0)).get(j)) {
                    ArrayList<Integer> family = new ArrayList<>();
                    for (int k = 0; k < 10; ++k) {
                        if (family.size() + 10 - k + 1 < 8) break;
                        if (slots.contains(0) && k == 0) continue;
                        int a = replaceNumbers(n, k, slots);
                        if (isPrime(a, 2)) family.add(a);
                    }
                    if (family.size() >= 8) {
                        Collections.sort(family);
                        System.out.println(family);
                        System.out.println(family.get(0));
                        return;
                    }
                }
            }
        }
    }

    static void loadMap() {
        for (int n = 4; n < 11; ++n) {
            ArrayList<ArrayList<ArrayList<Integer>>> listOfCombosForN = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                listOfCombosForN.add(new ArrayList<ArrayList<Integer>>());
            }
            for (int k = 1; k < n; ++k) {
                listOfCombosForN.set(k, getSlotCombos(n, k));
            }
            comboMap.put(n, listOfCombosForN);
        }
    }

    static int replaceNumbers(long n, int r, ArrayList<Integer> slots) {
        int replacedNumber = 0;
        int multiplier = 1;
        while (n > 0) {
            int digit = (int)(n % 10);
            if (slots.contains(len(n, 0) - 1)) {
                digit = r;
            }
            replacedNumber += digit * multiplier;
            multiplier *= 10;
            n /= 10;
        }
        return replacedNumber;
    }

    static ArrayList<ArrayList<Integer>> getSlotCombos(int n, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            list.add(0);
        }
        return getSlotCombos(list, n, k, 0, 0, new ArrayList<>());
    }

    static ArrayList<ArrayList<Integer>> getSlotCombos(ArrayList<Integer> list, int n, int k, 
        int p, int lo, ArrayList<ArrayList<Integer>> holder) {
        if (lo > n) {
            return holder;
        }
        if (p > k - 1) {
            holder.add(new ArrayList<>(list));
            return holder;
        }
        list.set(p, lo);
        getSlotCombos(list, n, k, p + 1, lo + 1, holder);
        getSlotCombos(list, n, k, p, lo + 1, holder);
        return holder;
    }

    static int len(long n, int len) {
        return (n == 0) ? len : len(n / 10, len + 1);
    }

    static boolean isPrime(int n, int i) {
        return (i * i > n) ? n != 1 : (n % i == 0) ? false : isPrime(n, i + 1);
    }
}