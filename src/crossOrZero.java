import java.util.Random;
import java.util.Scanner;

public class crossOrZero {
    public static char[][] map;
    public static final int size = 5;
    public static final int dotsToWin = 4;
    public static final char dotEmpty = '.';
    public static final char dotX = 'X';
    public static final char dot0 = '0';
    public static Scanner sc = new Scanner (System.in);
    public static Random random = new Random();

    public static void main (String[] args) {
        checkSizeAndDotsToWin();
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (myCheckWin(dotX)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            compTurn();
            printMap();
            if (myCheckWin(dot0)) {
                System.out.println("Победил компьютер");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");

}
    public static void initMap() {
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = dotEmpty;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x,y));
        map[y][x] = dotX;
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return false;
        }
        if (map[y][x] == dotEmpty) {
            return true;
        }
        return false;
    }
    public static void compTurn() {
        int x, y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (!isCellValid(x,y));
            System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1) + " ");
        map[y][x] = dot0;
        }
    public static boolean checkWin(char symb) {
        if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if(map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if(map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if(map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if(map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if(map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if(map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        return false;
    }
    public static boolean myCheckWin (char symb) {
        for (int i = 0; i < size; i++) {
            int horizontalCount = 0;
            int verticalCount = 0;
            for (int j = 0; j < size; j++) {
                if (map[i][j] == symb) {
                    horizontalCount++;
                } else {
                    horizontalCount = 0;
                }
                if (horizontalCount >= dotsToWin) return true;
                if (map[j][i] == symb) {
                    verticalCount++;
                } else {
                    verticalCount = 0;
                }
                if (verticalCount >= dotsToWin) return true;
            }
        }
        int mainDiagonalCount = 0;
        int sideDiagonalCount = 0;
        for (int i = 0; i < size; i++) {
             if (map[i][i] == symb) {
                 mainDiagonalCount++;
                } else {
                 mainDiagonalCount = 0;
                }
             if (mainDiagonalCount >= dotsToWin) return true;
             if (map[i][map.length - 1 - i] == symb) {
                 sideDiagonalCount++;
                } else {
                 sideDiagonalCount = 0;
                }
            if (sideDiagonalCount >= dotsToWin) return true;
        }
        return false;
    }
    public static boolean isMapFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == dotEmpty) return false;
            }
        }
        return true;
    }
    public static void checkSizeAndDotsToWin() {
        if (dotsToWin > size) {
            System.out.println("Игра невозможна! Число точек для победы больше размера поля!");
            System.exit(-1);
        }
    }




}


