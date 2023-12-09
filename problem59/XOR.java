import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class XOR {

    public static void main(String[] args) {
        System.out.println(toDecimal(myXOR(toBinary(65, ""), toBinary(42, ""))));
        try {
            Scanner scan = new Scanner(new File("cipher.txt"));
            String[] message = scan.nextLine().split(",");
            
            for (int a = 0; a < 26; ++a) {
                for (int b = 0; b < 26; ++b) {
                    for (int c = 0; c < 26; ++c) {
                        int[] key = {a, b, c};
                        int keyIndex = 0;
                        String decrypt = "";
                        for (int i = 0; i < message.length; ++i) {
                            decrypt = decrypt + (char)(toDecimal(myXOR(toBinary(Integer.parseInt(message[i]), ""), toBinary(key[keyIndex] + 'a', ""))));
                            keyIndex = (keyIndex + 1) % 3;
                        }
                        // System.out.println(decrypt); return;
                        if (decrypt.indexOf("extract") != -1) {
                            System.out.println(decrypt);
                            long sum = 0;
                            for (int k = 0; k < decrypt.length(); ++k) {
                                sum += decrypt.charAt(k);
                            }
                            System.out.println(sum);
                            return;
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static String toBinary(int n, String binary) {
        return (n == 0) ? binary : toBinary(n / 2, (n % 2) + binary);
    }

    static int toDecimal(String binary) {
        int result = 0;
        int decimalPlace = 1;
        for (int i = binary.length() - 1; i >= 0; i--) {
            result += (binary.charAt(i) - '0') * decimalPlace;
            decimalPlace *= 2;
        }
        return result;
    }
    

    static String myXOR(String a, String b) {
        String res = "";
        while (a.length() != 0 || b.length() != 0) {
            char p = (a.length() == 0) ? '0' : a.charAt(a.length() - 1);
            char q = (b.length() == 0) ? '0' : b.charAt(b.length() - 1);
            res = (p == q) ? '0' + res : '1' + res;
            a = (a.length() == 0) ? a : a.substring(0, a.length() - 1);
            b = (b.length() == 0) ? b : b.substring(0, b.length() - 1);
        }
        return res;
    }

}