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
        System.out.println("K = King, Q = Queen, B = Bishop, N = Knight, P = Pawn");
        System.out.println("Standart movement: Pe4, Qc7 etc.");
        System.out.println("Capturing Pieces: Pxe4, Qxc7 etc.");
        System.out.println("Dismabiguating moves: Bad5 or B2d5, Qh4xe1 etc.");
        System.out.println("Promoting Pawns: Pf8Q, Pa1T etc.");
        System.out.println("Enpasse: Pxe3e.p.");
        System.out.println("Capturing Pieces: Pxe4, Qxc7 etc.");
        System.out.println("Castiling: 0-0 (kignside) 0-0-0 (queenside)");
    }

    private boolean playTurn(Player player){
        System.out.println(player.name + ", what would you like to do?");
        Scanner scanner = new Scanner(System.in);
        String turn = scanner.nextLine();
        if(turn.isEmpty()){
            System.out.println("Sorry, but we require an input.");
            System.out.println("If you require help, pleas type in 'help'");
            return false;
        }
        else if(turn.toLowerCase().equals("help")){
            help();
            return false;
        }
        else if(turn.equals("(=)")){
            stop = true;
            System.out.println("You have called a draw");
            return true;
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
        board.printBoard();
        boolean nextTurn = false;
        while(!stop){
            while (!nextTurn){
                nextTurn = playTurn(players.get(currentPlayer));
            }
            nextTurn = false;
            board.printBoard();
            currentPlayer = (currentPlayer + 1)%2;
            currentRound +=1;
        }

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
