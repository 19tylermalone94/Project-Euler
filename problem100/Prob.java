
/*
 * 
 * quadratic diophantine: Ax^2 + Bxy + Cy^2 + Dx + Ey + F = 0
 * 
 * This problem: (x / y) * ((x - 1) / (y - 1)) = 1/2
 *  == (x^2 - x) / (y^2 - y) = 1/2
 *  == (2x^2 - 2x) / (y^2 - y) = 1
 *  == 2x^2 - 2x = y^2 - y
 *  == 2x^2 - y^2 - 2x - y = 0
 * 
 *  A = 2, B = 0, C = -1, D = -2, E = 1, F = 0
 * 
 * using a diophantine calculator, https://www.alpertron.com.ar/QUAD.HTM, 
 * you get the recursive solutions:
 *  x{n + 1} = 3x{n} + 2y{n} - 2
 *  y{n + 1} = 4x{n} + 3y{n} - 3
 * 
 *  In the context of this problem, y{n} is the total disks and x{n} is the blue ones
 */

public class Prob {

    public static void main(String[] args) {
        long LIMIT = 1000000000000l;
        long blueDisk = 3;
        long totalDisk = 4;
        while (totalDisk <= LIMIT) {
            long newBlue = 3*blueDisk + 2*totalDisk - 2;
            long newTotal = 4*blueDisk + 3*totalDisk - 3;
            blueDisk = newBlue;
            totalDisk = newTotal;
        }
        System.out.println("Blue: " + blueDisk + ", Total: " + totalDisk);
    }

}
