public class SquaresAndSums {

    public static int n;
    
    public static void main(String[] args) {
        n = Integer.parseInt(args[0]);
        int difference = squareOfSum() - sumOfSquares();
        System.out.println(difference);

    }

    public static int sumOfSquares() {
        int sum = 0;
        for (int i = 1; i <= n; ++i) {
            int square = i * i;
            sum += square;
        }
        return sum;
    }

    public static int squareOfSum() {
        int sum = 0;
        for (int i = 1; i <= n; ++i) {
            sum += i;
        }
        return sum * sum;
    }

}
