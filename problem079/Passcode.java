import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Passcode {

    class Node {
        int val;
        ArrayList<Node> before;
        ArrayList<Node> after;
        Node(int val) {
            this.val = val;
            before = new ArrayList<>();
            after = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Node))
                return false;
            Node other = (Node)o;
            return val == other.val;
        }

        @Override
        public final int hashCode() {
            return String.valueOf(val).hashCode();
        }
    
    }

    ArrayList<Node> passcode = new ArrayList<>();
    ArrayList<Node> unknown = new ArrayList<>();

    void readFile(String fileName) {
        try {
            Scanner scan = new Scanner(new File(fileName));
            while (scan.hasNext()) {
                String[] nums = scan.next().split("");
                Node first = findOrCreateNode(Integer.parseInt(nums[0]));
                Node second = findOrCreateNode(Integer.parseInt(nums[1]));
                Node third = findOrCreateNode(Integer.parseInt(nums[2]));
                
                if (!first.after.contains(second)) first.after.add(second);
                if (!first.after.contains(third)) first.after.add(third);
                if (!second.before.contains(first)) second.before.add(first);
                if (!second.after.contains(third)) second.after.add(third);
                if (!third.before.contains(first)) third.before.add(first);
                if (!third.before.contains(second)) third.before.add(second);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Node findOrCreateNode(int val) {
        Node node = new Node(val);
        if (unknown.contains(node)) {
            return unknown.get(unknown.indexOf(node));
        }
        unknown.add(node);
        return node;
    }

    void buildPasscode() {
        while (!unknown.isEmpty()) {
            for (Node node : unknown) {
                if (node.before.isEmpty()) {
                    passcode.add(unknown.remove(unknown.indexOf(node)));
                    for (Node n : unknown) {
                        if (n.before.contains(node)) {
                            n.before.remove(n.before.indexOf(node));
                        }
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Passcode p = new Passcode();
        p.readFile("keylog.txt");
        p.buildPasscode();
        System.out.print("Passcode: ");
        for (Node node : p.passcode) {
            System.out.print(node.val);
        }
        System.out.println();
    }

}