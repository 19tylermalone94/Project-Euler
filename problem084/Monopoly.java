import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Monopoly {

    List<Integer> posCounts = new ArrayList<Integer>(Collections.nCopies(40, 0));
    int dice = 4;
    Random rand = new Random();
    int position = 0;
    final int boardSize = 40;
    
    void commChest() {
        int draw = rand.nextInt(16);

        //adv to GO
        if (draw == 0) {
            position = 0;
        }
        // GTJ
        if (draw == 1) {
            position = 10;
        }
        // other cards don't matter
    }

    void chance() {
        int draw = rand.nextInt(16);
        switch (draw) {
            case 0: position = 0; break; // adv GO
            case 1: position = 10; break; // GTJ
            case 2: position = 11; break; // C1
            case 3: position = 24; break; // E3
            case 4: position = 39; break; // H2
            case 5: position = 5; break; // R1
            case 6:
            case 7: nextRR(); break;
            case 8: nextUT(); break;
            case 9: position = (position - 3 + boardSize) % boardSize; break;
            default: break;
        }
        if (position == 33) commChest(); // going back three spaces from 36 has the chance to land on CC at 33
        // other cards don't matter
    }

    void nextRR() {
        position = position < 5 ? 5
        : position < 15 ? 15
        : position < 25 ? 25
        : position < 35 ? 35 : 5;
    }

    void nextUT() {
        position = position < 12 ? 12
        : position < 28 ? 28 : 12;
    }

    void rollAndMove() {
        boolean doubles = false;
        int doubleCount = 0;
        do {
            int d1 = rand.nextInt(dice) + 1;
            int d2 = rand.nextInt(dice) + 1;
            doubles = d1 == d2;
            doubleCount += doubles ? 1 : 0;
            if (doubleCount == 3) {
                position = 10;
                break;
            }
            position = (position + d1 + d2) % boardSize;
            switch (position) {
                case 30: position = 10; doubles = false; break;
                case 2:
                case 17:
                case 33: commChest(); break;
                case 7:
                case 22:
                case 36: chance(); break;
            }
            posCounts.set(position, posCounts.get(position) + 1);
        } while (doubles);
    }

public static List<Integer> findThreeLargestIndexes(List<Integer> numbers) {
    return IntStream.range(0, numbers.size()).boxed().sorted((i, j) -> numbers.get(j) - numbers.get(i)).limit(3).toList();
}

    public static void main(String[] args) {
        Monopoly game = new Monopoly();
        for (int i = 0; i < 1000000; ++i) {
            game.rollAndMove();
        }
        System.out.println(findThreeLargestIndexes(game.posCounts).toString());
    }

}