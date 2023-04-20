import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        super(board, whiteLegalMoves, blackLegalMoves);
    }

    @Override
    public Collection<Piece> getPieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Colour getColour() {
        return Colour.WHITE;
    }

    public Player getPlayer() {
        return this.board.getBlackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals) {

        List<Move> kingCastles = new ArrayList<>();
        // WHITES KING SIDE CASTLE. This class redo it
        if (this.king.isFirstMove() && !this.inCheck()) {
            if (!this.board.getSquare(61).isOccupied() && !this.board.getSquare(62).isOccupied()) {
                Square rookSquare = this.board.getSquare(63);
                // Can compound this if statement (same applies below)
                if (rookSquare.isOccupied() && rookSquare.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(62, opponentLegals).isEmpty() &&
                        rookSquare.getPiece().getType().isRook()) {
                    }

                    kingCastles.add(new CastleMoveKingSide(this.board,
                            this.king,
                            62,
                            (Rook)rookSquare.getPiece(),
                            rookSquare.getSquarePosition(),
                            61));
                }
            }
            // Difference between isFirstMove and isFirstMove()? Why do  I need them both exactly?
            if (!this.board.getSquare(59).isOccupied() && !this.board.getSquare(58).isOccupied() &&
            !this.board.getSquare(57).isOccupied()) {
                Square rookSquare = this.board.getSquare(56);
                if (rookSquare.isOccupied() && rookSquare.getPiece().isFirstMove() &&
                        Player.calculateAttacksOnTile(58, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(59, opponentLegals).isEmpty() &&
                        rookSquare.getPiece().getType().isRook()) {
                    //TODO add castle move
                    kingCastles.add(new CastleMoveQueenSide(this.board,
                            this.king,
                            58,
                            (Rook) rookSquare.getPiece(),
                            rookSquare.getSquarePosition(),
                            59));
                }
            }
        }


        return kingCastles;
    }

}
