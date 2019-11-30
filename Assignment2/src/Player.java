import java.util.Scanner;
import java.util.ArrayList;

public class Player {
    public String name;
    private Boolean hasName = false;
    public Color colour;
    public ArrayList<Figure> figures;
    public ArrayList<Figure> eatenFigs = new ArrayList<>();


    public Player(Color colour, ArrayList<Figure> figureSet) {
        this.colour = colour;
        this.figures = figureSet;
        setName();
    }

    private void setName() {
        if(colour == Color.WHITE){
            System.out.println("Who is playing white?");
        }
        else{
            System.out.println("Who is playing black?");
        }
        while(!hasName) {
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();
            if(name.isEmpty()){
                System.out.println("Error: The player requires a name!");
            }
            else {
                hasName = true;
            }
        }
    }


    public Color getColour(){
        return colour;
    }
    
    public Figure getKing(){
        return figures.get(4);
    }

    public String preformMove(String move, int turnNum){
        ArrayList<Figure> movables = new ArrayList<>();
        for(Figure currfig : figures){
            if(currfig.canPerformMove(move,turnNum)){
                movables.add(currfig);
            }
        }
        int size = movables.size();
        if(size == 0){
            return "No figures can preform this move.";
        }
        else if(size > 1){
            return "The given move is ambiguous, please specify.";
        }
        else {
            Figure f = movables.get(0);
            f.perfromMove(move, this, turnNum);
            return "ok";
        }
    }

    public void addToEaten(Figure figure){
        eatenFigs.add(figure);
    }

    public void transformPawn(Pawn oldFigurine, Figure newFigurine){
        int i = figures.indexOf(oldFigurine);
        figures.set(i,newFigurine);
    }
}
