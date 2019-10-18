public class Game {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Figure Knight = new Knight();
        Figure Bishop = new Bishop();

        board.placeFigurine(Knight, 1, 1);
        board.placeFigurine(Bishop, 3, 8);
    }
}
