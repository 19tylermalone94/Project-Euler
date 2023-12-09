import java.util.ArrayList;
import java.util.List;

public class MillionthPerm {

    static int permCount = 1;
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        printMillionthPerm(list, new ArrayList<Integer>());
    }

    public static void printMillionthPerm(ArrayList<Integer> list, ArrayList<Integer> perm) {
        if (perm.size() == 10) {
            if (permCount == 1000000)
                System.out.println(perm.toString());
            ++permCount;
            return;
        }

        for (int i = 0; i < list.size(); ++i) {
            perm.add(list.remove(i));
            printMillionthPerm(list, perm);
            list.add(i, perm.remove(perm.size() - 1));
        }
    }

}