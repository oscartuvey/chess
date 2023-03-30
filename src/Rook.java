import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private final static int[] POSSIBLE_MOVES_VECTOR = {-8, -1, 1, 8};

    public Rook(int position, Colour colour) {
        super(position, colour, PieceType.ROOK);
    }

    @Override
    public List<Move> findLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (int vector : POSSIBLE_MOVES_VECTOR) {
            int newPosition = this.position;// Does this mean it can move to its current position?

            while (isValidMove(newPosition)) {
                newPosition += vector;

                if (isFirstColumnExclusion(position, vector) || isEighthColumnExclusion(position, vector)) {
                    break; //  Check this is correct (compare to knight)
                }

                final Square square = board.getSquare(newPosition);

                if (isValidMove(newPosition)) { //isValidSquare is wrong look at knight
                    if(!square.isOccupied()) {
                        legalMoves.add(new PieceMove(board, this, newPosition)); // Check this is right
                    }
                    else {
                        Piece piece = square.getPiece();
                        Colour colour = piece.getColour();

                        if (this.colour != colour) {
                            legalMoves.add(new CaptureMove(board, this, newPosition, piece)); // Revisit video 6 to check this is corredct
                        }
                        break;
                    }
                }
            }
        }

        return legalMoves;
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }

    @Override
    public Piece movePiece(Move move) {
        return new Rook(move.getNewPosition(), move.getPiece().getColour());
    }

    private static boolean isFirstColumnExclusion(int position, int vector) {
        return BoardUtility.FIRST_COLUMN[position] && (vector == -1);
    }

    private static boolean isEighthColumnExclusion(int position, int vector) {
        return BoardUtility.EIGHTH_COLUMN[position] && (vector == 1);
    }

    private boolean isValidMove(int position) {
        return position >= 0 && position < 64; // Redo this to make it available elsewhere
    }
}
