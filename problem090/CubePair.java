import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CubePair {

    static List<Pair> squares = new ArrayList<>() {
        {
            add(new Pair(0, 1));
            add(new Pair(0, 4));
            add(new Pair(0, 9));
            add(new Pair(1, 6));
            add(new Pair(2, 5));
            add(new Pair(3, 6));
            add(new Pair(4, 9));
            add(new Pair(6, 4));
            add(new Pair(8, 1));
        }
    };

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            return x == ((Pair)other).x && y == ((Pair)other).y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }

    static List<List<Integer>> diceCombinations() {
        List<List<Integer>> list = new ArrayList<>();
        for (int a = 0; a < 5; ++a) {
            for (int b = a + 1; b < 6; ++b) {
                for (int c = b + 1; c < 7; ++c) {
                    for (int d = c + 1; d < 8; ++d) {
                        for (int e = d + 1; e < 9; ++e) {
                            for (int f = e + 1; f < 10; ++f) {
                                list.add(List.of(a, b, c, d, e, f));
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    static List<Pair> getAllPairs(List<Integer> d1, List<Integer> d2) {
        List<Pair> pairs = new ArrayList<>();
        for (int x : d1) {
            for (int y : d2) {
                pairs.add(new Pair(x, y));
                if (x == 6 || x == 9) {
                    pairs.add(new Pair(6, y));
                    pairs.add(new Pair(9, y));
                }
                if (y == 6 || y == 9) {
                    pairs.add(new Pair(x, 6));
                    pairs.add(new Pair(x, 9));
                }
            }
        }
        return pairs;
    }

    static boolean containsAllSquares(List<Pair> pairs) {
        for (Pair square : squares) {
            if (!pairs.contains(square) && !pairs.contains(new Pair(square.y, square.x))) {
                return false;
            }
        }
        return true;
    }
    

    public static void main(String[] args) {
        List<List<Integer>> die = diceCombinations();
        int count = 0;
        for (int d1 = 0; d1 < die.size() - 1; ++d1) {
            for (int d2 = d1; d2 < die.size(); ++d2) {
                count += containsAllSquares(getAllPairs(die.get(d1), die.get(d2))) ? 1 : 0;
            }
        }
        System.out.println(count);
    }
    
}
