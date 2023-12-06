import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PandigitalSum {

    public static void main(String[] args) {
        // must be a 2 two digit times 3 digit product to have nine digits
        Set<Integer> products = new HashSet<Integer>();
        for (int a = 1; a <= 99; ++a) {
            for (int b = 100; b <= 9999; ++b) {
                if (isPandigitalProduct(a, b, a * b)) {
                    products.add(a * b);
                }
            }
        }
        int sum = 0;
        for (Integer num : products) {
            sum += num;
        }
        System.out.println(sum);
    }

    static boolean isPandigitalProduct(int a, int b, int c) {
        ArrayList<Integer> digits = new ArrayList<>();
        return check(a, digits) && check(b, digits) && check(c, digits) && digits.size() == 9;
    }

    static boolean check(int n, ArrayList<Integer> digits) {
        while (n != 0) {
            int lastDigit = n % 10;
            if (digits.contains(lastDigit) || lastDigit == 0) {
                return false;
            }
            digits.add(lastDigit);
            n /= 10;
        }
        return true;
    }

}