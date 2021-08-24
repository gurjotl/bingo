package main.java.com.sony;

import java.util.HashSet;
import java.util.Random;

public class Player {

    HashSet<Integer> ticket;
    HashSet<Integer> topLine;
    Integer earlyFive;

    public Player(){
        this.ticket = new HashSet<>();
        this.topLine = new HashSet<>();
        this.earlyFive = 5;
    }

    public void generateTicket(Integer max, Integer columns, Integer rows){
        Random random = new Random();
        Integer total = columns * rows;
        Integer randInt;

        while(total > ticket.size()){
            randInt = random.nextInt(max + 1) + 1;
            if(!ticket.contains(randInt)){
                ticket.add(randInt);
                if(columns > 0){
                    topLine.add(randInt);
                    columns--;
                }
            }
        }
    }

    public void processNumber(Integer number){
        if(ticket.contains(number)){
            ticket.remove(number);
            if(topLine.contains(number)){
                topLine.remove(number);
            }
            if(earlyFive > 0){
                earlyFive--;
            }
        }
    }

    public boolean isTopLineEmpty(){
        return topLine.isEmpty();
    }

    public boolean isEarlyFiveAchieved(){
        return earlyFive == 0;
    }

    public boolean isFullHouseAchieved(){
        return ticket.isEmpty();
    }
}
