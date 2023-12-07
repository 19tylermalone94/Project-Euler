import java.util.HashSet;

public class FacChains {

    static int factorial(int n) {
        return (n < 2) ? 1 : n * factorial(n - 1);
    }

    static int factorialDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += factorial(n % 10);
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 1000000; ++i) {
            HashSet<Integer> set = new HashSet<>();
            set.add(i);
            int fac = factorialDigitSum(i);
            while(!set.contains(fac)) {
                set.add(fac);
                fac = factorialDigitSum(fac);
            }
            count += (set.size() == 60) ? 1 : 0;
        }
        System.out.println(count);
    }

}