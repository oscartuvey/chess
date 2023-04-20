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


        return super.equals(otherPawnCaptureMoveEnPassant) &&
                getCapturedPiece().equals(otherPawnCaptureMoveEnPassant.getCapturedPiece());
    }

    @Override
    public Board execute() {
        Board.Builder builder = new Board.Builder();

        for (Piece p : this.board.currentPlayer().getPieces()) {

            if (!this.piece.equals(p)) {
                builder.setPiece(p);
            }
        }

        for (Piece p : board.currentPlayer().getPlayer().getPieces()) {
            if (!p.equals(this.getCapturedPiece())) {
                builder.setPiece(p);
            }
        }
        builder.setPiece(this.piece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getPlayer().getColour());

        return builder.build();
    }

}
