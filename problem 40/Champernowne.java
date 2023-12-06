public class Champernowne {

    public static void main(String[] args) {
        String seq = "123456789";
        for (long delim = 1; seq.length() < 1000000; ++delim) {
            for (int i = 0; i <= 9; ++i) {
                seq += delim + "" + i;
            }
        }
        int sol = (seq.charAt(0) - '0') * (seq.charAt(9) - '0') * (seq.charAt(99) - '0')
         * (seq.charAt(999) - '0') * (seq.charAt(9999) - '0') * (seq.charAt(99999) - '0')
         * (seq.charAt(999999) - '0');
         System.out.println(sol);
    }

}