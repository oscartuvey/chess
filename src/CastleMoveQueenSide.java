public class CastleMoveQueenSide extends CastleMove {
    protected CastleMoveQueenSide(Board board, Piece piece, int position, Rook rook, int rookPosition, int rookDestination) {
        super(board, piece, position, rook, rookPosition, rookDestination);
    }

    @Override
    public String toString() {
        return "0-0-0";
    }
}
