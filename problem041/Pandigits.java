import java.util.ArrayList;

public class Pandigits {

    static ArrayList<Integer> primes = new ArrayList<>();

    // 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45 % 3 == 0
    // 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 = 36 % 3 == 0
    // Start in the seven digit numbers

    public static void main(String[] args) {
        int max = 0;
        for (int i = 10000000; i > 0; --i) {
            if (isPrime(i, 2) && isPandigital(i)) {
                System.out.println(i);
                break;

            }
        }
    }

    static boolean isPandigital(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0) {
            if (digits.contains(n % 10) || n % 10 == 0 || n % 10 > 7) return false;
            digits.add(n % 10);
            n /= 10;
        }
        return true;
    }

    static boolean isPrime(int n, int i) {
        return (i != 1 && i * i > n) ? true : (n % i == 0) ? false : isPrime(n, i + 1);
    }

}