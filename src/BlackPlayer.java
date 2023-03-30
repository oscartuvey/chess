import java.util.Collection;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves); // Swapped round white and black deliberately
    }


    @Override
    public Collection<Piece> getPieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Colour getColour() {
        return Colour.BLACK;
    }

    public Player getPlayer() {
        return this.board.getWhitePlayer();
    }
}
