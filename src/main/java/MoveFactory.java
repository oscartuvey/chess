
public class MoveFactory {

    private MoveFactory() {
        throw new RuntimeException("Not instantiable.");
    }

    public static Move createMove(Board board, int position, int newPosition) {

        for (Move move : board.getAllLegalMoves()) {
            if (move.getPosition() == position && move.getNewPosition() == newPosition) {
                return move;
            }
        }

        return Move.NULL_MOVE;
    }

}
