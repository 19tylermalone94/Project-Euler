public class CountingRecs {

    public static void main(String[] args) {
        int area = 0;
        int minDif = Integer.MAX_VALUE;
        for (int h = 1; h <= 2000; ++h) {
            for (int w = h + 1; w <= 2000; ++w) {
                int numSubs = countSubRecs(w, h);
                int diff = Math.abs(2000000 - numSubs);
                if (diff < minDif) {
                    minDif = diff;
                    area = w * h;
                }
            }
        }
        System.out.println(area);
    }

    // number of ways to make vertical section with 2 slices is C(w + 1, 2)
    // number of ways to make a horizontal section with 2 slices is C(h + 1, 2)
    // number of total sub-rectangles is the product of these two
    static int countSubRecs(int w, int h) {
        return (w * (w + 1) / 2) * (h * (h + 1) / 2);
    }

}