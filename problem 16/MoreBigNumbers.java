import java.math.BigInteger;

public class MoreBigNumbers {

    public static void main(String[] args) {

        BigInteger num = BigInteger.valueOf(2);
        for (int i = 1; i < 1000; ++i) {
            num = num.multiply(BigInteger.valueOf(2));
        }
        String numString = num + "";

        long sum = 0;
        for (int i = 0; i < numString.length();++i) {
            sum += (int)(numString.charAt(i) - '0');
        }
        System.out.println(sum);
    }

}