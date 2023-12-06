import java.math.BigInteger;

public class BigFib {

    public static void main(String[] args) {
        BigInteger nMinus2 = BigInteger.ONE;
        BigInteger nMinus1 = BigInteger.ONE;
        int fibIndex = 3;
        while (nMinus2.add(nMinus1).toString().length() != 1000) {
            BigInteger temp = nMinus2.add(nMinus1);
            nMinus1 = nMinus2;
            nMinus2 = temp;
            ++fibIndex;
        }
        System.out.println(fibIndex);
    }
}