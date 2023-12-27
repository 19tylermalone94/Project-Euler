import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;

public class Anagram {

    static Map<Integer, List<Long>> squares = new HashMap<>();

    public static void main(String[] args) {
        loadSquares();
        System.out.println(squares.get(5).contains(96721l));
        List<String> words = readFile("input.txt");

        long max = 0;
        String bigWord = "";
        for (String word : words) {
            if (word.length() > 8) continue;
            System.out.println(word);
            List<String> perms = new ArrayList<>();
            getWordPerms(perms, new ArrayList<>(Arrays.asList(word.split(""))), new ArrayList<>());
            perms = new ArrayList<>(new HashSet<>(perms));
            perms.remove(word);
            for (String perm : perms) {
                if (words.contains(perm)) {
                    for (long sq : squares.get(word.length())) {
                        long n = sq;
                        Map<Character, Integer> translate = new HashMap<>();
                        boolean badSquare = false;
                        for (int i = word.length() - 1; i >= 0; --i) {
                            char currentChar = word.charAt(i);
                            int digit = (int)(n % 10);
                    
                            if (translate.containsKey(currentChar)) {
                                if (translate.get(currentChar) != digit) {
                                    badSquare = true;
                                    break;
                                }
                            } else {
                                if (translate.containsValue(digit)) {
                                    badSquare = true;
                                    break;
                                }
                                translate.put(currentChar, digit);
                            }
                            n /= 10;
                        }
                        if (badSquare) continue;
                        long transNum = 0;
                        long mult = (long)Math.pow(10, word.length() - 1);
                        for (int i = 0; i < word.length(); ++i) {
                            transNum += mult * translate.get(perm.charAt(i));
                            mult /= 10;
                        }
                        if (squares.get(word.length()).contains(transNum)) {
                            if (transNum > max) {
                                max = transNum;
                                bigWord = word;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(max);
        System.out.println(bigWord);
    }

    static void loadSquares() {
        for (int i = 1; i <= 14; ++i) {
            squares.put(i, new ArrayList<>());
        }
        for (long i = 1; numDigits(i * i) <= 14; ++i) {
            squares.get(numDigits(i * i)).add(i * i);
        }
    }

    static int numDigits(long n) {
        int count = 0;
        while (n > 0) {
            ++count;
            n /= 10;
        }
        return count;
    }

    static void getWordPerms(List<String> perms, List<String> word, List<String> perm) {
        if (word.isEmpty()) {
            perms.add(String.join("", new ArrayList<>(perm)));
        }

        for (int i = 0; i < word.size(); ++i) {
            perm.add(word.remove(i));
            getWordPerms(perms, word, perm);
            word.add(i, perm.remove(perm.size() - 1));
        }
    }

    static List<String> readFile(String fileName) {
        try {
            return Arrays.asList(new String(Files.readAllBytes(Paths.get(fileName))).replace("\"", "").split(","));
        } catch (IOException e) {
            return null;
        }
    }

}