public class PawnMovePromotion extends Move {

    Move move;
    Pawn pawn;

    public PawnMovePromotion(Move move) {
        super(move.getBoard(), move.getPiece(), move.getNewPosition());
        this.move = move;
        this.pawn = (Pawn)move.getPiece();
    }

    @Override
    public Board execute() {

        Board board = this.move.execute();
        Board.Builder builder = new Board.Builder();
        for (Piece piece : board.currentPlayer().getPieces()) {
            if (!this.pawn.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (Piece piece : board.currentPlayer().getPlayer().getPieces()) {
            builder.setPiece(piece);
        }
        builder.setPiece(this.pawn.getPromotionPiece().movePiece(this));

        builder.setMoveMaker(board.currentPlayer().getColour());
        return builder.build();
    }

    @Override
    public boolean isAttack() {
        return this.move.isAttack();
    }

    @Override
    public Piece getCapturedPiece() {
        return this.move.getCapturedPiece();
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
        PawnMovePromotion otherPawnMovePromotion = (PawnMovePromotion) obj;


        return super.equals(otherPawnMovePromotion);
    }

}
