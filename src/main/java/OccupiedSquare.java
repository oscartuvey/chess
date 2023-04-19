public class OccupiedSquare extends Square {

    private final Piece piece;

    OccupiedSquare(final int position, Piece piece) {
        super(position);
        this.piece = piece;
    }

    @Override
    public boolean isOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.piece;
    }

    @Override
    public String toString() {

        if (this.getPiece().getColour().isWhite()) {
            return this.getPiece().toString();
        }
        else {
            return this.getPiece().toString().toLowerCase();
        }


    }

}
