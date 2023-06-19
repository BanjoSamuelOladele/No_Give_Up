package Games;

import contacts.IncorrectPasswordException;

import java.util.Arrays;
import java.util.Scanner;

public class Sudoku1 {
    private static Scanner userInput = new Scanner(System.in);
    private static int[][] board = new int[9][9];

    public static void main(String[] args) {
        validate();
        System.out.println(Arrays.deepToString(board));
    }
    private static void array(){
        for (int index = 0; index < board.length; index++) {
            for (int inde = 0; inde < board[index].length; inde++) {
                displayMessage(index+1, inde+1);
                board[index][inde] = input(userInput);
            }
        }
        System.out.println(Arrays.deepToString(board));
    }
    private static void displayMessage(int first, int second){
        System.out.printf("""
                Input at row %d column  %d:\s
                """, first, second);
    }
    private static int input(Scanner userInput){
        return userInput.nextInt();
    }
    private static void validate(){
        array();
        for (int index = 0; index < board.length; index++) {
            for (int inde = 0; inde < board[index].length; inde++) {
                for (int ind = 1; ind < board[index].length; ind++) {
                    if (board[index][inde] == board[index][ind]) System.out.println("You lost!");
                }
            }
        }
        System.out.println("go on soun");
    }
}
