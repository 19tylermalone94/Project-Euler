public class HDTriangle {

    public static void main(String[] args) {

        int numDivisors = 0;
        int num = 0;
        int comm_dif = 0;
        while (numDivisors <= 500) {
            comm_dif++;
            num += comm_dif;
            numDivisors = countDivisors(num);
        }
        System.out.println(num);
    }

    private static int countDivisors(int number) {
        int divisorCount = 0;
        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                // If divisors are equal, count only one
                if (number / i == i) {
                    divisorCount++;
                } else {
                    // Otherwise count both
                    divisorCount += 2;
                }
            }
        }
        return divisorCount;
    }
}
