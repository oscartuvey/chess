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

        if (this.getPiece().getColour().isWhite()) { // TODO Check this works properly (11:03 #16)
            return this.toString();
        }
        else {
            return this.toString().toLowerCase();
        }


    }

}
