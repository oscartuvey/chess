import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Square {

    protected final int position;

    private static final Map<Integer, EmptySquare> EMPTY_SQUARES = createEmptySquares();

    private static Map<Integer,EmptySquare> createEmptySquares() {

        final Map<Integer, EmptySquare> emptySquares = new HashMap<>();

        for (int i = 0; i < 64; i++) {
            emptySquares.put(i, new EmptySquare(i));
        }

        return Collections.unmodifiableMap(emptySquares);
    }

    Square(final int position) {
        this.position = position;
    }

    public static Square createSquare(int position, Piece piece) {
        if (piece != null) {
            return new OccupiedSquare(position, piece);
        }
        else {
            return EMPTY_SQUARES.get(position);
        }
    }

    @Override
    public String toString() {
        return "-";
    }

    public abstract Piece getPiece();

    public abstract boolean isOccupied();

}
