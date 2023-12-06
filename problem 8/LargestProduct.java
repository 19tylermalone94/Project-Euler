import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LargestProduct {

    public static String bigNumber = "";

    public static void main(String[] args) {
        try {
            FileInputStream fStream = new FileInputStream("num.txt");
            Scanner fileReader = new Scanner(fStream);

            int[] digitArray = new int[1000];
            int i = 0;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                for (int j = 0; j < line.length(); ++j) {
                    digitArray[j +  (i * line.length())] = Integer.parseInt(line.charAt(j) + "");
                }
                ++i;
            }
            fileReader.close();

            long maxProduct = 0;
            long product = 1;
            int begin = 0;
            for (int end = 0; end < digitArray.length; ++end) {
                if (digitArray[end] == 0) {
                    product = 1;
                    begin = end + 1;
                    continue;
                }
                product *= digitArray[end];
                if (end - begin > 12) {
                    product /= digitArray[begin];
                    ++begin;
                }
                maxProduct = Math.max(maxProduct, product);
            }
            System.out.println(maxProduct);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found. Bye.");
        }
    }


    
}
