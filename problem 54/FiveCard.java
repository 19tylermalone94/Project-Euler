import java.util.*;
import java.io.*;

public class FiveCard {
    // Hand ranking constants
    static final int HIGH_CARD = 0;
    static final int ONE_PAIR = 1;
    static final int TWO_PAIR = 2;
    static final int THREE_OF_A_KIND = 3;
    static final int STRAIGHT = 4;
    static final int FLUSH = 5;
    static final int FULL_HOUSE = 6;
    static final int FOUR_OF_A_KIND = 7;
    static final int STRAIGHT_FLUSH = 8;
    static final int ROYAL_FLUSH = 9;

    // Card mappings and special hand sets
    static final String CARD_RANKS = "23456789TJQKA";
    static Map<Character, Integer> rankToValueMapping;
    static Set<Integer> royalFlushSet;
    static List<Set<Integer>> straightFlushSets;

    // Static initialization block
    static {
        initializeMappings();
        initializeSpecialHandSets();
    }

    public static void main(String[] args) throws IOException {
        processPokerHands("poker.txt");
    }

    private static void processPokerHands(String fileName) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int countPlayer1Wins = 0;

            while ((line = fileReader.readLine()) != null) {
                String[] cards = line.split(" ");
                if (compareHands(scoreHand(Arrays.copyOfRange(cards, 0, 5)), scoreHand(Arrays.copyOfRange(cards, 5, 10))) > 0) {
                    countPlayer1Wins++;
                }
            }

            System.out.println(countPlayer1Wins);
        }
    }

    private static int compareHands(List<Integer> hand1, List<Integer> hand2) {
        for (int i = 0; i < hand1.size(); i++) {
            if (!hand1.get(i).equals(hand2.get(i))) {
                return hand1.get(i) - hand2.get(i);
            }
        }
        return 0;
    }

    private static List<Integer> scoreHand(String[] cards) {
        // Method implementation remains the same
    }

    private static void initializeMappings() {
        rankToValueMapping = new HashMap<>();
        for (int i = 0; i < CARD_RANKS.length(); i++) {
            rankToValueMapping.put(CARD_RANKS.charAt(i), i + 2);
        }
    }

    private static void initializeSpecialHandSets() {
        royalFlushSet = createSetFromRanks("TJQKA");
        straightFlushSets = new ArrayList<>();

        for (int i = 0; i < CARD_RANKS.length() - 5; i++) {
            straightFlushSets.add(createSetFromRanks(CARD_RANKS.substring(i, i + 5)));
        }
    }

    private static Set<Integer> createSetFromRanks(String ranks) {
        Set<Integer> rankSet = new HashSet<>();
        for (char rank : ranks.toCharArray()) {
            rankSet.add(rankToValueMapping.get(rank));
        }
        return rankSet;
    }
}
