public class CaptureMove extends Move {


    // TODO complete this class - the captured piece should be removed from activePieces, a few other things as well
    final Piece capturedPiece;

    public CaptureMove(Board board, Piece piece, int newPosition, Piece capturedPiece) {
        super(board, piece, newPosition);
        this.capturedPiece = capturedPiece;
    }

    // THis method as it is now should actually be in PieceMove
    @Override
    public Board execute() {
        return null;
    }

    @Override
    public boolean isAttack() {
        return true;
    }

    @Override
    public Piece getCapturedPiece() {
        return this.capturedPiece;
    }

    @Override
    public int hashCode() {
        return this.capturedPiece.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Move otherCaptureMove = (CaptureMove) obj;

        // Check this return statement is right
        return super.equals(otherCaptureMove) && getCapturedPiece().equals(otherCaptureMove.getCapturedPiece());
    }

}
