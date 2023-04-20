public class CastleMoveQueenSide extends CastleMove {
    protected CastleMoveQueenSide(Board board, Piece piece, int position, Rook rook, int rookPosition, int rookDestination) {
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
        CastleMoveQueenSide otherCastleMoveQueenSide = (CastleMoveQueenSide) obj;

        return super.equals(otherCastleMoveQueenSide);
    }

}
