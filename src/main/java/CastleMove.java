public abstract class CastleMove extends Move {

    protected Rook rook;
    protected int rookPosition;
    protected int rookDestination;

    protected CastleMove(Board board, Piece piece, int position, Rook rook, int rookPosition, int rookDestination) {
        super(board, piece, position);
        this.rook = rook;
        this.rookPosition = rookPosition;
        this.rookDestination = rookDestination;
    }


    public Rook getRook() {
        return this.rook;
    }

    @Override
    public boolean isCastleMove() {
        return true;
    }

    @Override
    public Board execute() {
        Board.Builder builder = new Board.Builder();

        for (Piece piece : this.board.currentPlayer().getPieces()) { // Change the var names this is confusing
            if (!this.piece.equals(piece) && !this.rook.equals(piece)) {
                builder.setPiece(piece);
            }
        }

        // getPlayer() means opponent and this needs to change

        for (Piece piece : this.board.currentPlayer().getPlayer().getPieces()) {
            builder.setPiece(piece);
        }

        builder.setPiece(this.piece.movePiece(this)); // Check this 'piece' is actually the movedPiece

        // TODO fix this setPiece part
        builder.setPiece(new Rook(this.rookDestination, this.rook.getColour()));
        builder.setMoveMaker(this.board.currentPlayer().getPlayer().getColour());

        return builder.build();
    }



}
