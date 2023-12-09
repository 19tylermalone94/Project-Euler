public class Spiral {

    public static void main(String[] args) {
        int numPrime = 0;
        int width = 3;
        int index = 0;
        while (true) {
            numPrime += (++index % (width - 1) == 0 && isPrime(index + 1, 2)) ? 1 : 0; 
            if (index == (width * width) - 1) {
                if ((double)numPrime / (width * 2.0 - 1) < 0.1) {
                    System.out.println(width);
                    return;
                }
                width += 2;
            }
        }
    }

    static boolean isPrime(int n, int i) {
        return (i * i > n) ? n > 1 : (n % i == 0) ? false : isPrime(n, i + 1);
    }

}