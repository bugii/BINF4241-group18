package Assignment3.src;

import java.util.ArrayList;

public class Scoreboard implements Observer {
    private int white;
    private int black;
    public Scoreboard(){
        white = 0;
        black = 0;
    }

    @Override
    public void update(Figure figure) {
        int scoreup = 1;
        if (figure.getName().contains("Q")){
            scoreup = 5;
        }
        if (figure.getColor() == Color.WHITE){
            black += scoreup;
        }
        else {
            white += scoreup;
        }
    }

    public int getScore(Color colour){ //get one score at a time, this way it's clear whose is which
        if (colour == Color.WHITE){
            return white;
        }
        else {
            return black;
        }
    }
}
