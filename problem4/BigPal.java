public class BigPal {

    public static void main(String[] args) {
        int biggestPal = getBiggestPal(999, 999);
        System.out.println(biggestPal);
    }

    public static int getBiggestPal(int num1, int num2) {
        int biggestPal = 0;
        for (int i = num1; i > 99; --i) {
            for (int j = num2; j > 99; --j) {
                int product = i * j;
                biggestPal = (isPal(product + "")) ? Math.max(biggestPal, product) : biggestPal;
            }
        }
        return biggestPal;
    }

    private static boolean isPal(String numString) {
        if (numString.length() <= 1) return true;
        if (numString.charAt(0) == numString.charAt(numString.length() - 1)) {
            return true && isPal(numString.substring(1, numString.length() - 1));
        }
        return false;
    }
    
}
