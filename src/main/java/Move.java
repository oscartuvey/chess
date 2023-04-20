import java.util.Objects;

public abstract class Move {

    protected Board board;
    protected Piece piece;
    protected int newPosition;
    protected boolean isFirstMove;

    public static final Move NULL_MOVE = new InvalidMove();


    public Move(Board board, Piece piece, int newPosition ) {
        this.board = board;
        this.piece = piece;
        this.newPosition = newPosition;
        this.isFirstMove = piece.isFirstMove();
    }

    public Move(Board board, int newPosition) {
        this.board = board;
        this.newPosition = newPosition;
        this.piece = null;
        this.isFirstMove = false;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getNewPosition() {
        return this.newPosition;
    }

    public int getPosition() {
        return this.piece.getPosition();
    }

    public Board execute() {


        Board.Builder builder = new Board.Builder();

        for (Piece p : this.board.currentPlayer().getPieces()) {

            if (!this.piece.equals(p)) {
                builder.setPiece(p);
            }
        }

        for (Piece p : this.board.currentPlayer().getPlayer().getPieces()) {
            builder.setPiece(p);
        }

        builder.setPiece(this.piece.movePiece(this));

        builder.setMoveMaker(this.board.currentPlayer().getPlayer().getColour());

        return builder.build();
    }

    public Piece getPiece() {
        return this.piece;
    }

    public boolean getIsFirstMove() {
        return this.isFirstMove;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, piece, newPosition, isFirstMove);
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
        Move otherMove = (Move) obj;

        return this.getPosition() == otherMove.getPosition() &&
                piece == otherMove.getPiece() &&
                newPosition == otherMove.getNewPosition();
    }

    public boolean isAttack() {
        return false;
    }

    public boolean isCastleMove() {
        return false;
    }

    public Piece getCapturedPiece() {
        return null;
    }


}
