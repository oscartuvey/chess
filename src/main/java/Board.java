import java.util.*;

public class Board {

    private final List<Square> gameState;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;
    private final Pawn enPassantPawn;

    private Board(Builder builder) {
        this.gameState = createGameState(builder);

        this.whitePieces = findActivePieces(this.gameState, Colour.WHITE);
        this.blackPieces = findActivePieces(this.gameState, Colour.BLACK);
        this.enPassantPawn = builder.enPassantPawn;

        Collection<Move> whiteLegalMoves = findLegalMoves(this.whitePieces);
        Collection<Move> blackLegalMoves = findLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteLegalMoves, blackLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteLegalMoves, blackLegalMoves);

        this.currentPlayer = builder.nextMove.choosePlayer(this.whitePlayer, this.blackPlayer);
    }

    public static Board initialiseBoard() {
        Builder builder = new Builder();

        // Black
        builder.setPiece(new Rook(0, Colour.BLACK));
        builder.setPiece(new Knight(1, Colour.BLACK));
        builder.setPiece(new Bishop(2, Colour.BLACK));
        builder.setPiece(new Queen(3, Colour.BLACK));
        builder.setPiece(new King(4, Colour.BLACK));
        builder.setPiece(new Bishop(5, Colour.BLACK));
        builder.setPiece(new Knight(6, Colour.BLACK));
        builder.setPiece(new Rook(7, Colour.BLACK));
        builder.setPiece(new Pawn(8, Colour.BLACK));
        builder.setPiece(new Pawn(9, Colour.BLACK));
        builder.setPiece(new Pawn(10, Colour.BLACK));
        builder.setPiece(new Pawn(11, Colour.BLACK));
        builder.setPiece(new Pawn(12, Colour.BLACK));
        builder.setPiece(new Pawn(13, Colour.BLACK));
        builder.setPiece(new Pawn(14, Colour.BLACK));
        builder.setPiece(new Pawn(15, Colour.BLACK));
        // White
        builder.setPiece(new Pawn(48, Colour.WHITE));
        builder.setPiece(new Pawn(49, Colour.WHITE));
        builder.setPiece(new Pawn(50, Colour.WHITE));
        builder.setPiece(new Pawn(51, Colour.WHITE));
        builder.setPiece(new Pawn(52, Colour.WHITE));
        builder.setPiece(new Pawn(53, Colour.WHITE));
        builder.setPiece(new Pawn(54, Colour.WHITE));
        builder.setPiece(new Pawn(55, Colour.WHITE));
        builder.setPiece(new Rook(56, Colour.WHITE));
        builder.setPiece(new Knight(57, Colour.WHITE));
        builder.setPiece(new Bishop(58, Colour.WHITE));
        builder.setPiece(new Queen(59, Colour.WHITE));
        builder.setPiece(new King(60, Colour.WHITE));
        builder.setPiece(new Bishop(61, Colour.WHITE));
        builder.setPiece(new Knight(62, Colour.WHITE));
        builder.setPiece(new Rook(63, Colour.WHITE));
        //white to move
        builder.setMoveMaker(Colour.WHITE);
        //build the board
        return builder.build();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < BoardUtility.NUM_TILES; i++) {
            String s = this.gameState.get(i).toString();
            stringBuilder.append(s);
            if ((i + 1) % 8 == 0) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();

    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

    public Player currentPlayer() {
        return this.currentPlayer;
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Pawn getEnPassantPawn() { // this doesnt seem right
        return this.enPassantPawn;
    }
    private Collection<Move> findLegalMoves(Collection<Piece> pieces) {
        List<Move> legalMoves = new ArrayList<>();
        // This is seen as static context?
        for (Piece piece : pieces) {
            legalMoves.addAll(piece.findLegalMoves(this));
        }

        return legalMoves;
    }

    private static Collection<Piece> findActivePieces(List<Square> gameState, Colour colour) {

        List<Piece> activePieces = new ArrayList<>();

        for (Square square : gameState) {

           if (square.isOccupied() && square.getPiece().getColour() == colour) {
                activePieces.add(square.getPiece());
           }
        }

        return activePieces;
    }


    public Square getSquare(int position) {
        return gameState.get(position);
    }

    private static List<Square> createGameState(Builder builder) {
        Square[] squares = new Square[BoardUtility.NUM_TILES]; // Creates a list with 64 tiles

        for (int i = 0; i < BoardUtility.NUM_TILES; i++) {
            squares[i] = Square.createSquare(i, builder.boardLayout.get(i));
        }

        return Arrays.asList(squares);
    }

    public Collection<Move> getAllLegalMoves() {
        List<Move> legalMoves = new ArrayList<>();
        legalMoves.addAll(this.whitePlayer.getLegalMoves());
        legalMoves.addAll(this.blackPlayer.getLegalMoves());

        return legalMoves;
    }

    public static class Builder {
        Map<Integer, Piece> boardLayout;
        Colour nextMove;
        Pawn enPassantPawn;

        public Builder() {
            this.boardLayout = new HashMap<>();
        }

        public Builder setPiece(Piece piece) {
            this.boardLayout.put(piece.getPosition(), piece);
            return this;
        }

        public Builder setMoveMaker(Colour colour) {
            this.nextMove = colour;
            return this;
        }

        public Board build() {
            return new Board(this);
        }

        public void setEnPassantPawn(Pawn pawn) {
            this.enPassantPawn = pawn;
        }

    }
}
