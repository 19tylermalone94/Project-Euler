import java.math.BigInteger;

public class ReallyBig {

    public static void main(String[] args) {
        BigInteger num = new BigInteger("28433");
        num = num.multiply((BigInteger.TWO).pow(7830457)).add(BigInteger.ONE);
        System.out.println(num.mod(BigInteger.valueOf(10000000000l)));
    }

}