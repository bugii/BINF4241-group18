package main;

public class Card {
    private Cardtype cardtype;
    private Color color;

    public Card(Cardtype cardtype, Color color) {
        this.cardtype = cardtype;
        this.color = color;
    }

    public Cardtype getCardtype() {
        return cardtype;
    }


    protected void setCardtype(Cardtype cardtype) {
        this.cardtype = cardtype;
    }

    public Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    public void whenPlayed(Game game, Player player){

    }

    public void onNextPlayer(Game game, Player player){

    }
}
