import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Cyclic {

    static boolean isPoly(int n) {
        for (Function<Integer, Boolean> func : functions) {
            if (func.apply(n)) return true;
        }
        return false;
    }

    static boolean isPoly(int a, int b, int c, int y) {
        double sol = Math.max((-b + Math.sqrt(b*b - 4*a*c*y)) / (2 * a), (-b - Math.sqrt(b*b - 4*a*c*y)) / (2 * a));
        return sol == (int)sol;
    }
    static boolean isTriangle(int n) {
        return isPoly(1, 1, -2, n);
    }
    static boolean isSquare(int n) {
        return isPoly(1, 0, -1, n);
    }
    static boolean isPentagonal(int n) {
        return isPoly(3, -1, -2, n);
    }
    static boolean isHexagonal(int n) {
        return isPoly(2, -1, -1, n);
    }
    static boolean isHeptagonal(int n) {
        return isPoly(5, -3, -2, n);
    }
    static boolean isOctagonal(int n) {
        return isPoly(3, -2, -1, n);
    }

    static List<Function<Integer, Boolean>> functions = new ArrayList<>() {
        {
            add(n -> isTriangle(n));
            add(n -> isSquare(n));
            add(n -> isPentagonal(n));
            add(n -> isHexagonal(n));
            add(n -> isHeptagonal(n));
            add(n -> isOctagonal(n));
        }
    };

    static void checkCycle(ArrayList<Integer> list, ArrayList<Integer> perm) {
        if (perm.size() == 6) {
            if (isCycle(perm)) {
                System.out.println(perm.toString());
                return;
            }
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            perm.add(list.remove(i));
            checkCycle(list, perm);
            list.add(i, perm.remove(perm.size() - 1));
        }
    }

    static boolean isCycle(List<Integer> list) {
        return list.get(0) % 100 == list.get(1) / 100 && list.get(1) % 100 == list.get(2) / 100
            && list.get(2) % 100 == list.get(3) / 100 && list.get(3) % 100 == list.get(4) / 100
            && list.get(4) % 100 == list.get(5) / 100 && list.get(5) % 100 == list.get(0) / 100;
    }

    static void findCycle() {
        for (int a = 1000; a < 10000 - 5; ++a) {
            if (isPoly(a)) {
                for (int b = (a % 100) * 100; b > 1000 && b < (a % 100) * 100 + 100; ++b) {
                    if (isPoly(b)) {
                        for (int c = (b % 100) * 100; c > 1000 && c < (b % 100) * 100 + 100; ++c) {
                            if (isPoly(c)) {
                                for (int d = (c % 100) * 100; d > 1000 && d < (c % 100) * 100 + 100; ++d) {
                                    if (isPoly(d)) {
                                        for (int e = (d % 100) * 100; e > 1000 && e < (d % 100) * 100 + 100; ++e) {
                                            if (isPoly(e)) {
                                                int f = (e % 100) * 100 + (a / 100);
                                                if (isPoly(f) && f > 1000) {
                                                    if (allPolygonalsRepresented(List.of(a, b, c, d, e, f))) {
                                                        System.out.println(List.of(a, b, c, d, e, f));
                                                        System.out.println(a + b + c + d + e + f);
                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static boolean backtrackAssignments(List<Integer> numbers, List<Function<Integer, Boolean>> checks, Map<Integer, Integer> assignments) {
    if (assignments.size() == numbers.size()) {
        return true;
    }
    for (int i = 0; i < numbers.size(); i++) {
        if (!assignments.containsKey(numbers.get(i))) {
            for (int j = 0; j < checks.size(); j++) {
                if (checks.get(j).apply(numbers.get(i)) && !assignments.containsValue(j)) {
                    assignments.put(numbers.get(i), j);
                    if (backtrackAssignments(numbers, checks, assignments)) {
                        return true;
                    }
                    assignments.remove(numbers.get(i));
                }
            }
        }
    }
    return false;
}

static boolean allPolygonalsRepresented(List<Integer> numbers) {
    Map<Integer, Integer> assignments = new HashMap<>();
    return backtrackAssignments(numbers, functions, assignments);
}

    public static void main(String[] args) {
        findCycle();
    }


}