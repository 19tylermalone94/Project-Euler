import java.math.BigInteger;

public class FactorialDigitSum {

    public static void main(String[] args) {

        BigInteger big_num = factorial(100);
        String bigNumString = big_num.toString();
        long sum = 0;
        for (int i = 0; i < bigNumString.length(); ++i) {
            sum += bigNumString.charAt(i) - '0';
        }
        System.out.println(sum);
    }

    public static BigInteger factorial(long num) {
        if (num == 1) return BigInteger.ONE;

        return BigInteger.valueOf(num).multiply(factorial(num - 1));
    }

}