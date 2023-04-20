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

        for (Piece piece : this.board.currentPlayer().getPieces()) {
            if (!this.piece.equals(piece) && !this.rook.equals(piece)) {
                builder.setPiece(piece);
            }
        }

        for (Piece piece : this.board.currentPlayer().getPlayer().getPieces()) {
            builder.setPiece(piece);
        }

        builder.setPiece(this.piece.movePiece(this));

        // TODO fix this setPiece part
        builder.setPiece(new Rook(this.rookDestination, this.rook.getColour()));
        builder.setMoveMaker(this.board.currentPlayer().getPlayer().getColour());

        return builder.build();
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
        CastleMove otherCastleMove = (CastleMove) obj;

        // Check this return statement is right and in other cases
        return super.equals(otherCastleMove) && this.rook.equals(otherCastleMove.getRook());
    }
}

