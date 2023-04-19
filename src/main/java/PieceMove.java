public class PieceMove extends Move {

    public PieceMove(Board board, Piece piece, int newPosition) {
        super(board, piece, newPosition);
    }

    @Override
    public boolean equals (Object other) {
        return this == other || other instanceof PieceMove && super.equals(other);
    }

}
