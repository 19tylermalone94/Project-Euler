import java.util.ArrayList;

public class Fractions {

    static class Node {
            Frac val;
            Node left;
            Node right;

            Node(Frac val) {
                this.val = val;
            }
        }

    static class Tree {

        Node root;

        Tree(Node root) {
            this.root = root;
        }

        void insert(Node node, Node curr) {
            if (root == null)  {
                root = node;
                return;
            }
            if (node.val.val() < curr.val.val()) {
                if (curr.left == null) {
                    curr.left = node;
                     return;
                }
                insert(node, curr.left);
            }
            if (node.val.val() > curr.val.val()) {
                if (curr.right == null) {
                    curr.right = node;
                    return;
                }
                insert(node, curr.right);
            }
        }

        void search(Node curr) {
            if (curr == null) return;

            search(curr.left);
            System.out.println(curr.val.toString());
            search(curr.right);
        }
    }

    static class Frac implements Comparable<Frac> {
        int n;
        int d;

        Frac(int numerator, int denominator) {
            this.n = numerator;
            this.d = denominator;
            simplify();
        }

        Frac(int n, Frac d) {
            this.n = n * d.d;
            this.d = d.n;
        }

        Frac add(Frac other) {
            Frac sum = new Frac(n * other.d + d * other.n, d * other.d);
            return simplify(sum);
        }

        void simplify() {
            int gcd = gcd(n, d);
            n = n / gcd;
            d = d / gcd;;
        }

        Frac simplify(Frac frac) {
            int gcd = gcd(frac.n, frac.d);
            return new Frac(frac.n / gcd, frac.d / gcd);
        }
    
        private int gcd(int a, int b) {
            return (b == 0) ? a : gcd(b, a % b);
        }

        @Override
        public String toString() {
            return n + "/" + d;
        }

        public int compareTo(Frac other) {
            return (val() < other.val()) ? -1
            : (val() == other.val()) ? 0 : 1;
        }

        double val() {
            return (double)n / d;
        }
    }

    public static void main(String[] args) {
        // Tree tree = new Tree(null);
        ArrayList<Frac> fracs = new ArrayList<>();
        for (int n = 1; n < 8; ++n) {
            for (int d = 8; d > n && d >= 2; --d) {
                // tree.insert(new Node(new Frac(n, d)), tree.root);
                if (!fracs.contains(new Frac(n, d))) {
                    fracs.add(new Frac(n, d));
                }
            }
        }
        for (Frac frac : fracs) {
            System.out.println(frac.toString());
        }
        // tree.search(tree.root);
    }

}