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
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {

        this.primaryStage = new Stage();

        this.squares = new ArrayList<>();
        this.board = Board.initialiseBoard();

        this.root = new GridPane();
        root.setHgap(0.0);
        root.setVgap(0.0);

        for (int i = 0; i < 64; i++) {
            SquareNode square = new SquareNode(i);

            this.squares.add(square);

            root.add(square, i % BoardUtility.NUM_COLS, i / BoardUtility.NUM_ROWS);
        }

        Scene scene = new Scene(root, RECTANGLE_WIDTH * BoardUtility.NUM_COLS, RECTANGLE_HEIGHT * BoardUtility.NUM_ROWS);

        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("Chess");
        this.primaryStage.show();
    }

    public void updateBoard(Board board) {

        Test test = new Test();

        test.startTimer();

        GridPane root = this.getRoot();

        root.getChildren().clear();

        for (SquareNode square : squares) {

            if (board.getSquare(square.getPosition()).isOccupied() &&
            board.getSquare(square.getPosition()).getPiece() instanceof King) {
                square.drawKingSquare(board);
            }
            else {
                square.drawSquare(board);
            }

            int i = square.getPosition();

            root.add(square, i % BoardUtility.NUM_COLS, i / BoardUtility.NUM_ROWS);
        }

        if (board.getWhitePlayer().inCheckMate() || board.getBlackPlayer().inCheckMate()) {
                this.primaryStage.close();
                this.primaryStage = new Stage();
                start(this.primaryStage);
        }

        System.out.print("Board update ");
        test.stopTimer();

    }

    public GridPane getRoot() {
        return this.root;
    }

    public class SquareNode extends StackPane {

        private final int position;
        private Rectangle square;
        private ImageView imageView;


        SquareNode(int position) {

            super();
            this.position = position;
            this.square = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
            this.imageView = new ImageView();
            setColour();
            setPiece(board);

            setOnMouseClicked(event -> {
                Test testClick = new Test();
                testClick.startTimer();
                if (event.getButton() == MouseButton.SECONDARY) {
                    source = null;
                    destination = null;
                    movedPiece = null;
                }
                else if (event.getButton() == MouseButton.PRIMARY) {

                    if (source == null) {
                        source = board.getSquare(position);

                        movedPiece = source.getPiece();
                        if (movedPiece == null) {
                            source = null;
                        }
                    }
                    else {

                        destination = board.getSquare(position);
                        Move move = MoveFactory.createMove(board, source.getSquarePosition(), destination.getSquarePosition());
                        Outcome outcome = board.currentPlayer().makeMove(move);
                        if (outcome.getStatus().isDone()) {
                            board = outcome.getBoard();
                        }

                        source = null;
                        destination = null;
                        movedPiece = null;
                    }
                    updateBoard(board);
                    System.out.print("Piece click ");
                    testClick.stopTimer();
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

        public void drawKingSquare(Board board) {

            if ((board.getBlackPlayer().inCheck() && !board.getSquare(this.position).getPiece().getColour().isWhite()) ||
                (board.getWhitePlayer().inCheck() && board.getSquare(this.position).getPiece().getColour().isWhite())) {
                this.getChildren().clear();
                setKingColor();
                setPiece(board);
                showLegalMoves(board);

            }
            else {
                drawSquare(board);
            }
        }

        public void drawSquare(Board board) {

            this.getChildren().clear();
            setColour();
            setPiece(board);
            showLegalMoves(board);

        }

        public void setColour() {
            this.square.setFill((this.position % BoardUtility.NUM_COLS + this.position / BoardUtility.NUM_ROWS) % 2 == 0 ? Color.SADDLEBROWN : Color.TAN);
            this.getChildren().add(this.square);
        }

        public void setKingColor() {
            this.square.setFill(Color.RED);
            this.getChildren().add(this.square);
        }

        public void setPiece(Board board) {
            if (board.getSquare(this.position).isOccupied()) {
                String pngFile = renderPiece(board.getSquare(this.position).toString());

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

                    if (board.getSquare(this.getPosition()).isOccupied() &&
                        !board.getSquare(this.getPosition()).getPiece().isKing()) {
                        this.getSquare().setFill(Color.GREEN);
                    }
                    else {
                        String pngFile = "greenDot.png";
                        InputStream filePath = this.getClass().getResourceAsStream(pngFile);
                        Image image = new Image(filePath, RECTANGLE_WIDTH, RECTANGLE_HEIGHT, false, false);
                        this.imageView.setImage(image);
                        this.getChildren().add(this.imageView);
                    }
                }
            }
        }

        public Collection<Move> findLegalMoves(Board board) {

            List<Move> pieceMoves = new ArrayList<>();

            System.out.println("Current player moves: " + board.currentPlayer().getLegalMoves().size());

            if (movedPiece != null && movedPiece.getColour() == board.currentPlayer().getColour()) {
                for (Move move : board.getAllLegalMoves()) {
                    if (move.getPiece() == movedPiece) {
                        pieceMoves.add(move);
                    }
                }

                return pieceMoves;
            }
            else {
                return Collections.emptyList();
            }
        }

        public Rectangle getSquare() {
            return this.square;
        }
    }
}