public class CastleMoveKingSide extends CastleMove{
    protected CastleMoveKingSide(Board board, Piece piece, int position, Rook rook, int rookPosition, int rookDestination) {
        super(board, piece, position, rook, rookPosition, rookDestination);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CastleMoveKingSide otherCastleMoveKingSide = (CastleMoveKingSide) obj;

        // Check this return statement is right
        return super.equals(otherCastleMoveKingSide);
    }

}
