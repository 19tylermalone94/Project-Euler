public class MakeTwoPounds {

    static int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
    public static void main(String[] args) {
        System.out.println(combos(200, coins.length - 1));
    }

    static int combos(int amount, int c) {
        if (amount == 0 || c == 0) {
            return 1;
        } else if (coins[c] > amount) {
            return combos(amount, c - 1);
        } else {
            return combos(amount - coins[c], c) + combos(amount, c - 1);
        }
    }

}