import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves); // Swapped round white and black deliberately
    }


    @Override
    public Collection<Piece> getPieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Colour getColour() {
        return Colour.BLACK;
    }

    public Player getPlayer() {
        return this.board.getWhitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals) {

        List<Move> kingCastles = new ArrayList<>();
        // WHITES KING SIDE CASTLE
        if (this.king.isFirstMove() && !this.inCheck()) {
            if (!this.board.getSquare(5).isOccupied() && !this.board.getSquare(6).isOccupied()) {
                Square rookSquare = this.board.getSquare(7);

                // Can compound this if statement (same applies below)
                if (rookSquare.isOccupied() && rookSquare.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(5, opponentLegals).isEmpty() && //TODO  Why are both tiles being checked
                            Player.calculateAttacksOnTile(6, opponentLegals).isEmpty() && //TODO  WHy are both tiles being checked
                            rookSquare.getPiece().getType().isRook()) {

                    }
                    kingCastles.add(new CastleMoveKingSide(this.board,
                            this.king,
                            6,
                            (Rook)rookSquare.getPiece(),
                            rookSquare.getSquarePosition(),
                            5));
                }
            }
            // Difference between isFirstMove and isFirstMove()? Why do  I need them both exactly?
            if (!this.board.getSquare(1).isOccupied() && !this.board.getSquare(2).isOccupied() &&
                    !this.board.getSquare(3).isOccupied()) {
                Square rookSquare = this.board.getSquare(0);
                if (rookSquare.isOccupied() && rookSquare.getPiece().isFirstMove() &&
                Player.calculateAttacksOnTile(2, opponentLegals).isEmpty() &&
                Player.calculateAttacksOnTile(3, opponentLegals).isEmpty() &&
                rookSquare.getPiece().getType().isRook()) { // TODO check whether the position the king moves to is under atacc
                    kingCastles.add(new CastleMoveQueenSide(this.board,
                            this.king,
                            2,
                            (Rook) rookSquare.getPiece(),
                            rookSquare.getSquarePosition(),
                            3));
                }
            }
        }

        return kingCastles;
    }
}
