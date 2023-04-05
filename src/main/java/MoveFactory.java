public class MoveFactory {

    private MoveFactory() {
        throw new RuntimeException("Not instantiable.");
    }

    public static Move createMove(Board board, int position, int newPosition) {

        // Get legal moves for the board class will return the legal moves for both players

        for (Move move : board.getAllLegalMoves()) { // Might want to chena
            if (move.getPosition() == position && move.getNewPosition() == newPosition) {
                return move;
            }
        }
        return Move.NULL_MOVE; // Because this isnt a nested class within the move class unlike the example
        // Change the name of null move to invalid move
    }

}
