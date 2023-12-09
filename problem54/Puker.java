import java.util.*;
import java.io.*;

public class Puker {
    static final int HC = 0;
    static final int OP = 1;
    static final int TP = 2;
    static final int TK = 3;
    static final int S = 4;
    static final int F = 5;
    static final int FH = 6;
    static final int FK = 7;
    static final int SF = 8;
    static final int RF = 9;

    static final String cards = "23456789TJQKA";
    static Map<Character, Integer> mapping;
    static Set<Integer> rf;
    static List<Set<Integer>> sf;

    static {
        mapping = new HashMap<>();
        for (int i = 0; i < cards.length(); i++) {
            mapping.put(cards.charAt(i), i + 2);
        }
        rf = new HashSet<>();
        for (char c : "TJQKA".toCharArray()) {
            rf.add(mapping.get(c));
        }
        sf = new ArrayList<>();
        for (int i = 0; i < cards.length() - 5; i++) {
            Set<Integer> tempSet = new HashSet<>();
            for (char c : cards.substring(i, i + 5).toCharArray()) {
                tempSet.add(mapping.get(c));
            }
            sf.add(tempSet);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader f = new BufferedReader(new FileReader("poker.txt"))) {
            String line;
            int c = 0;
            while ((line = f.readLine()) != null) {
                String[] s = line.split(" ");
                if (compare(score(Arrays.copyOfRange(s, 0, 5)), score(Arrays.copyOfRange(s, 5, 10))) > 0) {
                    c++;
                }
            }
            System.out.println(c);
        }
    }

    private static int compare(List<Integer> hand1, List<Integer> hand2) {
        for (int i = 0; i < hand1.size(); i++) {
            if (!hand1.get(i).equals(hand2.get(i))) {
                return hand1.get(i) - hand2.get(i);
            }
        }
        return 0;
    }

    private static List<Integer> score(String[] cards) {
        List<Character> suits = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (String card : cards) {
            suits.add(card.charAt(1));
            values.add(mapping.get(card.charAt(0)));
        }
        Set<Character> ss = new HashSet<>(suits);
        Set<Integer> sv = new HashSet<>(values);

        List<Integer> cv = new ArrayList<>(sv);
        cv.sort((x, y) -> {
            int countX = Collections.frequency(values, x);
            int countY = Collections.frequency(values, y);
            if (countX != countY) {
                return countY - countX;
            }
            return y - x;
        });

        List<Integer> result = new ArrayList<>();
        if (ss.size() == 1) {
            if (sv.equals(rf)) {
                result.add(RF);
                return result;
            }
            if (sf.contains(sv)) {
                result.add(SF);
                result.add(Collections.max(values));
                return result;
            }
            if (sv.size() > 2) {
                result.add(F);
                result.addAll(values.stream().sorted(Collections.reverseOrder()).toList());
                return result;
            }
        }
        if (sv.size() == 2) {
            int c1 = cv.get(0);
            int c2 = cv.get(1);
            if (Collections.frequency(values, c1) == 3) {
                result.add(FH);
                result.addAll(cv);
                return result;
            }
            result.add(FK);
            result.addAll(cv);
            return result;
        }
        if (sf.contains(sv)) {
            result.add(S);
            result.add(Collections.max(values));
            return result;
        }
        if (Collections.frequency(values, cv.get(0)) == 3) {
            result.add(TK);
            result.addAll(cv);
            return result;
        }
        if (Collections.frequency(values, cv.get(0)) == 2) {
            if (Collections.frequency(values, cv.get(1)) == 2) {
                result.add(TP);
                result.addAll(cv);
                return result;
            }
            result.add(OP);
            result.addAll(cv);
            return result;
        }
        result.add(HC);
        result.addAll(cv);
        return result;
    }
}
