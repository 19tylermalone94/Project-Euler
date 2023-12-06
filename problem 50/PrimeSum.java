import java.util.ArrayList;

public class PrimeSum {

    public static void main(String[] args) {
        
        ArrayList<Integer> primes = generatePrimes(1000000);
        // find the longest sequence of consecutive primes who's sum is under 1'000'000
        int limit = 1000000, sum, count, maxCount = 0, maxPrime = 0;
        for (int i = 0; i < primes.size(); i++) {
            sum = count = 0;
            for (int j = i; j < primes.size() && sum < limit; j++, count++) {
                sum += primes.get(j);
                if (count > maxCount && isPrime(sum, 2)) {
                    maxCount = count;
                    maxPrime = sum;
                }
            }
        }
        System.out.println("The longest sequence has " + maxCount + " primes and sums up to " + maxPrime);
    }

    static ArrayList<Integer> generatePrimes(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i += (i == 2) ? 1 : 2) {
            if (isPrime(i, 2)) primes.add(i);
        }
        return primes;
    }

    static boolean isPrime(int n, int i) {
        return (i * i > n) ? n != 1 : (n % i == 0) ? false : isPrime(n, i + 1);
    }
    
}