import java.util.Objects;

public abstract class Move {

    final Board board;
    final Piece piece;
    final int newPosition;

    public static final Move NULL_MOVE = new InvalidMove();


    public Move(Board board, Piece piece, int newPosition ) {
        this.board = board;
        this.piece = piece;
        this.newPosition = newPosition;
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
        for (Piece piece1 : this.board.currentPlayer().getPieces()) { // rename piece1

            if (!this.piece.equals(piece1)) {
                builder.setPiece(piece1);
            }
        }

        // write method called to all active pieces
        // piece2 represemts the piece of the opposing player
        // getPlayer() means get opponent()
        for (Piece piece2 : this.board.currentPlayer().getPlayer().getPieces()) {
            builder.setPiece(piece2);
        }

        // Move the moved piece
        builder.setPiece(this.piece.movePiece(this)); // 'piece' here represents the moved peice

        // sets the move maker to be the other player
        builder.setMoveMaker(this.board.currentPlayer().getPlayer().getColour());

        // TODO explain to self what builder.build() does
        return builder.build();
    };

    public Piece getPiece() {
        return this.piece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, piece, newPosition);
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

        // Check this return statement is right
        return board == otherMove.getBoard() && piece == otherMove.getPiece() &&
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
