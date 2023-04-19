public class PawnCaptureMoveEnPassant extends PawnCaptureMove {

    public PawnCaptureMoveEnPassant(Board board, Piece piece, int newPosition, Piece capturedPiece) {
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
        PawnCaptureMoveEnPassant otherPawnCaptureMoveEnPassant = (PawnCaptureMoveEnPassant) obj;

        // Check this return statement is right
        return super.equals(otherPawnCaptureMoveEnPassant) &&
                getCapturedPiece().equals(otherPawnCaptureMoveEnPassant.getCapturedPiece());
    }

    @Override
    public Board execute() {
        Board.Builder builder = new Board.Builder();

        for (Piece p : this.board.currentPlayer().getPieces()) { // Check these are the active pieces. Check implementation
            // in other classes
            if (!this.piece.equals(p)) {
                builder.setPiece(p);
            }
        }

        // Check these have been doen right in other instances too
        for (Piece p : board.currentPlayer().getPlayer().getPieces()) {
            if (!p.equals(this.getCapturedPiece())) {
                builder.setPiece(p);
            }
        }
        builder.setPiece(this.piece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getPlayer().getColour()); // Check this is right

        return builder.build();
    }

}
