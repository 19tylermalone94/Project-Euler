public class Chains {

    static int[] memo = new int[10000000];

    static int getNextNum(int n) {
        int nextNum = 0;
        while(n > 0) {
            int lastDigit = n % 10;
            nextNum += lastDigit * lastDigit;
            n /= 10;
        }
        return nextNum;
    }

    static int count89() {
        int count = 0;
        for (int i = 1; i < 10000000; ++i) {
            int nextNum = i;
            while (nextNum != 1 && nextNum != 89) {
                if (memo[nextNum] == 0) {
                    int temp = getNextNum(nextNum);
                    memo[nextNum] = temp;
                    nextNum = temp;
                } else {
                    nextNum = memo[nextNum];
                }
            }
            count += nextNum == 89 ? 1 : 0;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(count89());
    }

}