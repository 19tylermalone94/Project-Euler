public class Collatz {

    public static void main(String[] args) {
        long[] mem = new long[1000000];
        long maxStart = 1;
        long maxTerms = 1;
        for (int i = 1; i < 1000000; ++i) {
            long currTerms = 1;
            long num = i;
            while (num != 1) {
                if (num < 1000000 && mem[(int)num] != 0) { 
                    currTerms += mem[(int)num] - 1;
                    break;
                }
                if (num % 2 == 0) {
                    num /= 2;
                } else {
                    num = 3 * num + 1;
                }
                ++currTerms;
            }
            if (currTerms > maxTerms) {
                maxStart = i;
                maxTerms = currTerms;
            }
            mem[i] = currTerms;
        }
        System.out.println(maxStart);
    }
}
