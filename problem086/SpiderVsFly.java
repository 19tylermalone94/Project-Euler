public class SpiderVsFly {

    public static void main(String[] args) {
        int M = 1;
        int solutions = 0;
        while (solutions < 1000000) {
            for (int h = 1; h <= M; h++) {
                for (int w = h; w <= M; w++) {
                    int l = M;
                    double path = Math.sqrt((w+h) * (w+h) + l*l);
                    solutions += path == (int)path ? 1 : 0;
                }
            }
            M++;
        }
        System.out.println(M - 1);
    }

}