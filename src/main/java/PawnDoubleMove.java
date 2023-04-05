public class PawnDoubleMove extends Move {

    public PawnDoubleMove(Board board, Piece piece, int newPosition) {
        super(board, piece, newPosition);
    }

    @Override
    public Board execute() {
        Board.Builder builder = new Board.Builder();

        for (Piece piece : this.board.currentPlayer().getPieces()) { // Change the var names this is confusing
            if (!this.piece.equals(piece)) {
                builder.setPiece(piece);
            }
        }

        // getPlayer() means opponent and this needs to change

        for (Piece piece : this.board.currentPlayer().getPlayer().getPieces()) {
            builder.setPiece(piece);
        }

        Pawn movedPawn = (Pawn)this.piece.movePiece(this);
        builder.setEnPassantPawn(movedPawn);
        builder.setMoveMaker(this.board.currentPlayer().getPlayer().getColour());
        return builder.build();
    }
}
