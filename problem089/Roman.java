import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Roman {

    static List<List<String>> readFile(String fileName) {
        try {
            return Files.lines(Paths.get(fileName)).map(line -> Arrays.asList(line.split(""))).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static int romanToInt(List<String> s) {
        int num = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (i < s.size() - 1) {
                if (s.get(i).equals("I") && (s.get(i + 1).equals("V") || s.get(i + 1).equals("X"))) {
                    num -= 1;
                    continue;
                } else if (s.get(i).equals("X") && (s.get(i + 1).equals("L") || s.get(i + 1).equals("C"))) {
                    num -= 10;
                    continue;
                }else if (s.get(i).equals("C") && (s.get(i + 1).equals("D") || s.get(i + 1).equals("M"))) {
                    num -= 100;
                    continue;
                }
            } 
            switch (s.get(i)) {
                case "I": num += 1; break;
                case "V": num += 5; break;
                case "X": num += 10; break;
                case "L": num += 50; break;
                case "C": num += 100; break;
                case "D": num += 500; break;
                case "M": num += 1000; break;
            }
        }
        return num;
    }

    static List<String> intToRoman(int n) {
        int decPlace = 1;
        List<String> s = new ArrayList<>();
        while (n > 0) {
            int lastDigit = n % 10;
            String a = "";
            switch (lastDigit) {
                case 1:
                case 2:
                case 3: 
                    a = (decPlace == 1 ? "I" : decPlace == 10 ? "X" : decPlace == 100 ? "C" : "M").repeat(lastDigit); 
                    break;
                case 4: 
                    a = (decPlace == 1 ? "VI" : decPlace == 10 ? "LX" : decPlace == 100 ? "DC" : "MMMM"); 
                    break;
                case 5: 
                case 6: 
                case 7:
                case 8: 
                    a = decPlace == 1 ? "V" : decPlace == 10 ? "L" : decPlace == 100 ? "D" : "D"; 
                    a = ((decPlace == 1 ? "I" : decPlace == 10 ? "X" : decPlace == 100 ? "C" : "C").repeat(lastDigit - 5)).concat(a); 
                    break;
                case 9: 
                    a = (decPlace == 1 ? "XI" : decPlace == 10 ? "CX" : decPlace == 100 ? "MC" : "MMMMMMMMM"); 
                    break;
                default: break;
            }
            if (!a.isEmpty()) s.addAll(Arrays.asList(a.split("")));
            n /= 10;
            decPlace *= 10;
        }
        Collections.reverse(s);
        return s;
    }

    public static void main(String[] args) {

        List<List<String>> content = readFile("input.txt");
        int numCharsSaved = 0;
        for (List<String> roman : content) {
            numCharsSaved += roman.size() - intToRoman(romanToInt(roman)).size();
        }
        System.out.println(numCharsSaved);
    }

}