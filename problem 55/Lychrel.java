public class Lychrel {

    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 10000; ++i) {
            long num = i;
            for (int k = 0; k < 50; ++k) {
                num = num + reverse(num);
                if (isPal(num)) {
                    ++count;
                    break;
                }
            }
        }
        System.out.println(10000 - count);
    }

    static boolean isPal(long n) {
        return n == reverse(n);
    }

    static long reverse(long n) {
        long reversed = 0;
        while (n > 0) {
            reversed = (reversed * 10) + n % 10;
            n /= 10;
        }
        return reversed;
    }

}