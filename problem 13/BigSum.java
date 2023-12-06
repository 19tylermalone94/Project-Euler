import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class BigSum {

    public static void main(String[] args) {
        BigInteger sum = BigInteger.ZERO;
        File file = new File("big_nums");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String number = scanner.nextLine();
                sum = sum.add(new BigInteger(number));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        String sumString = sum.toString();
        String firstTenDigits = sumString.substring(0, 10);

        System.out.println("First ten digits of the sum: " + firstTenDigits);
    }
}
