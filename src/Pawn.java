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

            else if (move == 16  && (BoardUtility.SEVENTH_ROW[this.position] &&
                    this.getColour().isWhite()) ||
                    BoardUtility.SECOND_ROW[this.position] && !(this.getColour().isWhite())) {

                int behindPosition = this.position + (this.getColour().getDirection() * 8);
                // This is the only place which was being added
                if(board.getSquare(behindPosition).isOccupied() && !board.getSquare(behindPosition).isOccupied()) {
                    legalMoves.add(new PawnMove(board, this, newPosition));
                    // Why was this being printed 3 times for each pawn? NOt efficient
                }

                System.out.println("test");
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
