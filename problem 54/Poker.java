import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Poker {

    static HashMap<Character, Integer> map = new HashMap<>() {
        {
            put('T', 10);
            put('J', 11);
            put('Q', 12);
            put('K', 13);
            put('A', 14);
        }
    };

    static class Card implements Comparable<Card> {
        int val;
        char suit;

        Card(int val, char suit) {
            this.val = val;
            this.suit = suit;
        }

        public int compareTo(Card other) {
            return (val < other.val) ? -1 : (val == other.val) ? 0 : 1;
        }
    }

    static ArrayList<Card> dealCards(String[] cards, int start) {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = start; i < start + 5; ++i) {
            if (Character.isAlphabetic(cards[i].charAt(0))) {
                hand.add(new Card(map.get(cards[i].charAt(0)), cards[i].charAt(1)));
            } else {
                hand.add(new Card(cards[i].charAt(0) - '0', cards[i].charAt(1)));
            }
        }
        Collections.sort(hand);
        return hand;
    }

    static evaluateHand(ArrayList<Card> hand) {
        
    }

    static String[][] readFile(String filePath) throws Exception {
        Scanner reader = new Scanner(new File(filePath));
        String[][] deals = new String[1000][10];
        for (int d = 0; d < deals.length; ++d) {
            for (int h = 0; h < 10; ++h) {
                deals[d][h] = reader.next();
            }
        }
        return deals;
    }
 
    public static void main(String[] args) throws Exception {
        int wins = 0;
        String[][] deals = readFile("poker.txt");
        for (int i = 0; i < 1000; ++i) {
            ArrayList<Card> player1 = dealCards(deals[i], 0);
            ArrayList<Card> player1 = dealCards(deals[i], 5);
        }
        System.out.println(wins);
    }

}