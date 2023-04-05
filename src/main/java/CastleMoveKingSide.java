public class CastleMoveKingSide extends CastleMove{
    protected CastleMoveKingSide(Board board, Piece piece, int position, Rook rook, int rookPosition, int rookDestination) {
        super(board, piece, position, rook, rookPosition, rookDestination);
    }

    @Override
    public String toString() {
        return "0-0";
    }
}
