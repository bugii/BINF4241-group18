package main;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards;
    private boolean saidUno = false;

    public Player() {

    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    protected void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public boolean isSaidUno() {
        return saidUno;
    }

    protected void setSaidUno(boolean saidUno) {
        this.saidUno = saidUno;
    }
}
