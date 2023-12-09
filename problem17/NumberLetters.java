import java.util.HashMap;

public class NumberLetters {

    public static void main(String[] args) {
        HashMap<Integer, String> onesMap = new HashMap<Integer, String>();
        onesMap.put(0, "");
        onesMap.put(1, "one");
        onesMap.put(2, "two");
        onesMap.put(3, "three");
        onesMap.put(4, "four");
        onesMap.put(5, "five");
        onesMap.put(6, "six");
        onesMap.put(7, "seven");
        onesMap.put(8, "eight");
        onesMap.put(9, "nine");
       
        HashMap<Integer, String> teensMap = new HashMap<>();
        teensMap.put(0, "");
        teensMap.put(10, "ten");
        teensMap.put(11, "eleven");
        teensMap.put(12, "twelve");
        teensMap.put(13, "thirteen");
        teensMap.put(14, "fourteen");
        teensMap.put(15, "fifteen");
        teensMap.put(16, "sixteen");
        teensMap.put(17, "seventeen");
        teensMap.put(18, "eighteen");
        teensMap.put(19, "nineteen");

        HashMap<Integer, String> tenMap = new HashMap<>();
        tenMap.put(0, "");
        tenMap.put(2, "twenty");
        tenMap.put(3, "thirty");
        tenMap.put(4, "forty");
        tenMap.put(5, "fifty");
        tenMap.put(6, "sixty");
        tenMap.put(7, "seventy");
        tenMap.put(8, "eighty");
        tenMap.put(9, "ninety");

        HashMap<Integer, String> hundredMap = new HashMap<>();
        hundredMap.put(1, "onehundred");
        hundredMap.put(2, "twohundred");
        hundredMap.put(3, "threehundred");
        hundredMap.put(4, "fourhundred");
        hundredMap.put(5, "fivehundred");
        hundredMap.put(6, "sixhundred");
        hundredMap.put(7, "sevenhundred");
        hundredMap.put(8, "eighthundred");
        hundredMap.put(9, "ninehundred");


        long numLetters = 0;
        for (int i = 1; i <= 1000; ++i) {
            if (i < 10) {
                numLetters += onesMap.get(i).length();
            } else if (i < 20) {
                numLetters += teensMap.get(i).length();
            } else if (i < 100) {
                numLetters += tenMap.get(i / 10).length() + onesMap.get(i % 10).length();
            } else if (i < 1000) {
                int ones = i % 10;
                int tens = (i / 10) % 10;
                int hundreds = i / 100;
            
                String word = hundredMap.get(hundreds);
                if (tens == 1) {
                    word += teensMap.get(tens * 10 + ones);
                } else {
                    word += tenMap.get(tens) + onesMap.get(ones);
                }
            
                if ((tens != 0 || ones != 0) && hundreds != 0) { // Append "and" only if there are tens or ones following the hundreds
                    word = word + "and"; 
                }
                numLetters += word.length();
            } else if (i == 1000) {
                numLetters += "onethousand".length();
            }
        }
        System.out.println(numLetters);
    }
}
