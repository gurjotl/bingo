package main.java.com.sony;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bingo {

    private Integer range;
    private Integer players;
    private Integer rows;
    private Integer columns;
    private Integer numbersPerRow;
    private List<Player> playersList;
    private boolean fullHouseWin;
    private boolean earlyFiveWin;
    private boolean topLineWin;

    public Bingo(){
        this.rows = 3;
        this.columns = 10;
        this.numbersPerRow = 5;
        this.playersList = new ArrayList<>();
        this.fullHouseWin = false;
        this.earlyFiveWin = false;
        this.topLineWin = false;
    };

    public void getInput(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("**** Let's Play Housie ***** \n\nNote: - Press 'Q' to quit any time.\n\n>> Enter the number range(1-n): ");
        this.range = myObj.nextInt();

        System.out.println(">> Enter Number of players playing the game: ");
        this.players = myObj.nextInt();

        System.out.println(">> Enter Ticket Size: ");
        String input = myObj.next();
        String[] parse = input.split("X");
        this.rows = Integer.valueOf(parse[0]);
        this.columns = Integer.valueOf(parse[1]);


        System.out.println(">> Enter numbers per row: ");
        this.numbersPerRow = myObj.nextInt();
    }

    public void setup(){
        for(int i = 0; i < this.players; i++){
            Player player = new Player();
            player.generateTicket(this.range, this.columns, this.rows);
            playersList.add(player);
        }

        System.out.println("\n***Ticket Created Successfully ****\n>> Press 'N' to generate next number.\n");
    }

    public boolean play(){
        Integer number = roll();
        process(number);
        return topLineWin && earlyFiveWin && fullHouseWin;
    }

    private Integer roll(){
        Random random = new Random();
        Integer number = random.nextInt(this.range + 1) + 1;
        System.out.println("Next number is: " + number);
        return number;
    }

    private void process(Integer number){
        Player p;
        for(int i = 0; i < playersList.size(); i++){
            p = playersList.get(i);
            p.processNumber(number);
            if(!topLineWin && p.isTopLineEmpty()){
                topLineWin = true;
                System.out.println("\nWe have a winner: Player#" + (i+1) + " has won 'Top Line' winning combination.");
            }
            if(!earlyFiveWin && p.isEarlyFiveAchieved()){
                earlyFiveWin = true;
                System.out.println("\nWe have a winner: Player#" + (i+1) + " has won 'Early Five' winning combination.");
            }
            if(!fullHouseWin && p.isFullHouseAchieved()){
                fullHouseWin = true;
                System.out.println("\nWe have a winner: Player#" + (i+1) + " has won 'Full House' winning combination.");
            }
        }
    }
}
