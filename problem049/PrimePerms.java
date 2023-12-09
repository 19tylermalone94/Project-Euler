import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PrimePerms {

    public static void main(String[] args) {
        HashSet<HashSet<Integer>> sequences = new HashSet<>();
        for (int num = 1000; num <= 9999; num++) {
            if (isPrime(num, 2)) {
                // Convert number to a list of digits
                ArrayList<Integer> digits = numberToList(num);
                // Generate all unique permutations of this 4-digit prime
                ArrayList<Integer> perms = getPerms(digits, new ArrayList<>());
                // Check for arithmetic sequences in the list of permutations
                HashSet<Integer> seq = checkForSequences(perms);
                if (!seq.isEmpty()) {
                    sequences.add(seq);
                }
            }
        }
        for (HashSet<Integer> set : sequences) {
            System.out.println(set);
        }
    }

    static HashSet<Integer> checkForSequences(ArrayList<Integer> perms) {
        HashSet<Integer> sequences = new HashSet<>();
        for (int i = 0; i < perms.size() - 2; ++i) {
            for (int j = 0; j < perms.size() - 1; ++j) {
                for (int k = 0; k < perms.size(); ++k) {
                    if (i == j || i == k || j == k) continue;
                    if (perms.get(j) - perms.get(i) == perms.get(k) - perms.get(j) 
                        && perms.get(i).compareTo(perms.get(j)) != 0 
                        && perms.get(i).compareTo(perms.get(k)) != 0 
                        && perms.get(j).compareTo(perms.get(k)) != 0) {
                        sequences.addAll(List.of(perms.get(i), perms.get(j), perms.get(k)));
                    }
                }
            }
        }
        return sequences;
    }

    static ArrayList<Integer> getPerms(ArrayList<Integer> num, ArrayList<Integer> perm) {
        ArrayList<Integer> res = new ArrayList<>();
        if (perm.size() == 4 && isPrime(listToNum(perm), 2) 
            && !res.contains(listToNum(perm))
            && listToNum(perm) >= 1000) {
            res.add(listToNum(perm));
        }

        for (int i = 0; i < num.size(); ++i) {
            perm.add(num.remove(i));
            res.addAll(getPerms(num, perm));
            num.add(i, perm.remove(perm.size() - 1));
        }
        return res;
    }

    static ArrayList<Integer> numberToList(int num) {
        ArrayList<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(0, num % 10);
            num /= 10;
        }
        return list;
    }

    static int listToNum(ArrayList<Integer> list) {
        String num = "";
        for (Integer digit : list) {
            num += digit;
        }
        return Integer.parseInt(num);
    }

    static boolean isPrime(int n, int i) {  
        return (i * i > n) ? true : (n == 1 || n % i == 0) ? false : isPrime(n, i + 1);
    }

} 