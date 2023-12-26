public class AlmostEquilaterals {

    public static void main(String[] args) {
        long count = 0;
        for (long n = 3; n <= 333333333; n += 2) {
            long base1 = n + 1, base2 = n - 1;
            long heightSquared1 = n * n - (base1 / 2) * (base1 / 2);
            long heightSquared2 = n * n - (base2 / 2) * (base2 / 2);

            if (isPerfectSquare(heightSquared1)) count += n * 2 + base1;
            if (isPerfectSquare(heightSquared2)) count += n * 2 + base2;
        }
        System.out.println(count);
    }

    private static boolean isPerfectSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return number == sqrt * sqrt;
    }
}
