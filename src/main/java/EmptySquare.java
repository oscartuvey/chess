public class EmptySquare extends Square {

    EmptySquare(final int position) {
        super(position);
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }
}
