public class FifthPower {

    public static void main(String[] args) {

        // 7 * 9^5 is 6 digit number, so check all numbers with 6 digits or less
        long sum = 0;
        for (int i = 2; i < 1000000; ++i) {
            sum += (digitSum(i, 0) == i) ? i : 0;
        }
        System.out.println(sum);
    }

    static int digitSum(int n, int sum) {
        if (n == 0) return sum;

        return digitSum(n / 10, sum + (int)Math.pow(n % 10, 5));
    }

}