public class PawnCaptureMove extends CaptureMove {

    public PawnCaptureMove(Board board, Piece piece, int newPosition, Piece capturedPiece) {
        super(board, piece, newPosition, capturedPiece);
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
        PawnCaptureMove otherPawnCaptureMove = (PawnCaptureMove) obj; // Why is it called other?

        // Check this return statement is right. Does it not check for the captured piece?
        return super.equals(otherPawnCaptureMove) && getCapturedPiece().equals(otherPawnCaptureMove.getCapturedPiece());
    }

    // TODO remove all unnecessary toString() methods in all classes

}
