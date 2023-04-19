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

    // Call location and destination when changing the names
    public int getPosition() {
        return this.piece.getPosition();
    }

    public Board execute() {
        // Go through all of current player's pieces
        // Set pieces on the outbound board which arent the current moved piece
        // Do the same thing for othe rplayer
        // Since they don thave a moved piece you can just iterate through the pieves without
        // checking for the moved piece


        Board.Builder builder = new Board.Builder();
        // piece represents the moved piece
        // piece1 represents the piece of the current player
        for (Piece p : this.board.currentPlayer().getPieces()) { // rename piece1

            if (!this.piece.equals(p)) { // THis cant be righ
                builder.setPiece(p);
            }
        }

        // write method called to all active pieces
        // piece2 represents the piece of the opposing player
        // getPlayer() means get opponent()
        for (Piece p : this.board.currentPlayer().getPlayer().getPieces()) {
            builder.setPiece(p);
        }

        // Move the moved piece
        builder.setPiece(this.piece.movePiece(this)); // 'piece' here represents the moved peice

        // sets the move maker to be the other player
        builder.setMoveMaker(this.board.currentPlayer().getPlayer().getColour()); // Refactor these names as they are confusing

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
    } //  #37 15:00

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

        // Check this return statement is right (see above reference)
        return this.getPosition() == otherMove.getPosition() &&
                piece == otherMove.getPiece() &&
                newPosition == otherMove.getNewPosition(); // check
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
