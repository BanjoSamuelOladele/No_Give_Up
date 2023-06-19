package Games;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    private static Scanner userInput = new Scanner(System.in);
    static SecureRandom secureRandom = new SecureRandom();
    static int[][] board = new int[9][9];
//    public static void sud(){
//
//    }
    private static int input(Scanner userInput){return userInput.nextInt();}
public static void main(String[] args) {
    for (int index = 0; index < board.length; index++) {
        for (int inde = 0; inde < board[index].length; inde++) {
            int num = secureRandom.nextInt(1,10);
            for (int i = 0; i <= inde; i++) {
                if (board[index][inde] == num) num = secureRandom.nextInt(1,10);
            }
            board[index][inde] = num;
        }
    }
    System.out.println(Arrays.deepToString(board));
}
}
