import java.util.ArrayList;

public class SumOfPrimes {
    
    public static ArrayList<Integer> primes = new ArrayList<Integer>();
    public static void main(String[] args) {
        long sum = 0;
        for (int num = 2; num < 2000000; ++num) {
           if (isPrime(num)) {
                sum += num;
           }
        }
        System.out.println(sum);
    }

    public static boolean isPrime(int num) {
        for (int j = 2; j * j <= num; ++j) {
            if (num % j == 0) return false;
        }
        return true;
    }
}
