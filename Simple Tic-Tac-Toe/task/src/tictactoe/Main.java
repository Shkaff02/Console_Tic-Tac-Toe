package tictactoe;
import java.util.*;

class Game {
    public static char gamer = 'X';
    int size = 3;
    char[][] field = new char[size][size];


    public Game() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = ' ';
            }
        }
    }

    public void printField() {
        System.out.println("---------");
        for (int i = 0; i < field.length; i++) {
            System.out.print("|" + " ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean checkCoordinates(String row, String col) {
        int size = 3;
        char freeCell = ' ';
        if (!isNumber(row) || !isNumber(col)) {
            System.out.println("You should enter numbers!");
            return false;
        }
        int Row = Integer.parseInt(row);
        int Col = Integer.parseInt(col);
        if(Row < 0 || Col < 0 || Row > size || Col > size) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        if (field[Row - 1][Col - 1] != freeCell) {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        } else {
            field[Row - 1][Col - 1] = gamer;
            return true;
        }
    }

    public boolean isNumber(String input) {
        if (!input.matches("^[0-9]+$")) {
            return false;
        }
        return true;
    }

    public boolean check() {
        if (isPossible()) {
            if (isWin('X') == 'X') {
                System.out.println("X wins");
                return true;
            } else if (isWin('O') == 'O') {
                System.out.println("O wins");
                return true;
            } else if (!hasFreeCells()) {
                System.out.println("Draw");
                return true;
            }
        } else {
            System.out.println("Impossible");
        }
        return false;
    }

    public char isWin(char c) {
        int count = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == c) count ++;
                if (count == 3) return c;
            }
            count = 0;
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[j][i] == c) count ++;
                if (count == 3) return c;
            }
            count = 0;
        }


        for (int i = 0, j = 0; i < field.length; i++) {
            j = i;
            if (field[i][j] == c) count ++;
            if (count == 3) return c;
        }
        count = 0;

        for (int i = 0; i < field.length; i++) {
            int j = (field.length - 1 - i);
            if (field[i][j] == c) count ++;
            if (count == 3) return c;
        }
        return 'n';
    }

    public boolean hasFreeCells() {
        char freeCell = ' ';
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == freeCell) return true;
            }
        }
        return false;
    }

    public boolean isPossible() {
        int countX = 0;
        int countO = 0;
        char X = 'X';
        char O = 'O';
        if (isWin('X') == 'X' && isWin('O') == 'O') {
            return false;
        }
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == X) countX++;
                else if (field[i][j] == O) countO++;
            }
        }
        if (Math.abs(countX - countO) > 1) return false;
        return true;
    }
}



public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.printField();
        Scanner scanner = new Scanner(System.in);
        String row;
        String col;


        while(game.hasFreeCells()) {
            do{
                System.out.println("Enter the coordinates:");
                row = scanner.next();
                col = scanner.next();
            }while(!game.checkCoordinates(row, col));
            game.printField();
            game.gamer = swap(game.gamer);
            if (game.check()) {
                return;
            }
        }

    }
    public static char swap(char xo) {
        if (xo == 'X') {
            return 'O';
        } else return 'X';
    }
}
