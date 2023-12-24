import java.util.ArrayList;
import java.util.List;

public class Triangles {

    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Point> generatePoints(int width) {
        List<Point> p = new ArrayList<>();
        for (int i = 0; i <= width; ++i) {
            for (int j = i == 0 ? 1 : 0; j <= width; ++j) {
                p.add(new Point(i, j));
            }
        }
        return p;
    }

    static int countRightTriangles(List<Point> points, List<Point> curr, int currIndex, int pointIndex) {
        if (currIndex == 2) {
            return isRightTriangle(curr.get(0), curr.get(1)) ? 1 : 0;
        }
        if (pointIndex >= points.size()) {
            return 0;
        }
        curr.set(currIndex, points.get(pointIndex));
        return countRightTriangles(points, curr, currIndex + 1, pointIndex + 1) + countRightTriangles(points, curr, currIndex, pointIndex + 1);
    }

    static boolean isRightTriangle(Point p1, Point p2) {
        int d1 = p1.x * p1.x + p1.y * p1.y;
        int d2 = p2.x * p2.x + p2.y * p2.y;
        int d3 = (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
        return d1 + d2 == d3 || d1 + d3 == d2 || d2 + d3 == d1;
    }
    

    public static void main(String[] args) {
        List<Point> points = generatePoints(50);
        System.out.println(countRightTriangles(points, new ArrayList<>(List.of(new Point(0, 0), new Point(0, 0))), 0, 0));
    }

}