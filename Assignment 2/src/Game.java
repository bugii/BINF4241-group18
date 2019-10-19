import java.util.Iterator;

public class Game {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Figure Knight = new Knight();
        Figure Bishop = new Bishop();

        board.placeFigurine(Knight, 1, 1);
        board.placeFigurine(Bishop, 3, 8);

        dimitrisTests();
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

    private static void dimitrisTests(){
        System.out.println("Hier starte Dimitris tests\n\n\n");

        Board board = new Board();

        //make figurines
        Figure tower = new Tower(board.getField(2,3),Color.BLACK,board);
        Figure queen = new Queen(board.getField(5,2),Color.WHITE,board);
        Figure bishop = new Bishop(board.getField(5,4),Color.WHITE,board);
        Figure knight = new Knight(board.getField(3,4),Color.WHITE,board);
        Figure king = new King(board.getField(2,4),Color.BLACK,board);
        Figure pawn = new Pawn(board.getField(3,2),Color.WHITE,board);

        dimisBoardPrint(board);
        System.out.println(pawn.canPerformMove("" + "PCxB3"));
        pawn.perfromMove("PCxB3",new Player(),3);
        dimisBoardPrint(board);
    }
}
