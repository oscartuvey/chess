public class PawnMove extends Move {

    public PawnMove(Board board, Piece piece, int newPosition) {
        super(board, piece, newPosition);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PawnMove otherPawnMove = (PawnMove) obj;

        return super.equals(otherPawnMove);
    }

}
