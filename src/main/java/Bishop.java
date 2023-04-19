import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private final static int[] POSSIBLE_MOVES_VECTOR = {-9, -7, 7, 9};

    public Bishop(int position, Colour colour) {
        super(position, colour, PieceType.BISHOP, true);
    }

    public Bishop(int position, Colour colour, boolean isFirstMove) {
        super(position, colour, PieceType.BISHOP, isFirstMove);
    }

    @Override
    public List<Move> findLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (int vector : POSSIBLE_MOVES_VECTOR) {
            System.out.println("Bishop initial position " + this.position);
            int newPosition = this.position + vector;
            System.out.println("Bishop initial position " + this.position + " new position " + newPosition);

            while (isValidMove(newPosition)) {

                if (isFirstColumnExclusion(position, vector) || isEighthColumnExclusion(position, vector)) {
                    break;
                }

                Square square = board.getSquare(newPosition);

                if (isValidMove(newPosition)) { //isValidSquare is wrong look at knight
                    if(!square.isOccupied()) {
                        legalMoves.add(new PieceMove(board, this, newPosition)); // Check this is right
                        System.out.println("added" + " " + position + " " + newPosition);
                    }
                    else {
                        Piece piece = square.getPiece();
                        Colour colour = piece.getColour();

                        if (this.colour != colour) {
                            System.out.println("Capture added" + " " + position + " " + newPosition); // Same moved being added? The same dot added over and over again
                            legalMoves.add(new PieceCaptureMove(board, this, newPosition, piece)); // REvisit #6
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


    // Can precompute all the possible bishops
    // # 22  5:00

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
