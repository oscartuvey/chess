import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final PieceType type;
    protected final int position;
    protected final Colour colour;
    protected final boolean isFirstMove;

    Piece(final int position, final Colour colour, final PieceType type) {
        this.position = position;
        this.colour = colour;
        // TODO more work here
        this.isFirstMove = false;
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
    // position

    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        }, KNIGHT("H") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        }, BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        }, ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        }, QUEEN("Q") {
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        };

        private String pieceName;

        PieceType(String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();
    }

}
