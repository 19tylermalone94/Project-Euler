import java.util.HashSet;
import java.util.Set;

public class LPF {

    public static void main(String[] args) {
        long i = 2;
        int consecutive = 0;
        while (consecutive < 4) {
            consecutive = (countPrimeFactors(i++, 2, new HashSet<Long>()) == 4) ? ++consecutive : 0;
        }
        System.out.println(i - 4);
    }

    public static int countPrimeFactors(long num, long divisor, Set<Long> factors) {
        while (divisor * divisor <= num) {
            if (num % divisor == 0) {
                 return Math.max(countPrimeFactors(num / divisor, 2, factors),
                 countPrimeFactors(divisor, 2,factors));
            }
            divisor++;
        } 
        factors.add(num);
        return factors.size();
    }
    
}
