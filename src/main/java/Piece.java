import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final PieceType type;
    protected final int position;
    protected final Colour colour;
    protected final boolean isFirstMove;

    Piece(final int position, final Colour colour, final PieceType type, boolean isFirstMove) {
        this.position = position;
        this.colour = colour;
        // TODO more work here
        this.isFirstMove = isFirstMove;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Piece otherPiece = (Piece) obj;

        return position == otherPiece.getPosition() && type == otherPiece.getType() &&
                colour == otherPiece.getColour() && isFirstMove == otherPiece.isFirstMove();
    }

    public int getValue() {
        return this.getType().getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, position, colour, isFirstMove);
    }

    // This is the next thing to do
    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public Colour getColour() {
        return this.colour;
    }
    public abstract List<Move> findLegalMoves(final Board board);

    public int getPosition() {
        return this.position;
    }

    public PieceType getType() {
        return this.type;
    }

    public abstract Piece movePiece(Move move); // This creates a new piece, rather than actually just changing the piece

    public boolean isKing() {
        return false;
    }
    // position

    public enum PieceType {

        PAWN( "P", 1) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        }, KNIGHT("H", 3) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        }, BISHOP("B", 3) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        }, ROOK("R", 5) {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        }, QUEEN("Q", 9) {
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K", 10) { // King value doesn't matter.
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

        private String pieceName;
        private int value;

        PieceType(String pieceName, int value) {
            this.pieceName = pieceName;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();

        public int getValue() {
            return this.value;
        }
    }

}
