public abstract class Move {

    final Board board;
    final Piece piece;
    final int newPosition;

    public Move(Board board, Piece piece, int newPosition ) {
        this.board = board;
        this.piece = piece;
        this.newPosition = newPosition;
    }

    public int getNewPosition() {
        return this.newPosition;
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
}
