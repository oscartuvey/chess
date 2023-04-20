public class PieceCaptureMove extends CaptureMove {
    public PieceCaptureMove(Board board, Piece piece, int newPosition, Piece capturedPiece) {
        super(board, piece, newPosition, capturedPiece);
    }

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
        PieceCaptureMove otherPieceCaptureMove = (PieceCaptureMove) obj;


        return super.equals(otherPieceCaptureMove) && getCapturedPiece().equals(otherPieceCaptureMove.getCapturedPiece());

    }


}
