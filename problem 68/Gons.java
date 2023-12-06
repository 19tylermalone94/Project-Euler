import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Gons {

    static ArrayList<Long> solutions = new ArrayList<>();

    public static void main(String[] args) {
        getAllPerms(new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), new ArrayList<>());
        Collections.sort(solutions);
        System.out.println(solutions.get(solutions.size() - 1));
    }

    static void getAllPerms(ArrayList<Integer> list, ArrayList<Integer> perm) {
        if (list.size() == 0) {
            hasSolution(perm);
            return;
        }

        for (int i = 0; i < list.size(); ++i) {
            perm.add(list.remove(i));
            getAllPerms(list, perm);
            list.add(i, perm.remove(perm.size() - 1));
        }
    }

    static void hasSolution(List<Integer> perm) {
        if (perm.get(0) + perm.get(1) + perm.get(2) == perm.get(3) + perm.get(2) + perm.get(4)
            && perm.get(3) + perm.get(2) + perm.get(4) == perm.get(5) + perm.get(4) + perm.get(6)
            && perm.get(5) + perm.get(4) + perm.get(6) == perm.get(7) + perm.get(6) + perm.get(8)
            && perm.get(7) + perm.get(6) + perm.get(8) == perm.get(9) + perm.get(8) + perm.get(1)) {
                
                // Check if the solution starts with the smallest of the outer numbers
                int minOuterNumber = Math.min(Math.min(Math.min(Math.min(perm.get(0), perm.get(3)), perm.get(5)), perm.get(7)), perm.get(9));
                if (perm.get(0) != minOuterNumber) return;
    
                String num = "" + perm.get(0) + perm.get(1) + perm.get(2) + 
                                    perm.get(3) + perm.get(2) + perm.get(4) + 
                                    perm.get(5) + perm.get(4) + perm.get(6) +
                                    perm.get(7) + perm.get(6) + perm.get(8) +
                                    perm.get(9) + perm.get(8) + perm.get(1);
                long sol = Long.parseLong(num);
                if (sol < 10000000000000000l) solutions.add(sol);
        }
    }

}

