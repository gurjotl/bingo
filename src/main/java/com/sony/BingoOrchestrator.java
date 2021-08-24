package main.java.com.sony;

import java.util.Scanner;

public class BingoOrchestrator {

    public static void main(String[] args){
        Bingo bingo = new Bingo();
        bingo.getInput();
        bingo.setup();

        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();
        boolean gameOver = false;

        while(!command.equals("Q") && !gameOver){
            gameOver = bingo.play();
            command = myObj.nextLine();
        }
    }
}
