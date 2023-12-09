
public class PrimeIndex {

    public static void main(String[] args) {

        System.out.println(getPrimeAtIndex(10001));

    }

    public static long getPrimeAtIndex(int primeIndex) {
        int index = 0;
        long num = 2;
        long prime = 0;
        while (index < primeIndex) {
            if (isPrime(num)) {
                ++index;
                prime = num;
            }
            ++num;
        }
        return prime;
    }

    public static boolean isPrime(long num) {
        int divisor = 2;
        while (divisor < (num / 2) + 1) {
            if (num % divisor == 0) 
                return false;
            ++divisor;
        }
        return true;
    }
    
}
