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

            if (!isValidMove(newPosition)) { // THis should be preventing this from happen
                 continue;
            }

            if (isFirstColumnExclusion(this.position, move) || isEighthColumnExclusion(this.position, move)) {
                continue;
            }

            if (move == 8 && !board.getSquare(newPosition).isOccupied()) {
                //TODO promotions
                legalMoves.add(new PawnMove(board, this, newPosition));
            }

            else if (move == 16  && !board.getSquare(newPosition).isOccupied()) {

                int behindPosition = this.position + (8 * this.getColour().getDirection());

                if (!board.getSquare(behindPosition).isOccupied()) {
                    if (this.getColour().isWhite() && BoardUtility.SEVENTH_ROW[this.position]) {
                        // TODO change this to double pawn move
                        legalMoves.add(new PawnMove(board, this, newPosition));
                    }
                    else if (!this.getColour().isWhite() && BoardUtility.SECOND_ROW[this.position]) {
                        // TODO change this to double pawn move
                        legalMoves.add(new PawnMove(board, this, newPosition));
                        // SonarLint suggests making this a method since its repeated a bunch of times, probably a good idea
                    }
                }

            }
            // This is completely wrong
            else if (move == 7 && board.getSquare(newPosition).isOccupied()) {
                if (this.getColour().isWhite() && !(board.getSquare(newPosition).getPiece().getColour().isWhite())) {
                    // legalMoves.add(new PawnMove(board, this, newPosition));
                    //TODO pawn capture move
                }
                else if (!(this.getColour().isWhite()) && board.getSquare(newPosition).getPiece().getColour().isWhite()) {
                    // legalMoves.add(new PawnMove(board, this, newPosition));
                    //TODO pawn capture move
                }
            }

            else if (move == 9 && board.getSquare(newPosition).isOccupied()) {
                if (this.getColour().isWhite() && !(board.getSquare(newPosition).getPiece().getColour().isWhite())) {
                    // legalMoves.add(new PawnMove(board, this, newPosition)); // How does it know the original position?
                    //TODO pawn capture move
                }
                else if (!(this.getColour().isWhite()) && board.getSquare(newPosition).getPiece().getColour().isWhite()) {
                    // legalMoves.add(new PawnMove(board, this, newPosition));
                    //TODO pawn capture move
                }
            }

        }

        System.out.println(legalMoves.size());

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
