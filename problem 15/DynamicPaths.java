
public class DynamicPaths {

    public static long numCombos = 0;
    public static void main(String[] args) {

        countPaths(0, 0);
        System.out.println(numCombos);
    }

    public static void countPaths(int a, int b) {
        if (a == 20 && b == 20) {
            ++numCombos;
            return;
        }
        if (a < 20) {
            countPaths(a + 1, b);
        }
        if (b < 20) {
            countPaths(a, b + 1);
        }
    }
}