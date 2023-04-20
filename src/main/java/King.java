import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final int[] POSSIBLE_MOVES = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(int position, Colour colour) {
        super(position, colour, PieceType.KING, true);
    }

    public King(int position, Colour colour, boolean isFirstMove) {
        super(position, colour, PieceType.KING, isFirstMove);
    }

    @Override
    public List<Move> findLegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        int newPosition;

        for (int move : POSSIBLE_MOVES) {
            newPosition = this.position + move;

            if (isFirstColumnExclusion(this.position, move) || isEighthColumnExclusion(this.position, move)) {
                break;
            }

            if (isValidMove(newPosition)) {
                Square square = board.getSquare(newPosition);
                if (!square.isOccupied()) {
                    legalMoves.add(new PieceMove(board, this, newPosition));
                } else {
                    Piece piece = square.getPiece();
                    Colour colour = piece.getColour();
                    if (this.getColour() != colour) {
                        legalMoves.add(new PieceCaptureMove(board, this, newPosition, piece));
                    }
                }
            }

        }

        return legalMoves;
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    @Override
    public Piece movePiece(Move move) {
        return new King(move.getNewPosition(), move.getPiece().getColour());
    }

    private boolean isValidMove(int position) {
        return position >= 0 && position < 64; // Redo this to make it available elsewhere
    }

    private static boolean isFirstColumnExclusion(int position, int move) { // Change var names

        return BoardUtility.FIRST_COLUMN[position] && ((move == 1) || (move == -7) || (move == 9));
    }

    private static boolean isEighthColumnExclusion(int position, int move) {

        return BoardUtility.EIGHTH_COLUMN[position] && ((move == -1) || (move == 7) || (move == -9));
    }
}
