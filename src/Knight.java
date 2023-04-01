import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private final static int[] POSSIBLE_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17}; // Check

    Knight(int position, Colour colour) {
        super(position, colour, PieceType.KNIGHT);
    }

    @Override
    public List<Move> findLegalMoves(Board board) {

        int newPosition;
        List<Move> legalMoves = new ArrayList<>();

        for (int move : POSSIBLE_MOVES) {
            newPosition = this.position + move;

            if (isValidMove(newPosition)) {

                if (isFirstColumnExclusion(this.position, move) || isSecondColumnExclusion(this.position, move) // Im not sure this is right
                || isSeventhColumnExclusion(this.position, move) || isEighthColumnExclusion(this.position, move)) {
                    break; // Check break works properly
                }

                Square square = board.getSquare(newPosition);

                if(!square.isOccupied()) {
                    legalMoves.add(new PieceMove(board, this, newPosition)); // Check this is right
                }
                else {
                    Piece piece = square.getPiece();
                    Colour colour = piece.getColour();

                    if (this.colour != colour) {
                        legalMoves.add(new CaptureMove(board, this, newPosition, piece)); // Revisit video 6 to check this is corredct
                    }
                }
            }
        }

        return legalMoves;// Figure whether to do the immutable list thing
    }

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    @Override
    public Piece movePiece(Move move) {
        return new Knight(move.getNewPosition(), move.getPiece().getColour());
    }

    private boolean isValidMove(int position) {
        return position >= 0 && position < 64; // Redo this to make it available elsewhere
    }

    private static boolean isFirstColumnExclusion(int position, int move) { // Change var names

        return BoardUtility.FIRST_COLUMN[position] && ((move == -17) || (move == -10)
                || (move == 6) || (move == 15));
    }

    private static boolean isSecondColumnExclusion(int position, int move) {

        return BoardUtility.SECOND_COLUMN[position] && ((move == -10) || (move == 6));
    }

    private static boolean isSeventhColumnExclusion(int position, int move) {

        return BoardUtility.SEVENTH_COLUMN[position] && ((move == 10) || (move == -6));
    }

    private static boolean isEighthColumnExclusion(int position, int move) {

        return BoardUtility.EIGHTH_COLUMN[position] && ((move == 17) || (move == 10)
                || (move == -6) || (move == -15));
    }
}