import java.util.ArrayList;

public class LCM {

    public static int factorRange = 20;
    public static ArrayList<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) {
        long leastCommonMultiple = getLCMofFactorRange();
        System.out.println(leastCommonMultiple);
    }

    public static long getLCMofFactorRange() {
        getUniquePrimesInRange();
        scalePrimesToHighestPowerInRange();
        return scaledPrimeProduct();
    }

    public static void getUniquePrimesInRange() {
        for (int i = 2; i <= factorRange; ++i) {
            getPrimeFactors(i);
        }
    }

    public static void getPrimeFactors(int num) {
        int divisor = 2;
        while(divisor < num) {
            if (num % divisor == 0) {
                getPrimeFactors(num / divisor);
                getPrimeFactors(divisor);
                return;
            }
            ++divisor;
        }
        if (!primeList.contains(num)) {
            primeList.add(num);
        }
    }

    public static void scalePrimesToHighestPowerInRange() {
        for (int i = 0; i < primeList.size(); ++i) {
            int num = primeList.get(i);
            int scaledNum = num;
            while (scaledNum * num < factorRange) {
                scaledNum *= num;
            }
            primeList.set(i, scaledNum);
        }
    }

    public static long scaledPrimeProduct() {
        long product = 1;
        for (Integer num : primeList) {
            product *= num;
        }
        return product;
    }
    
}
