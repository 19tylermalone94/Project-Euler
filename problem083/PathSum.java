import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// A* algorithm
public class PathSum {

    static class Node implements Comparable<Node> {
        int i, j;
        int fScore; // gScore + hScore
        int gScore; // tentative score that represents distance from this node to start node
        List<Node> neighbors;

        Node(int i, int j) {
            this.i = i;
            this.j = j;
            fScore = Integer.MAX_VALUE;
            gScore = Integer.MAX_VALUE;
            neighbors = new ArrayList<>();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {    
                return true;    
            }    
            if (other instanceof Node) {    
                Node o = (Node)other;
                return i == o.i && j == o.j; 
            }    
            return false;    
        }

        @Override
        public int compareTo(Node other) {
            return fScore - other.fScore;
        }
    }

    static int[][] matrix = readFile("input.txt");
    static Node[][] nodes = buildNodeGraph();

    static int[][] readFile(String fileName) {
        try {
            return Files.lines(Paths.get(fileName)).map(line -> Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
        } catch (IOException e) {
            return null;
        }
    }

    static Node[][] buildNodeGraph() {
        Node[][] graph = new Node[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                graph[i][j] = new Node(i, j);
            }
        }

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (i > 0) {
                graph[i][j].neighbors.add(graph[i - 1][j]);
            }
             if (i < matrix.length - 1) {
                graph[i][j].neighbors.add(graph[i + 1][j]);
             }
             if (j > 0) {
                graph[i][j].neighbors.add(graph[i][j - 1]);
             }
             if (j < matrix[0].length - 1) {
                graph[i][j].neighbors.add(graph[i][j + 1]);
             }
            }
        }
        return graph;
    }

    static List<Node> AStar(Node start, Node goal) {
        Queue<Node> openList = new PriorityQueue<>();
        Map<Node, Node> cameFrom = new HashMap<>();

        start.gScore = 0;

        start.fScore = h(start, goal);
        // System.out.println(matrix[start.i][start.j]);
        openList.add(start);

        while (!openList.isEmpty()) {
            Node curr = openList.poll();
            // System.out.println(matrix[curr.i][curr.j]);
            if (curr.equals(goal)) {
                return buildPathList(cameFrom, curr);
            }
            for (Node neighbor : curr.neighbors) {
                // System.out.println(matrix[neighbor.i][neighbor.j]);
                int tentGScore = curr.gScore + d(curr);
                if (tentGScore < neighbor.gScore) {

                    if (cameFrom.containsKey(neighbor)) {
                        cameFrom.replace(neighbor, curr);
                    } else {
                        cameFrom.put(neighbor, curr);
                    }
                    
                    neighbor.gScore = tentGScore + h(neighbor, goal);
                    neighbor.fScore = tentGScore + h(neighbor, goal);
                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }
        return null;
    }
    
    static List<Node> buildPathList(Map<Node, Node> cameFrom, Node curr) {
        List<Node> pathList = new ArrayList<>(List.of(curr));
        while (cameFrom.containsKey(curr)) {
            curr = cameFrom.get(curr);
            pathList.add(curr);
        }
        return pathList;
    }

    static int h(Node node, Node goal) {
        // Manhattan distance from node to goal
        return Math.abs(node.i - goal.i) + Math.abs(node.j - goal.j);
    }

    static int d(Node node) {
        // weight of the edge from node to any of its neighbors
        return matrix[node.i][node.j];
    }

    public static void main(String[] args) {
        List<Node> path = AStar(nodes[0][0], nodes[nodes.length - 1][nodes.length - 1]);
        // path.forEach(node -> System.out.print(matrix[node.i][node.j] + " "));
        int sum = 0;
        for (Node n : path) {
            sum += matrix[n.i][n.j];
        }
        System.out.println(sum);
    }

}