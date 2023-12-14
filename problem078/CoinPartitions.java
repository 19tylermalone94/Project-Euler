
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CoinPartitions {

    static List<BigInteger> p = new ArrayList<>() {
        {
            add(BigInteger.ONE);
        }
    };

    private static BigInteger getP(int index) {
        return (index < 0) ? BigInteger.ZERO : p.get(index);
    }

    public static BigInteger makeChange(int n) {
        while (p.size() <= n) {
            BigInteger nextP = BigInteger.ZERO;
            int sign = 1;

            for (int k = 1; ; k++, sign = -sign) {
                int pent1 = k * (3 * k - 1) / 2;
                int pent2 = k * (3 * k + 1) / 2;

                if (pent1 > p.size()) break;

                if (pent1 <= p.size()) nextP = nextP.add(getP(p.size() - pent1).multiply(BigInteger.valueOf(sign)));
                if (pent2 <= p.size()) nextP = nextP.add(getP(p.size() - pent2).multiply(BigInteger.valueOf(sign)));
            }
            p.add(nextP);
        }
        return p.get(n);
    }

    public static void main(String[] args) {
        int n = 100;
        BigInteger count;

        do {
            count = makeChange(n);
            n++;
        } while (!count.mod(BigInteger.valueOf(1000000)).equals(BigInteger.ZERO));

        System.out.println("The first n for which p(n) is divisible by 1,000,000: " + (n - 1));
    }
}
