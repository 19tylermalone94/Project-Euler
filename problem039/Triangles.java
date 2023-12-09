import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Triangles {

    static HashMap<Integer, HashSet<HashSet<Integer>>> triples = new HashMap<>();

    public static void main(String[] args) {
        for (int a = 1; a < 1000; ++a) {
            for (int b = 1; b < 1000; ++b) {
                for (int c = 1; c < 1000; ++c) {
                    if (a + b + c > 1000) continue;
                    if (a*a + b*b == c*c) {
                        if (!triples.containsKey(a + b + c)) {
                            HashSet<HashSet<Integer>> newSet = new HashSet<>();
                            newSet.add(new HashSet<Integer>(List.of(a, b, c)));
                            triples.put(a + b + c, newSet);
                        } else {
                            triples.get(a + b + c).add(new HashSet<>(List.of(a, b, c)));
                        }
                    }
                }
            }
        }
        int maxP = 0;
        int maxSol = 0;
        for (Integer p : triples.keySet()) {
            if (triples.get(p).size() > maxSol) {
                maxP = p;
                maxSol = triples.get(p).size();
            }
        }
        System.out.println(maxP);
    }
 
}