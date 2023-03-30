import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {

    protected Board board;
    protected King king;
    protected Collection<Move> legalMoves;
    private final boolean inCheck;

    Player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMoves) {
        this.board = board;
        this.king = initialiseKing();
        this.legalMoves = legalMoves;
        this.inCheck = !Player.calculateAttacksOnTile(this.king.getPosition(), opponentMoves).isEmpty();
    }

    //TODO check if this method is only used to check checks in which case rename it
    private static Collection<Move> calculateAttacksOnTile(int position, Collection<Move> moves) {
        List<Move> attackMoves = new ArrayList<>();

        for (Move move : moves) {
            if (position == move.getNewPosition()) {
                attackMoves.add(move);
            }
        }

        return attackMoves;
    }

    private King initialiseKing() {
        for (Piece piece : getPieces()) {
            if (piece.getType().isKing()) {
                return (King)piece;
            }
        }
        throw new RuntimeException("Invalid board.");
    }

    public King getKing() {
        return this.king;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    public boolean isLegalMove(Move move) {
        return this.legalMoves.contains(move);
    }

    //TODO implement below methods
    public boolean inCheck() {
        return this.inCheck;
    }

    public boolean inCheckMate() {
        return this.inCheck && !hasEscapeMoves();
    }

    public boolean inStaleMate() {
        return !this.inCheck() && !hasEscapeMoves();
    }

    public boolean isCastled() {
        return false;
    }

    public Outcome movePiece(Move move) {
        return null;
    }

    protected boolean hasEscapeMoves() {
        for (Move move : this.legalMoves) {
            Outcome outcome = makeMove(move) ;
            if (outcome.getStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    public Outcome makeMove(Move move) {

        if (!isLegalMove(move)) {
            return new Outcome(this.board, move, Status.ILLEGAL_MOVE);
        }

        Board newBoard = move.execute();

        Collection<Move> kingAttacks = // Finds the attacks on the opposing king
                Player.calculateAttacksOnTile(newBoard.currentPlayer().getPlayer().getKing().getPosition(),
                newBoard.currentPlayer().getLegalMoves());

        if (!kingAttacks.isEmpty()) { // Do comments to explain whats going on here
            return new Outcome(this.board, move, Status.PLAYER_IN_CHECK);
        }

        return new Outcome(newBoard, move, Status.DONE);
    }

    public abstract Collection<Piece> getPieces();

    public abstract Colour getColour();

    public abstract Player getPlayer();


}
