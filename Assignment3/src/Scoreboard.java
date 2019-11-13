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
    public void update(String figureN) {
        int scoreup = 1;
        if (figureN.contains("Q")){
            scoreup = 5;
        }
        if (figureN.contains("W")){
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

    @Override
    public void update() {
        return;
    }
}

