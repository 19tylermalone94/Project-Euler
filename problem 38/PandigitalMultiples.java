import java.util.ArrayList;

public class PandigitalMultiples {

    public static void main(String[] arg) {
        int maxPandigital = 0;
        for (int k = 1; k < 10000; ++k) {
            String s = "";
            for (int n = 1; n <= 9 && s.length() < 9; ++n) {
                s += k * n;
            }
            maxPandigital = (s.length() == 9 && check(Integer.parseInt(s))) ? Math.max(Integer.parseInt(s), maxPandigital) : maxPandigital;
        }
        System.out.println(maxPandigital);
    }

    static boolean check(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
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

    System.out.println()

}