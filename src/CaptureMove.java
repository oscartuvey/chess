public class CaptureMove extends Move {

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

}
