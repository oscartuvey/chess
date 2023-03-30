import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] POSSIBLE_MOVES = {7, 8, 9, 16};

    public Pawn(int position, Colour colour) {
        super(position, colour, PieceType.PAWN);
    }

    @Override
    public List<Move> findLegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();

        for (int move : POSSIBLE_MOVES) {

            int newPosition = this.position + (move * this.getColour().getDirection());

            if(!isValidMove(newPosition)) {
                continue;
            }
            // Some of this is wrong (#11 of the video)
            if (move == 8 && !board.getSquare(newPosition).isOccupied()) { // This code smell will go when other possible move vectors added


                //TODO promotions
                legalMoves.add(new PawnMove(board, this, newPosition));
            }
            // This needs to be redone
            else if (move == 16 && this.isFirstMove() // Review the error here URGENT
                    && (BoardUtility.SECOND_ROW[this.position] && !this.getColour().isWhite()) ||
                    BoardUtility.SEVENTH_ROW[this.position] && this.getColour().isWhite()) {
                int behindPosition = this.position + (this.colour.getDirection() * 8);
                if(!board.getSquare(behindPosition).isOccupied() && !board.getSquare(behindPosition).isOccupied()) {
                    legalMoves.add(new PawnMove(board, this, newPosition));
                }
            }
            //
            else if (move == 7 && !(BoardUtility.EIGHTH_COLUMN[this.position] && this.colour.isWhite() || // These are the wrong way round maybe
                    BoardUtility.FIRST_COLUMN[this.position] && !this.colour.isWhite())) { // One edge case
                if (board.getSquare(newPosition).isOccupied()) {
                    Piece piece = board.getSquare(newPosition).getPiece();
                    if (this.colour != piece.colour) {
                        //TODO more to do here
                        legalMoves.add(new PieceMove(board, this, newPosition));
                    }
                }
            }
            else if (move == 9 && !((BoardUtility.EIGHTH_COLUMN[this.position] && !this.colour.isWhite()) ||
                    BoardUtility.FIRST_COLUMN[this.position] && this.colour.isWhite())) { // One edge case
                if (board.getSquare(newPosition).isOccupied()) {
                    Piece piece = board.getSquare(newPosition).getPiece();
                    if (this.colour != piece.colour) {
                        //TODO more to do here
                        legalMoves.add(new PieceMove(board, this, newPosition));
                    }
                }
            }
        }

        return legalMoves;
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

    @Override
    public Piece movePiece(Move move) {
        return new Pawn(move.getNewPosition(), move.getPiece().getColour());
    }

    private boolean isValidMove(int position) {
        return position >= 0 && position < 64; // Redo this to make it available elsewhere
    }

    private static boolean isFirstColumnExclusion(int position, int vector) {
        return BoardUtility.FIRST_COLUMN[position] && (vector == -9 || vector == -1 || vector == 7);
    }

    private static boolean isEighthColumnExclusion(int position, int vector) {
        return BoardUtility.EIGHTH_COLUMN[position] && (vector == 9 || vector == 1 || vector == -7); // -7?
    }


}
