import java.io.InputStream;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseButton;
import java.util.Collections;
import java.util.Collection;

public class ChessUI extends Application {

    private static final int RECTANGLE_WIDTH = 90;
    private static final int RECTANGLE_HEIGHT = 90;
    private Board board;
    private List<SquareNode> squares;

    private Square source;
    private Square destination;
    private Piece movedPiece;

    private GridPane root;


    @Override
    public void start(Stage stage) {

        this.squares = new ArrayList<>();
        this.board = Board.initialiseBoard();

        this.root = new GridPane();
        root.setHgap(0.0);
        root.setVgap(0.0);

        for (int i = 0; i < 64; i++) {
            SquareNode square = new SquareNode(i); // add to list too

            this.squares.add(square); // Check this is okay and work out if it's even necessary

            root.add(square, i % BoardUtility.NUM_COLS, i / BoardUtility.NUM_ROWS);
        }

        Scene scene = new Scene(root, RECTANGLE_WIDTH * BoardUtility.NUM_COLS, RECTANGLE_HEIGHT * BoardUtility.NUM_ROWS);

        stage.setScene(scene);
        stage.setTitle("Chess");
        stage.show();
    }

    public void updateBoard(Board board) {

        GridPane root = this.getRoot();

        root.getChildren().clear();

        for (SquareNode square : squares) {
            square.drawSquare(board);

            int i = square.getPosition();

            // This method is used twice so maybe refactor
            root.add(square, i % BoardUtility.NUM_COLS, i / BoardUtility.NUM_ROWS);
        }

    }

    // Change name and move this somewhere more appropriate
    public static class Moves { // Could extend the List class or something along those lines. Defo refactor

        private List<Move> moves;

        public Moves() {
            this.moves = new ArrayList<>();
        }

        public List<Move> getMoves() {
            return this.moves;
        }

        public void add(Move move) {
            this.moves.add(move);
        }

        public int size() {
            return this.moves.size();
        }

        public void clear() {
            this.moves.clear();
        }

        public void remove(Move move) {
            this.moves.remove(move);
        }

        public void remove(int index) {
            this.moves.remove(index);
        }

    }

    public GridPane getRoot() {
        return this.root;
    }

    public class SquareNode extends StackPane {

        private final int position;
        private Rectangle square;
        private ImageView imageView;


        SquareNode(int position) {
            // Remember to clear previous image off
            super();
            this.position = position;
            this.square = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
            this.imageView = new ImageView();
            setColour();
            setPiece(board);

            // Might want to change this as it looks weird but keep it for now because it should definitely work
            setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    source = null;
                    destination = null;
                    movedPiece = null;
                }
                else if (event.getButton() == MouseButton.PRIMARY) {
                    // He adds attributes fo sourcetile, destinationtile, and humanmovedpiece
                    if (source == null) {
                        // first click (remove this and below)
                        source = board.getSquare(position);

                        movedPiece = source.getPiece();
                        if (movedPiece == null) { // If i click on atile and its not an empty tile then assign piece to human moved piece otherwise undo assingment of source tile
                            source = null;
                        }
                    }
                    else {
                        // second click. Explain this to self
                        destination = board.getSquare(position);
                        Move move = MoveFactory.createMove(board, source.getSquarePosition(), destination.getSquarePosition());
                        Outcome outcome = board.currentPlayer().makeMove(move);
                        if (outcome.getStatus().isDone()) {
                            board = outcome.getBoard();
                        }

                        // This could be refactored as this is used multiple times
                        source = null;
                        destination = null;
                        movedPiece = null;
                    }

                    updateBoard(board);

                }
            });
        }

        public static String renderPiece(String piece) {
            return switch (piece) {
                case ("P") -> "wP.png";
                case ("R") -> "wR.png";
                case ("H") -> "wH.png";
                case ("B") -> "wB.png";
                case ("Q") -> "wQ.png";
                case ("K") -> "wK.png";
                case ("p") -> "bP.png";
                case ("r") -> "bR.png";
                case ("h") -> "bH.png";
                case ("b") -> "bB.png";
                case ("q") -> "bQ.png";
                case ("k") -> "bK.png";
                default -> null;
            };
        }

        public void drawSquare(Board board) {
            this.getChildren().clear();
            setColour();
            setPiece(board); // It's probably because you don't remove
            showLegalMoves(board);

        }

        public void setColour() {
            this.square.setFill((this.position % BoardUtility.NUM_COLS + this.position / BoardUtility.NUM_ROWS) % 2 == 0 ? Color.TAN : Color.SADDLEBROWN);
            this.getChildren().add(this.square); // Maybe need to clear everything from the node first
        }

        public void setPiece(Board board) {
            if (board.getSquare(this.position).isOccupied()) {
                String pngFile = renderPiece(board.getSquare(this.position).toString());
                // Exception if filePath is null
                InputStream filePath = this.getClass().getResourceAsStream(pngFile);
                Image image = new Image(filePath, RECTANGLE_WIDTH, RECTANGLE_HEIGHT, false, false);
                this.imageView.setImage(image);
                this.getChildren().add(this.imageView);
            }
        }

        public int getPosition() {
            return this.position;
        }

        public void showLegalMoves(Board board) {

            for (Move move : findLegalMoves(board)) {
                if (move.getNewPosition() == this.position) {
                    System.out.println(this.position); // Its trying to show a dot on a square with a piece already and crashing
                    String pngFile = "greenDot.png";
                    InputStream filePath = this.getClass().getResourceAsStream(pngFile);
                    Image image = new Image(filePath, RECTANGLE_WIDTH / 2, RECTANGLE_HEIGHT / 2, false, false);
                    this.imageView.setImage(image);
                    // This is adding two things to the bishop at once
                    this.getChildren().add(this.imageView);
                }
            }
        }

        public Collection<Move> findLegalMoves(Board board) {
            if (movedPiece != null && movedPiece.getColour() == board.currentPlayer().getColour()) {
                return movedPiece.findLegalMoves(board);
            }
            else {
                return Collections.emptyList();
            } // TODO  refactor!!!
        }
    }
}