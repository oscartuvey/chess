public class CaptureMove extends Move {

    final Piece capturedPiece;

    public CaptureMove(Board board, Piece piece, int newPosition, Piece capturedPiece) {
        super(board, piece, newPosition);
        this.capturedPiece = capturedPiece;
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
        CaptureMove otherCaptureMove = (CaptureMove) obj;

        return super.equals(otherCaptureMove) && getCapturedPiece().equals(otherCaptureMove.getCapturedPiece());
    }

}
