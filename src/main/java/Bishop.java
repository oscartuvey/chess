import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private final static int[] POSSIBLE_MOVES_VECTOR = {-9, -7, 7, 9};

    Bishop(int position, Colour colour) {
        super(position, colour, PieceType.BISHOP);
    }

    @Override
    public List<Move> findLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (int vector : POSSIBLE_MOVES_VECTOR) {
            int newPosition = this.position + vector;

            while (isValidMove(newPosition)) {

                if (isFirstColumnExclusion(position, vector) || isEighthColumnExclusion(position, vector)) {
                    break;
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
                newPosition += vector;

            }
        }


        return legalMoves;
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }

    // Optimisation here
    // Can precompute all the possible bishops
    // Video # 22  5:00

    @Override
    public Piece movePiece(Move move) {
        return new Bishop(move.getNewPosition(), move.getPiece().getColour());
    }

    private static boolean isFirstColumnExclusion(int position, int vector) {
        return BoardUtility.FIRST_COLUMN[position] && (vector == -9 || vector == 7);
    }

    private static boolean isEighthColumnExclusion(int position, int vector) {
        return BoardUtility.EIGHTH_COLUMN[position] && (vector == 9 || vector == -7);
    }

    private boolean isValidMove(int position) {
        return position >= 0 && position < 64; // Redo this to make it available elsewhere
    }
}
