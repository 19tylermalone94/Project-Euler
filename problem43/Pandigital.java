import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Pandigital {

    static BigInteger bigSum = BigInteger.ZERO;

    public static void main(String[] arg) {
        ArrayList<String> num = new ArrayList<>(List.of("1", "0", "2", "3", "4", "5", "6" ,"7", "8", "9"));
        permute(num, new ArrayList<String>());
        System.out.println(bigSum);

    }

    static void permute(ArrayList<String> n, ArrayList<String> perm) {
        if (perm.size() == 10) {
            bigSum = (isSpecial(perm)) ? bigSum.add(new BigInteger(String.join("", perm))) : bigSum;
        }

        for (int i = 0; i < n.size(); ++i) {
            perm.add(n.remove(i));
            permute(n, perm);
            n.add(i, perm.remove(perm.size() - 1));
        }
    }

    static boolean isSpecial(ArrayList<String> n) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17};
        for (int i = 1; i < 8; ++i) {
            String num = "";
            for (int j = i; j < i + 3; ++j) {
                num += n.get(j);
            }
            if (Integer.parseInt(num) % primes[i - 1] != 0) return false;
        }
        return true;
    }

}