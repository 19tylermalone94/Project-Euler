import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Expressions {

    static List<List<Integer>> digitCombos = new ArrayList<>();
    static List<List<Integer>> comboPerms = new ArrayList<>();
    static char[] ops = {'*', '/', '+', '-'};
    static List<List<Character>> operatorCombos = new ArrayList<>();
    static Map<List<Integer>, List<Integer>> solMap = new HashMap<>();

    static void getDigitCombos(List<Integer> curr, int index, int element) {
        if (index == 4) {
            digitCombos.add(new ArrayList<>(curr));
            return;
        }
        if (element == 10) {            
            return;
        }
        curr.set(index, element);
        getDigitCombos(curr, index + 1, element + 1);
        getDigitCombos(curr, index, element + 1);
    }

    static void getDigitPerms(List<Integer> digits, List<Integer> curr) {
        if (curr.size() == 4) {
            comboPerms.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < digits.size(); ++i) {
            curr.add(digits.remove(i));
            getDigitPerms(digits, curr);
            digits.add(i, curr.remove(curr.size() - 1));
        }
    }

    static void getOperatorCombos() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    operatorCombos.add(new ArrayList<>(List.of(ops[i], ops[j], ops[k])));
                }
            }
        }
    }

    static double doMath(double x, double y, char op) {
        switch (op) {
            case '*': return x * y;
            case '/': return x / y;
            case '+': return x + y;
            case '-': return x - y;
        }
        return 0;
    }

    static List<Integer> getSolutions(List<Integer> perm) {
        List<Integer> sol = new ArrayList<>();
        for (List<Character> operators : operatorCombos) {
            double result1 = doMath(doMath(perm.get(0), perm.get(1), operators.get(0)), doMath(perm.get(2), perm.get(3), operators.get(2)), operators.get(1));
            double result2 = doMath(doMath(perm.get(0), doMath(perm.get(1), perm.get(2), operators.get(1)), operators.get(0)), perm.get(3), operators.get(2));
            double result3 = doMath(perm.get(0), doMath(doMath(perm.get(1), perm.get(2), operators.get(1)), perm.get(3), operators.get(2)), operators.get(0));
            double result4 = doMath(perm.get(0), doMath(perm.get(1), doMath(perm.get(2), perm.get(3), operators.get(2)), operators.get(1)), operators.get(0));
            double result5 = doMath(doMath(doMath(perm.get(0), perm.get(1), operators.get(0)), perm.get(2), operators.get(1)), perm.get(3), operators.get(2));
    
            checkAndAddResult(result1, sol);
            checkAndAddResult(result2, sol);
            checkAndAddResult(result3, sol);
            checkAndAddResult(result4, sol);
            checkAndAddResult(result5, sol);
        }
        return sol;
    }
    
    static void checkAndAddResult(double result, List<Integer> sol) {
        if (result > 0 && Math.abs(result - Math.round(result)) < 1e-9) {
            sol.add((int)Math.round(result));
        }
    }
    
    public static void main(String[] args) {
        getDigitCombos(new ArrayList<>(List.of(0, 0, 0, 0)), 0, 0);
        getOperatorCombos();

        for (List<Integer> combo : digitCombos) {
            getDigitPerms(new ArrayList<>(combo), new ArrayList<>());
            List<Integer> solutions = new ArrayList<>();
            for (List<Integer> perm : comboPerms) {
                solutions.addAll(getSolutions(perm));
            }
            solMap.put(combo, solutions);
            comboPerms.clear();
        }

        List<Integer> bestCombo = null;
        int max = 0;
        for (List<Integer> combo : digitCombos) {
            for (int i = 1; true; ++i) {
                if (!solMap.get(combo).contains(i)) {
                    if (i > max) {
                        max = i;
                        bestCombo = combo;
                    }
                    break;
                }
            }
        }
        
        System.out.println(bestCombo.toString());
    }

}