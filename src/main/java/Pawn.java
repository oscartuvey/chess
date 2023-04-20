import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] POSSIBLE_MOVES = {7, 8, 9, 16};

    public Pawn(int position, Colour colour) {
        super(position, colour, PieceType.PAWN, true);
    }

    public Pawn(int position, Colour colour, boolean isFirstMove) {
        super(position, colour, PieceType.PAWN, true);
    }

    @Override
    public List<Move> findLegalMoves(Board board) {

        List<Move> legalMoves = new ArrayList<>();
        for (int move : POSSIBLE_MOVES) {

            int newPosition = this.position + (move * this.getColour().getDirection());

            if (!isValidMove(newPosition)) {
                continue;
            }

            if (isFirstColumnExclusion(this.position, move * this.getColour().getDirection()) ||
                isEighthColumnExclusion(this.position, move * this.getColour().getDirection())) {
                continue; // Means oyu dont need the exclusions. This will
            }

            Square square = board.getSquare(newPosition);
            Piece piece = square.getPiece();

            if (move == 8 && !board.getSquare(newPosition).isOccupied()) {
                if (this.colour.isPromotionSquare(newPosition)) {
                    legalMoves.add(new PawnMovePromotion(new PawnMove(board, this, newPosition)));
                }
                else {
                    legalMoves.add(new PawnMove(board, this, newPosition)); // Put this in an else
                }
            }

            else if (move == 16  && !board.getSquare(newPosition).isOccupied()) {

                int behindPosition = this.position + (8 * this.getColour().getDirection());

                if (!board.getSquare(behindPosition).isOccupied()) {
                    if (this.getColour().isWhite() && BoardUtility.SEVENTH_ROW[this.position]) {
                        legalMoves.add(new PawnDoubleMove(board, this, newPosition));
                    }
                    else if (!this.getColour().isWhite() && BoardUtility.SECOND_ROW[this.position]) {
                        legalMoves.add(new PawnDoubleMove(board, this, newPosition));
                    }
                }

            }

            else if (move == 7 && board.getSquare(newPosition).isOccupied()) { // If this isn't covered by column exclusions
                // then #43 11:50
                if (this.getColour().isWhite() && !(board.getSquare(newPosition).getPiece().getColour().isWhite())) {
                    if (this.colour.isPromotionSquare(newPosition)) {
                        legalMoves.add(new PawnMovePromotion(new PawnMove(board, this, newPosition)));
                    }
                    else {
                        legalMoves.add(new PawnCaptureMove(board, this, newPosition, piece));
                    }
                }
                else if (!(this.getColour().isWhite()) && board.getSquare(newPosition).getPiece().getColour().isWhite()) {
                    if (this.colour.isPromotionSquare(newPosition)) {
                        legalMoves.add(new PawnMovePromotion(new PawnMove(board, this, newPosition)));
                    }
                    else {
                        legalMoves.add(new PawnCaptureMove(board, this, newPosition, piece));
                    }
                }
                else if (board.getEnPassantPawn() != null) {
                    // Multiplying by -1 is the same as finding the opposite direction
                    // En Passant pawns will be horizontally equal to the pawn capturing it
                    int enPassantPawnDirection = this.getPosition() + (this.getColour().getDirection() * -1); // Check
                    if (board.getEnPassantPawn().getPosition() == this.getPosition() + (enPassantPawnDirection)) {
                        Piece enPassantPawn = board.getEnPassantPawn();
                        if (this.colour != enPassantPawn.getColour()) {
                            legalMoves.add(new PawnCaptureMoveEnPassant(board, this, newPosition, enPassantPawn));
                        }
                    }
                }
            }

            else if (move == 9 && board.getSquare(newPosition).isOccupied()) {
                if (this.getColour().isWhite() && !(board.getSquare(newPosition).getPiece().getColour().isWhite())) {
                    if (this.colour.isPromotionSquare(newPosition)) {
                        legalMoves.add(new PawnMovePromotion(new PawnMove(board, this, newPosition)));
                    }
                    else {
                        legalMoves.add(new PawnCaptureMove(board, this, newPosition, piece));
                    }
                }
                else if (!(this.getColour().isWhite()) && board.getSquare(newPosition).getPiece().getColour().isWhite()) {
                    if (this.colour.isPromotionSquare(newPosition)) {
                        legalMoves.add(new PawnMovePromotion(new PawnMove(board, this, newPosition)));
                    }
                    else {
                        legalMoves.add(new PawnCaptureMove(board, this, newPosition, piece));
                    }
                }
                // TODO I have a feeling the column exclusions won't catch this so this must be tested
                else if (board.getEnPassantPawn() != null) {
                    // Multiplying by -1 is the same as finding the opposite direction
                    // En Passant pawns will be horizontally equal to the pawn capturing it
                    int enPassantPawnDirection = this.getPosition() - (this.getColour().getDirection() * -1);
                    if (board.getEnPassantPawn().getPosition() == this.getPosition() + (enPassantPawnDirection)) {
                        Piece enPassantPawn = board.getEnPassantPawn();
                        if (this.colour != enPassantPawn.getColour()) {
                            legalMoves.add(new PawnCaptureMoveEnPassant(board, this, newPosition, enPassantPawn));
                        }
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
        return BoardUtility.FIRST_COLUMN[position] && (vector == -9 || vector == -1 || vector == 7); // Check this works
    }

    private static boolean isEighthColumnExclusion(int position, int vector) {
        return BoardUtility.EIGHTH_COLUMN[position] && (vector == 9 || vector == 1 || vector == -7); // -7?
    }

    public Piece getPromotionPiece() {
        return new Queen(this.position, this.colour, false);
    }


}
