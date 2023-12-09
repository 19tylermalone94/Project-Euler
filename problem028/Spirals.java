public class Spirals {

    public static void main(String[] args) {
        int diagonalSum = 1;
        int width = 3;
        int index = 0;
        while (width <= 1001) {
            diagonalSum += (++index % (width - 1) == 0) ? index + 1 : 0;
            width = (index == (width * width) - 1) ? width + 2 : width;
        }
        System.out.println(diagonalSum);
    }

}