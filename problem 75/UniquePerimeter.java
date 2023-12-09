public class UniquePerimeter {
    
    static int[] perimMap = new int[1500001];

    static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static void main(String[] args) {
        for (int m = 2; m < 15000; ++m) {
            for (int n = 1; n < m; ++n) {
                if ((m - n) % 2 == 1 && gcd(m, n) == 1) {
                    int a = m * m - n * n;
                    int b = 2 * m * n;
                    int c = m * m + n * n;
                    int perim = a + b + c;
                    for (int k = 1; k * perim < perimMap.length; k++) {
                        perimMap[k * perim] += 1;
                    }
                }
            }
        }
        int count = 0;
        for (int c : perimMap) {
            count += (c == 1) ? 1 : 0;
        }
        System.out.println(count);
    }
}
