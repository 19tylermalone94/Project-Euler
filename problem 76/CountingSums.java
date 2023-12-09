public class CountingSums {

    public static void main(String[] args) {
        System.out.println(makeChange(100, 99));
    }

    static long makeChange(long amount, long coin) {
        if (amount == 0 || coin == 1) {
            return 1;
        } else if (coin > amount) {
            return makeChange(amount, coin - 1);
        } else {
            return makeChange(amount - coin, coin) + makeChange(amount, coin - 1);
        }
    }

}