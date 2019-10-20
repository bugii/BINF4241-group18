import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private Board board;
    private int currentRound = 0;
    private boolean stop = false;

    private Game(){
        board = new Board();
        players.add(new Player(Color.WHITE, makeSet(Color.WHITE)));
        players.add(new Player(Color.BLACK, makeSet(Color.BLACK)));
    }


    private ArrayList<Figure> makeSet(Color colour){
        ArrayList<Figure> set = new ArrayList<>();
        int row1, row2;
        if (colour == Color.WHITE){
            row1 = 1; row2 = 2; }
        else{ row1 = 8; row2 = 7; }
        set.add(new Tower(board.getField(1, row1), colour, board));
        set.add(new Knight(board.getField(2, row1), colour, board));
        set.add(new Bishop(board.getField(3, row1), colour, board));
        set.add(new Queen(board.getField(4, row1), colour, board));
        set.add(new King(board.getField(5, row1), colour, board));
        set.add(new Bishop(board.getField(6, row1), colour, board));
        set.add(new Knight(board.getField(7, row1), colour, board));
        set.add(new Tower(board.getField(8, row1), colour, board));
        for(int i = 1; i <= 8; i++){
            set.add(new Pawn(board.getField(i, row2), colour, board));
        }
        return set;
    }

    private void help(){
        System.out.println("Here is a guide to input:");
        System.out.println("*cough cough* I'll add this later...");
    }

    private boolean playTurn(Player player){
        System.out.println(player.name + ", what would you like to do?");
        Scanner scanner = new Scanner(System.in);
        String turn = scanner.nextLine();
        if(turn.isEmpty()){
            System.out.println("Sorry, but we require an input.");
            return false;
        }
        else if(turn.toLowerCase().equals("help")){
            help();
            return false;
        }
        else if(turn.toLowerCase().equals("stop")){
            stop = true;
            System.out.println("The game will be stopped.");
            return true;
        }
        else{
            String outcome = player.preformMove(turn, currentRound);
            if(outcome.equals("ok")){ return true; }
            else {
                System.out.println(outcome);
                return false;
            }
        }
    }

    private void play(){
        int currentPlayer = 0;
        currentRound = 1;
        dimisBoardPrint(board);
        boolean nextTurn = false;
        while(!stop){
            while (!nextTurn){
                nextTurn = playTurn(players.get(currentPlayer));
            }
            nextTurn = false;
            dimisBoardPrint(board);
            currentPlayer = (currentPlayer + 1)%2;
            currentRound +=1;
        }

    }

    private static void dimisBoardPrint(Board board){
        FieldNumber beginning = new FieldNumber(1,8);
        Iterable<FieldNumber> down = beginning.lineDown();

        //first line
        System.out.print("[");
        if(board.getField(beginning).isOccupied()){
            System.out.print(board.getField(beginning).byWhom().getName());
        } else {
            System.out.print("  ");
        }
        System.out.print("]");
        for(FieldNumber i:beginning.lineRight()){
            System.out.print("[");
            if(board.getField(i).isOccupied()){
                System.out.print(board.getField(i).byWhom().getName());
            } else {
                System.out.print("  ");
            }
            System.out.print("]");
        }
        System.out.print("\n");

        //rest
        for(FieldNumber j:down){
            System.out.print("[");
            if(board.getField(j).isOccupied()){
                System.out.print(board.getField(j).byWhom().getName());
            } else {
                System.out.print("  ");
            }
            System.out.print("]");
            for(FieldNumber i:j.lineRight()){
                System.out.print("[");
                if(board.getField(i).isOccupied()){
                    System.out.print(board.getField(i).byWhom().getName());
                } else {
                    System.out.print("  ");
                }
                System.out.print("]");
            }
            System.out.print("\n");

        }
        for(int i = 1; i < 9; ++i){
            System.out.print(" " + (char)(i+64) + "  ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
